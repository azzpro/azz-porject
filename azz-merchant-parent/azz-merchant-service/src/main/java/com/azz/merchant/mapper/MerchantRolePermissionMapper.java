package com.azz.merchant.mapper;

import java.util.List;

import com.azz.merchant.pojo.MerchantRolePermission;

public interface MerchantRolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantRolePermission record);

    int insertSelective(MerchantRolePermission record);

    MerchantRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantRolePermission record);

    int updateByPrimaryKey(MerchantRolePermission record);
    
    int deleteByRoleId(Long roleId);
    
    List<String> getPermissionCodesByRoleCode(String roleCode);
}