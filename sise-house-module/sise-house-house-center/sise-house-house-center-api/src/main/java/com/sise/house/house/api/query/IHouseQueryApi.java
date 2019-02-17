package com.sise.house.house.api.query;

import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;

/**
 * @Description: 房产中心：房产查询服务
 * @Auther: 冲之
 * @Date: 2019/2/15 22:16
 */
@Api(tags = {"房产中心：房产查询服务"})
@FeignClient(value = "sise-house",path = "/house/query")
@Validated
public interface IHouseQueryApi {

}
