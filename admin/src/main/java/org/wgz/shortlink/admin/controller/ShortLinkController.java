package org.wgz.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.common.convention.result.Results;
import org.wgz.shortlink.admin.remote.ShortLinkRemoteService;
import org.wgz.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.wgz.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.wgz.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

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

}
