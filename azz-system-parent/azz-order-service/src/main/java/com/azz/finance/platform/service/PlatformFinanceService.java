/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午12:01:06
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.finance.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.exception.JSR303ValidationException;
import com.azz.finance.merchant.mapper.MerchantWithdrawDepositApplyMapper;
import com.azz.order.finance.pojo.bo.SearchWithdrawDepositApplyParam;
import com.azz.order.finance.pojo.vo.AccountInfo;
import com.azz.order.finance.pojo.vo.ApplyInfo;
import com.azz.order.finance.pojo.vo.OrderInfo;
import com.azz.order.finance.pojo.vo.ThirdInfo;
import com.azz.order.finance.pojo.vo.WithdrawDepositApplyDetail;
import com.azz.order.finance.pojo.vo.WithdrawDepositApplyInfo;
import com.azz.util.StringUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 下午12:01:06
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class PlatformFinanceService {
	
	@Autowired
	private MerchantWithdrawDepositApplyMapper merchantWithdrawDepositApplyMapper;
	
	/**
	 * 
	 * <p>查询提现记录</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	public JsonResult<Pagination<WithdrawDepositApplyInfo>> getPlatformWithdrawDepositApplyInfos(@RequestBody SearchWithdrawDepositApplyParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<WithdrawDepositApplyInfo> infos = merchantWithdrawDepositApplyMapper.getPlatformWithdrawDepositApplyInfos(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询提现申请详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	public JsonResult<WithdrawDepositApplyDetail> getPlatformWithdrawDepositApplyDetail(@RequestParam("applyCode") String applyCode){
		if(StringUtils.isBlank(applyCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "提现申请编码不能为空");
		}
		// 提现信息
		ApplyInfo applyInfo = merchantWithdrawDepositApplyMapper.getWithdrawDepositApplyInfo(applyCode);
		// 账户信息
		AccountInfo accountInfo = null;// TODO
		// 订单信息
		OrderInfo orderInfo = merchantWithdrawDepositApplyMapper.getWithdrawDepositApplyOrderInfo(applyCode);
		if(orderInfo != null) {
			// 查询提现申请详情中的订单列表
			orderInfo.setOrders(merchantWithdrawDepositApplyMapper.getWithdrawDepositApplyOrders(applyCode));
		}
		// 三方信息
		ThirdInfo thirdInfo = null;// TODO
		return JsonResult.successJsonResult(new WithdrawDepositApplyDetail(applyInfo, accountInfo, orderInfo, thirdInfo));
	}

}

