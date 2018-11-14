/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.ClientConstants;
import com.azz.core.constants.ClientConstants.ShippingAddressStatus;
import com.azz.core.constants.ClientConstants.isDefaultShippingAddress;
import com.azz.core.constants.FileConstants;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.client.mapper.ClientOrderPersonalMapper;
import com.azz.order.client.mapper.ClientOrderShippingAddressMapper;
import com.azz.order.client.mapper.ClientSignForMapper;
import com.azz.order.client.pojo.ClientOrderPersonal;
import com.azz.order.client.pojo.ClientOrderShippingAddress;
import com.azz.order.client.pojo.ClientSignFor;
import com.azz.order.client.pojo.bo.AddShippingAddressParam;
import com.azz.order.client.pojo.bo.DelShippingAddressParam;
import com.azz.order.client.pojo.bo.EditShippingAddressParam;
import com.azz.order.client.pojo.bo.SearchClientOrderParam;
import com.azz.order.client.pojo.bo.SignForm;
import com.azz.order.client.pojo.bo.UploadFileInfo;
import com.azz.order.client.pojo.bo.UploadSignFormParam;
import com.azz.order.client.pojo.vo.ClientOrderDetail;
import com.azz.order.client.pojo.vo.ClientOrderInfo;
import com.azz.order.client.pojo.vo.DeliveryInfo;
import com.azz.order.client.pojo.vo.ExpressFileInfo;
import com.azz.order.client.pojo.vo.ShippingAddress;
import com.azz.order.client.pojo.vo.SignFileInfo;
import com.azz.order.client.pojo.vo.SignInfo;
import com.azz.order.merchant.mapper.ClientUserMapper;
import com.azz.order.merchant.pojo.ClientUser;
import com.azz.system.api.SystemImageUploadService;
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
public class ClientOrderService {

	@Autowired
	private ClientOrderPersonalMapper clientOrderPersonalMapper;
	
	@Autowired
	private ClientOrderShippingAddressMapper clientOrderShippingAddressMapper;
	
	@Autowired
	private ClientUserMapper clientUserMapper;
	
	@Autowired
	private ClientSignForMapper clientSignForMapper;
	
	@Autowired
    private SystemImageUploadService systemImageUploadService;
    
	/**
	 * 
	 * <p>查询客户端订单列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月13日 上午10:54:40
	 */
	public JsonResult<Pagination<ClientOrderInfo>> getClientOrderInfoList(@RequestBody SearchClientOrderParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<ClientOrderInfo> infos = clientOrderPersonalMapper.getClientOrderInfoList(param);
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
	 * <p>查询收货地址信息</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月13日 下午2:58:08
	 */
	public JsonResult<List<ShippingAddress>> getShippingAddressList(String clientUserCode){
		return JsonResult.successJsonResult(clientOrderShippingAddressMapper.getShippingAddressByClientUserCode(clientUserCode));
	}
	
	/**
	 * 
	 * <p>查询收货地址信息</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月13日 下午2:58:08
	 */
	public JsonResult<ShippingAddress> getShippingAddress(Long shippingId){
		ShippingAddress address = clientOrderShippingAddressMapper.getShippingAddressByShippingId(shippingId);
		if(address == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "收货地址不存在");
		}
		return JsonResult.successJsonResult(address);
	}
	
