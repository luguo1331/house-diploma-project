package com.sise.house.portal.app.controller;

import com.sise.common.rest.ServerResponse;
import com.sise.house.portal.app.service.HouseSupportAddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 支持城市地区、接口
 * @Auther: 冲之
 * @Date: 2019/2/21 05:11
 */
@RestController
@RequestMapping("/houseSupport/")
public class HouseSupportAddressController {
    @Resource
    private HouseSupportAddressService houseSupportAddressService;

    @GetMapping("/allCity")
    @ApiOperation(value = "获取所有支持的城市列表", notes = "获取所有支持的城市列表")
    ServerResponse findAllCities() {
        return houseSupportAddressService.findAllCities();
    }

    @GetMapping("/findCityAndRegion")
    @ApiOperation(value = "获取对应城市支持区域列表", notes = "获取对应城市支持区域列表")
    ServerResponse findCityAndRegion(@RequestParam(name = "cityEnName") String cityEnName,
                                     @RequestParam(name = "regionEnName") String regionEnName) {
        return houseSupportAddressService.findCityAndRegion(cityEnName, regionEnName);
    }

    @GetMapping("/findAllSubwayByCity")
    @ApiOperation(value = "获取具体城市所支持的地铁线路", notes = "获取具体城市所支持的地铁线路")
    ServerResponse findAllSubwayByCity(String cityEnName) {
        return houseSupportAddressService.findAllSubwayByCity(cityEnName);
    }

    @GetMapping("/findAllStationBySubway")
    @ApiOperation(value = "获取对应地铁线路所支持的地铁站点", notes = "获取对应地铁线路所支持的地铁站点")
    ServerResponse findAllStationBySubway(Long subwayId) {
        return houseSupportAddressService.findAllStationBySubway(subwayId);
    }
}
