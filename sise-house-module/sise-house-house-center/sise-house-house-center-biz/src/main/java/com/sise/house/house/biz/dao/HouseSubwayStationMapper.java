package com.sise.house.house.biz.dao;

import com.sise.house.house.biz.pojo.HouseSubwayStation;
import com.sise.house.house.biz.pojo.HouseSubwayStationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseSubwayStationMapper {
    int countByExample(HouseSubwayStationExample example);

    int deleteByExample(HouseSubwayStationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HouseSubwayStation record);

    int insertSelective(HouseSubwayStation record);

    List<HouseSubwayStation> selectByExample(HouseSubwayStationExample example);

    HouseSubwayStation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HouseSubwayStation record, @Param("example") HouseSubwayStationExample example);

    int updateByExample(@Param("record") HouseSubwayStation record, @Param("example") HouseSubwayStationExample example);

    int updateByPrimaryKeySelective(HouseSubwayStation record);

    int updateByPrimaryKey(HouseSubwayStation record);
}