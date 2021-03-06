/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.controller;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.MerchantConstants.IsChangeGoodsModulePic;
import com.azz.merchant.api.GoodsModuleService;
import com.azz.merchant.pojo.bo.AddGoodsModuleParam;
import com.azz.merchant.pojo.bo.AddGoodsModuleWebParam;
import com.azz.merchant.pojo.bo.AddModuleProductParam;
import com.azz.merchant.pojo.bo.EditGoodsModuleParam;
import com.azz.merchant.pojo.bo.EditGoodsModuleWebParam;
import com.azz.merchant.pojo.bo.GoodsModulePic;
import com.azz.merchant.pojo.bo.PutOnOrPutOffOrDelGoodsModuleParam;
import com.azz.merchant.pojo.bo.SearchGoodsModuleParam;
import com.azz.merchant.pojo.bo.SearchProductForImportParam;
import com.azz.merchant.pojo.vo.GoodsModuleInfo;
import com.azz.merchant.pojo.vo.ImportedProductInfo;
import com.azz.merchant.pojo.vo.ProductForImport;
import com.azz.merchant.utils.WebUtils;
import com.azz.util.Base64;
import com.azz.util.JSR303ValidateUtils;

/**
 * 
 * <P>
 * 模组控制器
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月17日 下午1:42:55
 */
@RestController
@RequestMapping("/azz/api/merchant/goodsModule")
public class GoodsModuleController {
	
	@Autowired
	GoodsModuleService goodsModuleService;

	/**
	 * 
	 * <p>查询模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午3:33:53
	 */
	@RequestMapping("/getGoodModuleInfoList")
	public JsonResult<Pagination<GoodsModuleInfo>> getGoodModuleInfoList(SearchGoodsModuleParam param){
		param.setMerchantId(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantId());
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
	public JsonResult<GoodsModuleInfo> getGoodModuleInfo(String moduleCode){
		return goodsModuleService.getGoodModuleInfo(moduleCode);
	}
	
	/**
	 * 
	 * <p>新增商品模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午4:08:42
	 * @throws IOException 
	 */
	@RequestMapping("/addGoodsModule")
	public JsonResult<String> addGoodsModule(AddGoodsModuleWebParam webParam) throws IOException{
		JSR303ValidateUtils.validate(webParam);
		AddGoodsModuleParam param = new AddGoodsModuleParam();
		BeanUtils.copyProperties(webParam, param);
		MultipartFile goodsModulePicFile = webParam.getGoodsModulePicFile();
		GoodsModulePic goodsModulePic = new GoodsModulePic(goodsModulePicFile.getOriginalFilename(),
		    		goodsModulePicFile.getSize(), Base64.encode(goodsModulePicFile.getBytes()));
		param.setGoodsModulePic(goodsModulePic);
		param.setCreator(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
		return goodsModuleService.addGoodsModule(param);
	}
	
	/**
	 * 
	 * <p>修改商品模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午5:16:03
	 * @throws IOException 
	 */
	@RequestMapping("/editGoodsModule")
	public JsonResult<String> editGoodsModule(EditGoodsModuleWebParam webParam) throws IOException{
		JSR303ValidateUtils.validate(webParam);
		EditGoodsModuleParam param = new EditGoodsModuleParam();
		BeanUtils.copyProperties(webParam, param);
		MultipartFile goodsModulePicFile = webParam.getGoodsModulePicFile();
		if (webParam.getIsChangeGoodsModulePic() == IsChangeGoodsModulePic.Y.getValue() && goodsModulePicFile != null) {
			GoodsModulePic goodsModulePic = new GoodsModulePic(goodsModulePicFile.getOriginalFilename(),
					goodsModulePicFile.getSize(), Base64.encode(goodsModulePicFile.getBytes()));
			param.setGoodsModulePic(goodsModulePic);
		}
		param.setModifier(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
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
	public JsonResult<String> putOnOrPutOffOrDelGoodsModule(PutOnOrPutOffOrDelGoodsModuleParam param){
		param.setModifier(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
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
	public JsonResult<ImportedProductInfo> getImportedProductInfos(String moduleCode){
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
	public JsonResult<Pagination<ProductForImport>> getProductsForImport(SearchProductForImportParam param){
		return goodsModuleService.getProductsForImport(param);
	}
	
	/**
	 * 
	 * <p>保存模组产品</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月13日 下午4:19:46
	 */
	@RequestMapping(value = "/saveModuleProducts" , produces="application/json;charset=UTF-8")
	public JsonResult<String> saveModuleProducts(@RequestBody AddModuleProductParam param){
		param.setModifier(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
		return goodsModuleService.saveModuleProducts(param);
	}
	
}
