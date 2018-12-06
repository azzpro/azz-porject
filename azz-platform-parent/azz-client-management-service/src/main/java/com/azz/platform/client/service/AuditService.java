/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月24日 下午4:44:51 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.client.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.BaseErrorCode;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.exception.BaseException;
import com.azz.platform.client.common.constants.AuditConstants;
import com.azz.platform.client.common.constants.ClientConstants;
import com.azz.platform.client.mapper.ClientApplyMapper;
import com.azz.platform.client.mapper.ClientPermissionMapper;
import com.azz.platform.client.mapper.ClientRoleMapper;
import com.azz.platform.client.mapper.ClientRolePermissionMapper;
import com.azz.platform.client.mapper.ClientUserCompanyMapper;
import com.azz.platform.client.mapper.ClientUserMapper;
import com.azz.platform.client.mapper.ClientUserRoleMapper;
import com.azz.platform.client.pojo.ClientApply;
import com.azz.platform.client.pojo.ClientRole;
import com.azz.platform.client.pojo.ClientRolePermission;
import com.azz.platform.client.pojo.ClientUser;
import com.azz.platform.client.pojo.ClientUserCompany;
import com.azz.platform.client.pojo.ClientUserRole;
import com.azz.platform.client.pojo.bo.AuditParam;
import com.azz.platform.client.pojo.vo.Permission;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.StringUtils;
import com.azz.util.SystemSeqUtils;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 彭斌 2018年10月24日 下午4:44:51
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AuditService {

    @Autowired
    ClientApplyMapper clientApplyMapper;

    @Autowired
    ClientUserMapper clientUserMapper;

    @Autowired
    ClientUserCompanyMapper clientUserCompanyMapper;
    
    @Autowired
    ClientPermissionMapper clientPermissionMapper;
    
    @Autowired
    ClientRoleMapper clientRoleMapper;
    
    @Autowired
    ClientRolePermissionMapper clientRolePermissionMapper;
    
    @Autowired
    ClientUserRoleMapper clientUserRoleMapper;
    
    @Autowired
    private DbSequenceService dbSequenceService;
    
    public JsonResult<String> auditClient(@RequestBody AuditParam param) {
        JSR303ValidateUtils.validate(param);
        int isExist = clientUserMapper.checkClientApplyInfo(param.getClientUserCode());
        if (isExist == 0) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_CLIENT_AUDIT_NO_EXIST);
        }

        ClientUserCompany cucObj = clientUserCompanyMapper.selectByCompanyCode(param.getCompanyCode());
        if(ObjectUtils.isNull(cucObj)) {
            // 审核的企业不存在
            throw new BaseException(PlatformUserErrorCode.PLATFORM_CLIENT_ENTERPRISE_NO_EXIST);
        }
        
        if (!AuditConstants.AuditStatus.checkStatusExist(param.getStatus())) {
            // 审核状态不存在
            throw new BaseException(
                    PlatformUserErrorCode.PLATFORM_MERCHANT_AUDIT_STATUS_ERROR_NO_EXIST);
        }
        ClientUser clientUser = clientUserMapper.selectByClientUserCode(param.getClientUserCode());
        if(ObjectUtils.isNull(clientUser)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_CLIENT_AUDIT_NO_EXIST);
        }
        
        ClientApply clientApplyObj = clientApplyMapper.selectByClientUserIdStatus(clientUser.getId());
        
        if (param.getStatus().equals(AuditConstants.AuditStatus.PASSED.getValue())) {
            // 审核通过(clientApply)
            clientApplyObj.setStatus(AuditConstants.AuditStatus.PASSED.getValue());
            
            // 更新客户为企业用户
            clientUser.setClientType(ClientConstants.EnterpriseType.ENTERPRISE.getValue());
            clientUserMapper.updateByPrimaryKeySelective(clientUser);
            
            // 申请基本信息和资质信息新增到公司表
            cucObj.setClientUserCode(clientUser.getClientUserCode());
            cucObj.setCompanyName(clientApplyObj.getCompanyName());
            cucObj.setCreditCode(clientApplyObj.getCreditCode());
            cucObj.setCompanyTel(clientApplyObj.getCompanyTel());
            cucObj.setTradingCertificateFirstFileName(clientApplyObj.getTradingCertificateFirstFileName());
            cucObj.setTradingCertificateFirstFileUrl(clientApplyObj.getTradingCertificateFirstFileUrl());
            cucObj.setTradingCertificateSecondFileName(clientApplyObj.getTradingCertificateSecondFileUrl());
            cucObj.setTradingCertificateSecondFileUrl(clientApplyObj.getTradingCertificateSecondFileUrl());
            cucObj.setTradingCertificateThirdFileName(clientApplyObj.getTradingCertificateThirdFileName());
            cucObj.setTradingCertificateThirdFileUrl(clientApplyObj.getTradingCertificateThirdFileUrl());
            cucObj.setLastModifyTime(new Date());
            clientUserCompanyMapper.updateByPrimaryKeySelective(cucObj);
            String sequence = dbSequenceService.getPlatPowerNumber();
            
            // 为客户新增管理员角色
            ClientRole roleRecord = ClientRole.builder()
                    .createTime(new Date())
                    .creator(param.getClientUserCode())
                    .companyCode(param.getCompanyCode())
                    .remark("审核通过，新增客户的管理员角色")
                    .roleName("管理员")
                    .roleCode(SystemSeqUtils.getSeq(sequence))
                    .build();
            clientRoleMapper.insertSelective(roleRecord);
            
            // 为管理员角色新增权限
            List<Permission> permissions = clientPermissionMapper.getAllPermissions();
            for (Permission permission : permissions) {
                ClientRolePermission rolePermissionRecord = ClientRolePermission
                        .builder()
                        .createTime(new Date())
                        .creator(param.getAuditor())
                        .permissionId(permission.getPermissionId())
                        .roleId(roleRecord.getId())
                        .build();
                clientRolePermissionMapper.insertSelective(rolePermissionRecord);
            }
            
            // 为客户成员绑定该角色
            ClientUserRole userRoleRecord = ClientUserRole.builder()
                    .createTime(new Date())
                    .creator(param.getClientUserCode())
                    .clientUserId(clientUser.getId())
                    .roleId(roleRecord.getId())
                    .build();
            clientUserRoleMapper.insertSelective(userRoleRecord);
            
            
        } else if (param.getStatus().equals(AuditConstants.AuditStatus.REFUSED.getValue())) {
            // 审核拒绝
            clientApplyObj.setStatus(AuditConstants.AuditStatus.REFUSED.getValue());
        }
        // 更新申请信息的审核人，审核时间
        clientApplyObj.setAuditor(param.getAuditor());
        clientApplyObj.setAuditorTime(new Date());
        clientApplyMapper.updateByPrimaryKeySelective(clientApplyObj);
        
        return JsonResult.successJsonResult();
    }

}

