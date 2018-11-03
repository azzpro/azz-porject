package com.azz.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.merchant.pojo.PlatformGoodsParams;

@Mapper
public interface PlatformGoodsParamsMapper {
	/**
	 * <p>根据分类ID 查询参数</p>
	 * @param id
	 * @return
	 * @author 刘建麟  2018年11月3日 下午1:14:40
	 */
	PlatformGoodsParams selectParamsByAssortmentId(@Param("assortmentId") Long assortmentId);
}