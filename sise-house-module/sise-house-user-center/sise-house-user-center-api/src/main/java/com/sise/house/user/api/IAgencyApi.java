package com.sise.house.user.api;

import com.sise.common.rest.ServerResponse;
import com.sise.house.user.api.dto.request.AgencyReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 用户中心：中介机构接口
 * @Auther: 冲之
 * @Date: 2019/2/15 18:59
 */
@Api(tags = {"用户中心：中介机构接口"})
@FeignClient(value = "sise-user", path = "/agency")
@Validated
public interface IAgencyApi {

    @RequestMapping("/add")
    @ApiOperation(value = "新增中介", notes = "新增中介")
    ServerResponse addAgency(@RequestBody AgencyReqDto agencyReqDto);

}
