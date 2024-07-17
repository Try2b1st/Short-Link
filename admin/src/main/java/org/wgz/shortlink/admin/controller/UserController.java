package org.wgz.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.common.convention.result.Results;
import org.wgz.shortlink.admin.dto.req.UserLoginReqDTO;
import org.wgz.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.wgz.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.wgz.shortlink.admin.dto.resp.UserLoginRespDTO;
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

    @PutMapping("/api/shortLink/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO userUpdateReqDTO) {
        userService.update(userUpdateReqDTO);
        return Results.success();
    }

    @PostMapping("/api/shortLink/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO userLoginReqDTO) {
        return Results.success(userService.login(userLoginReqDTO));
    }


    @GetMapping("/api/shortLink/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username,
                                      @RequestParam("token") String token) {
        return Results.success(userService.checkLogin(username, token));
    }


    @DeleteMapping("/api/shortLink/v1/user/logout")
    public Result<Void> logout(@RequestParam("username") String username,
                               @RequestParam("token") String token) {
        userService.logout(username, token);
        return Results.success();
    }
}
























