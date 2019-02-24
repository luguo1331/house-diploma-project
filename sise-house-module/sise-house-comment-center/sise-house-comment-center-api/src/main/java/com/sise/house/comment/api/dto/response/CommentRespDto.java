package com.sise.house.comment.api.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 评论 RespDto
 * @Auther: 冲之
 * @Date: 2019/2/21 22:41
 */
@ApiModel(value = "CommentRespDto", description = "评论 RespDto")
@Data
public class CommentRespDto implements Serializable {
    private static final long serialVersionUID = -1964056498599762532L;

    private Long id;

    private String content;

    private Long houseId;

    private Date createTime;

    private Long blogId;

    private Byte type;

    private Long userId;
}
