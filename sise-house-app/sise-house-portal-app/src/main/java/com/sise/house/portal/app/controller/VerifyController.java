package com.sise.house.portal.app.controller;

import com.sise.common.rest.ServerResponse;
import com.sise.house.portal.app.service.EmailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 验证码Controller
 * @Auther: 冲之
 * @Date: 2019/2/18 05:42
 */
@RestController
@RequestMapping("/verify/")
public class VerifyController {
    @Resource
    private EmailService emailService;

    @GetMapping("/notifyVerifyCode/{email}")
    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    public ServerResponse<String> notifyVerifyCode(@PathVariable(name = "email") String email) {
        return emailService.notifyVerifyCode(email);
    }
}
