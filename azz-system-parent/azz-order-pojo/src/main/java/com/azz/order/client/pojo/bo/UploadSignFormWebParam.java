/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午1:52:03
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.pojo.bo;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * 
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月2日 下午4:18:38
 */
@Data
public class UploadSignFormWebParam {
    
	@NotBlank(message = "缺少请求参数")
	private String clientOrderCode;

	@NotBlank(message = "请填写收货人")
	private String consignee;

	@NotEmpty(message = "请上传签收单")
	private MultipartFile[] signFormFiles;//签收单文件集合

	private String creator;
	
}

