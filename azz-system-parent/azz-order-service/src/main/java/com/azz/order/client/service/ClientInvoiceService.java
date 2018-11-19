/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月19日 上午10:36:47
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
import com.azz.order.client.mapper.ClientInvoiceMapper;
import com.azz.order.client.mapper.ClientInvoiceTemplateMapper;
import com.azz.order.client.mapper.ClientOrderPersonalMapper;
import com.azz.order.client.mapper.ClientOrderShippingAddressMapper;
import com.azz.order.client.pojo.bo.SearchAddInvoiceApplyParam;
import com.azz.order.client.pojo.bo.SearchClientInvoiceParam;
import com.azz.order.client.pojo.bo.SearchInvoiceTemplateParam;
import com.azz.order.client.pojo.vo.ClientAddInvoice;
import com.azz.order.client.pojo.vo.ClientInvoiceList;
import com.azz.order.client.pojo.vo.ClientInvoiceTemplateList;
import com.azz.util.JSR303ValidateUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>客户发票管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月19日 上午10:36:47
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ClientInvoiceService {
    @Autowired
    private ClientInvoiceMapper clientInvoiceMapper;
    
    @Autowired
    private ClientInvoiceTemplateMapper clientInvoiceTemplateMapper;
    
    @Autowired
    private ClientOrderPersonalMapper clientOrderPersonalMapper;
    
    @Autowired
    private ClientOrderShippingAddressMapper clientOrderShippingAddressMapper;
    
    /**
     * <p>查询客户发票管理列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月19日 下午2:37:47
     */
    public JsonResult<Pagination<ClientInvoiceList>> getClientInvoiceList(@RequestBody SearchClientInvoiceParam param){
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ClientInvoiceList> list = clientInvoiceMapper.getClientInvoiceList(param);
        return JsonResult.successJsonResult(new Pagination<>(list));
    }
    
    /**
     * <p>根据发票类型和客户id获取所有发票模板信息</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月19日 下午4:05:09
     */
    public JsonResult<List<ClientInvoiceTemplateList>> getInvoiceTemplateList(@RequestBody SearchInvoiceTemplateParam param){
        JSR303ValidateUtils.validate(param);
        List<ClientInvoiceTemplateList> list = clientInvoiceTemplateMapper.getInvoiceTemplate(param);
        return JsonResult.successJsonResult(list);
    }
    
    /**
     * <p>获取新增开票申请列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月19日 下午5:08:28
     */
    public JsonResult<List<ClientAddInvoice>> getInvoiceClientList(@RequestBody SearchAddInvoiceApplyParam param){
        JSR303ValidateUtils.validate(param);
        List<ClientAddInvoice> list = clientOrderPersonalMapper.getInvoiceClient(param);
        return JsonResult.successJsonResult(list);
    }
    
    
}

