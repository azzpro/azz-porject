package com.azz.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.merchant.pojo.MerchantPermission;
import com.azz.merchant.pojo.vo.MerchantUserPermission;
import com.azz.merchant.pojo.vo.Permission;

@Mapper
public interface MerchantPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantPermission record);

    int insertSelective(MerchantPermission record);

    MerchantPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantPermission record);

    int updateByPrimaryKey(MerchantPermission record);
    
    List<MerchantUserPermission> getMerchantUserPermissionInfoByPhoneNumber(String phoneNumber);
    
    List<MerchantUserPermission> getMerchantUserPermissionByPhoneNumberAndLevel(@Param("phoneNumber")String phoneNumber, @Param("level")Integer level);
    
    MerchantPermission getMerchantPermissionByPermissionCode(String permissionCode);
    
    List<MerchantPermission> getMerchantPermissionByParentPermissionCode(String parentPermissionCode);
    
    List<Permission> getAllPermissions();
    
}