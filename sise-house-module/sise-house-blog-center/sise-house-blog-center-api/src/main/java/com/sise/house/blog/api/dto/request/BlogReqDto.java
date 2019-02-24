package com.sise.house.blog.api.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 博客 ReqDto
 * @Auther: 冲之
 * @Date: 2019/2/21 21:51
 */
@ApiModel(value = "BlogReqDto", description = "博客 ReqDto")
@Data
public class BlogReqDto implements Serializable {
    private static final long serialVersionUID = 5037216819458084321L;

    private Long id;

    private Long userId;

    private String tags;

    private Date createTime;

    private String title;

    private Long cat;

    private String content;
}
