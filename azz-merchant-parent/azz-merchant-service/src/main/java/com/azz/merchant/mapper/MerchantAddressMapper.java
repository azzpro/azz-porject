package com.azz.merchant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.merchant.pojo.MerchantAddress;

@Mapper
public interface MerchantAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantAddress record);

    int insertSelective(MerchantAddress record);

    MerchantAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantAddress record);

    int updateByPrimaryKey(MerchantAddress record);
}