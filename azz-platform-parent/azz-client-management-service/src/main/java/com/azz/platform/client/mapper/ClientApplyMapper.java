package com.azz.platform.client.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.client.pojo.ClientApply;
import com.azz.platform.client.pojo.bo.SearchClientParam;
import com.azz.platform.client.pojo.vo.ClientCertification;

@Mapper
public interface ClientApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientApply record);

    int insertSelective(ClientApply record);

    ClientApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientApply record);

    int updateByPrimaryKey(ClientApply record);
    
    List<ClientCertification> selectByClientCertificationList(SearchClientParam param);
    
    ClientApply selectByClientUserIdStatus(Long clientUserId);
}