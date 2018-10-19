package com.azz.platform.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.user.pojo.PlatformRole;
import com.azz.platform.user.pojo.bo.SearchRoleParam;
import com.azz.platform.user.pojo.vo.RoleInfo;

@Mapper
public interface PlatformRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformRole record);

    int insertSelective(PlatformRole record);

    PlatformRole selectByPrimaryKey(Long id);
    
    PlatformRole selectByRoleCode(String roleCode);

    int updateByPrimaryKeySelective(PlatformRole record);
    
    int updateByRoleCode(PlatformRole record);

    int updateByPrimaryKey(PlatformRole record);
    
    PlatformRole hasRoleName(@Param("roleName")String roleName, @Param("roleCode") String roleCode);
    
    List<RoleInfo> getRoleInfoBySearchParam(SearchRoleParam param);
}