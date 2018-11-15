package com.azz.order.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.merchant.pojo.MerchantOrderItem;
@Mapper
public interface MerchantOrderItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantOrderItem record);
    
    int batchInsert(List<MerchantOrderItem> records);

    int insertSelective(MerchantOrderItem record);

    MerchantOrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantOrderItem record);

    int updateByPrimaryKey(MerchantOrderItem record);
}