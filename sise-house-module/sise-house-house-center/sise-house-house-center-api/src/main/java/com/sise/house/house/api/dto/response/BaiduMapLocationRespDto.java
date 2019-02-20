package com.sise.house.house.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 百度位置信息 RespDto
 * @Auther: 冲之
 * @Date: 2019/2/18 16:29
 */
@ApiModel(value = "BaiduMapLocationRespDto", description = "百度位置信息 RespDto")
@Data
public class BaiduMapLocationRespDto implements Serializable {
    private static final long serialVersionUID = 7927248958565230204L;
    /**
     * 经度
     */
    @JsonProperty("lon")
    private double longitude;

    /**
     * 纬度
     */
    @JsonProperty("lat")
    private double latitude;
}
