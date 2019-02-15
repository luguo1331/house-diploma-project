package com.sise.house.house.api.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 示例 RespDto
 * @Auther: 冲之
 * @Date: 2019/1/30 16:06
 */
@ApiModel(value = "ExampleRespDto", description = "示例 RespDto")
@Data
public class ExampleRespDto implements Serializable {

    private static final long serialVersionUID = -1944298284046766009L;

    /**
     * 用户名/登录名
     */
    @ApiModelProperty(name = "userName", value = "用户名/登录名")
    private String userName;
}
