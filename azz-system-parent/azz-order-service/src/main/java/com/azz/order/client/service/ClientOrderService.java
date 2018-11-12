/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.client.mapper.ClientOrderPersonalMapper;
import com.azz.order.client.pojo.bo.SearchClientOrderParam;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.github.pagehelper.PageHelper;

/**
 * <P>客户端订单业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月12日 下午3:14:43
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ClientOrderService {
	
	@Autowired
	private ClientOrderPersonalMapper clientOrderPersonalMapper;
	
	public JsonResult<Pagination<ClientOrderInfo>> getClientOrderInfoList(@RequestBody SearchClientOrderParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<ClientOrderInfo> infos = clientOrderPersonalMapper.getClientOrderInfoList(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}

}

