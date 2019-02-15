package com.sise.house.user.biz.service;

import com.sise.house.user.biz.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 用户中心：用户查询 Service层
 * @Auther: 冲之
 * @Date: 2019/2/15 18:18
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;


}
