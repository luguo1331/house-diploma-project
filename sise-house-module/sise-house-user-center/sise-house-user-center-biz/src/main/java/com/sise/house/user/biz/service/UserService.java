package com.sise.house.user.biz.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.common.util.IdWorker;
import com.sise.common.util.MD5Util;
import com.sise.house.user.api.dto.request.UserReqDto;
import com.sise.house.user.api.dto.response.UserRespDto;
import com.sise.house.user.biz.dao.UserMapper;
import com.sise.house.user.biz.exception.MyException;
import com.sise.house.user.biz.exception.ResultEnum;
import com.sise.house.user.biz.pojo.User;
import com.sise.house.user.biz.pojo.UserExample;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description: 用户中心：用户查询 Service层
 * @Auther: 冲之
 * @Date: 2019/2/15 18:18
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private IdWorker idWorker;
    @Value("project.salt")
    private String salt;
    @Resource
    private ModelMapper modelMapper;

    /**
     * 新增用户
     *
     * @param user
     */
    public Long addUser(User user) {
        if (!checkEmail(user)) {
            throw new MyException(ResultEnum.EXIST_EMAIL);
        }
        if (!checkName(user)) {
            throw new MyException(ResultEnum.EXIST_NICK_NAME);
        }
        user.setId(idWorker.nextId());
        user.setCreateTime(new Date());
        user.setPasswd(MD5Util.MD5String(user.getPasswd(), salt));
        userMapper.insert(user);
        return user.getId();
    }


    /**
     * 验证密码、邮箱
     *
     * @param userReqDto
     * @return
     */
    public User auth(UserReqDto userReqDto) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andPasswdEqualTo(MD5Util.MD5String(userReqDto.getPasswd(), salt))
                .andEmailEqualTo(userReqDto.getEmail())
                .andEnableEqualTo(true);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.isEmpty()) {
            throw new MyException(ResultEnum.LOGIN_ERROR);
        }
        return users.get(0);
    }

    /**
     * 检查手机是否被使用
     *
     * @param user
     * @return
     */
    public Boolean checkPhone(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneNumberEqualTo(user.getPhoneNumber());
        List<User> users = userMapper.selectByExample(userExample);
        return users.isEmpty() ? false : true;
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    public void modifyUser(User user) {
        if (userMapper.selectByPrimaryKey(user.getId()) == null) {
            throw new MyException(ResultEnum.UN_EXIST_USER);
        }
        if (userMapper.updateByPrimaryKeySelective(user) < 1) {
            throw new MyException(ResultEnum.MODIFY_USER_ERROR);
        }
    }

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @param type
     * @return
     */
    public User queryUserById(Long id, Boolean type) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdEqualTo(id)
                .andTypeEqualTo(type);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.isEmpty()) {
            throw new MyException(ResultEnum.UN_EXIST_USER);
        }
        return users.get(0);
    }

    /**
     * 分页查询用户列表
     *
     * @param pageSize
     * @param pageNum
     * @param type
     * @return
     */
    public PageInfo<UserRespDto> queryUserByList(Integer pageSize, Integer pageNum, Boolean type) {
        UserExample userExample = new UserExample();
        PageHelper.startPage(pageNum, pageSize);
        userExample.createCriteria()
                .andTypeEqualTo(type);
        List<User> users = userMapper.selectByExample(userExample);
        PageInfo result = new PageInfo(users);
        List<UserRespDto> map = modelMapper.map(users, new TypeToken<List<UserRespDto>>() {
        }.getType());
        result.setList(map);
        return result;
    }

    /**
     * 检查邮箱是否被使用
     *
     * @param user
     * @return
     */
    public Boolean checkEmail(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(user.getEmail());
        List<User> users = userMapper.selectByExample(userExample);
        return users.isEmpty() ? false : true;
    }

    /**
     * 检查昵称是否被使用
     *
     * @param user
     * @return
     */
    public Boolean checkName(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(user.getName());
        List<User> users = userMapper.selectByExample(userExample);
        return users.isEmpty() ? false : true;
    }
}
