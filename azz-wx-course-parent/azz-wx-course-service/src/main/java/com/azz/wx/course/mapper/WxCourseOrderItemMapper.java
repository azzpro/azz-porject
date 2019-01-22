package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseOrderItem;

@Mapper
public interface WxCourseOrderItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseOrderItem record);

    int insertSelective(WxCourseOrderItem record);

    WxCourseOrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseOrderItem record);

    int updateByPrimaryKey(WxCourseOrderItem record);
}