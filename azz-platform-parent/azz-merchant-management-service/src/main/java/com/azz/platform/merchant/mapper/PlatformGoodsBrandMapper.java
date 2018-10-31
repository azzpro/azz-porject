package com.azz.platform.merchant.mapper;


import com.azz.platform.merchant.pojo.PlatformGoodsBrand;

public interface PlatformGoodsBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformGoodsBrand record);

    int insertSelective(PlatformGoodsBrand record);

    PlatformGoodsBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformGoodsBrand record);

    int updateByPrimaryKeyWithBLOBs(PlatformGoodsBrand record);

    int updateByPrimaryKey(PlatformGoodsBrand record);
}