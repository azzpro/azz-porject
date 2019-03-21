/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:30:18
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.api.client;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.order.client.pojo.RetBean;
import com.azz.order.client.pojo.bo.CashWithdrawal;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月15日 下午2:09:21
 */
@FeignClient("azz-order-service")
public interface CashWithdrawalService {
    
	/**
	 * 
	 * <p>发起提现请求</p>
	 * @param param
	 * @return
	 * @author jonly
	 */
	@RequestMapping("/azz/api/cashWithdrawal/reqCashWithdrawal")
	Map<String,String> reqCashWithdrawal(@RequestBody CashWithdrawal cwa);
	
	/**
	 * 
	 * <p>提现请求回调</p>
	 * @param param
	 * @return
	 * @author jonly
	 */
	@RequestMapping("/azz/api/cashWithdrawal/cashWithdrawalCallback")
	JsonResult<RetBean> cashWithdrawalCallback(@RequestParam("responseMsg") String responseMsg,@RequestParam("customerId") String customerId);
	
}

