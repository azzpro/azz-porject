package com.azz.wx.course.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.ClientUser;

@Mapper
public interface ClientUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientUser record);

    int insertSelective(ClientUser record);

    ClientUser selectByPrimaryKey(Long id);
 
    int updateByPrimaryKeySelective(ClientUser record);

    int updateByPrimaryKey(ClientUser record);
    
    ClientUser getClientUserByClientUserCode(String clientUserCode);
    
    ClientUser getClientUserByClientUserPhoneNumber(String phoneNumber);
}