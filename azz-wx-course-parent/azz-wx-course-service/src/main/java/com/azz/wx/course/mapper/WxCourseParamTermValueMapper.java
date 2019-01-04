package com.azz.wx.course.mapper;

import com.azz.wx.course.pojo.WxCourseParamTermValue;

public interface WxCourseParamTermValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseParamTermValue record);

    int insertSelective(WxCourseParamTermValue record);

    WxCourseParamTermValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseParamTermValue record);

    int updateByPrimaryKey(WxCourseParamTermValue record);
}