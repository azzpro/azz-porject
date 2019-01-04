/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 下午5:22:15
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
 * @author 黄智聪  2019年1月4日 下午5:22:15
 */
@Data
public class StartClassRecord {

    private String startClassCode;

    private Date startClassTime;

    private Integer hours;

    private Integer peopleNumber;

    private BigDecimal price;

    private String location;

    private Byte status;

}

