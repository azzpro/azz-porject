package com.azz.order.client.mapper;

import com.azz.order.client.pojo.ClientOrderShippingAddress;

public interface ClientOrderShippingAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientOrderShippingAddress record);

    int insertSelective(ClientOrderShippingAddress record);

    ClientOrderShippingAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientOrderShippingAddress record);

    int updateByPrimaryKey(ClientOrderShippingAddress record);
}