package com.azz.platform.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.merchant.pojo.PlatformGoodsClassification;
import com.azz.platform.merchant.pojo.bo.SearchSameLevelClassification;
import com.azz.platform.merchant.pojo.vo.Classification;
import com.azz.platform.merchant.pojo.vo.ClassificationList;
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
    
    List<ClassificationList> selectParentByParam(@Param("param") String param);
    
    List<ClassificationSet> selectByAssortmentCodeName(String assortmentCodeName);

    
    List<ClassificationList> selectByParam(@Param("param") String param);
    
    /**
     * <p>根据参数ID查询</p>
     * @param id
     * @return
     * @author 刘建麟  2018年11月1日 下午3:10:44
     */
    List<PlatformGoodsClassification> selectCountByParams(Long id);

    /**
     * <p>校验模型是否存在分类数据</p>
     * @param classificationId
     * @return
     * @author 彭斌  2018年11月1日 下午9:17:18
     */
    int selectCountById(Long classificationId);
    
    PlatformGoodsClassification selectByAssortmentParentCode(String assortmentParentCode);
    
    int selectSameLevelClassification(SearchSameLevelClassification param);
}