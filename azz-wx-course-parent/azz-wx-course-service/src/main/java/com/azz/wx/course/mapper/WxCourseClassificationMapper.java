package com.azz.wx.course.mapper;

import java.util.List;

import com.azz.wx.course.pojo.WxCourseClassification;
import com.azz.wx.course.pojo.bo.SearchClassificationListParam;
import com.azz.wx.course.pojo.bo.SearchSameLevelClassification;
import com.azz.wx.course.pojo.vo.ClassificationParams;
import com.azz.wx.course.pojo.vo.CourseClassification;

public interface WxCourseClassificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseClassification record);

    int insertSelective(WxCourseClassification record);

    WxCourseClassification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseClassification record);

    int updateByPrimaryKey(WxCourseClassification record);
    
    WxCourseClassification selectByClassificationCode(String classificationCode);

    List<CourseClassification> selectByParam(String param);
    
    int selectSameLevelClassification(SearchSameLevelClassification record);
    
    int countClassification(String classificationCode);
    
    List<WxCourseClassification> selectByAssortmentParentCode(String classificationParentCode);
    
    List<CourseClassification> selectParentByParam(String param);
    
    List<ClassificationParams> selectParentByAssortmentName(SearchClassificationListParam param);
    
    List<ClassificationParams> selectChild(String parentCode);
}