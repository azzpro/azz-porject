/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月21日 下午5:37:20
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.vo;

import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月21日 下午5:37:20
 */
@Data
public class ActivityEvaluationInfo {
	
	private String evaluationCode;
	
    private Byte grade;
    
    private String evaluationContent;

    private String headImageUrl;
    
    private String nickname;
    
    private String openid;
    
    private Date createTime;
    
}

