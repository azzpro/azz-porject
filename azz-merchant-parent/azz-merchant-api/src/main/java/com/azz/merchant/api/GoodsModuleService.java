/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:23:23
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.merchant.pojo.bo.AddGoodsModuleParam;
import com.azz.merchant.pojo.bo.AddModuleProductParam;
import com.azz.merchant.pojo.bo.EditGoodsModuleParam;
import com.azz.merchant.pojo.bo.PutOnOrPutOffOrDelGoodsModuleParam;
import com.azz.merchant.pojo.bo.SearchGoodsModuleParam;
import com.azz.merchant.pojo.bo.SearchProductForImportParam;
import com.azz.merchant.pojo.vo.GoodsModuleInfo;
import com.azz.merchant.pojo.vo.ImportedProductInfo;
import com.azz.merchant.pojo.vo.ProductForImport;

/**
 * <P>模组业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月1日 下午2:23:23
 */
@FeignClient("azz-merchant-service")
public interface GoodsModuleService {
	
	
	/**
	 * 
	 * <p>查询模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午3:33:53
	 */
	@RequestMapping("/azz/api/merchant/goodsModule/getGoodModuleInfoList")
	public JsonResult<Pagination<GoodsModuleInfo>> getGoodModuleInfoList(@RequestBody SearchGoodsModuleParam param);
	
	/**
	 * 
	 * <p>查询模组详情</p>
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年11月1日 下午8:45:15
	 */
	@RequestMapping("/azz/api/merchant/goodsModule/getGoodModuleInfo")
	public JsonResult<GoodsModuleInfo> getGoodModuleInfo(@RequestParam("moduleCode")String moduleCode);
	
	/**
	 * 
	 * <p>新增商品模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午4:08:42
	 */
	@RequestMapping("/azz/api/merchant/goodsModule/addGoodsModule")
	public JsonResult<String> addGoodsModule(@RequestBody AddGoodsModuleParam param);
	
	/**
	 * 
	 * <p>修改商品模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午5:16:03
	 */
	@RequestMapping("/azz/api/merchant/goodsModule/editGoodsModule")
	public JsonResult<String> editGoodsModule(@RequestBody EditGoodsModuleParam param);
	
	/**
	 * 
	 * <p>上架、下架或删除模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午8:02:16
	 */
	@RequestMapping("/azz/api/merchant/goodsModule/putOnOrPutOffOrDelGoodsModule")
	public JsonResult<String> putOnOrPutOffOrDelGoodsModule(@RequestBody PutOnOrPutOffOrDelGoodsModuleParam param);
	
	/**
	 * 
	 * <p>查询模组已导入的产品信息</p>
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年12月13日 下午2:45:42
	 */
	@RequestMapping("/azz/api/merchant/goodsModule/getImportedProductInfos")
	public JsonResult<ImportedProductInfo> getImportedProductInfos(@RequestParam("moduleCode") String moduleCode);
	
	/**
	 * 
	 * <p>查询当前模组能导入的产品信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月13日 下午4:16:39
	 */
	@RequestMapping("/azz/api/merchant/goodsModule/getProductsForImport")
	public JsonResult<Pagination<ProductForImport>> getProductsForImport(@RequestBody SearchProductForImportParam param);
	
	/**
	 * 
	 * <p>保存模组产品</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月13日 下午4:19:46
	 */
	@RequestMapping("/azz/api/merchant/goodsModule/saveModuleProducts")
	public JsonResult<String> saveModuleProducts(@RequestBody AddModuleProductParam param);
	
}

