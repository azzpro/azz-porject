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

import com.alibaba.fastjson.JSONArray;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.ClientConstants.ClientOrderOperationType;
import com.azz.core.constants.ClientConstants.ClientOrderStatus;
import com.azz.core.constants.ClientConstants.ClientOrderType;
import com.azz.core.constants.MerchantConstants.MerchantOrderStatusEnum;
import com.azz.core.constants.MerchantConstants.MerchantOrderType;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.client.mapper.ClientOrderOperationRecordMapper;
import com.azz.order.client.mapper.ClientOrderPersonalMapper;
import com.azz.order.client.mapper.ClientOrderStatusPersonalMapper;
import com.azz.order.client.pojo.ClientOrderOperationRecord;
import com.azz.order.client.pojo.ClientOrderPersonal;
import com.azz.order.client.pojo.ClientOrderStatusPersonal;
import com.azz.order.client.pojo.vo.ClientOrderDetail;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.order.client.pojo.vo.DeliveryInfo;
import com.azz.order.client.pojo.vo.ExpressFileInfo;
import com.azz.order.client.pojo.vo.SignFileInfo;
import com.azz.order.client.pojo.vo.SignInfo;
import com.azz.order.merchant.mapper.MerchantOrderItemMapper;
import com.azz.order.merchant.mapper.MerchantOrderMapper;
import com.azz.order.merchant.mapper.MerchantOrderStatusMapper;
import com.azz.order.merchant.pojo.MerchantOrder;
import com.azz.order.merchant.pojo.MerchantOrderItem;
import com.azz.order.merchant.pojo.MerchantOrderStatus;
import com.azz.order.platform.Merchant;
import com.azz.order.platform.bo.AllocateClientOrderParam;
import com.azz.order.platform.bo.MerchantOrderInfoParam;
import com.azz.order.platform.bo.SearchPlatformClientOrderParam;
import com.azz.order.platform.vo.AllocatedMerchantOrderInfo;
import com.azz.order.platform.vo.MerchantOrderInfo;
import com.azz.order.platform.vo.MerchantOrderItemInfo;
import com.azz.order.platform.vo.OrderOperationRecord;
import com.azz.order.platform.vo.PlatformClientOrderInfo;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.azz.util.SystemSeqUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>客户端订单业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月12日 下午3:14:43
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class PlatformClientOrderService {

	@Autowired
	private ClientOrderPersonalMapper clientOrderPersonalMapper;

	@Autowired
	private ClientOrderStatusPersonalMapper clientOrderStatusPersonalMapper;
	
	@Autowired
	private ClientOrderOperationRecordMapper clientOrderOperationRecordMapper;
	
	@Autowired
	private MerchantOrderMapper merchantOrderMapper;

	@Autowired
	private MerchantOrderStatusMapper merchantOrderStatusMapper;
	
	@Autowired
	private MerchantOrderItemMapper merchantOrderItemMapper;
	
	private DbSequenceService dbSequenceService;
	
	/**
	 * 
	 * <p>查询平台客户订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月13日 上午10:54:40
	 */
	public JsonResult<Pagination<PlatformClientOrderInfo>> getClientOrderInfoList(@RequestBody SearchPlatformClientOrderParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<PlatformClientOrderInfo> infos = clientOrderPersonalMapper.getPlatformClientOrderInfoList(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询客户订单详情</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月13日 上午10:56:03
	 */
	public JsonResult<ClientOrderDetail> getClientOrderDetail(String clientOrderCode){
		if(StringUtils.isBlank(clientOrderCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单号不允许为空");
		}
		// 查询订单详情
		ClientOrderInfo orderInfo = clientOrderPersonalMapper.getClientOrderDetailByClientOrderCode(clientOrderCode);
		if(orderInfo == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单不存在");
		}
		// 发货信息
		List<DeliveryInfo> deliveryInfos = clientOrderPersonalMapper.getDeliveryInfoByClientOrderCode(clientOrderCode);
		for (DeliveryInfo deliveryInfo : deliveryInfos) {
			if(deliveryInfo != null) {
				// 发货文件信息的json字符串
				String shipmentFileInfo = deliveryInfo.getShipmentFileInfo();
				List<ExpressFileInfo> expressFileInfos = JSONArray.parseArray(shipmentFileInfo, ExpressFileInfo.class);
				deliveryInfo.setExpressFileInfos(expressFileInfos);
			}
		}
		// 签收信息
		SignInfo signInfo = clientOrderPersonalMapper.getSignInfoByClientOrderCode(clientOrderCode);
		if(signInfo != null) {
			// 发货文件信息的json字符串
			String signFileInfo = signInfo.getSignFileInfo();
			List<SignFileInfo> signFileInfos = JSONArray.parseArray(signFileInfo, SignFileInfo.class);
			signInfo.setSignFileInfos(signFileInfos);
		}
		return JsonResult.successJsonResult(new ClientOrderDetail(orderInfo, deliveryInfos, signInfo));
	}
	
	/**
	 * 
	 * <p>查询客户订单拆单后的生成的商户订单</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月16日 上午11:14:56
	 */
	public JsonResult<AllocatedMerchantOrderInfo> getGeneratedMerchantOrderInfo(String clientOrderCode){
		if(StringUtils.isBlank(clientOrderCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单号不允许为空");
		}
		ClientOrderPersonal clientOrder = clientOrderPersonalMapper.getClientOrderPersonalByClientOrderCode(clientOrderCode);
		if(clientOrder == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户订单号不存在"); 
		}
		// 查询分配后的商户订单信息 
		AllocatedMerchantOrderInfo info = clientOrderPersonalMapper.getAllocatedMerchantOrderInfoByClientOrderCode(clientOrderCode);
		if(info == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "分配后的商户订单信息不存在"); 
		}
		// 查询客户订单拆单后的生成的商户订单列表
		List<MerchantOrderInfo> merchantOrderInfos = clientOrderPersonalMapper.getGeneratedMerchantOrderListByClientOrderCode(clientOrder.getId());
		info.setMerchantOrderInfos(merchantOrderInfos);
		return JsonResult.successJsonResult(info);
	}
	
	/**
	 * 
	 * <p>查询客户订单拆分后的商户订单列表</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月14日 下午3:58:10
	 */
	public JsonResult<AllocatedMerchantOrderInfo> getAllocatedMerchantOrder(String clientOrderCode){
		if(StringUtils.isBlank(clientOrderCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单号不允许为空");
		}
		ClientOrderPersonal clientOrder = clientOrderPersonalMapper.getClientOrderPersonalByClientOrderCode(clientOrderCode);
		if(clientOrder == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户订单号不存在"); 
		}
		// 查询分配后的商户订单信息 
		AllocatedMerchantOrderInfo info = clientOrderPersonalMapper.getAllocatedMerchantOrderInfoByClientOrderCode(clientOrderCode);
		if(info == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "分配后的商户订单信息不存在"); 
		}
		// 查询拆单后的商户订单列表
		List<MerchantOrderInfo> merchantOrderInfos = clientOrderPersonalMapper.getMerchantOrderListByClientOrderCode(clientOrderCode);
		info.setMerchantOrderInfos(merchantOrderInfos);
		return JsonResult.successJsonResult(info);
	}
	
	/**
	 * 
	 * <p>分配客户订单</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月14日 下午2:31:17
	 */
	public JsonResult<String> allocateClientOrder(@RequestBody AllocateClientOrderParam param){
		// 参数校验
		JSR303ValidateUtils.validate(param);
 		ClientOrderPersonal clientOrder = clientOrderPersonalMapper.getClientOrderPersonalByClientOrderCode(param.getClientOrderCode());
 		if(clientOrder == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户订单号不存在"); 
		}
 		if(ClientOrderStatus.NOT_CONFIRMED.getValue() != clientOrder.getOrderStatusId()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户订单状态异常"); 
		}
 		List<MerchantOrderInfoParam> infos = param.getInfos();
 		Date nowDate = new Date();
		for (MerchantOrderInfoParam info : infos) {
			JSR303ValidateUtils.validate(info);
			// 根据商户编码查询出来的这个商户订单列表
			MerchantOrderInfo merchantOrderInfo = clientOrderPersonalMapper.getMerchantOrderListByClientOrderCodeAndMerchantCode(param.getClientOrderCode(), info.getMerchantCode());
			if(merchantOrderInfo == null) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "拆单后的商户订单信息不存在"); 
			}
			Merchant merchant = merchantOrderMapper.selectMerchantByMerchantCode(merchantOrderInfo.getMerchantCode());
			if(merchant == null) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在"); 
			}
			// 新增一个待确认的商户订单
			String code = dbSequenceService.getMerchantOrderNumber();
			MerchantOrder merchantOrderRecord = MerchantOrder.builder()
					.clientOrderId(clientOrder.getId())
					.createTime(nowDate)
					.grandTotal(merchantOrderInfo.getEachMerchantGrandTotal())
					.merchantId(merchant.getId())
					.merchantOrderCode(SystemSeqUtils.getSeq(code))
					.orderStatusId(MerchantOrderStatusEnum.NOT_CONFIRMED.getValue())
					.orderType(MerchantOrderType.PERSON.getValue())
					.remark(info.getRemark())
					.build();
			merchantOrderMapper.insertSelective(merchantOrderRecord);
			
			// 新增商户订单状态变更记录
			MerchantOrderStatus merchantOrderStatusRecord = MerchantOrderStatus.builder()
					.createTime(nowDate)
					.creator(param.getAllocatePerson())
					.merchantOrderId(merchantOrderRecord.getId())
					.merchantStatusId(MerchantOrderStatusEnum.NOT_CONFIRMED.getValue())
					.remark(info.getRemark())
					.build();
			merchantOrderStatusMapper.insertSelective(merchantOrderStatusRecord);
			
			// 新增商户订单细项
			List<MerchantOrderItemInfo> items = merchantOrderInfo.getOrderItems();
			List<MerchantOrderItem> merchantOrderItemRecords = new ArrayList<>();
			for (MerchantOrderItemInfo itemInfo : items) {
				MerchantOrderItem item = MerchantOrderItem.builder()
						.assortmentName(itemInfo.getAssortmentName())
						.brandName(itemInfo.getBrandName())
						.createTime(nowDate)
						.creator(param.getAllocatePerson())
						.deliveryDate(itemInfo.getDeliveryDate())
						.deliveryTime(itemInfo.getDeliveryTime())
						.merchantOrderId(merchantOrderRecord.getId())
						.moduleName(itemInfo.getModuleName())
						.modulePicUrl(itemInfo.getModulePicUrl())
						.productCode(itemInfo.getProductCode())
						.productParamsName(itemInfo.getProductParamsName())
						.productPrice(itemInfo.getProductPrice())
						.quantity(itemInfo.getQuantity())
						.build();
				merchantOrderItemRecords.add(item);
			}
			merchantOrderItemMapper.batchInsert(merchantOrderItemRecords);
		}
		
		// 客户订单状态改为待配货
		ClientOrderPersonal clientOrderRecord = ClientOrderPersonal.builder()
				.modifier(param.getAllocatePerson())
				.modifyTime(nowDate)
				.orderStatusId(ClientOrderStatus.NOT_ALLOCATED.getValue())
				.handler(param.getAllocatePerson())
				.handlerTime(nowDate)
				.id(clientOrder.getId())
				.build();
		clientOrderPersonalMapper.updateByPrimaryKeySelective(clientOrderRecord);
		
		// 新增客户订单状态变更记录
		ClientOrderStatusPersonal clientOrderStatusRecord = ClientOrderStatusPersonal.builder()
				.createTime(nowDate)
				.creator(param.getAllocatePerson())
				.orderId(clientOrder.getId())
				.orderStatusId(ClientOrderStatus.NOT_ALLOCATED.getValue())
				.remark("平台端用户拆单确认")
				.build();
		clientOrderStatusPersonalMapper.insertSelective(clientOrderStatusRecord);
		
		// 新增客户订单的操作记录
		int allocatedAmount = infos.size();
		ClientOrderOperationRecord clientOrderOperationRecord = ClientOrderOperationRecord.builder()
				.clientOrderId(clientOrder.getId())
				.operator(param.getAllocatePerson())
				.optRemark("订单共被分配至" + allocatedAmount + "个商户")
				.optTime(nowDate)
				.optType(ClientOrderOperationType.ALLOCATE_ORDER.getValue())
				.orderType(ClientOrderType.PERSONAL.getValue())
				.build();
		clientOrderOperationRecordMapper.insertSelective(clientOrderOperationRecord);
		
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>查询客户订单操作记录</p>
	 * @param clientOrderCode
	 * @return
	 * @author 黄智聪  2018年11月16日 下午2:31:47
	 */
	public JsonResult<List<OrderOperationRecord>> getClientOrderOperationRecords(String clientOrderCode){
		if(StringUtils.isBlank(clientOrderCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单号不允许为空");
		}
		List<OrderOperationRecord> records = clientOrderOperationRecordMapper.getClientOrderOperationRecordByClientOrderCode(clientOrderCode);
		return JsonResult.successJsonResult(records);
	}
	
}

