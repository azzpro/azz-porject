package com.azz.wx.course.mapper;

import com.azz.wx.course.pojo.WxCourseOrder;

public interface WxCourseOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseOrder record);

    int insertSelective(WxCourseOrder record);

    WxCourseOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseOrder record);

    int updateByPrimaryKey(WxCourseOrder record);
}