package com.sise.house.user.biz.apiImpl;

import com.sise.common.rest.ServerResponse;
import com.sise.house.user.api.IExampleApi;
import com.sise.house.user.api.dto.request.ExampleReqDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/14 23:14
 */
@RestController
@RequestMapping("/example")
public class ExampleApiImpl implements IExampleApi {
    @ApiOperation(value = "新增示例方法", notes = "新增示例方法")
    public ServerResponse<Long> addExample(@RequestBody ExampleReqDto dto) {
        return null;
    }

    @ApiOperation(value = "删除示例方法", notes = "删除示例方法")
    public ServerResponse<Boolean> removeExample(@RequestBody ExampleReqDto dto) {
        return ServerResponse.createBySuccess(true);
    }

    @ApiOperation(value = "修改示例方法", notes = "修改示例方法")
    public ServerResponse<Boolean> modifyExample(@RequestBody ExampleReqDto dto) {
        return null;
    }

    @ApiOperation(value = "修改示例方法", notes = "修改示例方法")
    public String testa(String string) {
        return "返回给你的" + string;
    }

    @ApiOperation(value = "新增示例方法", notes = "新增示例方法")
    public ServerResponse<String> test(@PathVariable(name = "test") String test) {
        return ServerResponse.createBySuccess("调用成功了");
    }

}
