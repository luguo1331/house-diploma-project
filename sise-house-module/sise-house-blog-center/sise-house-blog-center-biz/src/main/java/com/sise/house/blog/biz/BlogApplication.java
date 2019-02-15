package com.sise.house.blog.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: 资讯中心启动类
 * @Auther: 冲之
 * @Date: 2019/2/15 20:03
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.sise.house.blog.biz.dao")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
