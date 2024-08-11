package org.wgz.shortlink.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wgz.shortlink.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkGroupStatsReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkStatsAccessRecordReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkStatsReqDTO;
import org.wgz.shortlink.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import org.wgz.shortlink.dto.resp.ShortLinkStatsRespDTO;

/**
 * 短链接监控接口层
 */
public interface ShortLinkStatsService {

    /**
     * 获取单个短链接监控数据
     *
     * @param requestParam 获取短链接监控数据入参
     * @return 短链接监控数据
     */
    ShortLinkStatsRespDTO oneShortLinkStats(ShortLinkStatsReqDTO requestParam);

    /**
     * 分页查询单个短链接访问记录
     *
     * @param requestParam 请求参数
     * @return 访问数据
     */
    IPage<ShortLinkStatsAccessRecordRespDTO> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam);

    /**
     * 分页查询分组短链接访问记录
     *
     * @param requestParam 请求参数
     * @return 访问数据
     */
    IPage<ShortLinkStatsAccessRecordRespDTO> groupShortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam);

    /**
     * 获取分组短链接监控数据
     *
     * @param requestParam 获取分组监控数据入参
     * @return 分组监控数据
     */
    ShortLinkStatsRespDTO groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam);
}
