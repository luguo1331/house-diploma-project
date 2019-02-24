package com.sise.house.comment.biz.apiImpl.query;

import com.github.pagehelper.PageInfo;
import com.sise.common.rest.ServerResponse;
import com.sise.house.comment.api.dto.response.CommentRespDto;
import com.sise.house.comment.api.query.ICommentQueryApi;
import com.sise.house.comment.biz.Service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 22:56
 */
@RestController
@RequestMapping("/comment/query")
public class CommentQueryApiImpl implements ICommentQueryApi {

    @Resource
    private CommentService commentService;
    @Resource
    private ModelMapper modelMapper;

    @Override
    @ApiOperation(value = "列表查询评论", notes = "列表查询评论")
    public ServerResponse queryCommentByPage(@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                             @RequestParam(name = "type", required = false) Byte type) {
        PageInfo<CommentRespDto> commentRespDtoPageInfo = commentService.queryCommentByPage(pageSize, pageNum, type);
        return ServerResponse.createBySuccess(commentRespDtoPageInfo);
    }
}
