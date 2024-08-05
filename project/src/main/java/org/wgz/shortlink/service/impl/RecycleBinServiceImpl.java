package org.wgz.shortlink.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.wgz.shortlink.dao.entity.ShortLinkDO;
import org.wgz.shortlink.dao.mapper.ShortLinkMapper;
import org.wgz.shortlink.dto.req.RecycleBinSaveReqDTO;
import org.wgz.shortlink.service.RecycleBinService;

import static org.wgz.shortlink.common.constant.RedisKeyConstant.GOTO_SHORT_LINK_KEY;

/**
 * 回收站管理 接口实现层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO>
        implements RecycleBinService {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void saveRecycleBin(RecycleBinSaveReqDTO recycleBinSaveReqDTO) {
        LambdaUpdateWrapper<ShortLinkDO> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, recycleBinSaveReqDTO.getGid())
                .eq(ShortLinkDO::getFullShortUrl, recycleBinSaveReqDTO.getFullShortUrl())
                .eq(ShortLinkDO::getEnableStatus, 0);

        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                .enableStatus(1)
                .build();
        baseMapper.update(shortLinkDO, updateWrapper);

        // 移至回收站后删除缓存
        stringRedisTemplate.delete(String.format(GOTO_SHORT_LINK_KEY, recycleBinSaveReqDTO.getFullShortUrl()));
    }
}
