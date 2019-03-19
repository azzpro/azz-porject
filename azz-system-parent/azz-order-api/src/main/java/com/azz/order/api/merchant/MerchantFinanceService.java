/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午6:40:33
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.api.merchant;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.finance.pojo.bo.SearchMerchantOrderParam;
import com.azz.order.finance.pojo.bo.SearchWithdrawDepositApplyParam;
import com.azz.order.finance.pojo.bo.WithdrawDepositApplyParam;
import com.azz.order.finance.pojo.vo.MerchantOrderInfo;
import com.azz.order.finance.pojo.vo.WithdrawDepositApplyDetail;
import com.azz.order.finance.pojo.vo.WithdrawDepositApplyInfo;
import com.azz.order.finance.pojo.vo.WithdrawDepositCount;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 下午6:40:33
 */
@FeignClient("azz-order-service")
public interface MerchantFinanceService {

	/**
	 * 
	 * <p>根据商户查询提现统计</p>
	 * @param merchantCode
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:26:36
	 */
	@RequestMapping("/azz/api/merchant/finance/getWithdrawDepositCount")
	public JsonResult<WithdrawDepositCount> getWithdrawDepositCount(@RequestParam("merchantCode") String merchantCode);

	
	/**
	 * 
	 * <p>查询提现记录</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	@RequestMapping("/azz/api/merchant/finance/getWithdrawDepositApplyInfos")
	public JsonResult<Pagination<WithdrawDepositApplyInfo>> getWithdrawDepositApplyInfos(@RequestBody SearchWithdrawDepositApplyParam param);

	/**
	 * 
	 * <p>查询提现申请详情</p>
	 * @param applyCode
	 * @return
	 * @author 黄智聪  2019年3月19日 下午7:28:50
	 */
	@RequestMapping("/azz/api/merchant/finance/getWithdrawDepositApplyDetail")
	public JsonResult<WithdrawDepositApplyDetail> getWithdrawDepositApplyDetail(@RequestParam("applyCode") String applyCode);
	
	/**
	 * 
	 * <p>提现申请</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	@RequestMapping("/azz/api/merchant/finance/withdrawDepositApply")
	public JsonResult<String> withdrawDepositApply(@RequestBody WithdrawDepositApplyParam param);

	/**
	 * 
	 * <p>获取提现申请的商户订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	@RequestMapping("/azz/api/merchant/finance/getMerchantOrders")
	public JsonResult<Pagination<MerchantOrderInfo>> getMerchantOrders(@RequestBody SearchMerchantOrderParam param);

	
}

