package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourse;
import com.azz.wx.course.pojo.bo.SearchCourseInfoParam;
import com.azz.wx.course.pojo.vo.CourseInfo;

@Mapper
public interface WxCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourse record);

    int insertSelective(WxCourse record);

    WxCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourse record);

    int updateByPrimaryKeyWithBLOBs(WxCourse record);

    int updateByPrimaryKey(WxCourse record);
    
    /**
     * 
     * <p>查询课程列表</p>
     * @param param
     * @return
     * @author 黄智聪  2019年1月4日 上午11:31:17
     */
    List<CourseInfo> getCourseInfos(SearchCourseInfoParam param);
}