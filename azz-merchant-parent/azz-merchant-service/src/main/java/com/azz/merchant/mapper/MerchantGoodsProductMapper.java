package com.azz.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.merchant.pojo.MerchantGoodsProduct;

@Mapper
public interface MerchantGoodsProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantGoodsProduct record);

    int insertSelective(MerchantGoodsProduct record);

    MerchantGoodsProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantGoodsProduct record);

    int updateByPrimaryKey(MerchantGoodsProduct record);
}