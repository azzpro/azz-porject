/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.MerchantConstants.MerchantInvoiceApplyStatusEnum;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.merchant.mapper.MerchantInvoiceLogisticsMapper;
import com.azz.order.merchant.mapper.MerchantInvoiceMapper;
import com.azz.order.merchant.mapper.MerchantOrderMapper;
import com.azz.order.merchant.pojo.MerchantInvoice;
import com.azz.order.merchant.pojo.MerchantInvoiceLogistics;
import com.azz.order.merchant.pojo.MerchantOrder;
import com.azz.order.merchant.pojo.bo.ConfirmBillingParam;
import com.azz.order.merchant.pojo.bo.OrderInvoiceParam;
import com.azz.order.merchant.pojo.bo.SearchInvoiceListParam;
import com.azz.order.merchant.pojo.bo.SearchOrderDetailParam;
import com.azz.order.merchant.pojo.vo.MerchantInvoiceDetail;
import com.azz.order.merchant.pojo.vo.MerchantInvoiceList;
import com.azz.order.merchant.pojo.vo.OrderDetail;
import com.azz.order.merchant.pojo.vo.OrderItem;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月22日 下午7:58:40
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class MerchantInvoiceService {
	
	@Autowired
	private MerchantOrderMapper merchantOrderMapper;
	
	@Autowired
    private MerchantInvoiceMapper merchantInvoiceMapper;
	
	@Autowired
    private MerchantInvoiceLogisticsMapper merchantInvoiceLogisticsMapper;
	/**
	 * <p>商户发票管理列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月22日 下午8:25:02
	 */
	public JsonResult<Pagination<MerchantInvoiceList>> getMerchantInvoiceList(@RequestBody SearchInvoiceListParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<MerchantInvoiceList> list = merchantInvoiceMapper.getMerchantInvoiceList(param);
		return JsonResult.successJsonResult(new Pagination<>(list));
	}
	
	
	/**
	 * <p>商户发票详情</p>
	 * @param merchantOrderCode
	 * @return
	 * @author 彭斌  2018年11月23日 下午2:35:04
	 */
	public JsonResult<MerchantInvoiceDetail> getMerchantInvoiceDetail(String merchantOrderCode){
	    if(null == merchantOrderCode) {
	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "参数无效");
	    }
	    MerchantInvoiceDetail mid = merchantInvoiceMapper.getMerchantInvoiceDetailInfo(merchantOrderCode);
	    
	    // 产品明细
	    if(ObjectUtils.isNotNull(mid)) {
	        SearchOrderDetailParam param = new SearchOrderDetailParam();
	        param.setOrderCode(mid.getMerchantOrderCode());
	        param.setMerchantId(mid.getMerchantId());
	        OrderDetail od = merchantOrderMapper.selectOrderInfo(param);
	        List<OrderItem> oi = od.getOrderItems();
	        mid.setOrderItems(oi);
	    }
	    return JsonResult.successJsonResult(mid);
	}
	
	/**
	 * <p>商户确认开票申请</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月23日 下午6:13:34
	 */
	public JsonResult<String> confirmBillingApplication(@RequestBody ConfirmBillingParam param){
	    JSR303ValidateUtils.validate(param);
	    MerchantOrder mo = merchantOrderMapper.selectMerchantOrderInfo(param.getMerchantOrderCode());
	    
	    if(ObjectUtils.isNull(mo)) {
	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "未找到订单信息");
	    }
	    
	    Long merichatOrderId = mo.getId();
	    
	    MerchantInvoice mi = merchantInvoiceMapper.selectMerchantInvoiceByOrderId(merichatOrderId);
	    if(ObjectUtils.isNull(mi)) {
	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "未找到商户发票信息");
	    }
	    mi.setStatus(MerchantInvoiceApplyStatusEnum.NOT_INVOICED.getValue());
	    mi.setModifier(param.getMerchantUserCode());
	    mi.setModifyTime(new Date());
	    merchantInvoiceMapper.updateByPrimaryKeySelective(mi);
	    return JsonResult.successJsonResult();
	}
	
	/**
	 * <p>订单开票</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月23日 下午6:51:25
	 */
	public JsonResult<String> orderInvoice(@RequestBody OrderInvoiceParam param){
	    MerchantOrder mo = merchantOrderMapper.selectMerchantOrderInfo(param.getMerchantOrderCode());
	    if(ObjectUtils.isNull(mo)) {
	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "未找到订单信息");
	    }
	    MerchantInvoice mi = merchantInvoiceMapper.selectMerchantInvoiceByOrderId(mo.getId());
	    if(ObjectUtils.isNull(mi)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "未找到商户发票信息");
        }
	    MerchantInvoiceLogistics mil = new MerchantInvoiceLogistics();
	    if(param.getDeliveryType() == 0) {
	        mil.setExpressCompanyId(param.getExpressCompanyId());
	        mil.setNumber(param.getNumber());
	    } else if(param.getDeliveryType() == 1) {
	        mil.setDeliveryPerson(param.getDeliveryPerson());
	        mil.setDeliveryPhone(param.getDeliveryPhone());
	    } else {
	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "发票物流信息异常");
	    }
	    mil.setCreateTime(new Date());
	    mil.setCreator(param.getMerchantUserCode());
	    mil.setDeliveryType(param.getDeliveryType());
	    mil.setMerchantInvoiceId(mi.getId());
	    merchantInvoiceLogisticsMapper.insertSelective(mil);
	    
	    return JsonResult.successJsonResult();
	}
	
}

