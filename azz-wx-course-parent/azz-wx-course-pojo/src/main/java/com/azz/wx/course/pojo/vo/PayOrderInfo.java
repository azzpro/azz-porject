/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月22日 下午4:17:08
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月22日 下午4:17:08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayOrderInfo {
	
	private String orderCode;

	private String courseName;

    private String startClassCode;
    
    private String startClassName;

    private Date startClassTime;

    private BigDecimal price;
    
    private Integer orderStatus;
    
}

