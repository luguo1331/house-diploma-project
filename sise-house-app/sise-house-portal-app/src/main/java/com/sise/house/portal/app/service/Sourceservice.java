package com.sise.house.portal.app.service;

import com.sise.common.rest.ServerResponse;
import com.sise.house.house.api.ISourceApi;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: 冲之
 * @Date: 2019/2/21 05:21
 */
@Service
public class Sourceservice {
    @Resource
    private ISourceApi sourceApi;

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    public ServerResponse uploadPhoto(@RequestParam(name = "file", required = true) MultipartFile file) {
        return sourceApi.uploadPhoto(file);
    }
}
