package com.sise.house.user.api.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description: 中介机构 RespDto
 * @Auther: 冲之
 * @Date: 2019/2/15 19:14
 */
@ApiModel(value = "AgencyRespDto", description = "中介机构 RespDto")
@Data
public class AgencyRespDto {

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
