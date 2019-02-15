package com.sise.house.house.biz.dao;

import com.sise.house.house.biz.pojo.HouseMsg;
import com.sise.house.house.biz.pojo.HouseMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseMsgMapper {
    int countByExample(HouseMsgExample example);

    int deleteByExample(HouseMsgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HouseMsg record);

    int insertSelective(HouseMsg record);

    List<HouseMsg> selectByExample(HouseMsgExample example);

    HouseMsg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HouseMsg record, @Param("example") HouseMsgExample example);

    int updateByExample(@Param("record") HouseMsg record, @Param("example") HouseMsgExample example);

    int updateByPrimaryKeySelective(HouseMsg record);

    int updateByPrimaryKey(HouseMsg record);
}