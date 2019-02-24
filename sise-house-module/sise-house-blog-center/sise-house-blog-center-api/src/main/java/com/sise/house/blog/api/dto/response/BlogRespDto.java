package com.sise.house.blog.api.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 博客 RespDto
 * @Auther: 冲之
 * @Date: 2019/2/21 21:52
 */
@ApiModel(value = "BlogRespDto", description = "博客 RespDto")
@Data
public class BlogRespDto implements Serializable {
    private static final long serialVersionUID = -8096353647350791L;

    private Long id;

    private Long userId;

    private String tags;

    private Date createTime;

    private String title;

    private Long cat;

    private String content;
}
