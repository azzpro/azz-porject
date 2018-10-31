package com.azz.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.merchant.pojo.MerchantGoodsModule;

@Mapper
public interface MerchantGoodsModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantGoodsModule record);

    int insertSelective(MerchantGoodsModule record);

    MerchantGoodsModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantGoodsModule record);

    int updateByPrimaryKeyWithBLOBs(MerchantGoodsModule record);

    int updateByPrimaryKey(MerchantGoodsModule record);
}