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
public abstract class WxActivityConstants {
	
	/**
	 * 活动编码前缀
	 */
	public static final String ACTIVITY_CODE_PRIFIX = "A";
	
	/**
	 * 活动图文件大小限制
	 */
	public static final long ACTIVITY_PIC_FILE_SIZE_LIMIT = 2 * 1024 * 1024L;
	
	public enum ActivityStatus {

		DISABLE(0, "无效"),

		PUT_ON(1, "上架"),

		PUT_OFF(2, "下架");

		@Getter
		private int value;

		@Getter
		private String desc;

		ActivityStatus(int value, String desc) {
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
			ActivityStatus[] values = ActivityStatus.values();
			for (ActivityStatus status : values) {
				if (status.getValue() == value) {
					return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * 
	 * <P>是否修改了模组图片</P>
	 * @version 1.0
	 * @author 黄智聪  2018年11月1日 下午5:29:36
	 */
	public enum IsChangeActivityPic {
		N(0, "否"),

		Y(1, "是");

		@Getter
		private int value;

		@Getter
		private String desc;

		IsChangeActivityPic(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
	}
	
	/**
	 * 
	 * <P>是否屏蔽</P>
	 * @version 1.0
	 * @author 黄智聪  2018年11月1日 下午5:29:36
	 */
	public enum IsShield {
		N(0, "否"),

		Y(1, "是");

		@Getter
		private int value;

		@Getter
		private String desc;

		IsShield(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public static boolean checkStatusExist(int value) {
			IsShield[] values = IsShield.values();
            for (IsShield status : values) {
                if (status.getValue() == value) {
                    return true;
                }
            }
            return false;
        }
	}
	
	/**
	 * 
	 * <P>订单状态</P>
	 * @version 1.0
	 * @author 黄智聪  2018年11月1日 下午5:29:36
	 */
	public enum OrderStatus {
		
		NOT_PAID(1, "待支付"),

		PAID(2, "已支付"),

		CLOSED(3, "已关闭");

		@Getter
		private int value;

		@Getter
		private String desc;

		OrderStatus(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
	}

}

