package com.sise.house.comment.biz.apiImpl;

import com.sise.common.rest.ServerResponse;
import com.sise.house.comment.api.IEmailApi;
import com.sise.house.comment.biz.Service.EmailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 邮件服务实现类
 * @Auther: 冲之
 * @Date: 2019/2/18 02:25
 */
@RestController
@RequestMapping("/email")
public class EmailApiImpl implements IEmailApi {
    @Resource
    private EmailService emailService;


    /**
     * 发送验证码
     *
     * @param email
     * @return
     */
    @Override
    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    public ServerResponse codeNotify(@PathVariable("email") String email) {
        emailService.codeNotify(email);
        return ServerResponse.createBySuccess();
    }
}
