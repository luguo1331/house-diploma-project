package com.sise.house.house.api.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 房产基本信息 RespDto
 * @Auther: 冲之
 * @Date: 2019/2/21 04:20
 */
@ApiModel(value = "HouseBaseInfoRespDto", description = "房产基本信息 RespDto")
@Data
public class HouseBaseInfoRespDto implements Serializable {
    private static final long serialVersionUID = -8845762505885453670L;
    private Long id;

    private String name;

    private Byte type;

    private Integer price;

    private Integer area;

    private Integer beds;

    private Integer baths;

    private Integer parlour;

    private Integer floor;

    private Integer totalFloor;

    private Integer watchTimes;

    private Integer buildYear;

    private Double rating;

    private String remarks;

    private String properties;

    private String floorPlan;

    private String cityEnName;

    private String regionEnName;

    private Long communityId;

    private String district;

    private String address;

    private String cover;

    private Integer direction;

    private Date createTime;

    private Date lastUpdateTime;

    private Integer status;

    private Integer distanceToSubway;

    private Long userId;

    private String street;
}
