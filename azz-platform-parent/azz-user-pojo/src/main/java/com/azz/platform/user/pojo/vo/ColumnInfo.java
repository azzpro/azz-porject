/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月27日 下午7:56:11
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.pojo.vo;

import java.util.Date;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月27日 下午7:56:11
 */
@Data
public class ColumnInfo {
    private Long id;
    private String columnPicUrl;
    private String columnName;
    private String columnCode;
    private Integer columnType;
    private Date createTime;
}

