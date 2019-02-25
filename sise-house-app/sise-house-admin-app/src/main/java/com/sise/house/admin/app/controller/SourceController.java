package com.sise.house.admin.app.controller;

import com.alibaba.fastjson.JSON;
import com.qiniu.http.Response;
import com.sise.common.rest.QiNiuPutRet;
import com.sise.common.rest.ServerResponse;
import com.sise.house.admin.app.service.QiNiuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/26 02:55
 */
@Controller
@Slf4j
public class SourceController {

    @Resource()
    private QiNiuService qiNiuService;

    /**
     * 上传图片接口
     *
     * @param file
     * @return
     */
    @PostMapping(value = "admin/upload/photo", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ServerResponse uploadPhoto(@RequestParam("file") MultipartFile file) {
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
