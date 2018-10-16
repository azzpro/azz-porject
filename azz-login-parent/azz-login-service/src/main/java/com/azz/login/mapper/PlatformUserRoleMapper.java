package com.azz.login.mapper;

import com.azz.login.pojo.PlatformUserRole;

public interface PlatformUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformUserRole record);

    int insertSelective(PlatformUserRole record);

    PlatformUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformUserRole record);

    int updateByPrimaryKey(PlatformUserRole record);
}