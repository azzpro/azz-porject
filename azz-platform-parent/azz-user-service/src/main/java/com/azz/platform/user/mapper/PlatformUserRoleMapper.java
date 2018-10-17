package com.azz.platform.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.user.pojo.PlatformUserRole;

@Mapper
public interface PlatformUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformUserRole record);

    int insertSelective(PlatformUserRole record);

    PlatformUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformUserRole record);

    int updateByPrimaryKey(PlatformUserRole record);
}