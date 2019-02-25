package com.sise.house.admin.app.support;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description: Token
 * @Auther: 冲之
 * @Date: 2019/1/6 22:47
 */
@Data
public class Token {
    private Long userId;
    private String token;
    private LocalDateTime expireTime;
    private LocalDateTime updateTime;
}
