/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 下午1:35:26
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.core.constants;

import com.azz.core.constants.MerchantConstants.GoodsModuleStatus;

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
	
	/**
	 * 
	 * <P>是否修改了模组图片</P>
	 * @version 1.0
	 * @author 黄智聪  2018年11月1日 下午5:29:36
	 */
	public enum IsChangeCoursePic {
		N(0, "否"),

		Y(1, "是");

		@Getter
		private int value;

		@Getter
		private String desc;

		IsChangeCoursePic(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
	}

	public enum CourseStatus {
		// 0删除 1上架 2下架
		INVALID(0, "无效"),

		PUT_ON(1, "有效"),

		PUT_OFF(2, "下架");

		@Getter
		private int value;

		@Getter
		private String desc;

		CourseStatus(int value, String desc) {
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
			CourseStatus[] values = CourseStatus.values();
			for (CourseStatus status : values) {
				if (status.getValue() == value) {
					return true;
				}
			}
			return false;
		}
	}
	
}

