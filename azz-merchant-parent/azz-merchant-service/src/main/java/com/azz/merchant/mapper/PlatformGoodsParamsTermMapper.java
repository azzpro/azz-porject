package com.azz.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.merchant.pojo.PlatformGoodsParamsTerm;


@Mapper
public interface PlatformGoodsParamsTermMapper {
	/**
	 * <p>根据参数ID 查询参数项</p>
	 * @param id
	 * @return
	 * @author 刘建麟  2018年11月3日 下午1:18:48
	 */
	List<PlatformGoodsParamsTerm> selectParamsByParamsId(Long id);
}