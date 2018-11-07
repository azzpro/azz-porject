/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:23:23
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

/**
 * <P>模组业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月1日 下午2:23:23
 */
@FeignClient("azz-merchant-management-service")
public interface CombinationService {
	
	/**
	 * 
	 * <p>查询组合列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月5日 下午7:10:34
	 */
	@RequestMapping("/azz/api/merchant/combination/getCombinationInfoList")
	JsonResult<Pagination<CombinationInfo>> getCombinationInfoList(@RequestBody SearchCombinationParam param);
	
	/**
	 * 
	 * <p>查询组合详情</p>
	 * @param combinationCode
	 * @return
	 * @author 黄智聪  2018年11月5日 下午7:21:10
	 */
	@RequestMapping("/azz/api/merchant/combination/getCombinationInfo")
	JsonResult<CombinationInfo> getCombinationInfo(@RequestParam("combinationCode")String combinationCode);
	
	/**
	 * 
	 * <p>新增推荐组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 上午10:55:57
	 */
	@RequestMapping("/azz/api/merchant/combination/addCombination")
	JsonResult<String> addCombination(@RequestBody AddCombinationParam param);
	
	/**
	 * 
	 * <p>修改推荐组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月5日 下午7:41:49
	 */
	@RequestMapping("/azz/api/merchant/combination/editCombination")
	JsonResult<String> editCombination(@RequestBody EditCombinationParam param);
	
	/**
	 * 
	 * <p>上架、下架或删除组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:01:18
	 */
	@RequestMapping("/azz/api/merchant/combination/putOnOrPutOffOrDelCombination")
	JsonResult<String> putOnOrPutOffOrDelCombination(@RequestBody PutOnOrPutOffOrDelCombinationParam param);
	
	/**
	 * 
	 * <p>查询方案列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:43:38
	 */
	@RequestMapping("/azz/api/merchant/combination/getCaseInfoList")
	JsonResult<Pagination<CaseInfo>> getCaseInfoList(@RequestBody SearchCaseInfoParam param);
	
	/**
	 * 
	 * <p>查询模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:43:38
	 */
	@RequestMapping("/azz/api/merchant/combination/getModuleInfoList")
	JsonResult<Pagination<GoodsModuleInfo>> getModuleInfoList(@RequestBody SearchGoodsModuleParam param);
	
	/**
	 * 
	 * <p>查询产品列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:43:38
	 */
	@RequestMapping("/azz/api/merchant/combination/getProductInfoList")
	JsonResult<Pagination<ProdInfo>> getProductInfoList(@RequestBody SearchProductInfoParam param);
	
}

