package com.sise.house.portal.app.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description: 修改密码Dto
 * @Auther: 冲之
 * @Date: 2019/2/21 04:01
 */
@ApiModel(value = "RegisterDto", description = "注册 Dto")
@Data
public class PasswordDto {

    /**
     * 电子邮件
     */
    @ApiModelProperty(name = "email", value = "电子邮件")
    @NotBlank(message = "邮件不能为空")
    private String email;

    /**
     * 经过MD5加密的密码
     */
    @ApiModelProperty(name = "passwd", value = "经过MD5加密的密码")
    @NotBlank(message = "密码不能为空")
    private String passwd;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;
}
