/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:04:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.MerchantProductErrorCode;
import com.azz.core.common.errorcode.PlatformGoodsErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.merchant.mapper.MerchantGoodsModuleMapper;
import com.azz.merchant.mapper.MerchantGoodsProductMapper;
import com.azz.merchant.mapper.MerchantGoodsProductParamsMapper;
import com.azz.merchant.mapper.MerchantGoodsProductPriceMapper;
import com.azz.merchant.pojo.MerchantGoodsModule;
import com.azz.merchant.pojo.MerchantGoodsProduct;
import com.azz.merchant.pojo.MerchantGoodsProductParams;
import com.azz.merchant.pojo.MerchantGoodsProductPrice;
import com.azz.merchant.pojo.bo.MerchantProductParam;
import com.azz.merchant.pojo.bo.ProductParam;
import com.azz.merchant.pojo.bo.ProductParams;
import com.azz.merchant.pojo.bo.ProductPrice;
import com.azz.merchant.pojo.vo.MerchantProductList;
import com.azz.platform.merchant.api.ClassificationService;
import com.azz.platform.merchant.api.ParamsService;
import com.azz.platform.merchant.pojo.PlatformGoodsClassification;
import com.azz.platform.merchant.pojo.PlatformGoodsParams;
import com.azz.platform.merchant.pojo.PlatformGoodsParamsTerm;
import com.azz.platform.merchant.pojo.PlatformGoodsParamsValue;
import com.azz.platform.merchant.pojo.bo.Param;
import com.azz.platform.merchant.pojo.bo.ParamsData;
import com.azz.platform.merchant.pojo.vo.Classification;
import com.azz.system.sequence.api.RandomSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月1日 下午2:04:46
 */
@Service
public class ProductService {
	
	@Autowired
	private MerchantGoodsProductMapper goodsProductMapper;
	
	@Autowired
	private ClassificationService classificationService;
	
	@Autowired
	private ParamsService paramsService;
	
	@Autowired
	private MerchantGoodsModuleMapper goodsModuleMapper;
	
	@Autowired
	private RandomSequenceService randomSequenceService;
	
	@Autowired
	private MerchantGoodsProductPriceMapper goodsProductPriceMapper;
	
	@Autowired
	private MerchantGoodsProductParamsMapper goodsProductParamsMapper;
	
	
	/**
	 * <p>根据分类ID 查询产品</p>
	 * @param id
	 * @return
	 * @author 刘建麟  2018年11月1日 下午2:05:44
	 */
	public String selectProductByAssortmentId(Long id) {
		JSR303ValidateUtils.validate(id);
		int count = goodsProductMapper.selectProductByAssortmentId(id);
		if(count > 0) {
			return "NO";
		}else {
			return "OK";
		}
	} 
	
	/**
     * <p>产品列表</p>
     * @param param
     * @return
     * @author 刘建麟  2018年11月2日 下午2:31:40
     */
	public JsonResult<Pagination<MerchantProductList>> selectProductList(@RequestBody MerchantProductParam param){
    	JSR303ValidateUtils.validate(param);
    	PageHelper.startPage(param.getPageNum(), param.getPageSize());
    	List<MerchantProductList> productList = goodsProductMapper.selectProductList(param);
    	return JsonResult.successJsonResult(new Pagination<>(productList));
	}
	
	/**
	 * <p>新增产品</p>
	 * @param params
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:47:30
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor=Exception.class)
	public JsonResult<String> addProduct(@RequestBody ProductParams params){
			if(null != params) {
				//获取品牌CODE 然后判断 品牌是否存在，存在获取品牌ID
				String brandCode = params.getBrandCode();
				//获取参数CODE
				String paramsCode = params.getParamsCode();
				
				//获取分类CODE 然后判断分类是否存在，存在获取分类ID
				String code = params.getAssortmentCode();
				if(StringUtils.isBlank(code))
					throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"分类CODE不能为空");
				PlatformGoodsClassification classification = classificationService.getClassification(code);
				if(null == classification)
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_ASSORTMENT_EXIST);
				//判断参数是否存在 并且属于分类
				if(StringUtils.isBlank(paramsCode))
					throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"参数CODE不能为空");
				PlatformGoodsParams byassortment = paramsService.selectParamsByassortment(paramsCode,classification.getId());
				if(null == byassortment)
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
				//获取模组CODE
				String moduleCode = params.getModuleCode();
				if(StringUtils.isBlank(moduleCode))
					throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"模组CODE不能为空");
				MerchantGoodsModule goodsModule = goodsModuleMapper.selectByModuleCode(moduleCode);
				if(null == goodsModule)
					throw new BaseException(MerchantProductErrorCode.MERCHANT_MODULE_ERROR_NOT_EXIST);
				//获取产品
				MerchantGoodsProduct mgp = new MerchantGoodsProduct();
				mgp.setAssortmentId(classification.getId());
				mgp.setBrandId(1L);//todo 需要查询  品牌ID
				mgp.setCreateTime(new Date());
				mgp.setCreator(params.getCreator());
				mgp.setModuleId(goodsModule.getId());
				mgp.setProductCode(params.getProductCode());
				mgp.setProductSystemCode(randomSequenceService.getProductCodeNumber());
				mgp.setProductStatus(params.getStatus());
				int i = goodsProductMapper.insertSelective(mgp);
				if(i != 1)
					throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_SAVE_ERROR);
				//插入价格
				List<ProductPrice> prices = params.getPrices();
				if(null == prices || prices.size() <= 0)
					throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_PRICE_IS_NULL);
				for (ProductPrice productPrice : prices) {
					MerchantGoodsProductPrice mpp = new MerchantGoodsProductPrice();
					mpp.setDeliveryDate(productPrice.getDeliveryDate());
					mpp.setPrice(productPrice.getPrice());
					mpp.setProductId(mgp.getId());
					goodsProductPriceMapper.insertSelective(mpp);
					mpp = null;
				}
				//插入产品参数
				List<ProductParam> pp = params.getParams();
				if(null == pp || pp.size() <= 0)
					throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_ASSORTMENT_IS_NULL);
				for (ProductParam productParam : pp) {
					MerchantGoodsProductParams mpp = new MerchantGoodsProductParams();
					mpp.setParamsId(byassortment.getId());
					mpp.setParamsName(productParam.getParamName());
					mpp.setParamsValue(productParam.getValues());
					mpp.setProductId(mgp.getId());
					mpp.setParamsType(productParam.getType());
					goodsProductParamsMapper.insertSelective(mpp);
					mpp = null;
				}
			}
				
		return JsonResult.successJsonResult();
	}
}

