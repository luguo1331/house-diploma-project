package com.sise.house.comment.api.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 22:41
 */
@ApiModel(value = "CommentReqDto", description = "评论 ReqDto")
@Data
public class CommentReqDto implements Serializable {
    private static final long serialVersionUID = -262461067191970700L;

    private Long id;

    private String content;

    private Long houseId;

    private Date createTime;

    private Long blogId;

    private Byte type;

    private Long userId;
}
