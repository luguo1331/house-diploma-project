package com.sise.house.blog.api;


import com.sise.common.rest.ServerResponse;
import com.sise.house.blog.api.dto.request.ExampleReqDto;
import com.sise.house.blog.api.fallback.ExampleApiFallback;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: 示例中心：示例服务
 * @Auther: 冲之
 * @Date: 2019/1/30 16:08
 */
@Api(tags = {"示例中心：示例服务"})
@FeignClient(value = "example", path = "/example", fallback = ExampleApiFallback.class)
@Validated
public interface IExampleApi {

    /**
     * 新增示例方法
     *
     * @param dto 新增实体
     * @return Long 数据保存后的ID
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增示例方法", notes = "新增示例方法")
    ServerResponse<Long> addExample(@RequestBody ExampleReqDto dto);

    /**
     * 删除示例方法
     *
     * @param dto
     * @return Boolean 是否删除成功
     */
    @PostMapping("/remove/")
    @ApiOperation(value = "删除示例方法", notes = "删除示例方法")
    ServerResponse<Boolean> removeExample(@RequestBody ExampleReqDto dto);

    /**
     * 修改示例方法
     *
     * @param dto
     * @return Boolean 是否修改成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "修改示例方法", notes = "修改示例方法")
    ServerResponse<Boolean> modifyExample(@RequestBody ExampleReqDto dto);

    @PostMapping("/testa")
    @ApiOperation(value = "修改示例方法", notes = "修改示例方法")
    String testa(@RequestBody String string);
}
