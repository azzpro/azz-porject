/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午1:52:03
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.pojo.bo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

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
public class AddCombinationWebParam {
	
	@NotBlank(message = "请输入推荐组合名称")
	private String combinationName;
	
	@NotBlank(message = "请选择组合所属方案")
	private String caseCode;
	
	@NotNull(message = "请选择推荐组合状态 ")
	private Integer status;
	
	private String recommendReason;
	
	@NotNull(message = "请上传推荐组合主图")
	private MultipartFile combinationPicFile;
	
	private List<String> moduleCodes;

	private String creator;
	
}

