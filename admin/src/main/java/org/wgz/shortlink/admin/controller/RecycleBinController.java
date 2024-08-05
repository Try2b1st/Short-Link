package org.wgz.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.common.convention.result.Results;
import org.wgz.shortlink.admin.remote.ShortLinkRemoteService;
import org.wgz.shortlink.admin.remote.dto.req.RecycleBinSaveReqDTO;

@RestController
@RequestMapping("/api/shortLink/admin")
@RequiredArgsConstructor
public class RecycleBinController {

    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    @PostMapping("/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO recycleBinSaveReqDTO) {
        shortLinkRemoteService.saveRecycleBin(recycleBinSaveReqDTO);
        return Results.success();
    }
}