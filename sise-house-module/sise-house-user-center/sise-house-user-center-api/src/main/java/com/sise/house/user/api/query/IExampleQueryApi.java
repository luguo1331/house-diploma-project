package com.sise.house.user.api.query;

import com.sise.common.rest.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description: 示例中心：示例查询服务
 * @Auther: 冲之
 * @Date: 2019/1/30 16:08
 */
@Api(tags = {"示例中心：示例服务"})
@FeignClient(value = "example",path = "/example/query")
@Validated
public interface IExampleQueryApi {

    /**
     * 查询示例方法
     *
     * @param id
     * @return Long 数据保存后的ID
     */
    @PostMapping("/query/{id}")
    @ApiOperation(value = "查询示例方法", notes = "查询示例方法")
    ServerResponse<Long> queryExample(@PathVariable(name = "id") Long id);

}
