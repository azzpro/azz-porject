package com.azz.order.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.order.merchant.pojo.MerchantOrderLogistics;
@Mapper
public interface MerchantOrderLogisticsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantOrderLogistics record);

    int insertSelective(MerchantOrderLogistics record);

    MerchantOrderLogistics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantOrderLogistics record);

    int updateByPrimaryKey(MerchantOrderLogistics record);
}