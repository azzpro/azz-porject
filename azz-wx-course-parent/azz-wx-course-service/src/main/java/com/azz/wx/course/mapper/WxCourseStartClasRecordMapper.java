package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseStartClasRecord;

@Mapper
public interface WxCourseStartClasRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseStartClasRecord record);

    int insertSelective(WxCourseStartClasRecord record);

    WxCourseStartClasRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseStartClasRecord record);

    int updateByPrimaryKey(WxCourseStartClasRecord record);
}