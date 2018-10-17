package com.azz.platform.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.user.pojo.PlatformRolePermission;

@Mapper
public interface PlatformRolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformRolePermission record);

    int insertSelective(PlatformRolePermission record);

    PlatformRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformRolePermission record);

    int updateByPrimaryKey(PlatformRolePermission record);
}