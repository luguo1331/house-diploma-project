package com.sise.house.blog.biz.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.common.util.IdWorker;
import com.sise.house.blog.api.dto.response.BlogRespDto;
import com.sise.house.blog.biz.dao.BlogMapper;
import com.sise.house.blog.biz.pojo.Blog;
import com.sise.house.blog.biz.pojo.BlogExample;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 21:48
 */
@Service
public class BlogService {
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private IdWorker idWorker;
    @Resource
    private ModelMapper modelMapper;

    /**
     * 新增博客
     *
     * @param blog
     */
    public Long addBlog(Blog blog) {
        blog.setId(idWorker.nextId());
        blog.setCreateTime(new Date());
        blogMapper.insert(blog);
        return blog.getId();
    }

    /**
     * 博客详情
     *
     * @param id
     * @return
     */
    public Blog queryBlogById(Long id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询博客
     *
     * @param pageSize
     * @param pageNum
     * @return
     */
    public PageInfo<BlogRespDto> queryBlogByPage(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        BlogExample blogExample = new BlogExample();
        List<Blog> blogs = blogMapper.selectByExample(blogExample);
        PageInfo pageInfo = new PageInfo(blogs);
        List<BlogRespDto> map = modelMapper.map(blogs, new TypeToken<List<BlogRespDto>>() {
        }.getType());
        pageInfo.setList(map);
        return pageInfo;
    }
}
