package com.sise.house.user.biz.apiImpl.query;

import com.sise.common.rest.ServerResponse;
import com.sise.house.user.api.query.IUserQueryApi;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

/**
 * @Description: 用户中心：用户查询 实现类
 * @Auther: 冲之
 * @Date: 2019/2/15 18:15
 */
public class UserQueryApiImpl implements IUserQueryApi {

    @Resource

    @ApiOperation(value = "根据id查询用户信息", notes = "根据id查询用户信息")
    public ServerResponse getUserById(Long id) {
        return null;
    }
}
