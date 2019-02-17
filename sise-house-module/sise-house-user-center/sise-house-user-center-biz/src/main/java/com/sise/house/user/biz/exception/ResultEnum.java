package com.sise.house.user.biz.exception;

import lombok.Getter;

/**
 * @author Stalary
 * @description
 * @date 2018/03/24
 */
public enum ResultEnum {
    /**
     * 服务器错误
     */
    UNKNOW_ERROR(500, "服务器错误"),

    // 1开头为用户有关的错误
    /**
     * 未登陆
     */
    NEED_LOGIN(1001, "未登陆"),
    /**
     *
     */
    EXIST_EMAIL(1002, "邮箱已被注册"),
    /**
     * 手机已被使用
     */
    EXIST_PHONE(1003, "手机已被使用"),
    /**
     * 昵称已被使用
     */
    EXIST_NICK_NAME(1004, "昵称已被使用"),
    /**
     * 登陆失败
     */
    LOGIN_ERROR(1005, "登陆失败"),
    /**
     * 不存在此用户
     */
    UN_EXIST_USER(1006, "不存在此用户"),
    /**
     * 更新用户失败
     */
    MODIFY_USER_ERROR(1007, "更新用户失败"),

    // 2开头为第三方接口的错误
    /**
     * 发送短信失败
     */
    SEND_NOTE_ERROR(2001, "发送短信失败"),
    /**
     * 七牛云接口出错
     */
    QINIU_ERROR(2002, "七牛云接口出错"),
    /**
     * 成功
     */
    SUCCESS(0, "成功");

    @Getter
    private Integer code;

    @Getter
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
