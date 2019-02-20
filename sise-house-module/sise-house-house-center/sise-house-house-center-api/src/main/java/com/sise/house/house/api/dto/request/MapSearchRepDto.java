package com.sise.house.house.api.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/19 01:07
 */
@Data
public class MapSearchRepDto implements Serializable {
    private static final long serialVersionUID = -8237822489019931536L;
    private String cityEnName;

    /**
     * 地图缩放级别
     */
    private int level = 12;
    private String orderBy = "lastUpdateTime";
    private String orderDirection = "desc";
    /**
     * 左上角
     */
    private Double leftLongitude;
    private Double leftLatitude;

    /**
     * 右下角
     */
    private Double rightLongitude;
    private Double rightLatitude;

    private int start = 0;
    private int size = 5;
}
