package com.sise.house.portal.app.controller;

import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.constants.RentSearch;
import com.sise.house.portal.app.annotation.LoginHouse;
import com.sise.house.portal.app.annotation.LoginRequired;
import com.sise.house.portal.app.service.HouseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 04:36
 */
@RestController
@RequestMapping("/house/")
public class HouseController {
    @Resource
    private HouseService houseService;

    @GetMapping("/myHouse")
    @ApiOperation(value = "查询我的房屋列表", notes = "查询我的房屋列表")
    @LoginRequired
    ServerResponse queryHouseById(@RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @LoginHouse Long userId) {
        return houseService.queryHouseById(pageSize, pageNum, userId);
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页条件查询房屋信息列表", notes = "分页条件查询房屋信息列表")
    ServerResponse queryHouseInfoByPage(@RequestBody RentSearch rentSearch) {
        return houseService.queryHouseInfoByPage(rentSearch);
    }
}
