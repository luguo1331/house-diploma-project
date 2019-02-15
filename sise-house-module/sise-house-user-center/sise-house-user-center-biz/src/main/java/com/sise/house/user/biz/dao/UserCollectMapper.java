package com.sise.house.user.biz.dao;

import com.sise.house.user.biz.pojo.UserCollect;
import com.sise.house.user.biz.pojo.UserCollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCollectMapper {
    int countByExample(UserCollectExample example);

    int deleteByExample(UserCollectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserCollect record);

    int insertSelective(UserCollect record);

    List<UserCollect> selectByExample(UserCollectExample example);

    UserCollect selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserCollect record, @Param("example") UserCollectExample example);

    int updateByExample(@Param("record") UserCollect record, @Param("example") UserCollectExample example);

    int updateByPrimaryKeySelective(UserCollect record);

    int updateByPrimaryKey(UserCollect record);
}