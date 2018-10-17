package com.azz.platform.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.user.pojo.PlatformRole;

@Mapper
public interface PlatformRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformRole record);

    int insertSelective(PlatformRole record);

    PlatformRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformRole record);

    int updateByPrimaryKey(PlatformRole record);
}