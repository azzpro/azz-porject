package com.azz.platform.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformCaseClassificationParams;

@Mapper
public interface PlatformCaseClassificationParamsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformCaseClassificationParams record);

    int insertSelective(PlatformCaseClassificationParams record);

    PlatformCaseClassificationParams selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformCaseClassificationParams record);

    int updateByPrimaryKey(PlatformCaseClassificationParams record);
}