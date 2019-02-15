package com.sise.house.house.api;

import com.sise.common.rest.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: 测试
 * @Auther: 冲之
 * @Date: 2019/2/15 12:50
 */
@Api(tags = {"示例中心：示例服务"})
@FeignClient(value = "test", path = "/test")
@Validated
public interface ITestApi {

    @GetMapping("/test/{$test}")
    @ApiOperation(value = "示例方法", notes = "示例方法")
    ServerResponse test(@PathVariable(name = "test") String test);
}
