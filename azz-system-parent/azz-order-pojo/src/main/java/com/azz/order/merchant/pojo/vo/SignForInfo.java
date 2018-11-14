/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月13日 下午4:42:40
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.pojo.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月13日 下午4:42:40
 */
@Data
public class SignForInfo {
    private String consignee;
    private Date createTime;
    private String signFileInfo;
    List<SignFileInfo> signFileInfos;
}

