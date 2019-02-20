package com.sise.house.house.api.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 审核 ReqDto
 * @Auther: 冲之
 * @Date: 2019/2/19 04:23
 */
@Data
@ApiModel(value = "AuditReqDto", description = "审核 ReqDto")
public class AuditReqDto implements Serializable {

    private static final long serialVersionUID = -3438668072420798318L;

    @NotNull(message = "房屋ID不允许为空")
    private Long houseId;

    @NotNull(message = "状态不允许为空")
    private Integer status;
}
