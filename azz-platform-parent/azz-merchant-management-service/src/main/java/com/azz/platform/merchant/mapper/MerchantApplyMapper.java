package com.azz.platform.merchant.mapper;

import com.azz.platform.merchant.pojo.MerchantApply;
import com.azz.platform.merchant.pojo.vo.MerchantInfo;

public interface MerchantApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantApply record);

    int insertSelective(MerchantApply record);

    MerchantApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantApply record);

    int updateByPrimaryKey(MerchantApply record);
    
    MerchantApply selectByCodeAndStatus(String merchantCode);
    
    MerchantInfo selectMerchantInfoByCode(String merchantCode);
}