package com.sise.house.user.biz.apiImpl;

import com.sise.common.rest.ServerResponse;
import com.sise.house.user.api.IUserApi;
import com.sise.house.user.api.dto.request.UserReqDto;
import com.sise.house.user.api.dto.response.UserRespDto;
import com.sise.house.user.biz.pojo.User;
import com.sise.house.user.biz.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 用户中心：用户 实现类
 * @Auther: 冲之
 * @Date: 2019/2/15 18:20
 */
@RestController
@RequestMapping("/user/")
public class UserApiImpl implements IUserApi {

    @Resource
    private UserService userService;
    @Resource
    private ModelMapper modelMapper;

    /**
     * 注册
     *
     * @param userReqDto
     * @return
     */
    @ApiOperation(value = "注册", notes = "注册")
    public ServerResponse register(@RequestBody UserReqDto userReqDto) {
        User map = modelMapper.map(userReqDto, User.class);
        Long userId = userService.addUser(map);
        return ServerResponse.createBySuccess(userId);
    }


    /**
     * 更新用户信息
     *
     * @param userReqDto
     * @return
     */
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    public ServerResponse modifyUser(@RequestBody UserReqDto userReqDto) {
        User map = modelMapper.map(userReqDto, User.class);
        userService.modifyUser(map);
        return ServerResponse.createBySuccess();
    }

    /**
     * 根据邮箱修改密码
     *
     * @param userReqDto
     * @return
     */
    @ApiOperation(value = "根据邮箱修改密码", notes = "根据邮箱修改密码")
    public ServerResponse modifyPasswordByEmail(@RequestBody UserReqDto userReqDto) {
        User map = modelMapper.map(userReqDto, User.class);
        userService.modifyPasswordByEmail(map);
        return ServerResponse.createBySuccess();

    }
}
