package com.azz.order.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.order.merchant.pojo.MerchantOrderStatus;
import com.azz.order.merchant.pojo.bo.SearchOrderStatusParam;
@Mapper
public interface MerchantOrderStatusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantOrderStatus record);

    int insertSelective(MerchantOrderStatus record);

    MerchantOrderStatus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantOrderStatus record);

    int updateByPrimaryKey(MerchantOrderStatus record);
    
    MerchantOrderStatus selectOrderStatus(SearchOrderStatusParam param);
}