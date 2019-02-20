package com.sise.house.house.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/18 15:26
 */
@ApiModel(value = "SupportAddressRespDto", description = "支持地址 RespDto")
@Data
public class SupportAddressRespDto implements Serializable {

    private static final long serialVersionUID = 7268727095451782024L;
    private Long id;
    @JsonProperty(value = "belong_to")
    private String belongTo;

    @JsonProperty(value = "en_name")
    private String enName;

    @JsonProperty(value = "cn_name")
    private String cnName;

    private String level;

    private double baiduMapLongitude;

    private double baiduMapLatitude;
}
