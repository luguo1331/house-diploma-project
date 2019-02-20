package com.sise.house.house.api.query;

import com.sise.common.rest.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 房产中心：支持城市查询服务
 * @Auther: 冲之
 * @Date: 2019/2/15 22:19
 */
@Api(tags = {"房产中心：支持城市查询服务"})
@FeignClient(value = "sise-house", path = "/houseSupport/query")
@Validated
public interface IHouseSupportQueryApi {

    @GetMapping("/allCity")
    @ApiOperation(value = "获取所有支持的城市列表", notes = "获取所有支持的城市列表")
    ServerResponse findAllCities();

    @GetMapping("/findCityAndRegion")
    @ApiOperation(value = "获取对应城市支持区域列表", notes = "获取对应城市支持区域列表")
    ServerResponse findCityAndRegion(@RequestParam(name = "cityEnName") String cityEnName,
                                     @RequestParam(name = "regionEnName") String regionEnName);

    @GetMapping("/findAllSubwayByCity")
    @ApiOperation(value = "获取具体城市所支持的地铁线路", notes = "获取具体城市所支持的地铁线路")
    ServerResponse findAllSubwayByCity(String cityEnName);

    @GetMapping("/findAllStationBySubway")
    @ApiOperation(value = "获取对应地铁线路所支持的地铁站点", notes = "获取对应地铁线路所支持的地铁站点")
    ServerResponse findAllStationBySubway(Long subwayId);

}
