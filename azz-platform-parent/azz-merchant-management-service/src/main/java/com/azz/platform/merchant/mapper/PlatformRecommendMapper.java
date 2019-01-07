package com.azz.platform.merchant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformRecommend;

@Mapper
public interface PlatformRecommendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformRecommend record);

    int insertSelective(PlatformRecommend record);

    PlatformRecommend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformRecommend record);

    int updateByPrimaryKey(PlatformRecommend record);
}