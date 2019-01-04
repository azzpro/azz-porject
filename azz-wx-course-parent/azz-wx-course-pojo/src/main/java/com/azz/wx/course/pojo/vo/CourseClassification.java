/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 上午11:37:35
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月4日 上午11:37:35
 */
@Data
public class CourseClassification {
    private Long id;
    private String classificationCode;
    private String classificationName;
    private Byte sort;
    private Date createTime;
    private String classificationPicUrl;
    private String classificationParentCode;
    private Byte classificationTop;
    private List<CourseClassification> childList;
}

