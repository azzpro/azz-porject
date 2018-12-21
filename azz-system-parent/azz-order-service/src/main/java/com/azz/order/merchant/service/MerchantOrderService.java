/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月12日 下午3:14:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.merchant.service;

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
import com.azz.core.constants.ClientConstants.ClientOrderStatus;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.MerchantConstants.MerchantOrderStatusEnum;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.client.mapper.ClientOrderPersonalMapper;
import com.azz.order.client.mapper.ClientOrderStatusPersonalMapper;
import com.azz.order.client.pojo.ClientOrderPersonal;
import com.azz.order.client.pojo.ClientOrderStatusPersonal;
import com.azz.order.merchant.mapper.ExpressCompanyMapper;
import com.azz.order.merchant.mapper.MerchantOrderLogisticsMapper;
import com.azz.order.merchant.mapper.MerchantOrderMapper;
import com.azz.order.merchant.mapper.MerchantOrderStatusMapper;
import com.azz.order.merchant.pojo.MerchantOrder;
import com.azz.order.merchant.pojo.MerchantOrderLogistics;
import com.azz.order.merchant.pojo.MerchantOrderStatus;
import com.azz.order.merchant.pojo.bo.EditOrderStatus;
import com.azz.order.merchant.pojo.bo.SearchOrderDetailParam;
import com.azz.order.merchant.pojo.bo.SearchOrderListParam;
import com.azz.order.merchant.pojo.bo.ShipmentFile;
import com.azz.order.merchant.pojo.vo.ExpressCompanyInfo;
import com.azz.order.merchant.pojo.vo.OrderDetail;
import com.azz.order.merchant.pojo.vo.OrderList;
import com.azz.order.merchant.pojo.vo.ReceiverAddress;
import com.azz.order.merchant.pojo.vo.ShipInfo;
import com.azz.order.merchant.pojo.vo.SignFileInfo;
import com.azz.order.merchant.pojo.vo.SignForInfo;
import com.azz.system.api.SystemImageUploadService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.StringUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>商户订单管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月13日 下午5:04:00
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class MerchantOrderService {
	
	@Autowired
	private MerchantOrderMapper merchantOrderMapper;
	
	@Autowired
    private MerchantOrderStatusMapper merchantOrderStatusMapper;
	
	@Autowired
    private MerchantOrderLogisticsMapper merchantOrderLogisticsMapper;
	
	@Autowired
	private ExpressCompanyMapper expressCompanyMapper;
	
	@Autowired
    private ClientOrderPersonalMapper clientOrderPersonalMapper;
    
    @Autowired
    private ClientOrderStatusPersonalMapper clientOrderStatusPersonalMapper;
	
	@Autowired
	private SystemImageUploadService systemImageUploadService;
	/**
	 * <p>查询商户订单管理</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月13日 下午5:07:04
	 */
	public JsonResult<Pagination<OrderList>> getMerchantOrderList(@RequestBody SearchOrderListParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<OrderList> list = merchantOrderMapper.selectOrderList(param);
		return JsonResult.successJsonResult(new Pagination<>(list));
	}
	
	/**
	 * <p>订单详情</p>
	 * @param clientOrderCode
	 * @return
	 * @author 彭斌  2018年11月13日 下午5:08:02
	 */
	public JsonResult<OrderDetail> getMerchantOrderDetail(@RequestBody SearchOrderDetailParam param){
	    JSR303ValidateUtils.validate(param);
	    // 获取订单基本详情和产品详情信息
	    OrderDetail od = merchantOrderMapper.selectOrderInfo(param);
		if(ObjectUtils.isNotNull(od)) {
		    String merchantOrderCode = param.getOrderCode();
		    // 获取收货地址信息
		    ReceiverAddress receiverAddress = merchantOrderMapper.selectReceiverAddressInfo(merchantOrderCode);
		    if(ObjectUtils.isNotNull(receiverAddress)) {
		        od.setReceiverAddress(receiverAddress);
		    }
		    // 获取发货信息
		    ShipInfo shipInfo = merchantOrderMapper.selectShipInfo(merchantOrderCode);
		    if(ObjectUtils.isNotNull(shipInfo)) {
		        String shipmentFileInfoJson =shipInfo.getShipmentFileInfo();
		        List<SignFileInfo> signFileInfos = JSONArray.parseArray(shipmentFileInfoJson, SignFileInfo.class);
		        shipInfo.setShipmentFileInfos(signFileInfos);
		        od.setShipInfo(shipInfo);
            }
		    
		    // 获取签收信息
		    SignForInfo signForInfo = merchantOrderMapper.selectSignFor(merchantOrderCode);
		    if(ObjectUtils.isNotNull(signForInfo)) {
		        String signFileInfoJson = signForInfo.getSignFileInfo();
		        List<SignFileInfo> signFileInfos = JSONArray.parseArray(signFileInfoJson, SignFileInfo.class);
		        signForInfo.setSignFileInfos(signFileInfos);
		        od.setSignForInfo(signForInfo);
            }
		}
		return JsonResult.successJsonResult(od);
	}

	
	/**
	 * <p>订单流转</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月14日 上午9:59:49
	 */
	public JsonResult<String> editOrderStatus(@RequestBody EditOrderStatus param){
	    JSR303ValidateUtils.validate(param);
	    
	    MerchantOrder mo = merchantOrderMapper.selectMerchantOrderInfo(param.getOrderCode());
	    if(ObjectUtils.isNull(mo)) {
	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单不存在");
	    }
	    // 校验订单状态合法性
	    Integer statusId = mo.getOrderStatusId();
	    boolean isExist = MerchantOrderStatusEnum.checkStatusExist(statusId);
	    if(!isExist) {
	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "无效的订单状态");
	    }
	    
	    if(MerchantOrderStatusEnum.NOT_SENT_OUT.getValue() != statusId &&
	            MerchantOrderStatusEnum.NOT_CONFIRMED.getValue() != statusId) {
	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单状态超出变更范围");
	    }
	    
	    /*SearchOrderStatusParam sosp = new SearchOrderStatusParam();
        sosp.setMerchantOrderId(mo.getId());
        sosp.setMerchantStatusId(param.getStatus());
        MerchantOrderStatus mos = merchantOrderStatusMapper.selectOrderStatus(sosp);
        if(ObjectUtils.isNull(mos)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "订单状态已变更");
        }*/
        
        // 查询客户订单信息
        ClientOrderPersonal clientOrderObj = clientOrderPersonalMapper.selectByPrimaryKey(mo.getClientOrderId());
        if(ObjectUtils.isNull(clientOrderObj)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户订单未找到");
        }
        // 客户订单状态变更记录
        ClientOrderStatusPersonal cosp = new ClientOrderStatusPersonal();
        
        if(MerchantOrderStatusEnum.NOT_CONFIRMED.getValue() == statusId) {
            clientOrderObj.setOrderStatusId(ClientOrderStatus.NOT_ALLOCATED.getValue());
            mo.setOrderStatusId(MerchantOrderStatusEnum.NOT_SENT_OUT.getValue());
            cosp.setOrderStatusId(ClientOrderStatus.NOT_ALLOCATED.getValue());
        } else if(MerchantOrderStatusEnum.NOT_SENT_OUT.getValue() == statusId) {
            clientOrderObj.setOrderStatusId(ClientOrderStatus.NOT_SIGNED.getValue());
            mo.setOrderStatusId(MerchantOrderStatusEnum.NOT_SIGNED.getValue());
            cosp.setOrderStatusId(ClientOrderStatus.NOT_SIGNED.getValue());
        }
        
	    // 变更商户订单头部信息
	    mo.setModifier(param.getModifier());
	    mo.setModifyTime(new Date());
	    
	    // 新增商户订单状态信息
	    MerchantOrderStatus mos = new MerchantOrderStatus();
        mos.setMerchantOrderId(mo.getId());
        if(param.getStatus() == MerchantOrderStatusEnum.NOT_CONFIRMED.getValue()) {
            mos.setMerchantStatusId(MerchantOrderStatusEnum.NOT_SENT_OUT.getValue());
        } else if(param.getStatus() == MerchantOrderStatusEnum.NOT_SENT_OUT.getValue()) {
            mos.setMerchantStatusId(MerchantOrderStatusEnum.NOT_SIGNED.getValue());
        }
        mos.setCreator(param.getModifier());
        mos.setCreateTime(new Date());
        mos.setRemark("商户端订单变更记录【待确认-待发货】");
        
	    merchantOrderMapper.updateByPrimaryKeySelective(mo);
	    
	    
	    // 变更客户订单状态
	    clientOrderObj.setModifier(param.getModifier());
	    clientOrderObj.setModifyTime(new Date());
	    clientOrderPersonalMapper.updateByPrimaryKey(clientOrderObj);
	    
	    // 变更客户订单状态记录
	    cosp.setOrderId(mo.getId());
	    cosp.setCreator(param.getModifier());
	    cosp.setCreateTime(new Date());
	    clientOrderStatusPersonalMapper.insertSelective(cosp);
	    
	    // 订单发货需记录发货信息
	    if(MerchantOrderStatusEnum.NOT_SENT_OUT.getValue() == statusId) {
	    	if(null == param.getDeliveryType()) {
	    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "配送方式为必选项");
	    	}
	    	if(param.getDeliveryType() == 1) {
	    	    if(null == param.getExpressCompanyId()) {
	    	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "快递公司为必选项");
	    	    }
	    	    if(null == param.getNumber()) {
	    	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "单号为必选项");
	    	    }
	    	}
	    	if(param.getDeliveryType() == 2) {
	    	    if(null == param.getLogistiscCompanyName()) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "物流公司为必选项");
                }
	    	}
	    	if(param.getDeliveryType() == 3) {
	    	    if(null == param.getDeliveryPerson()) {
	    	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "配送人员为必选项");
	    	    }
	    	    if(null == param.getDeliveryPhoneNumber()) {
	    	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "配送人员联系方式为必选项");
	    	    }
	    	}
	    	if(param.getShipmentFiles().size() == 0) {
	    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "出货信息为必选项");
	    	}
	    	
	    MerchantOrderLogistics record = new MerchantOrderLogistics();
	    //  1快递 2物流 3自送
	    switch (param.getDeliveryType()) {
	    case 1:
	    	record.setExpressCompanyId(param.getExpressCompanyId());
	    	record.setNumber(param.getNumber());
	    	break;
	    case 2:
	    	record.setLogistiscCompanyName(param.getLogistiscCompanyName());
	    	record.setNumber(param.getNumber());
	    	break;
	    case 3:
	    	record.setDeliveryPerson(param.getDeliveryPerson());
	    	record.setDeliveryPhoneNumber(param.getDeliveryPhoneNumber());
	    	break;
	    default:
	    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "配送方式不存在");
	    }
	    
	    List<SignFileInfo> uploadFileInfos = new ArrayList<>();
	    
	    List<ShipmentFile> shipmentFiles = param.getShipmentFiles();
	    for (int i = 0; i < shipmentFiles.size(); i++) {
	    	ShipmentFile shipmentFile =  shipmentFiles.get(i);
	    	if(StringUtils.isBlank(shipmentFile.getFileName())) {
	    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个出库单文件名为空");
	    	}
	    	if(shipmentFile.getFileSize() > ClientConstants.BUSINESS_LICENSE_FILE_SIZE_LIMIT) {
	    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个出库单文件大小不能超过20M");
	    	}
	    	String filedata = shipmentFile.getFileBase64Str();
	    	if(StringUtils.isBlank(filedata)) {
	    		throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第" + (i + 1) + "个出库单文件内容为空");
	    	}
	    	
	    	int dotIndex = shipmentFile.getFileName().lastIndexOf(".");
	    	String fileNameNoSufix = shipmentFile.getFileName().substring(0, dotIndex);
	    	String sufix = shipmentFile.getFileName().substring(dotIndex + 1, shipmentFile.getFileName().length());
	    	// 新名称为文件名  + 第几张
	    	String newFileName = fileNameNoSufix + "_" + param.getOrderCode() + "_" + (i+1);
	    	// 图片url
	    	JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix,
	    			filedata, FileConstants.AZZ_MERCHANT, FileConstants.AZZ_SHIPMENT_FORM_IMAGE_TYPE);
	    	if(jr.getCode() == SystemErrorCode.SUCCESS.getCode()) {
	    		SignFileInfo file = new SignFileInfo(shipmentFile.getFileName(),jr.getData());
	    		uploadFileInfos.add(file);
	    	}else {
	    		throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE,"出库单上传失败，请重试");
	    	}
	    }
	    
    	    String shipmentInfoUrl = JSON.toJSONString(uploadFileInfos);
    	    record.setMerchantOrderId(mo.getId());
    	    record.setDeliveryType(param.getDeliveryType());
    	    record.setShipmentFileInfo(shipmentInfoUrl);
    	    record.setCreateTime(new Date());
    	    record.setCreator(param.getModifier());
    	    merchantOrderLogisticsMapper.insertSelective(record);
    	    mos.setRemark("商户端订单变更记录【待发货-待签收】");
	    }
	    
	    merchantOrderStatusMapper.insertSelective(mos);
	    
	    return JsonResult.successJsonResult();
	}
	
	/**
	 * <p>获取所有快递公司列表</p>
	 * @return
	 * @author 彭斌  2018年11月16日 上午9:21:22
	 */
	public JsonResult<List<ExpressCompanyInfo>> getAllExpressCompany(){
	    return JsonResult.successJsonResult(expressCompanyMapper.selectAll());
	}
	
}

