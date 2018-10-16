package com.azz.login.mapper;

import com.azz.login.pojo.latformPermission;

public interface latformPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(latformPermission record);

    int insertSelective(latformPermission record);

    latformPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(latformPermission record);

    int updateByPrimaryKey(latformPermission record);
}