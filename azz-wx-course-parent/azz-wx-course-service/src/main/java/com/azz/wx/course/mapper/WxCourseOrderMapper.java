package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.azz.wx.course.pojo.WxCourseOrder;
import com.azz.wx.course.pojo.bo.SearchCourseOrderParam;
import com.azz.wx.course.pojo.vo.CourseOrderDetail;
import com.azz.wx.course.pojo.vo.CourseOrderInfo;
import com.azz.wx.course.pojo.vo.PayOrderInfo;
import com.azz.wx.course.pojo.vo.PlatformCourseOrderDetail;
import com.azz.wx.course.pojo.vo.PlatformCourseOrderInfo;

@Mapper
public interface WxCourseOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxCourseOrder record);

    int insertSelective(WxCourseOrder record);

    WxCourseOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxCourseOrder record);
    
    int updateByOrderCode(WxCourseOrder record);

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
    
    /**
     * 
     * <p>查询课程订单详情</p>
     * @param orderCode
     * @return
     * @author 黄智聪  2019年1月23日 上午10:35:29
     */
    CourseOrderDetail getCourseOrderDetail(String orderCode);
    
    /**
     * 
     * <p>平台端查询课程订单列表</p>
     * @param param
     * @return
     * @author 黄智聪  2019年1月23日 下午5:44:34
     */
    List<PlatformCourseOrderInfo> getPlatformCourseOrders(SearchCourseOrderParam param);
    
    /**
     * 
     * <p>平台端查询课程订单详情</p>
     * @param orderCode
     * @return
     * @author 黄智聪  2019年1月23日 上午10:35:29
     */
    PlatformCourseOrderDetail getPlatformCourseOrderDetail(String orderCode);

    /**
     * 
     * <p>查询6小时未支付的订单编码集合</p>
     * @param status
     * @return
     * @author 黄智聪  2019年1月23日 下午6:43:05
     */
	List<String> getSixHoursNotPaidCourseOrderCodes(int status);
    
}