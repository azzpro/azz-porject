/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月5日 下午2:41:56
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.pojo.bo.PlatformProduct;
import com.azz.platform.merchant.pojo.vo.PlatfomrProductList;
import com.azz.platform.merchant.pojo.vo.ProductInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月5日 下午2:41:56
 */
@FeignClient("azz-merchant-management-service")
public interface ProductService {

	@RequestMapping(value="/azz/api/merchant/product/selectPlatformProductList",method=RequestMethod.POST)
	public JsonResult<Pagination<PlatfomrProductList>> selectPlatformProductList(@RequestBody PlatformProduct mp);

	@RequestMapping(value="/azz/api/merchant/product/downProduct",method=RequestMethod.POST)
	public JsonResult<String> downProduct(@RequestParam("id") Long id);
		
	@RequestMapping(value="/azz/api/merchant/product/productInfo",method=RequestMethod.POST)
	public JsonResult<ProductInfo> productInfo(@RequestParam("id") Long id);
}

