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
import com.azz.platform.merchant.pojo.bo.PutOnOrPutOffGoodsModuleParam;
import com.azz.platform.merchant.pojo.bo.SearchGoodsModuleParam;
import com.azz.platform.merchant.pojo.vo.GoodsModuleInfo;

/**
 * <P>模组业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月1日 下午2:23:23
 */
@FeignClient("azz-merchant-management-service")
public interface GoodsModuleService {
	

	/**
	 * 
	 * <p>查询模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午3:33:53
	 */
	@RequestMapping("/azz/api/merchant/goodsModule/getGoodModuleInfoList")
	JsonResult<Pagination<GoodsModuleInfo>> getGoodModuleInfoList(@RequestBody SearchGoodsModuleParam param);
	
	/**
	 * 
	 * <p>查询模组详情</p>
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年11月1日 下午8:45:15
	 */
	@RequestMapping("/azz/api/merchant/goodsModule/getGoodModuleInfo")
	JsonResult<GoodsModuleInfo> getGoodModuleInfo(@RequestParam("moduleCode")String moduleCode);
	
	
	/**
	 * 
	 * <p>上架、下架模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午8:02:16
	 */
	@RequestMapping("/azz/api/merchant/goodsModule/putOnOrPutOffGoodsModule")
	JsonResult<String> putOnOrPutOffGoodsModule(@RequestBody PutOnOrPutOffGoodsModuleParam param);
	
}

