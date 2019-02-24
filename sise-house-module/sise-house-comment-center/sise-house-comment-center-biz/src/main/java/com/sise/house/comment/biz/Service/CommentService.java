package com.sise.house.comment.biz.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.common.util.IdWorker;
import com.sise.house.comment.api.dto.response.CommentRespDto;
import com.sise.house.comment.biz.dao.CommentMapper;
import com.sise.house.comment.biz.pojo.Comment;
import com.sise.house.comment.biz.pojo.CommentExample;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 22:36
 */
@Service
public class CommentService {

    @Resource
    private ModelMapper modelMapper;
    @Resource
    private IdWorker idWorker;
    @Resource
    private CommentMapper commentMapper;

    /**
     * 新增评论
     *
     * @param comment
     * @return
     */
    public Long addComment(Comment comment) {
        comment.setId(idWorker.nextId());
        comment.setCreateTime(new Date());
        commentMapper.insert(comment);
        return comment.getId();
    }

    /**
     * 分页查询评论
     *
     * @param pageSize
     * @param pageNum
     * @param type
     * @return
     */
    public PageInfo<CommentRespDto> queryCommentByPage(Integer pageSize, Integer pageNum, Byte type) {
        PageHelper.startPage(pageNum, pageSize);
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("`create_time` desc");
        commentExample.createCriteria()
                .andTypeEqualTo(type);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        PageInfo pageInfo = new PageInfo(comments);
        List<CommentRespDto> map = modelMapper.map(comments, new TypeToken<List<CommentRespDto>>() {
        }.getType());
        pageInfo.setList(map);
        return pageInfo;
    }
}
