/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月7日 下午8:14:57
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.api.SpecialPerformanceService;
import com.azz.platform.merchant.pojo.bo.AddOrRemoveModuleParam;
import com.azz.platform.merchant.pojo.bo.AddOrRemoveProductParam;
import com.azz.platform.merchant.pojo.bo.AddRecommendParam;
import com.azz.platform.merchant.pojo.bo.EditRecommendParam;
import com.azz.platform.merchant.pojo.bo.PutOnOrPutOffRecommendParam;
import com.azz.platform.merchant.pojo.bo.SearchRecommendInfoParam;
import com.azz.platform.merchant.pojo.bo.SearchRecommendProductInfoParam;
import com.azz.platform.merchant.pojo.bo.SearchRelatedModuleInfoParam;
import com.azz.platform.merchant.pojo.bo.SearchSpecialPerformanceRelatedModuleInfoParam;
import com.azz.platform.merchant.pojo.vo.ModuleInfo;
import com.azz.platform.merchant.pojo.vo.RecommendInfo;
import com.azz.platform.merchant.pojo.vo.RecommentProductInfo;
import com.azz.platform.merchant.pojo.vo.RelatedModuleInfo;
import com.azz.utils.WebUtils;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月7日 下午8:14:57
 */
@RestController
@RequestMapping("/azz/api/platform/specialPerformance")
public class SpecialPerformanceController {
	
	@Autowired
	SpecialPerformanceService specialPerformanceService;
	
	/**
	 * 
	 * <p>查询某专场的推荐活动列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 上午11:27:04
	 */
	@RequestMapping("/getRecommendInfos")
	public JsonResult<List<RecommendInfo>> getRecommendInfos(SearchRecommendInfoParam param){
		return specialPerformanceService.getRecommendInfos(param);
	}
	
	/**
	 * 
	 * <p>新增专场推荐活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 上午11:56:09
	 */
	@RequestMapping("/addRecommend")
	public JsonResult<String> addRecommend(AddRecommendParam param){
		param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return specialPerformanceService.addRecommend(param);
	}
	
	/**
	 * 
	 * <p>修改专场推荐活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 上午11:56:09
	 */
	@RequestMapping("/editRecommend")
	public JsonResult<String> editRecommend(EditRecommendParam param){
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return specialPerformanceService.editRecommend(param);
	}
	
	/**
	 * 
	 * <p>上架或下架推荐活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午1:08:46
	 */
	@RequestMapping("/putOnOrPutOffRecommendParam")
	public JsonResult<String> putOnOrPutOffRecommendParam(PutOnOrPutOffRecommendParam param){
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return specialPerformanceService.putOnOrPutOffRecommendParam(param);
	}
	
	/**
	 * 
	 * <p>查询关联模组信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:38:35
	 */
	@RequestMapping("/getRelatedModuleInfos")
	public JsonResult<Pagination<ModuleInfo>> getRelatedModuleInfos(SearchRelatedModuleInfoParam param){
		return specialPerformanceService.getRelatedModuleInfos(param);
	}
	
	/**
	 * 
	 * <p>新增或移除模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:52:04
	 */
	@RequestMapping("/addOrRemoveModule")
	public JsonResult<String> addOrRemoveModule(AddOrRemoveModuleParam param){
		param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return specialPerformanceService.addOrRemoveModule(param);
	}
	
	/**
	 * 
	 * <p>查询专场所关联的模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:52:04
	 */
	@RequestMapping("/getSpecialPerformanceRelatedModuleInfos")
	public JsonResult<Pagination<RelatedModuleInfo>> getSpecialPerformanceRelatedModuleInfos(SearchSpecialPerformanceRelatedModuleInfoParam param){
		return specialPerformanceService.getSpecialPerformanceRelatedModuleInfos(param);
	}
	
	/**
	 * 
	 * <p>查询活动中某个模组所关联的产品列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:52:04
	 */
	@RequestMapping("/getRecommentProductInfos")
	public JsonResult<Pagination<RecommentProductInfo>> getRecommentProductInfos(SearchRecommendProductInfoParam param){
		return specialPerformanceService.getRecommentProductInfos(param);
	}
	
	/**
	 * 
	 * <p>新增或移除模组中的产品</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:52:04
	 */
	@RequestMapping("/addOrRemoveProduct")
	public JsonResult<String>  addOrRemoveProduct(AddOrRemoveProductParam param){
		param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return specialPerformanceService.addOrRemoveProduct(param);
	}

}

