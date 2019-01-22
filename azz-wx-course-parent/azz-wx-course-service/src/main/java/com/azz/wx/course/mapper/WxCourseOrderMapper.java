package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseOrder;

@Mapper
public interface WxCourseOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseOrder record);

    int insertSelective(WxCourseOrder record);

    WxCourseOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseOrder record);

    int updateByPrimaryKey(WxCourseOrder record);
}