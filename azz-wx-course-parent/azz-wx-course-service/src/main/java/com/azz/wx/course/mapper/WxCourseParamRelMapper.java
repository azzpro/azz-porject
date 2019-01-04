package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseParamRel;

@Mapper
public interface WxCourseParamRelMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByCourseCode(String courseCode);

    int insert(WxCourseParamRel record);

    int insertSelective(WxCourseParamRel record);

    WxCourseParamRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseParamRel record);

    int updateByPrimaryKey(WxCourseParamRel record);
}