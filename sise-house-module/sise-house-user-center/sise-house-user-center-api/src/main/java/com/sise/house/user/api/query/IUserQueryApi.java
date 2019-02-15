package com.sise.house.user.api.query;

import com.sise.common.rest.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: 用户中心：用户查询接口
 * @Auther: 冲之
 * @Date: 2019/2/15 17:04
 */
@Api(tags = {"用户中心：用户查询接口"})
@FeignClient(value = "sise-user", path = "/user/query")
@Validated
public interface IUserQueryApi {

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询用户信息", notes = "根据id查询用户信息")
    ServerResponse getUserById(@PathVariable(name = "id") Long id);

    @GetMapping("agentDetail/{id}")
    @ApiOperation(value = "查询中介信息", notes = "查询中介信息")
    ServerResponse agentDetail(@PathVariable(name = "id") Long id);
}
