/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月5日 下午2:32:25
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.api.ProductService;
import com.azz.platform.merchant.pojo.bo.PlatformProduct;
import com.azz.platform.merchant.pojo.vo.PlatfomrProductList;
import com.azz.platform.merchant.pojo.vo.ProductInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月5日 下午2:32:25
 */
@RestController
@RequestMapping("/azz/api/merchant/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * <p>产品列表</p>
	 * @param mp
	 * @return
	 * @author 刘建麟  2018年11月5日 下午2:43:48
	 */
	@RequestMapping(value="selectPlatformProductList",method=RequestMethod.POST)
	public JsonResult<Pagination<PlatfomrProductList>> selectPlatformProductList( PlatformProduct mp){
		return productService.selectPlatformProductList(mp);
	}
	
	/**
	 * <p>产品下架</p>
	 * @param id
	 * @return
	 * @author 刘建麟  2018年11月5日 下午3:36:34
	 */
	@RequestMapping(value="downProduct",method=RequestMethod.POST)
	public JsonResult<String> downProduct(@RequestParam("id") Long id){
		return productService.downProduct(id);
	}
	
	/**
	 * <p>产品详情</p>
	 * @param id
	 * @return
	 * @author 刘建麟  2018年11月5日 下午3:36:34
	 */
	@RequestMapping(value="productInfo",method=RequestMethod.POST)
	public JsonResult<ProductInfo> productInfo(@RequestParam("id") Long id){
		return productService.productInfo(id);
	}
}

