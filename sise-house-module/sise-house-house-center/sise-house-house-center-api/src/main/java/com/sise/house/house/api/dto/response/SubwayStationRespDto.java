package com.sise.house.house.api.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 地铁站 RespDto
 * @Auther: 冲之
 * @Date: 2019/2/18 16:12
 */
@ApiModel(value = "SubwayStationRespDto", description = "地铁站 RespDto")
@Data
public class SubwayStationRespDto implements Serializable {
    private static final long serialVersionUID = 4560703517000712804L;
    private Long id;
    private Long subwayId;
    private String name;
}
