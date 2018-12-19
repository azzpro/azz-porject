/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月2日 下午2:55:19
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
import com.azz.merchant.api.ProductService;
import com.azz.merchant.pojo.bo.BatchAddProduct;
import com.azz.merchant.pojo.bo.MerchantProductParam;
import com.azz.merchant.pojo.bo.ModulePrams;
import com.azz.merchant.pojo.bo.ProductParams;
import com.azz.merchant.pojo.bo.Products;
import com.azz.merchant.pojo.vo.MerchantProductList;
import com.azz.merchant.pojo.vo.Module;
import com.azz.merchant.pojo.vo.ProductParamsBrands;
import com.azz.merchant.utils.WebUtils;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月2日 下午2:55:19
 */
@RestController
@RequestMapping("/azz/api/merchant/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	/**
	 * <p>产品列表</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年11月2日 下午3:02:35
	 */
	@RequestMapping(value="selectProductList",method=RequestMethod.GET)
	JsonResult<Pagination<MerchantProductList>> selectProductList(MerchantProductParam param){
		param.setMerchantId(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantId());
		return productService.selectProductList(param);
	}
	
	/**
	 * <p>去新增产品页面  加载模组列表</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年11月2日 下午3:01:02
	 */
	@RequestMapping(value="getModule",method=RequestMethod.POST)
	public JsonResult<Pagination<Module>> getModule(ModulePrams mp){
		return productService.getModule(mp);
	}
	
	/**
	 * <p>新增产品页面  加载品牌列表 参数列表</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年11月2日 下午3:01:02
	 */
	@RequestMapping(value="toAddProduct",method=RequestMethod.POST)
	public JsonResult<ProductParamsBrands> toAddProduct(){
		JsonResult<ProductParamsBrands> product = productService.toAddProduct();
		return product;
	}
	
	/**
	 * <p>新增产品页面  加载品牌列表 参数列表</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年11月2日 下午3:01:02
	 */
	@RequestMapping(value="getPrams",method=RequestMethod.POST)
	public JsonResult<com.azz.merchant.pojo.vo.ProductParams> getPrams(Long assortmentId){
		JsonResult<com.azz.merchant.pojo.vo.ProductParams> product = productService.getPrams(assortmentId);
		return product;
	}
	
	/**
	 * <p>新增产品</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年11月2日 下午3:01:02
	 */
	@RequestMapping(value="addProduct",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public JsonResult<String> addProduct(@RequestBody ProductParams param){
		param.setCreator(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
		param.setMerchantId(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantId());
		return productService.addProduct(param);
	}
	
	/**
	 * <p>去到编辑产品页面</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年11月2日 下午3:01:02
	 */
	@RequestMapping(value="toUpdateProduct",method=RequestMethod.POST)
	public JsonResult<Products> toUpdateProduct(Long id){
		return productService.toUpdateProduct(id);
	}
	
	/**
	 * <p>编辑产品</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年11月2日 下午3:01:02
	 */
	@RequestMapping(value="updateProduct",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public JsonResult<String> updateProduct(@RequestBody ProductParams param){
		param.setModify(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
		return productService.updateProduct(param);
	}
	
	/**
	 * <p>删除或下架</p>
	 * @param id
	 * @param type
	 * @return
	 * @author 刘建麟  2018年11月5日 上午11:46:55
	 */
	@RequestMapping(value="deleteOrDownProduct",method=RequestMethod.POST)
	public JsonResult<String> deleteOrDownProduct(Long id,Byte type){
		return productService.deleteOrDownProduct(id,type);
	}
	
	/**
	 * <p>批量新增产品信息</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年12月14日 下午2:02:11
	 */
	@RequestMapping(value="batchAddProduct", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    public JsonResult<String> batchAddProduct(@RequestBody BatchAddProduct param){
        param.setCreator(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
        param.setMerchantId(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantId());
        return productService.batchAddProduct(param);
    }
	
}

