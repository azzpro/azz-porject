/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月19日 上午10:36:47
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.client.service;

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
import com.azz.core.constants.ClientConstants.InvoiceType;
import com.azz.core.constants.MerchantConstants.MerchantInvoiceApplyStatusEnum;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.client.mapper.ClientInvoiceMapper;
import com.azz.order.client.mapper.ClientInvoiceTemplateMapper;
import com.azz.order.client.mapper.ClientOrderPersonalMapper;
import com.azz.order.client.pojo.ClientInvoice;
import com.azz.order.client.pojo.ClientInvoiceTemplate;
import com.azz.order.client.pojo.ClientOrderPersonal;
import com.azz.order.client.pojo.bo.AddEditInvoiceTemplateParam;
import com.azz.order.client.pojo.bo.AddInvoiceApplyParam;
import com.azz.order.client.pojo.bo.SearchAddInvoiceApplyParam;
import com.azz.order.client.pojo.bo.SearchClientInvoiceParam;
import com.azz.order.client.pojo.bo.SearchClientOrderParam;
import com.azz.order.client.pojo.bo.SearchCountTemplateParam;
import com.azz.order.client.pojo.bo.SearchInvoiceTemplateParam;
import com.azz.order.client.pojo.bo.SigningInvoiceParam;
import com.azz.order.client.pojo.vo.ClientAddInvoice;
import com.azz.order.client.pojo.vo.ClientInvoiceApplyDetail;
import com.azz.order.client.pojo.vo.ClientInvoiceDeliveryDetail;
import com.azz.order.client.pojo.vo.ClientInvoiceList;
import com.azz.order.client.pojo.vo.ClientInvoiceTemplateList;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.order.client.pojo.vo.ClientOrderItemInfo;
import com.azz.order.merchant.mapper.ClientUserMapper;
import com.azz.order.merchant.mapper.MerchantInvoiceMapper;
import com.azz.order.merchant.mapper.MerchantOrderMapper;
import com.azz.order.merchant.pojo.ClientUser;
import com.azz.order.merchant.pojo.MerchantInvoice;
import com.azz.order.merchant.pojo.MerchantOrder;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.SystemSeqUtils;
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
    private DbSequenceService dbSequenceService;
    
    @Autowired
    private ClientUserMapper clientUserMapper;
    
    @Autowired
    private MerchantOrderMapper merchantOrderMapper;
    
    @Autowired
    private MerchantInvoiceMapper merchantInvoiceMapper;
    
    
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
     * <p>根据发票类型和客户编码获取所有发票模板信息</p>
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
    public JsonResult<Pagination<ClientAddInvoice>> getInvoiceClientList(@RequestBody SearchAddInvoiceApplyParam param){
        JSR303ValidateUtils.validate(param);
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ClientAddInvoice> list = clientOrderPersonalMapper.getInvoiceClient(param);
        return JsonResult.successJsonResult(new Pagination<>(list));
    }
    
    /**
     * <p>新增发票申请</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月19日 下午6:46:56
     */
    public JsonResult<String> addInvoiceApply(@RequestBody AddInvoiceApplyParam param){
        JSR303ValidateUtils.validate(param);
        
        ClientOrderPersonal cop = clientOrderPersonalMapper.getClientOrderPersonalByClientOrderCode(param.getClientOrderCode());
        if(ObjectUtils.isNull(cop)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单未找到");
        }
        
        // 校验订单是否已经申请过并且已拒绝
        int isExist = clientInvoiceMapper.getExistClientInvoice(param.getClientOrderCode());
        if(isExist > 0) {
            // 已经申请待处理状态不允许再次申请
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单已申请开票待处理不允许再次申请");
        }
        
        ClientUser user = clientUserMapper.getClientUserByClientUserCode(param.getCreator());
        if(ObjectUtils.isNull(user)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户信息未找到");
        }
        String code = dbSequenceService.getClientInvoiceApplyNumber();
        ClientInvoice ciObj = new ClientInvoice();
        ciObj.setClientApplyCode(SystemSeqUtils.getSeq(code));
        ciObj.setAmount(param.getAmount());
        ciObj.setClientOrderId(cop.getId());
        ciObj.setClientUserId(user.getId());
        ciObj.setCreator(param.getCreator());
        ciObj.setInvoiceTemplateId(param.getInvoiceTemplateId());
        ciObj.setShippingAddressId(param.getShippingAddressId());
        ciObj.setStatus(ClientInvoiceType.PENDING.getValue());
        ciObj.setCreateTime(new Date());
        // 添加开票申请
        clientInvoiceMapper.insertSelective(ciObj);
        return JsonResult.successJsonResult();
    }

    /**
     * <p>获取客户发票模板详情</p>
     * @param invoiceId
     * @return
     * @author 彭斌  2018年11月20日 上午11:35:32
     */
    public JsonResult<ClientInvoiceTemplate> getClientInvoiceTemplateDetail(Long invoiceId){
        ClientInvoiceTemplate cit = clientInvoiceTemplateMapper.selectByPrimaryKey(invoiceId);
        return JsonResult.successJsonResult(cit);
    }
    
    /**
     * <p>发票模板管理新增，编辑操作</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月20日 下午2:00:59
     */
    public JsonResult<String> addEditInvoiceTemplate(@RequestBody AddEditInvoiceTemplateParam param){
        JSR303ValidateUtils.validate(param);
        
        ClientUser user = clientUserMapper.getClientUserByClientUserCode(param.getClientUserCode());
        if(ObjectUtils.isNull(user)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户信息不存在");
        }
        
        SearchCountTemplateParam sctp = new SearchCountTemplateParam();
        sctp.setClientUserId(user.getId());
        sctp.setInvoiceType(param.getInvoiceType());
        
        if(ObjectUtils.isNull(param.getId())) {
            // 新增模板信息
            ClientInvoiceTemplate record = new ClientInvoiceTemplate();
            record.setClientUserId(user.getId());
            record.setCreateTime(new Date());
            record.setCreator(param.getClientUserCode());
            
            if(ObjectUtils.isNull(param.getNumber())) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "填写纳税识别号");
            }
            
            if(InvoiceType.ORDINARY_INVOICE.getValue() == param.getInvoiceType()) {
                // 普通发票操作
                if(ObjectUtils.isNull(param.getInvoiceTitle())) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "填写发票抬头");
                }
                
                // 客户普通发票抬头唯一校验
                sctp.setInvoiceTile(param.getInvoiceTitle());
                int countTitle = clientInvoiceTemplateMapper.getCountTemplate(sctp);
                if(countTitle > 0) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "已录入发票抬头");
                }
                
                // 客户普通发票纳税号唯一校验
                sctp.setNumber(param.getNumber());
                sctp.setInvoiceTile(null);
                int countNumber = clientInvoiceTemplateMapper.getCountTemplate(sctp);
                if(countNumber > 0) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "已录入纳税识别号");
                }
                record.setInvoiceTitle(param.getInvoiceTitle());
                record.setTaxIdentificationNumber(param.getNumber());
                record.setRemark(param.getRemark());
                record.setInvoiceType(InvoiceType.ORDINARY_INVOICE.getValue());
            } else if(InvoiceType.VAT_SPECIAL_INVOICE.getValue() == param.getInvoiceType()) {
                // 增值发票操作
                if(ObjectUtils.isNull(param.getCompanyName())) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "填写公司名称");
                }
                if(ObjectUtils.isNull(param.getRegAddress())) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "填写注册地址");
                }
                if(ObjectUtils.isNull(param.getRegPhone())) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "填写注册电话");
                }
                if(ObjectUtils.isNull(param.getBank())) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "填写开户银行");
                }
                if(ObjectUtils.isNull(param.getBankAccount())) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "填写银行账号");
                }
                
                // 客户增值发票公司名称唯一校验
                sctp.setCompanyName(param.getCompanyName());
                int countCompany = clientInvoiceTemplateMapper.getCountTemplate(sctp);
                if(countCompany > 0) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "已录入公司名称");
                }
                
                // 客户增值发票纳税号唯一校验
                sctp.setNumber(param.getNumber());
                sctp.setCompanyName(null);
                int countNumber = clientInvoiceTemplateMapper.getCountTemplate(sctp);
                if(countNumber > 0) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "已录入纳税识别号");
                }
                record.setCompanyName(param.getCompanyName());
                record.setTaxIdentificationNumber(param.getNumber());
                record.setRegAddress(param.getRegAddress());
                record.setRegTelephone(param.getRegPhone());
                record.setBank(param.getBank());
                record.setBankAccount(param.getBankAccount());
                record.setInvoiceType(InvoiceType.VAT_SPECIAL_INVOICE.getValue());
            } else {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "发票类型不存在");
            }
            // 新增客户普通发票
            clientInvoiceTemplateMapper.insertSelective(record);
        } else {
            // 修改模板信息
            ClientInvoiceTemplate citObj = clientInvoiceTemplateMapper.selectByPrimaryKey(param.getId());
            citObj.setModifier(param.getClientUserCode());
            citObj.setModifyTime(new Date());
            if(InvoiceType.ORDINARY_INVOICE.getValue() == param.getInvoiceType()) {
                
                if(ObjectUtils.isNull(param.getInvoiceTitle())) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "填写发票抬头");
                }
                // 修改的信息是否重复
                if(!citObj.getInvoiceTitle().equals(param.getInvoiceTitle())) {
                    // 客户普通发票抬头唯一校验
                    sctp.setInvoiceTile(param.getInvoiceTitle());
                    int countTitle = clientInvoiceTemplateMapper.getCountTemplate(sctp);
                    if(countTitle > 0) {
                        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "已录入发票抬头");
                    }
                }
                
                if(!citObj.getTaxIdentificationNumber().equals(param.getNumber())) {
                    // 客户普通发票纳税号唯一校验
                    sctp.setNumber(param.getNumber());
                    sctp.setInvoiceTile(null);
                    int countNumber = clientInvoiceTemplateMapper.getCountTemplate(sctp);
                    if(countNumber > 0) {
                        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "已录入纳税识别号");
                    }
                }
                citObj.setInvoiceTitle(param.getInvoiceTitle());
                citObj.setTaxIdentificationNumber(param.getNumber());
                citObj.setRemark(param.getRemark());
            } else if(InvoiceType.VAT_SPECIAL_INVOICE.getValue() == param.getInvoiceType()) {
                
                if(ObjectUtils.isNull(param.getCompanyName())) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "填写公司名称");
                }
                if(ObjectUtils.isNull(param.getRegAddress())) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "填写注册地址");
                }
                if(ObjectUtils.isNull(param.getRegPhone())) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "填写注册电话");
                }
                if(ObjectUtils.isNull(param.getBank())) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "填写开户银行");
                }
                if(ObjectUtils.isNull(param.getBankAccount())) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "填写银行账号");
                }
                // 修改增值发票
                if(!citObj.getCompanyName().equals(param.getCompanyName())) {
                    // 客户增值发票公司名称唯一校验
                    sctp.setCompanyName(param.getCompanyName());
                    int countCompany = clientInvoiceTemplateMapper.getCountTemplate(sctp);
                    if(countCompany > 0) {
                        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "已录入公司名称");
                    }
                }
                
                if(!citObj.getTaxIdentificationNumber().equals(param.getNumber())) {
                    // 客户增值发票纳税号唯一校验
                    sctp.setNumber(param.getNumber());
                    sctp.setCompanyName(null);
                    int countNumber = clientInvoiceTemplateMapper.getCountTemplate(sctp);
                    if(countNumber > 0) {
                        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "已录入纳税识别号");
                    }
                }
                
                citObj.setCompanyName(param.getCompanyName());
                citObj.setTaxIdentificationNumber(param.getNumber());
                citObj.setRegAddress(param.getRegAddress());
                citObj.setRegTelephone(param.getRegPhone());
                citObj.setBank(param.getBank());
                citObj.setBankAccount(param.getBankAccount());
            } else {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "发票类型不存在");
            }
            
            // 编辑增值发票模板信息
            clientInvoiceTemplateMapper.updateByPrimaryKeySelective(citObj);
        }
        return JsonResult.successJsonResult();
    }
    
    /**
     * <p>删除发票模板</p>
     * @param id
     * @param userCode
     * @return
     * @author 彭斌  2018年11月21日 上午10:26:20
     */
    public JsonResult<String> delInvoiceTemplate(Long id,String userCode){
        ClientUser user = clientUserMapper.getClientUserByClientUserCode(userCode);
        if(ObjectUtils.isNull(user)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户信息不存在");
        }
        clientInvoiceTemplateMapper.deleteByPrimaryKey(id,user.getId());
        return JsonResult.successJsonResult();
    }
    
    /**
     * <p>申请详情</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月21日 下午1:57:48
     */
    public JsonResult<ClientInvoiceApplyDetail> getClientInvoiceApplyDetail(@RequestBody SearchAddInvoiceApplyParam param){
        JSR303ValidateUtils.validate(param);
        // 关联订单基本信息、开票详情、寄送地址
        ClientInvoiceApplyDetail ciad = clientInvoiceMapper.getClientInvoiceOrderApplyDetail(param);
        if(ObjectUtils.isNull(ciad)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单基本信息不存在");
        }
        // 开票信息
        List<ClientInvoiceDeliveryDetail> ciddList = clientInvoiceMapper.getInvoiceDeliveryDetail(param.getClientOrderCode());
        if(ObjectUtils.isNotNull(ciddList)  && ciddList.size() > 0) {
            ciad.setInvoiceDelivery(ciddList);
        }
        
        // 产品明细
        List<ClientOrderInfo> coiList = clientOrderPersonalMapper.getClientOrderInfoListByParam(param.getClientOrderCode(),param.getClientUserCode());
        List<ClientOrderItemInfo> orderItem = new ArrayList<>();
        if(ObjectUtils.isNotNull(coiList) && coiList.size() > 0) {
            orderItem = coiList.get(0).getOrderItems();
        }
        ciad.setOrderItem(orderItem);
        
        return JsonResult.successJsonResult(ciad);
    }
    
    /**
     * <p>获取客户产品明细</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月21日 下午4:23:51
     */
    public JsonResult<List<ClientOrderItemInfo>> getClientOrderItems(@RequestBody SearchClientOrderParam param){
        List<ClientOrderInfo> coiList = clientOrderPersonalMapper.getClientOrderInfoList(param);
        if(ObjectUtils.isNotNull(coiList) && coiList.size() > 0 && coiList.get(0).getOrderItems().size() > 0) {
            return JsonResult.successJsonResult(coiList.get(0).getOrderItems());
        }
        return JsonResult.successJsonResult(null);
    }
    
    /**
     * <p>客户确认签收发票</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月24日 上午11:51:41
     */
    public JsonResult<String> signingInvoice(@RequestBody SigningInvoiceParam param){
        JSR303ValidateUtils.validate(param);
        
        // 校验是否满足所有商户已经开发票
        ClientOrderPersonal copObj = clientOrderPersonalMapper.getClientOrderPersonalByClientOrderCode(param.getClientOrderCode());
        if(ObjectUtils.isNull(copObj)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单基本信息不存在");
        }
        // **********************根据客户订单id获取商户订单集合**********************
        List<MerchantOrder> list = merchantOrderMapper.selectMerchantOrderByClientOrderId(copObj.getId());
        if(ObjectUtils.isNull(list) || list.size() == 0) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "未找到商户订单");
        }
        
        for (int i = 0; i < list.size(); i++) {
            MerchantOrder mo = list.get(i);
            Long merchantOrderId = mo.getId();
            // **********************根据商户订单id获取商户发票详情**********************
            MerchantInvoice miObj = merchantInvoiceMapper.selectMerchantInvoiceByOrderId(merchantOrderId);
            if(ObjectUtils.isNotNull(miObj)) {
                if(miObj.getStatus() != MerchantInvoiceApplyStatusEnum.NOT_SIGNED.getValue()) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "可能部分发票已开出，稍后再全部签收");
                }
                // 商户发票状态更新已完成操作
                miObj.setStatus(MerchantInvoiceApplyStatusEnum.COMPLETED.getValue());
                merchantInvoiceMapper.updateByPrimaryKeySelective(miObj);
            }
            
        }
        // 客户发票状态更新已完成操作
        ClientInvoice clientInvoice = clientInvoiceMapper.getClientInvoiceByCLientOrderId(copObj.getId());
        if(ObjectUtils.isNull(clientInvoice)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户发票信息不存在");
        }
        
        clientInvoice.setStatus(ClientInvoiceType.COMPLETED.getValue());
        clientInvoiceMapper.updateByPrimaryKeySelective(clientInvoice);
        
        return JsonResult.successJsonResult();
    }
}

