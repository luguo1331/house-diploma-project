package com.sise.house.comment.api;

import com.sise.common.rest.ServerResponse;
import com.sise.house.comment.api.dto.request.CommentReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: 消息中心：评论服务
 * @Auther: 冲之
 * @Date: 2019/2/21 22:52
 */
@Api(tags = {"消息中心：评论服务"})
@FeignClient(value = "sise-comment", path = "/comment/")
@Validated
public interface ICommentApi {

    @PostMapping("/add")
    @ApiOperation(value = "新增评论", notes = "新增评论")
    ServerResponse addComment(@RequestBody CommentReqDto commentReqDto);
}
