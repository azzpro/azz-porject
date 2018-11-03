package com.azz.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.merchant.pojo.MerchantGoodsProductPrice;

@Mapper
public interface MerchantGoodsProductPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantGoodsProductPrice record);

    int insertSelective(MerchantGoodsProductPrice record);

    MerchantGoodsProductPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantGoodsProductPrice record);

    int updateByPrimaryKey(MerchantGoodsProductPrice record);
    
    List<MerchantGoodsProductPrice> selectPriceByProductId(Long id);
}