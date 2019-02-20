package com.sise.house.house.api;

import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.dto.request.AuditReqDto;
import com.sise.house.house.api.dto.request.HouseMsgReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: 房产中心：房产服务
 * @Auther: 冲之
 * @Date: 2019/2/15 22:16
 */
@Api(tags = {"房产中心：房产服务"})
@FeignClient(value = "sise-house", path = "/house/")
@Validated
public interface IHouseApi {

    @PostMapping("/add")
    @ApiOperation(value = "新增房源", notes = "新增房源")
    ServerResponse addHouse(@RequestBody HouseMsgReqDto houseMsgReqDto);

    @PostMapping("/audit")
    @ApiOperation(value = "审核房源", notes = "审核房源")
    ServerResponse auditHouse(@RequestBody AuditReqDto auditReqDto);

    @PostMapping("/modify")
    @ApiOperation(value = "修改房源", notes = "修改房源")
    ServerResponse modifyHouse(@RequestBody HouseMsgReqDto houseMsgReqDto);

}
