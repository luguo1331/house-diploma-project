package com.sise.house.portal.app.Dto;

import com.sise.house.user.api.dto.request.UserReqDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description: 注册 Dto
 * @Auther: 冲之
 * @Date: 2019/2/18 05:37
 */
@ApiModel(value = "RegisterDto", description = "注册 Dto")
@Data
public class RegisterDto extends UserReqDto {

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;
}
