package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseParamTermValue;

@Mapper
public interface WxCourseParamTermValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseParamTermValue record);

    int insertSelective(WxCourseParamTermValue record);

    WxCourseParamTermValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseParamTermValue record);

    int updateByPrimaryKey(WxCourseParamTermValue record);
    
    int deleteValue(String[] array);
    
    List<WxCourseParamTermValue> selectValueByCode(String code);
}