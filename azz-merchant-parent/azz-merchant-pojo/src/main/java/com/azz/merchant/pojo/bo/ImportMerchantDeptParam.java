/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月11日 下午3:17:44
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.bo;

import lombok.Data;

/**
 * <P>商户部门导入</P>
 * @version 1.0
 * @author 彭斌  2018年12月12日 上午11:37:22
 */
@Data
public class ImportMerchantDeptParam {
	
	private String base64Str;
	
	private String creator;

	private Long merchantId;
}

