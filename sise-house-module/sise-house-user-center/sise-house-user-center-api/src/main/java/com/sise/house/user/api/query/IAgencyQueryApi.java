package com.sise.house.user.api.query;

import com.sise.common.rest.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 用户中心：中介机构查询接口
 * @Auther: 冲之
 * @Date: 2019/2/15 18:59
 */
@Api(tags = {"用户中心：中介机构查询接口"})
@FeignClient(value = "sise-user", path = "/agency/query")
@Validated
public interface IAgencyQueryApi {

    @GetMapping("/list")
    @ApiOperation(value = "分页查询中介列表", notes = "分页查询中介列表")
    ServerResponse agencyList(@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum);

    @GetMapping("agencyDetail/{id}")
    @ApiOperation(value = "查询中介机构信息", notes = "查询中介机构信息")
    ServerResponse agencyDetail(@PathVariable(name = "id") Long id);
}
