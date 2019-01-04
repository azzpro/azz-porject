package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseStartClasRecord;

@Mapper
public interface WxCourseStartClasRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseStartClasRecord record);

    int insertSelective(WxCourseStartClasRecord record);

    WxCourseStartClasRecord selectByPrimaryKey(Long id);

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
}