	/**
	 * 
	 * <p>新增收货地址信息</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月13日 下午2:58:08
	 */
	public JsonResult<String> AddShippingAddress(@RequestBody AddShippingAddressParam param){
		// 参数校验
		JSR303ValidateUtils.validate(param);
		ClientUser user = clientUserMapper.getClientUserByClientUserCode(param.getClientUserCode());
		if(user == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户不存在");
		}
		int count = clientOrderShippingAddressMapper.countShippingAddressByClientUserCode(param.getClientUserCode());
		if(count > ClientConstants.SHIPPING_ADDRESS_AMOUNT_LIMIT) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "收货地址不能超过10个");
		}
		// 若为默认地址，将该客户的其他地址改为非默认
		if(isDefaultShippingAddress.YES.getValue() == param.getIsDefault()) {
			clientOrderShippingAddressMapper.setOtherShippingAddressNotDefault(user.getId());
		}
		ClientOrderShippingAddress shippingAddressRecord = ClientOrderShippingAddress.builder()
				.addressAlias(param.getAddressAlias())
				.areaCode(param.getAreaCode())
				.areaName(param.getAreaName())
				.cityCode(param.getCityCode())
				.cityName(param.getCityName())
				.clientUserId(user.getId())
				.createTime(new Date())
				.creator(param.getCreator())
				.detailAddress(param.getDetailAddress())
				.isDefault(param.getIsDefault())
				.provinceCode(param.getProvinceCode())
				.provinceName(param.getProvinceName())
				.receiverName(param.getReceiverName())
				.receiverPhoneNumber(param.getReceiverPhoneNumber())
				.build();
		clientOrderShippingAddressMapper.insertSelective(shippingAddressRecord);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>修改收货地址信息</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月13日 下午2:58:08
	 */
	public JsonResult<String> EditShippingAddress(@RequestBody EditShippingAddressParam param){
		// 参数校验
		JSR303ValidateUtils.validate(param);
		ClientUser user = clientUserMapper.getClientUserByClientUserCode(param.getClientUserCode());
		if(user == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户不存在");
		}
		ShippingAddress address = clientOrderShippingAddressMapper.getShippingAddressByShippingId(param.getShippingId());
		if(address == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "收货地址不存在");
		}
		// 若为默认地址，将该用户的其他地址改为非默认
		if(isDefaultShippingAddress.YES.getValue() == param.getIsDefault()) {
			clientOrderShippingAddressMapper.setOtherShippingAddressNotDefault(user.getId());
		}
		ClientOrderShippingAddress shippingAddressRecord = ClientOrderShippingAddress.builder()
				.id(param.getShippingId())
				.addressAlias(param.getAddressAlias())
				.areaCode(param.getAreaCode())
				.areaName(param.getAreaName())
				.cityCode(param.getCityCode())
				.cityName(param.getCityName())
				.clientUserId(user.getId())
				.detailAddress(param.getDetailAddress())
				.isDefault(param.getIsDefault())
				.provinceCode(param.getProvinceCode())
				.provinceName(param.getProvinceName())
				.modifier(param.getModifier())
				.modifyTime(new Date())
				.receiverName(param.getReceiverName())
				.receiverPhoneNumber(param.getReceiverPhoneNumber())
				.build();
		clientOrderShippingAddressMapper.updateByPrimaryKeySelective(shippingAddressRecord);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>查询收货地址信息</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月13日 下午2:58:08
	 */
	public JsonResult<String> delShippingAddress(@RequestBody DelShippingAddressParam param){
		ClientOrderShippingAddress shippingAddressRecord = ClientOrderShippingAddress.builder()
				.id(param.getShippingId())
				.status(ShippingAddressStatus.INVALID.getValue())
				.modifier(param.getModifier())
				.modifyTime(new Date())
				.build();
		clientOrderShippingAddressMapper.updateByPrimaryKeySelective(shippingAddressRecord);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>上传签收单</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月14日 上午10:55:45
	 */
	public JsonResult<String> uploadSignForm(@RequestBody UploadSignFormParam param){
		// 参数校验
		JSR303ValidateUtils.validate(param);
		ClientOrderPersonal order = clientOrderPersonalMapper.getClientOrderPersonalByClientOrderCode(param.getClientOrderCode());
		if(order == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "签收单所属订单不存在");
		}
		List<UploadFileInfo> uploadFiles = new ArrayList<>();
		List<SignForm> signForms = param.getSignForms();
		for (int i = 0 ; i < signForms.size(); i++) {
			SignForm signForm = signForms.get(i);
			String originalFileName = signForm.getFileName();
		    if(StringUtils.isBlank(originalFileName)) {
		    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个签收单文件名为空");
		    }
		    if(signForm.getFileSize() > ClientConstants.SIGN_FORM_FILE_SIZE_LIMIT) {
		    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个签收单文件大小不能超过10M");
		    }
		    String filedata = signForm.getFileBase64Str();
		    if(StringUtils.isBlank(filedata)) {
		    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个签收单文件内容为空");
		    }
		    int dotIndex = originalFileName.lastIndexOf(".");
		    String fileNameNoSufix = originalFileName.substring(0, dotIndex);
		    String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
		    // 新名称为文件名 + 客户订单编码 + 第几张
		    String newFileName = fileNameNoSufix + "_" + param.getClientOrderCode() + "_" + (i+1);
		    // 图片url
		    JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix,
			    filedata, FileConstants.AZZ_CLIENT, FileConstants.AZZ_SIGN_FORM_IMAGE_TYPE);
		    if(jr.getCode() == SystemErrorCode.SUCCESS.getCode()) {
		    	UploadFileInfo file = new UploadFileInfo(jr.getData(), originalFileName);
		    	uploadFiles.add(file);
		    }else {
		    	throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE,"签收单上传失败，请重试");
		    }
		    // json字符串
		    String signFileInfo = JSON.toJSONString(uploadFiles);
			ClientSignFor clientSignForRecord = ClientSignFor.builder()
					.clientOrderId(order.getId())
					.consignee(param.getConsignee())
					.createTime(new Date())
					.creator(param.getCreator())
					.signFileInfo(signFileInfo)
					.build();
			clientSignForMapper.insertSelective(clientSignForRecord);
		}
		return JsonResult.successJsonResult();
	}
	
}

