package com.azz.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.system.bo.AddClientWxUserParam;
import com.azz.system.bo.AddWxBindingRecordParam;
import com.azz.system.pojo.ClientWxUser;

@Mapper
public interface ClientWxUserMapper {
	
	int insert(ClientWxUser wu);
	
	ClientWxUser selectWxUserByOpenid(String openid);
	
	int updateAvatarUrlAndNickname(@Param("url") String url,@Param("name") String name,@Param("id") String id);
	
	int insertUser(AddClientWxUserParam param);
	
	int deleteByPrimaryKey(String openid);
	
	int insertWxBindingRecord(AddWxBindingRecordParam param);
}
