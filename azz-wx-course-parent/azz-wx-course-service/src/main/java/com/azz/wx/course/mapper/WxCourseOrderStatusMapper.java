package com.azz.wx.course.mapper;

import com.azz.wx.course.pojo.WxCourseOrderStatus;

public interface WxCourseOrderStatusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseOrderStatus record);

    int insertSelective(WxCourseOrderStatus record);

    WxCourseOrderStatus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseOrderStatus record);

    int updateByPrimaryKey(WxCourseOrderStatus record);
}