package com.azz.platform.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.user.pojo.PlatformPermission;
import com.azz.platform.user.pojo.vo.UserPermission;

@Mapper
public interface PlatformPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformPermission record);

    int insertSelective(PlatformPermission record);

    PlatformPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformPermission record);

    int updateByPrimaryKey(PlatformPermission record);
    
    List<UserPermission> getUserPermissionInfoByPhoneNumber(String phoneNumber);
    
    List<UserPermission> getUserPermissionByPhoneNumberAndLevel(@Param("phoneNumber")String phoneNumber, @Param("level")Integer level);
    
    PlatformPermission getPermissionByPermissionCode(String permissionCode);
    
    List<PlatformPermission> getPermissionByParentPermissionCode(String parentPermissionCode);
    
}