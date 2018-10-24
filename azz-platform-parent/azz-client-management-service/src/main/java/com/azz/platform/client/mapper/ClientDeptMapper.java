package com.azz.platform.client.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.client.pojo.ClientDept;

@Mapper
public interface ClientDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientDept record);

    int insertSelective(ClientDept record);

    ClientDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientDept record);

    int updateByPrimaryKey(ClientDept record);
}