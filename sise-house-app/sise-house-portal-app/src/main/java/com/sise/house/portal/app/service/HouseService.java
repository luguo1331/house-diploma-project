package com.sise.house.portal.app.service;

import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.constants.RentSearch;
import com.sise.house.house.api.query.IHouseQueryApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 04:37
 */
@Service
public class HouseService {
    @Resource
    private IHouseQueryApi houseQueryApi;

    /**
     * 查询我的房屋列表
     *
     * @param pageSize
     * @param pageNum
     * @param userId
     * @return
     */
    public ServerResponse queryHouseById(Integer pageSize, Integer pageNum, Long userId) {
        return houseQueryApi.queryHouseById(pageSize, pageNum, userId);
    }

    /**
     * 分页条件查询房屋信息列表
     *
     * @param rentSearch
     * @return
     */
    public ServerResponse queryHouseInfoByPage(RentSearch rentSearch) {
        return houseQueryApi.queryHouseInfoByPage(rentSearch);
    }


}
