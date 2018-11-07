/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月7日 上午10:45:33
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月7日 上午10:45:33
 */
@Data
public class AddCaseWebParam {
    
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
    
    private String creator;
    
    @NotNull(message = "请上传主图")
    private MultipartFile caseFile;
}

