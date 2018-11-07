package com.azz.platform.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.merchant.pojo.PlatformCaseClassificationParams;

@Mapper
public interface PlatformCaseClassificationParamsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformCaseClassificationParams record);

    int insertSelective(PlatformCaseClassificationParams record);

    PlatformCaseClassificationParams selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformCaseClassificationParams record);

    int updateByPrimaryKey(PlatformCaseClassificationParams record);
    
    PlatformCaseClassificationParams selectParam(@Param("param") Long caseId, @Param("paramsId") Long paramsId);
}