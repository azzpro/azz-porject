/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月7日 下午8:10:44
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
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

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月7日 下午8:10:44
 */
@FeignClient("azz-merchant-management-service")
public interface SpecialPerformanceService {
	
	/**
	 * 
	 * <p>查询某专场的推荐活动列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 上午11:27:04
	 */
	@RequestMapping("/azz/api/platform/specialPerformance/getRecommendInfos")
	public JsonResult<List<RecommendInfo>> getRecommendInfos(@RequestBody SearchRecommendInfoParam param);
	
	/**
	 * 
	 * <p>新增专场推荐活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 上午11:56:09
	 */
	@RequestMapping("/azz/api/platform/specialPerformance/addRecommend")
	public JsonResult<String> addRecommend(@RequestBody AddRecommendParam param);
	
	/**
	 * 
	 * <p>修改专场推荐活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 上午11:56:09
	 */
	@RequestMapping("/azz/api/platform/specialPerformance/editRecommend")
	public JsonResult<String> editRecommend(@RequestBody EditRecommendParam param);
	
	/**
	 * 
	 * <p>上架或下架推荐活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午1:08:46
	 */
	@RequestMapping("/azz/api/platform/specialPerformance/putOnOrPutOffRecommendParam")
	public JsonResult<String> putOnOrPutOffRecommendParam(@RequestBody PutOnOrPutOffRecommendParam param);
	
	/**
	 * 
	 * <p>查询关联模组信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:38:35
	 */
	@RequestMapping("/azz/api/platform/specialPerformance/getRelatedModuleInfos")
	public JsonResult<Pagination<ModuleInfo>> getRelatedModuleInfos(@RequestBody SearchRelatedModuleInfoParam param);
	
	/**
	 * 
	 * <p>新增或移除模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:52:04
	 */
	@RequestMapping("/azz/api/platform/specialPerformance/addOrRemoveModule")
	public JsonResult<String> addOrRemoveModule(@RequestBody AddOrRemoveModuleParam param);
	
	/**
	 * 
	 * <p>查询专场所关联的模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:52:04
	 */
	@RequestMapping("/azz/api/platform/specialPerformance/getSpecialPerformanceRelatedModuleInfos")
	public JsonResult<Pagination<RelatedModuleInfo>> getSpecialPerformanceRelatedModuleInfos(@RequestBody SearchSpecialPerformanceRelatedModuleInfoParam param);
	
	/**
	 * 
	 * <p>查询活动中某个模组所关联的产品列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:52:04
	 */
	@RequestMapping("/azz/api/platform/specialPerformance/getRecommentProductInfos")
	public JsonResult<Pagination<RecommentProductInfo>> getRecommentProductInfos(@RequestBody SearchRecommendProductInfoParam param);
	
	/**
	 * 
	 * <p>新增或移除模组中的产品</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:52:04
	 */
	@RequestMapping("/azz/api/platform/specialPerformance/addOrRemoveProduct")
	public JsonResult<String>  addOrRemoveProduct(@RequestBody AddOrRemoveProductParam param);

}

