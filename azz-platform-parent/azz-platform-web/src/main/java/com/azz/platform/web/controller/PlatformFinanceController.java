/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 上午11:59:47
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.api.platform.PlatformFinanceService;
import com.azz.order.finance.pojo.bo.SearchWithdrawDepositApplyParam;
import com.azz.order.finance.pojo.vo.WithdrawDepositApplyDetail;
import com.azz.order.finance.pojo.vo.WithdrawDepositApplyInfo;

/**
 * <P>平台端提现模块</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 上午11:59:47
 */
@RestController
@RequestMapping("/azz/api/platform/finance")
public class PlatformFinanceController {
	
	@Autowired
	private PlatformFinanceService platformFinanceService;

	/**
	 * 
	 * <p>查询提现记录</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	@RequestMapping("/getPlatformWithdrawDepositApplyInfos")
	public JsonResult<Pagination<WithdrawDepositApplyInfo>> getPlatformWithdrawDepositApplyInfos(SearchWithdrawDepositApplyParam param){
		return platformFinanceService.getPlatformWithdrawDepositApplyInfos(param);
	}
	
	/**
	 * 
	 * <p>查询提现申请详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	@RequestMapping("/getPlatformWithdrawDepositApplyDetail")
	public JsonResult<WithdrawDepositApplyDetail> getPlatformWithdrawDepositApplyDetail(@RequestParam("applyCode") String applyCode){
		return platformFinanceService.getPlatformWithdrawDepositApplyDetail(applyCode);
	}
	
}

