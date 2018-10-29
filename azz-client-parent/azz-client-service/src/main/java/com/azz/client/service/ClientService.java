/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午1:24:28
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.client.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.client.mapper.ClientApplyMapper;
import com.azz.client.mapper.ClientDeptMapper;
import com.azz.client.mapper.ClientPermissionMapper;
import com.azz.client.mapper.ClientRoleMapper;
import com.azz.client.mapper.ClientUserCompanyAddressMapper;
import com.azz.client.mapper.ClientUserCompanyMapper;
import com.azz.client.mapper.ClientUserMapper;
import com.azz.client.mapper.ClientUserRoleMapper;
import com.azz.client.pojo.ClientApply;
import com.azz.client.pojo.ClientDept;
import com.azz.client.pojo.ClientRole;
import com.azz.client.pojo.ClientUser;
import com.azz.client.pojo.ClientUserCompany;
import com.azz.client.pojo.ClientUserCompanyAddress;
import com.azz.client.pojo.ClientUserRole;
import com.azz.client.pojo.bo.AddClientUserParam;
import com.azz.client.pojo.bo.Avatar;
import com.azz.client.pojo.bo.ChangeAvatarParam;
import com.azz.client.pojo.bo.ClientRegistParam;
import com.azz.client.pojo.bo.EditClientUserParam;
import com.azz.client.pojo.bo.EnterpriseAuthParam;
import com.azz.client.pojo.bo.LoginParam;
import com.azz.client.pojo.bo.RemoveClientUserParam;
import com.azz.client.pojo.bo.SearchClientUserParam;
import com.azz.client.pojo.bo.TradingCertificate;
import com.azz.client.pojo.vo.ClientPersonalInfo;
import com.azz.client.pojo.vo.ClientUserInfo;
import com.azz.client.pojo.vo.ClientUserPermission;
import com.azz.client.pojo.vo.LoginClientUserInfo;
import com.azz.client.pojo.vo.Menu;
import com.azz.client.pojo.vo.UploadFileInfo;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.ShiroAuthErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.ClientConstants;
import com.azz.core.constants.ClientConstants.IsEnterpriseAuthenticator;
import com.azz.core.constants.ClientConstants.QualificationApplyStatus;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.UserConstants.ClientType;
import com.azz.core.exception.BaseException;
import com.azz.core.exception.ShiroAuthException;
import com.azz.exception.JSR303ValidationException;
import com.azz.model.Password;
import com.azz.system.api.SystemImageUploadService;
import com.azz.system.sequence.api.RandomSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.PasswordHelper;
import com.azz.util.RandomStringUtils;
import com.azz.util.StringUtils;
import com.github.pagehelper.PageHelper;


