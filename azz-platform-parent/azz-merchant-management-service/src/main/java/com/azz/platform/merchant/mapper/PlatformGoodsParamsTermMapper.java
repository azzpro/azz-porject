package com.azz.platform.merchant.mapper;


import com.azz.platform.merchant.pojo.PlatformGoodsParamsTerm;

public interface PlatformGoodsParamsTermMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformGoodsParamsTerm record);

    int insertSelective(PlatformGoodsParamsTerm record);

    PlatformGoodsParamsTerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformGoodsParamsTerm record);

    int updateByPrimaryKey(PlatformGoodsParamsTerm record);
}