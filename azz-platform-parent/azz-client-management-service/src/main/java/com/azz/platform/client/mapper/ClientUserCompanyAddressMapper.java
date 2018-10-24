package com.azz.platform.client.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.client.pojo.ClientUserCompanyAddress;

@Mapper
public interface ClientUserCompanyAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientUserCompanyAddress record);

    int insertSelective(ClientUserCompanyAddress record);

    ClientUserCompanyAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientUserCompanyAddress record);

    int updateByPrimaryKey(ClientUserCompanyAddress record);
}