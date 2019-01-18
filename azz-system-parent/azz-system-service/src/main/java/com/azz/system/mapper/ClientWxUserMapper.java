package com.azz.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.system.pojo.ClientWxUser;

@Mapper
public interface ClientWxUserMapper {
	
	int insert(ClientWxUser wu);
}
