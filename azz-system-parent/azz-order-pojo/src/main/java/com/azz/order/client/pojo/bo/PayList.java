/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年12月3日 下午2:51:16
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.pojo.bo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年12月3日 下午2:51:16
 */

import com.azz.core.common.QueryPage;

import lombok.Data;

@Data	
public class PayList extends QueryPage{
	
	public String param;
}

