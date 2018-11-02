/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午1:52:03
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.merchant.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * 
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月2日 下午4:18:33
 */
@Data
public class EditGoodsBrandParam {
	
	@NotBlank(message = "品牌编码不允许为空")
	private String brandCode;

	@NotBlank(message = "请输入品牌名称")
	private String brandName;

	private String brandEnglishName;

	private String brandDescription;

	// 是否换了品牌主图
	@NotNull(message = "缺少请求参数")
	private Integer isChangeGoodsBrandPic;
	
	private GoodsBrandPic goodsBrandPic;

	private String modifier;
}
