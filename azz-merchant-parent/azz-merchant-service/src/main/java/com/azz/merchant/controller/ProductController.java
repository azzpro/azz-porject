/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:07:30
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.merchant.service.ProductService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月1日 下午2:07:30
 */
@RestController
@RequestMapping("/azz/api/merchant/product")
public class ProductController {

	
	@Autowired
	private ProductService productService;
	
	/**
	 * <p>根据分类ID 查询产品</p>
	 * @param id
	 * @return
	 * @author 刘建麟  2018年11月1日 下午2:09:43
	 */
	@RequestMapping("selectProductByAssortmentId")
	public String selectProductByAssortmentId(String id) {
		return productService.selectProductByAssortmentId(id);
	}
}

