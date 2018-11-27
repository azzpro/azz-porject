package com.azz.platform.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.user.pojo.PlatformIndexImage;
@Mapper
public interface PlatformIndexImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformIndexImage record);

    int insertSelective(PlatformIndexImage record);

    PlatformIndexImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformIndexImage record);

    int updateByPrimaryKey(PlatformIndexImage record);
    
    int getIndexImageByColumnId(Long indexColumnId);
}