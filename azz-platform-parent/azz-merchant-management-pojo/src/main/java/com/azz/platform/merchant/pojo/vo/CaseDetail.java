/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月8日 上午10:13:22
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.vo;

import java.util.List;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月8日 上午10:13:22
 */
@Data
public class CaseDetail {
    /**
     * 方案编码
     *
     * @mbg.generated
     */
    private String caseCode;

    /**
     * 方案名称
     *
     * @mbg.generated
     */
    private String caseName;

    /**
     * 所属分类id
     *
     * @mbg.generated
     */
    private Long classificationId;

    /**
     * 方案状态（0删除 1上架 2下架）
     *
     * @mbg.generated
     */
    private Integer caseStatus;

    /**
     * 主图url
     *
     * @mbg.generated
     */
    private String casePicUrl;

    /**
     * 方案主图名称
     *
     * @mbg.generated
     */
    private String casePicName;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;
    
    /**
     * 选型参数集合
     */
    private List<CaseParamsList> caseParamsList;
}

