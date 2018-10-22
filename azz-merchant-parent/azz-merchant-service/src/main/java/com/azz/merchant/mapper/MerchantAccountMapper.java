package com.azz.merchant.mapper;

import com.azz.merchant.pojo.MerchantAccount;

public interface MerchantAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantAccount record);

    int insertSelective(MerchantAccount record);

    MerchantAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantAccount record);

    int updateByPrimaryKey(MerchantAccount record);
}