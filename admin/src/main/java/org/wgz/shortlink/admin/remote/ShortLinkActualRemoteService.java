package org.wgz.shortlink.admin.remote;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.remote.dto.req.*;
import org.wgz.shortlink.admin.remote.dto.resp.*;

import java.util.List;

/**
 * 短链接中台远程服务调用
 */
@FeignClient("short-link-project")
public interface ShortLinkActualRemoteService {

    /**
     * 创建短链接
     *
     * @param requestParam 创建短链接请求参数
     * @return 创建返回参数
     */
    @PostMapping("/api/shortLink/v1/create")
    Result<ShortLinkCreateRespDTO> create(@RequestBody ShortLinkCreateReqDTO requestParam);

    /**
     * 批量创建短链接
     *
     * @param requestParam 批量创建短链接请求参数
     * @return 短链接批量创建响应
     */
    @PostMapping("/api/shortLink/v1/create/batch")
    Result<ShortLinkBatchCreateRespDTO> batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam);

    /**
     * 修改短链接
     *
     * @param shortLinkUpdateReqDTO 修改短链接请求参数
     */
    @PostMapping("/api/shortLink/v1/update")
    void updateShortLink(@RequestBody ShortLinkUpdateReqDTO shortLinkUpdateReqDTO);

    /**
     * 分页查询短链接
     *
     * @param gid      分组标识
     * @param orderTag 排序标识
     * @param current  当前页码
     * @param size     页大小
     * @return 分页结果
     */
    @GetMapping("/api/shortLink/v1/page")
    Result<Page<ShortLinkPageRespDTO>> pageShortLink(@RequestParam("gid") String gid,
                                                     @RequestParam("orderTag") String orderTag,
                                                     @RequestParam("current") Long current,
                                                     @RequestParam("size") Long size);

    /**
     * 查询分组短链接数目
     *
     * @param requestParam gid列表请求参数
     * @return 响应参数
     */
    @GetMapping("/api/shortLink/v1/count")
    Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam);

    /**
     * 根据 URL 获取网站标题
     *
     * @param url 网址
     * @return 网站标题
     */
    @GetMapping("/api/shortLink/v1/title?url=")
    Result<String> getTitleByUrl(@RequestParam("url") String url);

    /**
     * 短链接移至回收站
     */
    @PostMapping("/api/shortLink/v1/recycle-bin/save")
    void saveRecycleBin(@RequestBody RecycleBinSaveReqDTO recycleBinSaveReqDTO);

    /**
     * 分页查询回收站短链接
     *
     * @param shortLinkRecycleBinPageReqDTO 查询参数
     * @return 短链接数据列表
     */
    @GetMapping("/api/shortLink/v1/recycle-bin/page")
    Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(@SpringQueryMap ShortLinkRecycleBinPageReqDTO shortLinkRecycleBinPageReqDTO);

    /**
     * 恢复短链接
     *
     * @param recycleBinRecoverReqDTO 恢复短链接请求参数
     */
    @PostMapping("/api/shortLink/v1/recycle-bin/recover")
    void recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO recycleBinRecoverReqDTO);

    /**
     * 从回收站删除短链接
     *
     * @param recycleBinRemoveReqDTO 移除短链接请求参数
     */
    @PostMapping("/api/shortLink/v1/recycle-bin/remove")
    void removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO recycleBinRemoveReqDTO);

    @GetMapping("/api/short-link/v1/stats")
    Result<ShortLinkStatsRespDTO> oneShortLinkStats(@SpringQueryMap ShortLinkStatsReqDTO requestParam);

    /**
     * 访问分组短链接指定时间内监控数据
     *
     * @param requestParam 访分组问短链接监控请求参数
     * @return 分组短链接监控信息
     */
    @GetMapping("/api/short-link/v1/stats/group")
    Result<ShortLinkStatsRespDTO> groupShortLinkStats(@SpringQueryMap ShortLinkGroupStatsReqDTO requestParam);

    @GetMapping("/api/short-link/v1/stats/access-record")
    Result<Page<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(
            @SpringQueryMap ShortLinkStatsAccessRecordReqDTO requestParam);

    @GetMapping("/api/short-link/v1/stats/access-record/group")
    Result<Page<ShortLinkStatsAccessRecordRespDTO>> groupShortLinkStatsAccessRecord(
            @SpringQueryMap ShortLinkGroupStatsAccessRecordReqDTO requestParam);
}
