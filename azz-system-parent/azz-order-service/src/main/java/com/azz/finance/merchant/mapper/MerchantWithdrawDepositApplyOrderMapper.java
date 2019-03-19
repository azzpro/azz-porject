package com.azz.finance.merchant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.finance.pojo.MerchantWithdrawDepositApplyOrder;

@Mapper
public interface MerchantWithdrawDepositApplyOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantWithdrawDepositApplyOrder record);

    int insertSelective(MerchantWithdrawDepositApplyOrder record);

    MerchantWithdrawDepositApplyOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantWithdrawDepositApplyOrder record);

    int updateByPrimaryKey(MerchantWithdrawDepositApplyOrder record);
}