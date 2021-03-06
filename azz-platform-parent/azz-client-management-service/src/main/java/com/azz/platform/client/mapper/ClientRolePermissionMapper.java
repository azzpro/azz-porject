package com.azz.platform.client.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.client.pojo.ClientRolePermission;
@Mapper
public interface ClientRolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientRolePermission record);

    int insertSelective(ClientRolePermission record);

    ClientRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientRolePermission record);

    int updateByPrimaryKey(ClientRolePermission record);
    
}