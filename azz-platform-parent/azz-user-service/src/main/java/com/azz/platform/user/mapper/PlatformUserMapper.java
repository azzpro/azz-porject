package com.azz.platform.user.mapper;

import com.azz.platform.user.pojo.PlatformUser;

public interface PlatformUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformUser record);

    int insertSelective(PlatformUser record);

    PlatformUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformUser record);

    int updateByPrimaryKey(PlatformUser record);
    
    PlatformUser getUserByPhoneNumber(String phoneNumber);
}