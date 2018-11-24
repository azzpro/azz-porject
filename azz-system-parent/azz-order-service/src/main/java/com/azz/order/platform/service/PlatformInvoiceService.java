/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.platform.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.ClientConstants.ClientInvoiceType;
import com.azz.core.constants.MerchantConstants.MerchantInvoiceApplyStatusEnum;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.client.mapper.ClientInvoiceMapper;
import com.azz.order.client.mapper.ClientInvoiceTemplateMapper;
import com.azz.order.client.mapper.ClientOrderPersonalMapper;
import com.azz.order.client.pojo.ClientInvoice;
import com.azz.order.client.pojo.ClientInvoiceTemplate;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.order.client.pojo.vo.ClientOrderItemInfo;
import com.azz.order.merchant.mapper.MerchantInvoiceMapper;
import com.azz.order.merchant.mapper.MerchantOrderMapper;
import com.azz.order.merchant.pojo.MerchantInvoice;
import com.azz.order.merchant.pojo.MerchantOrder;
import com.azz.order.platform.bo.AuditInvoiceStatusParam;
import com.azz.order.platform.bo.SearchInvoiceListParam;
import com.azz.order.platform.vo.ClientOrderRelevanceInvoice;
import com.azz.order.platform.vo.PlatformClientInvoiceApplyDetail;
import com.azz.order.platform.vo.PlatformClientInvoiceList;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>平台端发票管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月22日 上午11:06:57
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class PlatformInvoiceService {

    @Autowired
    private ClientInvoiceMapper clientInvoiceMapper;
	
    @Autowired
    private ClientOrderPersonalMapper clientOrderPersonalMapper;
    
    @Autowired
    private MerchantOrderMapper merchantOrderMapper;
    
    @Autowired
    private ClientInvoiceTemplateMapper clientInvoiceTemplateMapper;
    
    @Autowired
    private MerchantInvoiceMapper merchantInvoiceMapper;
    
    @Autowired
    private DbSequenceService dbSequenceService;
    
    /**
     * <p>获取平台端客户发票列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月22日 下午12:45:09
     */
    public JsonResult<Pagination<PlatformClientInvoiceList>> getPlatformClientInvoiceList(@RequestBody SearchInvoiceListParam param){
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<PlatformClientInvoiceList> infos = clientInvoiceMapper.getPlatformClientInvoiceList(param);
        return JsonResult.successJsonResult(new Pagination<>(infos));
    }
    
    /**
     * <p>获取申请详情基本信息</p>
     * @param clientOrderCode
     * @return
     * @author 彭斌  2018年11月22日 下午1:20:07
     */
    public JsonResult<PlatformClientInvoiceApplyDetail> getPlatformClientInvoiceDetail(String clientOrderCode){
        if(ObjectUtils.isNull(clientOrderCode)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单编码参数不许为空");
        }
        
        PlatformClientInvoiceApplyDetail pciad = clientInvoiceMapper.getPlatformClientInvoiceOrderDetail(clientOrderCode);
        if(ObjectUtils.isNull(pciad)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "未获取订单信息");
        }
        
        // 关联商户
        List<ClientOrderRelevanceInvoice> list = clientInvoiceMapper.getOrderRelevanceInvoiceList(clientOrderCode);
        pciad.setRelevanceMerchantItem(list);
        
        // 产品详情
        List<ClientOrderInfo> coiList = clientOrderPersonalMapper.getClientOrderInfoListByParam(clientOrderCode,"");
        List<ClientOrderItemInfo> orderItem = new ArrayList<>();
        if(ObjectUtils.isNotNull(coiList) && coiList.size() > 0) {
            orderItem = coiList.get(0).getOrderItems();
        }
        pciad.setOrderItem(orderItem);
        
        return JsonResult.successJsonResult(pciad);
    }


    /**
     * <p>平台审批操作</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月22日 下午4:06:37
     */
    public JsonResult<String> operationInvoiceStatus(@RequestBody AuditInvoiceStatusParam param){
        JSR303ValidateUtils.validate(param);
        
        ClientInvoice ci = clientInvoiceMapper.getClientInvoiceByApplyCode(param.getClientInvoiceApplyCode());
        if(ObjectUtils.isNull(ci)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "申请信息不存在");
        }
        
        if(ci.getStatus() != ClientInvoiceType.PENDING.getValue()) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "申请信息已处理不允许再次操作");
        }
        
        // 更新客户发票信息待开票
        if(1 == param.getStatus()) {
            ci.setStatus(ClientInvoiceType.PENDING_INVOICE.getValue());
        } else if(0 == param.getStatus()){
            ci.setStatus(ClientInvoiceType.REJECTED.getValue());
        } else {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "审核状态异常");
        }
        
        ci.setModifier(param.getPlatformUserCode());
        ci.setModifyTime(new Date());
        
        if(1 == param.getStatus()) {
            // 生成商户发票信息
            ClientInvoiceTemplate cit = clientInvoiceTemplateMapper.selectByPrimaryKey(ci.getInvoiceTemplateId());
            List<MerchantOrder> list = merchantOrderMapper.selectMerchantOrderByClientOrderId(ci.getClientOrderId());
            for (int i = 0; i < list.size(); i++) {
                MerchantInvoice record = new MerchantInvoice();
                record.setInvoiceType(cit.getInvoiceType());
                record.setMerchantApplyCode(dbSequenceService.getMerchantInvoiceApplyNumber());
                record.setApplyAmount(list.get(i).getGrandTotal());
                record.setCreateTime(new Date());
                record.setCreator(param.getPlatformUserCode());
                record.setMerchantId(list.get(i).getMerchantId());
                record.setMerchantOrderId(list.get(i).getId());
                record.setStatus(MerchantInvoiceApplyStatusEnum.NOT_CONFIRMED.getValue());
                merchantInvoiceMapper.insertSelective(record);
            }
            // 更新发票总数
            ci.setQuantity(list.size());
        }
        
        clientInvoiceMapper.updateByPrimaryKeySelective(ci);
        return JsonResult.successJsonResult();
    }
}

