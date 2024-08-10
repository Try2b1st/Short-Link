package org.wgz.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.common.convention.result.Results;
import org.wgz.shortlink.admin.remote.ShortLinkRemoteService;
import org.wgz.shortlink.admin.remote.dto.req.*;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkStatsRespDTO;

@RestController
@RequestMapping("/api/shortLink/admin")
public class ShortLinkController {
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    @PostMapping("/v1/create")
    public Result<ShortLinkCreateRespDTO> create(@RequestBody ShortLinkCreateReqDTO shortLinkCreateReqDTO) {
        return shortLinkRemoteService.create(shortLinkCreateReqDTO);
    }

    @GetMapping("/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO shortLinkPageReqDTO) {
        return shortLinkRemoteService.pageShortLink(shortLinkPageReqDTO);
    }

    @PostMapping("/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO shortLinkUpdateReqDTO) {
        shortLinkRemoteService.updateShortLink(shortLinkUpdateReqDTO);
        return Results.success();
    }

    /**
     * 访问单个短链接指定时间内监控数据
     */
    @GetMapping("/v1/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return shortLinkRemoteService.oneShortLinkStats(requestParam);
    }

    /**
     * 访问单个短链接指定时间内监控访问数据
     */
    @GetMapping("/v1/stats/access-record")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return shortLinkRemoteService.shortLinkStatsAccessRecord(requestParam);
    }
}
