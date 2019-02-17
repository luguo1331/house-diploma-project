package com.sise.house.house.api;

import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;

/**
 * @Description: 房产中心：房产服务
 * @Auther: 冲之
 * @Date: 2019/2/15 22:16
 */
@Api(tags = {"房产中心：房产服务"})
@FeignClient(value = "sise-house", path = "/house/")
@Validated
public interface IHouseApi {

    //TODO 新增房源

    //TODO 修改房源信息
}
