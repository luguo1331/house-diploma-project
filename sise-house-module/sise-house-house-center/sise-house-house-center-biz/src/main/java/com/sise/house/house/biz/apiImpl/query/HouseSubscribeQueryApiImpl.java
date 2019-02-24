package com.sise.house.house.biz.apiImpl.query;

import com.github.pagehelper.PageInfo;
import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.dto.response.HouseSubscribeRespDto;
import com.sise.house.house.api.query.IHouseSubscribeQueryApi;
import com.sise.house.house.biz.pojo.HouseSubscribe;
import com.sise.house.house.biz.service.HouseSubscribeService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 05:40
 */
public class HouseSubscribeQueryApiImpl implements IHouseSubscribeQueryApi {

    @Resource
    private HouseSubscribeService houseSubscribeService;
    @Resource
    private ModelMapper modelMapper;

    @Override
    public @ApiOperation(value = "查看用户的预约清单列表", notes = "查看用户的预约清单列表")
    ServerResponse querySubscribeByPage(@RequestParam(name = "pageSize") Integer pageSize,
                                        @RequestParam(name = "pageNum") Integer pageNum,
                                        @RequestParam(name = "status") Integer status,
                                        @RequestParam(name = "userId") Long userId) {
        PageInfo<HouseSubscribeRespDto> houseSubscribeRespDtoPageInfo = houseSubscribeService.querySubscribeByPage(pageSize, pageNum, status, userId);
        return ServerResponse.createBySuccess(houseSubscribeRespDtoPageInfo);
    }


    @Override
    @ApiOperation(value = "查看用户的预约清单列表", notes = "查看用户的预约清单列表")
    public ServerResponse queryBeSubscribe(@RequestParam(name = "pageSize") Integer pageSize,
                                           @RequestParam(name = "pageNum") Integer pageNum,
                                           @RequestParam(name = "status") Integer status,
                                           @RequestParam(name = "adminId") Long adminId) {
        PageInfo<HouseSubscribeRespDto> houseSubscribeRespDtoPageInfo = houseSubscribeService.queryBeSubscribe(pageSize, pageNum, status, adminId);
        return ServerResponse.createBySuccess(houseSubscribeRespDtoPageInfo);
    }

    @Override
    @ApiOperation(value = "查看用户的预约清单详情", notes = "查看用户的预约清单详情")
    public ServerResponse querySubscribeDetail(@RequestParam(name = "id") Long id,
                                               @RequestParam(name = "userId") Long userId) {
        HouseSubscribe bySubscribe = houseSubscribeService.findBySubscribe(id, userId);
        HouseSubscribeRespDto map = modelMapper.map(bySubscribe, HouseSubscribeRespDto.class);
        return ServerResponse.createBySuccess(map);
    }


}
