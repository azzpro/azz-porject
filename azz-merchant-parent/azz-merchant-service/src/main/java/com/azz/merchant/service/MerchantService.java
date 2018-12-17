/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 上午10:27:34
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.EmailConstants;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.MerchantConstants;
import com.azz.core.constants.MerchantConstants.IsMerchantRegister;
import com.azz.core.constants.MerchantConstants.PersonalEditType;
import com.azz.core.constants.MerchantConstants.QualificationApplyStatus;
import com.azz.core.constants.MerchantConstants.UserStatus;
import com.azz.core.constants.SmsConstants;
import com.azz.core.constants.SmsConstants.SmsCode;
import com.azz.core.constants.UserConstants;
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
import com.azz.merchant.pojo.Merchant;
import com.azz.merchant.pojo.MerchantAddress;
import com.azz.merchant.pojo.MerchantApply;
import com.azz.merchant.pojo.MerchantDept;
import com.azz.merchant.pojo.MerchantRole;
import com.azz.merchant.pojo.MerchantUser;
import com.azz.merchant.pojo.MerchantUserRole;
import com.azz.merchant.pojo.bo.AddMerchantUserParam;
import com.azz.merchant.pojo.bo.BusinessLicense;
import com.azz.merchant.pojo.bo.CheckVerificationCodeParam;
import com.azz.merchant.pojo.bo.CompleteMerchantInfoParam;
import com.azz.merchant.pojo.bo.EditMerchantUserParam;
import com.azz.merchant.pojo.bo.EditPersonalInfoParam;
import com.azz.merchant.pojo.bo.EnableOrDisableOrDelMerchantUserParam;
import com.azz.merchant.pojo.bo.ImportMerchantUserParam;
import com.azz.merchant.pojo.bo.LoginParam;
import com.azz.merchant.pojo.bo.MerchantRegistParam;
import com.azz.merchant.pojo.bo.SearchMerchantDeptInfoParam;
import com.azz.merchant.pojo.bo.SearchMerchantUserParam;
import com.azz.merchant.pojo.bo.TradingCertificate;
import com.azz.merchant.pojo.vo.LoginMerchantUserInfo;
import com.azz.merchant.pojo.vo.Menu;
import com.azz.merchant.pojo.vo.MerchantInfo;
import com.azz.merchant.pojo.vo.MerchantUserInfo;
import com.azz.merchant.pojo.vo.MerchantUserPermission;
import com.azz.merchant.pojo.vo.UploadFileInfo;
import com.azz.model.Password;
import com.azz.system.api.SystemEmailService;
import com.azz.system.api.SystemImageUploadService;
import com.azz.system.api.SystemSmsSendService;
import com.azz.system.bo.MailCheck;
import com.azz.system.bo.MailCodeValidation;
import com.azz.system.bo.MailParam;
import com.azz.system.bo.SmsCheck;
import com.azz.system.bo.SmsCodeValidation;
import com.azz.system.bo.SmsParams;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.system.vo.SmsInfo;
import com.azz.util.ExcelUtils;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.PasswordHelper;
import com.azz.util.RandomStringUtils;
import com.azz.util.StringUtils;
import com.azz.util.SystemSeqUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;

import lombok.Cleanup;
import sun.misc.BASE64Decoder;

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
public class MerchantService {

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

	@Autowired
	private SystemSmsSendService systemSmsSendService;
	
	@Autowired
	private SystemEmailService systemEmailService;

