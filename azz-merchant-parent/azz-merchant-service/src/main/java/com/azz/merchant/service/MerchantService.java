/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:27:34
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.constants.MessageConstants;
import com.azz.core.common.constants.MessageConstants.MessagePlatform;
import com.azz.core.common.constants.MessageConstants.MessageSendStatus;
import com.azz.core.common.constants.MessageConstants.MessageType;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.MerchantConstants;
import com.azz.core.constants.MerchantConstants.QualificationApplyStatus;
import com.azz.core.constants.UserConstants.UserStatus;
import com.azz.core.exception.BaseException;
import com.azz.core.exception.ShiroAuthException;
import com.azz.exception.JSR303ValidationException;
import com.azz.merchant.mapper.MerchantAddressMapper;
import com.azz.merchant.mapper.MerchantApplyMapper;
import com.azz.merchant.mapper.MerchantDeptMapper;
import com.azz.merchant.mapper.MerchantMapper;
import com.azz.merchant.mapper.MerchantPermissionMapper;
import com.azz.merchant.mapper.MerchantRoleMapper;
import com.azz.merchant.mapper.MerchantUserMapper;
import com.azz.merchant.mapper.MerchantUserRoleMapper;
import com.azz.merchant.mapper.MsgLogMapper;
import com.azz.merchant.pojo.Merchant;
import com.azz.merchant.pojo.MerchantAddress;
import com.azz.merchant.pojo.MerchantApply;
import com.azz.merchant.pojo.MerchantDept;
import com.azz.merchant.pojo.MerchantRole;
import com.azz.merchant.pojo.MerchantUser;
import com.azz.merchant.pojo.MerchantUserRole;
import com.azz.merchant.pojo.MsgLog;
import com.azz.merchant.pojo.bo.AddMerchantUserParam;
import com.azz.merchant.pojo.bo.BusinessLicense;
import com.azz.merchant.pojo.bo.CompleteMerchantInfoParam;
import com.azz.merchant.pojo.bo.EditMerchantUserParam;
import com.azz.merchant.pojo.bo.EnableOrDisableOrDelMerchantUserParam;
import com.azz.merchant.pojo.bo.LoginParam;
import com.azz.merchant.pojo.bo.MerchantRegistParam;
import com.azz.merchant.pojo.bo.SearchMerchantUserParam;
import com.azz.merchant.pojo.bo.TradingCertificate;
import com.azz.merchant.pojo.vo.LoginMerchantUserInfo;
import com.azz.merchant.pojo.vo.Menu;
import com.azz.merchant.pojo.vo.MerchantUserInfo;
import com.azz.merchant.pojo.vo.MerchantUserPermission;
import com.azz.merchant.pojo.vo.UploadFileInfo;
import com.azz.model.Password;
import com.azz.system.api.SystemImageUploadService;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.PasswordHelper;
import com.azz.util.RandomStringUtils;
import com.azz.util.StringUtils;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * <P>
 * 商户服务类
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
    private MerchantUserMapper merchantUserMapper;
    
    @Autowired
    private MerchantPermissionMapper merchantPermissionMapper;
    
    @Autowired
    private MerchantAddressMapper merchantAddressMapper;
    
    @Autowired
    private MerchantApplyMapper merchantApplyMapper;

    @Autowired
    private MerchantRoleMapper merchantRoleMapper;

    @Autowired
    private MerchantUserRoleMapper merchantUserRoleMapper;
    
    @Autowired
    private MerchantDeptMapper mrchantDeptMapper;
    
    @Autowired
    private SystemImageUploadService systemImageUploadService;
    
    @Autowired
    private DbSequenceService dbSequenceService;
    
    
    /**
     * 
     * <p>商户登录认证</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月23日 下午3:49:33
     */
    public JsonResult<String> loginAuth(@RequestBody LoginParam param) {
	String phoneNumber = param.getPhoneNumber();
	String password = param.getPassword();
	MerchantUser merchantUser = merchantUserMapper.getMerchantUserByPhoneNumber(phoneNumber);
	if (merchantUser == null) {// 无效用户
	    throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "无效用户");
	}
	boolean isRight = PasswordHelper.checkPassword(password, merchantUser.getSalt(), merchantUser.getPassword());
	if (!isRight) {// 与盐值加密的密码不匹配
	    throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "手机号或密码错误");
	}
	return JsonResult.successJsonResult();
    }
    
    /**
     * 
     * <p>获取登录商户的用户信息</p>
     * @param phoneNumber
     * @return
     * @author 黄智聪  2018年10月23日 下午4:23:06
     */
    public JsonResult<LoginMerchantUserInfo> getLoginMerchantUserInfoByPhoneNumber(@RequestParam("phoneNumber")String phoneNumber){
	LoginMerchantUserInfo info = new LoginMerchantUserInfo();
	MerchantUserInfo merchantUserInfo = merchantUserMapper.getMerchantUserInfoByPhoneNumber(phoneNumber);
	String merchantCode = merchantUserInfo.getMerchantCode();
	Merchant merchant = merchantMapper.getMerchantByMerchantCode(merchantCode);
	List<MerchantUserPermission> merchantUserPermissions = merchantPermissionMapper.getMerchantUserPermissionInfoByPhoneNumber(merchant.getId(), phoneNumber);
	info.setMerchantUserInfo(merchantUserInfo);
	info.setMerchantUserPermissions(merchantUserPermissions);
	info.setMenus(generateMenuTree(merchantCode, phoneNumber));
	return JsonResult.successJsonResult(info);
    }
    
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
	String password = param.getPassword();
	String confirmPassword = param.getConfirmPassword();
	// 密码与确认密码一致性校验
	if (!password.equals(confirmPassword)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "密码与确认密码不一致");
	}
	String phoneNumber = param.getPhoneNumber();
	Date nowDate = new Date();
	MerchantUser merchantUser = merchantUserMapper.getMerchantUserByPhoneNumber(phoneNumber);
	if(merchantUser != null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号已被注册");
	}
	String merchantCode = dbSequenceService.getMerchantTenantNumber();
	String registerName = param.getRegisterName();
	Merchant merchantRecord = Merchant.builder()
		.merchantCode(merchantCode)
		.createTime(nowDate).contactPhone(phoneNumber)
		.registeredPerson(registerName).build();
	merchantMapper.insertSelective(merchantRecord);
	
	String merchantUserCode = dbSequenceService.getMerchantEmployeeNumber();
	// 生成盐值加密的密码
	Password pwd = PasswordHelper.encryptPasswordByModel(password);
	MerchantUser merchantUserRecord = MerchantUser.builder()
		.createTime(nowDate)
		.merchantCode(merchantCode)
		.merchantUserName(registerName)
		.merchantUserCode(merchantUserCode)
		.password(pwd.getPassword())
		.phoneNumber(phoneNumber)
		.salt(pwd.getSalt())
		.remark("来自商户注册")
		.build();
	merchantUserMapper.insertSelective(merchantUserRecord);
	return JsonResult.successJsonResult(merchantUserCode);
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
	String creditCode = param.getCreditCode();
	String merchantCode = param.getMerchantCode();
	Merchant merchant = merchantMapper.getMerchantByMerchantCode(merchantCode);
	if(merchant == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在");
	}
	merchant = merchantMapper.getMerchantByCreditCode(creditCode);
	if(merchant != null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "信用代码已存在");
	}
	String provinceName = param.getProviceName();
	String cityName = param.getCityName();
	String areaName = param.getAreaName();
	String detailAddress = param.getDetailAddress();
	Date nowDate = new Date();
	MerchantAddress merchantAddressRecord = MerchantAddress.builder()
		.merchantCode(merchantCode)
		.areaCode(param.getAreaCode())
		.areaName(areaName)
		.cityCode(param.getCityCode())
		.cityName(cityName)
		.createTime(nowDate)
		.detailAddress(detailAddress)
		.provinceCode(param.getProviceCode())
		.provinceName(provinceName)
		.build();
	merchantAddressMapper.insertSelective(merchantAddressRecord);
	
	List<UploadFileInfo> uploadBusinessLicenseFileInfos = new ArrayList<>();
	List<UploadFileInfo> uploadTradingCertificateFileInfos = new ArrayList<>();
	
	// 经营执照目前只有1个
	List<BusinessLicense> businessLicenses = param.getBusinessLicenses();
	for (int i = 0 ; i < businessLicenses.size(); i++) {
	    BusinessLicense businessLicense = businessLicenses.get(i);
	    String originalFileName = businessLicense.getFileName();
	    if(StringUtils.isBlank(originalFileName)) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个经营执照文件名为空");
	    }
	    if(businessLicense.getFileSize() > MerchantConstants.BUSINESS_LICENSE_FILE_SIZE_LIMIT) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个经营执照文件大小不能超过20M");
	    }
	    String filedata = businessLicense.getFileBase64Str();
	    if(StringUtils.isBlank(filedata)) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个经营执照文件内容为空");
	    }
	    int dotIndex = originalFileName.lastIndexOf(".");
	    String fileNameNoSufix = originalFileName.substring(0, dotIndex);
	    String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
	    // 新名称为文件名 + 商户编码 + 文件后缀
	    String newFileName = fileNameNoSufix + "_" + merchantCode + "." + sufix;
	    // 图片url
	    String imgUrl = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix,
		    filedata, FileConstants.AZZ_MERCHANT, FileConstants.AZZ_TRADING_CERTIFICATE_IMAGE_TYPE);
	    UploadFileInfo file = new UploadFileInfo(imgUrl, originalFileName);
	    uploadBusinessLicenseFileInfos.add(file);
	}
	// 营业执照最多3个
	List<TradingCertificate> tradingCertificates = param.getTradingCertificates();
	for (int i = 0 ; i < tradingCertificates.size(); i++) {
	    TradingCertificate tradingCertificate = tradingCertificates.get(i);
	    String originalFileName = tradingCertificate.getFileName();
	    if(StringUtils.isBlank(originalFileName)) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个营业执照文件名为空");
	    }
	    if(tradingCertificate.getFileSize() > MerchantConstants.BUSINESS_LICENSE_FILE_SIZE_LIMIT) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个营业执照文件大小不能超过20M");
	    }
	    String filedata = tradingCertificate.getFileBase64Str();
	    if(StringUtils.isBlank(filedata)) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个营业执照文件内容为空");
	    }
	    int dotIndex = originalFileName.lastIndexOf(".");
	    String fileNameNoSufix = originalFileName.substring(0, dotIndex);
	    String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
	    // 新名称为文件名 + 商户编码 + 文件后缀
	    String newFileName = fileNameNoSufix + "_" + merchantCode + "." + sufix;
	    // 图片url
	    String imgUrl = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix,
		    filedata, FileConstants.AZZ_MERCHANT, FileConstants.AZZ_BUSINESS_IMAGE_TYPE);
	    UploadFileInfo file = new UploadFileInfo(imgUrl, originalFileName);
	    uploadTradingCertificateFileInfos.add(file);
	}
	
	// 完善资料后，需插入申请记录
	MerchantApply merchantApplyRecord = MerchantApply.builder()
		.businessLicenseFileName(uploadBusinessLicenseFileInfos.get(0) == null ? null : uploadBusinessLicenseFileInfos.get(0).getOriginalFileName())
		.businessLicenseFileUrl(uploadBusinessLicenseFileInfos.get(0) == null ? null : uploadBusinessLicenseFileInfos.get(0).getImgUrl())
		.companyName(param.getCompanyName())
		.companyTel(param.getCompanyTel())
		.creditCode(creditCode)
		.createTime(nowDate)
		.merchantCode(merchantCode)
		.address(provinceName + cityName + areaName + detailAddress)
		.merchantName(param.getMerchantName())
		.status(QualificationApplyStatus.PENDING.getValue())
		.tradingCertificateFirstFileName(uploadTradingCertificateFileInfos.get(0) == null ? null : uploadTradingCertificateFileInfos.get(0).getOriginalFileName())
		.tradingCertificateFirstFileUrl(uploadTradingCertificateFileInfos.get(0) == null ? null : uploadTradingCertificateFileInfos.get(0).getImgUrl())
		.tradingCertificateSecondFileName(uploadTradingCertificateFileInfos.get(1) == null ? null : uploadTradingCertificateFileInfos.get(0).getOriginalFileName())
		.tradingCertificateSecondFileUrl(uploadTradingCertificateFileInfos.get(1) == null ? null : uploadTradingCertificateFileInfos.get(0).getImgUrl())
		.tradingCertificateThirdFileName(uploadTradingCertificateFileInfos.get(2) == null ? null : uploadTradingCertificateFileInfos.get(0).getOriginalFileName())
		.tradingCertificateThirdFileUrl(uploadTradingCertificateFileInfos.get(2) == null ? null : uploadTradingCertificateFileInfos.get(0).getImgUrl())
		.build();
	merchantApplyMapper.insertSelective(merchantApplyRecord);
	return JsonResult.successJsonResult();
    }
    
    public JsonResult<String> addMerchantUser(@RequestBody AddMerchantUserParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);

	String password = param.getPassword();
	String confirmPassword = param.getConfirmPassword();
	// 密码与确认密码一致性校验
	if (!password.equals(confirmPassword)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "密码与确认密码不一致");
	}
	MerchantDept dept = mrchantDeptMapper.selectByDeptCode(param.getDeptCode());
	if (dept == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "部门不存在");
	}
	MerchantRole role = merchantRoleMapper.selectByRoleCode(param.getRoleCode());
	if (role == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "角色不存在");
	}
	String email = param.getEmail();
	if (!StringUtils.isBlank(email)) {
	    MerchantUser user = merchantUserMapper.getMerchantUserByEmail(email, null);
	    if (user != null) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "邮箱已存在");
	    }
	}
	String phoneNumber = param.getPhoneNumber();
	MerchantUser u = merchantUserMapper.getMerchantUserByPhoneNumberAndMerchantUserCode(phoneNumber, null);
	if (u != null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号已存在");
	}

	// 生成盐值加密的密码
	Password pwd = PasswordHelper.encryptPasswordByModel(password);
	Date nowDate = new Date();
	String creator = param.getCreator();
	MerchantUser userRecord = MerchantUser.builder().createTime(nowDate).creator(creator)
		.deptId(dept.getId())
		.email(param.getEmail()).password(pwd.getPassword()).phoneNumber(phoneNumber)
		.postName(param.getPostName()).merchantUserCode(dbSequenceService.getMerchantEmployeeNumber())
		.merchantUserName(param.getMerchantUserName()).merchantCode(param.getMerchantCode()).salt(pwd.getSalt()).build();
	merchantUserMapper.insertSelective(userRecord);
	// 用户与角色绑定
	MerchantUserRole userRoleRecord = MerchantUserRole.builder().createTime(nowDate).creator(creator)
		.merchantUserId(userRecord.getId()).roleId(role.getId()).build();
	merchantUserRoleMapper.insertSelective(userRoleRecord);
	return JsonResult.successJsonResult();
    }

    public JsonResult<String> editMerchantUser(@RequestBody EditMerchantUserParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	String password = param.getPassword();
	String confirmPassword = param.getConfirmPassword();

	Password pwd = null;
	if (!StringUtils.isBlank(password) || !StringUtils.isBlank(confirmPassword)) {
	    // 密码与确认密码一致性校验
	    if (!password.equals(confirmPassword)) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "密码与确认密码不一致");
	    }
	    // 生成盐值加密的密码
	    pwd = PasswordHelper.encryptPasswordByModel(password);
	}
	MerchantDept dept = mrchantDeptMapper.selectByDeptCode(param.getDeptCode());
	if (dept == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "部门不存在");
	}
	
	MerchantRole role = merchantRoleMapper.selectByRoleCode(param.getRoleCode());
	if (role == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "角色不存在");
	}

	String merchantUserCode = param.getMerchantUserCode();
	MerchantUser user = merchantUserMapper.getMerchantUserByMerchantUserCode(merchantUserCode);
	if (user == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "用户不存在");
	}
	String email = param.getEmail();
	if (!StringUtils.isBlank(email)) {
	    // 带上用户编码是为了排除当前用户以外是否存在邮箱了
	    MerchantUser u = merchantUserMapper.getMerchantUserByEmail(email, merchantUserCode);
	    if (u != null) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "邮箱已存在");
	    }
	}
	MerchantUser u = merchantUserMapper.getMerchantUserByPhoneNumberAndMerchantUserCode(param.getPhoneNumber(), merchantUserCode);
	if (u != null) {
	    // 带上用户编码是为了排除当前用户以外是否存在手机了
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号已存在");
	}

	Date nowDate = new Date();
	String modifier = param.getModifier();
	Long merchantUserId = user.getId();
	MerchantUser merchantUserRecord = MerchantUser.builder().modifier(modifier).lastModifyTime(nowDate)
		.deptId(dept.getId()).email(param.getEmail()).password(pwd == null ? null : pwd.getPassword())
		.phoneNumber(param.getPhoneNumber()).postName(param.getPostName()).merchantUserName(param.getMerchantUserName())
		.salt(pwd == null ? null : pwd.getSalt()).id(merchantUserId).build();
	merchantUserMapper.updateByPrimaryKeySelective(merchantUserRecord);

	// 先删除原先的用户与角色的绑定
	merchantUserRoleMapper.deleteByMerchantUserId(merchantUserId);

	// 重新对用户与角色进行新的绑定
	MerchantUserRole userRoleRecord = MerchantUserRole.builder().createTime(nowDate).creator(modifier)
		.merchantUserId(merchantUserId).roleId(role.getId()).build();
	merchantUserRoleMapper.insertSelective(userRoleRecord);
	return JsonResult.successJsonResult();
    }

    public JsonResult<Pagination<MerchantUserInfo>> getMerchantUserList(@RequestBody SearchMerchantUserParam param) {
	PageHelper.startPage(param.getPageNum(), param.getPageSize());
	List<MerchantUserInfo> users = merchantUserMapper.getMerchantUserInfoBySearchParam(param);
	return JsonResult.successJsonResult(new Pagination<>(users));
    }
    
    public JsonResult<String> enableOrDisableOrDelMerchantUser(@RequestBody EnableOrDisableOrDelMerchantUserParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	int status = param.getStatus();
	this.checkStatusExist(status);
	MerchantUser merchantUserRecord = MerchantUser.builder().merchantUserCode(param.getMerchantUserCode()).status(status)
		.modifier(param.getModifier()).lastModifyTime(new Date()).build();
	merchantUserMapper.updateByMerchantUserCode(merchantUserRecord);
	return JsonResult.successJsonResult();
    }
    
    public JsonResult<MerchantUserInfo> getMerchantUserInfo(String merchantUserCode) {
	if (StringUtils.isBlank(merchantUserCode)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "用户编码不允许为空");
	}
	MerchantUserInfo userInfo = merchantUserMapper.getMerchantUserInfoByMerchantUserCode(merchantUserCode);
	if (userInfo == null) {
	    throw new BaseException(PlatformUserErrorCode.PLATFORM_USER_ERROR_INVALID_USER);
	}
	return JsonResult.successJsonResult(userInfo);
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
    public void checkStatusExist(int value) {
	if (!UserStatus.checkStatusExist(value)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "用户状态不存在");
	}
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
    
    /**
     * 
     * <p>
     * 根据手机号查询当前商户角色并生成菜单树
     * </p>
     * 
     * @param phoneNumber
     *            手机号
     * @return
     * @author 黄智聪 2018年10月19日 上午10:36:34
     */
    private List<Menu> generateMenuTree(String merchantCode, String phoneNumber) {
	// 根据手机号查询所有一级菜单权限
	List<MerchantUserPermission> oneMenuPermissions = merchantPermissionMapper
		.getMerchantUserPermissionByPhoneNumberAndLevel(merchantCode, phoneNumber, 1);
	// 根据手机号查询所有二级菜单权限
	List<MerchantUserPermission> twoMenuPermissions = merchantPermissionMapper
		.getMerchantUserPermissionByPhoneNumberAndLevel(merchantCode, phoneNumber, 2);
	List<Menu> oneLevelMenus = new ArrayList<>();
	for (MerchantUserPermission oneMenuPermission : oneMenuPermissions) {
	    // 一级菜单的权限编码
	    String oneLevelPermissionCode = oneMenuPermission.getPermissionCode();
	    List<Menu> twoLevelMenus = new ArrayList<>();
	    for (MerchantUserPermission twoMenuPermission : twoMenuPermissions) {
		// 二级菜单的父级权限编码
		String twoLevelPermissionCode = twoMenuPermission.getParentPermissionCode();
		if (twoLevelPermissionCode.equals(oneLevelPermissionCode)) {// 一二级菜单进行分类
		    Menu twoLevelMenu = new Menu(twoMenuPermission.getPermissionName(), twoMenuPermission.getPageUrl(),
			    twoMenuPermission.getIcon(), null);
		    twoLevelMenus.add(twoLevelMenu);
		}
	    }
	    Menu oneLevelMenu = new Menu(oneMenuPermission.getPermissionName(), oneMenuPermission.getPageUrl(),
		    oneMenuPermission.getIcon(), twoLevelMenus);
	    oneLevelMenus.add(oneLevelMenu);
	}
	return oneLevelMenus;
    }

}
