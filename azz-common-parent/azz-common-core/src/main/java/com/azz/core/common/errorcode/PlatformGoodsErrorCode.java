/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月16日 下午6:57:17
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.core.common.errorcode;
/**
 * <P>平台商品模块错误码</P>
 * @version 1.0
 * @author 黄智聪  2018年10月16日 下午6:57:17
 */
public class PlatformGoodsErrorCode extends BaseErrorCode{
    
    public static final PlatformGoodsErrorCode PLATFORM_GOODS_ERROR_INVALID_NULL = new PlatformGoodsErrorCode(20001, "参数不存在");
    
    public static final PlatformGoodsErrorCode PLATFORM_GOODS_ERROR_INVALID_PARAMS = new PlatformGoodsErrorCode(20002, "参数名相同");
    
    public static final PlatformGoodsErrorCode PLATFORM_GOODS_ERROR_CODE_NOTEXIST = new PlatformGoodsErrorCode(20002, "CODE不存在");
    
    public static final PlatformGoodsErrorCode PLATFORM_GOODS_ERROR_UPDATE_ERROR = new PlatformGoodsErrorCode(20003, "更新失败");
    
    public static final PlatformGoodsErrorCode PLATFORM_GOODS_ERROR_PRODUCT_EXIST = new PlatformGoodsErrorCode(20003, "关联分类存在产品");
    
    public PlatformGoodsErrorCode(int code, String message) {
	super(code, message);
    }

    @Override
    public String getErrorType() {
	return "平台用户异常";
    }

}

