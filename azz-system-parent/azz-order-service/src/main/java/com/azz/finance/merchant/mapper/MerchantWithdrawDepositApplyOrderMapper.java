package com.azz.finance.merchant.mapper;

import com.azz.order.finance.pojo.MerchantWithdrawDepositApplyOrder;

public interface MerchantWithdrawDepositApplyOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantWithdrawDepositApplyOrder record);

    int insertSelective(MerchantWithdrawDepositApplyOrder record);

    MerchantWithdrawDepositApplyOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantWithdrawDepositApplyOrder record);

    int updateByPrimaryKey(MerchantWithdrawDepositApplyOrder record);
}