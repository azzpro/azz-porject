/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月17日 上午11:14:59
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>部门信息修改</P>
 * @version 1.0
 * @author 彭斌  2018年10月17日 上午11:14:59
 */
@Data
public class EditDeptParam {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @NotBlank(message = "部门ID不允许为空")
    private Long id;

    /**
     * 部门名称
     *
     * @mbg.generated
     */
    @NotBlank(message = "部门名称不允许为空")
    private String deptName;

    /**
     * 描述
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 状态   0无效  1有效
     *
     * @mbg.generated
     */
    @NotBlank(message = "状态不允许为空")
    private Integer status;


}

