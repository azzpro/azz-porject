package com.azz.platform.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.user.pojo.PlatformIndexColumn;
import com.azz.platform.user.pojo.vo.ColumnInfo;
@Mapper
public interface PlatformIndexColumnMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformIndexColumn record);

    int insertSelective(PlatformIndexColumn record);

    PlatformIndexColumn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformIndexColumn record);

    int updateByPrimaryKey(PlatformIndexColumn record);
    
    List<ColumnInfo> getColumnList();
}