/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月11日 下午3:17:44
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.pojo.bo;

import lombok.Data;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年12月11日 下午3:17:44
 */
@Data
public class ImportMerchantUserParam {
	
	private String base64Str;
	
	private String merchantCode;
	
	private String creator;

}

