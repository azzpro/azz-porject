package com.azz.order.client.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.client.pojo.MerchantYeeBind;

@Mapper
public interface MerchantYeeBindMapper {

	int updateBind(String yee_merchant_no);
	
	int insertBind(MerchantYeeBind myb);
	
	List<MerchantYeeBind> selectBindByYeeNo(String no);
	
	MerchantYeeBind selectBindByMerchantNo(String no);
}
