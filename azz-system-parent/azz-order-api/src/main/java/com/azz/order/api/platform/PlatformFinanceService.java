/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午6:40:33
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.api.platform;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.finance.pojo.bo.SearchWithdrawDepositApplyParam;
import com.azz.order.finance.pojo.vo.WithdrawDepositApplyDetail;
import com.azz.order.finance.pojo.vo.WithdrawDepositApplyInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 下午6:40:33
 */
@FeignClient("azz-order-service")
public interface PlatformFinanceService {

	/**
	 * 
	 * <p>查询提现记录</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	@RequestMapping("/azz/api/platform/finance/getPlatformWithdrawDepositApplyInfos")
	public JsonResult<Pagination<WithdrawDepositApplyInfo>> getPlatformWithdrawDepositApplyInfos(@RequestBody SearchWithdrawDepositApplyParam param);

	/**
	 * 
	 * <p>查询提现申请详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	@RequestMapping("/azz/api/platform/finance/getPlatformWithdrawDepositApplyDetail")
	public JsonResult<WithdrawDepositApplyDetail> getPlatformWithdrawDepositApplyDetail(@RequestParam("applyCode") String applyCode);

}

