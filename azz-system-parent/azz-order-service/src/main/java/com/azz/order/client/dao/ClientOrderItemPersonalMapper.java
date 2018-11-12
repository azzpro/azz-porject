package com.azz.order.client.dao;

import com.azz.order.client.pojo.ClientOrderItemPersonal;

public interface ClientOrderItemPersonalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientOrderItemPersonal record);

    int insertSelective(ClientOrderItemPersonal record);

    ClientOrderItemPersonal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientOrderItemPersonal record);

    int updateByPrimaryKey(ClientOrderItemPersonal record);
}