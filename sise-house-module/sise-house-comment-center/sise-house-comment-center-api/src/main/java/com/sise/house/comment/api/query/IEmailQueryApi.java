package com.sise.house.comment.api.query;

import com.sise.common.rest.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/18 06:28
 */
@Api(tags = {"消息中心：邮件服务"})
@FeignClient(value = "sise-comment", path = "/email/query")
@Validated
public interface IEmailQueryApi {

    @GetMapping("/queryEmailByCode/{verifyCode}")
    @ApiOperation(value = "根据验证码查询邮箱", notes = "根据验证码查询邮箱")
    ServerResponse queryEmailByCode(@PathVariable(name = "verifyCode") String verifyCode);
}
