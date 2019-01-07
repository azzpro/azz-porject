package com.azz.platform.merchant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformSpecialPerformance;

@Mapper
public interface PlatformSpecialPerformanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformSpecialPerformance record);

    int insertSelective(PlatformSpecialPerformance record);

    PlatformSpecialPerformance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformSpecialPerformance record);

    int updateByPrimaryKey(PlatformSpecialPerformance record);
}