package com.sise.house.user.api.query;

import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/15 18:59
 */
@Api(tags = {"用户中心：中介查询接口"})
@FeignClient(value = "sise-user", path = "/agency/query")
@Validated
public interface IAgencyQueryApi {
}
