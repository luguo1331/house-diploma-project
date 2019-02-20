package com.sise.house.user.api.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 中介机构 ReqDto
 * @Auther: 冲之
 * @Date: 2019/2/15 19:14
 */
@ApiModel(value = "AgencyReqDto", description = "中介机构 ReqDto")
@Data
public class AgencyReqDto implements Serializable {

    private static final long serialVersionUID = -398710567961725969L;

    /**
     * id
     */
    private Long id;

    /**
     * 负责人ID
     */
    @NotNull(message = "负责人ID不能为空")
    private Long userId;

    /**
     * 经纪机构名称
     */
    @NotBlank(message = "经纪机构名称不能为空")
    private String name;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * 手机
     */
    @NotBlank(message = "手机不能为空")
    private String phone;

    /**
     * 电子邮件
     */
    @NotBlank(message = "电子邮件不能为空")
    private String email;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空")
    private String aboutUs;

    /**
     * 电话
     */
    @NotBlank(message = "电话不能为空")
    private String mobile;

    /**
     * 网站
     */
    @NotBlank(message = "网站不能为空")
    private String webSite;
}
