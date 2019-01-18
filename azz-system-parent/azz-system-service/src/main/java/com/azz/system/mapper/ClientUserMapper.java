package com.azz.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.system.pojo.ClientUser;


@Mapper
public interface ClientUserMapper {
    
    
    ClientUser getClientUserByPhoneNumber(String phoneNumber);
    
    int insertSelective(ClientUser record);
}