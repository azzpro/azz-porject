package com.azz.platform.merchant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformRecommendModuleRel;

@Mapper
public interface PlatformRecommendModuleRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformRecommendModuleRel record);

    int insertSelective(PlatformRecommendModuleRel record);

    PlatformRecommendModuleRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformRecommendModuleRel record);

    int updateByPrimaryKey(PlatformRecommendModuleRel record);
}