package org.wgz.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.common.convention.result.Results;
import org.wgz.shortlink.admin.remote.ShortLinkRemoteService;
import org.wgz.shortlink.admin.remote.dto.req.RecycleBinRecoverReqDTO;
import org.wgz.shortlink.admin.remote.dto.req.RecycleBinRemoveReqDTO;
import org.wgz.shortlink.admin.remote.dto.req.RecycleBinSaveReqDTO;
import org.wgz.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.wgz.shortlink.admin.service.RecycleBinService;

@RestController
@RequestMapping("/api/shortLink/admin")
@RequiredArgsConstructor
public class RecycleBinController {

    private final RecycleBinService recycleBinService;

    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    @PostMapping("/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO recycleBinSaveReqDTO) {
        shortLinkRemoteService.saveRecycleBin(recycleBinSaveReqDTO);
        return Results.success();
    }

    /**
     * 回收站分页查询短链接
     */
    @GetMapping("/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO shortLinkPageReqDTO) {
        return recycleBinService.pageRecycleBinShortLink(shortLinkPageReqDTO);
    }

    /**
     * 从回收站回复短链接
     */
    @PostMapping("/v1/recycle-bin/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO recycleBinRecoverReqDTO) {
        shortLinkRemoteService.recoverRecycleBin(recycleBinRecoverReqDTO);
        return Results.success();
    }

    /**
     * 从回收站删除短链接
     */
    @PostMapping("/v1/recycle-bin/remove")
    public Result<Void> removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO recycleBinRemoveReqDTO) {
        shortLinkRemoteService.removeRecycleBin(recycleBinRemoveReqDTO);
        return Results.success();
    }
}