package com.sise.house.user.biz.apiImpl.query;

import com.github.pagehelper.PageInfo;
import com.sise.common.rest.ServerResponse;
import com.sise.house.user.api.dto.request.LoginReqDto;
import com.sise.house.user.api.dto.request.UserReqDto;
import com.sise.house.user.api.dto.response.UserRespDto;
import com.sise.house.user.api.query.IUserQueryApi;
import com.sise.house.user.biz.pojo.User;
import com.sise.house.user.biz.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户中心：用户查询 实现类
 * @Auther: 冲之
 * @Date: 2019/2/15 18:15
 */
@RestController
@RequestMapping("/user/query")
public class UserQueryApiImpl implements IUserQueryApi {

    @Resource
    private UserService userService;
    @Resource
    private ModelMapper modelMapper;


    /**
     * 查询用户信息
     *
     * @param id
     * @param type
     * @return
     */
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    public ServerResponse<UserRespDto> queryUserById(Long id, Byte type) {
        User user = userService.queryUserById(id, type);
        UserRespDto map = modelMapper.map(user, UserRespDto.class);
        return ServerResponse.createBySuccess(map);
    }

    /**
     * 验证密码、邮箱
     *
     * @param loginReqDto
     * @return
     */
    @ApiOperation(value = "验证密码、邮箱", notes = "验证密码、邮箱")
    public ServerResponse<UserRespDto> auth(@RequestBody LoginReqDto loginReqDto) {
        User auth = userService.auth(loginReqDto);
        UserRespDto map = modelMapper.map(auth, UserRespDto.class);
        return ServerResponse.createBySuccess(map);
    }

    /**
     * 分页查询用户列表
     *
     * @param pageSize
     * @param pageNum
     * @param type
     * @return
     */
    @ApiOperation(value = "分页查询用户列表", notes = "分页查询用户列表")
    public ServerResponse<PageInfo<UserRespDto>> queryUserByList(Integer pageSize, Integer pageNum, Long agencyId, Byte type) {
        return ServerResponse.createBySuccess(userService.queryUserByList(pageSize, pageNum, type, agencyId));
    }
}
