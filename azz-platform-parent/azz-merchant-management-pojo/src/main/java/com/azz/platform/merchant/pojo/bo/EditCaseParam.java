/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月5日 下午6:46:21
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月5日 下午6:46:21
 */
@Data
public class EditCaseParam {
    
    /**
     * 方案编码
     *
     * @mbg.generated
     */
    private String caseCode;
    
    /**
     * 所属分类id
     *
     * @mbg.generated
     */
    private Long classificationId;
    
    /**
     * 方案名称
     *
     * @mbg.generated
     */
    private String caseName;
    
    
    /**
     * 方案状态（0下架 1上架）
     *
     * @mbg.generated
     */
    private Integer caseStatus;
    
    
    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;
    
    /**
     * 主图
     */
    @NotNull(message = "分类主图")
    private CasePic casePic;
    
    /**
     * 修改人
     */
    private String modifier;
    
    /**
     * 是否修改过主图 0 未修改 1修改过
     */
    private Integer isEditPic;
}

