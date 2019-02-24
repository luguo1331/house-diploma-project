package com.sise.house.portal.app.service;

import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.query.IHouseSupportQueryApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 支持城市地区、接口
 * @Auther: 冲之
 * @Date: 2019/2/21 05:12
 */
@Service
public class HouseSupportAddressService {
    @Resource
    private IHouseSupportQueryApi houseSupportQueryApi;

    /**
     * 获取所有支持的城市列表
     *
     * @return
     */
    public ServerResponse findAllCities() {
        return houseSupportQueryApi.findAllCities();
    }

    /**
     * 获取对应城市支持区域列表
     *
     * @param cityEnName
     * @param regionEnName
     * @return
     */
    public ServerResponse findCityAndRegion(String cityEnName, String regionEnName) {
        return houseSupportQueryApi.findCityAndRegion(cityEnName, regionEnName);
    }

    /**
     * 获取具体城市所支持的地铁线路
     *
     * @param cityEnName
     * @return
     */
    public ServerResponse findAllSubwayByCity(String cityEnName) {
        return houseSupportQueryApi.findAllSubwayByCity(cityEnName);
    }

    /**
     * 获取对应地铁线路所支持的地铁站点
     *
     * @param subwayId
     * @return
     */
    public ServerResponse findAllStationBySubway(Long subwayId) {
        return houseSupportQueryApi.findAllStationBySubway(subwayId);
    }

}
