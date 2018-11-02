/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:07:30
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.merchant.pojo.bo.MerchantProductParam;
import com.azz.merchant.pojo.bo.ProductParams;
import com.azz.merchant.pojo.vo.MerchantProductList;
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
	public String selectProductByAssortmentId(Long id) {
		return productService.selectProductByAssortmentId(id);
	}
	
	/**
	 * <p>产品列表</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年11月2日 下午2:37:03
	 */
	@RequestMapping("selectProductList")
	JsonResult<Pagination<MerchantProductList>> selectProductList(@RequestBody MerchantProductParam param){
		return productService.selectProductList(param);
	}
	
	/**
	 * <p>新增产品</p>
	 * @param params
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:47:30
	 */
	@RequestMapping(value="addProduct",method=RequestMethod.POST)
	public JsonResult<String> addProduct(@RequestBody ProductParams params){
		return productService.addProduct(params);
	}
}

