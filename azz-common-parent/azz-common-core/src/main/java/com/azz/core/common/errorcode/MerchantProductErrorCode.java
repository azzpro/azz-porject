/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月16日 下午6:57:17
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.core.common.errorcode;
/**
 * <P>商户产品模块错误码</P>
 * @version 1.0
 * @author 黄智聪  2018年10月16日 下午6:57:17
 */
public class MerchantProductErrorCode extends BaseErrorCode{
    
    public static final MerchantProductErrorCode MERCHANT_MODULE_ERROR_NOT_EXIST = new MerchantProductErrorCode(20001, "模组不存在");
    
    public static final MerchantProductErrorCode MERCHANT_PRODUCT_SAVE_ERROR = new MerchantProductErrorCode(20002, "产品保存失败");
    
    public MerchantProductErrorCode(int code, String message) {
	super(code, message);
    }

    @Override
    public String getErrorType() {
	return "平台用户异常";
    }

}

