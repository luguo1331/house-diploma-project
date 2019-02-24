package com.sise.house.comment.biz;

import com.sise.common.util.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @Description: 消息中心启动类
 * @Auther: 冲之
 * @Date: 2019/2/15 20:13
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.sise.house.comment.biz.dao")
public class CommentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentApplication.class, args);
    }

    @Bean
    public IdWorker IdWorker() {
        return new IdWorker();
    }

}
