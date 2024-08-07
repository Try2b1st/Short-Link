package org.wgz.shortlink.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wgz.shortlink.common.convention.result.Result;
import org.wgz.shortlink.common.convention.result.Results;
import org.wgz.shortlink.dto.req.ShortLinkCreateReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkPageReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkUpdateReqDTO;
import org.wgz.shortlink.dto.resp.ShortLinkCreateRespDTO;
import org.wgz.shortlink.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.wgz.shortlink.dto.resp.ShortLinkPageRespDTO;
import org.wgz.shortlink.service.ShortLinkService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * 新增短链接
     */
    @PostMapping("/api/shortLink/v1/create")
    public Result<ShortLinkCreateRespDTO> create(@RequestBody ShortLinkCreateReqDTO shortLinkCreateReqDTO) {
        return Results.success(shortLinkService.create(shortLinkCreateReqDTO));
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/shortLink/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO shortLinkPageReqDTO) {
        return Results.success(shortLinkService.pageShortLink(shortLinkPageReqDTO));
    }

    /**
     * 系统内部调用：查看分组下短链接数目
     */
    @GetMapping("/api/shortLink/v1/count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam) {
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParam));
    }

    /**
     * 短链接修改
     */
    @PostMapping("/api/shortLink/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO shortLinkUpdateReqDTO) {
        shortLinkService.updateShortLink(shortLinkUpdateReqDTO);
        return Results.success();
    }

    /**
     * 短链接跳转
     */
    @GetMapping("/{short-uri}")
    public void restoreUrl(@PathVariable("short-uri") String shortUri, ServletRequest request, ServletResponse response) {
        shortLinkService.restoreUrl(shortUri, request, response);
    }
}
