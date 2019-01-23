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
public class CourseOrderDetail {
	
	/**  订单信息 start  **/
	private String orderCode;
	
	private String courseName;
	
	private String coursePicUrl;
	
	private String coursePicName;
	
    private BigDecimal grandTotal;
	
	private Integer orderStatus; 
	
	private Date orderTime;
	
	private Date payTime;
	
	private String payNumber;
	/**  订单信息 end  **/
	
	
	/**  开课信息 start  **/
	private String startClassCode;
	
	private String startClassName;
	
	private Date startClassTime;
	
	private Integer hours;
	
	private Integer peopleNumber;
	
	private BigDecimal longitude;
	
	private BigDecimal latitude;
	/**  开课信息 end  **/
	

	/**  报名信息 start  **/
	private String personName;

    private String phoneNumber;

    private String email;

    private String company;

    private String graduateSchool;
    /**  报名信息  end  **/

}

