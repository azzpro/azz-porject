package com.azz.platform.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.platform.merchant.pojo.PlatformGoodsBrand;
import com.azz.platform.merchant.pojo.bo.SearchGoodsBrandParam;
import com.azz.platform.merchant.pojo.vo.GoodsBrandInfo;

@Mapper
public interface PlatformGoodsBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformGoodsBrand record);

    int insertSelective(PlatformGoodsBrand record);

    PlatformGoodsBrand selectByPrimaryKey(Long id);
    
    PlatformGoodsBrand selectByBrandCode(String brandCode);

    int updateByPrimaryKeySelective(PlatformGoodsBrand record);
    
    int updateByBrandCode(PlatformGoodsBrand record);

    int updateByPrimaryKey(PlatformGoodsBrand record);
    
    int countGoodsBrandByBrandName(@Param("brandName")String brandName, @Param("brandCode")String brandCode);
    
    int countGoodsBrandByBrandEnglishName(@Param("brandEnglishName")String brandEnglishName, @Param("brandCode")String brandCode);
    
    /**
     * 
     * <p>查询品牌列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月2日 下午2:47:41
     */
    List<GoodsBrandInfo> getGoodsBrandInfoList(SearchGoodsBrandParam param);
    
    /**
     * 
     * <p>查询品牌详情</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月2日 下午2:47:41
     */
    GoodsBrandInfo getGoodsBrandInfo(String brandCode);
    
    int countBindingProduct(Long brandId);
    
}