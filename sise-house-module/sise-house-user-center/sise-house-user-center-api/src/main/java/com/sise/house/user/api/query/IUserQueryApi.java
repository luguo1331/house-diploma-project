package com.sise.house.user.api.query;

import com.sise.common.rest.ServerResponse;
import com.sise.house.user.api.dto.request.UserReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 用户中心：用户查询接口
 * @Auther: 冲之
 * @Date: 2019/2/15 17:04
 */
@Api(tags = {"用户中心：用户查询接口"})
@FeignClient(value = "sise-user", path = "/user/query")
@Validated
public interface IUserQueryApi {

    @GetMapping("/userDetail")
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    ServerResponse queryUserById(@RequestParam(name = "id") Long id,
                                 @RequestParam(name = "type") Boolean type);

    @GetMapping("auth")
    @ApiOperation(value = "验证密码、邮箱", notes = "验证密码、邮箱")
    ServerResponse auth(@RequestBody UserReqDto userReqDto);

    @GetMapping("/list")
    @ApiOperation(value = "分页查询用户列表", notes = "分页查询用户列表")
    ServerResponse queryUserByList(@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "type") Boolean type);
}
