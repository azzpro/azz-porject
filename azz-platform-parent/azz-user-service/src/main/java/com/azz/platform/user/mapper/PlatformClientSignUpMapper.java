package com.azz.platform.user.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.platform.user.pojo.PlatformClientSignUp;
import com.azz.platform.user.pojo.bo.SearchCourseParam;
import com.azz.platform.user.pojo.vo.SignUpCourse;
@Mapper
public interface PlatformClientSignUpMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformClientSignUp record);

    int insertSelective(PlatformClientSignUp record);

    PlatformClientSignUp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformClientSignUp record);

    int updateByPrimaryKey(PlatformClientSignUp record);
    
    List<SignUpCourse> getClientSignUpList(SearchCourseParam param);
}