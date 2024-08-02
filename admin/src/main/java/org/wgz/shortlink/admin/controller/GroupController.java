package org.wgz.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.common.convention.result.Results;
import org.wgz.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.wgz.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.wgz.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.wgz.shortlink.admin.dto.resp.ShortLinkGroupListRespDTO;
import org.wgz.shortlink.admin.service.GroupService;

import java.util.List;

/**
 * 短链接分组功能
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shortLink/admin")
public class GroupController {

    private final GroupService groupService;

    /**
     * 新增短链接分组
     */
    @PostMapping("/v1/group")
    public Result<Void> saveGroup(@RequestBody ShortLinkGroupSaveReqDTO shortLinkGroupSaveReqDTO) {
        groupService.saveGroup(shortLinkGroupSaveReqDTO.getGroupName());
        return Results.success();
    }

    /**
     * 获取短链接分组列表
     */
    @GetMapping("/v1/group")
    public Result<List<ShortLinkGroupListRespDTO>> shortLinkGroupList() {
        return Results.success(groupService.groupList());
    }

    /**
     * 新增短链接分组
     */
    @PutMapping("/v1/group")
    public Result<Void> updateGroup(@RequestParam ShortLinkGroupUpdateReqDTO shortLinkGroupUpdateReqDTO) {
        groupService.updateGroup(shortLinkGroupUpdateReqDTO);
        return Results.success();
    }

    /**
     * 删除短链接分组
     *
     * @param gid 分组标识
     * @return
     */
    @DeleteMapping("/v1/group")
    public Result<Void> deleteGroup(@RequestBody String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }

    /**
     * 短链接分组排序
     */
    @PostMapping("/v1/group/sort")
    public Result<Void> sortGroup(@RequestBody List<ShortLinkGroupSortReqDTO> shortLinkGroupSortReqDTOS) {
        groupService.sortGroup(shortLinkGroupSortReqDTOS);
        return Results.success();
    }
}
