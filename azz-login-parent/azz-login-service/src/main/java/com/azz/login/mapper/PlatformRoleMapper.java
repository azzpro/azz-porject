package com.azz.login.mapper;

import com.azz.login.pojo.PlatformRole;

public interface PlatformRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformRole record);

    int insertSelective(PlatformRole record);

    PlatformRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformRole record);

    int updateByPrimaryKey(PlatformRole record);
}