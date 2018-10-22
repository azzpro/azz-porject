/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:27:34
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.exception.JSR303ValidationException;
import com.azz.merchant.pojo.bo.MerchantRegistParam;
import com.azz.merchant.pojo.bo.SearchMerchantParam;
import com.azz.merchant.pojo.vo.MerchantInfo;
import com.azz.util.JSR303ValidateUtils;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月22日 上午10:27:34
 */
@Transactional
@Service
public class MerchantService{
   
    public JsonResult<Long> sendVerificationCode(String phoneNumber) {
	return null;
    }

    public JsonResult<String> merchantRegist(@RequestBody MerchantRegistParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	// 根据短信id查询验证码
	Long msgId = param.getMsgId();
	if(msgId == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "缺少短信id");
	}
	
	
	
	return null;
    }

    public JsonResult<Pagination<MerchantInfo>> getMerchantList(SearchMerchantParam param) {
	
	return null;
    }
}

