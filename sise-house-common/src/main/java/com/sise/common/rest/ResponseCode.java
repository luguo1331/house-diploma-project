package com.sise.common.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 回复参数枚举类型
 * @Auther: 冲之
 * @Date: 2018/12/2 21:36
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(1, "SUCCESS"),
    /**
     * 错误
     */
    ERROR(2, "ERROR"),
    /**
     * 非法参数
     */
    ILLEGAL_ARGUMENT(3, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;
}
