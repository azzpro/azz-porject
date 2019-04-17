package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxActivityUserSignUp;

@Mapper
public interface WxActivityUserSignUpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxActivityUserSignUp record);

    int insertSelective(WxActivityUserSignUp record);

    WxActivityUserSignUp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxActivityUserSignUp record);

    int updateByPrimaryKey(WxActivityUserSignUp record);
}