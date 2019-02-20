package com.sise.house.house.biz.apiImpl.query;

import com.github.pagehelper.PageInfo;
import com.sise.common.rest.ServerResponse;
import com.sise.common.rest.ServiceMultiResult;
import com.sise.house.house.api.constants.RentSearch;
import com.sise.house.house.api.dto.response.HouseMsgRespDto;
import com.sise.house.house.api.query.IHouseQueryApi;
import com.sise.house.house.biz.service.HouseService;
import com.sise.house.house.biz.service.SearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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


}
