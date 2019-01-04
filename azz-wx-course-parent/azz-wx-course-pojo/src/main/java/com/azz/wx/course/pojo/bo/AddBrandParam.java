/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午1:52:03
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.pojo.bo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月31日 下午1:52:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBrandParam {
	
	@NotBlank(message = "请输入品牌名称")
	private String brandName;
	
	@NotNull(message = "请上传品牌主图")
	private GoodsBrandPic goodsBrandPic;

	private String brandInfo;
	
	private String brandDescription;
	
	private String creator;
	
}

