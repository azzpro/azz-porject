package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.wx.course.pojo.WxCourseBrand;
import com.azz.wx.course.pojo.bo.SearchBrandParam;
import com.azz.wx.course.pojo.vo.BrandInfo;

@Mapper
public interface WxCourseBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseBrand record);

    int insertSelective(WxCourseBrand record);

    WxCourseBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseBrand record);

    int updateByPrimaryKeyWithBLOBs(WxCourseBrand record);

    int updateByPrimaryKey(WxCourseBrand record);
    
    List<BrandInfo> getBrandInfoList(SearchBrandParam param);
    
    BrandInfo getBrandInfo(String brandCode);
    
    int countGoodsBrandByBrandName(@Param("brandName")String brandName, @Param("brandCode")String brandCode);

    WxCourseBrand selectByBrandCode(String brandCode);
    
    int updateByBrandCode(WxCourseBrand record);
    
    int countBindingCourse(String brandCode);
}