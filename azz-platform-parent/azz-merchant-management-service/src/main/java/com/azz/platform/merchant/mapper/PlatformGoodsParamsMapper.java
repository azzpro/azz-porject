package com.azz.platform.merchant.mapper;


import com.azz.platform.merchant.pojo.PlatformGoodsParams;

public interface PlatformGoodsParamsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformGoodsParams record);

    int insertSelective(PlatformGoodsParams record);

    PlatformGoodsParams selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformGoodsParams record);

    int updateByPrimaryKey(PlatformGoodsParams record);
}