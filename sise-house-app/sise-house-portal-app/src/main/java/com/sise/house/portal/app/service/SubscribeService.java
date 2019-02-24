package com.sise.house.portal.app.service;

import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.IHouseSubscribeApi;
import com.sise.house.house.api.dto.request.SureSubscribeReqDto;
import com.sise.house.house.api.query.IHouseQueryApi;
import com.sise.house.house.api.query.IHouseSubscribeQueryApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 05:44
 */
@Service
public class SubscribeService {
    @Resource
    private IHouseSubscribeApi houseSubscribeApi;
    @Resource
    private IHouseSubscribeQueryApi houseSubscribeQueryApi;
    @Resource
    private IHouseQueryApi houseQueryApi;

    /**
     * 查看用户的预约清单列表
     *
     * @param pageSize
     * @param pageNum
     * @param status
     * @param userId
     * @return
     */
    public ServerResponse querySubscribeByPage(Integer pageSize, Integer pageNum, Integer status, Long userId) {
        return houseSubscribeQueryApi.querySubscribeByPage(pageSize, pageNum, status, userId);
    }

    /**
     * 查看用户的被预约清单列表
     *
     * @param pageSize
     * @param pageNum
     * @param status
     * @param adminId
     * @return
     */
    public ServerResponse queryBeSubscribe(Integer pageSize, Integer pageNum, Integer status, Long adminId) {
        return houseSubscribeQueryApi.queryBeSubscribe(pageSize, pageNum, status, adminId);
    }

    /**
     * 查看用户的预约清单详情
     *
     * @param id
     * @param userId
     * @return
     */
    public ServerResponse querySubscribeDetail(Long id, Long userId) {
        return houseSubscribeQueryApi.querySubscribeDetail(id, userId);
    }

    /**
     * 新增预约
     *
     * @param houseId
     * @param userId
     * @return
     */
    public ServerResponse addSubscribeOrder(Long houseId, Long userId) {
        return houseSubscribeApi.addSubscribeOrder(houseId, userId);
    }

    /**
     * 确认预约
     */
    public ServerResponse subscribe(SureSubscribeReqDto sureSubscribeReqDto) {
        return houseSubscribeApi.subscribe(sureSubscribeReqDto);
    }

    /**
     * 修改预约状态
     *
     * @param houseId
     * @param userId
     * @param status
     * @return
     */
    public ServerResponse modifySubscribe(Long houseId, Long userId, Integer status) {
        return houseSubscribeApi.modifySubscribe(houseId, userId, status);
    }
}
