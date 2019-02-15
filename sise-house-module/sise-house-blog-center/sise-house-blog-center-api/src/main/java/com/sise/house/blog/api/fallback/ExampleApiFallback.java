package com.sise.house.blog.api.fallback;

import com.sise.common.rest.ServerResponse;
import com.sise.house.blog.api.IExampleApi;
import com.sise.house.blog.api.dto.request.ExampleReqDto;
import org.springframework.stereotype.Component;

/**
 * @Description: 服务降级示例类
 * @Auther: 冲之
 * @Date: 2019/2/14 15:23
 */
@Component
public class ExampleApiFallback implements IExampleApi {

    public ServerResponse addExample(ExampleReqDto dto) {
        return ServerResponse.createByErrorDate(dto);
    }

    public ServerResponse removeExample(ExampleReqDto dto) {
        return ServerResponse.createByErrorDate(dto);

    }

    public ServerResponse modifyExample(ExampleReqDto dto) {
        return ServerResponse.createByErrorDate(dto);
    }

    public String testa(String string) {
        return "error, " + string;
    }
}
