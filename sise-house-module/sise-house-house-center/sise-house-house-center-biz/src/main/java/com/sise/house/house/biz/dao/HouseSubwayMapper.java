package com.sise.house.house.biz.dao;

import com.sise.house.house.biz.pojo.HouseSubway;
import com.sise.house.house.biz.pojo.HouseSubwayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseSubwayMapper {
    int countByExample(HouseSubwayExample example);

    int deleteByExample(HouseSubwayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HouseSubway record);

    int insertSelective(HouseSubway record);

    List<HouseSubway> selectByExample(HouseSubwayExample example);

    HouseSubway selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HouseSubway record, @Param("example") HouseSubwayExample example);

    int updateByExample(@Param("record") HouseSubway record, @Param("example") HouseSubwayExample example);

    int updateByPrimaryKeySelective(HouseSubway record);

    int updateByPrimaryKey(HouseSubway record);
}