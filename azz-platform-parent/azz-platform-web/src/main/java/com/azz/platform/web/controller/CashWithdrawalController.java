package com.azz.platform.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.order.api.client.CashWithdrawalService;
import com.azz.order.client.pojo.RetBean;
import com.azz.order.client.pojo.bo.CashWithdrawal;
import com.azz.util.JSR303ValidateUtils;

/**
 * @author THINK
 * 提现----->易宝
 */
@RestController
@RequestMapping("/azz/api/cashWithdrawal")
public class CashWithdrawalController {
	
	private static Logger log = LoggerFactory.getLogger("cashwithdrawal request----->");
	
	@Autowired
	private CashWithdrawalService cashWithdrawalService;
	
	/**
	 * 申请提现
	 * @param cwa
	 * @return
	 */
	@RequestMapping("reqCashWithdrawal")
	public Map<String,String> reqCashWithdrawal(CashWithdrawal cwa){
		JSR303ValidateUtils.validate(cwa);
		return cashWithdrawalService.reqCashWithdrawal(cwa);
	}
	
	/**
	 * 提现回调
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("cashWithdrawalCallback")
	public void cashWithdrawalCallback(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String responseMsg = request.getParameter("response");
		String customerId = request.getParameter("customerIdentification");
		log.info("进入提现回调接口");
		log.info("responseMsg---->"+responseMsg);
		log.info("customerId---->"+customerId);
		JsonResult<RetBean> notify = cashWithdrawalService.cashWithdrawalCallback(responseMsg,customerId);
		response.getWriter().write(notify.getMsg());
		response.getWriter().flush();
	}
}
