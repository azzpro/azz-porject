package com.azz.finance.merchant.mapper;

import java.util.List;

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
    
    /**
     * 
     * <p>判断所选订单是否存在已经提过款的或正在提款中的</p>
     * @param orderCodes
     * @return
     * @author 黄智聪  2019年3月20日 上午10:58:02
     */
    int existPayWithOrder(List<String> orderCodes);
}