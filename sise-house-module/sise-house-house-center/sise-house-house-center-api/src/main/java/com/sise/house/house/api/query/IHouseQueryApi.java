package com.sise.house.house.api.query;

import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.constants.RentSearch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 房产中心：房产查询服务
 * @Auther: 冲之
 * @Date: 2019/2/15 22:16
 */
@Api(tags = {"房产中心：房产查询服务"})
@FeignClient(value = "sise-house", path = "/house/query")
@Validated
public interface IHouseQueryApi {

    @GetMapping("/houseInfo/{houseId}")
    @ApiOperation(value = "查询房屋全部信息", notes = "查询房屋全部信息")
    ServerResponse queryHouseInfoById(@PathVariable(name = "houseId") Long houseId);

    @PostMapping("/list")
    @ApiOperation(value = "分页条件查询房屋信息列表", notes = "分页条件查询房屋信息列表")
    ServerResponse queryHouseInfoByPage(@RequestBody RentSearch rentSearch);

}
