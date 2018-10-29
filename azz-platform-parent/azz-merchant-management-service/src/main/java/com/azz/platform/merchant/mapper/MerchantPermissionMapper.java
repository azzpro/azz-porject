package com.azz.platform.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.MerchantPermission;
import com.azz.platform.merchant.pojo.vo.Permission;

@Mapper
public interface MerchantPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantPermission record);

    int insertSelective(MerchantPermission record);

    MerchantPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantPermission record);

    int updateByPrimaryKey(MerchantPermission record);
    
    List<Permission> getAllPermissions();
    
}