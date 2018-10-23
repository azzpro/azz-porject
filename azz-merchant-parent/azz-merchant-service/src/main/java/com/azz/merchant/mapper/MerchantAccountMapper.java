package com.azz.merchant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.merchant.pojo.MerchantAccount;

@Mapper
public interface MerchantAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantAccount record);

    int insertSelective(MerchantAccount record);

    MerchantAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantAccount record);

    int updateByPrimaryKey(MerchantAccount record);
}