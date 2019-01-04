package com.azz.wx.course.mapper;

import com.azz.wx.course.pojo.WxCourseParamRel;

public interface WxCourseParamRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseParamRel record);

    int insertSelective(WxCourseParamRel record);

    WxCourseParamRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseParamRel record);

    int updateByPrimaryKey(WxCourseParamRel record);
}