package com.azz.finance.merchant.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.finance.pojo.MerchantWithdrawDepositApply;
import com.azz.order.finance.pojo.bo.SearchMerchantOrderParam;
import com.azz.order.finance.pojo.bo.SearchWithdrawDepositApplyParam;
import com.azz.order.finance.pojo.vo.MerchantOrderInfo;
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
     * <p>查询商户订单总收入</p>
     * @param merchantCode
     * @return
     * @author 黄智聪  2019年3月19日 下午4:32:54
     */
    BigDecimal getTotalIncomeByMerchantCode(String merchantCode);
    
    /**
     * 
     * <p>查询商户订单总提现金额</p>
     * @param merchantCode
     * @return
     * @author 黄智聪  2019年3月19日 下午4:32:54
     */
    BigDecimal getWithdrawDepositMoneyByMerchantCode(String merchantCode);
    
    /**
     * 
     * <p>查询提现记录</p>
     * @param param
     * @return
     * @author 黄智聪  2019年3月19日 下午4:17:38
     */
    List<WithdrawDepositApplyInfo> getWithdrawDepositApplyInfos(SearchWithdrawDepositApplyParam param);
    
    /**
     * 
     * <p>查询提现申请的商户订单列表</p>
     * @param param
     * @return
     * @author 黄智聪  2019年3月19日 下午5:56:55
     */
    List<MerchantOrderInfo> getMerchantOrders(SearchMerchantOrderParam param);
}