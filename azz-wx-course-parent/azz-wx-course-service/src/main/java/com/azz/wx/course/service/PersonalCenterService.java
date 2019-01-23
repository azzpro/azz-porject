/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月22日 下午1:52:22
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.azz.core.common.JsonResult;
import com.azz.system.mapper.ClientWxUserMapper;
import com.azz.system.pojo.ClientWxUser;
import com.azz.util.ObjectUtils;
import com.azz.wx.course.mapper.WxCourseOrderMapper;
import com.azz.wx.course.pojo.vo.PersonalCenterInfo;

/**
 * <P>个人中心</P>
 * @version 1.0
 * @author 彭斌  2019年1月22日 下午1:52:22
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class PersonalCenterService {
    
    @Autowired
    private ClientWxUserMapper clientWxUserMapper;
    
    @Autowired
    private WxCourseOrderMapper wxCourseOrderMapper;
    
    /**
     * <p>获取个人中心首页相关信息</p>
     * @param wechatId
     * @return
     * @author 彭斌  2019年1月22日 下午4:40:37
     */
    public JsonResult<PersonalCenterInfo> getPersonlCenter(String wechatId){
        PersonalCenterInfo info = null;
        
        if(ObjectUtils.isNotNull(wechatId)) {
            ClientWxUser cwu = clientWxUserMapper.selectWxUserByOpenid(wechatId);
            if(null != cwu) {
                info = new PersonalCenterInfo();
                // 客户头部基础信息
                info.setWechatId(wechatId);
                info.setAvatarUrl(cwu.getAvatarUrl());
                info.setNickName(cwu.getNickName());
                
                // 统计数值
                int allOrders = wxCourseOrderMapper.getCountOrders(cwu.getUserCode());
               // wxCourseOrderMapper.getCountOrdersStatus(orderStatus);
                
                
            }
        }
        
        return JsonResult.successJsonResult(info);
    }
}

