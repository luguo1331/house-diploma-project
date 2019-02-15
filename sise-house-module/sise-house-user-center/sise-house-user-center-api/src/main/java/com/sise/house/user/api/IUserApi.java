package com.sise.house.user.api;

import com.sise.common.rest.ServerResponse;
import com.sise.house.user.api.dto.request.UserReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: 用户中心：用户接口
 * @Auther: 冲之
 * @Date: 2019/2/15 17:05
 */
@Api(tags = {"用户中心：用户接口"})
@FeignClient(value = "sise-user", path = "/user/")
@Validated
public interface IUserApi {

    @PostMapping("register")
    @ApiOperation(value = "注册", notes = "注册")
    ServerResponse register(@RequestBody UserReqDto userReqDto);

    @PostMapping("auth")
    @ApiOperation(value = "登陆", notes = "登陆")
    ServerResponse auth(@RequestBody UserReqDto userReqDto);

    @PostMapping("logout/{email}")
    @ApiOperation(value = "退出登录", notes = "退出登录")
    ServerResponse logout(@PathVariable(name = "email") String email);

    @PostMapping("modfiy")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    ServerResponse modifyUser(@RequestBody UserReqDto userReqDto);

}
