package com.azz.platform.client.service;
/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月22日 下午8:31:02 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.exception.BaseException;
import com.azz.platform.client.api.ClientService;
import com.azz.platform.client.mapper.ClientApplyMapper;
import com.azz.platform.client.mapper.ClientMapper;
import com.azz.platform.client.pojo.bo.SearchClientParam;
import com.azz.platform.client.pojo.vo.ClientCertification;
import com.azz.platform.client.pojo.vo.ClientInfo;
import com.azz.util.StringUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 彭斌 2018年10月22日 下午8:31:02
 */
@RestController
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    ClientApplyMapper clientApplyMapper;

    @Override
    public JsonResult<Pagination<ClientCertification>> searchClientList(@RequestBody SearchClientParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ClientCertification> ClientList = clientMapper.selectByClientCertificationList(param);
        return JsonResult.successJsonResult(new Pagination<>(ClientList));
    }

    @Override
    public JsonResult<ClientInfo> searchClientInfo(String clientCode) {
        if (StringUtils.isBlank(clientCode)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_CLIENT_NO_EXIST);
        }
        ClientInfo ciObj = clientApplyMapper.selectClientApplyByClientCode(clientCode);
        return JsonResult.successJsonResult(ciObj);
        
    }

   

}

