package com.azz.platform.client.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.client.pojo.ClientUser;
import com.azz.platform.client.pojo.vo.ClientInfo;

@Mapper
public interface ClientUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientUser record);

    int insertSelective(ClientUser record);

    ClientUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientUser record);

    int updateByPrimaryKey(ClientUser record);
    
    ClientInfo selectDetailsByClientUserCode(String clientUserCode);
    
    int checkClientApplyInfo(String clientUserCode);
    
    ClientUser selectByClientUserCode(String clientUserCode);
}