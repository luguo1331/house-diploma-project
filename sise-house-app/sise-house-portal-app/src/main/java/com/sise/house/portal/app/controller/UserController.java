package com.sise.house.portal.app.controller;

import com.sise.common.rest.ServerResponse;
import com.sise.house.portal.app.Dto.RegisterDto;
import com.sise.house.portal.app.annotation.LoginHouse;
import com.sise.house.portal.app.annotation.LoginRequired;
import com.sise.house.portal.app.service.UserService;
import com.sise.house.user.api.dto.request.LoginReqDto;
import com.sise.house.user.api.dto.request.UserReqDto;
import com.sise.house.user.api.dto.response.UserRespDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/17 15:49
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Resource
    private UserService userService;


    @GetMapping("/userDetail")
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    @LoginRequired
    ServerResponse queryUserById(@LoginHouse Long id) {
        return userService.queryUserById(id, (byte) 1);
    }

    @GetMapping("/agencyDetail")
    @ApiOperation(value = "查询中介信息", notes = "查询中介信息")
    @LoginRequired
    ServerResponse queryAgencyById(@LoginHouse Long id) {
        return userService.queryUserById(id, (byte) 2);
    }

    @PostMapping("/login")
    @ApiOperation(value = "登陆", notes = "登陆")
    ServerResponse login(@RequestBody LoginReqDto loginReqDto) {
        return userService.login(loginReqDto);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册", notes = "注册")
    ServerResponse register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @LoginRequired
    ServerResponse logout(@RequestHeader("X-House-Token") String token) {
        return userService.logout(token);
    }

}
