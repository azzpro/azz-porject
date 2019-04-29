package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.wx.course.pojo.WxActivityEvaluation;
import com.azz.wx.course.pojo.vo.ActivityEvaluationInfo;

@Mapper
public interface WxActivityEvaluationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxActivityEvaluation record);

    int insertSelective(WxActivityEvaluation record);

    WxActivityEvaluation selectByPrimaryKey(Long id);
    
    WxActivityEvaluation selectByCode(String evaluationCode);
    
    int updateByPrimaryKeySelective(WxActivityEvaluation record);

    int updateByPrimaryKey(WxActivityEvaluation record);
    
    List<ActivityEvaluationInfo> getEvaluationInfos(@Param("activityCode")String activityCode, @Param("isShield")Integer isShield);
    
    int countActivityEvaluation(@Param("openid")String openid, @Param("activityCode")String activityCode);
    
}