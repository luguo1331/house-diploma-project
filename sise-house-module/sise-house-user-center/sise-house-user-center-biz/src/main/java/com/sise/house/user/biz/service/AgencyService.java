package com.sise.house.user.biz.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.common.util.IdWorker;
import com.sise.house.user.api.dto.response.AgencyRespDto;
import com.sise.house.user.biz.dao.AgencyMapper;
import com.sise.common.exception.MyException;
import com.sise.common.exception.ResultEnum;
import com.sise.house.user.biz.pojo.Agency;
import com.sise.house.user.biz.pojo.AgencyExample;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 中介机构 Service 层
 * @Auther: 冲之
 * @Date: 2019/2/17 18:55
 */
@Service
@Transactional
public class AgencyService {

    @Resource
    private ModelMapper modelMapper;
    @Resource
    private AgencyMapper agencyMapper;
    @Resource
    private IdWorker idWorker;


    /**
     * 按条件分页查询中介结构列表
     *
     * @param pageSize
     * @param pageNum
     * @param keyword
     * @return
     */
    public PageInfo<AgencyRespDto> queryAgencyList(Integer pageSize, Integer pageNum, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        AgencyExample agencyExample = new AgencyExample();
        AgencyExample.Criteria criteria = agencyExample.createCriteria();
        if (!StringUtils.isBlank(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        List<Agency> agencies = agencyMapper.selectByExample(agencyExample);
        PageInfo result = new PageInfo(agencies);
        List<AgencyRespDto> map = modelMapper.map(agencies, new TypeToken<List<AgencyRespDto>>() {
        }.getType());
        result.setList(map);
        return result;
    }

    /**
     * 查询中介机构信息
     *
     * @param id
     * @return
     */
    public Agency queryAgencyDetail(Long id) {
        return agencyMapper.selectByPrimaryKey(id);
    }


    /**
     * 新增中介机构
     *
     * @param agency
     * @return
     */
    public Long addAgency(Agency agency) {
        agency.setId(idWorker.nextId());
        agencyMapper.insert(agency);
        return agency.getId();
    }

    /**
     * 更新中介信息
     *
     * @param agency
     */
    public void modifyAgency(Agency agency) {
        if (agencyMapper.selectByPrimaryKey(agency.getId()) == null) {
            throw new MyException(ResultEnum.UN_EXIST_AGENCY);
        }
        if (agencyMapper.updateByPrimaryKey(agency) < 1) {
            throw new MyException(ResultEnum.MODIFY_AGENCY_ERROR);
        }
    }
}
