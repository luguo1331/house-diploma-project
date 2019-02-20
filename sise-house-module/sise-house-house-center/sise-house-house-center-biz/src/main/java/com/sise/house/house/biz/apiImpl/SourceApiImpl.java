package com.sise.house.house.biz.apiImpl;

import com.alibaba.fastjson.JSON;
import com.qiniu.http.Response;
import com.sise.common.rest.QiNiuPutRet;
import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.ISourceApi;
import com.sise.house.house.biz.service.QiNiuService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 房产中心：资源服务
 * @Auther: 冲之
 * @Date: 2019/2/21 02:30
 */
@RestController
@RequestMapping("/housePicture")
@Slf4j
public class SourceApiImpl implements ISourceApi {
    @Resource
    private QiNiuService qiNiuService;


    @PostMapping(value = "/upload/photo", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "上传图片", notes = "上传图片")
    @Override
    public ServerResponse uploadPhoto(@RequestParam(name = "file", required = true) MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            InputStream inputStream = file.getInputStream();
            Response response = qiNiuService.uploadFile(inputStream);
            if (response.isOK()) {
                return ServerResponse.createBySuccess(JSON.parseObject(response.bodyString(), QiNiuPutRet.class));
            } else {
                return ServerResponse.createByErrorDate(JSON.parseObject(response.bodyString(), QiNiuPutRet.class));
            }
        } catch (IOException e) {
            log.error("上传图片失败" + e.getMessage());
            e.printStackTrace();
        }
        return ServerResponse.createByError();
    }
}
