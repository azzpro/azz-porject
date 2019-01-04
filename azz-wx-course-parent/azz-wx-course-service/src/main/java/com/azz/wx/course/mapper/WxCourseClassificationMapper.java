package com.azz.wx.course.mapper;

import com.azz.wx.course.pojo.WxCourseClassification;

public interface WxCourseClassificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseClassification record);

    int insertSelective(WxCourseClassification record);

    WxCourseClassification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseClassification record);

    int updateByPrimaryKey(WxCourseClassification record);
}