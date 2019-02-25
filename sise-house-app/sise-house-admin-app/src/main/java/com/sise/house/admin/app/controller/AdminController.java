package com.sise.house.admin.app.controller;

import com.sise.common.exception.MyException;
import com.sise.common.exception.ResultEnum;
import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.dto.request.HouseMsgReqDto;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/26 02:19
 */
@Controller
public class AdminController {
    /**
     * 后台管理中心
     *
     * @return
     */
    @GetMapping("/admin/center")
    public String adminCenterPage() {
        return "admin/center";
    }

    /**
     * 欢迎页
     *
     * @return
     */
    @GetMapping("/admin/welcome")
    public String welcomePage() {
        return "admin/welcome";
    }

    /**
     * 管理员登录页
     *
     * @return
     */
    @GetMapping("/admin/login")
    public String adminLoginPage() {
        return "admin/login";
    }

    /**
     * 房源列表页
     *
     * @return
     */
    @GetMapping("admin/house/list")
    public String houseListPage() {
        return "admin/house-list";
    }

    /**
     * 新增房源功能页
     * @return
     */
    @GetMapping("admin/add/house")
    public String addHousePage() {
        return "admin/house-add";
    }

    /**
     * 新增房源接口
     *
     * @param houseMsgReqDto
     * @return
     */
    @PostMapping("admin/add/house")
    @ResponseBody
    public ServerResponse addHouse(@Valid @ModelAttribute("form-house-add") HouseMsgReqDto houseMsgReqDto) {
        if (houseMsgReqDto.getPhotos() == null || houseMsgReqDto.getCover() == null) {
            throw new MyException(ResultEnum.PHOTO_MUST_UPLOAD);
        }
        return null;
    }
}
