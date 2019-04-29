package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxActivityOrderStatus;

@Mapper
public interface WxActivityOrderStatusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxActivityOrderStatus record);

    int insertSelective(WxActivityOrderStatus record);

    WxActivityOrderStatus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxActivityOrderStatus record);

    int updateByPrimaryKey(WxActivityOrderStatus record);
}