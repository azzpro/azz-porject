/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年4月17日 下午7:16:07
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.vo;

import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年4月17日 下午7:16:07
 */
@Data
public class SignUpInfo {
	
    private String nickname;

    private String headImageUrl;

    private String userName;

    private String phoneNumber;

    private String companyName;

    private String position;
    
    private String mainProductOrService;
    
    private String email;

    private Date signUpTime;
    
}

