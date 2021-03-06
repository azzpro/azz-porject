package com.azz.platform.client.service;
/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月22日 下午8:31:02 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.client.mapper.ClientApplyMapper;
import com.azz.platform.client.mapper.ClientUserCompanyMapper;
import com.azz.platform.client.mapper.ClientUserMapper;
import com.azz.platform.client.pojo.ClientUser;
import com.azz.platform.client.pojo.bo.SearchClientManagerParam;
import com.azz.platform.client.pojo.bo.SearchClientMerchantManagerParam;
import com.azz.platform.client.pojo.bo.SearchClientParam;
import com.azz.platform.client.pojo.vo.ClientAccountInfo;
import com.azz.platform.client.pojo.vo.ClientCertification;
import com.azz.platform.client.pojo.vo.ClientCompanyEmployee;
import com.azz.platform.client.pojo.vo.ClientCompanyInfo;
import com.azz.platform.client.pojo.vo.ClientInfo;
import com.azz.platform.client.pojo.vo.ClientMerchantInfo;
import com.github.pagehelper.PageHelper;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 彭斌 2018年10月22日 下午8:31:02
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ClientService {
    
    @Autowired
    ClientApplyMapper clientApplyMapper;
    
    @Autowired
    ClientUserMapper clientUserMapper;
    
    @Autowired
    private ClientUserCompanyMapper clientUserCompanyMapper;
    
    /**
     * <p>获取审批的客户列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月25日 下午2:03:14
     */
    public JsonResult<Pagination<ClientCertification>> searchClientList(@RequestBody SearchClientParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ClientCertification> clientList = clientApplyMapper.selectByClientCertificationList(param);
        return JsonResult.successJsonResult(new Pagination<>(clientList));
    }
	
	/**
	 * <p>平台 客户管理</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月25日 下午3:18:38
	 */
	public JsonResult<Pagination<ClientUser>> selectClientUserList(@RequestBody SearchClientManagerParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ClientUser> clientUserList = clientUserMapper.selectClientUserList(param);
        return JsonResult.successJsonResult(new Pagination<>(clientUserList));
    }
	
	/**
	 * <p>平台 客户管理 企业用户</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月25日 下午3:18:38
	 */
	public JsonResult<Pagination<ClientMerchantInfo>> selectClientMerchantList(@RequestBody SearchClientMerchantManagerParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ClientMerchantInfo> merchantList = clientUserCompanyMapper.selectClientMerchantList(param);
        return JsonResult.successJsonResult(new Pagination<>(merchantList));
    }
	
	/**
	 * <p>平台 客户管理 企业 成员</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月25日 下午3:18:38
	 */
	public JsonResult<Pagination<ClientCompanyEmployee>> selectClientCompanyEmployeeList(@RequestBody SearchClientManagerParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ClientCompanyEmployee> merchantList = clientUserMapper.selectClientCompanyEmployeeList(param);
        return JsonResult.successJsonResult(new Pagination<>(merchantList));
    }
	
	/**
	 * <p>平台 客户管理 用户详情</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月25日 下午3:18:38
	 */
	public JsonResult<ClientAccountInfo> selectClientUserInfo(String code) {
        ClientAccountInfo userInfo = clientUserMapper.selectClientUserInfo(code);
        return JsonResult.successJsonResult(userInfo);
    }
	
	/**
	 * <p>平台 客户管理 企业详情</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月25日 下午3:18:38
	 */
	public JsonResult<ClientCompanyInfo> selectClientCompanyDetail(String code) {
		ClientCompanyInfo userInfo = clientUserCompanyMapper.selectClientCompanyDetail(code);
        return JsonResult.successJsonResult(userInfo);
    }
   
	/**
     * <p>客户管理 启动 禁用</p>
     * @param param
     * @return
     * @author 刘建麟  2018年10月24日 下午7:31:57
     */
    @Transactional(rollbackFor=Exception.class)
    public JsonResult<String> updateClientUserStatus(String code,Integer status) {
    	 clientUserMapper.updateClientUserStatus(code,status);
    	 return JsonResult.successJsonResult();
    }
    
    /**
     * <p>客户管理 企业管理 启动 禁用</p>
     * @param param
     * @return
     * @author 刘建麟  2018年10月24日 下午7:31:57
     */
    @Transactional(rollbackFor=Exception.class)
    public JsonResult<String> updateClientCompnayStatus(String code,Integer status) {
    	if(StringUtils.isBlank(code)) {
    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"企业编码不能为空");
    	}
    	if(null == status) {
    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"状态不能为空");
    	}
    	clientUserCompanyMapper.updateClientCompnayStatus(code,status);
    	 return JsonResult.successJsonResult();
    }
    /**
     * <p>根据企业编码获取审批详情</p>
     * @param code
     * @return
     * @author 彭斌  2018年10月25日 下午2:06:08
     */
    public JsonResult<ClientInfo> selectDetailsClientInfo(String companyCode){
        ClientInfo clientInfo = clientUserMapper.selectDetailsByCompanyCode(companyCode);
        return JsonResult.successJsonResult(clientInfo);
    }

}

