package com.sise.house.house.biz.apiImpl.query;

import com.github.pagehelper.PageInfo;
import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.constants.RentSearch;
import com.sise.house.house.api.dto.response.HouseBaseInfoRespDto;
import com.sise.house.house.api.dto.response.HouseMsgRespDto;
import com.sise.house.house.api.query.IHouseQueryApi;
import com.sise.house.house.biz.service.HouseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/19 03:56
 */
@RestController
@RequestMapping("/house/query")
public class HouseQueryApiImpl implements IHouseQueryApi {
    @Resource
    private HouseService houseService;


    @Override
    @ApiOperation(value = "查询房屋全部信息", notes = "查询房屋全部信息")
    public ServerResponse queryHouseInfoById(@PathVariable(name = "houseId") Long houseId) {
        HouseMsgRespDto houseInfo = houseService.findHouseInfo(houseId);
        return ServerResponse.createBySuccess(houseInfo);
    }

    @Override
    @ApiOperation(value = "分页条件查询房屋信息列表", notes = "分页条件查询房屋信息列表")
    public ServerResponse queryHouseInfoByPage(@RequestBody RentSearch rentSearch) {
        PageInfo<HouseMsgRespDto> houseMsgRespDtoPageInfo = houseService.queryHouseInfoByPage(rentSearch);
        return ServerResponse.createBySuccess(houseMsgRespDtoPageInfo);
    }

    @Override
    @ApiOperation(value = "查询我的房屋列表", notes = "查询我的房屋列表")
    public ServerResponse queryHouseById(@RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                         @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(name = "userId") Long userId) {
        PageInfo<HouseBaseInfoRespDto> myHouseList = houseService.findMyHouseList(pageSize, pageNum, userId);
        return ServerResponse.createBySuccess(myHouseList);

    }


}
