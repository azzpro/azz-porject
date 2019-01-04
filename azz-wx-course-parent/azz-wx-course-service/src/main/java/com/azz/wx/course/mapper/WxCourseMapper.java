package com.azz.wx.course.mapper;

import com.azz.wx.course.pojo.WxCourse;

public interface WxCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourse record);

    int insertSelective(WxCourse record);

    WxCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourse record);

    int updateByPrimaryKeyWithBLOBs(WxCourse record);

    int updateByPrimaryKey(WxCourse record);
}