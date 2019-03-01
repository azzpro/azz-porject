/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月28日 下午5:36:00
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年2月28日 下午5:36:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProxyIp {
	
	private String ip;
	
	private int port;
	
	@Override
	public String toString() {
		return "[ip:"+ip + ", port:"+port+"]";
	}

}

