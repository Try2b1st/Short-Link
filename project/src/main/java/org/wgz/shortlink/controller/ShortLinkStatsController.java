package org.wgz.shortlink.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wgz.shortlink.common.convention.result.Result;
import org.wgz.shortlink.common.convention.result.Results;
import org.wgz.shortlink.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkGroupStatsReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkStatsAccessRecordReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkStatsReqDTO;
import org.wgz.shortlink.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import org.wgz.shortlink.dto.resp.ShortLinkStatsRespDTO;
import org.wgz.shortlink.service.ShortLinkStatsService;

/**
 * 短链接监控控制层
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/short-link")
public class ShortLinkStatsController {
    private final ShortLinkStatsService shortLinkStatsService;

    /**
     * 访问单个短链接指定时间内监控数据
     */
    @GetMapping("/v1/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return Results.success(shortLinkStatsService.oneShortLinkStats(requestParam));
    }

    /**
     * 访问分组内短链接指定时间内监控数据
     */
    @GetMapping("/v1/group/stats")
    public Result<ShortLinkStatsRespDTO> groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam) {
        return Results.success(shortLinkStatsService.groupShortLinkStats(requestParam));
    }

    /**
     * 访问单个短链接指定时间内监控数据
     */
    @GetMapping("/v1/stats/access-record")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return Results.success(shortLinkStatsService.shortLinkStatsAccessRecord(requestParam));
    }

    /**
     * 访问分组短链接指定时间内监控数据
     */
    @GetMapping("/v1/stats/access-record/group")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> groupShortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam) {
        return Results.success(shortLinkStatsService.groupShortLinkStatsAccessRecord(requestParam));
    }
}
