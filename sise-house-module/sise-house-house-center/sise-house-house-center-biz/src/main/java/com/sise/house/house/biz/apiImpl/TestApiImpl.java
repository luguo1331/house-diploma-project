package com.sise.house.house.biz.apiImpl;

import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.ITestApi;
import com.sise.house.user.api.IExampleApi;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/15 14:08
 */
@RestController
public class TestApiImpl implements ITestApi {

    @Resource
    private IExampleApi exampleApi;

    @GetMapping("/test/{test}")
    @ApiOperation(value = "示例方法", notes = "示例方法")
    public ServerResponse test(@PathVariable(name = "test") String test) {
        String testa = exampleApi.testa(test);
        return ServerResponse.createBySuccess(testa);
    }
}
