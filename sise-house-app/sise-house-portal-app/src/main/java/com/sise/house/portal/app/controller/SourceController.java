package com.sise.house.portal.app.controller;

import com.sise.common.rest.ServerResponse;
import com.sise.house.portal.app.service.Sourceservice;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Description: 资源服务接口
 * @Auther: 冲之
 * @Date: 2019/2/21 05:20
 */
@RestController
@RequestMapping("/source/")
public class SourceController {
    @Resource
    private Sourceservice sourceservice;

    @PostMapping(value = "/upload/photo", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "上传图片", notes = "上传图片")
    ServerResponse uploadPhoto(@RequestParam(name = "file", required = true) MultipartFile file) {
        return sourceservice.uploadPhoto(file);
    }
}
