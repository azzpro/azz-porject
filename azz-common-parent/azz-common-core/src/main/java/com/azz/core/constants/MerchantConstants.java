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
 * 商户常量类
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月19日 下午2:59:26
 */
public abstract class MerchantConstants {

	public static final String LOGIN_MERCHANT_USER = "loginMerchantUser";

	/**
	 * 营业执照文件大小限制
	 */
	public static final long BUSINESS_LICENSE_FILE_SIZE_LIMIT = 20 * 1024 * 1024L;

	/**
	 * 经营执照文件大小限制
	 */
	public static final long TRADING_CERTIFICATE_FILE_SIZE_LIMIT = 20 * 1024 * 1024L;
	
	/**
	 * 模组主图文件大小限制
	 */
	public static final long GOODS_MODULE_FILE_SIZE_LIMIT = 2 * 1024 * 1024L;
	
	/**
	 * 品牌主图文件大小限制
	 */
	public static final long GOODS_BRAND_FILE_SIZE_LIMIT = 2 * 1024 * 1024L;

	/**
	 * 
	 * <P>
	 * 资质申请状态（0：未申请 1：待审批 2：已通过 3：已拒绝）
	 * </P>
	 * 
	 * @version 1.0
	 * @author 黄智聪 2018年10月23日 上午11:57:37
	 */
	public enum QualificationApplyStatus {

		NOT_APPLY(0, "未申请"),

		PENDING(1, "待审批"),

		PASSED(2, "已通过"),

		REFUSED(3, "已拒绝");

		@Getter
		private int value;

		@Getter
		private String desc;

		QualificationApplyStatus(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
	}

	public enum DeptStatus {
		// 0无效 1有效 2禁用
		INVALID(0, "无效"),

		ENABLE(1, "启用"),

		DISABLE(2, "禁用");

		@Getter
		private int value;

		@Getter
		private String desc;

		DeptStatus(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
	}

	public enum IsMerchantRegister {
		N(0, "否"),

		Y(1, "是");

		@Getter
		private int value;

		@Getter
		private String desc;

		IsMerchantRegister(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
	}
	
	/**
	 * 
	 * <P>是否修改了模组图片</P>
	 * @version 1.0
	 * @author 黄智聪  2018年11月1日 下午5:29:36
	 */
	public enum IsChangeGoodsModulePic {
		N(0, "否"),

		Y(1, "是");

		@Getter
		private int value;

		@Getter
		private String desc;

		IsChangeGoodsModulePic(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
	}
	
	/**
	 * 
	 * <P>是否修改了品牌图片</P>
	 * @version 1.0
	 * @author 黄智聪  2018年11月1日 下午5:29:36
	 */
	public enum IsChangeGoodsBrandPic {
		N(0, "否"),

		Y(1, "是");

		@Getter
		private int value;

		@Getter
		private String desc;

		IsChangeGoodsBrandPic(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
	}

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
	
	public enum GoodsModuleStatus {

		DISABLE(0, "无效"),

		PUT_ON(1, "上架"),

		PUT_OFF(2, "下架");

		@Getter
		private int value;

		@Getter
		private String desc;

		GoodsModuleStatus(int value, String desc) {
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
			GoodsModuleStatus[] values = GoodsModuleStatus.values();
			for (GoodsModuleStatus status : values) {
				if (status.getValue() == value) {
					return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * 
	 * <P>品牌状态</P>
	 * @version 1.0
	 * @author 黄智聪  2018年11月2日 下午4:39:19
	 */
	public enum GoodsBrandStatus {

		INVALID(0, "无效"),

		VALID(1, "有效");


		@Getter
		private int value;

		@Getter
		private String desc;

		GoodsBrandStatus(int value, String desc) {
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
			GoodsBrandStatus[] values = GoodsBrandStatus.values();
			for (GoodsBrandStatus status : values) {
				if (status.getValue() == value) {
					return true;
				}
			}
			return false;
		}
	}
}
