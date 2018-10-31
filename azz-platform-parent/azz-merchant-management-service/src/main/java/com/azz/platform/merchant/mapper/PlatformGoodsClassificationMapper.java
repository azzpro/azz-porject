package com.azz.platform.merchant.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformGoodsClassification;
import com.azz.platform.merchant.pojo.vo.Classification;

@Mapper
public interface PlatformGoodsClassificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformGoodsClassification record);

    int insertSelective(PlatformGoodsClassification record);

    PlatformGoodsClassification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformGoodsClassification record);

    int updateByPrimaryKey(PlatformGoodsClassification record);
    
    PlatformGoodsClassification selectByAssortmentCode(String assortmentCode);
    
    Classification selectDetailByAssortmentCode(String assortmentCode);
}