package com.azz.wx.course.mapper;

import com.azz.wx.course.pojo.WxCourseBrand;

public interface WxCourseBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseBrand record);

    int insertSelective(WxCourseBrand record);

    WxCourseBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseBrand record);

    int updateByPrimaryKeyWithBLOBs(WxCourseBrand record);

    int updateByPrimaryKey(WxCourseBrand record);
}