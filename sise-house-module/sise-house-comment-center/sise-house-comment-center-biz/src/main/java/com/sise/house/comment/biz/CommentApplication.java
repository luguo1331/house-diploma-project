package com.sise.house.comment.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: 消息中心启动类
 * @Auther: 冲之
 * @Date: 2019/2/15 20:13
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.sise.house.comment.biz.dao")
public class CommentApplication {
}