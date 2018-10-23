package com.azz.merchant.mapper;

import com.azz.merchant.pojo.MerchantRoleRef;

public interface MerchantRoleRefMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantRoleRef record);

    int insertSelective(MerchantRoleRef record);

    MerchantRoleRef selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantRoleRef record);

    int updateByPrimaryKey(MerchantRoleRef record);
}