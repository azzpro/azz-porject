package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseOrder;
import com.azz.wx.course.pojo.bo.SearchCourseOrderParam;
import com.azz.wx.course.pojo.vo.CourseOrderInfo;
import com.azz.wx.course.pojo.vo.PayOrderInfo;

@Mapper
public interface WxCourseOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseOrder record);

    int insertSelective(WxCourseOrder record);

    WxCourseOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseOrder record);

    int updateByPrimaryKey(WxCourseOrder record);
    
    int getCountOrders(String userCode);
    
    int getCountOrdersStatus(Integer orderStatus);
    
    String getUserPhone(String userCode);
    /**
     * 
     * <p>查询待支付订单信息</p>
     * @param orderCode
     * @return
     * @author 黄智聪  2019年1月22日 下午4:36:54
     */
    PayOrderInfo getPayOrderInfo(String orderCode);
    
    /**
     * 
     * <p>查询个人课程订单列表</p>
     * @param param
     * @return
     * @author 黄智聪  2019年1月22日 下午6:12:52
     */
    List<CourseOrderInfo> getCourseOrders(SearchCourseOrderParam param);
}