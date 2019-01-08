package com.azz.platform.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformSpecialPerformance;
import com.azz.platform.merchant.pojo.bo.SearchSpecialParam;
import com.azz.platform.merchant.pojo.vo.SpecialInfo;

@Mapper
public interface PlatformSpecialPerformanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformSpecialPerformance record);

    int insertSelective(PlatformSpecialPerformance record);

    PlatformSpecialPerformance selectByPrimaryKey(Long id);
    
    PlatformSpecialPerformance selectBySpecialPerformanceCode(String specialPerformanceCode);

    int updateByPrimaryKeySelective(PlatformSpecialPerformance record);

    int updateByPrimaryKey(PlatformSpecialPerformance record);
    
    List<SpecialInfo> getSpecialList(SearchSpecialParam param);
    
    SpecialInfo getSpecialInfo(String code);
    
    int countSpecial(String name);
}