/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年4月17日 下午5:54:30
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.core.constants;

import lombok.Getter;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年4月17日 下午5:54:30
 */
public abstract class WxSolicitContributionConstants {
	
	/**
	 * 征稿编码前缀
	 */
	public static final String SOLICIT_CONTRIBUTION_CODE_PRIFIX = "SC";
	
	/**
	 * 征稿图文件大小限制
	 */
	public static final long SOLICIT_CONTRIBUTION_PIC_FILE_SIZE_LIMIT = 2 * 1024 * 1024L;
	
	public enum SolicitContributionStatus {

		DISABLE(0, "无效"),

		PUT_ON(1, "上架"),

		PUT_OFF(2, "下架");

		@Getter
		private int value;

		@Getter
		private String desc;

		SolicitContributionStatus(int value, String desc) {
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
			SolicitContributionStatus[] values = SolicitContributionStatus.values();
			for (SolicitContributionStatus status : values) {
				if (status.getValue() == value) {
					return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * 
	 * <P>是否修改了征稿图片</P>
	 * @version 1.0
	 * @author 黄智聪  2018年11月1日 下午5:29:36
	 */
	public enum IsChangeSolicitContributionPic {
		N(0, "否"),

		Y(1, "是");

		@Getter
		private int value;

		@Getter
		private String desc;

		IsChangeSolicitContributionPic(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
	}
	
}

