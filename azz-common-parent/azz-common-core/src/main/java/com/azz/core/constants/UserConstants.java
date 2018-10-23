/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月19日 下午2:59:26
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.core.constants;

import lombok.Getter;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月19日 下午2:59:26
 */
public abstract class UserConstants {
    
    public static final String LOGIN_USER = "loginUser"; 

    public enum UserStatus {

	DISABLE(0, "注销"),

	VALID(1, "有效"),

	INVALID(2, "禁用");

	@Getter
	private int value;

	@Getter
	private String desc;

	UserStatus(int value, String desc) {
	    this.value = value;
	    this.desc = desc;
	}

	/**
	 * 
	 * <p>
	 * 校验是否存在该状态
	 * </p>
	 * 
	 * @param value
	 * @return
	 * @author 黄智聪 2018年10月20日 上午11:29:37
	 */
	public static boolean checkStatusExist(int value) {
	    UserStatus[] values = UserStatus.values();
	    for (UserStatus userStatus : values) {
		if (userStatus.getValue() == value) {
		    return true;
		}
	    }
	    return false;
	}
    }

}
