/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月14日 上午11:33:00
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.pojo.Order;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月14日 上午11:33:00
 */
@Mapper
public interface OrderMapper {

	List<Order> getName();
	
	List<Order> getD();
}

