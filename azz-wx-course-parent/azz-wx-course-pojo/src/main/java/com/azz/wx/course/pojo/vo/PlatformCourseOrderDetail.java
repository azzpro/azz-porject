/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月22日 下午5:23:10
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月22日 下午5:23:10
 */
@Data
public class PlatformCourseOrderDetail {
	
	/**  微信信息 start  **/
	private String openid;
	
	private String nickName;
	
	private String wxBindingPhone;
	/**  微信信息 end  **/
	
	
	/**  订单信息 start  **/
	private String orderCode;

	private String courseName;
	
	private String classificationName;
	
	private String courseParams;
	
    private BigDecimal grandTotal;
	
	private Integer orderStatus; 
	
	private Date orderTime;
	/**  订单信息 end  **/
	
	
	/**  开课信息 start  **/
	private String startClassCode;
	
	private String startClassName;
	
	private Date startClassTime;
	
	private Integer hours;
	
	private Integer peopleNumber;
	
	private BigDecimal price;
	/**  开课信息 end  **/
	

	/**  报名信息 start  **/
	private String personName;

    private String phoneNumber;

    private String email;

    private String company;

    private String graduateSchool;
    /**  报名信息  end  **/
    
    
    /**  评价信息 start  **/
	private Byte grade;

    private String evaluationContent;
    /**  评价信息  end  **/

}

