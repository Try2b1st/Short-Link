package org.wgz.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.common.convention.result.Results;
import org.wgz.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.wgz.shortlink.admin.service.GroupService;

/**
 * 短链接分组功能
 */

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 新增短链接
     */
    @PostMapping("/api/shortLink/v1/group")
    public Result<Void> saveGroup(@RequestBody ShortLinkGroupSaveReqDTO shortLinkGroupSaveReqDTO) {
        groupService.saveGroup(shortLinkGroupSaveReqDTO.getGroupName());
        return Results.success();
    }
}
