package com.sise.house.comment.biz.apiImpl.query;

import com.sise.common.rest.ServerResponse;
import com.sise.house.comment.api.query.IEmailQueryApi;
import com.sise.house.comment.biz.Service.EmailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 03:22
 */
@RestController
@RequestMapping("/email/query")
public class EmailQueryApiImpl implements IEmailQueryApi {

    @Resource
    private EmailService emailService;

    @Override
    @ApiOperation(value = "根据验证码查询邮箱", notes = "根据验证码查询邮箱")
    public ServerResponse queryEmailByCode(@PathVariable(name = "verifyCode") String verifyCode) {
        String keyEmail = emailService.getKeyEmail(verifyCode);
        return ServerResponse.createBySuccess(keyEmail);
    }
}
