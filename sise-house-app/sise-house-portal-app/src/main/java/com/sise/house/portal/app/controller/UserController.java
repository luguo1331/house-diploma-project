package com.sise.house.portal.app.controller;

import com.sise.common.rest.ServerResponse;
import com.sise.house.portal.app.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/17 15:49
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Resource
    private UserService userService;


    @GetMapping("/userDetail")
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    ServerResponse queryUserById(@RequestParam(name = "id") Long id,
                                 @RequestParam(name = "type") Boolean type) {

        return userService.queryUserById(id, type);
    }
}
