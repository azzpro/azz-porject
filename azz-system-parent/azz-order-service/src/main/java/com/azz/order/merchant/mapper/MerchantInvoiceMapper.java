package com.azz.order.merchant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.merchant.pojo.MerchantInvoice;
@Mapper
public interface MerchantInvoiceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantInvoice record);

    int insertSelective(MerchantInvoice record);

    MerchantInvoice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantInvoice record);

    int updateByPrimaryKey(MerchantInvoice record);
}