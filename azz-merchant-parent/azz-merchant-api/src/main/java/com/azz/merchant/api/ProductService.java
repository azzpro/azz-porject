/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:06:38
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月1日 下午2:06:38
 */
@FeignClient("azz-merchant-service")
public interface ProductService {

	@GetMapping("/azz/api/merchant/product/selectProductByAssortmentId")
	String selectProductByAssortmentId(@RequestParam("id") Long id);
}
