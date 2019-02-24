package com.sise.house.portal.app.controller;

import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.dto.request.SureSubscribeReqDto;
import com.sise.house.portal.app.Dto.ModifySubscribeDto;
import com.sise.house.portal.app.Dto.SubscribeDto;
import com.sise.house.portal.app.annotation.LoginHouse;
import com.sise.house.portal.app.annotation.LoginRequired;
import com.sise.house.portal.app.service.SubscribeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 05:42
 */
@RestController
@RequestMapping("/house/subscribe")
public class HouseSubscribeController {

    @Resource
    private SubscribeService subscribeService;

    @GetMapping("/query/list")
    @ApiOperation(value = "查看用户的预约清单列表", notes = "查看用户的预约清单列表")
    @LoginRequired
    ServerResponse querySubscribeByPage(@RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                        @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(name = "status", required = false, defaultValue = "1") Integer status,
                                        @LoginHouse Long userId) {
        return subscribeService.querySubscribeByPage(pageSize, pageNum, status, userId);

    }

    @GetMapping("/queryBeSubscribe")
    @ApiOperation(value = "查看用户的被预约清单列表", notes = "查看用户的被预约清单列表")
    @LoginRequired
    ServerResponse queryBeSubscribe(@RequestParam(name = "pageSize") Integer pageSize,
                                    @RequestParam(name = "pageNum") Integer pageNum,
                                    @RequestParam(name = "status") Integer status,
                                    @LoginHouse Long userId) {
        return subscribeService.queryBeSubscribe(pageSize, pageNum, status, userId);
    }

    @GetMapping("/query/detail")
    @ApiOperation(value = "查看用户的预约清单详情", notes = "查看用户的预约清单详情")
    @LoginRequired
    ServerResponse querySubscribeDetail(@RequestParam(name = "id") Long id,
                                        @LoginHouse Long userId) {
        return subscribeService.querySubscribeDetail(id, userId);
    }

    @PostMapping(value = "/add", produces = "application/json")
    @ApiOperation(value = "新增预约", notes = "新增预约")
    ServerResponse addSubscribeOrder(@RequestBody SubscribeDto subscribeDto,
                                     @LoginHouse Long userId) {
        return subscribeService.addSubscribeOrder(subscribeDto.getHouseId(), userId);
    }

    @PostMapping(value = "/sure", produces = "application/json")
    @ApiOperation(value = "确认预约", notes = "确认预约")
    ServerResponse subscribe(@RequestBody SureSubscribeReqDto sureSubscribeReqDto) {
        return subscribeService.subscribe(sureSubscribeReqDto);
    }

    @PostMapping(value = "/modify", produces = "application/json")
    @ApiOperation(value = "修改预约状态", notes = "修改预约状态")
    @LoginRequired
    ServerResponse modifySubscribe(@RequestBody @Validated ModifySubscribeDto modifySubscribeDto,
                                   @LoginHouse Long userId) {
        return subscribeService.modifySubscribe(modifySubscribeDto.getHouseId(), userId, modifySubscribeDto.getStatus());
    }
}
