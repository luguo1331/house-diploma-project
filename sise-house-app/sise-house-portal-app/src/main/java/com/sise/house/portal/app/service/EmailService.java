package com.sise.house.portal.app.service;

import com.sise.common.rest.ServerResponse;
import com.sise.house.comment.api.IEmailApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/18 05:46
 */
@Service
public class EmailService {

    @Resource
    private IEmailApi emailApi;

    public ServerResponse notifyVerifyCode(String email) {
        return emailApi.codeNotify(email);
    }
}
