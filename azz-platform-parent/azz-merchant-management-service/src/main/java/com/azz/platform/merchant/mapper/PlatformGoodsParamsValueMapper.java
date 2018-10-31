package com.azz.platform.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformGoodsParamsValue;

@Mapper
public interface PlatformGoodsParamsValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformGoodsParamsValue record);

    int insertSelective(PlatformGoodsParamsValue record);

    PlatformGoodsParamsValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformGoodsParamsValue record);

    int updateByPrimaryKey(PlatformGoodsParamsValue record);
}