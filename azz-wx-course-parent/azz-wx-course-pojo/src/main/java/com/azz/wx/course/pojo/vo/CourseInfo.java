/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 上午10:50:50
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
 * @author 黄智聪  2019年1月4日 上午10:50:50
 */
@Data
public class CourseInfo {

    private String courseCode;

    private String courseName;

    private String brandName;
    
    private String coursePicName;
    
    private String coursePicUrl;
    
    private Date latelyStartClassTime;
    
    private String latelyStartClassLocation;
    
    private BigDecimal minPrice;
    
    private Integer peopleNumber;

    private Integer hours;

    private Byte status;

    private Date createTime;

}

