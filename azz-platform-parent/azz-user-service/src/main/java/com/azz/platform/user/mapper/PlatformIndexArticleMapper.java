package com.azz.platform.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.user.pojo.PlatformIndexArticle;
@Mapper
public interface PlatformIndexArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformIndexArticle record);

    int insertSelective(PlatformIndexArticle record);

    PlatformIndexArticle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformIndexArticle record);

    int updateByPrimaryKeyWithBLOBs(PlatformIndexArticle record);

    int updateByPrimaryKey(PlatformIndexArticle record);
    
    int getIndexArticleByColumnId(Long indexColumnId);
}