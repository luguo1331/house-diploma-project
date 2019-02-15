package com.sise.house.house.biz.dao;

import com.sise.house.house.biz.pojo.HouseCommunity;
import com.sise.house.house.biz.pojo.HouseCommunityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseCommunityMapper {
    int countByExample(HouseCommunityExample example);

    int deleteByExample(HouseCommunityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HouseCommunity record);

    int insertSelective(HouseCommunity record);

    List<HouseCommunity> selectByExample(HouseCommunityExample example);

    HouseCommunity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HouseCommunity record, @Param("example") HouseCommunityExample example);

    int updateByExample(@Param("record") HouseCommunity record, @Param("example") HouseCommunityExample example);

    int updateByPrimaryKeySelective(HouseCommunity record);

    int updateByPrimaryKey(HouseCommunity record);
}