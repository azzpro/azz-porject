package com.azz.platform.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.user.pojo.PlatformDept;
import com.azz.platform.user.pojo.bo.SearchDeptParam;
import com.azz.platform.user.pojo.vo.Dept;

@Mapper
public interface PlatformDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformDept record);

    int insertSelective(PlatformDept record);

    PlatformDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformDept record);

    int updateByPrimaryKey(PlatformDept record);
    
    PlatformDept selectByDeptName(String deptName);
    
    List<Dept> selectDeptList(SearchDeptParam param);
    
    PlatformDept selectByDeptCode(String deptCode);
}