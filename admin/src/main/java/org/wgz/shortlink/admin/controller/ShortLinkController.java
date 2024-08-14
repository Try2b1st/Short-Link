package org.wgz.shortlink.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.common.convention.result.Results;
import org.wgz.shortlink.admin.remote.ShortLinkActualRemoteService;
import org.wgz.shortlink.admin.remote.dto.req.ShortLinkBatchCreateReqDTO;
import org.wgz.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.wgz.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.wgz.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkBaseInfoRespDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkBatchCreateRespDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.wgz.shortlink.admin.util.EasyExcelWebUtil;

import java.util.List;

@RestController
@RequestMapping("/api/shortLink/admin")
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkActualRemoteService shortLinkActualRemoteService;

    @PostMapping("/v1/create")
    public Result<ShortLinkCreateRespDTO> create(@RequestBody ShortLinkCreateReqDTO shortLinkCreateReqDTO) {
        return shortLinkActualRemoteService.create(shortLinkCreateReqDTO);
    }

    /**
     * 批量创建短链接
     */
    @SneakyThrows
    @PostMapping("/v1/create/batch")
    public void batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam, HttpServletResponse response) {
        Result<ShortLinkBatchCreateRespDTO> shortLinkBatchCreateRespDTOResult = shortLinkActualRemoteService.batchCreateShortLink(requestParam);
        if (shortLinkBatchCreateRespDTOResult.isSuccess()) {
            List<ShortLinkBaseInfoRespDTO> baseLinkInfos = shortLinkBatchCreateRespDTOResult.getData().getBaseLinkInfos();
            EasyExcelWebUtil.write(response, "批量创建短链接-SaaS短链接系统", ShortLinkBaseInfoRespDTO.class, baseLinkInfos);
        }
    }

    @GetMapping("/v1/page")
    public Result<Page<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO shortLinkPageReqDTO) {
        return shortLinkActualRemoteService.pageShortLink(shortLinkPageReqDTO.getGid(),
                shortLinkPageReqDTO.getOrderTag(),
                shortLinkPageReqDTO.getCurrent(),
                shortLinkPageReqDTO.getSize());
    }

    @PostMapping("/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO shortLinkUpdateReqDTO) {
        shortLinkActualRemoteService.updateShortLink(shortLinkUpdateReqDTO);
        return Results.success();
    }
}
