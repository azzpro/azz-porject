package com.azz.platform.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.user.pojo.Merchant;
@Mapper
public interface MerchantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    Merchant selectByPrimaryKey(Long id);
    
    Merchant selectByMerchantCode(String merchantCode);

    int updateByPrimaryKeySelective(Merchant record);

    int updateByPrimaryKey(Merchant record);
    
    int updateByMerchantCode(Merchant record);

}