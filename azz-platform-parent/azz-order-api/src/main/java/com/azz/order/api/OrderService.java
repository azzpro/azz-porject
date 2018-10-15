/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月14日 上午11:38:20
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.azz.order.pojo.Order;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月14日 上午11:38:20
 */
@FeignClient("azz-order-service")
public interface OrderService {
	
	@GetMapping("getOrder")
	List<Order> getName();
	
	@GetMapping("getD")
	List<Order> getD();
}

