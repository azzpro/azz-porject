/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月5日 下午2:06:22
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.pojo.bo.PlatformProduct;
import com.azz.platform.merchant.pojo.vo.PlatfomrProductList;
import com.azz.platform.merchant.pojo.vo.ProductInfo;
import com.azz.platform.merchant.service.ProductService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月5日 下午2:06:22
 */
@RestController
@RequestMapping("/azz/api/merchant/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	/**
	 * <p>平台端 产品列表 </p>
	 * @param mp
	 * @return
	 * @author 刘建麟  2018年11月5日 下午2:04:53
	 */
	@RequestMapping(value="selectPlatformProductList",method=RequestMethod.POST)
	public JsonResult<Pagination<PlatfomrProductList>> selectPlatformProductList(@RequestBody PlatformProduct mp){
		return productService.selectPlatformProductList(mp);
	}
	
	@RequestMapping(value="downProduct",method=RequestMethod.POST)
	public JsonResult<String> downProduct(@RequestParam("id") Long id){
		return productService.downProduct(id);
	}
	
	@RequestMapping(value="productInfo",method=RequestMethod.POST)
	public JsonResult<ProductInfo> productInfo(@RequestParam("id") Long id){
		return productService.productInfo(id);
	}
}

