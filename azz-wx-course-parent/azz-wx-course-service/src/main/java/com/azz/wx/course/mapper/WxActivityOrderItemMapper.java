package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxActivityOrderItem;

@Mapper
public interface WxActivityOrderItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxActivityOrderItem record);

    int insertSelective(WxActivityOrderItem record);

    WxActivityOrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxActivityOrderItem record);

    int updateByPrimaryKey(WxActivityOrderItem record);
}