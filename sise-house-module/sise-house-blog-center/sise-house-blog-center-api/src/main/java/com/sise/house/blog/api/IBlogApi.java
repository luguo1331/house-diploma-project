package com.sise.house.blog.api;

import com.sise.common.rest.ServerResponse;
import com.sise.house.blog.api.dto.request.BlogReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: 资讯中心：博客服务
 * @Auther: 冲之
 * @Date: 2019/2/21 21:54
 */
@Api(tags = {"资讯中心：博客服务"})
@FeignClient(value = "sise-blog", path = "/blog/")
@Validated
public interface IBlogApi {

    @PostMapping("/add")
    @ApiOperation(value = "新增博客", notes = "新增博客")
    ServerResponse addBlog(@RequestBody BlogReqDto blogReqDto);
}
