package com.sise.house.portal.app.controller;

import com.github.pagehelper.PageInfo;
import com.sise.common.rest.ServerResponse;
import com.sise.common.util.MD5Util;
import com.sise.house.portal.app.Dto.PasswordDto;
import com.sise.house.portal.app.Dto.RegisterDto;
import com.sise.house.portal.app.annotation.LoginHouse;
import com.sise.house.portal.app.annotation.LoginRequired;
import com.sise.house.portal.app.service.UserService;
import com.sise.house.user.api.dto.request.LoginReqDto;
import com.sise.house.user.api.dto.request.UserReqDto;
import com.sise.house.user.api.dto.response.UserRespDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("project.salt")
    private String salt;


    @GetMapping("/userDetail")
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    @LoginRequired
    public ServerResponse queryUserById(@LoginHouse Long id) {
        return userService.queryUserById(id, (byte) 1);
    }

    @GetMapping("/agencyDetail")
    @ApiOperation(value = "查询中介信息", notes = "查询中介信息")
    @LoginRequired
    public ServerResponse queryAgencyById(@LoginHouse Long id) {
        return userService.queryUserById(id, (byte) 2);
    }

    @PostMapping("/login")
    @ApiOperation(value = "登陆", notes = "登陆")
    public ServerResponse login(@RequestBody LoginReqDto loginReqDto) {
        return userService.login(loginReqDto);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册", notes = "注册")
    public ServerResponse register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @LoginRequired
    public ServerResponse logout(@RequestHeader("X-House-Token") String token) {
        return userService.logout(token);
    }

    @PostMapping("/modify/userInfo")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @LoginRequired
    public ServerResponse modifyUserInfo(@RequestBody UserReqDto userReqDto, @LoginHouse Long id) {
        userReqDto.setId(id);
        return userService.modifyUserInfo(userReqDto);
    }

    @PostMapping("/modify/password")
    @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
    @LoginRequired
    public ServerResponse modifyPassword(@RequestBody UserReqDto userReqDto, @LoginHouse Long id) {
        userReqDto.setId(id);
        userReqDto.setPasswd(MD5Util.MD5String(userReqDto.getPasswd(), salt));
        return userService.modifyUserInfo(userReqDto);
    }

    @PostMapping("/modify/forgetPassword")
    @ApiOperation(value = "忘记密码", notes = "忘记密码")
    public ServerResponse forgetPassword(@RequestBody @Validated PasswordDto passwordDto) {
        return userService.forgetPassword(passwordDto);
    }

    @GetMapping("/agentList")
    @ApiOperation(value = "分页查询中介列表", notes = "分页查询中介列表")
    ServerResponse<PageInfo<UserRespDto>> queryUserByList(@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        return userService.queryUserByList(pageSize, pageNum);
    }
}
