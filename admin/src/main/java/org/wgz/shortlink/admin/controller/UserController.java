package org.wgz.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.common.convention.result.Results;
import org.wgz.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.wgz.shortlink.admin.dto.resp.UserRespActualDTO;
import org.wgz.shortlink.admin.dto.resp.UserRespDTO;
import org.wgz.shortlink.admin.service.UserService;

/**
 * 用户管理
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 根据用户名查找用户信息
     */
    @GetMapping("/api/shortLink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }

    /**
     * 根据用户名查找用户信息
     */
    @GetMapping("/api/shortLink/v1/actual/user/{username}")
    public Result<UserRespActualDTO> getActualUserByUsername(@PathVariable("username") String username) {
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username),
                UserRespActualDTO.class));
    }

    /**
     * 判断用户名是否可用
     */
    @GetMapping("/api/shortLink/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(!userService.hasUsername(username));
    }

    /**
     * 注册用户
     */
    @PostMapping("/api/shortLink/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO userRegisterReqDTO) {
        userService.register(userRegisterReqDTO);
        return Results.success();
    }
}
























