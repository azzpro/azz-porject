package com.azz.platform.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.merchant.pojo.PlatformGoodsClassification;
import com.azz.platform.merchant.pojo.vo.Classification;
import com.azz.platform.merchant.pojo.vo.ClassificationList;

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
    
    List<ClassificationList> selectParentByParam(@Param("param") String param);
    
    List<ClassificationList> selectByParam(@Param("param") String param);
    
}