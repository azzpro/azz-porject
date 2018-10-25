package com.azz.platform.client.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.client.pojo.ClientUser;

@Mapper
public interface ClientUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientUser record);

    int insertSelective(ClientUser record);

    ClientUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientUser record);

    int updateByPrimaryKey(ClientUser record);
}