package com.sise.house.user.api.fallback;

import com.sise.common.rest.ServerResponse;
import com.sise.house.user.api.IUserApi;
import com.sise.house.user.api.dto.request.UserReqDto;
import org.springframework.stereotype.Component;

/**
 * @Description: 用户服务降级类
 * @Auther: 冲之
 * @Date: 2019/2/15 18:56
 */
@Component
public class UserApiFallback implements IUserApi {

    public ServerResponse register(UserReqDto userReqDto) {
        return null;
    }

    public ServerResponse auth(UserReqDto userReqDto) {
        return null;
    }

    public ServerResponse logout(String email) {
        return null;
    }

    public ServerResponse modifyUser(UserReqDto userReqDto) {
        return null;
    }

    public ServerResponse modifyPasswordByEmail(UserReqDto userReqDto) {
        return null;
    }
}
