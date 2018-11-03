package com.azz.merchant.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.merchant.pojo.PlatformGoodsBrand;
import com.azz.merchant.pojo.bo.SearchGoodsBrandParam;
import com.azz.merchant.pojo.vo.GoodsBrandInfo;

@Mapper
public interface PlatformGoodsBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformGoodsBrand record);

    int insertSelective(PlatformGoodsBrand record);

    PlatformGoodsBrand selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(PlatformGoodsBrand record);
    
    int updateByPrimaryKey(PlatformGoodsBrand record);
    
    /**
     * 
     * <p>查询品牌列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月2日 下午2:47:41
     */
    List<GoodsBrandInfo> getGoodsBrandInfoList(SearchGoodsBrandParam param);
    
    List<PlatformGoodsBrand> selectBrand();
    
    PlatformGoodsBrand selectBrandById(Long id);
    
}