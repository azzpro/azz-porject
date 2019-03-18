package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseApplyInfo;
import com.azz.wx.course.pojo.bo.EditCourseApplyIsDefaultParam;
import com.azz.wx.course.pojo.vo.CourseSignUpInfo;

@Mapper
public interface WxCourseApplyInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseApplyInfo record);

    int insertSelective(WxCourseApplyInfo record);

    WxCourseApplyInfo selectByPrimaryKey(Long id);
    
    WxCourseApplyInfo selectByCode(String code);

    int updateByPrimaryKeySelective(WxCourseApplyInfo record);

    int updateByPrimaryKey(WxCourseApplyInfo record);
    
    int getCountApplyInfo(String userCode);
    
    List<CourseSignUpInfo> getCourseListByUserCode(String userCode);
    
    CourseSignUpInfo getCourseInfoByApplyCode(String applyCode);
    
    int updateByApplyInfoCode(WxCourseApplyInfo record);
    
    int deleteApplyInfo(String applyInfoCode);
    
    int updateIsDefaultByApplyInfoCode(EditCourseApplyIsDefaultParam param);
}