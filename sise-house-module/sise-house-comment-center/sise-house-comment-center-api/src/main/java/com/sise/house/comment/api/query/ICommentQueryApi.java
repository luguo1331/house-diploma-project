package com.sise.house.comment.api.query;

import com.sise.common.rest.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 消息中心：评论查询服务
 * @Auther: 冲之
 * @Date: 2019/2/21 22:52
 */
@Api(tags = {"消息中心：评论服务"})
@FeignClient(value = "sise-comment", path = "/comment/query")
@Validated
public interface ICommentQueryApi {

    @GetMapping("/list")
    @ApiOperation(value = "列表查询评论", notes = "列表查询评论")
    ServerResponse queryCommentByPage(@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                      @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(name = "type", required = false) Byte type);
}
