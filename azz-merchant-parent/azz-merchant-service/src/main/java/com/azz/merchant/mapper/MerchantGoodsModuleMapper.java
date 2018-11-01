package com.azz.merchant.mapper;

import com.azz.merchant.pojo.MerchantGoodsModule;

public interface MerchantGoodsModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantGoodsModule record);

    int insertSelective(MerchantGoodsModule record);

    MerchantGoodsModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantGoodsModule record);

    int updateByPrimaryKeyWithBLOBs(MerchantGoodsModule record);

    int updateByPrimaryKey(MerchantGoodsModule record);
}