package com.azz.platform.user.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.user.pojo.PlatformMerchant;

@Mapper
public interface PlatformMerchantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformMerchant record);

    int insertSelective(PlatformMerchant record);

    PlatformMerchant selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformMerchant record);

    int updateByPrimaryKey(PlatformMerchant record);
    
    PlatformMerchant selectMerchantByCode(String merchantCode);
}