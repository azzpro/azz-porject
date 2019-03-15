/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月20日 下午2:50:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;

import com.azz.core.common.QueryPage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <P>参数管理查询参数</P>
 * @version 1.0
 * @author 彭斌  2018年10月20日 下午2:50:46
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SearchPersonalOrderParam extends QueryPage{
    /**
	 * TODO
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 参数编号 用户编码
     */
	@NotBlank(message="用户编码不允许为空")
    private String userCode;
    
}

