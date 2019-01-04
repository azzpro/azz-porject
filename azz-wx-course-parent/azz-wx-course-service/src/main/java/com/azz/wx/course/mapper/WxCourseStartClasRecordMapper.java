package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseStartClasRecord;
import com.azz.wx.course.pojo.bo.SearchStartClassRecordParam;
import com.azz.wx.course.pojo.vo.StartClassRecord;

@Mapper
public interface WxCourseStartClasRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseStartClasRecord record);

    int insertSelective(WxCourseStartClasRecord record);

    WxCourseStartClasRecord selectByPrimaryKey(Long id);
    
    WxCourseStartClasRecord selectByStartClassCode(String startClassCode);
    
    int updateByPrimaryKeySelective(WxCourseStartClasRecord record);

    int updateByPrimaryKey(WxCourseStartClasRecord record);
    
    /**
     * 
     * <p>查询课程绑定的开课数量</p>
     * @param courseCode
     * @return
     * @author 黄智聪  2019年1月4日 下午3:07:38
     */
    int countStartClassRecordByCourseCode(String courseCode);
    
    /**
     * 
     * <p>查询开课信息列表</p>
     * @param param
     * @return
     * @author 黄智聪  2019年1月4日 下午5:36:34
     */
    List<StartClassRecord> getStartClassRecords(SearchStartClassRecordParam param);
    
    /**
     * 
     * <p>查询开课信息详情</p>
     * @param startClassCode
     * @return
     * @author 黄智聪  2019年1月4日 下午6:21:47
     */
    StartClassRecord getStartClassRecordDetail(String startClassCode);
}