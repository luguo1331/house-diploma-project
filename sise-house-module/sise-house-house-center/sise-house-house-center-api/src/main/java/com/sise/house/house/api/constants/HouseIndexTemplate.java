package com.sise.house.house.api.constants;

import com.sise.house.house.api.dto.response.BaiduMapLocationRespDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/18 21:13
 */
@Data
public class HouseIndexTemplate implements Serializable {
    private static final long serialVersionUID = -3178762932935905186L;

    private Long houseId;

    private String name;

    private int price;

    private int area;

    private Date createTime;

    private Date lastUpdateTime;

    private String cityEnName;

    private String regionEnName;

    private int direction;

    private int distanceToSubway;

    private String subwayLineName;

    private String subwayStationName;

    private String street;

    private String district;

    private String description;

    private String layoutDesc;

    private String traffic;

    private String roundService;

    private int type;

    private List<String> tags;

    private List<HouseSuggest> suggest;

    private BaiduMapLocationRespDto location;
}
