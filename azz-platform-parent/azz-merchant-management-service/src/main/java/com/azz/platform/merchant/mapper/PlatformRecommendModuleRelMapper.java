package com.azz.platform.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.merchant.pojo.PlatformRecommendModuleRel;

@Mapper
public interface PlatformRecommendModuleRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformRecommendModuleRel record);

    int insertSelective(PlatformRecommendModuleRel record);

    PlatformRecommendModuleRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformRecommendModuleRel record);

    int updateByPrimaryKey(PlatformRecommendModuleRel record);
    
    /**
     * 
     * <p>删除推荐活动所关联的模组</p>
     * @param moduleCode
     * @param recommendCode
     * @return
     * @author 黄智聪  2019年1月7日 下午3:20:35
     */
    int deleteRecommendModule(@Param("moduleCode")String moduleCode, @Param("recommendCode")String recommendCode);
    
    /**
     * 
     * <p>计算该模组已被关联的数量</p>
     * @param moduleCode
     * @return
     * @author 黄智聪  2019年1月7日 下午3:15:08
     */
    int countRelatedModule(String moduleCode);
    
    /**
     * 
     * <p>计算模组的产品数量</p>
     * @param moduleCode
     * @return
     * @author 黄智聪  2019年1月7日 下午3:10:55
     */
    List<String> getProductCodesByModuleCode(String moduleCode);
}