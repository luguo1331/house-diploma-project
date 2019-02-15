package com.sise.house.user.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: 用户中心启动类
 * @Auther: 冲之
 * @Date: 2019/2/14 23:12
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.sise.house.user.biz.dao")
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }
}
