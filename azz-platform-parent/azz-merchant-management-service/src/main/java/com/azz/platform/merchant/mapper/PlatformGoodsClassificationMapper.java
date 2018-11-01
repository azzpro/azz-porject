package com.azz.platform.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.merchant.pojo.PlatformGoodsClassification;
import com.azz.platform.merchant.pojo.vo.Classification;
import com.azz.platform.merchant.pojo.vo.ClassificationSet;

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
    
    List<ClassificationSet> selectParentByAssortmentCodeName(String assortmentCodeName);
    
    List<ClassificationSet> selectByAssortmentCodeName(String assortmentCodeName);

    /**
     * <p>根据参数ID查询</p>
     * @param id
     * @return
     * @author 刘建麟  2018年11月1日 下午3:10:44
     */
    List<PlatformGoodsClassification> selectCountByParams(Long id);

}