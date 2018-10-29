package com.azz.platform.merchant.mapper;

import java.util.List;

import javax.management.relation.RoleInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.merchant.pojo.MerchantRole;
import com.azz.platform.merchant.pojo.bo.SearchRoleParam;

@Mapper
public interface MerchantRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantRole record);

    int insertSelective(MerchantRole record);

    MerchantRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantRole record);

    int updateByPrimaryKey(MerchantRole record);
    
    MerchantRole selectByRoleCode(String roleCode);
    
    int updateByRoleCode(MerchantRole record);
    
    MerchantRole hasRoleName(@Param("merchantId") Long merchantId, @Param("roleName")String roleName, @Param("roleCode") String roleCode);
    
    List<RoleInfo> getRoleInfoBySearchParam(SearchRoleParam param);
    
}