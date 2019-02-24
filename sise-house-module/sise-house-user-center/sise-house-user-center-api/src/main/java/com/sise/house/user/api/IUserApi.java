package com.sise.house.user.api;

import com.sise.common.rest.ServerResponse;
import com.sise.house.user.api.dto.request.UserReqDto;
import com.sise.house.user.api.fallback.UserApiFallback;
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
@FeignClient(value = "sise-user", path = "/user/", fallback = UserApiFallback.class)
@Validated
public interface IUserApi {

    @PostMapping("register")
    @ApiOperation(value = "注册", notes = "注册")
    ServerResponse register(@RequestBody UserReqDto userReqDto);

    @PostMapping("modfiy")
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    ServerResponse modifyUser(@RequestBody UserReqDto userReqDto);

    @PostMapping("modifyPasswd/ByEmail")
    @ApiOperation(value = "根据邮箱修改密码", notes = "根据邮箱修改密码")
    ServerResponse modifyPasswordByEmail(@RequestBody UserReqDto userReqDto);

}
