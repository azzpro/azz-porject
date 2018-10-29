package com.azz.platform.merchant.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.merchant.common.constants.AuditConstants;
import com.azz.platform.merchant.mapper.MerchantApplyMapper;
import com.azz.platform.merchant.mapper.MerchantMapper;
import com.azz.platform.merchant.mapper.MerchantPermissionMapper;
import com.azz.platform.merchant.mapper.MerchantRoleMapper;
import com.azz.platform.merchant.mapper.MerchantRolePermissionMapper;
import com.azz.platform.merchant.mapper.MerchantUserMapper;
import com.azz.platform.merchant.mapper.MerchantUserRoleMapper;
import com.azz.platform.merchant.pojo.Merchant;
import com.azz.platform.merchant.pojo.MerchantApply;
import com.azz.platform.merchant.pojo.MerchantRole;
import com.azz.platform.merchant.pojo.MerchantRolePermission;
import com.azz.platform.merchant.pojo.MerchantUser;
import com.azz.platform.merchant.pojo.MerchantUserRole;
import com.azz.platform.merchant.pojo.bo.AuditParam;
import com.azz.platform.merchant.pojo.vo.Permission;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;

/**
 * <P>
 * 审核企业信息
 * </P>
 * 
 * @version 1.0
 * @author 彭斌 2018年10月20日 下午2:54:40
 */
@Transactional(rollbackFor=Exception.class)
@Service
public class AuditService{

    @Autowired
    MerchantApplyMapper merchantApplyMapper;

    @Autowired
    MerchantMapper merchantMapper;
    
    @Autowired
    MerchantUserMapper merchantUserMapper;
    
    @Autowired
    MerchantPermissionMapper merchantPermissionMapper;
    
    @Autowired
    MerchantRoleMapper merchantRoleMapper;
    
    @Autowired
    MerchantRolePermissionMapper merchantRolePermissionMapper;
    
    @Autowired
    MerchantUserRoleMapper merchantUserRoleMapper;
    
    @Autowired
    private DbSequenceService dbSequenceService;

    public JsonResult<String> auditEnterprise(@RequestBody AuditParam param) {
        JSR303ValidateUtils.validate(param);

        // 校验商户编码
        MerchantApply maObj = merchantApplyMapper.selectByCodeAndStatus(param.getMerchantCode());

        if (ObjectUtils.isNull(maObj)) {
            // 商户不存在无法审核
            throw new BaseException(PlatformUserErrorCode.PLATFORM_MERCHANT_ERROR_NO_EXIST);
        }

        if (!AuditConstants.AuditStatus.checkStatusExist(param.getStatus())) {
            // 审核状态不存在
            throw new BaseException(
                    PlatformUserErrorCode.PLATFORM_MERCHANT_AUDIT_STATUS_ERROR_NO_EXIST);
        }

        maObj.setStatus(param.getStatus());
        maObj.setAuditor(param.getAuditor());
        maObj.setAuditorTime(new Date());
        // 处理申请资料审批结果
        merchantApplyMapper.updateByPrimaryKeySelective(maObj);

        Merchant merchant = merchantMapper.selectByCode(maObj.getMerchantCode());

        if(merchant == null) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在");
        }
        if (param.getStatus().equals(AuditConstants.AuditStatus.PASSED.getValue())) {
            // 审核通过
            merchant.setQualificationApplyStatus(AuditConstants.AuditStatus.PASSED.getValue());
        } else if (param.getStatus().equals(AuditConstants.AuditStatus.REFUSED.getValue())) {
            // 审核拒绝
            merchant.setQualificationApplyStatus(AuditConstants.AuditStatus.REFUSED.getValue());
        }
        merchant.setAuditor(param.getAuditor());
        merchant.setMerchantName(maObj.getMerchantName());
        merchant.setLegalPersonName(maObj.getLegalPersonName());
        merchant.setLegalPersonIdCard(maObj.getLegalPersonName());
        merchant.setCompanyName(maObj.getCompanyName());
        merchant.setCreditCode(maObj.getCreditCode());
        merchant.setCompanyTel(maObj.getCompanyTel());
        merchant.setAddress(maObj.getAddress());
        merchant.setTradingCertificateFirstFileName(maObj.getTradingCertificateFirstFileName());
        merchant.setTradingCertificateFirstFileUrl(maObj.getTradingCertificateFirstFileUrl());
        merchant.setTradingCertificateSecondFileName(maObj.getTradingCertificateSecondFileName());
        merchant.setTradingCertificateSecondFileUrl(maObj.getTradingCertificateSecondFileUrl());
        merchant.setTradingCertificateThirdFileName(maObj.getTradingCertificateThirdFileName());
        merchant.setTradingCertificateThirdFileUrl(maObj.getTradingCertificateThirdFileUrl());
        merchant.setLegalPersonIdCardFileName(maObj.getBusinessLicenseFileName());
        merchant.setLegalPersonIdCardFileUrl(maObj.getBusinessLicenseFileUrl());
        // 审批流程走完需要覆盖商户表数据作为最新的商户信息
        merchantMapper.updateByPrimaryKeySelective(merchant);
        
        // 为商户新增管理员角色
        MerchantRole roleRecord = MerchantRole.builder()
                .createTime(new Date())
                .creator(param.getAuditor())
                .merchantId(merchant.getId())
                .remark("审核通过，新增商户的管理员角色")
                .roleName("管理员")
                .roleCode(dbSequenceService.getMerchantPowerNumber())
                .build();
        merchantRoleMapper.insertSelective(roleRecord);
        
        // 为管理员角色新增权限
        List<Permission> permissions = merchantPermissionMapper.getAllPermissions();
        for (Permission permission : permissions) {
            MerchantRolePermission rolePermissionRecord = MerchantRolePermission
                    .builder()
                    .createTime(new Date())
                    .creator(param.getAuditor())
                    .permissionId(permission.getPermissionId())
                    .roleId(roleRecord.getId())
                    .build();
            merchantRolePermissionMapper.insertSelective(rolePermissionRecord);
        }
        
        // 查询出该商户的注册人信息
        MerchantUser merchantUser = merchantUserMapper.getRegistMerchantUserByMerchantCode(param.getMerchantCode());
        
        if(merchantUser == null) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户注册人不存在");
        }
        
        // 为商户成员绑定该角色
        MerchantUserRole userRoleRecord = MerchantUserRole.builder()
                .createTime(new Date())
                .creator(param.getAuditor())
                .merchantUserId(merchantUser.getId())
                .roleId(roleRecord.getId())
                .build();
        merchantUserRoleMapper.insertSelective(userRoleRecord);
        
        return JsonResult.successJsonResult();
    }


}

