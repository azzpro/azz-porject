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
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.ClientConstants.ClientInvoiceType;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.client.mapper.ClientInvoiceMapper;
import com.azz.order.client.mapper.ClientInvoiceTemplateMapper;
import com.azz.order.client.mapper.ClientOrderPersonalMapper;
import com.azz.order.client.mapper.ClientOrderShippingAddressMapper;
import com.azz.order.client.pojo.ClientInvoice;
import com.azz.order.client.pojo.bo.AddInvoiceApplyParam;
import com.azz.order.client.pojo.bo.SearchAddInvoiceApplyParam;
import com.azz.order.client.pojo.bo.SearchClientInvoiceParam;
import com.azz.order.client.pojo.bo.SearchInvoiceTemplateParam;
import com.azz.order.client.pojo.vo.ClientAddInvoice;
import com.azz.order.client.pojo.vo.ClientInvoiceList;
import com.azz.order.client.pojo.vo.ClientInvoiceTemplateList;
import com.azz.order.merchant.mapper.ClientUserMapper;
import com.azz.order.merchant.pojo.ClientUser;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
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
    
    @Autowired
    private DbSequenceService dbSequenceService;
    
    @Autowired
    private ClientUserMapper clientUserMapper;
    
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
    
    /**
     * <p>新增发票申请</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月19日 下午6:46:56
     */
    public JsonResult<String> addInvoiceApply(@RequestBody AddInvoiceApplyParam param){
        JSR303ValidateUtils.validate(param);
        // 校验订单是否已经申请过并且已拒绝
        int isExist = clientInvoiceMapper.getExistClientInvoice(param.getClientOrderId());
        if(isExist > 0) {
            // 已经申请待处理状态不允许再次申请
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单已申请开票待处理不允许再次申请");
        }
        
        ClientUser user = clientUserMapper.getClientUserByClientUserCode(param.getCreator());
        if(ObjectUtils.isNull(user)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户信息未找到");
        }
        
        ClientInvoice ciObj = new ClientInvoice();
        ciObj.setClientApplyCode(dbSequenceService.getClientInvoiceApplyNumber());
        ciObj.setAmount(param.getAmount());
        ciObj.setClientOrderId(param.getClientOrderId());
        ciObj.setClientUserId(user.getId());
        ciObj.setCreator(param.getCreator());
        ciObj.setInvoiceTemplateId(param.getInvoiceTemplateId());
        ciObj.setShippingAddressId(param.getShippingAddressId());
        ciObj.setStatus(ClientInvoiceType.PENDING.getValue());
        // 添加开票申请
        clientInvoiceMapper.insertSelective(ciObj);
        return JsonResult.successJsonResult();
    }
}

