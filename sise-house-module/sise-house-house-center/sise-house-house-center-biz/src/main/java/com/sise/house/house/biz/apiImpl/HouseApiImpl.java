package com.sise.house.house.biz.apiImpl;

import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.IHouseApi;
import com.sise.house.house.api.dto.request.AuditReqDto;
import com.sise.house.house.api.dto.request.HouseMsgReqDto;
import com.sise.house.house.biz.service.HouseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/19 03:55
 */
@RestController
@RequestMapping("/house")
public class HouseApiImpl implements IHouseApi {
    @Resource
    private HouseService houseService;

    @PostMapping("/add")
    @ApiOperation(value = "新增房源", notes = "新增房源")
    @Override
    public ServerResponse addHouse(@RequestBody HouseMsgReqDto houseMsgReqDto) {
        Long aLong = houseService.addHouse(houseMsgReqDto);
        return ServerResponse.createBySuccess(aLong);
    }

    @Override
    @PostMapping("/audit")
    @ApiOperation(value = "审核房源", notes = "审核房源")
    public ServerResponse auditHouse(@RequestBody AuditReqDto auditReqDto) {
        houseService.auditHouseStatus(auditReqDto.getHouseId(), auditReqDto.getStatus());
        return ServerResponse.createBySuccess();
    }

    @Override
    @PostMapping("/modify")
    @ApiOperation(value = "修改房源", notes = "修改房源")
    public ServerResponse modifyHouse(HouseMsgReqDto houseMsgReqDto) {
        houseService.updateHouse(houseMsgReqDto);
        return ServerResponse.createBySuccess();
    }
}
