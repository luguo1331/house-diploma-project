package com.sise.house.house.api.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/20 02:08
 */
@Data
public class HouseSubscribeRespDto implements Serializable {
    private static final long serialVersionUID = 5128334761629360574L;

    private Long id;

    private Long houseId;

    private Long userId;

    private String desc;

    private Integer status;

    private Date createTime;

    private Date lastUpdateTime;

    private Date orderTime;

    private String telephone;

    private Long adminId;
}
