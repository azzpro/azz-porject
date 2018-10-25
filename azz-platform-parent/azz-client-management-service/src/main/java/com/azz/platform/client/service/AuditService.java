/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月24日 下午4:44:51 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.exception.BaseException;
import com.azz.platform.client.common.constants.AuditConstants;
import com.azz.platform.client.mapper.ClientApplyMapper;
import com.azz.platform.client.mapper.ClientUserMapper;
import com.azz.platform.client.pojo.ClientApply;
import com.azz.platform.client.pojo.ClientUser;
import com.azz.platform.client.pojo.bo.AuditParam;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;

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

    public JsonResult<String> auditClient(@RequestBody AuditParam param) {
        JSR303ValidateUtils.validate(param);
        int isExist = clientUserMapper.checkClientApplyInfo(param.getClientUserCode());
        if (isExist == 0) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_CLIENT_AUDIT_NO_EXIST);
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
        
        ClientApply ca = new ClientApply();
        if (param.getStatus().equals(AuditConstants.AuditStatus.PASSED.getValue())) {
            // 审核通过
            
            
            clientApplyMapper.updateByPrimaryKeySelective(ca);
        } else if (param.getStatus().equals(AuditConstants.AuditStatus.REFUSED.getValue())) {
            // 审核拒绝
        }
        return null;
    }

}

