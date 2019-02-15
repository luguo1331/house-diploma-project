package com.sise.house.blog.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 示例 ReqDto
 * @Auther: 冲之
 * @Date: 2019/1/30 16:06
 */
@ApiModel(value = "ExampleReqDto", description = "示例 ReqDto")
@Data
public class ExampleReqDto implements Serializable {

    private static final long serialVersionUID = 3344271538728787553L;
    /**
     * 用户名/登录名
     */
    @ApiModelProperty(name = "userName", value = "用户名/登录名")
    private String userName;
}
