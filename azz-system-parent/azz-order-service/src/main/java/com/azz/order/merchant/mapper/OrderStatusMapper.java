package com.azz.order.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.order.merchant.pojo.OrderStatus;
@Mapper
public interface OrderStatusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderStatus record);

    int insertSelective(OrderStatus record);

    OrderStatus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderStatus record);

    int updateByPrimaryKey(OrderStatus record);
}