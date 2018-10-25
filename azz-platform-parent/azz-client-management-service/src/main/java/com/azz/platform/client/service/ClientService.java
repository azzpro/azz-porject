package com.azz.platform.client.service;
/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月22日 下午8:31:02 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.client.mapper.ClientApplyMapper;
import com.azz.platform.client.mapper.ClientUserMapper;
import com.azz.platform.client.pojo.bo.SearchClientParam;
import com.azz.platform.client.pojo.vo.ClientCertification;
import com.azz.platform.client.pojo.vo.ClientInfo;
import com.github.pagehelper.PageHelper;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 彭斌 2018年10月22日 下午8:31:02
 */
@Service
public class ClientService {
    
    @Autowired
    ClientApplyMapper clientApplyMapper;
    
    @Autowired
    ClientUserMapper clientUserMapper;
    
    /**
     * <p>获取审批的客户列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月25日 下午2:03:14
     */
    public JsonResult<Pagination<ClientCertification>> searchMerchantList(@RequestBody SearchClientParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ClientCertification> clientList = clientApplyMapper.selectByClientCertificationList(param);
        return JsonResult.successJsonResult(new Pagination<>(clientList));
    }

    /**
     * <p>根据客户编码获取审批详情</p>
     * @param code
     * @return
     * @author 彭斌  2018年10月25日 下午2:06:08
     */
    public JsonResult<ClientInfo> selectDetailsClientInfo(String code){
        ClientInfo clientInfo = clientUserMapper.selectDetailsByClientUserCode(code);
        return JsonResult.successJsonResult(clientInfo);
    }

}

