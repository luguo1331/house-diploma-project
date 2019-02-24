package com.sise.house.portal.app.Dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 06:07
 */
@Data
public class SubscribeDto {

    @NotNull(message = "房屋Id不能为空")
    private Long houseId;
}
