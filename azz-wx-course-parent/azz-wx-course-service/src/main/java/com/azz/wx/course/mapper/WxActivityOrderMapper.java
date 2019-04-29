package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxActivityOrder;
import com.azz.wx.course.pojo.vo.ActivityPayOrderInfo;

@Mapper
public interface WxActivityOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxActivityOrder record);

    int insertSelective(WxActivityOrder record);

    WxActivityOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxActivityOrder record);

    int updateByPrimaryKey(WxActivityOrder record);
    
    ActivityPayOrderInfo getPayOrderInfo(String orderCode);

	int updateByOrderCode(WxActivityOrder orderRecord);
}