package com.azz.client.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.client.pojo.Client;

@Mapper
public interface ClientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Client record);

    int insertSelective(Client record);

    Client selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);
}