/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月19日 下午12:01:06
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.finance.merchant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.finance.merchant.mapper.MerchantWithdrawDepositApplyMapper;
import com.azz.order.finance.pojo.bo.SearchWithdrawDepositApplyParam;
import com.azz.order.finance.pojo.vo.WithdrawDepositApplyInfo;
import com.azz.order.finance.pojo.vo.WithdrawDepositCount;
import com.azz.util.JSR303ValidateUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年3月19日 下午12:01:06
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class FinanceService {
	
	@Autowired
	private MerchantWithdrawDepositApplyMapper merchantWithdrawDepositApplyMapper;
	
	
	/**
	 * 
	 * <p>根据商户查询提现统计</p>
	 * @param merchantCode
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:26:36
	 */
	public JsonResult<WithdrawDepositCount> getWithdrawDepositCount(String merchantCode){
		return null;
	}
	
	/**
	 * 
	 * <p>查询提现记录</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年3月19日 下午4:18:04
	 */
	public JsonResult<Pagination<WithdrawDepositApplyInfo>> getWithdrawDepositApplyInfos(@RequestBody SearchWithdrawDepositApplyParam param){
		JSR303ValidateUtils.validate(param);
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<WithdrawDepositApplyInfo> infos = merchantWithdrawDepositApplyMapper.getWithdrawDepositApplyInfos(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	

}

