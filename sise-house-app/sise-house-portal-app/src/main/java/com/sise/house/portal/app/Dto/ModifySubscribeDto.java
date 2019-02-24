package com.sise.house.portal.app.Dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 06:28
 */
@Data
public class ModifySubscribeDto extends SubscribeDto{

    @NotNull(message = "状态不能为空")
    private Integer status;
}
