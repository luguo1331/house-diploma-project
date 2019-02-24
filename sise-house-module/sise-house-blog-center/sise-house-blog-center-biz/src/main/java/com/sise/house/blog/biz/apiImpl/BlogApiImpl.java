package com.sise.house.blog.biz.apiImpl;

import com.sise.common.rest.ServerResponse;
import com.sise.house.blog.api.IBlogApi;
import com.sise.house.blog.api.dto.request.BlogReqDto;
import com.sise.house.blog.biz.Service.BlogService;
import com.sise.house.blog.biz.pojo.Blog;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 22:13
 */
@RestController
@RequestMapping("/blog/")
public class BlogApiImpl implements IBlogApi {

    @Resource
    private BlogService blogService;
    @Resource
    private ModelMapper modelMapper;

    @ApiOperation(value = "新增博客", notes = "新增博客")
    public ServerResponse addBlog(@RequestBody BlogReqDto blogReqDto) {
        Blog map = modelMapper.map(blogReqDto, Blog.class);
        Long aLong = blogService.addBlog(map);
        return ServerResponse.createBySuccess(aLong);
    }

}
