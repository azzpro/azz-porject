/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月14日 上午11:37:47
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.order.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.azz.order.api.OrderService;
import com.azz.order.pojo.Order;
import com.azz.platform.goods.pojo.Demo;
import com.azz.platform.order.mapper.OrderMapper;
import com.azz.platform.order.service.feign.GoodsServiceClient;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月14日 上午11:37:47
 */
@RestController
public class OrderServiceImpl implements OrderService{

	@Autowired
	private GoodsServiceClient gsc;
	
	@Autowired
	private OrderMapper om;
	
	@Override
	public List<Order> getName() {
		List<Demo> name = gsc.getName();
		System.out.println("Goods Service 调用 Order Service...."+name.size());
		return om.getName();
	}

	@Override
	public List<Order> getD() {
		return om.getD();
	}

}

