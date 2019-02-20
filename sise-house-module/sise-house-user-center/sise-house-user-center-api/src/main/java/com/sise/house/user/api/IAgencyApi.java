package com.sise.house.user.api;

import com.sise.common.rest.ServerResponse;
import com.sise.house.user.api.dto.request.AgencyReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 用户中心：中介机构接口
 * @Auther: 冲之
 * @Date: 2019/2/15 18:59
 */
@Api(tags = {"用户中心：中介机构接口"})
@FeignClient(value = "sise-user", path = "/agency")
public interface IAgencyApi {

    @PostMapping("/add")
    @ApiOperation(value = "新增中介机构", notes = "新增中介机构")
    ServerResponse addAgency(@RequestBody @Validated AgencyReqDto agencyReqDto);

    @PostMapping("/modify")
    @ApiOperation(value = "修改中介机构", notes = "修改中介机构")
    ServerResponse modifyAgency(@RequestBody AgencyReqDto agencyReqDto);
}
