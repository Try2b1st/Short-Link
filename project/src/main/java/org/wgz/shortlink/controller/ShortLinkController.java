package org.wgz.shortlink.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wgz.shortlink.common.convention.result.Result;
import org.wgz.shortlink.common.convention.result.Results;
import org.wgz.shortlink.dao.entity.ShortLinkDO;
import org.wgz.shortlink.dto.req.ShortLinkCreateReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkPageReqDTO;
import org.wgz.shortlink.dto.resp.ShortLinkCreateRespDTO;
import org.wgz.shortlink.dto.resp.ShortLinkPageRespDTO;
import org.wgz.shortlink.service.ShortLinkService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shortLink/")
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    @PostMapping("/v1/create")
    public Result<ShortLinkCreateRespDTO> create(@RequestBody ShortLinkCreateReqDTO shortLinkCreateReqDTO) {
        return Results.success(shortLinkService.create(shortLinkCreateReqDTO));
    }

    @GetMapping("/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO shortLinkPageReqDTO){
        return Results.success(shortLinkService.pageShortLink(shortLinkPageReqDTO));
    }
}
