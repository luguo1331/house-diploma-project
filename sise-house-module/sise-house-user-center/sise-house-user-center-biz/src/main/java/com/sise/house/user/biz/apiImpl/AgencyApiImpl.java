package com.sise.house.user.biz.apiImpl;

import com.sise.common.rest.ServerResponse;
import com.sise.house.user.api.IAgencyApi;
import com.sise.house.user.api.dto.request.AgencyReqDto;
import com.sise.house.user.biz.pojo.Agency;
import com.sise.house.user.biz.service.AgencyService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 用户中心：中介机构接口实现类
 * @Auther: 冲之
 * @Date: 2019/2/17 18:52
 */
@RestController
@RequestMapping("/agency/")
public class AgencyApiImpl implements IAgencyApi {
    @Resource
    private AgencyService agencyService;
    @Resource
    private ModelMapper modelMapper;

    /**
     * 新增中介机构
     *
     * @param agencyReqDto
     * @return
     */
    @ApiOperation(value = "新增中介机构", notes = "新增中介机构")
    public ServerResponse addAgency(AgencyReqDto agencyReqDto) {
        Agency map = modelMapper.map(agencyReqDto, Agency.class);
        Long agencyId = agencyService.addAgency(map);
        return ServerResponse.createBySuccess(agencyId);
    }

    /**
     * 修改中介机构
     *
     * @param agencyReqDto
     * @return
     */
    @ApiOperation(value = "修改中介机构", notes = "修改中介机构")
    public ServerResponse modifyAgency(AgencyReqDto agencyReqDto) {
        Agency map = modelMapper.map(agencyReqDto, Agency.class);
        agencyService.modifyAgency(map);
        return ServerResponse.createBySuccess();
    }
}
