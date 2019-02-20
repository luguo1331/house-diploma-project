package com.sise.house.user.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户响应 ReqDto
 * @Auther: 冲之
 * @Date: 2019/2/15 17:21
 */
@ApiModel(value = "UserReqDto", description = "用户响应 ReqDto")
@Data
public class UserReqDto implements Serializable {

    private static final long serialVersionUID = -5383218606919539536L;

    /**
     * 主键
     */
    @ApiModelProperty(name = "id", value = "主键")
    private long id;

    /**
     * 用户名
     */
    @ApiModelProperty(name = "name", value = "用户名")
    private String name;

    /**
     * 手机号
     */
    @ApiModelProperty(name = "phoneNumber", value = "手机号")
    private Object phoneNumber;

    /**
     * 电子邮件
     */
    @ApiModelProperty(name = "email", value = "电子邮件")
    @NotBlank(message = "邮件不能为空")
    private String email;

    /**
     * 自我介绍
     */
    @ApiModelProperty(name = "aboutme", value = "自我介绍")
    private String aboutme;

    /**
     * 经过MD5加密的密码
     */
    @ApiModelProperty(name = "passwd", value = "经过MD5加密的密码")
    @NotBlank(message = "密码不能为空")
    private String passwd;

    /**
     * 头像图片
     */
    @ApiModelProperty(name = "avatar", value = " 头像图片")
    private String avatar;

    /**
     * 1:普通用户，2:房产经纪人
     */
    @ApiModelProperty(name = "type", value = "1:普通用户，2:房产经纪人")
    private int type;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    /**
     * 是否启用，1启用，0停用
     */
    @ApiModelProperty(name = "enable", value = "是否启用，1启用，0停用")
    private int enable;

    /**
     * 所属经纪机构
     */
    @ApiModelProperty(name = "agencyId", value = "所属经纪机构")
    private long agencyId;

    /**
     * 上次登录时间
     */
    @ApiModelProperty(name = "lastLoginTime", value = "上次登录时间")
    private Date lastLoginTime;

    /**
     * 上次更新记录时间
     */
    @ApiModelProperty(name = "lastUpdateTime", value = "上次更新记录时间")
    private Date lastUpdateTime;

}
