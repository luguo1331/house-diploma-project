package com.sise.house.comment.biz.apiImpl;

import com.sise.common.rest.ServerResponse;
import com.sise.house.comment.api.ICommentApi;
import com.sise.house.comment.api.dto.request.CommentReqDto;
import com.sise.house.comment.biz.Service.CommentService;
import com.sise.house.comment.biz.pojo.Comment;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 23:05
 */
@RestController
@RequestMapping("/comment/")
public class CommentApiImpl implements ICommentApi {

    @Resource
    private CommentService commentService;
    @Resource
    private ModelMapper modelMapper;

    @Override
    @ApiOperation(value = "新增评论", notes = "新增评论")
    public ServerResponse addComment(@RequestBody CommentReqDto commentReqDto) {
        Comment map = modelMapper.map(commentReqDto, Comment.class);
        Long aLong = commentService.addComment(map);
        return ServerResponse.createBySuccess(aLong);
    }

}
