package com.sise.house.user.api.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

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
     * 经纪机构名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 手机
     */
    private String phone;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 描述
     */
    private String aboutUs;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 网站
     */
    private String webSite;
}
