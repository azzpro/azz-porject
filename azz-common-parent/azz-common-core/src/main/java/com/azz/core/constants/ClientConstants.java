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
public abstract class ClientConstants {
    
    public static final String LOGIN_CLIENT_USER = "loginClientUser";
    
    /**
     * 客户头像文件大小限制
     */
    public static final long AVATAR_FILE_SIZE_LIMIT = 2 * 1024 * 1024L;
    
    /**
     * 营业执照文件大小限制
     */
    public static final long BUSINESS_LICENSE_FILE_SIZE_LIMIT = 20 * 1024 * 1024L;
    
    /**
     * 经营执照文件大小限制
     */
    public static final long TRADING_CERTIFICATE_FILE_SIZE_LIMIT = 20 * 1024 * 1024L;

    /**
     * 
     * <P>资质申请状态（0：未申请 1：待审批 2：已通过 3：已拒绝）</P>
     * @version 1.0
     * @author 黄智聪  2018年10月23日 上午11:57:37
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
    
    /**
     * 
     * <P>资质申请状态（0：未申请 1：待审批 2：已通过 3：已拒绝）</P>
     * @version 1.0
     * @author 黄智聪  2018年10月23日 上午11:57:37
     */
    public enum IsEnterpriseAuthenticator {

	YES(0, "否"),

	NO(1, "是");
	
	@Getter
	private int value;

	@Getter
	private String desc;

	IsEnterpriseAuthenticator(int value, String desc) {
	    this.value = value;
	    this.desc = desc;
	}
    }

}
