package com.azz.platform.merchant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformRecommendModuleProductRel;

@Mapper
public interface PlatformRecommendModuleProductRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformRecommendModuleProductRel record);

    int insertSelective(PlatformRecommendModuleProductRel record);

    PlatformRecommendModuleProductRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformRecommendModuleProductRel record);

    int updateByPrimaryKey(PlatformRecommendModuleProductRel record);
}