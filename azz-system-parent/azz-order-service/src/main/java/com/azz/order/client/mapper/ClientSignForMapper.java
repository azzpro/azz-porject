package com.azz.order.client.mapper;

import com.azz.order.client.pojo.ClientSignFor;

public interface ClientSignForMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientSignFor record);

    int insertSelective(ClientSignFor record);

    ClientSignFor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientSignFor record);

    int updateByPrimaryKeyWithBLOBs(ClientSignFor record);

    int updateByPrimaryKey(ClientSignFor record);
}