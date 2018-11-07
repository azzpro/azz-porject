/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午2:55:20
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月31日 下午2:55:20
 */
@Data
public class ParamsData {
	
	@NotEmpty(message="参数不能为空")
	private List<Param> params;
	@NotBlank(message="分类编码不能为空")
	private String assortmentCode;
	private String creator;
	private String modifier;
	@NotBlank(message="参数编码不能为空")
	private String paramCode;
	@NotBlank(message="父参数编码不能为空")
	private String parentCode;
	
}

