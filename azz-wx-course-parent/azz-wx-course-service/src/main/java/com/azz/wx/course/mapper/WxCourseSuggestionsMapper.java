package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseSuggestions;

@Mapper
public interface WxCourseSuggestionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseSuggestions record);

    int insertSelective(WxCourseSuggestions record);

    WxCourseSuggestions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseSuggestions record);

    int updateByPrimaryKey(WxCourseSuggestions record);
}