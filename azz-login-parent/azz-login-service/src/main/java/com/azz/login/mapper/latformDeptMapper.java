package com.azz.login.mapper;

import com.azz.login.pojo.latformDept;

public interface latformDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(latformDept record);

    int insertSelective(latformDept record);

    latformDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(latformDept record);

    int updateByPrimaryKey(latformDept record);
}