/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.platform.service;

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
import com.azz.core.constants.MerchantConstants.MerchantOrderStatus;
import com.azz.core.constants.MerchantConstants.MerchantOrderType;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.client.mapper.ClientOrderPersonalMapper;
import com.azz.order.client.pojo.ClientOrderPersonal;
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
import com.azz.order.platform.Merchant;
import com.azz.order.platform.bo.AllocateClientOrderParam;
import com.azz.order.platform.bo.MerchantOrderInfoParam;
import com.azz.order.platform.bo.SearchPlatformClientOrderParam;
import com.azz.order.platform.vo.AllocatedMerchantOrderInfo;
import com.azz.order.platform.vo.MerchantOrderInfo;
import com.azz.order.platform.vo.PlatformClientOrderInfo;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
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
	private MerchantOrderMapper merchantOrderMapper;

	@Autowired
	private MerchantOrderStatusMapper merchantOrderStatusMapper;
	
	@Autowired
	private MerchantOrderItemMapper merchantOrderItemMapper;
	
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
		List<MerchantOrderInfo> merchantOrderInfos = clientOrderPersonalMapper.getMerchantOrderListByClientOrderCode(clientOrderCode, null);
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
 		List<MerchantOrderInfoParam> infos = param.getInfos();
 		Date nowDate = new Date();
		for (MerchantOrderInfoParam info : infos) {
			JSR303ValidateUtils.validate(info);
			// 根据商户编码查询拆单后的商户订单信息
			List<MerchantOrderInfo> merchantOrderInfos = clientOrderPersonalMapper.getMerchantOrderListByClientOrderCode(param.getClientOrderCode(), info.getMerchantCode());
			if(merchantOrderInfos.size() == 0) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "拆单后的商户订单信息不存在"); 
			}
			// 根据商户编码查询出来的这个商户订单列表，按道理来说只会查询到一条，因此get(0)获取其具体的信息
			MerchantOrderInfo merchantOrderInfo = merchantOrderInfos.get(0);
			Merchant merchant = merchantOrderMapper.selectMerchantByMerchantCode(merchantOrderInfo.getMerchantCode());
			if(merchant == null) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商户不存在"); 
			}
			MerchantOrder merchantOrderRecord = MerchantOrder.builder()
					.clientOrderId(clientOrder.getId())
					.createTime(nowDate)
					.grandTotal(merchantOrderInfo.getEachMerchantGrandTotal())
					.merchantId(merchant.getId())
					.merchantOrderCode(System.currentTimeMillis() + "")// TODO
					.orderStatusId(MerchantOrderStatus.NOT_CONFIRMED.getValue())
					.orderType(MerchantOrderType.PERSON.getValue())
					.remark(info.getRemark())
					.build();
			merchantOrderMapper.insertSelective(merchantOrderRecord);
			//MerchantOrderStatus merchantOrderStatusRecord = MerchantOrderStatus
			//merchantOrderStatusMapper.insertSelective(merchantOrderStatusRecord);
		}
		return JsonResult.successJsonResult();
	}
	
}

