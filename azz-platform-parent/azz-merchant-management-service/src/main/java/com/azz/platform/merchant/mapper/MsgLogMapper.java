package com.azz.platform.merchant.mapper;


import com.azz.platform.merchant.pojo.MsgLog;

public interface MsgLogMapper {
    int deleteByPrimaryKey(Long msgId);

    int insert(MsgLog record);

    int insertSelective(MsgLog record);

    MsgLog selectByPrimaryKey(Long msgId);

    int updateByPrimaryKeySelective(MsgLog record);

    int updateByPrimaryKey(MsgLog record);
}