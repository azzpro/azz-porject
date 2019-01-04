package com.azz.wx.course.mapper;

import com.azz.wx.course.pojo.WxCourseParam;

public interface WxCourseParamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseParam record);

    int insertSelective(WxCourseParam record);

    WxCourseParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseParam record);

    int updateByPrimaryKey(WxCourseParam record);
}