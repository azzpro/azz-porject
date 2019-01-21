package com.azz.wx.course.mapper;

import com.azz.wx.course.pojo.WxCourseAapplyInfo;

public interface WxCourseAapplyInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseAapplyInfo record);

    int insertSelective(WxCourseAapplyInfo record);

    WxCourseAapplyInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseAapplyInfo record);

    int updateByPrimaryKey(WxCourseAapplyInfo record);
}