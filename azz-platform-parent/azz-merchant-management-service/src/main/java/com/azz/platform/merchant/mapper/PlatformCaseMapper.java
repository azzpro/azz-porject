package com.azz.platform.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformCase;
@Mapper
public interface PlatformCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformCase record);

    int insertSelective(PlatformCase record);

    PlatformCase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformCase record);

    int updateByPrimaryKey(PlatformCase record);
    
    PlatformCase selectByCaseCode(String caseCode);
    
    PlatformCase selectByCaseName(String caseName);
    
    PlatformCase selectByClassificationId(Long classificationId);
}