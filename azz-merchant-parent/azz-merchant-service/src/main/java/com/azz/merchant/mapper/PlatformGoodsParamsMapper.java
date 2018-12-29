package com.azz.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.merchant.pojo.PlatformGoodsParams;
import com.azz.merchant.pojo.PlatformGoodsParamsTerm;
import com.azz.platform.merchant.pojo.PlatformGoodsParamsValue;

@Mapper
public interface PlatformGoodsParamsMapper {
	/**
	 * <p>根据分类ID 查询参数</p>
	 * @param id
	 * @return
	 * @author 刘建麟  2018年11月3日 下午1:14:40
	 */
	PlatformGoodsParams selectParamsByAssortmentId(@Param("assortmentId") Long assortmentId);
	
	int insertSelective(PlatformGoodsParams record);
	
	int insertTermSelective(PlatformGoodsParamsTerm record);
	
	int insertTermValueSelective(PlatformGoodsParamsValue record);
	
	int countParamsValue(@Param("paramsValue")String paramsValue, @Param("paramsCode")String paramsCode);
	
	Long selectParamTermId(String paramsCode);
	
	PlatformGoodsParamsTerm selectParamTerm(String paramsCode);
}