package com.azz.order.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.order.merchant.pojo.MerchantOrderItem;
@Mapper
public interface MerchantOrderItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantOrderItem record);

    int insertSelective(MerchantOrderItem record);

    MerchantOrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantOrderItem record);

    int updateByPrimaryKey(MerchantOrderItem record);
}