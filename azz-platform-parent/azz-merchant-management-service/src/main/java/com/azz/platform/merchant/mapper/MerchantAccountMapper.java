package com.azz.platform.merchant.mapper;

import com.azz.platform.merchant.pojo.MerchantAccount;

public interface MerchantAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantAccount record);

    int insertSelective(MerchantAccount record);

    MerchantAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantAccount record);

    int updateByPrimaryKey(MerchantAccount record);
}