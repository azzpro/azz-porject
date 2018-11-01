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

	REFUSED(0, "已拒绝"),

	PENDING(1, "待审批"),
	
	PASSED(2, "已通过"),
	
	NOT_APPLY(3, "已拒绝");

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
     * <P>是否为企业认证者</P>
     * @version 1.0
     * @author 黄智聪  2018年10月29日 下午10:11:19
     */
    public enum IsEnterpriseAuthenticator {

	YES(1, "是"),

	NO(0, "否");
	
	@Getter
	private int value;

	@Getter
	private String desc;

	IsEnterpriseAuthenticator(int value, String desc) {
	    this.value = value;
	    this.desc = desc;
	}
    }
    
    public enum DeptStatus {
        // 0无效  1有效 2禁用
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
    }
}
