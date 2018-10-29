package com.azz.platform.client.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.client.pojo.ClientPermission;
import com.azz.platform.client.pojo.vo.Permission;
@Mapper
public interface ClientPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientPermission record);

    int insertSelective(ClientPermission record);

    ClientPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientPermission record);

    int updateByPrimaryKey(ClientPermission record);
    
    List<Permission> getAllPermissions();
    
}