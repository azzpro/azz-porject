/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午12:01:06
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.finance.merchant.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.MerchantConstants.WithdrawDepositApplyStatus;
import com.azz.exception.JSR303ValidationException;
import com.azz.finance.merchant.mapper.MerchantWithdrawDepositApplyMapper;
import com.azz.finance.merchant.mapper.MerchantWithdrawDepositApplyOrderMapper;
import com.azz.order.finance.pojo.MerchantWithdrawDepositApply;
import com.azz.order.finance.pojo.MerchantWithdrawDepositApplyOrder;
import com.azz.order.finance.pojo.bo.SearchMerchantOrderParam;
import com.azz.order.finance.pojo.bo.SearchWithdrawDepositApplyParam;
import com.azz.order.finance.pojo.bo.WithdrawDepositApplyParam;
import com.azz.order.finance.pojo.vo.AccountInfo;
import com.azz.order.finance.pojo.vo.ApplyInfo;
import com.azz.order.finance.pojo.vo.MerchantOrderInfo;
import com.azz.order.finance.pojo.vo.OrderInfo;
import com.azz.order.finance.pojo.vo.WithdrawDepositApplyDetail;
import com.azz.order.finance.pojo.vo.WithdrawDepositApplyInfo;
import com.azz.order.finance.pojo.vo.WithdrawDepositCount;
import com.azz.order.merchant.mapper.MerchantOrderMapper;
import com.azz.order.merchant.pojo.MerchantOrder;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>财务模块</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 下午12:01:06
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class MerchantFinanceService {
	
	@Autowired
	private MerchantWithdrawDepositApplyMapper merchantWithdrawDepositApplyMapper;
	
	@Autowired
	private MerchantWithdrawDepositApplyOrderMapper merchantWithdrawDepositApplyOrderMapper;
	
	@Autowired
	private MerchantOrderMapper merchantOrderMapper;
	
	/**
	 * 提现手续费率
	 */
	private static final BigDecimal WITHDRAW_DEPOSIT_RATE = new BigDecimal(0.01);
	
	/**
	 * 
	 * <p>根据商户查询提现统计</p>
	 * @param merchantCode
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:26:36
	 */
	public JsonResult<WithdrawDepositCount> getWithdrawDepositCount(@RequestParam("merchantCode") String merchantCode){
		if(StringUtils.isBlank(merchantCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户编码不能为空");
		}
		// 总收入
		BigDecimal totalIncome = merchantWithdrawDepositApplyMapper.getTotalIncomeByMerchantCode(merchantCode);
		// 已提现金额
		BigDecimal withdrawDepositMoney = merchantWithdrawDepositApplyMapper.getWithdrawDepositMoneyByMerchantCode(merchantCode);
		// 可提现金额  总收入-已提现金额
		BigDecimal notWithdrawDepositMoney = totalIncome.subtract(withdrawDepositMoney);
		return JsonResult.successJsonResult(new WithdrawDepositCount(totalIncome, withdrawDepositMoney, notWithdrawDepositMoney));
	}
	
	/**
	 * 
	 * <p>查询提现记录</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	public JsonResult<Pagination<WithdrawDepositApplyInfo>> getWithdrawDepositApplyInfos(@RequestBody SearchWithdrawDepositApplyParam param){
		if(StringUtils.isBlank(param.getMerchantCode())) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "缺少请求参数");
		}
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<WithdrawDepositApplyInfo> infos = merchantWithdrawDepositApplyMapper.getWithdrawDepositApplyInfos(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询提现申请详情</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	public JsonResult<WithdrawDepositApplyDetail> getWithdrawDepositApplyDetail(@RequestParam("applyCode") String applyCode){
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
		return JsonResult.successJsonResult(new WithdrawDepositApplyDetail(applyInfo, accountInfo, orderInfo, null));
	}
	
	/**
	 * 
	 * <p>提现申请</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	public JsonResult<String> withdrawDepositApply(@RequestBody WithdrawDepositApplyParam param){
		JSR303ValidateUtils.validate(param);
		Date nowDate = new Date();
		String applyCode = System.currentTimeMillis() + ""; // TODO
		List<String> orderCodes = param.getMerchantOrderCodes();
		// 判断所选订单是否存在已经提过款的或正在提款中的
		int count = merchantWithdrawDepositApplyOrderMapper.existPayWithOrder(orderCodes);
		if(count > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "所选订单中包含提款中或已提款的订单");
		}
		// 订单总额
		BigDecimal totalOrderMoney = BigDecimal.ZERO;
		// 实际到账金额
		BigDecimal totalWithdrawDepositMoney = BigDecimal.ZERO;
		for (String orderCode : orderCodes) {
			MerchantOrder order = merchantOrderMapper.selectMerchantOrderInfo(orderCode);
			if(order == null) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "所选订单中包含不存在的订单");
			}
			BigDecimal eachOrderGrandTotal = order.getGrandTotal();
			// 单个订单的实际到账金额 = 订单金额 * (1-手续费率)
			BigDecimal withdrawDepositMoney = eachOrderGrandTotal.multiply(BigDecimal.ONE.subtract(WITHDRAW_DEPOSIT_RATE));
			// 单笔订单的交易费用 = 订单金额 - 单个订单的实际到账金额
			BigDecimal transactionCost = eachOrderGrandTotal.subtract(withdrawDepositMoney);
			MerchantWithdrawDepositApplyOrder applyOrderRecord = MerchantWithdrawDepositApplyOrder.builder()
					.applyCode(applyCode)
					.createTime(nowDate)
					.creator(param.getMerchantUserCode())
					.grandTotal(eachOrderGrandTotal)
					.merchantOrderCode(orderCode)
					.orderDate(order.getCreateTime())
					.transactionCost(transactionCost)
					.withdrawDepositMoney(withdrawDepositMoney)
					.build();
			merchantWithdrawDepositApplyOrderMapper.insertSelective(applyOrderRecord);
			totalOrderMoney = totalOrderMoney.add(eachOrderGrandTotal);
			totalWithdrawDepositMoney = totalWithdrawDepositMoney.add(withdrawDepositMoney);
		}
		MerchantWithdrawDepositApply applyRecord = MerchantWithdrawDepositApply.builder()
				.applyCode(applyCode)
				.createTime(nowDate)
				.creator(param.getMerchantUserCode())
				.merchantCode(param.getMerchantCode())
				.status((byte)WithdrawDepositApplyStatus.PENDING.getValue())
				.totalOrderCount(orderCodes.size())
				.totalOrderMoney(totalOrderMoney)
				.totalWithdrawDepositMoney(totalWithdrawDepositMoney)
				.withdrawDepositAccount(param.getWithdrawDepositAccount())
				.build();
		merchantWithdrawDepositApplyMapper.insertSelective(applyRecord);
		
		
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>获取提现申请的商户订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	public JsonResult<Pagination<MerchantOrderInfo>> getMerchantOrders(@RequestBody SearchMerchantOrderParam param){
		JSR303ValidateUtils.validate(param);
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<MerchantOrderInfo> infos = merchantWithdrawDepositApplyMapper.getMerchantOrders(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
}

