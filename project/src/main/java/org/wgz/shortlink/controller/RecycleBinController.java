package org.wgz.shortlink.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wgz.shortlink.common.convention.result.Result;
import org.wgz.shortlink.common.convention.result.Results;
import org.wgz.shortlink.dto.req.RecycleBinRecoverReqDTO;
import org.wgz.shortlink.dto.req.RecycleBinRemoveReqDTO;
import org.wgz.shortlink.dto.req.RecycleBinSaveReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.wgz.shortlink.dto.resp.ShortLinkPageRespDTO;
import org.wgz.shortlink.service.RecycleBinService;

@RestController
@RequestMapping("/api/shortLink")
@RequiredArgsConstructor
public class RecycleBinController {

    private final RecycleBinService recycleBinService;

    /**
     * 短链接移至回收站
     */
    @PostMapping("/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO recycleBinSaveReqDTO) {
        recycleBinService.saveRecycleBin(recycleBinSaveReqDTO);
        return Results.success();
    }

    /**
     * 回收站分页查询短链接
     */
    @GetMapping("/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO shortLinkRecycleBinPageReqDTO) {
        return Results.success(recycleBinService.pageShortLink(shortLinkRecycleBinPageReqDTO));
    }

    /**
     * 从回收站恢复短链接
     */
    @PostMapping("/v1/recycle-bin/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO recycleBinRecoverReqDTO) {
        recycleBinService.recoverRecycleBin(recycleBinRecoverReqDTO);
        return Results.success();
    }

    /**
     * 从回收站删除短链接
     */
    @PostMapping("/v1/recycle-bin/remove")
    public Result<Void> removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO recycleBinRemoveReqDTO) {
        recycleBinService.removeRecycleBin(recycleBinRemoveReqDTO);
        return Results.success();
    }
}
