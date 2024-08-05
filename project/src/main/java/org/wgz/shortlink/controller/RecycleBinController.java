package org.wgz.shortlink.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wgz.shortlink.common.convention.result.Result;
import org.wgz.shortlink.common.convention.result.Results;
import org.wgz.shortlink.dto.req.RecycleBinSaveReqDTO;
import org.wgz.shortlink.service.RecycleBinService;

@RestController
@RequestMapping("/api/shortLink")
@RequiredArgsConstructor
public class RecycleBinController {

    private final RecycleBinService recycleBinService;

    @PostMapping("/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO recycleBinSaveReqDTO) {
        recycleBinService.saveRecycleBin(recycleBinSaveReqDTO);
        return Results.success();
    }
}
