/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月14日 上午11:30:31
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.pojo;

import java.io.Serializable;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月14日 上午11:30:31
 */
public class Order implements Serializable{
	
	/**
	 * TODO
	 */
	private static final long serialVersionUID = -1646544899849521382L;

	private Integer id;
	
	private String code;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}

