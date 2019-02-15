package com.sise.house.house.biz.dao;

import com.sise.house.house.biz.pojo.HouseSubscribe;
import com.sise.house.house.biz.pojo.HouseSubscribeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseSubscribeMapper {
    int countByExample(HouseSubscribeExample example);

    int deleteByExample(HouseSubscribeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HouseSubscribe record);

    int insertSelective(HouseSubscribe record);

    List<HouseSubscribe> selectByExample(HouseSubscribeExample example);

    HouseSubscribe selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HouseSubscribe record, @Param("example") HouseSubscribeExample example);

    int updateByExample(@Param("record") HouseSubscribe record, @Param("example") HouseSubscribeExample example);

    int updateByPrimaryKeySelective(HouseSubscribe record);

    int updateByPrimaryKey(HouseSubscribe record);
}