package com.sise.house.house.biz.dao;

import com.sise.house.house.biz.pojo.HouseSupportAddress;
import com.sise.house.house.biz.pojo.HouseSupportAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseSupportAddressMapper {
    int countByExample(HouseSupportAddressExample example);

    int deleteByExample(HouseSupportAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HouseSupportAddress record);

    int insertSelective(HouseSupportAddress record);

    List<HouseSupportAddress> selectByExample(HouseSupportAddressExample example);

    HouseSupportAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HouseSupportAddress record, @Param("example") HouseSupportAddressExample example);

    int updateByExample(@Param("record") HouseSupportAddress record, @Param("example") HouseSupportAddressExample example);

    int updateByPrimaryKeySelective(HouseSupportAddress record);

    int updateByPrimaryKey(HouseSupportAddress record);
}