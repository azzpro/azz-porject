package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseAapplyInfo;
import com.azz.wx.course.pojo.bo.EditCourseApplyIsDefaultParam;
import com.azz.wx.course.pojo.vo.CourseSignUpInfo;

@Mapper
public interface WxCourseAapplyInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseAapplyInfo record);

    int insertSelective(WxCourseAapplyInfo record);

    WxCourseAapplyInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseAapplyInfo record);

    int updateByPrimaryKey(WxCourseAapplyInfo record);
    
    int getCountApplyInfo(String userCode);
    
    List<CourseSignUpInfo> getCourseListByUserCode(String userCode);
    
    CourseSignUpInfo getCourseInfoByApplyCode(String applyCode);
    
    int updateByApplyInfoCode(WxCourseAapplyInfo record);
    
    int deleteApplyInfo(String applyInfoCode);
    
    int updateIsDefaultByApplyInfoCode(EditCourseApplyIsDefaultParam param);
}