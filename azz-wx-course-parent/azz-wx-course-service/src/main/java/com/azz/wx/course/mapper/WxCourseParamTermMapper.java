package com.azz.wx.course.mapper;

import com.azz.wx.course.pojo.WxCourseParamTerm;

public interface WxCourseParamTermMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseParamTerm record);

    int insertSelective(WxCourseParamTerm record);

    WxCourseParamTerm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseParamTerm record);

    int updateByPrimaryKey(WxCourseParamTerm record);
}