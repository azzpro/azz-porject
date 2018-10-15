/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午2:51:07
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.login.pojo;
/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月15日 下午2:51:07
 */
public class Login {

	private Integer id;
	
	private String name;
	
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
	
}

