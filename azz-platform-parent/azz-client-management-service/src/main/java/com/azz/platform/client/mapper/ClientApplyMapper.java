package com.azz.platform.client.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.client.pojo.ClientApply;
import com.azz.platform.client.pojo.vo.ClientInfo;
@Mapper
public interface ClientApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientApply record);

    int insertSelective(ClientApply record);

    ClientApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientApply record);

    int updateByPrimaryKey(ClientApply record);
    
    ClientInfo selectClientApplyByClientCode(String clientCode);
}