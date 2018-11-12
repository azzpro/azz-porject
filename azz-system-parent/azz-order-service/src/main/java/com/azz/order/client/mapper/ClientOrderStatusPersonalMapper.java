package com.azz.order.client.mapper;

import com.azz.order.client.pojo.ClientOrderStatusPersonal;

public interface ClientOrderStatusPersonalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientOrderStatusPersonal record);

    int insertSelective(ClientOrderStatusPersonal record);

    ClientOrderStatusPersonal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientOrderStatusPersonal record);

    int updateByPrimaryKey(ClientOrderStatusPersonal record);
}