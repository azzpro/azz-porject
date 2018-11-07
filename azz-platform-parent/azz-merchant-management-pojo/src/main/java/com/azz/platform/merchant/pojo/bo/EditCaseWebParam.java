/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月7日 上午11:10:28
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月7日 上午11:10:28
 */
@Data
public class EditCaseWebParam {
    /**
     * 方案编码
     *
     * @mbg.generated
     */
    @NotBlank(message = "方案名称不允许为空")
    private String caseCode;
    
    /**
     * 所属分类id
     *
     * @mbg.generated
     */
    @NotNull(message = "分类编码不允许为空")
    private Long classificationId;
    
    /**
     * 方案名称
     *
     * @mbg.generated
     */
    @NotBlank(message = "方案名称不允许为空")
    private String caseName;
    
    
    /**
     * 方案状态（0删除 1上架 2下架）
     *
     * @mbg.generated
     */
    @NotBlank(message = "方案状态不许为空")
    private Integer caseStatus;
    
    
    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;
    
    /**
     * 修改人
     */
    private String modifier;
    
    /**
     * 是否修改过主图 0 未修改 1修改过
     */
    @NotBlank(message = "是否修改过主图")
    private Integer isEditPic;
    
    /**
     * 参数id
     */
    private List<String> paramsId;
    
    private MultipartFile caseFile;
}

