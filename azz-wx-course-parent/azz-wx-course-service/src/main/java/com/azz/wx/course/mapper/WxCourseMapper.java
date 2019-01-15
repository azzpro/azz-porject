package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourse;
import com.azz.wx.course.pojo.bo.SearchCourseInfoParam;
import com.azz.wx.course.pojo.vo.CourseDetail;
import com.azz.wx.course.pojo.vo.CourseInfo;
import com.azz.wx.course.pojo.vo.Param;

@Mapper
public interface WxCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourse record);

    int insertSelective(WxCourse record);

    WxCourse selectByPrimaryKey(Long id);
    
    WxCourse selectByCourseCode(String courseCode);

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
    
    /**
     * 
     * <p>查询课程详情</p>
     * @param courseCode
     * @return
     * @author 黄智聪  2019年1月4日 下午3:40:58
     */
    CourseDetail getCourseDetail(String courseCode);
    
    /**
     * 
     * <p>查询分类所有绑定的参数</p>
     * @param classificationCode
     * @return
     * @author 黄智聪  2019年1月4日 下午4:41:01
     */
    Param getAllParamsByClassificationCode(String classificationCode);
    
    /**
     * 
     * <p>查询课程绑定的参数</p>
     * @param classificationCode
     * @return
     * @author 黄智聪  2019年1月4日 下午4:41:01
     */
    Param getCourseParamsByCourseCode(String courseCode);
    
}