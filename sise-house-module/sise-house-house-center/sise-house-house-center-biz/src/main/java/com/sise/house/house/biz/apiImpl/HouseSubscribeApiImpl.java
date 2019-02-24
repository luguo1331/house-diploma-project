package com.sise.house.house.biz.apiImpl;

import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.IHouseSubscribeApi;
import com.sise.house.house.api.dto.request.SureSubscribeReqDto;
import com.sise.house.house.biz.service.HouseSubscribeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 05:35
 */
public class HouseSubscribeApiImpl implements IHouseSubscribeApi {

    @Resource
    private HouseSubscribeService houseSubscribeService;

    @Override
    public @ApiOperation(value = "新增预约", notes = "新增预约")
    ServerResponse addSubscribeOrder(@RequestParam(name = "houseId") Long houseId,
                                     @RequestParam(name = "userId") Long userId) {
        houseSubscribeService.addSubscribeOrder(houseId, userId);
        return ServerResponse.createBySuccess();
    }


    @Override
    @ApiOperation(value = "确认预约", notes = "确认预约")
    public ServerResponse subscribe(@RequestBody SureSubscribeReqDto sureSubscribeReqDto) {
        houseSubscribeService.subscribe(sureSubscribeReqDto);
        return ServerResponse.createBySuccess();
    }

    @Override
    @ApiOperation(value = "修改预约状态", notes = "修改预约状态")
    public ServerResponse modifySubscribe(@RequestParam(name = "houseId") Long houseId,
                                          @RequestParam(name = "userId") Long userId,
                                          @RequestParam(name = "status") Integer status) {
        houseSubscribeService.cancelSubscribe(houseId, userId, status);
        return ServerResponse.createBySuccess();
    }
}
