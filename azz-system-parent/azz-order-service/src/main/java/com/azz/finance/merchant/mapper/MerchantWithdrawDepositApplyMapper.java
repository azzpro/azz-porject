package com.azz.finance.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.finance.pojo.MerchantWithdrawDepositApply;
import com.azz.order.finance.pojo.bo.SearchWithdrawDepositApplyParam;
import com.azz.order.finance.pojo.vo.WithdrawDepositApplyInfo;

@Mapper
public interface MerchantWithdrawDepositApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantWithdrawDepositApply record);

    int insertSelective(MerchantWithdrawDepositApply record);

    MerchantWithdrawDepositApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantWithdrawDepositApply record);

    int updateByPrimaryKey(MerchantWithdrawDepositApply record);
    
    /**
     * 
     * <p>查询提现记录</p>
     * @param param
     * @return
     * @author 黄智聪  2019年3月19日 下午4:17:38
     */
    List<WithdrawDepositApplyInfo> getWithdrawDepositApplyInfos(SearchWithdrawDepositApplyParam param);
}