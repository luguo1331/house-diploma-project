package com.sise.house.portal.app.service;

import com.sise.common.rest.ServerResponse;
import com.sise.house.user.api.IUserApi;
import com.sise.house.user.api.query.IUserQueryApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/17 15:14
 */
@Service
public class UserService {
    @Resource
    private IUserApi userApi;

    @Resource
    private IUserQueryApi userQueryApi;

    /**
     * 查询用户信息
     *
     * @param id
     * @param type
     * @return
     */
    public ServerResponse queryUserById(Long id, Boolean type) {
        return userQueryApi.queryUserById(id, type);
    }
}
