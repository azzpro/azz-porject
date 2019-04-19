package com.azz.wx.course.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.azz.wx.course.pojo.WxActivityUserSignUp;

@Mapper
public interface WxActivityUserSignUpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxActivityUserSignUp record);

    int insertSelective(WxActivityUserSignUp record);

    WxActivityUserSignUp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxActivityUserSignUp record);

    int updateByPrimaryKey(WxActivityUserSignUp record);
    
    /**
     * 
     * <p>查询某个openid的报名记录数量</p>
     * @param openid
     * @param activityCode
     * @return
     * @author 黄智聪  2019年4月19日 下午5:06:56
     */
    int countSignUpRecodeByOpenid(@Param("openid")String openid, @Param("activityCode")String activityCode);
}