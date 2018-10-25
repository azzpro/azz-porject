package com.azz.platform.client.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.client.pojo.ClientUserCompany;

@Mapper
public interface ClientUserCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientUserCompany record);

    int insertSelective(ClientUserCompany record);

    ClientUserCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientUserCompany record);

    int updateByPrimaryKey(ClientUserCompany record);
}