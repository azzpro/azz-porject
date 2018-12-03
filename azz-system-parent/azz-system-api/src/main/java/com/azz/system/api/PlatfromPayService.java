/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月26日 下午3:19:35
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.system.bo.PayList;
import com.azz.system.bo.SubmitPayParams;
import com.azz.system.pojo.PlatformPay;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月26日 下午3:19:35
 */
@FeignClient("azz-system-service")
public interface PlatfromPayService {
	
	/**
	 * <p>提交支付</p>
	 * @param spp
	 * @return
	 * @author 刘建麟  2018年11月26日 下午3:20:20
	 */
	@RequestMapping(value="/azz/api/pay/submitOrderPay",method=RequestMethod.POST)
	public JsonResult<String> submitOrderPay(@RequestBody SubmitPayParams spp);
	
	/**
	 * <p>支付管理列表</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年12月3日 下午2:46:08
	 */
	@RequestMapping("/azz/api/pay/toPayList")
	public JsonResult<Pagination<PlatformPay>> toPayList(@RequestBody PayList pl);
}

