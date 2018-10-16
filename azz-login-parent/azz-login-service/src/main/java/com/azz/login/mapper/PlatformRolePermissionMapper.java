package com.azz.login.mapper;

import com.azz.login.pojo.PlatformRolePermission;

public interface PlatformRolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformRolePermission record);

    int insertSelective(PlatformRolePermission record);

    PlatformRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformRolePermission record);

    int updateByPrimaryKey(PlatformRolePermission record);
}