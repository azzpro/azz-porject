package com.azz.platform.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.merchant.pojo.PlatformRecommendModuleProductRel;

@Mapper
public interface PlatformRecommendModuleProductRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformRecommendModuleProductRel record);

    int insertSelective(PlatformRecommendModuleProductRel record);

    PlatformRecommendModuleProductRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformRecommendModuleProductRel record);

    int updateByPrimaryKey(PlatformRecommendModuleProductRel record);

	int batchInsert(List<PlatformRecommendModuleProductRel> records);
	
	/**
	 * 
	 * <p>批量删除模组绑定的产品信息</p>
	 * @param moduleCode
	 * @param productCode
	 * @return
	 * @author 黄智聪  2019年1月7日 下午7:07:17
	 */
	int deleteByModuleCode(String moduleCode);
	
	/**
	 * 
	 * <p>删除模组中的绑定的产品</p>
	 * @param moduleCode
	 * @param productCode
	 * @return
	 * @author 黄智聪  2019年1月7日 下午7:36:56
	 */
	int deleteByModuleCodeAndProductCode(@Param("moduleCode")String moduleCode, @Param("productCode")String productCode);
	
	/**
	 * 
	 * <p>查询产品是否被绑定过</p>
	 * @param productCode
	 * @return
	 * @author 黄智聪  2019年1月7日 下午7:39:49
	 */
	int countProduct(String productCode);
}