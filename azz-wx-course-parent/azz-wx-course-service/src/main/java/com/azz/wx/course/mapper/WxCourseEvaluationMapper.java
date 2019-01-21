package com.azz.wx.course.mapper;

import com.azz.wx.course.pojo.WxCourseEvaluation;

public interface WxCourseEvaluationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseEvaluation record);

    int insertSelective(WxCourseEvaluation record);

    WxCourseEvaluation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseEvaluation record);

    int updateByPrimaryKey(WxCourseEvaluation record);
}