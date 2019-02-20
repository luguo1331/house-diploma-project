package com.sise.house.house.biz.apiImpl.query;

import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.constants.Level;
import com.sise.house.house.api.dto.response.SubwayRespDto;
import com.sise.house.house.api.dto.response.SubwayStationRespDto;
import com.sise.house.house.api.dto.response.SupportAddressRespDto;
import com.sise.house.house.api.query.IHouseSupportQueryApi;
import com.sise.house.house.biz.service.SupportAddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: 房产中心：支持城市查询服务
 * @Auther: 冲之
 * @Date: 2019/2/21 02:57
 */
@RestController
@RequestMapping("/houseSupport/query")
public class HouseSupportQueryApiImpl implements IHouseSupportQueryApi {
    @Resource
    private SupportAddressService supportAddressService;

    @Override
    @ApiOperation(value = "获取所有支持的城市列表", notes = "获取所有支持的城市列表")
    public ServerResponse findAllCities() {
        List<SupportAddressRespDto> allCities = supportAddressService.findAllCities();
        return ServerResponse.createBySuccess(allCities);
    }

    @Override
    @ApiOperation(value = "获取对应城市支持区域列表", notes = "获取对应城市支持区域列表")
    public ServerResponse findCityAndRegion(@RequestParam(name = "cityEnName") String cityEnName,
                                            @RequestParam(name = "regionEnName") String regionEnName) {
        Map<Level, SupportAddressRespDto> cityAndRegion = supportAddressService.findCityAndRegion(cityEnName, regionEnName);
        return ServerResponse.createBySuccess(cityAndRegion);
    }

    @Override
    @ApiOperation(value = "获取具体城市所支持的地铁线路", notes = "获取具体城市所支持的地铁线路")
    public ServerResponse findAllSubwayByCity(String cityEnName) {
        List<SubwayRespDto> allSubwayByCity = supportAddressService.findAllSubwayByCity(cityEnName);
        return ServerResponse.createBySuccess(allSubwayByCity);

    }

    @Override
    @ApiOperation(value = "获取对应地铁线路所支持的地铁站点", notes = "获取对应地铁线路所支持的地铁站点")
    public ServerResponse findAllStationBySubway(Long subwayId) {
        List<SubwayStationRespDto> allStationBySubway = supportAddressService.findAllStationBySubway(subwayId);
        return ServerResponse.createBySuccess(allStationBySubway);
    }


}
