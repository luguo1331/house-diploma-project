package com.sise.house.house.api.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 房屋图片RespDto
 * @Auther: 冲之
 * @Date: 2019/2/21 03:08
 */
@ApiModel(value = "HousePictureRespDto", description = "房屋图片 RespDto")
@Data
public class HousePictureRespDto implements Serializable {
    private static final long serialVersionUID = -5007407333594998018L;

    private Long id;

    private Long houseId;

    private String cdnPrefix;

    private Integer width;

    private Integer height;

    private String location;

    private String path;
}
