package com.sise.house.house.api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 预约看房Dto
 * @Auther: 冲之
 * @Date: 2019/2/20 02:13
 */
@Data
public class SureSubscribeReqDto implements Serializable {
    private static final long serialVersionUID = -6798371106964907643L;

    @NotNull(message = "房屋Id")
    private Long houseId;

    @NotNull(message = "用户Id")
    private Long userId;

    @NotBlank(message = "预约时间不能为空")
    private Date orderTime;

    @NotBlank(message = "预约手机不能为空")
    private String telephone;

    private String desc;
}
