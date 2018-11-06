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

import com.azz.core.common.JsonResult;
import com.azz.platform.merchant.pojo.bo.AddCombinationParam;

/**
 * <P>模组业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月1日 下午2:23:23
 */
@FeignClient("azz-merchant-management-service")
public interface CombinationService {
	
	/**
	 * 
	 * <p>新增推荐组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 上午10:50:59
	 */
	@RequestMapping("/azz/api/merchant/combination/addCombination")
	JsonResult<String> addCombination(@RequestBody AddCombinationParam param);
	
}

