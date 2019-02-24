package com.sise.house.blog.api.query;

import com.github.pagehelper.PageInfo;
import com.sise.common.rest.ServerResponse;
import com.sise.house.blog.api.dto.response.BlogRespDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 资讯中心：博客查询服务
 * @Auther: 冲之
 * @Date: 2019/2/21 21:54
 */
@Api(tags = {"资讯中心：博客查询服务"})
@FeignClient(value = "sise-blog", path = "/blog/query")
@Validated
public interface IBlogQueryApi {

    @GetMapping("/list")
    @ApiOperation(value = "分页查询博客列表", notes = "分页查询博客列表")
    ServerResponse<PageInfo<BlogRespDto>> queryBlogByList(@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum);


    @GetMapping("/{id}")
    @ApiOperation(value = "博客详情", notes = "博客详情")
    ServerResponse queryBlogById(@PathVariable(name = "id") Long id);

}
