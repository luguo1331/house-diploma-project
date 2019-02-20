package com.sise.house.user.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description: 登陆
 * @Auther: 冲之
 * @Date: 2019/2/17 20:48
 */
@ApiModel(value = "LoginReqDto", description = "登陆 ReqDto")
@Data
public class LoginReqDto implements Serializable {

    private static final long serialVersionUID = 6602154625266210210L;
    /**
     * 电子邮件
     */
    @ApiModelProperty(name = "email", value = "电子邮件")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 经过MD5加密的密码
     */
    @ApiModelProperty(name = "passwd", value = "经过MD5加密的密码")
    @NotBlank(message = "密码不能为空")
    private String passwd;
}
