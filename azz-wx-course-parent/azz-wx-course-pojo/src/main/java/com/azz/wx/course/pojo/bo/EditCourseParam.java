/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 上午11:43:36
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月4日 上午11:43:36
 */
@Data
public class EditCourseParam {
	
	@NotBlank(message = "请选中课程")
	private String courseCode;
	
    @NotBlank(message = "请选择分类")
    private String classificationCode;

	@NotBlank(message = "请输入课程名称")
    private String courseName;

	@NotBlank(message = "请输入课程简介")
    private String courseDescription;
	
	// 是否换了模组主图
	@NotNull(message = "缺少请求参数")
	private Integer isChangeCoursePic;

    private CoursePic coursePic;

    @NotNull(message = "请选择课程状态")
    private Byte status;

    private String courseInfo;
    
    private List<CourseParam> params;

    private String modifier;

}

