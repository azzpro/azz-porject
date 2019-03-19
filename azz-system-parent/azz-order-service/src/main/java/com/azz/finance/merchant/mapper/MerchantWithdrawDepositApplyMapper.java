package com.azz.finance.merchant.mapper;

import com.azz.order.finance.pojo.MerchantWithdrawDepositApply;

public interface MerchantWithdrawDepositApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantWithdrawDepositApply record);

    int insertSelective(MerchantWithdrawDepositApply record);

    MerchantWithdrawDepositApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantWithdrawDepositApply record);

    int updateByPrimaryKey(MerchantWithdrawDepositApply record);
}