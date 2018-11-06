package com.azz.platform.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.MerchantGoodsProductPrice;


@Mapper
public interface MerchantGoodsProductPriceMapper {
	
	List<MerchantGoodsProductPrice> selectPriceByProductId(Long id);
}