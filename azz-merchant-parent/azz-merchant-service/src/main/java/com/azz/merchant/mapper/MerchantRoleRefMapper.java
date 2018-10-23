package com.azz.merchant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.merchant.pojo.MerchantRoleRef;

@Mapper
public interface MerchantRoleRefMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantRoleRef record);

    int insertSelective(MerchantRoleRef record);

    MerchantRoleRef selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantRoleRef record);

    int updateByPrimaryKey(MerchantRoleRef record);
}