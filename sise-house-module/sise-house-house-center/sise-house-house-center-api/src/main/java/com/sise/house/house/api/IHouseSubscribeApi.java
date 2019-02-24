package com.sise.house.house.api;

import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.dto.request.SureSubscribeReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 房产中心：预约服务
 * @Auther: 冲之
 * @Date: 2019/2/21 05:20
 */
@Api(tags = {"房产中心：预约服务"})
@FeignClient(value = "sise-house", path = "/house/subscribe")
@Validated
public interface IHouseSubscribeApi {

    @PostMapping(value = "/add", produces = "application/json")
    @ApiOperation(value = "新增预约", notes = "新增预约")
    ServerResponse addSubscribeOrder(@RequestParam(name = "houseId") Long houseId,
                                     @RequestParam(name = "userId") Long userId);

    @PostMapping(value = "/sure", produces = "application/json")
    @ApiOperation(value = "确认预约", notes = "确认预约")
    ServerResponse subscribe(@RequestBody SureSubscribeReqDto sureSubscribeReqDto);

    @PostMapping(value = "/modify", produces = "application/json")
    @ApiOperation(value = "修改预约状态", notes = "修改预约状态")
    ServerResponse modifySubscribe(@RequestParam(name = "houseId") Long houseId,
                                   @RequestParam(name = "userId") Long userId,
                                   @RequestParam(name = "status") Integer status);
}
