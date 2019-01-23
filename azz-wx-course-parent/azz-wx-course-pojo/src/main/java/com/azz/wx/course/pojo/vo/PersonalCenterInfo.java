/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月22日 下午2:47:45
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.vo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月22日 下午2:47:45
 */
@Data
public class PersonalCenterInfo {
    private String wechatId;
    private String avatarUrl;
    private String nickName;
    private Integer allOrders;
    private Integer pendingOrder;
    private Integer toBeConfirmed;
    private Integer comment;
    private String cellphoneNumber;
    private Integer signUp;
}

