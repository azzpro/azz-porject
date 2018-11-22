package com.azz.order.merchant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.order.merchant.pojo.MerchantInvoiceLogistics;
@Mapper
public interface MerchantInvoiceLogisticsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantInvoiceLogistics record);

    int insertSelective(MerchantInvoiceLogistics record);

    MerchantInvoiceLogistics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantInvoiceLogistics record);

    int updateByPrimaryKey(MerchantInvoiceLogistics record);
}