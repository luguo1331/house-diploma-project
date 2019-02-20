package com.sise.house.house.api.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 地铁线路 RespDto
 * @Auther: 冲之
 * @Date: 2019/2/18 16:05
 */
@ApiModel(value = "SubwayRespDto", description = "地铁线路 RespDto")
@Data
public class SubwayRespDto  implements Serializable {
    private static final long serialVersionUID = -3911908380150101508L;
    private Long id;
    private String name;
    private String cityEnName;
}
