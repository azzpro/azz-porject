package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.wx.course.pojo.WxCourseParam;
import com.azz.wx.course.pojo.bo.SearchParams;
import com.azz.wx.course.pojo.vo.Params;

@Mapper
public interface WxCourseParamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseParam record);

    int insertSelective(WxCourseParam record);

    WxCourseParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseParam record);

    int updateByPrimaryKey(WxCourseParam record);
    
    /**
     * <p>参数列表</p>
     * @param param
     * @return
     * @author 刘建麟  2018年10月31日 上午11:28:49
     */
    List<Params> searchParamsList(SearchParams param);
    
    WxCourseParam selectParamsByCode(String code);
    
    int deleteByCode(String code);
    
    int updateAssormentIdById(@Param("classCode") String classCode,@Param("pCode") String pCode);
}