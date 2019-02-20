package com.sise.house.portal.app.service;

import com.sise.common.exception.MyException;
import com.sise.common.exception.ResultEnum;
import com.sise.common.rest.ServerResponse;
import com.sise.house.comment.api.IEmailApi;
import com.sise.house.comment.api.query.IEmailQueryApi;
import com.sise.house.portal.app.Dto.RegisterDto;
import com.sise.house.portal.app.support.TokenManager;
import com.sise.house.user.api.IUserApi;
import com.sise.house.user.api.dto.request.LoginReqDto;
import com.sise.house.user.api.dto.request.UserReqDto;
import com.sise.house.user.api.dto.response.UserRespDto;
import com.sise.house.user.api.query.IUserQueryApi;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/17 15:14
 */
@Service
public class UserService {
    @Resource
    private IUserApi userApi;
    @Resource
    private IUserQueryApi userQueryApi;
    @Resource
    private IEmailQueryApi emailQueryApi;
    @Resource
    private ModelMapper modelMapper;


    /**
     * 查询用户信息
     *
     * @param id
     * @param type
     * @return
     */
    public ServerResponse queryUserById(Long id, Byte type) {
        return userQueryApi.queryUserById(id, type);
    }


    /**
     * 登陆
     *
     * @param loginReqDto
     * @return
     */
    public ServerResponse login(LoginReqDto loginReqDto) {
        ServerResponse<UserRespDto> auth = userQueryApi.auth(loginReqDto);
        if (auth.isFlag()) {
            auth.getData().setToken(TokenManager.generateToken(auth.getData().getId()).getToken());
        }
        return auth;
    }

    /**
     * 注册
     *
     * @param registerDto
     * @return
     */
    public ServerResponse register(RegisterDto registerDto) {
        ServerResponse serverResponse = emailQueryApi.queryEmailByCode(registerDto.getVerifyCode());
        if (serverResponse.getData() == null || !serverResponse.getData().equals(registerDto.getEmail())) {
            throw new MyException(ResultEnum.ERROR_VERIFY_CODE);
        }
        UserReqDto map = modelMapper.map(registerDto, UserReqDto.class);
        return userApi.register(map);
    }

    /**
     * 退出登录
     *
     * @param token
     * @return
     */
    public ServerResponse logout(String token) {
        TokenManager.logout(token);
        return ServerResponse.createBySuccess();
    }

}
