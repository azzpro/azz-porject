package com.azz.platform.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.merchant.pojo.MerchantGoodsProductParams;


@Mapper
public interface MerchantGoodsProductParamsMapper {
	
	List<MerchantGoodsProductParams> selectParamsByProductId(Long id);
	
	int updateHidden(@Param("id") Long id,@Param("status") Integer status);
}