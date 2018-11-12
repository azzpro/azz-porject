package com.azz.order.client.dao;

import com.azz.order.client.pojo.ClientOrderPersonal;

public interface ClientOrderPersonalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientOrderPersonal record);

    int insertSelective(ClientOrderPersonal record);

    ClientOrderPersonal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientOrderPersonal record);

    int updateByPrimaryKey(ClientOrderPersonal record);
}