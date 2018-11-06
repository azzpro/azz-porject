package com.azz.platform.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformCombinationModule;

@Mapper
public interface PlatformCombinationModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformCombinationModule record);

    int insertSelective(PlatformCombinationModule record);

    PlatformCombinationModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformCombinationModule record);

    int updateByPrimaryKey(PlatformCombinationModule record);
    
    int deleteByCombinationId(Long combinationId);
    
    int batchInsert(List<PlatformCombinationModule> records);
}