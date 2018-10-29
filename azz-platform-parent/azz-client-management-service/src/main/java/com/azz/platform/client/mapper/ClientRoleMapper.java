package com.azz.platform.client.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.client.pojo.ClientRole;

@Mapper
public interface ClientRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientRole record);

    int insertSelective(ClientRole record);

    ClientRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientRole record);

    int updateByPrimaryKey(ClientRole record);

    int updateByRoleCode(ClientRole record);
   
}