package com.sise.house.house.biz.dao;

import com.sise.house.house.biz.pojo.HouseDetail;
import com.sise.house.house.biz.pojo.HouseDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseDetailMapper {
    int countByExample(HouseDetailExample example);

    int deleteByExample(HouseDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HouseDetail record);

    int insertSelective(HouseDetail record);

    List<HouseDetail> selectByExample(HouseDetailExample example);

    HouseDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HouseDetail record, @Param("example") HouseDetailExample example);

    int updateByExample(@Param("record") HouseDetail record, @Param("example") HouseDetailExample example);

    int updateByPrimaryKeySelective(HouseDetail record);

    int updateByPrimaryKey(HouseDetail record);
}