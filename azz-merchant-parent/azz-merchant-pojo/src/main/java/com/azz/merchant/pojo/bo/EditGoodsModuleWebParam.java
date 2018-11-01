/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午3:43:20
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月1日 下午3:43:20
 */
@Data
public class EditGoodsModuleWebParam {

	@NotBlank(message = "模组编码不允许为空")
	private String moduleCode;
	
	@NotBlank(message = "请选择分类")
	private String assortmentCode;
	
	@NotBlank(message = "请输入模组名称")
	private String moduleName;

	@NotNull(message = "请选择模组状态")
	private Byte moduleStatus;

	@NotNull(message = "请上传模组主图")
	private GoodsModulePic goodsModulePic;

	@NotBlank(message = "请编辑模组详情")
	private String moduleInfo;
	
	@NotBlank(message = "商户编码不允许为空")
	private String merchantCode;
	
	private String modifier;
	
}

