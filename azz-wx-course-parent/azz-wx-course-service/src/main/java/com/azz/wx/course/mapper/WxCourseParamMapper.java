package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseParam;

@Mapper
public interface WxCourseParamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseParam record);

    int insertSelective(WxCourseParam record);

    WxCourseParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseParam record);

    int updateByPrimaryKey(WxCourseParam record);
}