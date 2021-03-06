package com.sise.house.house.api.dto.request;

import com.sise.house.house.api.constants.PhotoForm;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description: 房屋基本信息查询 DTO
 * @Auther: 冲之
 * @Date: 2019/2/15 21:38
 */
@ApiModel(value = "HouseMsgReqDto", description = "房屋基本信息查询 ReqDto")
@Data
public class HouseMsgReqDto implements Serializable {
    private static final long serialVersionUID = 5268065456134081720L;

    private Long id;
    /**
     * 房产名称
     */
    @NotNull(message = "大标题不允许为空!")
    @Size(min = 1, max = 30, message = "标题长度必须在1~30之间")
    private String name;

    /**
     * 1:销售，2:出租
     */
    @NotNull(message = "必须选中一个租赁方式")
    @Min(value = 1)
    @Max(value = 2)
    private Byte type;

    /**
     * 单位元
     */
    @NotNull(message = "必须填写租赁价格")
    @Min(value = 1)
    private Integer price;

    /**
     * 面积
     */
    @NotNull(message = "必须填写面积")
    @Min(value = 1)
    private Integer area;

    /**
     * 卧室数量
     */
    @NotNull(message = "必须填写卧室数量")
    @Min(value = 0, message = "非法的卧室数量")
    private Integer beds;

    /**
     * 卫生间数量
     */
    private Integer baths;

    /**
     * 客厅数量
     */
    private Integer parlour;

    /**
     * 楼层
     */
    @NotNull(message = "必须填写所属楼层")
    private Integer floor;

    /**
     * 总楼层
     */
    @NotNull(message = "必须填写总楼层")
    private Integer totalFloor;

    /**
     * 被看次数
     */
    private Integer watchTimes;

    /**
     * 建立年限
     */
    @NotNull(message = "必须填写建筑起始时间")
    @Min(value = 1900, message = "非法的建筑起始时间")
    private Integer buildYear;
    /**
     * 评级
     */
    private Double rating;
    /**
     * 房产描述
     */
    @Size(max = 255)
    private String remarks;
    /**
     * 属性
     */
    private String properties;
    /**
     * 户型图
     */
    private String floorPlan;
    /**
     * 城市标记缩写 如 北京bj
     */
    @NotNull(message = "必须选中一个城市")
    @Size(min = 1, message = "非法的城市")
    private String cityEnName;
    /**
     * 地区英文简写 如昌平区 cpq
     */
    @NotNull(message = "必须选中一个地区")
    @Size(min = 1, message = "非法的地区")
    private String regionEnName;
    /**
     * 小区id
     */
    private Long communityId;
    /**
     * 所在小区名称
     */
    @NotNull(message = "必须填写小区")
    private String district;
    /**
     * 房产地址
     */
    private String address;
    /**
     * 封面
     */
    private String cover;
    /**
     * 房屋朝向
     */
    @NotNull(message = "必须填写房屋朝向")
    private Integer direction;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最近数据更新时间
     */
    private Date lastUpdateTime;
    /**
     * 房屋状态 0-未审核 1-审核通过 2-已出租 3-逻辑删除
     */
    private Integer status;
    /**
     * '距地铁距离 默认-1 附近无地铁
     */
    private Integer distanceToSubway;
    /**
     * 所属经纪
     */
    private Long userId;
    /**
     * 街道
     */
    @NotNull(message = "必须填写街道")
    @Size(min = 1, message = "非法的街道")
    private String street;


    private Long subwayLineId;

    private Long subwayStationId;

    private String layoutDesc;

    private String roundService;

    private String traffic;

    @Size(max = 255)
    private String description;

    private List<String> tags;

    private List<PhotoForm> photos;

}
