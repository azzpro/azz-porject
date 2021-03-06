package com.azz.wx.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.wx.course.pojo.WxActivity;
import com.azz.wx.course.pojo.bo.SearchActivityInfoParam;
import com.azz.wx.course.pojo.vo.ActivityInfo;
import com.azz.wx.course.pojo.vo.ClientSignUpInfo;
import com.azz.wx.course.pojo.vo.SignUpInfo;

@Mapper
public interface WxActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxActivity record);

    int insertSelective(WxActivity record);

    WxActivity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxActivity record);
    
    int updateByCodeSelective(WxActivity record);

    int updateByPrimaryKeyWithBLOBs(WxActivity record);

    int updateByPrimaryKey(WxActivity record);

	WxActivity getActivityByActivityCode(String activityCode);
	
	WxActivity getActivityWithoutContentByActivityCode(String activityCode);
	
	/**
	 * 
	 * <p>查询活动列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 下午12:08:50
	 */
	List<ActivityInfo> getActivityInfos(SearchActivityInfoParam param);
	
	/**
	 * 
	 * <p>查询活动详情</p>
	 * @param activityCode
	 * @return
	 * @author 黄智聪  2019年4月17日 下午7:20:20
	 */
	ActivityInfo getActivityInfoByActivityCode(@Param("activityCode")String activityCode, @Param("openid")String openid);
	
	/**
	 * 
	 * <p>查询活动报名人数列表</p>
	 * @param activityCode
	 * @return
	 * @author 黄智聪  2019年4月17日 下午7:20:20
	 */
	List<SignUpInfo> getActivitySignUpInfoByActivityCode(String activityCode);
	
	/**
	 * 
	 * <p>查询活动报名人数列表</p>
	 * @param activityCode
	 * @return
	 * @author 黄智聪  2019年4月17日 下午7:20:20
	 */
	List<ClientSignUpInfo> getActivityClientSignUpInfoByActivityCode(String activityCode);

}