/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年10月24日 下午1:24:28
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ClientService {

    @Autowired
    private ClientUserMapper clientUserMapper;
    
    @Autowired
    private ClientUserCompanyMapper clientUserCompanyMapper;
    
    @Autowired
    private ClientPermissionMapper clientPermissionMapper;

    @Autowired
    private ClientUserCompanyAddressMapper clientUserCompanyAddressMapper;
    
    @Autowired
    private ClientApplyMapper clientApplyMapper; 
    
    @Autowired
    private ClientDeptMapper clientDeptMapper;
    
    @Autowired
    private ClientRoleMapper clientRoleMapper;

    @Autowired
    private ClientUserRoleMapper clientUserRoleMapper;
    
    @Autowired
    private SystemImageUploadService systemImageUploadService;
    
    @Autowired
    private RandomSequenceService randomSequenceService;
    
    
    /**
     * 
     * <p>客户登录认证</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月23日 下午3:49:33
     */
    public JsonResult<String> loginAuth(@RequestBody LoginParam param) {
	String phoneNumber = param.getPhoneNumber();
	String password = param.getPassword();
	ClientUser clientUser = clientUserMapper.getClientUserByPhoneNumber(phoneNumber);
	if (clientUser == null) {// 无效用户
	    throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "无效用户");
	}
	boolean isRight = PasswordHelper.checkPassword(password, clientUser.getSalt(), clientUser.getPassword());
	if (!isRight) {// 与盐值加密的密码不匹配
	    throw new ShiroAuthException(ShiroAuthErrorCode.SHIRO_AUTH_ERROR_LOGIN_ERROR, "手机号或密码错误");
	}
	return JsonResult.successJsonResult();
    }
    
    /**
     * 
     * <p>获取登录客户的用户信息</p>
     * @param phoneNumber
     * @return
     * @author 黄智聪  2018年10月23日 下午4:23:06
     */
    public JsonResult<LoginClientUserInfo> getLoginClientUserInfoByPhoneNumber(String phoneNumber){
	LoginClientUserInfo info = new LoginClientUserInfo();
	ClientUserInfo clientUserInfo = clientUserMapper.getClientUserInfoByPhoneNumber(phoneNumber);
	Long clientUserCompanyId = clientUserInfo.getClientUserCompanyId();
	List<ClientUserPermission> clientUserPermissions = clientPermissionMapper.getClientUserPermissionInfoByPhoneNumber(clientUserCompanyId, phoneNumber);
	info.setClientUserInfo(clientUserInfo);
	info.setClientUserPermissions(clientUserPermissions);
	info.setMenus(generateMenuTree(clientUserCompanyId, phoneNumber));
	return JsonResult.successJsonResult(info);
    }
    
    /**
     * 
     * <p>
     * 客户注册
     * </p>
     * 
     * @param param
     * @return
     * @author 黄智聪 2018年10月22日 下午6:51:03
     */
    public JsonResult<String> clientRegist(@RequestBody ClientRegistParam param) {
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
	ClientUser clientUser = clientUserMapper.getClientUserByPhoneNumber(phoneNumber);
	if(clientUser != null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号已被注册");
	}
	String clientUserCode = randomSequenceService.getClientNumber();
	// 生成盐值加密的密码
	Password pwd = PasswordHelper.encryptPasswordByModel(password);
	ClientUser clientUserRecord = ClientUser.builder()
		.createTime(nowDate)
		.clientUserCode(clientUserCode)
		.password(pwd.getPassword())
		.phoneNumber(phoneNumber)
		.salt(pwd.getSalt())
		//.isEnterpriseAuthenticator(IsEnterpriseAuthenticator.YES.getValue())
		.remark("来自客户注册")
		.build();
	clientUserMapper.insertSelective(clientUserRecord);
	return JsonResult.successJsonResult();
    }
    
    /**
     * 
     * <p>企业认证</p>
     * @param param
     * @return
     * @author 黄智聪  2018年10月25日 下午1:40:03
     */
    public JsonResult<String> enterpriseAuth(@RequestBody EnterpriseAuthParam param){
	JSR303ValidateUtils.validate(param);
	String clientUserCode = param.getClientUserCode();
	ClientUser user = clientUserMapper.getClientUserByClientUserCode(clientUserCode);
	if(user == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户不存在");
	}
	// 校验是否已经存在待审核的申请记录
	int applyCount = clientApplyMapper.selectClientUserId(user.getId());
	if(applyCount > 0) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "请勿重复申请");
	}
	String creditCode = param.getCreditCode();
	int count = clientUserCompanyMapper.getCountByCrditCode(creditCode);
	if(count > 0) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "信用代码已存在");
	}
	List<UploadFileInfo> uploadTradingCertificateFileInfos = new ArrayList<>(3);
	// 营业执照最多3个
	List<TradingCertificate> tradingCertificates = param.getTradingCertificates();
	for (int i = 0 ; i < tradingCertificates.size(); i++) {
	    TradingCertificate tradingCertificate = tradingCertificates.get(i);
	    String originalFileName = tradingCertificate.getFileName();
	    if(StringUtils.isBlank(originalFileName)) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个营业执照文件名为空");
	    }
	    if(tradingCertificate.getFileSize() > ClientConstants.BUSINESS_LICENSE_FILE_SIZE_LIMIT) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个营业执照文件大小不能超过20M");
	    }
	    String filedata = tradingCertificate.getFileBase64Str();
	    if(StringUtils.isBlank(filedata)) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个营业执照文件内容为空");
	    }
	    int dotIndex = originalFileName.lastIndexOf(".");
	    String fileNameNoSufix = originalFileName.substring(0, dotIndex);
	    String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
	    // 新名称为文件名 + 客户编码 + 文件后缀
	    String newFileName = fileNameNoSufix + "_" + clientUserCode + "." + sufix;
	    // 图片url
	    JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix,
		    filedata, FileConstants.AZZ_CLIENT, FileConstants.AZZ_TRADING_CERTIFICATE_IMAGE_TYPE);
	    if(jr.getCode() == SystemErrorCode.SUCCESS.getCode()) {
		UploadFileInfo file = new UploadFileInfo(jr.getData(), originalFileName);
		uploadTradingCertificateFileInfos.add(file);
	    }else {
		throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE,"营业执照上传失败，请重试");
	    }
	}
	
	Date nowDate = new Date();
	ClientUserCompany clientUserCompanyRecord = ClientUserCompany.builder()
		.clientUserCode(clientUserCode)
		.companyCode(randomSequenceService.getCompanyNumber())
		.companyName(param.getCompanyName())
		.companyTel(param.getCompanyTel())
		.creditCode(creditCode)
		.createTime(nowDate)
		.creator(clientUserCode)
		.build();
	if(uploadTradingCertificateFileInfos.size() == 1) {
	    clientUserCompanyRecord.setTradingCertificateFirstFileName(uploadTradingCertificateFileInfos.get(0).getOriginalFileName());
	    clientUserCompanyRecord.setTradingCertificateFirstFileUrl(uploadTradingCertificateFileInfos.get(0).getImgUrl());
	}
	if(uploadTradingCertificateFileInfos.size() == 2) {
	    clientUserCompanyRecord.setTradingCertificateFirstFileName(uploadTradingCertificateFileInfos.get(0).getOriginalFileName());
	    clientUserCompanyRecord.setTradingCertificateFirstFileUrl(uploadTradingCertificateFileInfos.get(0).getImgUrl());
	    clientUserCompanyRecord.setTradingCertificateSecondFileName(uploadTradingCertificateFileInfos.get(1).getOriginalFileName());
	    clientUserCompanyRecord.setTradingCertificateSecondFileUrl(uploadTradingCertificateFileInfos.get(1).getImgUrl());
	}
	if(uploadTradingCertificateFileInfos.size() == 3) {
	    clientUserCompanyRecord.setTradingCertificateFirstFileName(uploadTradingCertificateFileInfos.get(0).getOriginalFileName());
	    clientUserCompanyRecord.setTradingCertificateFirstFileUrl(uploadTradingCertificateFileInfos.get(0).getImgUrl());
	    clientUserCompanyRecord.setTradingCertificateSecondFileName(uploadTradingCertificateFileInfos.get(1).getOriginalFileName());
	    clientUserCompanyRecord.setTradingCertificateSecondFileUrl(uploadTradingCertificateFileInfos.get(1).getImgUrl());
	    clientUserCompanyRecord.setTradingCertificateThirdFileName(uploadTradingCertificateFileInfos.get(2).getOriginalFileName());
	    clientUserCompanyRecord.setTradingCertificateThirdFileUrl(uploadTradingCertificateFileInfos.get(2).getImgUrl());
	}
	clientUserCompanyMapper.insertSelective(clientUserCompanyRecord);
	
	String provinceName = param.getProviceName();
	String cityName = param.getCityName();
	String areaName = param.getAreaName();
	String detailAddress = param.getDetailAddress();
	ClientUserCompanyAddress clientUserCompanyAddressRecord = ClientUserCompanyAddress.builder()
		.areaCode(param.getAreaCode())
		.areaName(areaName)
		.clientUserCompanyId(clientUserCompanyRecord.getId())
		.cityCode(param.getCityCode())
		.cityName(cityName)
		.createTime(new Date())
		.detailAddress(detailAddress)
		.provinceCode(param.getProviceCode())
		.provinceName(provinceName)
		.createTime(nowDate)
		.build();
	clientUserCompanyAddressMapper.insertSelective(clientUserCompanyAddressRecord);
	
	ClientUser clientUserRecord = ClientUser.builder()
	        .clientUserCode(clientUserCode)
	        .isEnterpriseAuthenticator(IsEnterpriseAuthenticator.YES.getValue())
	        .lastModifyTime(nowDate)
	        .build();
	clientUserMapper.updateByClientUserCode(clientUserRecord);
	
	
	// 完善资料后，需插入申请记录
	ClientApply clientApplyRecord = ClientApply.builder()
		.address(provinceName + cityName + areaName + detailAddress)
		.companyName(param.getCompanyName())
		.companyTel(param.getCompanyTel())
		.creditCode(creditCode)
		.clientUserId(user.getId())
		.clientUserName(param.getClientUserName())
		.createTime(nowDate)
		.status(QualificationApplyStatus.PENDING.getValue())
		.tradingCertificateFirstFileName(uploadTradingCertificateFileInfos.size() == 1 ? uploadTradingCertificateFileInfos.get(0).getOriginalFileName() : null)
		.tradingCertificateFirstFileUrl(uploadTradingCertificateFileInfos.size() == 1 ? uploadTradingCertificateFileInfos.get(0).getImgUrl() : null)
		.tradingCertificateSecondFileName(uploadTradingCertificateFileInfos.size() == 2  ? uploadTradingCertificateFileInfos.get(1).getOriginalFileName() : null)
		.tradingCertificateSecondFileUrl(uploadTradingCertificateFileInfos.size() == 2 ? uploadTradingCertificateFileInfos.get(1).getImgUrl() : null)
		.tradingCertificateThirdFileName(uploadTradingCertificateFileInfos.size() == 3 ? uploadTradingCertificateFileInfos.get(2).getOriginalFileName() : null)
		.tradingCertificateThirdFileUrl(uploadTradingCertificateFileInfos.size() == 3  ? uploadTradingCertificateFileInfos.get(2).getImgUrl() : null)
		.build();
	clientApplyMapper.insertSelective(clientApplyRecord);
	return JsonResult.successJsonResult();
    }
    
    public JsonResult<String> addClientUser(@RequestBody AddClientUserParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	ClientDept dept = clientDeptMapper.selectByDeptCode(param.getDeptCode());
	if (dept == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "部门不存在");
	}
	ClientRole role = clientRoleMapper.selectByRoleCode(param.getRoleCode());
	if (role == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "角色不存在");
	}
	String email = param.getEmail();
	if (!StringUtils.isBlank(email)) {
	    ClientUser user = clientUserMapper.getClientUserByEmail(email, null);
	    if (user != null) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "邮箱已存在");
	    }
	}
	String phoneNumber = param.getPhoneNumber();
	ClientUser u = clientUserMapper.getClientUserByPhoneNumberAndClientUserCode(phoneNumber, null);
	if (u != null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号已存在");
	}
	// 随机生成6位数密码
	String password = RandomStringUtils.randomNumeric(6);
	// 生成盐值加密的密码
	Password pwd = PasswordHelper.encryptPasswordByModel(password + "");
	Date nowDate = new Date();
	String creator = param.getCreator();
	String clientUserCode = randomSequenceService.getClientNumber();
	ClientUser userRecord = ClientUser.builder().createTime(nowDate).creator(creator)
		.clientDeptId(dept.getId()).email(param.getEmail()).password(pwd.getPassword()).phoneNumber(phoneNumber)
		.postName(param.getPostName()).clientUserCode(clientUserCode)
		
		.clientUserName(param.getClientUserName()).salt(pwd.getSalt()).build();
	clientUserMapper.insertSelective(userRecord);
	
	// 为新增的成员绑定公司
	Long clientUserCompanyId = param.getClientUserCompanyId();
	ClientUserCompany company = clientUserCompanyMapper.selectByPrimaryKey(clientUserCompanyId);
	ClientUserCompany clientUserCompanyRecord = ClientUserCompany.builder()
		.createTime(nowDate)
		.clientUserCode(clientUserCode)
		.companyName(company.getCompanyName())
		.companyCode(company.getCompanyCode())
		.remark("手动新增成员")
		.creator(param.getCreator())
		.companyTel(company.getCompanyTel())
		.build();
	clientUserCompanyMapper.insertSelective(clientUserCompanyRecord);
	
	// 用户与角色绑定
	ClientUserRole userRoleRecord = ClientUserRole.builder().createTime(nowDate).creator(creator)
		.clientUserId(userRecord.getId()).roleId(role.getId()).build();
	clientUserRoleMapper.insertSelective(userRoleRecord);
	
	// 发送短信通知成员 TODO
	this.sendPasswordMsg(phoneNumber, password);
	return JsonResult.successJsonResult();
    }
    
    public JsonResult<String> editClientUser(@RequestBody EditClientUserParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);

	ClientDept dept = clientDeptMapper.selectByDeptCode(param.getDeptCode());
	if (dept == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "部门不存在");
	}
	
	ClientRole role = clientRoleMapper.selectByRoleCode(param.getRoleCode());
	if (role == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "角色不存在");
	}

	String clientUserCode = param.getClientUserCode();
	ClientUser user = clientUserMapper.getClientUserByClientUserCode(clientUserCode);
	if (user == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "用户不存在");
	}
	String email = param.getEmail();
	if (!StringUtils.isBlank(email)) {
	    // 带上用户编码是为了排除当前用户以外是否存在邮箱了
	    ClientUser u = clientUserMapper.getClientUserByEmail(email, clientUserCode);
	    if (u != null) {
		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "邮箱已存在");
	    }
	}
	ClientUser u = clientUserMapper.getClientUserByPhoneNumberAndClientUserCode(param.getPhoneNumber(), clientUserCode);
	if (u != null) {
	    // 带上用户编码是为了排除当前用户以外是否存在手机了
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "手机号已存在");
	}

	Date nowDate = new Date();
	String modifier = param.getModifier();
	Long clientUserId = user.getId();
	ClientUser merchantUserRecord = ClientUser.builder().modifier(modifier).lastModifyTime(nowDate)
		.email(param.getEmail()).phoneNumber(param.getPhoneNumber())
		.postName(param.getPostName())
		.clientUserName(param.getClientUserName())
		.id(clientUserId).build();
	clientUserMapper.updateByPrimaryKeySelective(merchantUserRecord);

	// 先删除原先的用户与角色的绑定
	clientUserRoleMapper.deleteByClientUserId(clientUserId);

	// 重新对用户与角色进行新的绑定
	ClientUserRole userRoleRecord = ClientUserRole.builder().createTime(nowDate).creator(modifier)
		.clientUserId(clientUserId).roleId(role.getId()).build();
	clientUserRoleMapper.insertSelective(userRoleRecord);
	return JsonResult.successJsonResult();
    }
    
    public JsonResult<Pagination<ClientUserInfo>> getClientUserList(@RequestBody SearchClientUserParam param) {
	PageHelper.startPage(param.getPageNum(), param.getPageSize());
	List<ClientUserInfo> users = clientUserMapper.getClientUserInfoBySearchParam(param);
	return JsonResult.successJsonResult(new Pagination<>(users));
    }
    
    public JsonResult<String> removeClientUser(@RequestBody RemoveClientUserParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	String clientUserCode = param.getClientUserCode();
	// 1.将客户类型改为个人
	ClientUser clientUserRecord = ClientUser.builder().clientUserCode(clientUserCode)
		.clientType(ClientType.PERSON.getValue()).modifier(param.getModifier())
		.lastModifyTime(new Date()).build();
	clientUserMapper.updateByClientUserCode(clientUserRecord);
	
	ClientUser clientUser = clientUserMapper.getClientUserByClientUserCode(clientUserCode);
	
	// 2.删除客户与公司的绑定
	clientUserCompanyMapper.deleteByPrimaryKey(clientUser.getId());
	
	return JsonResult.successJsonResult();
    }
    
    public JsonResult<ClientUserInfo> getClientUserInfo(String clientUserCode) {
	if (StringUtils.isBlank(clientUserCode)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "用户编码不允许为空");
	}
	ClientUserInfo userInfo = clientUserMapper.getClientUserInfoByClientUserCode(clientUserCode);
	if (userInfo == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "用户不存在");
	}
	return JsonResult.successJsonResult(userInfo);
    }
    
    public JsonResult<String> changeAvatar(@RequestBody ChangeAvatarParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	Avatar avatar = param.getAvatar();
	String clientUserCode = param.getClientUserCode();
	
	String originalFileName = avatar.getFileName();
	if(StringUtils.isBlank(originalFileName)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "头像文件名为空");
	}
	if(avatar.getFileSize() > ClientConstants.AVATAR_FILE_SIZE_LIMIT) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "头像文件大小不能超过2M");
	}
	String filedata = avatar.getFileBase64Str();
	if(StringUtils.isBlank(filedata)) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "头像文件内容为空");
	}
	int dotIndex = originalFileName.lastIndexOf(".");
	String fileNameNoSufix = originalFileName.substring(0, dotIndex);
	String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
	// 新名称为文件名 + 客户编码 + 文件后缀
	String newFileName = fileNameNoSufix + "_" + clientUserCode + "." + sufix;
	
	// 图片url
	JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix,
		filedata, FileConstants.AZZ_CLIENT, FileConstants.AZZ_AVATAR_IMAGE_TYPE);
	if(jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
	    throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "头像上传失败，请重试");
	}
	
	ClientUser clientUserRecord = ClientUser.builder()
		.clientUserCode(clientUserCode)
		.clientAvatarUrl(jr.getData())
		.lastModifyTime(new Date()).build();
	clientUserMapper.updateByClientUserCode(clientUserRecord);
	return JsonResult.successJsonResult();
	
    }
    
    public JsonResult<ClientPersonalInfo> getClientPersonalInfo(String clientUserCode){
	ClientPersonalInfo info = clientUserMapper.getClientPersonalInfoByClientUserCode(clientUserCode);
	if(info == null) {
	    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户成员信息不存在");
	}
	return JsonResult.successJsonResult(info);
    }
    
    // 发送短信通知成员 TODO
    private void sendPasswordMsg(String phoneNumber, String password) {
	//您的企业将您添加为企业成员，初始密码“123456”，请登陆平台后及时修改。
    }

    /**
     * 
     * <p>
     * 根据手机号查询当前客户角色并生成菜单树
     * </p>
     * 
     * @param phoneNumber
     *            手机号
     * @return
     * @author 黄智聪 2018年10月19日 上午10:36:34
     */
    private List<Menu> generateMenuTree(Long clientUserCompanyId, String phoneNumber) {
	// 根据手机号查询所有一级菜单权限
	List<ClientUserPermission> oneMenuPermissions = clientPermissionMapper
		.getClientUserPermissionByPhoneNumberAndLevel(clientUserCompanyId, phoneNumber, 1);
	// 根据手机号查询所有二级菜单权限
	List<ClientUserPermission> twoMenuPermissions = clientPermissionMapper
		.getClientUserPermissionByPhoneNumberAndLevel(clientUserCompanyId, phoneNumber, 2);
	List<Menu> oneLevelMenus = new ArrayList<>();
	for (ClientUserPermission oneMenuPermission : oneMenuPermissions) {
	    // 一级菜单的权限编码
	    String oneLevelPermissionCode = oneMenuPermission.getPermissionCode();
	    List<Menu> twoLevelMenus = new ArrayList<>();
	    for (ClientUserPermission twoMenuPermission : twoMenuPermissions) {
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

