package com.azz.order.client.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.order.client.pojo.RetBean;
import com.azz.order.client.pojo.bo.CashWithdrawal;
import com.azz.order.client.service.ClientPayService;

/**
 * @author THINK
 * 提现----->易宝
 */
@RestController
@RequestMapping("/azz/api/cashWithdrawal")
public class CashWithdrawalController {
	
	private static Logger log = LoggerFactory.getLogger("cashwithdrawal request----->");
	
	@Autowired
	private ClientPayService clientPayService;	
	
	/**
	 * 提现请求
	 * @param cwa
	 * @return
	 */
	@RequestMapping("reqCashWithdrawal")
	public Map<String,String> reqCashWithdrawal(@RequestBody CashWithdrawal cwa){
		return clientPayService.cashWithdrawal(cwa);
	}
	
	/**
	 * 
	 * <p>提现请求回调</p>
	 * @param param
	 * @return
	 * @author jonly
	 */
	@RequestMapping("cashWithdrawalCallback")
	public JsonResult<RetBean> cashWithdrawalCallback(@RequestParam("responseMsg") String responseMsg,@RequestParam("customerId") String customerId) {
		return clientPayService.cashWithdrawalCallback(responseMsg, customerId);
	}
}
