package com.sise.house.house.api;

import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;

/**
 * @Description: 房产中心：支持城市服务
 * @Auther: 冲之
 * @Date: 2019/2/15 22:19
 */
@Api(tags = {"房产中心：支持城市服务"})
@FeignClient(value = "sise-house", path = "/houseSupport/")
@Validated
public interface IHouseSupportApi {


}
