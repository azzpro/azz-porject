package com.azz.platform.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformGoodsBrand;

@Mapper
public interface PlatformGoodsBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformGoodsBrand record);

    int insertSelective(PlatformGoodsBrand record);

    PlatformGoodsBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformGoodsBrand record);

    int updateByPrimaryKeyWithBLOBs(PlatformGoodsBrand record);

    int updateByPrimaryKey(PlatformGoodsBrand record);
}