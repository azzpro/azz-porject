package com.azz.platform.user.mapper;

import com.azz.platform.user.pojo.PlatformPermission;

public interface PlatformPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformPermission record);

    int insertSelective(PlatformPermission record);

    PlatformPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformPermission record);

    int updateByPrimaryKey(PlatformPermission record);
}