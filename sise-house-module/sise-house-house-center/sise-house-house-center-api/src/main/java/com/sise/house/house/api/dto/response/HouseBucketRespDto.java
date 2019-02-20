package com.sise.house.house.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 城市数据聚合 RespDto
 * @Auther: 冲之
 * @Date: 2019/2/19 00:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseBucketRespDto implements Serializable {
    private static final long serialVersionUID = -2081708492217143293L;
    /**
     * 聚合bucket的key
     */
    private String key;

    /**
     * 聚合结果值
     */
    private long count;
}
