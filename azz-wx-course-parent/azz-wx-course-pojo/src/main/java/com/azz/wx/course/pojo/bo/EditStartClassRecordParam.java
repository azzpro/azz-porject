/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 上午11:43:36
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月4日 上午11:43:36
 */
@Data
public class EditStartClassRecordParam {
	
	@NotBlank(message = "请选择开课记录")
	private String startClassCode;

	@NotBlank(message = "请输入开课名称")
	private String startClassName;

	@NotNull(message = "请输入课程课时")
	private Integer hours;

	@NotNull(message = "请输入开课人数")
	private Integer peopleNumber;
	
	@NotNull(message = "请选择开课时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startClassTime;

	@NotNull(message = "请输入课程价格")
    private BigDecimal price;

	@NotBlank(message = "请输入课程所在地点的房间编号")
	private String room;
	
	@NotNull(message = "缺少请求参数")
    private BigDecimal longitude;

	@NotNull(message = "缺少请求参数")
    private BigDecimal latitude;

	@NotBlank(message = "请选择开课地点")
    private String location;

	@NotBlank(message = "请选择所属课程")
	private String courseCode;
	
    private String modifier;

}

