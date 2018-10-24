package com.azz.platform.client.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.client.pojo.Client;
import com.azz.platform.client.pojo.bo.SearchClientParam;
import com.azz.platform.client.pojo.vo.ClientCertification;

@Mapper
public interface ClientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Client record);

    int insertSelective(Client record);

    Client selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);
    
    List<ClientCertification> selectByClientCertificationList(SearchClientParam param);
}