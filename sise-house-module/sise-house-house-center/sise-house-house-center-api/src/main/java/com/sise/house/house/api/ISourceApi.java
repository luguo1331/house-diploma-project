package com.sise.house.house.api;

import com.sise.common.rest.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 房产中心：资源服务
 * @Auther: 冲之
 * @Date: 2019/2/21 02:27
 */
@Api(tags = {"房产中心：资源服务"})
@FeignClient(value = "sise-house", path = "/housePicture/")
@Validated
public interface ISourceApi {

    @PostMapping(value = "/upload/photo", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "上传图片", notes = "上传图片")
    ServerResponse uploadPhoto(@RequestParam(name = "file", required = true) MultipartFile file);

}
