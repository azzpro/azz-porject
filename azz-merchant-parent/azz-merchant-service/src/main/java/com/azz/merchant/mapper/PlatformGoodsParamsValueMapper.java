package com.azz.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.merchant.pojo.PlatformGoodsParamsValue;


@Mapper
public interface PlatformGoodsParamsValueMapper {
	
	/**
	 * <p>根据参数项ID 查询 参数值</p>
	 * @return
	 * @author 刘建麟  2018年11月3日 下午1:22:19
	 */
	List<PlatformGoodsParamsValue> selectValueByTermId(Long id);
	
	
}