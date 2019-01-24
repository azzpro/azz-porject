package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.ClientWxUser;
import com.azz.wx.course.pojo.bo.AddClientWxUserParam;
import com.azz.wx.course.pojo.bo.AddWxBindingRecordParam;

@Mapper
public interface ClientWxUserMapper {
	
	int insert(ClientWxUser wu);
	
	ClientWxUser selectWxUserByOpenid(String openid);
	
	int insertUser(AddClientWxUserParam param);
	
	int deleteByPrimaryKey(String openid);
	
	int insertWxBindingRecord(AddWxBindingRecordParam param);
}
