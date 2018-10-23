package com.azz.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.azz.merchant.pojo.MerchantPermission;
import com.azz.merchant.pojo.vo.MerchantPermissionInfo;
import com.azz.merchant.pojo.vo.Permission;

public interface MerchantPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantPermission record);

    int insertSelective(MerchantPermission record);

    MerchantPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantPermission record);

    int updateByPrimaryKey(MerchantPermission record);
    
    List<MerchantPermissionInfo> getMerchantPermissionInfoByPhoneNumber(String phoneNumber);
    
    List<MerchantPermissionInfo> getMerchantPermissionInfoByPhoneNumberAndLevel(@Param("phoneNumber")String phoneNumber, @Param("level")Integer level);
    
    MerchantPermission getPermissionByPermissionCode(String permissionCode);
    
    List<MerchantPermission> getPermissionByParentPermissionCode(String parentPermissionCode);
    
    List<Permission> getAllPermissions();
}