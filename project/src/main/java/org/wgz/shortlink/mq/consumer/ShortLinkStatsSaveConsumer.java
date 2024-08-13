package org.wgz.shortlink.mq.consumer;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;
import org.wgz.shortlink.common.convention.exception.ServiceException;
import org.wgz.shortlink.dao.entity.*;
import org.wgz.shortlink.dao.mapper.*;
import org.wgz.shortlink.dto.biz.ShortLinkStatsRecordDTO;
import org.wgz.shortlink.handler.MessageQueueIdempotentHandler;
import org.wgz.shortlink.mq.producer.DelayShortLinkStatsProducer;

import java.util.*;

import static org.wgz.shortlink.common.constant.RedisKeyConstant.LOCK_GID_UPDATE_KEY;
import static org.wgz.shortlink.common.constant.ShortLinkConstant.AMAP_REMOTE_URL;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShortLinkStatsSaveConsumer implements StreamListener<String, MapRecord<String, String, String>> {

    private final ShortLinkMapper shortLinkMapper;

    private final ShortLinkGotoMapper shortLinkGotoMapper;

    private final RedissonClient redissonClient;

    private final LinkAccessStatsMapper linkAccessStatsMapper;

    private final LinkLocaleStatsMapper linkLocaleStatsMapper;

    private final LinkOsStatsMapper linkOsStatsMapper;

    private final LinkBrowserStatsMapper linkBrowserStatsMapper;

    private final LinkAccessLogsMapper linkAccessLogsMapper;

    private final LinkDeviceStatsMapper linkDeviceStatsMapper;

    private final LinkNetworkStatsMapper linkNetworkStatsMapper;

    private final LinkStatsTodayMapper linkStatsTodayMapper;

    private final DelayShortLinkStatsProducer delayShortLinkStatsProducer;

    private final StringRedisTemplate stringRedisTemplate;

    private final MessageQueueIdempotentHandler messageQueueIdempotentHandler;


    @Value("${short-link.stats.locale.amap-key}")
    private String statsLocaleAmapKey;

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        String stream = message.getStream();
        RecordId id = message.getId();

        if (!messageQueueIdempotentHandler.isMessageProcessed(id.toString())) {
            if (!messageQueueIdempotentHandler.isAccomplish(id.toString())) {
                return;
            }
            throw new ServiceException("消息未完成流程，需要消息队列重试");
        }
        try {
            Map<String, String> producerMap = message.getValue();
            String fullShortUrl = producerMap.get("fullShortUrl");
            if (StrUtil.isNotBlank(fullShortUrl)) {
                String gid = producerMap.get("gid");
                ShortLinkStatsRecordDTO statsRecord = JSON.parseObject(producerMap.get("statsRecord"), ShortLinkStatsRecordDTO.class);
                actualSaveShortLinkStats(fullShortUrl, gid, statsRecord);
            }
            stringRedisTemplate.opsForStream().delete(Objects.requireNonNull(stream), id.getValue());
            messageQueueIdempotentHandler.setAccomplish(id.toString());

        } catch (Throwable throwable) {
            // 某某某情况宕机了
            messageQueueIdempotentHandler.delMessageProcessed(id.toString());
            log.error("记录短链接监控消费异常", throwable);
        }
        messageQueueIdempotentHandler.setAccomplish(id.toString());
    }

    public void actualSaveShortLinkStats(String fullShortUrl, String gid, ShortLinkStatsRecordDTO statsRecord) {
        fullShortUrl = Optional.ofNullable(fullShortUrl).orElse(statsRecord.getFullShortUrl());
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(String.format(LOCK_GID_UPDATE_KEY, fullShortUrl));
        RLock rLock = readWriteLock.readLock();
        if (!rLock.tryLock()) {
            // 在执行修改短链接操作时，防止短链接访问记录丢失
            delayShortLinkStatsProducer.send(statsRecord);
            return;
        }
        try {
            //记录当前时间
            Date now = new Date();
            if (StrUtil.isBlank(gid)) {
                LambdaQueryWrapper<ShortLinkGotoDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkGotoDO.class)
                        .eq(ShortLinkGotoDO::getFullShortUrl, fullShortUrl);
                ShortLinkGotoDO shortLinkGotoDO = shortLinkGotoMapper.selectOne(queryWrapper);
                gid = shortLinkGotoDO.getGid();

                int hour = DateUtil.hour(now, true);
                Week week = DateUtil.dayOfWeekEnum(now);
                int weekValue = week.getIso8601Value();
                LinkAccessStatsDO linkAccessStatsDO = LinkAccessStatsDO.builder()
                        .pv(1)
                        .uv(statsRecord.getUvFirstFlag() ? 1 : 0)
                        .uip(statsRecord.getUipFirstFlag() ? 1 : 0)
                        .hour(hour)
                        .weekday(weekValue)
                        .fullShortUrl(fullShortUrl)
                        .date(now)
                        .build();
                linkAccessStatsMapper.insertOrUpdateToStats(linkAccessStatsDO);

                // 通过高德地图的IP定位API
                Map<String, Object> localeParamMap = new HashMap<>();
                localeParamMap.put("key", statsLocaleAmapKey);
                localeParamMap.put("ip", statsRecord.getRemoteAddr());
                String localeResultStr = HttpUtil.get(AMAP_REMOTE_URL, localeParamMap);
                JSONObject localeResultObj = JSON.parseObject(localeResultStr);

                LinkLocaleStatsDO linkLocaleStatsDO;
                String infoCode = localeResultObj.getString("infocode");
                String actualProvince = "未知";
                String actualCity = "未知";
                if (StrUtil.isNotBlank(infoCode) && StrUtil.equals(infoCode, "10000")) {
                    String province = localeResultObj.getString("province");
                    boolean unknownFlag = StrUtil.equals(province, "[]");

                    // 记录IP地区位置
                    linkLocaleStatsDO = LinkLocaleStatsDO.builder()
                            .fullShortUrl(fullShortUrl)
                            .province(actualProvince = unknownFlag ? actualProvince : province)
                            .city(actualCity = unknownFlag ? actualCity : localeResultObj.getString("city"))
                            .adcode(unknownFlag ? "未知" : localeResultObj.getString("adcode"))
                            .date(now)
                            .cnt(1)
                            .build();
                    linkLocaleStatsMapper.shortLinkLocalStats(linkLocaleStatsDO);
                }
                LinkOsStatsDO linkOsStatsDO = LinkOsStatsDO.builder()
                        .os(statsRecord.getOs())
                        .cnt(1)
                        .fullShortUrl(fullShortUrl)
                        .date(new Date())
                        .build();
                linkOsStatsMapper.upsertLinkOsStats(linkOsStatsDO);

                LinkBrowserStatsDO linkBrowserStatsDO = LinkBrowserStatsDO.builder()
                        .browser(statsRecord.getBrowser())
                        .cnt(1)
                        .fullShortUrl(fullShortUrl)
                        .date(new Date())
                        .build();
                linkBrowserStatsMapper.shortLinkBrowserState(linkBrowserStatsDO);

                LinkDeviceStatsDO linkDeviceStatsDO = LinkDeviceStatsDO.builder()
                        .device(statsRecord.getDevice())
                        .cnt(1)
                        .fullShortUrl(fullShortUrl)
                        .date(new Date())
                        .build();
                linkDeviceStatsMapper.shortLinkDeviceState(linkDeviceStatsDO);

                LinkNetworkStatsDO linkNetworkStatsDO = LinkNetworkStatsDO.builder()
                        .network(statsRecord.getNetwork())
                        .cnt(1)
                        .fullShortUrl(fullShortUrl)
                        .date(new Date())
                        .build();
                linkNetworkStatsMapper.shortLinkNetworkState(linkNetworkStatsDO);

                LinkAccessLogsDO linkAccessLogsDO = LinkAccessLogsDO.builder()
                        .user(statsRecord.getUv())
                        .ip(statsRecord.getRemoteAddr())
                        .browser(statsRecord.getBrowser())
                        .os(statsRecord.getOs())
                        .network(statsRecord.getNetwork())
                        .device(statsRecord.getDevice())
                        .locale(StrUtil.join("-", "中国", actualProvince, actualCity))
                        .fullShortUrl(fullShortUrl)
                        .build();
                linkAccessLogsMapper.insert(linkAccessLogsDO);

                shortLinkMapper.incrementStats(gid, fullShortUrl, 1, statsRecord.getUvFirstFlag() ? 1 : 0, statsRecord.getUipFirstFlag() ? 1 : 0);

                LinkStatsTodayDO linkStatsTodayDO = LinkStatsTodayDO.builder()
                        .todayPv(1)
                        .todayUv(statsRecord.getUvFirstFlag() ? 1 : 0)
                        .todayUip(statsRecord.getUipFirstFlag() ? 1 : 0)
                        .fullShortUrl(fullShortUrl)
                        .date(new Date())
                        .build();
                linkStatsTodayMapper.shortLinkTodayState(linkStatsTodayDO);
            }
        } catch (Throwable ex) {
            log.error("短链接访问量统计异常", ex);
        }
    }
}