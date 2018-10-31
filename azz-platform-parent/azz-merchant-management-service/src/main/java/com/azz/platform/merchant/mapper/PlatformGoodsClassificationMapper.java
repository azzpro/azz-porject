package com.azz.platform.merchant.mapper;


import com.azz.platform.merchant.pojo.PlatformGoodsClassification;

public interface PlatformGoodsClassificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformGoodsClassification record);

    int insertSelective(PlatformGoodsClassification record);

    PlatformGoodsClassification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformGoodsClassification record);

    int updateByPrimaryKey(PlatformGoodsClassification record);
}