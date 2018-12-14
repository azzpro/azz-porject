/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:06:38
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.merchant.pojo.bo.BatchAddProduct;
import com.azz.merchant.pojo.bo.MerchantProductParam;
import com.azz.merchant.pojo.bo.ModulePrams;
import com.azz.merchant.pojo.bo.ProductParams;
import com.azz.merchant.pojo.bo.Products;
import com.azz.merchant.pojo.vo.MerchantProductList;
import com.azz.merchant.pojo.vo.Module;
import com.azz.merchant.pojo.vo.ProductParamsBrands;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月1日 下午2:06:38
 */
@FeignClient("azz-merchant-service")
public interface ProductService {

	@GetMapping("/azz/api/merchant/product/selectProductByAssortmentId")
	String selectProductByAssortmentId(@RequestParam("id") Long id);
	
	@GetMapping("/azz/api/merchant/product/selectProductList")
	JsonResult<Pagination<MerchantProductList>> selectProductList(@RequestBody MerchantProductParam param);
	
	@RequestMapping(value="/azz/api/merchant/product/addProduct",method=RequestMethod.POST)
	public JsonResult<String> addProduct(@RequestBody ProductParams params);
	
	@RequestMapping(value="/azz/api/merchant/product/updateProduct",method=RequestMethod.POST)
	public JsonResult<String> updateProduct(@RequestBody ProductParams params);
	
	@RequestMapping(value="/azz/api/merchant/product/deleteOrDownProduct",method=RequestMethod.POST)
	public JsonResult<String> deleteOrDownProduct(@RequestParam("id") Long id,@RequestParam("type") Byte type);
	
	@RequestMapping(value="/azz/api/merchant/product/toUpdateProduct",method=RequestMethod.POST)
	public JsonResult<Products> toUpdateProduct(@RequestParam("id") Long id);
	
	@RequestMapping(value="/azz/api/merchant/product/toAddProduct",method=RequestMethod.POST)
	public JsonResult<ProductParamsBrands> toAddProduct();
	
	@RequestMapping(value="/azz/api/merchant/product/getModule",method=RequestMethod.POST)
	public JsonResult<Pagination<Module>> getModule(@RequestBody ModulePrams mp);
	
	@RequestMapping(value="/azz/api/merchant/product/getPrams",method=RequestMethod.POST)
	public JsonResult<com.azz.merchant.pojo.vo.ProductParams> getPrams(@RequestParam("assortmentId") Long assortmentId);

	@RequestMapping(value="/azz/api/merchant/product/batchAddProduct",method=RequestMethod.POST)
	public JsonResult<String> batchAddProduct(@RequestBody BatchAddProduct params);
}

