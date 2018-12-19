/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月19日 下午4:07:49
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.selection.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.QueryPage;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.ClientConstants.ClientOrderStatus;
import com.azz.core.constants.ClientConstants.PayStatus;
import com.azz.core.constants.ClientConstants.SelectionRecordStatus;
import com.azz.exception.JSR303ValidationException;
import com.azz.order.client.mapper.ClientOrderItemPersonalMapper;
import com.azz.order.client.mapper.ClientOrderPersonalMapper;
import com.azz.order.client.mapper.ClientOrderShippingAddressMapper;
import com.azz.order.client.mapper.ClientOrderStatusPersonalMapper;
import com.azz.order.client.pojo.ClientOrderItemPersonal;
import com.azz.order.client.pojo.ClientOrderPersonal;
import com.azz.order.client.pojo.ClientOrderShippingAddress;
import com.azz.order.client.pojo.ClientOrderStatusPersonal;
import com.azz.order.client.pojo.bo.SearchClientOrderParam;
import com.azz.order.merchant.mapper.ClientUserMapper;
import com.azz.order.merchant.pojo.ClientUser;
import com.azz.order.selection.ClientSelectionRecord;
import com.azz.order.selection.ClientShoppingCart;
import com.azz.order.selection.bo.AddSelectionRecordParam;
import com.azz.order.selection.bo.AddToShoppingCartParam;
import com.azz.order.selection.bo.CallBackParam;
import com.azz.order.selection.bo.DelSelectionRecordParam;
import com.azz.order.selection.bo.OrderItem;
import com.azz.order.selection.bo.OrderParam;
import com.azz.order.selection.bo.SearchCombinationInitParamsParam;
import com.azz.order.selection.bo.SearchInitParamsParam;
import com.azz.order.selection.bo.SearchInitParamsParamWithSort;
import com.azz.order.selection.bo.SearchSelectionModuleParam;
import com.azz.order.selection.bo.SearchSelectionRecordParam;
import com.azz.order.selection.vo.ClassificationInfo;
import com.azz.order.selection.vo.CombinationDetail;
import com.azz.order.selection.vo.CombinationInfo;
import com.azz.order.selection.vo.CombinationInitParams;
import com.azz.order.selection.vo.InitParams;
import com.azz.order.selection.vo.ModuleDetail;
import com.azz.order.selection.vo.ModuleInfo;
import com.azz.order.selection.vo.Params;
import com.azz.order.selection.vo.ProductInfo;
import com.azz.order.selection.vo.ProductInfomation;
import com.azz.order.selection.vo.ProductParams;
import com.azz.order.selection.vo.ProductPrice;
import com.azz.order.selection.vo.SelectionCaseInfo;
import com.azz.order.selection.vo.SelectionIndexData;
import com.azz.order.selection.vo.SelectionModuleInfo;
import com.azz.order.selection.vo.SelectionRecord;
import com.azz.order.selection.vo.ShoppingCartItemInfo;
import com.azz.order.selection.vo.ShoppingCartProductInfo;
import com.azz.selection.mapper.ClientSelectionRecordMapper;
import com.azz.selection.mapper.ClientShoppingCartMapper;
import com.azz.selection.mapper.SelectionMapper;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.DateUtils;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.azz.util.SystemSeqUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>选型业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月19日 下午4:07:49
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class SelectionService {

	@Autowired
	private SelectionMapper selectionMapper;
	
	@Autowired
	private ClientUserMapper clientUserMapper;
	
	@Autowired
	private ClientSelectionRecordMapper clientSelectionRecordMapper;
	
	@Autowired
	private ClientShoppingCartMapper clientShoppingCartMapper;
	
	@Autowired
	private ClientOrderPersonalMapper clientOrderPersonalMapper;
	
	@Autowired
	private ClientOrderStatusPersonalMapper clientOrderStatusPersonalMapper;
	
	@Autowired
	private ClientOrderItemPersonalMapper clientOrderItemPersonalMapper;
	
	@Autowired
	private ClientOrderShippingAddressMapper clientOrderShippingAddressMapper;
	
	@Autowired
	private DbSequenceService dbSequenceService;

	/**
	 * 
	 * <p>查询选型的所有方案列表     1</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月19日 下午5:35:02
	 */
	public JsonResult<Pagination<SelectionCaseInfo>> getSelectionCaseInfos(@RequestBody QueryPage param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<SelectionCaseInfo> infos = selectionMapper.getSelectionCaseInfos();
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询初始化参数   2</p>
	 * @param caseCode
	 * @return
	 * @author 黄智聪  2018年11月20日 下午7:32:14
	 */
	public JsonResult<List<InitParams>> getInitParamsByCaseCode(@RequestBody SearchInitParamsParam param){
		JSR303ValidateUtils.validate(param);
		return JsonResult.successJsonResult(selectionMapper.getInitParams(param));
	}
	
	/**
	 * 
	 * <pre>
	 * 		根据参数完善页中选中的参数，获取符合这些参数的产品的公共参数   3
	 * 	额外说明：
	 * 	   	此方法查询的是，根据参数完善页面所选中的参数，
	 * 	   	查询有哪些产品的包含这些选中的参数的，
	 * 		然后筛选出这些产品所属的推荐组合下的所有产品有哪些公共参数。
	 * </pre>
	 * @param caseCode
	 * @return
	 * @author 黄智聪  2018年11月20日 下午7:32:14
	 */
	public JsonResult<List<CombinationInitParams>> getCombinationInitParams(@RequestBody SearchInitParamsParam param){
		JSR303ValidateUtils.validate(param);
		List<CombinationInitParams> infos = selectionMapper.getCombinationInitParams(param);
		return JsonResult.successJsonResult(infos);
	}
	
	/**
	 * 
	 * <pre>
	 * 		根据参数完善页中选中的参数，获取符合这些参数的产品的公共参数   3.1
	 * </pre>
	 * @param caseCode
	 * @return
	 * @author 黄智聪  2018年11月20日 下午7:32:14
	 */
	public JsonResult<List<CombinationInitParams>> getCombinationParams(@RequestBody SearchInitParamsParam param){
		JSR303ValidateUtils.validate(param);
		List<CombinationInitParams> infos = selectionMapper.getCombinationParams(param);
		return JsonResult.successJsonResult(infos);
	}
	
	/**
	 * 
	 * <p>查询推荐组合列表 4 </p>
	 * @param caseCode
	 * @return
	 * @author 黄智聪  2018年11月20日 下午7:32:14
	 */
	public JsonResult<Pagination<CombinationInfo>> getCombinationInfos(@RequestBody SearchInitParamsParamWithSort param){
		JSR303ValidateUtils.validate(param);
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<CombinationInfo> infos = selectionMapper.getCombinationInfos(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询推荐组合详情（包含推荐组合的信息、所包含的产品的公共选型参数、产品列表）  5</p>
	 * @param combinationCode
	 * @return
	 * @author 黄智聪  2018年11月22日 下午3:12:23
	 */
	public JsonResult<CombinationDetail> getCombinationDetail(@RequestBody SearchCombinationInitParamsParam searchParam){
		JSR303ValidateUtils.validate(searchParam);
		// 推荐组合的信息
		CombinationDetail detail = selectionMapper.getCombinationDetail(searchParam.getCombinationCode());
		if(detail == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐组合不存在");
		}
		// 所包含的产品的公共选型参数
		SearchCombinationInitParamsParam param = new SearchCombinationInitParamsParam();
		param.setCombinationCode(searchParam.getCombinationCode());
		param.setInputParams(searchParam.getInputParams());
		param.setSelectParams(searchParam.getSelectParams());
		List<List<Object>> results = new ArrayList<>();// 返回给前端的一个产品集合
		List<Object> title = new ArrayList<>();
		title.add("产品编码");
		title.add("单价");
		title.add("交期");
		// 查询方案下所有产品的公共参数
		List<Params> allProductParams = selectionMapper.getParamsNamesByCaseId(detail.getCaseId());
		for (Params eachProductParams : allProductParams) {
			title.add(eachProductParams.getParamsName());
		}
		results.add(title);// 此时第一条数据格式类似如：  产品编码、单价、交期、颜色、尺寸...
		// 产品列表
		List<ProductInfo> productInfos = selectionMapper.getProductInfos(param);
		//　处理每一条数据
		for (ProductInfo productInfo : productInfos) {
			List<Object> eachResult = new ArrayList<>();
			eachResult.add(productInfo.getProductCode());// 产品编码
			eachResult.add(productInfo.getPrice().setScale(2).toString());//单价
			eachResult.add(productInfo.getDeliveryDate());//交期
			for (int i = 0; i < allProductParams.size(); i++) {
				Long paramsTermId = allProductParams.get(i).getParamsTermId();
				boolean hasParamsTermId = false;
				Object value = null;
				for (ProductParams productParams : productInfo.getProductParams()) {
					if(productParams.getParamsTermId().equals(paramsTermId)) {
						value = productParams.getParamsValue();
						hasParamsTermId = true;
						break;
					}
				}
				if(hasParamsTermId) {
					eachResult.add(value);
				}else {
					eachResult.add("-");
				}
			}
			results.add(eachResult);
		}
		detail.setProductInfos(results);
		// 查询初始化参数
		List<InitParams> params = selectionMapper.getCombinationDetailInitParams(param);
		detail.setParams(params);
		return JsonResult.successJsonResult(detail);
	}
	
	/**
	 * 
	 * <p>根据所选参数查询产品列表  6</p>
	 * @param searchParam
	 * @return
	 * @author 黄智聪  2018年11月22日 下午9:15:42
	 */
	public JsonResult<List<List<Object>>> getProductInfos(@RequestBody SearchCombinationInitParamsParam searchParam){
		JSR303ValidateUtils.validate(searchParam);
		// 推荐组合的信息
		CombinationDetail detail = selectionMapper.getCombinationDetail(searchParam.getCombinationCode());
		if(detail == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐组合不存在");
		}
		List<List<Object>> results = new ArrayList<>();// 返回给前端的一个产品集合
		List<Object> title = new ArrayList<>();
		title.add("产品编码");
		title.add("单价");
		title.add("交期");
		// 查询方案下所有产品的公共参数
		List<Params> allProductParams = selectionMapper.getParamsNamesByCaseId(detail.getCaseId());
		for (Params eachProductParams : allProductParams) {
			title.add(eachProductParams.getParamsName());
		}
		results.add(title);// 此时第一条数据格式类似如：  产品编码、单价、交期、颜色、尺寸...
		// 查询选中参数后的产品列表
		SearchCombinationInitParamsParam param = new SearchCombinationInitParamsParam();
		param.setCombinationCode(searchParam.getCombinationCode());
		param.setSelectParams(searchParam.getSelectParams());
		List<ProductInfo> productInfos = selectionMapper.getProductInfos(param);
		//　处理每一条数据
		for (ProductInfo productInfo : productInfos) {
			List<Object> eachResult = new ArrayList<>();
			eachResult.add(productInfo.getProductCode());// 产品编码
			eachResult.add(productInfo.getPrice());//单价
			eachResult.add(productInfo.getDeliveryDate());//交期
			for (int i = 0; i < allProductParams.size(); i++) {
				Long paramsTermId = allProductParams.get(i).getParamsTermId();
				boolean hasParamsTermId = false;
				Object value = null;
				for (ProductParams productParams : productInfo.getProductParams()) {
					if(productParams.getParamsTermId().equals(paramsTermId)) {
						value = productParams.getParamsValue();
						hasParamsTermId = true;
						break;
					}
				}
				if(hasParamsTermId) {
					eachResult.add(value);
				}else {
					eachResult.add("-");
				}
			}
			results.add(eachResult);
		}
		return JsonResult.successJsonResult(results);
	}
	
	/**
	 * 
	 * <p>根据产品编码查询该产品的价格信息 7</p>
	 * @param productCode
	 * @return
	 * @author 黄智聪  2018年11月23日 上午11:11:52
	 */
	public JsonResult<ProductPrice> getProductPrice(String productCode){
		return JsonResult.successJsonResult(selectionMapper.getProductPrice(productCode));
	}
	
	/**
	 * 
	 * <p>查询选型记录 8</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月23日 上午11:11:52
	 */
	public JsonResult<Pagination<SelectionRecord>> getSelectionRecord(@RequestBody SearchSelectionRecordParam param){
		JSR303ValidateUtils.validate(param);
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<SelectionRecord> records = clientSelectionRecordMapper.getSelectionRecordByClientUserCode(param);
		return JsonResult.successJsonResult(new Pagination<>(records));
	}
	
	/**
	 * 
	 * <p>保存选型记录 9</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月23日 下午3:48:46
	 */
	public JsonResult<String> addSelectionRecord(@RequestBody AddSelectionRecordParam param){
		JSR303ValidateUtils.validate(param);
		String productCode = param.getProductCode();
		Long productPriceId = param.getProductPriceId();
		ProductInfomation productInfo = selectionMapper.getProductInfoByProductCode(productCode, productPriceId);
		if(productInfo == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "产品信息不存在");
		}
		// 根据产品编码、价格id查询选型记录的个数
		int count = clientSelectionRecordMapper.countSelectionRecordByProductCodeAndProductPriceId(productCode, productPriceId);
		if(count > 0) {// 若已存在相同的选型记录，则啥也不做
			return JsonResult.successJsonResult();
		}else {// 否则才添加至选型记录
			ClientUser user = clientUserMapper.getClientUserByClientUserCode(param.getClientUserCode());
			ClientSelectionRecord clientSelectionRecord = ClientSelectionRecord.builder()
					.clientUserId(user.getId())
					.createTime(new Date())
					.creator(param.getClientUserCode())
					.deliveryDate(productInfo.getDeliveryDate())
					.moduleName(productInfo.getModuleName())
					.paramsValue(productInfo.getParamValues())
					.price(productInfo.getPrice())
					.productPriceId(productPriceId)
					.productCode(productInfo.getProductCode())
					.build();
			clientSelectionRecordMapper.insertSelective(clientSelectionRecord);
			return JsonResult.successJsonResult();
		}
	}
	
	/**
	 * 
	 * <p>删除选型记录 10</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月23日 下午6:32:17
	 */
	public JsonResult<String> delSelectionRecord(@RequestBody DelSelectionRecordParam param){
		JSR303ValidateUtils.validate(param);
		List<Long> selectionRecordIds = param.getSelectionRecordIds();
		Date nowDate = new Date();
		for (Long selectionRecordId : selectionRecordIds) {
			ClientSelectionRecord clientSelectionRecord = ClientSelectionRecord.builder()
					.id(selectionRecordId)
					.status(SelectionRecordStatus.INVALID.getValue())
					.modifier(param.getClientUserCode())
					.lastModifyTime(nowDate)
					.build();
			clientSelectionRecordMapper.updateByPrimaryKeySelective(clientSelectionRecord);
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>添加产品到购物车 11</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月23日 下午3:48:46
	 */
	public JsonResult<String> addProductsToShoppingCart(@RequestBody AddToShoppingCartParam param){
		JSR303ValidateUtils.validate(param);
		String clientUserCode = param.getClientUserCode();
		ClientUser user = clientUserMapper.getClientUserByClientUserCode(clientUserCode);
		List<Long> selectionRecordIds = param.getSelectionRecordIds();
		Date nowDate = new Date();
		for (Long selectionRecordId : selectionRecordIds) {
			// 查询客户选型记录所在的购物车
			ClientShoppingCart record = clientShoppingCartMapper.selectBySelectionRecordIdAndClientUserId(selectionRecordId, user.getId());
			if(record == null) {// 若选型记录未添加至购物车，才将选型记录添加至购物车，否则啥也不干
				ClientShoppingCart shoppingCartRecord = ClientShoppingCart.builder()
						.clientUserId(user.getId())
						.createTime(nowDate)
						.creator(param.getClientUserCode())
						.selectionRecordId(selectionRecordId)
						.build();
				clientShoppingCartMapper.insertSelective(shoppingCartRecord);
			}
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>查询购物车产品信息 12</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月23日 下午6:23:58
	 */
	public JsonResult<List<ShoppingCartProductInfo>> getShoppingCartProductInfos(String clientUserCode){
		return JsonResult.successJsonResult(clientShoppingCartMapper.getShoppingCartProductInfos(clientUserCode));
	}
	
	/**
	 * 
	 * <p>移除购物车中的产品 13</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月23日 下午6:49:03
	 */
	public JsonResult<String> removeShoppingCartProduct(Long shoppingCartId){
		if(shoppingCartId == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "请选择要移除的记录");
		}
		clientShoppingCartMapper.deleteByPrimaryKey(shoppingCartId);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>查询确认订单页面的商品信息 14</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月24日 下午3:03:52
	 */
	public JsonResult<List<ProductInfomation>> getConfirmOrderProductInfos(String clientUserCode){
		return JsonResult.successJsonResult(selectionMapper.getConfirmOrderProductInfos(clientUserCode));
	}
	
	/**
	 * 
	 * <p>查询用户是否能对购物车的产品进行下单操作 15</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年11月23日 下午6:57:21
	 */
	public JsonResult<String> checkOrderOpt(String clientUserCode){
		// 查询客户购物车中的产品存在的下架商品数量
		int count = clientShoppingCartMapper.countPutOffProducts(clientUserCode);
		if(count > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "购物车中存在已下架产品，请移除");
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>下单 16</p>
	 * @return
	 * @author 黄智聪  2018年11月24日 上午10:58:45
	 */
	public JsonResult<String> addOrder(@RequestBody OrderParam param){
		JSR303ValidateUtils.validate(param);
		Long shippingId = param.getShippingId();
		ClientOrderShippingAddress shippingAddress = clientOrderShippingAddressMapper.selectByPrimaryKey(shippingId);
		if(shippingAddress == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "收货地址不存在");
		}
		Long clientUserId = shippingAddress.getClientUserId();
		ClientUser user = clientUserMapper.getClientUserByClientUserCode(param.getClientUserCode());
		if(!clientUserId.equals(user.getId())) {// 非当前登录用户的收货地址
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "收货地址无效");
		}
		// 前端传入的商品信息
		List<OrderItem> orderItems = param.getOrderItems();
		// key(productCode):value(productCode对应的数量)
		Map<String,Integer> itemMap = new HashMap<>();
		for (int i = 0; i < orderItems.size(); i++) {
			OrderItem item = orderItems.get(i);
			String productCode = item.getProductCode();
			Integer quantity = item.getQuantity();
			if(StringUtils.isBlank(productCode)) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "第"+ (i + 1) +"个产品编码为空");
			}
			if(quantity == null) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "请输入产品[" + productCode + "]的下单数量");
			}
			itemMap.put(productCode, quantity);
		}
		// 查询客户购物车的商品信息
		List<ShoppingCartItemInfo> items = clientShoppingCartMapper.getShoppingCartOrderItems(param.getClientUserCode(), orderItems);
		// 比较前端传入的商品信息与购物车的商品信息是否匹配
		if(items.size() != orderItems.size()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "商品信息与购物车商品信息不匹配");
		}
		// 总金额
		BigDecimal grandTotal = BigDecimal.ZERO; 
		for (ShoppingCartItemInfo item : items) {
			// 每个产品的小计 = 产品单价 * 数量（数量通过itemMap的key：productCode来获取）
			grandTotal = grandTotal.add(item.getProductPrice().multiply(new BigDecimal(itemMap.get(item.getProductCode()))));
		}
		Date nowDate = new Date();
		// 新增未支付的订单记录
		String clientOrderCode = dbSequenceService.getPersonlOrderNumber();
		// 若姓名为空，取手机号
		String orderCreator = StringUtils.isBlank(user.getClientUserName()) ? user.getPhoneNumber() : user.getClientUserName();
		ClientOrderPersonal  clientOrderRecord = ClientOrderPersonal.builder()
				.clientOrderCode(SystemSeqUtils.getSeq(clientOrderCode))
				.clientUserId(user.getId())
				.createTime(nowDate)
				.creator(param.getClientUserCode())
				.orderCreator(orderCreator)
				.grandTotal(grandTotal)
				.orderShippingId(param.getShippingId())
				.orderStatusId(ClientOrderStatus.NOT_PAID.getValue())
				.remark(param.getRemark())
				.build();
		clientOrderPersonalMapper.insertSelective(clientOrderRecord);
		
		// 新增客户订单状态变更记录
		ClientOrderStatusPersonal clientOrderStatusRecord = ClientOrderStatusPersonal.builder()
				.createTime(nowDate)
				.creator(param.getClientUserCode())
				.orderId(clientOrderRecord.getId())
				.orderStatusId(ClientOrderStatus.NOT_PAID.getValue())
				.remark("客户下单，生成未支付订单")
				.build();
		clientOrderStatusPersonalMapper.insertSelective(clientOrderStatusRecord);
		
		for (ShoppingCartItemInfo shoppingCartItemInfo : items) {
			// 交期
			Integer deliveryDate = shoppingCartItemInfo.getDeliveryDate();
			// 交货日期为下单日期 + 交期天数
			Date deliveryTime = DateUtils.addDay(nowDate, deliveryDate);
			ClientOrderItemPersonal clientOrderItemRecord = ClientOrderItemPersonal.builder()
					.assortmentName(shoppingCartItemInfo.getAssortmentName())
					.brandName(shoppingCartItemInfo.getBrandName())
					.clientOrderId(clientOrderRecord.getId())
					.createTime(nowDate)
					.creator(param.getClientUserCode())
					.deliveryDate(deliveryDate)
					.deliveryTime(deliveryTime)
					.moduleName(shoppingCartItemInfo.getModuleName())
					.modulePicUrl(shoppingCartItemInfo.getModulePicUrl())
					.productCode(shoppingCartItemInfo.getProductCode())
					.productParamsName(shoppingCartItemInfo.getParamsValue())
					.productPrice(shoppingCartItemInfo.getProductPrice())
					.quantity(itemMap.get(shoppingCartItemInfo.getProductCode()))
					.build();
			clientOrderItemPersonalMapper.insertSelective(clientOrderItemRecord);
		}
		
		// 清空客户的购物车信息
		clientShoppingCartMapper.deleteShoppingCartByClientUserId(user.getId());
		return JsonResult.successJsonResult(SystemSeqUtils.getSeq(clientOrderCode));
	}
	
	/**
	 * 
	 * <p>客户订单支付成功后的操作 17</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月26日 下午3:41:55
	 */
	public JsonResult<String> clientOrderPaySuccessOpt(@RequestBody CallBackParam param){
		JSR303ValidateUtils.validate(param);
		String clientOrderCode = param.getClientOrderCode();
		ClientOrderPersonal order = clientOrderPersonalMapper.getClientOrderPersonalByClientOrderCode(clientOrderCode);
		if(order == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户订单不存在");
		}
		if(ClientOrderStatus.NOT_PAID.getValue() != order.getOrderStatusId()) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "客户订单状态异常");
		}
		Date nowDate = new Date();
		// 修改订单
		ClientOrderPersonal clientOrderRecord = ClientOrderPersonal.builder()
				.id(order.getId())
				.orderStatusId(ClientOrderStatus.NOT_CONFIRMED.getValue())
				.paymentMethod(param.getPaymentMethod())
				.paymentType(param.getPaymentType())
				.paymentStatus(PayStatus.PAY_SUCCESS.getValue())
				.modifyTime(nowDate)
				.build();
		clientOrderPersonalMapper.updateByPrimaryKeySelective(clientOrderRecord);
		
		// 新增客户订单状态变更记录
		ClientOrderStatusPersonal clientOrderStatusRecord = ClientOrderStatusPersonal.builder()
				.createTime(nowDate)
				.orderId(clientOrderRecord.getId())
				.orderStatusId(ClientOrderStatus.NOT_CONFIRMED.getValue())
				.remark("订单支付成功，生成待确认订单")
				.build();
		clientOrderStatusPersonalMapper.insertSelective(clientOrderStatusRecord);
		
		return JsonResult.successJsonResult();
	}
	
	/********************************************* 选型二期 **********************************************/
	
	/**
	 * 
	 * <p>查询个人中心首页选型记录数据   1</p>
	 * @param clientUserCode
	 * @return
	 * @author 黄智聪  2018年12月18日 下午3:38:50
	 */
	public JsonResult<SelectionIndexData> getSelectionIndexData(String clientUserCode){
		ClientUser user = clientUserMapper.getClientUserByClientUserCode(clientUserCode);
		Long clientUserId = user.getId();
		// 查询个人选型记录的数量
		int selectionRecordCount = clientSelectionRecordMapper.countSelectionRecordByClientUserId(clientUserId);
		SearchClientOrderParam param = new SearchClientOrderParam();
		param.setClientUserCode(clientUserCode);
		param.setOrderStatus(ClientOrderStatus.NOT_PAID.getValue());
		// 待支付订单数量
		int notPaidOrderCount = clientOrderPersonalMapper.getClientOrderInfoList(param).size();
		param.setOrderStatus(ClientOrderStatus.NOT_SIGNED.getValue());
		// 待签收订单数量
		int notSignedOrderCount = clientOrderPersonalMapper.getClientOrderInfoList(param).size();
		
		PageHelper.startPage(1, 5);
		List<SelectionCaseInfo> cases = selectionMapper.getSelectionCaseInfos();
		Pagination<SelectionCaseInfo> casesPages = new Pagination<>(cases);
		// 分页后的前5条方案
		List<SelectionCaseInfo> caseInfos = casesPages.getRows();
		
		// 分页后的前5条一级分类
		PageHelper.startPage(1, 5);
		List<ClassificationInfo> classifications = selectionMapper.getClassificationInfos();
		Pagination<ClassificationInfo> classificationsPages = new Pagination<>(classifications);
		List<ClassificationInfo> classificationInfos = classificationsPages.getRows();
		
		return JsonResult.successJsonResult(new SelectionIndexData(selectionRecordCount, notPaidOrderCount, notSignedOrderCount, caseInfos, classificationInfos));
	}
	
	/**
	 * 
	 * <p>根据分类编码查询模组列表  2</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月18日 下午6:13:27
	 */
	public JsonResult<Pagination<SelectionModuleInfo>> getSelectionModuleInfos(@RequestBody SearchSelectionModuleParam param){
		JSR303ValidateUtils.validate(param);
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<SelectionModuleInfo> infos = selectionMapper.getSelectionModuleInfos(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询选择三级分类后的初始化参数 3</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年12月19日 上午10:50:02
	 */
	public JsonResult<List<InitParams>> getSelectionModuleParams(@RequestBody SearchSelectionModuleParam param) {
		JSR303ValidateUtils.validate(param);
		return JsonResult.successJsonResult(selectionMapper.getSelectionModuleParams(param));
	}
	
	/**
	 * 
	 * <p>查询模组详情 4</p>
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年12月19日 上午11:40:58
	 */
	public JsonResult<ModuleDetail> getModuleDetail(String moduleCode){
		if(StringUtils.isBlank(moduleCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "模组编码不允许为空");
		}
		// 模组详情
		ModuleInfo moduleInfo = selectionMapper.getGoodsModuleInfo(moduleCode);
		if(moduleInfo == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "模组不存在");
		}
		
		List<InitParams> params = null;
		
		List<List<Object>> productInfos = null;
		
		
		return JsonResult.successJsonResult(new ModuleDetail(moduleInfo, params, productInfos));
	}
	
 	
	/********************************************* 选型二期 **********************************************/
	
	
	
}

