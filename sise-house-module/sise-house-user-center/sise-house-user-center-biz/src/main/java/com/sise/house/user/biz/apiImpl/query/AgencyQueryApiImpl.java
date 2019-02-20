package com.sise.house.user.biz.apiImpl.query;

import com.github.pagehelper.PageInfo;
import com.sise.common.rest.ServerResponse;
import com.sise.house.user.api.dto.response.AgencyRespDto;
import com.sise.house.user.api.query.IAgencyQueryApi;
import com.sise.house.user.biz.pojo.Agency;
import com.sise.house.user.biz.service.AgencyService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 用户中心：中介机构查询接口实现类
 * @Auther: 冲之
 * @Date: 2019/2/17 18:53
 */
@RestController
@RequestMapping("/agency/query/")
public class AgencyQueryApiImpl implements IAgencyQueryApi {

    @Resource
    private AgencyService agencyService;
    @Resource
    private ModelMapper modelMapper;

    @ApiOperation(value = "分页查询中介列表", notes = "分页查询中介列表")
    public ServerResponse queryAgencyList(Integer pageSize, Integer pageNum, String keyword) {
        PageInfo<AgencyRespDto> agencyRespDtoPageInfo = agencyService.queryAgencyList(pageSize, pageNum, keyword);
        return ServerResponse.createBySuccess(agencyRespDtoPageInfo);
    }

    @ApiOperation(value = "查询中介机构信息", notes = "查询中介机构信息")
    public ServerResponse queryAgencyDetail(Long id) {
        Agency agency = agencyService.queryAgencyDetail(id);
        AgencyRespDto map = modelMapper.map(agency, AgencyRespDto.class);
        return ServerResponse.createBySuccess(map);
    }
}
