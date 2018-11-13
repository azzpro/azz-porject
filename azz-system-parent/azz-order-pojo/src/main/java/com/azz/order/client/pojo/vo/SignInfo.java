/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午7:09:22
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.pojo.vo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月12日 下午7:09:22
 */

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class SignInfo {
	
	private String consignee;// 签收人
	
	private Date signTime;
	
	private String signFileInfo;// 签收文件信息的json字符串，后面会转换成SignFileInfo

	private List<SignFileInfo> signFileInfos;
	
}

