package com.sise.house.house.biz.dao;

import com.sise.house.house.biz.pojo.HouseCollect;
import com.sise.house.house.biz.pojo.HouseCollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseCollectMapper {
    int countByExample(HouseCollectExample example);

    int deleteByExample(HouseCollectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HouseCollect record);

    int insertSelective(HouseCollect record);

    List<HouseCollect> selectByExample(HouseCollectExample example);

    HouseCollect selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HouseCollect record, @Param("example") HouseCollectExample example);

    int updateByExample(@Param("record") HouseCollect record, @Param("example") HouseCollectExample example);

    int updateByPrimaryKeySelective(HouseCollect record);

    int updateByPrimaryKey(HouseCollect record);
}