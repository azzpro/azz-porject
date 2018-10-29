package com.azz.platform.client.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.client.pojo.ClientUserRole;
@Mapper
public interface ClientUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientUserRole record);

    int insertSelective(ClientUserRole record);

    ClientUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientUserRole record);

    int updateByPrimaryKey(ClientUserRole record);
    
    int deleteByClientUserId(Long clientUserId);
}