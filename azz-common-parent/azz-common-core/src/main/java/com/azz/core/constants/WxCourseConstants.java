/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 下午1:35:26
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.core.constants;

import lombok.Getter;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年1月4日 下午1:35:26
 */
public abstract class WxCourseConstants {
	
	/**
	 * 课程主图文件大小限制 2M
	 */
	public static final long COURSE_PIC_FILE_SIZE_LIMIT = 2 * 1024 * 1024L;

	public enum CourseStatus {
		// 0无效 1有效
		INVALID(0, "无效"),

		VALID(1, "有效");

		@Getter
		private int value;

		@Getter
		private String desc;

		CourseStatus(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
	}
	
}