	/**
	 * 
	 * <p>
	 * 商户登录认证
	 * </p>
	 * 
	 * @param param
	 * @return
	 * @author 黄智聪 2018年10月23日 下午3:49:33
	 */
	public JsonResult<String> loginAuth(@RequestBody LoginParam param) {
		String phoneNumber = param.getPhoneNumber();
		String password = param.getPassword();
		MerchantUser merchantUser = merchantUserMapper.getMerchantUserByPhoneNumber(phoneNumber);
		if (merchantUser == null) {// 无效用户
			throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "请输入正确的账号或密码");
		}
		if (merchantUser.getStatus() == UserStatus.INVALID.getValue()) {
			throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "账号已被禁用，请联系管理员解除");
		}
		boolean isRight = PasswordHelper.checkPassword(password, merchantUser.getSalt(), merchantUser.getPassword());
		if (!isRight) {// 与盐值加密的密码不匹配
			throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "请输入正确的账号或密码");
		}
		return JsonResult.successJsonResult();
	}

	/**
	 * 
	 * <p>
	 * 获取登录商户的用户信息
	 * </p>
	 * 
	 * @param phoneNumber
	 * @return
	 * @author 黄智聪 2018年10月23日 下午4:23:06
	 */
	public JsonResult<LoginMerchantUserInfo> getLoginMerchantUserInfoByPhoneNumber(
			@RequestParam("phoneNumber") String phoneNumber) {
		LoginMerchantUserInfo info = new LoginMerchantUserInfo();
		MerchantUserInfo merchantUserInfo = merchantUserMapper.getMerchantUserInfoByPhoneNumber(phoneNumber);
		String merchantCode = merchantUserInfo.getMerchantCode();
		Merchant merchant = merchantMapper.getMerchantByMerchantCode(merchantCode);
		List<MerchantUserPermission> merchantUserPermissions = merchantPermissionMapper
				.getMerchantUserPermissionInfoByPhoneNumber(merchant.getId(), phoneNumber);
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
	public JsonResult<String> sendVerificationCode(String phoneNumber) {
		SmsParams sms = new SmsParams();
		sms.setPhone(phoneNumber);
		sms.setMsgType(SmsConstants.MERCHANT_REGISTER.getMsgType());
		return systemSmsSendService.sendSmsCode(sms);
	}

	/**
	 * 
	 * <p>
	 * 校验验证码
	 * </p>
	 * 
	 * @param param
	 * @return
	 * @author 黄智聪 2018年11月26日 下午7:10:22
	 */
	public void checkVerificationCode(String phoneNumber, String verificationCode) {
		// 先校验验证码是否已失效
		SmsCodeValidation sv = new SmsCodeValidation();
		sv.setPhone(phoneNumber);
		sv.setSec(MerchantConstants.MERCHANT_REGIST_SMS_TIME_OUT);
		JsonResult<SmsInfo> jr = systemSmsSendService.checkMsgCodeTime(sv);
		if (jr.getData() == null || !jr.getData().getCode().equals(SmsCode.SUCCESS.getCode())) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "短信验证码已失效，请重新获取");
		}
		// 再校验验证码是否正确
		SmsCheck sc = new SmsCheck();
		sc.setCode(verificationCode);
		sc.setPhone(phoneNumber);
		jr = systemSmsSendService.checkMsgCode(sc);
		if (jr.getData() == null || !jr.getData().getCode().equals(SmsCode.SUCCESS.getCode())) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "验证码错误");
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
		if (merchantUser != null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号已被注册");
		}
		// 校验
		this.checkVerificationCode(phoneNumber, param.getVerificationCode());

		String merchantCode = dbSequenceService.getMerchantTenantNumber();
		String registerName = param.getRegisterName();
		Merchant merchantRecord = Merchant.builder().merchantCode(SystemSeqUtils.getSeq(merchantCode))
				.createTime(nowDate).contactPhone(phoneNumber).registeredPerson(registerName).build();
		merchantMapper.insertSelective(merchantRecord);

		String merchantUserCode = dbSequenceService.getMerchantEmployeeNumber();
		// 生成盐值加密的密码
		Password pwd = PasswordHelper.encryptPasswordByModel(password);
		MerchantUser merchantUserRecord = MerchantUser.builder().createTime(nowDate)
				.merchantCode(SystemSeqUtils.getSeq(merchantUserCode)).merchantUserName(registerName)
				.merchantUserCode(SystemSeqUtils.getSeq(merchantUserCode)).password(pwd.getPassword())
				.phoneNumber(phoneNumber).salt(pwd.getSalt()).isMerchantRegister(IsMerchantRegister.Y.getValue())
				.remark("来自商户注册").build();
		merchantUserMapper.insertSelective(merchantUserRecord);
		return JsonResult.successJsonResult(merchantCode);
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
		Merchant merchant = merchantMapper.getMerchantByMerchantCode(merchantCode);
		if (merchant == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在");
		}
		int qualificationApplyStatus = merchant.getQualificationApplyStatus();
		if (qualificationApplyStatus == QualificationApplyStatus.PASSED.getValue()
				|| qualificationApplyStatus == QualificationApplyStatus.PENDING.getValue()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户资质申请状态有误");
		}
		String creditCode = param.getCreditCode();
		merchant = merchantMapper.getMerchantByCreditCode(creditCode);
		if (merchant != null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "信用代码已存在");
		}
		String provinceName = param.getProviceName();
		String cityName = param.getCityName();
		String areaName = param.getAreaName();
		String detailAddress = param.getDetailAddress();
		Date nowDate = new Date();
		MerchantAddress merchantAddressRecord = MerchantAddress.builder().merchantCode(merchantCode)
				.areaCode(param.getAreaCode()).areaName(areaName).cityCode(param.getCityCode()).cityName(cityName)
				.createTime(nowDate).detailAddress(detailAddress).provinceCode(param.getProviceCode())
				.provinceName(provinceName).build();
		merchantAddressMapper.insertSelective(merchantAddressRecord);

		List<UploadFileInfo> uploadBusinessLicenseFileInfos = new ArrayList<>(3);
		List<UploadFileInfo> uploadTradingCertificateFileInfos = new ArrayList<>(3);

		// 经营执照目前只有1个
		List<BusinessLicense> businessLicenses = param.getBusinessLicenses();
		for (int i = 0; i < businessLicenses.size(); i++) {
			BusinessLicense businessLicense = businessLicenses.get(i);
			String originalFileName = businessLicense.getFileName();
			if (StringUtils.isBlank(originalFileName)) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
						"第" + (i + 1) + "个经营执照文件名为空");
			}
			if (businessLicense.getFileSize() > MerchantConstants.BUSINESS_LICENSE_FILE_SIZE_LIMIT) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
						"第" + (i + 1) + "个经营执照文件大小不能超过20M");
			}
			String filedata = businessLicense.getFileBase64Str();
			if (StringUtils.isBlank(filedata)) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
						"第" + (i + 1) + "个经营执照文件内容为空");
			}
			int dotIndex = originalFileName.lastIndexOf(".");
			String fileNameNoSufix = originalFileName.substring(0, dotIndex);
			String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
			// 新名称为文件名 + 商户编码 + 第几张
			String newFileName = fileNameNoSufix + "_" + merchantCode + "_" + (i + 1);
			// 图片url
			JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName,
					sufix, filedata, FileConstants.AZZ_MERCHANT, FileConstants.AZZ_BUSINESS_IMAGE_TYPE);
			if (jr.getCode() == SystemErrorCode.SUCCESS.getCode()) {
				UploadFileInfo file = new UploadFileInfo(jr.getData(), originalFileName);
				uploadBusinessLicenseFileInfos.add(file);
			} else {
				throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "经营执照上传失败，请重试");
			}
		}
		// 营业执照最多3个
		List<TradingCertificate> tradingCertificates = param.getTradingCertificates();
		for (int i = 0; i < tradingCertificates.size(); i++) {
			TradingCertificate tradingCertificate = tradingCertificates.get(i);
			String originalFileName = tradingCertificate.getFileName();
			if (StringUtils.isBlank(originalFileName)) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
						"第" + (i + 1) + "个营业执照文件名为空");
			}
			if (tradingCertificate.getFileSize() > MerchantConstants.BUSINESS_LICENSE_FILE_SIZE_LIMIT) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
						"第" + (i + 1) + "个营业执照文件大小不能超过20M");
			}
			String filedata = tradingCertificate.getFileBase64Str();
			if (StringUtils.isBlank(filedata)) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
						"第" + (i + 1) + "个营业执照文件内容为空");
			}
			int dotIndex = originalFileName.lastIndexOf(".");
			String fileNameNoSufix = originalFileName.substring(0, dotIndex);
			String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
			// 新名称为文件名 + 商户编码 + 第几张
			String newFileName = fileNameNoSufix + "_" + merchantCode + "_" + (i + 1);
			// 图片url
			JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName,
					sufix, filedata, FileConstants.AZZ_MERCHANT, FileConstants.AZZ_TRADING_CERTIFICATE_IMAGE_TYPE);
			if (jr.getCode() == SystemErrorCode.SUCCESS.getCode()) {
				UploadFileInfo file = new UploadFileInfo(jr.getData(), originalFileName);
				uploadTradingCertificateFileInfos.add(file);
			} else {
				throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "营业执照上传失败，请重试");
			}
		}

		// 完善资料后，需插入申请记录
		MerchantApply merchantApplyRecord = MerchantApply.builder().companyName(param.getCompanyName())
				.companyTel(param.getCompanyTel()).creditCode(creditCode).createTime(nowDate).merchantCode(merchantCode)
				.address(provinceName + cityName + (areaName == null ? "" : areaName) + detailAddress)
				.merchantName(param.getMerchantName()).status(QualificationApplyStatus.PENDING.getValue()).build();
		if (uploadBusinessLicenseFileInfos.size() == 1) {
			merchantApplyRecord.setBusinessLicenseFileName(uploadBusinessLicenseFileInfos.get(0).getOriginalFileName());
			merchantApplyRecord.setBusinessLicenseFileUrl(uploadBusinessLicenseFileInfos.get(0).getImgUrl());
		}
		if (uploadTradingCertificateFileInfos.size() == 1) {
			merchantApplyRecord
					.setTradingCertificateFirstFileName(uploadTradingCertificateFileInfos.get(0).getOriginalFileName());
			merchantApplyRecord.setTradingCertificateFirstFileUrl(uploadTradingCertificateFileInfos.get(0).getImgUrl());
		}
		if (uploadTradingCertificateFileInfos.size() == 2) {
			merchantApplyRecord
					.setTradingCertificateFirstFileName(uploadTradingCertificateFileInfos.get(0).getOriginalFileName());
			merchantApplyRecord.setTradingCertificateFirstFileUrl(uploadTradingCertificateFileInfos.get(0).getImgUrl());
			merchantApplyRecord.setTradingCertificateSecondFileName(
					uploadTradingCertificateFileInfos.get(1).getOriginalFileName());
			merchantApplyRecord
					.setTradingCertificateSecondFileUrl(uploadTradingCertificateFileInfos.get(1).getImgUrl());
		}
		if (uploadTradingCertificateFileInfos.size() == 3) {
			merchantApplyRecord
					.setTradingCertificateFirstFileName(uploadTradingCertificateFileInfos.get(0).getOriginalFileName());
			merchantApplyRecord.setTradingCertificateFirstFileUrl(uploadTradingCertificateFileInfos.get(0).getImgUrl());
			merchantApplyRecord.setTradingCertificateSecondFileName(
					uploadTradingCertificateFileInfos.get(1).getOriginalFileName());
			merchantApplyRecord
					.setTradingCertificateSecondFileUrl(uploadTradingCertificateFileInfos.get(1).getImgUrl());
			merchantApplyRecord
					.setTradingCertificateThirdFileName(uploadTradingCertificateFileInfos.get(2).getOriginalFileName());
			merchantApplyRecord.setTradingCertificateThirdFileUrl(uploadTradingCertificateFileInfos.get(2).getImgUrl());
		}
		merchantApplyMapper.insertSelective(merchantApplyRecord);

		Merchant record = Merchant.builder().merchantCode(merchantCode)
				.qualificationApplyStatus(QualificationApplyStatus.PENDING.getValue()).build();
		merchantMapper.updateByMerchantCode(record);

		return JsonResult.successJsonResult();
	}

	public JsonResult<String> addMerchantUser(@RequestBody AddMerchantUserParam param) {
		// 参数校验
		JSR303ValidateUtils.validate(param);

		SearchMerchantDeptInfoParam deptObj = new SearchMerchantDeptInfoParam();
		deptObj.setDeptCode(param.getDeptCode());
		Merchant merchant = merchantMapper.getMerchantByMerchantCode(param.getMerchantCode());
		if (merchant == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在");
		}
		SearchMerchantDeptInfoParam deptParm = new SearchMerchantDeptInfoParam();
		deptParm.setDeptCode(param.getDeptCode());
		deptParm.setMerchantId(merchant.getId());
		MerchantDept dept = mrchantDeptMapper.selectByDeptCode(deptParm);
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

		// 随机6位数字密码
		String randomPwd = RandomStringUtils.randomNumeric(6);

		// 生成盐值加密的密码
		Password pwd = PasswordHelper.encryptPasswordByModel(randomPwd);

		Date nowDate = new Date();
		String creator = param.getCreator();
		String code = dbSequenceService.getMerchantEmployeeNumber();
		MerchantUser userRecord = MerchantUser.builder().createTime(nowDate).creator(creator).deptId(dept.getId())
				.email(param.getEmail()).phoneNumber(phoneNumber).postName(param.getPostName())
				.merchantUserCode(SystemSeqUtils.getSeq(code)).password(pwd.getPassword()).salt(pwd.getSalt())
				.merchantUserName(param.getMerchantUserName()).merchantCode(param.getMerchantCode()).build();
		merchantUserMapper.insertSelective(userRecord);
		// 用户与角色绑定
		MerchantUserRole userRoleRecord = MerchantUserRole.builder().createTime(nowDate).creator(creator)
				.merchantUserId(userRecord.getId()).roleId(role.getId()).build();
		merchantUserRoleMapper.insertSelective(userRoleRecord);
		try {
			this.sendPasswordMsg(param.getPhoneNumber(), randomPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return JsonResult.successJsonResult();

	}

	public JsonResult<String> editMerchantUser(@RequestBody EditMerchantUserParam param) {
		// 参数校验
		JSR303ValidateUtils.validate(param);

		Merchant merchant = merchantMapper.getMerchantByMerchantCode(param.getMerchantCode());
		if (merchant == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在");
		}
		SearchMerchantDeptInfoParam deptParm = new SearchMerchantDeptInfoParam();
		deptParm.setDeptCode(param.getDeptCode());
		deptParm.setMerchantId(merchant.getId());
		MerchantDept dept = mrchantDeptMapper.selectByDeptCode(deptParm);
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
		if (user.getIsMerchantRegister() == IsMerchantRegister.Y.getValue()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "该成员不允许修改");
		}
		String email = param.getEmail();
		if (!StringUtils.isBlank(email)) {
			// 带上用户编码是为了排除当前用户以外是否存在邮箱了
			MerchantUser u = merchantUserMapper.getMerchantUserByEmail(email, merchantUserCode);
			if (u != null) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "邮箱已存在");
			}
		}
		MerchantUser u = merchantUserMapper.getMerchantUserByPhoneNumberAndMerchantUserCode(param.getPhoneNumber(),
				merchantUserCode);
		if (u != null) {
			// 带上用户编码是为了排除当前用户以外是否存在手机了
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号已存在");
		}

		Date nowDate = new Date();
		String modifier = param.getModifier();
		Long merchantUserId = user.getId();
		MerchantUser merchantUserRecord = MerchantUser.builder().modifier(modifier).lastModifyTime(nowDate)
				.deptId(dept.getId()).email(param.getEmail()).phoneNumber(param.getPhoneNumber())
				.postName(param.getPostName()).merchantUserName(param.getMerchantUserName()).id(merchantUserId).build();
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

	public JsonResult<String> enableOrDisableOrDelMerchantUser(
			@RequestBody EnableOrDisableOrDelMerchantUserParam param) {
		// 参数校验
		JSR303ValidateUtils.validate(param);
		int status = param.getStatus();
		this.checkStatusExist(status);
		MerchantUser merchantUserRecord = MerchantUser.builder().merchantUserCode(param.getMerchantUserCode())
				.status(status).modifier(param.getModifier()).lastModifyTime(new Date()).build();
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
	 * 查询商户资料
	 * </p>
	 * 
	 * @param merchantCode
	 * @return
	 * @author 黄智聪 2018年10月29日 下午1:40:26
	 */
	public JsonResult<MerchantInfo> getMerchantInfo(String merchantCode) {
		MerchantInfo info = merchantMapper.getMerchantInfoByMerchantCode(merchantCode);
		if (info == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在");
		}
		return JsonResult.successJsonResult(info);
	}

	/**
	 * 
	 * <p>
	 * 导入商户成员
	 * </p>
	 * 
	 * @param in
	 * @param merchantCode
	 * @param creator
	 * @return
	 * @throws IOException
	 * @author 黄智聪 2018年12月11日 下午3:16:08
	 */
	public JsonResult<String> importMerchantUser(@RequestBody ImportMerchantUserParam param) throws IOException {
		// 记录出错行数
		int errorRowNum = 1;
		String merchantCode = param.getMerchantCode();
		String creator = param.getCreator();

		String base64Str = param.getBase64Str();
		// 将字符串转换为byte数组
		byte[] bytes = new BASE64Decoder().decodeBuffer(base64Str);
		// 转化为输入流
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

		@Cleanup
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheetAt(0);
		Merchant merchant = merchantMapper.getMerchantByMerchantCode(merchantCode);
		if (merchant == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在");
		}
		// 手机号码以及对应的密码
		Map<String, String> phoneNumbers = new HashMap<>();
		int lastRowNum = sheet.getLastRowNum();
		if (lastRowNum == 0) {// 未填写数据
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "导入数据不可为空");
		}
		for (int i = 1; i <= lastRowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			errorRowNum++;
			if (ObjectUtils.isNotNull(row)) {
				// 获取当前行的元素信息
				ArrayList<Cell> rowCells = Lists.newArrayList(row.cellIterator());
				String merchantUserName = null;
				String phoneNumber = null;
				String email = null;
				String postName = null;
				Long roleId = null;
				Long deptId = null;
				String randomPwd = null;

				// 成员姓名校验
				if (Cell.CELL_TYPE_BLANK != rowCells.get(0).getCellType()) {
					String cell_1 = ExcelUtils.getStringValue(row.getCell(0));
					if (StringUtils.isNotBlank(cell_1)) {
						merchantUserName = cell_1;
					} else {
						throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
								"第" + errorRowNum + "行成员姓名不允许为空");
					}
				} else {
					throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
							"第" + errorRowNum + "行成员姓名不允许为空");
				}

				// 手机号校验
				if (Cell.CELL_TYPE_BLANK != rowCells.get(1).getCellType()) {
					String cell_2 = ExcelUtils.getStringValue(row.getCell(1));
					if (StringUtils.isNotBlank(cell_2)) {
						phoneNumber = cell_2;
						if (!StringUtils.isPhoneNumber(phoneNumber)) {
							throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
									"第" + errorRowNum + "行手机号格式不正确");
						}
						MerchantUser u = merchantUserMapper.getMerchantUserByPhoneNumberAndMerchantUserCode(phoneNumber,
								null);
						if (u != null) {
							throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
									"第" + errorRowNum + "行手机号已存在");
						}
					} else {
						throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
								"第" + errorRowNum + "行手机号不允许为空");
					}
				} else {
					throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
							"第" + errorRowNum + "行手机号不允许为空");
				}

				// 邮箱校验
				if (Cell.CELL_TYPE_BLANK != rowCells.get(2).getCellType()) {
					String cell_3 = ExcelUtils.getStringValue(row.getCell(2));
					if (StringUtils.isNotBlank(cell_3)) {
						email = cell_3;
						if (!StringUtils.isEmail(email)) {
							throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
									"第" + errorRowNum + "行邮箱格式不正确");
						}
						MerchantUser u = merchantUserMapper.getMerchantUserByEmail(email, null);
						if (u != null) {
							throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
									"第" + errorRowNum + "行邮箱已存在");
						}
					}
				}

				// 岗位名称校验
				if (Cell.CELL_TYPE_BLANK != rowCells.get(3).getCellType()) {
					String cell_4 = ExcelUtils.getStringValue(row.getCell(3));
					if (StringUtils.isNotBlank(cell_4)) {
						postName = cell_4;
					}
				}

				// 部门编码校验
				if (Cell.CELL_TYPE_BLANK != rowCells.get(4).getCellType()) {
					String cell_5 = ExcelUtils.getStringValue(row.getCell(4));
					if (StringUtils.isNotBlank(cell_5)) {
						String deptCode = cell_5;
						SearchMerchantDeptInfoParam deptParm = new SearchMerchantDeptInfoParam();
						deptParm.setDeptCode(deptCode);
						deptParm.setMerchantId(merchant.getId());
						MerchantDept dept = mrchantDeptMapper.selectByDeptCode(deptParm);
						if (dept == null) {
							throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
									"第" + errorRowNum + "行部门编码不存在");
						}
						deptId = dept.getId();
					} else {
						throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
								"第" + errorRowNum + "行部门编码不允许为空");
					}
				} else {
					throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
							"第" + errorRowNum + "行部门编码不允许为空");
				}

				// 角色编码校验
				if (Cell.CELL_TYPE_BLANK != rowCells.get(5).getCellType()) {
					String cell_6 = ExcelUtils.getStringValue(row.getCell(5));
					if (StringUtils.isNotBlank(cell_6)) {
						String roleCode = cell_6;
						MerchantRole role = merchantRoleMapper.selectMerchantRole(roleCode, merchant.getId());
						if (role == null) {
							throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
									"第" + errorRowNum + "行角色编码不存在");
						}
						roleId = role.getId();
					} else {
						throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
								"第" + errorRowNum + "行角色编码不允许为空");
					}
				} else {
					throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
							"第" + errorRowNum + "行角色编码不允许为空");
				}

				// 随机6位数字密码
				randomPwd = RandomStringUtils.randomNumeric(6);
				// 生成盐值加密的密码
				Password pwd = PasswordHelper.encryptPasswordByModel(randomPwd);
				// 将手机号和密码存起来
				phoneNumbers.put(phoneNumber, randomPwd);

				Date nowDate = new Date();
				String code = dbSequenceService.getMerchantEmployeeNumber();
				MerchantUser userRecord = MerchantUser.builder().createTime(nowDate).creator(creator).deptId(deptId)
						.email(email).password(pwd.getPassword()).phoneNumber(phoneNumber).postName(postName)
						.merchantUserCode(SystemSeqUtils.getSeq(code)).merchantUserName(merchantUserName)
						.merchantCode(merchantCode).salt(pwd.getSalt()).build();
				merchantUserMapper.insertSelective(userRecord);
				// 用户与角色绑定
				MerchantUserRole userRoleRecord = MerchantUserRole.builder().createTime(nowDate).creator(creator)
						.merchantUserId(userRecord.getId()).roleId(roleId).build();
				merchantUserRoleMapper.insertSelective(userRoleRecord);
			}
		}

		// 向成员发送密码
		Set<String> set = phoneNumbers.keySet();
		for (String phoneNumber : set) {
			String pwd = phoneNumbers.get(phoneNumber);
			try {
				this.sendPasswordMsg(phoneNumber, pwd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return JsonResult.successJsonResult();
	}

	// 发送短信通知成员
	private void sendPasswordMsg(String phoneNumber, String password) {
		SmsParams sms = new SmsParams();
		sms.setPhone(phoneNumber);
		sms.setMsgType(SmsConstants.ACCOUNT_CREATE_SUCCESS.getMsgType());
		systemSmsSendService.sendSmsCode(sms);
	}
	
	/**
	 * 
	 * <p>
	 * 发送修改个人信息的验证码
	 * </p>
	 * 
	 * @param phoneNumber
	 * @return
	 * @author 黄智聪 2018年10月22日 下午5:37:30
	 */
	public JsonResult<String> sendEditVerificationCode(String phoneNumber) {
		SmsParams sms = new SmsParams();
		sms.setPhone(phoneNumber);
		sms.setMsgType(SmsConstants.CHANGE_DATA.getMsgType());
		return systemSmsSendService.sendSmsCode(sms);
	}
	
	/**
	 * 
	 * <p>校验编辑个人信息的验证码</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月12日 下午3:53:25
	 */
	public JsonResult<String> checkEditVerificationCode(@RequestBody CheckVerificationCodeParam param) {
		JSR303ValidateUtils.validate(param);
		String phoneNumber = param.getPhoneNumber();
		String verificationCode = param.getVerificationCode();
		// 先校验验证码是否已失效
		SmsCodeValidation sv = new SmsCodeValidation();
		sv.setPhone(phoneNumber);
		sv.setSec(UserConstants.CHANGE_DATA_SMS_TIME_OUT);
		JsonResult<SmsInfo> jr = systemSmsSendService.checkMsgCodeTime(sv);
		if (jr.getData() == null || !jr.getData().getCode().equals(SmsCode.SUCCESS.getCode())) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "短信验证码已失效，请重新获取");
		}
		// 再校验验证码是否正确
		SmsCheck sc = new SmsCheck();
		sc.setCode(verificationCode);
		sc.setPhone(phoneNumber);
		jr = systemSmsSendService.checkMsgCode(sc);
		if (jr.getData() == null || !jr.getData().getCode().equals(SmsCode.SUCCESS.getCode())) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "验证码错误");
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>修改个人资料</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月12日 下午2:56:38
	 */
	public JsonResult<String> editPersonalInfo(@RequestBody EditPersonalInfoParam param){
		JSR303ValidateUtils.validate(param);
		MerchantUser merchantUserRecord = null;
		MerchantUser u = null;
		Date nowDate = new Date();
		switch (param.getEditType()) {
		case PersonalEditType.NAME:
			String merchantUserName = param.getUserName();
			if(StringUtils.isBlank(merchantUserName)) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "姓名不允许为空");
			}
			merchantUserRecord = MerchantUser.builder()
					.merchantUserCode(param.getModifier())
					.merchantUserName(merchantUserName)
					.modifier(param.getModifier())
					.lastModifyTime(nowDate)
					.build();
			break;
		case PersonalEditType.PHONE_NUMBER:
			String phoneNumber = param.getPhoneNumber();
			// 手机格式校验
			if(StringUtils.isBlank(phoneNumber)) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号不允许为空");
			}
			if (!StringUtils.isPhoneNumber(phoneNumber)) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "请输入正确的手机号");
			}
			// 手机是否已被商户成员所使用
			u = merchantUserMapper.getMerchantUserByPhoneNumberAndMerchantUserCode(phoneNumber, param.getModifier());
			if (u != null) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号已被使用，请更改");
			}
			
			// 校验验证码
			CheckVerificationCodeParam checkParam = new CheckVerificationCodeParam();
			checkParam.setPhoneNumber(phoneNumber);
			checkParam.setVerificationCode(param.getVerificationCode());
			this.checkEditVerificationCode(checkParam);

			merchantUserRecord = MerchantUser.builder()
					.merchantUserCode(param.getModifier())
					.phoneNumber(phoneNumber)
					.modifier(param.getModifier())
					.lastModifyTime(nowDate)
					.build();
			break;
		case PersonalEditType.EMAIL:
			String email = param.getEmail();
			// 邮箱格式校验
			if(StringUtils.isBlank(email)) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "邮箱不允许为空");
			}
			if (!StringUtils.isEmail(email)) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "请输入正确的邮箱");
			}
			// 邮箱是否已被商户成员所使用
			u = merchantUserMapper.getMerchantUserByEmail(email, param.getModifier());
			if (u != null) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "邮箱已被使用，请更改");
			}
			
			// 校验邮箱验证码
			this.checkEditEmailVerificationCode(param.getVerificationCode(), email);

			merchantUserRecord = MerchantUser.builder()
					.merchantUserCode(param.getModifier())
					.email(email)
					.modifier(param.getModifier())
					.lastModifyTime(nowDate)
					.build();
			break;
		case PersonalEditType.PASSWORD:
			String password = param.getPassword();
			String confirmPassword = param.getConfirmPassword();
			if(StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "密码或确认密码不允许为空");
			}
			// 密码与确认密码一致性校验
			if (!password.equals(confirmPassword)) {
			    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "密码与确认密码不一致");
			}
			
			// 生成盐值加密的密码
			Password pwd = PasswordHelper.encryptPasswordByModel(password);
			merchantUserRecord = MerchantUser.builder()
					.merchantUserCode(param.getModifier())
					.password(pwd.getPassword())
					.salt(pwd.getSalt())
					.modifier(param.getModifier())
					.lastModifyTime(nowDate)
					.build();
			break;
		default:
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "修改类型不存在");
		}
		merchantUserMapper.updateByMerchantUserCode(merchantUserRecord);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>
	 * 发送修改个人信息的邮箱验证码
	 * </p>
	 * 
	 * @param phoneNumber
	 * @return
	 * @author 黄智聪 2018年10月22日 下午5:37:30
	 */
	public JsonResult<String> sendEditEmailVerificationCode(String email) {
		MailParam m = new MailParam();
		m.setTo(email);
		JsonResult<SmsInfo> jr = systemEmailService.sendMail(m);
		if (jr.getData() == null || !jr.getData().getCode().equals(EmailConstants.EMAIL_SEND_SUCCESS)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "邮箱验证码发送失败，请重试");
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>校验邮箱验证码 </p>
	 * @param verificationCode
	 * @param email
	 * @author 黄智聪  2018年12月12日 下午4:27:39
	 */
	public void checkEditEmailVerificationCode(String verificationCode, String email) {
		// 先校验验证码是否已失效
		JsonResult<SmsInfo> jr = null;
		MailCodeValidation mcv = new MailCodeValidation();
		mcv.setMail(email);
		mcv.setSec(UserConstants.CHANGE_DATA_EMAIL_TIME_OUT);
		jr = systemEmailService.validationMailCodeTime(mcv);
		if (jr.getData() == null || !jr.getData().getCode().equals(EmailConstants.EMAIL_SEND_SUCCESS)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "邮箱验证码已失效，请重新获取");
		}
		// 再校验邮箱验证码是否正确
		MailCheck mailCheck = new MailCheck();
		mailCheck.setCode(verificationCode);
		mailCheck.setMail(email);
		jr = systemEmailService.checkMailCode(mailCheck);
		if (jr.getData() == null || !jr.getData().getCode().equals(EmailConstants.EMAIL_SEND_SUCCESS)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "验证码错误");
		}
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
	private void checkStatusExist(int value) {
		if (!UserStatus.checkStatusExist(value)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "用户状态不存在");
		}
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
