package com.azz.platform.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformGoodsClassification;

@Mapper
public interface PlatformGoodsClassificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformGoodsClassification record);

    int insertSelective(PlatformGoodsClassification record);

    PlatformGoodsClassification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformGoodsClassification record);

    int updateByPrimaryKey(PlatformGoodsClassification record);
}