/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月7日 下午7:46:55
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月7日 下午7:46:55
 */
@Data
public class CaseList {
    private String caseCode;
    private String caseName;
    private Integer status;
    private String classificationName;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    private String userName;
}

