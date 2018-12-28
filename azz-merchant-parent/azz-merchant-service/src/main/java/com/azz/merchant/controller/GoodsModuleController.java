/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午5:18:06
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.azz.merchant.service.GoodsModuleService;


/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月22日 下午5:18:06
 */
@RestController
@RequestMapping("/azz/api/merchant/goodsModule")
public class GoodsModuleController {

	@Autowired
	private GoodsModuleService goodsModuleService;
	
	/**
	 * 
	 * <p>查询模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午3:33:53
	 */
	@RequestMapping("/getGoodModuleInfoList")
	public JsonResult<Pagination<GoodsModuleInfo>> getGoodModuleInfoList(@RequestBody SearchGoodsModuleParam param){
		return goodsModuleService.getGoodModuleInfoList(param);
	}
	
	/**
	 * 
	 * <p>查询模组详情</p>
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年11月1日 下午8:45:15
	 */
	@RequestMapping("/getGoodModuleInfo")
	public JsonResult<GoodsModuleInfo> getGoodModuleInfo(@RequestParam("moduleCode")String moduleCode){
		return goodsModuleService.getGoodModuleInfo(moduleCode);
	}
	
	/**
	 * 
	 * <p>新增商品模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午4:08:42
	 */
	@RequestMapping("/addGoodsModule")
	public JsonResult<String> addGoodsModule(@RequestBody AddGoodsModuleParam param){
		return goodsModuleService.addGoodsModule(param);
	}
	
	/**
	 * 
	 * <p>修改商品模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午5:16:03
	 */
	@RequestMapping("/editGoodsModule")
	public JsonResult<String> editGoodsModule(@RequestBody EditGoodsModuleParam param){
		return goodsModuleService.editGoodsModule(param);
	}
	
	/**
	 * 
	 * <p>上架、下架或删除模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午8:02:16
	 */
	@RequestMapping("/putOnOrPutOffOrDelGoodsModule")
	public JsonResult<String> putOnOrPutOffOrDelGoodsModule(@RequestBody PutOnOrPutOffOrDelGoodsModuleParam param){
		return goodsModuleService.putOnOrPutOffOrDelGoodsModule(param);
	}
	
	
	/**
	 * 
	 * <p>查询模组已导入的产品信息</p>
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年12月13日 下午2:45:42
	 */
	@RequestMapping("/getImportedProductInfos")
	public JsonResult<ImportedProductInfo> getImportedProductInfos(@RequestParam("moduleCode") String moduleCode){
		return goodsModuleService.getImportedProductInfos(moduleCode);
	}
	
	/**
	 * 
	 * <p>查询当前模组能导入的产品信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月13日 下午4:16:39
	 */
	@RequestMapping("/getProductsForImport")
	public JsonResult<Pagination<ProductForImport>> getProductsForImport(@RequestBody SearchProductForImportParam param){
		return goodsModuleService.getProductsForImport(param);
	}
	
	/**
	 * 
	 * <p>保存模组产品</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月13日 下午4:19:46
	 */
	@RequestMapping("/saveModuleProducts")
	public JsonResult<String> saveModuleProducts(@RequestBody AddModuleProductParam param){
		return goodsModuleService.saveModuleProducts(param);
	}
	
	/**
	 * 
	 * <p>批量导入模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月13日 下午4:19:46
	 */
	@RequestMapping("/batchAddModule")
	public JsonResult<String> batchAddModule(@RequestParam("merchantId") Long merchantId, @RequestParam("creator") String creator) {
		return goodsModuleService.batchAddModule(merchantId, creator);
	}
	
	/**
	 * 
	 * <p>批量导入参数</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月13日 下午4:19:46
	 */
	@RequestMapping("/batchAddParam")
	public JsonResult<String> batchAddParam(@RequestParam("creator") String creator) {
		return goodsModuleService.batchAddParam(creator);
	}
	
}

