package org.wgz.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.wgz.shortlink.admin.service.GroupService;

/**
 * 短链接分组功能
 */

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

}
