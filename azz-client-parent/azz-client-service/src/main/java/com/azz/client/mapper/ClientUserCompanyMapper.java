package com.azz.client.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.client.pojo.ClientUserCompany;
@Mapper
public interface ClientUserCompanyMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByClientUserCode(String clientUserCode);
    
    int insert(ClientUserCompany record);

    int insertSelective(ClientUserCompany record);

    ClientUserCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientUserCompany record);

    int updateByPrimaryKey(ClientUserCompany record);
    
    int getCountByCrditCode(String creditCode);
}