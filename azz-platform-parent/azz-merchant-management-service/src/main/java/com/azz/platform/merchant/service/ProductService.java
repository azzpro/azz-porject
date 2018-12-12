/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月5日 下午2:38:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.MerchantProductErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.merchant.mapper.MerchantGoodsProductMapper;
import com.azz.platform.merchant.mapper.MerchantGoodsProductParamsMapper;
import com.azz.platform.merchant.mapper.MerchantGoodsProductPriceMapper;
import com.azz.platform.merchant.pojo.MerchantGoodsProduct;
import com.azz.platform.merchant.pojo.MerchantGoodsProductParams;
import com.azz.platform.merchant.pojo.bo.PlatformProduct;
import com.azz.platform.merchant.pojo.vo.PlatfomrProductList;
import com.azz.platform.merchant.pojo.vo.ProductInfo;
import com.github.pagehelper.PageHelper;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月5日 下午2:38:43
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {

	
	@Autowired
	private MerchantGoodsProductMapper goodsProductMapper;
	
	@Autowired
	private MerchantGoodsProductParamsMapper goodsProductParamsMapper;
	
	@Autowired
	private MerchantGoodsProductPriceMapper goodsProductPriceMapper;
	
	/**
	 * <p>平台端 产品列表 </p>
	 * @param mp
	 * @return
	 * @author 刘建麟  2018年11月5日 下午2:04:53
	 */
	public JsonResult<Pagination<PlatfomrProductList>> selectPlatformProductList(@RequestBody PlatformProduct mp){
		PageHelper.startPage(mp.getPageNum(), mp.getPageSize());
		List<PlatfomrProductList> productList = goodsProductMapper.selectPlatformProductList(mp);
		return JsonResult.successJsonResult(new Pagination<>(productList));
	}
	
	/**
	 * <p>产品下架</p>
	 * @param id
	 * @param type
	 * @return
	 * @author 刘建麟  2018年11月5日 下午3:19:18
	 */
	public JsonResult<String> downProduct(@RequestParam("id") Long id){
		if(null == id)
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"ID不能为空");
		int i = goodsProductMapper.downProduct(id);
		if(i != 1)
			throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_DOWN_ERROR);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * <p>产品详情</p>
	 * @param id
	 * @return
	 * @author 刘建麟  2018年11月5日 下午3:44:05
	 */
	public JsonResult<ProductInfo> productInfo(@RequestParam("id") Long id){
		if(null == id)
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"ID不能为空");
		Map<String, Object> byId = goodsProductMapper.selectProductById(id);
		if(null == byId)
			throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_NOT_EXIST);
		ProductInfo pi = new ProductInfo();
		pi.setCreateTime((Date)byId.get("createTime"));
		pi.setCurrentStatus((Integer)byId.get("pStatus"));
		pi.setMerchantName((String)byId.get("merchantName"));
		pi.setModuleName((String)byId.get("moduleName"));
		pi.setProductCode((String)byId.get("productCode"));
		pi.setParams(goodsProductParamsMapper.selectParamsByProductId(id));
		pi.setPrices(goodsProductPriceMapper.selectPriceByProductId(id));
		return new JsonResult<>(pi);
	}
}

