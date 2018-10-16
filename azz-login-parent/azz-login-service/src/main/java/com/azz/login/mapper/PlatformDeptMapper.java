package com.azz.login.mapper;

import com.azz.login.pojo.PlatformDept;

public interface PlatformDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformDept record);

    int insertSelective(PlatformDept record);

    PlatformDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformDept record);

    int updateByPrimaryKey(PlatformDept record);
}