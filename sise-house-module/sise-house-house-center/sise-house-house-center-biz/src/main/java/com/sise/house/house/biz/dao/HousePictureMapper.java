package com.sise.house.house.biz.dao;

import com.sise.house.house.biz.pojo.HousePicture;
import com.sise.house.house.biz.pojo.HousePictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HousePictureMapper {
    int countByExample(HousePictureExample example);

    int deleteByExample(HousePictureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HousePicture record);

    int insertSelective(HousePicture record);

    List<HousePicture> selectByExample(HousePictureExample example);

    HousePicture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HousePicture record, @Param("example") HousePictureExample example);

    int updateByExample(@Param("record") HousePicture record, @Param("example") HousePictureExample example);

    int updateByPrimaryKeySelective(HousePicture record);

    int updateByPrimaryKey(HousePicture record);
}