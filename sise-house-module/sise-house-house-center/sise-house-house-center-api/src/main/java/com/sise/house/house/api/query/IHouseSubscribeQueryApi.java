package com.sise.house.house.api.query;

import com.sise.common.rest.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 房产中心：预约查询服务
 * @Auther: 冲之
 * @Date: 2019/2/21 05:20
 */
@Api(tags = {"房产中心：预约服务"})
@FeignClient(value = "sise-house", path = "/house/subscribe/query")
@Validated
public interface IHouseSubscribeQueryApi {

    @GetMapping("/list")
    @ApiOperation(value = "查看用户的预约清单列表", notes = "查看用户的预约清单列表")
    ServerResponse querySubscribeByPage(@RequestParam(name = "pageSize") Integer pageSize,
                                        @RequestParam(name = "pageNum") Integer pageNum,
                                        @RequestParam(name = "status") Integer status,
                                        @RequestParam(name = "userId") Long userId);

    @GetMapping("/queryBeSubscribe")
    @ApiOperation(value = "查看用户的被预约清单列表", notes = "查看用户的被预约清单列表")
    ServerResponse queryBeSubscribe(@RequestParam(name = "pageSize") Integer pageSize,
                                        @RequestParam(name = "pageNum") Integer pageNum,
                                        @RequestParam(name = "status") Integer status,
                                        @RequestParam(name = "adminId") Long adminId);

    @GetMapping("/detail")
    @ApiOperation(value = "查看用户的预约清单详情", notes = "查看用户的预约清单详情")
    ServerResponse querySubscribeDetail(@RequestParam(name = "id") Long id,
                                        @RequestParam(name = "userId") Long userId);
}
