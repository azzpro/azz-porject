package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseOrderStatus;

@Mapper
public interface WxCourseOrderStatusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseOrderStatus record);

    int insertSelective(WxCourseOrderStatus record);

    WxCourseOrderStatus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseOrderStatus record);

    int updateByPrimaryKey(WxCourseOrderStatus record);
}