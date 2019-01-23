package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseEvaluation;
import com.azz.wx.course.pojo.bo.SearchEvaluationInfoParam;
import com.azz.wx.course.pojo.vo.EvaluationInfo;

@Mapper
public interface WxCourseEvaluationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseEvaluation record);

    int insertSelective(WxCourseEvaluation record);

    WxCourseEvaluation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseEvaluation record);

    int updateByPrimaryKey(WxCourseEvaluation record);
    
    /**
     * 
     * <p>根据课程编码查询评价</p>
     * @param param
     * @return
     * @author 黄智聪  2019年1月21日 下午7:52:28
     */
    List<EvaluationInfo> getEvaluationInfos(SearchEvaluationInfoParam param);
    
    /**
     * 
     * <p>查询订单评价数量</p>
     * @param orderCode
     * @return
     * @author 黄智聪  2019年1月23日 下午12:38:37
     */
    int countOrderEvaluation(String orderCode);
}