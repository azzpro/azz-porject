/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年3月26日 上午10:28:07
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.core.constants.poverty.relief;

import lombok.Getter;

/**
 * <P>
 * 批评常量类
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2019年3月26日 上午10:28:07
 */
public abstract class PovertyReliefConstants {
	
	/**
	 * 登录信息对应的session的key
	 */
	public static final String LOGIN_INFO = "loginInfo";

	/**
	 * 最顶层的父级地区编码
	 */
	public static final String TOP_PARENT_REGION_CODE = "0";
	
	/**
	 * 需展示的地区层级
	 */
	public static final byte DISPLAY_REGION_LEVEL = 3;

	/**
	 * 
	 * <P>用户状态</P>
	 * @version 1.0
	 * @author 黄智聪  2019年3月28日 下午5:10:58
	 */
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
		 * <p>校验是否存在该状态</p>
		 * @param value
		 * @return
		 * @author 黄智聪  2019年3月28日 下午5:09:42
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
