package com.sise.common.exception;

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

    /**
     * 验证码错误
     */
    ERROR_VERIFY_CODE(1008, "验证码错误"),

    // 2开头为第三方接口的错误
    /**
     * 发送短信失败
     */
    SEND_NOTE_ERROR(2001, "发送短信失败"),
    /**
     * 七牛云接口出错
     */
    QINIU_ERROR(2002, "七牛云接口出错"),

    //3 中介相关接口错误
    /**
     * 不存在此中介
     */
    UN_EXIST_AGENCY(3001, "不存在此中介"),
    /**
     * 更新中介失败
     */
    MODIFY_AGENCY_ERROR(3002, "更新中介失败"),
    //4 房产相关接口错误
    /**
     * 不存在此房子
     */
    UN_EXIST_HOUSE(4001, "不存在此房子"),
    /**
     * 此标签已存在
     */
    EXIST_TAG(4002, "此标签已存在"),

    /**
     * 此标签不存在
     */
    UN_EXIST_TAG(4003, "此标签不存在"),
    /**
     * 此图片不存在
     */
    UN_EXIST_PICTURE(4004, "此图片不存在"),
    /**
     * 已加入预约
     */
    EXIST_SUBSCRIBE(4005, "已加入预约"),
    /**
     * 无预约记录
     */
    UN_EXIST_SUBSCRIBE(4006, "无预约记录"),

    /**
     * 无法预约
     */
    ERROR_SUBSCRIBE(4007, "无法预约"),
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
