package org.wgz.shortlink.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wgz.shortlink.common.convention.result.Result;
import org.wgz.shortlink.common.convention.result.Results;
import org.wgz.shortlink.dto.req.ShortLinkStatsReqDTO;
import org.wgz.shortlink.dto.resp.ShortLinkStatsRespDTO;
import org.wgz.shortlink.service.ShortLinkStatsService;

/**
 * 短链接监控控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkStatsController {
    private final ShortLinkStatsService shortLinkStatsService;

    /**
     * 访问单个短链接指定时间内监控数据
     */
    @GetMapping("/api/short-link/v1/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return Results.success(shortLinkStatsService.oneShortLinkStats(requestParam));
    }
}
