package com.azz.platform.client.service;
/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月22日 下午8:31:02 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.client.mapper.ClientUserMapper;
import com.azz.platform.client.pojo.ClientUser;
import com.azz.platform.client.pojo.bo.SearchClientManagerParam;
import com.azz.platform.merchant.pojo.bo.SearchMerchantParam;
import com.azz.platform.merchant.pojo.vo.MerchantApproval;
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
	private ClientUserMapper clientUserMapper;
	
	/**
	 * <p>平台 客户管理</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月25日 下午3:18:38
	 */
	public JsonResult<Pagination<ClientUser>> selectClientUserList(@RequestBody SearchClientManagerParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ClientUser> merchantList = clientUserMapper.selectClientUserList(param);
        return JsonResult.successJsonResult(new Pagination<>(merchantList));
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

}

