package com.sise.house.house.biz.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.common.exception.MyException;
import com.sise.common.exception.ResultEnum;
import com.sise.common.util.IdWorker;
import com.sise.house.house.api.constants.HouseSubscribeStatus;
import com.sise.house.house.api.dto.request.SureSubscribeReqDto;
import com.sise.house.house.api.dto.response.HouseSubscribeRespDto;
import com.sise.house.house.biz.dao.HouseSubscribeMapper;
import com.sise.house.house.biz.pojo.HouseMsg;
import com.sise.house.house.biz.pojo.HouseSubscribe;
import com.sise.house.house.biz.pojo.HouseSubscribeExample;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 房屋预约Service
 * @Auther: 冲之
 * @Date: 2019/2/20 01:00
 */
@Service
@Transactional
public class HouseSubscribeService {
    @Resource
    private ModelMapper modelMapper;
    @Resource
    private HouseSubscribeMapper subscribeMapper;
    @Resource
    private HouseService houseService;
    @Resource
    private IdWorker idWorker;

    /**
     * 新增预约清单
     *
     * @param houseId
     * @param userId
     */
    public void addSubscribeOrder(Long houseId, Long userId) {
        HouseMsg houseMsg = houseService.findhouseById(houseId);
        if (houseMsg == null) {
            throw new MyException(ResultEnum.UN_EXIST_HOUSE);
        }
        HouseSubscribe bySubscribe = findBySubscribe(houseId, userId);
        if (bySubscribe != null) {
            throw new MyException(ResultEnum.EXIST_SUBSCRIBE);
        }
        HouseSubscribe houseSubscribe = new HouseSubscribe();
        Date now = new Date();
        houseSubscribe.setId(idWorker.nextId());
        houseSubscribe.setCreateTime(now);
        houseSubscribe.setLastUpdateTime(now);
        houseSubscribe.setUserId(userId);
        houseSubscribe.setHouseId(houseId);
        houseSubscribe.setStatus(HouseSubscribeStatus.IN_ORDER_LIST.getValue());
        houseSubscribe.setAdminId(houseMsg.getUserId());
        subscribeMapper.insert(houseSubscribe);
    }

    /***
     * 查看用户的预约清单列表
     * @param pageSize
     * @param pageNum
     * @param status
     * @param userId
     * @return
     */
    public PageInfo<HouseSubscribeRespDto> querySubscribeByPage(Integer pageSize, Integer pageNum, Integer status, Long userId) {
        PageHelper.startPage(pageNum, pageSize);
        HouseSubscribeExample houseSubscribeExample = new HouseSubscribeExample();
        HouseSubscribeExample.Criteria criteria = houseSubscribeExample.createCriteria();
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        List<HouseSubscribe> houseSubscribes = subscribeMapper.selectByExample(houseSubscribeExample);
        PageInfo pageInfo = new PageInfo(houseSubscribes);
        List<HouseSubscribeRespDto> list = new ArrayList<>();
        List<HouseSubscribeRespDto> map = modelMapper.map(houseSubscribes, new TypeToken<List<HouseSubscribeRespDto>>() {
        }.getType());
        pageInfo.setList(map);
        return pageInfo;
    }

    /**
     * 查询用户被预约列表
     *
     * @param pageSize
     * @param pageNum
     * @param status
     * @param adminId
     * @return
     */
    public PageInfo<HouseSubscribeRespDto> queryBeSubscribe(Integer pageSize, Integer pageNum, Integer status, Long adminId) {
        PageHelper.startPage(pageNum, pageSize);
        HouseSubscribeExample houseSubscribeExample = new HouseSubscribeExample();
        HouseSubscribeExample.Criteria criteria = houseSubscribeExample.createCriteria();
        if (adminId != null) {
            criteria.andAdminIdEqualTo(adminId);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        List<HouseSubscribe> houseSubscribes = subscribeMapper.selectByExample(houseSubscribeExample);
        PageInfo pageInfo = new PageInfo(houseSubscribes);
        List<HouseSubscribeRespDto> list = new ArrayList<>();
        List<HouseSubscribeRespDto> map = modelMapper.map(houseSubscribes, new TypeToken<List<HouseSubscribeRespDto>>() {
        }.getType());
        pageInfo.setList(map);
        return pageInfo;
    }

    /**
     * 确定预约时间
     *
     * @param sureSubscribeReqDto
     */
    public void subscribe(SureSubscribeReqDto sureSubscribeReqDto) {
        HouseSubscribe bySubscribe = findBySubscribe(sureSubscribeReqDto.getHouseId(), sureSubscribeReqDto.getUserId());
        if (bySubscribe == null) {
            throw new MyException(ResultEnum.UN_EXIST_SUBSCRIBE);
        }
        if (bySubscribe.getStatus() != HouseSubscribeStatus.IN_ORDER_TIME.getValue()) {
            throw new MyException(ResultEnum.ERROR_SUBSCRIBE);
        }
        bySubscribe.setStatus(HouseSubscribeStatus.IN_ORDER_TIME.getValue());
        bySubscribe.setLastUpdateTime(new Date());
        bySubscribe.setTelephone(sureSubscribeReqDto.getTelephone());
        bySubscribe.setDesc(sureSubscribeReqDto.getDesc());
        bySubscribe.setOrderTime(sureSubscribeReqDto.getOrderTime());
        subscribeMapper.updateByPrimaryKeySelective(bySubscribe);
    }

    /**
     * 修改预约状态
     *
     * @param houseId
     * @param userId
     */
    public void cancelSubscribe(Long houseId, Long userId, Integer status) {
        HouseSubscribe bySubscribe = findBySubscribe(houseId, userId);
        if (bySubscribe == null) {
            throw new MyException(ResultEnum.UN_EXIST_SUBSCRIBE);
        }
//        if (bySubscribe.getStatus() != HouseSubscribeStatus.IN_ORDER_LIST.getValue()) {
//            throw new MyException(ResultEnum.ERROR_SUBSCRIBE);
//        }
        bySubscribe.setStatus(status);
        subscribeMapper.updateByPrimaryKeySelective(bySubscribe);
    }

    /**
     * 查看用户是否预约房子
     *
     * @param houseId
     * @param userId
     * @return
     */
    public HouseSubscribe findBySubscribe(Long houseId, Long userId) {
        HouseSubscribe result = null;
        HouseSubscribeExample houseSubscribeExample = new HouseSubscribeExample();
        houseSubscribeExample.createCriteria()
                .andHouseIdEqualTo(houseId)
                .andUserIdEqualTo(userId);
        List<HouseSubscribe> houseSubscribes = subscribeMapper.selectByExample(houseSubscribeExample);
        if (houseSubscribes.isEmpty()) {
            return result;
        }
        return houseSubscribes.get(0);
    }
}
