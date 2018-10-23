/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:27:34
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.constants.MessageConstants;
import com.azz.core.common.constants.MessageConstants.MessagePlatform;
import com.azz.core.common.constants.MessageConstants.MessageSendStatus;
import com.azz.core.common.constants.MessageConstants.MessageType;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.exception.JSR303ValidationException;
import com.azz.merchant.constants.MerchantConstants.QualificationApplyStatus;
import com.azz.merchant.mapper.MerchantAddressMapper;
import com.azz.merchant.mapper.MerchantMapper;
import com.azz.merchant.mapper.MsgLogMapper;
import com.azz.merchant.pojo.Merchant;
import com.azz.merchant.pojo.MerchantAddress;
import com.azz.merchant.pojo.MsgLog;
import com.azz.merchant.pojo.bo.CompleteMerchantInfoParam;
import com.azz.merchant.pojo.bo.MerchantRegistParam;
import com.azz.merchant.pojo.bo.SearchMerchantParam;
import com.azz.merchant.pojo.vo.MerchantInfo;
import com.azz.util.DateUtils;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.RandomStringUtils;
import com.azz.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月22日 上午10:27:34
 */
@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class MerchantService {

    @Autowired
    private MsgLogMapper msgLogMapper;

    @Autowired
    private MerchantMapper merchantMapper;
    
    @Autowired
    private MerchantAddressMapper merchantAddressMapper;

    /**
     * 
     * <p>
     * 发送注册验证码
     * </p>
     * 
     * @param phoneNumber
     * @return
     * @author 黄智聪 2018年10月22日 下午5:37:30
     */
    public JsonResult<Long> sendVerificationCode(String phoneNumber) {
	// 手机校验
	this.validatePhoneNumber(phoneNumber);
	// 生成短信验证码
	String verificationCode = RandomStringUtils.randomNumeric(MessageConstants.MESSAGE_VERYFICATION_CODE_LENGTH);
	boolean sendSuccess = true;
	String errorMsg = null;
	try {
	    sendPhoneMessage(phoneNumber);
	} catch (Exception e) {
	    log.debug("商户注册短信验证码发送失败", e);
	    sendSuccess = false;
	    errorMsg = e.getMessage();
	}
	MsgLog msgLogRecord = MsgLog.builder().msgContent("您正在注册平台商户账号，验证码为：" + verificationCode + "，有效期十分钟，请及时输入。")
		.msgTitle("商户注册验证码").msgPhone(Long.parseLong(phoneNumber)).msgError(errorMsg)
		.msgStatus(sendSuccess ? MessageSendStatus.SUCCESS.getValue() : MessageSendStatus.FAIL.getValue())
		.msgTime(new Date()).msgType(MessageType.REGIST.getValue()).msgPlatform(MessagePlatform.ALI.getValue())
		.build();
	msgLogMapper.insertSelective(msgLogRecord);
	// 不能抛异常，否则事务回滚
	if (!sendSuccess) {
	    JsonResult<Long> jr = new JsonResult<>();
	    jr.setCode(SystemErrorCode.SYS_ERROR_MESSAGE_SERVICE_CALL_ERROR.getCode());
	    jr.setMsg(SystemErrorCode.SYS_ERROR_MESSAGE_SERVICE_CALL_ERROR.getMessage());
	    jr.setData(-1L);
	    return jr;
	} else {
	    return JsonResult.successJsonResult(msgLogRecord.getMsgId());
	}
    }

    /**
     * 
     * <p>
     * 商户注册
     * </p>
     * 
     * @param param
     * @return
     * @author 黄智聪 2018年10月22日 下午6:51:03
     */
    public JsonResult<String> merchantRegist(@RequestBody MerchantRegistParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	// 根据短信id查询验证码
	Long msgId = param.getMsgId();
	if (msgId == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "缺少短信id");
	}
	MsgLog msgLog = msgLogMapper.selectByPrimaryKey(msgId);
	if (msgLog == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "短信验证码有误");
	}
	Date nowDate = new Date();
	if (DateUtils.timeDiffMin(msgLog.getMsgTime(), nowDate) > 10) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "短信验证码已失效");
	}
	String verificationCode = msgLog.getMsgContent();
	if (!param.getVerificationCode().equals(verificationCode)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "短信验证码不正确");
	}
	String password = param.getPassword();
	String confirmPassword = param.getConfirmPassword();
	// 密码与确认密码一致性校验
	if (!password.equals(confirmPassword)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "密码与确认密码不一致");
	}
	Merchant merchantRecord = Merchant.builder().merchantCode(System.currentTimeMillis() + "")// TODO
		.password(param.getPassword()).contactPhone(param.getPhoneNumber()).createTime(nowDate)
		.registeredPerson(param.getRegisterName()).build();
	merchantMapper.insertSelective(merchantRecord);
	return JsonResult.successJsonResult();
    }

    /**
     * 
     * <p>
     * 查询商户资质申请状态
     * </p>
     * 
     * @param merchantCode
     * @return
     * @author 黄智聪 2018年10月22日 下午8:35:59
     */
    public JsonResult<Integer> getMerchantQualificationApplyStatus(String merchantCode) {
	Merchant merchant = merchantMapper.selectByMerchantCode(merchantCode);
	if (merchant == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户记录不存在");
	}
	return JsonResult.successJsonResult(merchant.getQualificationApplyStatus());
    }

    /**
     * 
     * <p>
     * 完善商户信息
     * </p>
     * 
     * @param param
     * @return
     * @author 黄智聪 2018年10月23日 上午11:27:06
     */
    public JsonResult<String> completeMerchantInfo(@RequestBody CompleteMerchantInfoParam param) {
	JSR303ValidateUtils.validate(param);
	String merchantCode = param.getMerchantCode();
	String provinceName = param.getProviceName();
	String cityName = param.getCityName();
	String areaName = param.getAreaName();
	String detailAddress = param.getDetailAddress();
	Merchant merchantRecord = Merchant.builder().address(provinceName + cityName + areaName + detailAddress)
		.businessLicenseFileName(param.getBusinessLicenseFileName())
		.businessLicenseFileUrl(param.getBusinessLicenseFileUrl())
		.companyName(param.getCompanyName())
		.companyTel(param.getCompanytel())
		.creditCode(param.getCreditCode())
		.merchantCode(merchantCode)
		.qualificationApplyStatus(QualificationApplyStatus.PENDING.getValue())
		.tradingCertificateFirstFileName(param.getTradingCertificateFirstFileName())
		.tradingCertificateFirstFileUrl(param.getTradingCertificateFirstFileUrl())
		.tradingCertificateSecondFileName(param.getTradingCertificateSecondFileName())
		.tradingCertificateSecondFileUrl(param.getTradingCertificateSecondFileUrl())
		.tradingCertificateThirdFileName(param.getTradingCertificateThirdFileName())
		.tradingCertificateThirdFileUrl(param.getTradingCertificateThirdFileUrl())
		.build();
	merchantMapper.updateByMerchantCode(merchantRecord);
	
	MerchantAddress merchantAddressRecord = MerchantAddress.builder()
		.merchantCode(merchantCode)
		.areaCode(param.getAreaCode())
		.areaName(areaName)
		.cityCode(param.getCityCode())
		.cityName(cityName)
		.createTime(new Date())
		.detailAddress(detailAddress)
		.provinceCode(param.getProviceCode())
		.provinceName(provinceName)
		.build();
	merchantAddressMapper.insertSelective(merchantAddressRecord);
	return null;
    }

    /**
     * TODO 要挪到平台端
     * <p>
     * 商户列表查询
     * </p>
     * 
     * @param param
     * @return
     * @author 黄智聪 2018年10月22日 下午6:51:22
     */
    public JsonResult<Pagination<MerchantInfo>> getMerchantList(@RequestBody SearchMerchantParam param) {

	return null;
    }

    /**
     * 
     * <p>
     * 手机号校验
     * </p>
     * 
     * @param phoneNumber
     * @author 黄智聪 2018年10月22日 下午6:50:10
     */
    private void validatePhoneNumber(String phoneNumber) {
	if (StringUtils.isBlank(phoneNumber)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号不允许为空");
	}
	if (!StringUtils.isPhoneNumber(phoneNumber)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机格式不正确");
	}
    }

    /**
     * TODO 待实现
     * <p>
     * 发送短信
     * </p>
     * 
     * @param phoneNumber
     * @return
     * @author 黄智聪 2018年10月22日 下午6:50:22
     */
    private boolean sendPhoneMessage(String phoneNumber) {
	return false;
    }

}
