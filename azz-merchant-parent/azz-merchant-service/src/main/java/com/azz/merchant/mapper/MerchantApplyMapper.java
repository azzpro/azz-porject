package com.azz.merchant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.merchant.pojo.MerchantApply;

@Mapper
public interface MerchantApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantApply record);

    int insertSelective(MerchantApply record);

    MerchantApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantApply record);

    int updateByPrimaryKey(MerchantApply record);
}