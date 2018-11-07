/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月5日 下午6:44:48
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.pojo.bo.AddCombinationParam;
import com.azz.platform.merchant.pojo.bo.EditCombinationParam;
import com.azz.platform.merchant.pojo.bo.PutOnOrPutOffOrDelCombinationParam;
import com.azz.platform.merchant.pojo.bo.SearchCaseInfoParam;
import com.azz.platform.merchant.pojo.bo.SearchCombinationParam;
import com.azz.platform.merchant.pojo.bo.SearchGoodsModuleParam;
import com.azz.platform.merchant.pojo.bo.SearchProductInfoParam;
import com.azz.platform.merchant.pojo.vo.CaseInfo;
import com.azz.platform.merchant.pojo.vo.CombinationInfo;
import com.azz.platform.merchant.pojo.vo.GoodsModuleInfo;
import com.azz.platform.merchant.pojo.vo.ProdInfo;
import com.azz.platform.merchant.service.CombinationService;

/**
 * <P>组合推荐相关控制器</P>
 * @version 1.0
 * @author 黄智聪  2018年11月5日 下午6:44:48
 */
@RestController
@RequestMapping("/azz/api/merchant/combination")
public class CombinationController {
	
	@Autowired
	CombinationService combinationService;
	
	/**
	 * 
	 * <p>查询组合列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月5日 下午7:10:34
	 */
	@RequestMapping("/getCombinationInfoList")
	public JsonResult<Pagination<CombinationInfo>> getCombinationInfoList(@RequestBody SearchCombinationParam param){
		return combinationService.getCombinationInfoList(param);
	}
	
	/**
	 * 
	 * <p>查询组合详情</p>
	 * @param combinationCode
	 * @return
	 * @author 黄智聪  2018年11月5日 下午7:21:10
	 */
	@RequestMapping("/getCombinationInfo")
	public JsonResult<CombinationInfo> getCombinationInfo(@RequestParam("combinationCode")String combinationCode){
		return combinationService.getCombinationInfo(combinationCode);
	}
	
	/**
	 * 
	 * <p>新增推荐组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 上午10:55:57
	 */
	@RequestMapping("/addCombination")
	public JsonResult<String> addCombination(@RequestBody AddCombinationParam param) {
		return combinationService.addCombination(param);
	}
	
	/**
	 * 
	 * <p>修改推荐组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月5日 下午7:41:49
	 */
	@RequestMapping("/editCombination")
	public JsonResult<String> editCombination(@RequestBody EditCombinationParam param) {
		return combinationService.editCombination(param);
	}
	
	/**
	 * 
	 * <p>上架、下架或删除组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:01:18
	 */
	@RequestMapping("/putOnOrPutOffOrDelCombination")
	public JsonResult<String> putOnOrPutOffOrDelCombination(@RequestBody PutOnOrPutOffOrDelCombinationParam param) {
		return combinationService.putOnOrPutOffOrDelCombination(param);
	}
	
	/**
	 * 
	 * <p>查询方案列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:43:38
	 */
	@RequestMapping("/getCaseInfoList")
	public JsonResult<Pagination<CaseInfo>> getCaseInfoList(@RequestBody SearchCaseInfoParam param){
		return combinationService.getCaseInfoList(param);
	}
	
	/**
	 * 
	 * <p>查询模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:43:38
	 */
	@RequestMapping("/getModuleInfoList")
	public JsonResult<Pagination<GoodsModuleInfo>> getModuleInfoList(@RequestBody SearchGoodsModuleParam param){
		return combinationService.getModuleInfoList(param);
	}
	
	/**
	 * 
	 * <p>查询产品列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:43:38
	 */
	@RequestMapping("/getProductInfoList")
	public JsonResult<Pagination<ProdInfo>> getProductInfoList(@RequestBody SearchProductInfoParam param){
		return combinationService.getProductInfoList(param);
	}

}

