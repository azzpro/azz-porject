/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:04:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import com.azz.merchant.mapper.PlatformGoodsBrandMapper;
import com.azz.merchant.mapper.PlatformGoodsClassificationMapper;
import com.azz.merchant.mapper.PlatformGoodsParamsMapper;
import com.azz.merchant.mapper.PlatformGoodsParamsTermMapper;
import com.azz.merchant.mapper.PlatformGoodsParamsValueMapper;
import com.azz.merchant.pojo.MerchantGoodsModule;
import com.azz.merchant.pojo.MerchantGoodsProduct;
import com.azz.merchant.pojo.MerchantGoodsProductParams;
import com.azz.merchant.pojo.MerchantGoodsProductPrice;
import com.azz.merchant.pojo.PlatformGoodsBrand;
import com.azz.merchant.pojo.PlatformGoodsParamsTerm;
import com.azz.merchant.pojo.PlatformGoodsParamsValue;
import com.azz.merchant.pojo.bo.BatchAddProduct;
import com.azz.merchant.pojo.bo.BatchAddProductItem;
import com.azz.merchant.pojo.bo.MerchantProductParam;
import com.azz.merchant.pojo.bo.ModulePrams;
import com.azz.merchant.pojo.bo.ProductParam;
import com.azz.merchant.pojo.bo.ProductParams;
import com.azz.merchant.pojo.bo.ProductPrice;
import com.azz.merchant.pojo.bo.Products;
import com.azz.merchant.pojo.vo.Brand;
import com.azz.merchant.pojo.vo.GoodsBrandInfo;
import com.azz.merchant.pojo.vo.MerchantProductList;
import com.azz.merchant.pojo.vo.Module;
import com.azz.merchant.pojo.vo.ParamsValue;
import com.azz.merchant.pojo.vo.ProductParamsBrands;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.SystemSeqUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月1日 下午2:04:46
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ProductService {
	
	@Autowired
	private MerchantGoodsProductMapper goodsProductMapper;
	
	@Autowired
	private PlatformGoodsClassificationMapper platformGoodsClassificationMapper;
	@Autowired
	private PlatformGoodsBrandMapper brandMapper;
	
	@Autowired
	private MerchantGoodsModuleMapper goodsModuleMapper;
	
	@Autowired
	private DbSequenceService dbSequenceService;
	
	@Autowired
	private MerchantGoodsProductPriceMapper goodsProductPriceMapper;
	
	@Autowired
	private MerchantGoodsProductParamsMapper goodsProductParamsMapper;
	
	@Autowired
	private PlatformGoodsParamsMapper platformGoodsParamsMapper;
	
	@Autowired
	private PlatformGoodsParamsTermMapper platformGoodsParamsTermMapper;
	
	@Autowired
	private PlatformGoodsParamsValueMapper platformGoodsParamsValueMapper;
	
	@Autowired
	private PlatformGoodsBrandMapper platformGoodsBrandMapper;
	
	/**
	 * <p>去新增产品页面  加载模组列表</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年11月2日 下午3:01:02
	 */
	@RequestMapping(value="getModule",method=RequestMethod.POST)
	public JsonResult<Pagination<Module>> getModule(@RequestBody ModulePrams mp){
		PageHelper.startPage(mp.getPageNum(), mp.getPageSize());
		List<Module> assormentId = goodsModuleMapper.selectModuleByAssorId(mp);
		return JsonResult.successJsonResult(new Pagination<>(assormentId));
	}
	
	/**
	 * <p>新增产品页面获取参数</p>
	 * @param params
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:47:30
	 */
	@RequestMapping(value="getPrams",method=RequestMethod.POST)
	public JsonResult<com.azz.merchant.pojo.vo.ProductParams> getPrams(Long assortmentId){
		if(null == assortmentId)
			throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_ASSORTMENT_IS_NULL);
		com.azz.merchant.pojo.PlatformGoodsParams goodsParams = platformGoodsParamsMapper.selectParamsByAssortmentId(assortmentId);
		/*if(null == goodsParams)
			throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_VALUES_IS_NULL);*/
		//根据参数ID 查询参数项类型
		List<PlatformGoodsParamsTerm> paramsId = null;
		if(null != goodsParams) {
			paramsId = platformGoodsParamsTermMapper.selectParamsByParamsId(goodsParams.getId());
		}
		com.azz.merchant.pojo.vo.ProductParams pp = new com.azz.merchant.pojo.vo.ProductParams();
		if(null !=  paramsId && paramsId.size() > 0) {
			List<ParamsValue> pvs = new ArrayList<>();
			List<String> values = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			//根据参数项ID 查询值
			for (PlatformGoodsParamsTerm platformGoodsParamsTerm : paramsId) {
				ParamsValue pv = new ParamsValue();
				pv.setChoice(platformGoodsParamsTerm.getParamsChoice());
				pv.setType(platformGoodsParamsTerm.getParamsType());
				pv.setParamName(platformGoodsParamsTerm.getParamsName());
				pv.setTermId(platformGoodsParamsTerm.getId());
				if(platformGoodsParamsTerm.getParamsType() == 1) {
					List<PlatformGoodsParamsValue> termId = platformGoodsParamsValueMapper.selectValueByTermId(platformGoodsParamsTerm.getId());
					for (PlatformGoodsParamsValue ppv : termId) {
						sb.append(ppv.getParamsValue());
						if(ppv != termId.get(termId.size()-1)) {
							sb.append(",");
						}
						
					}
					values = Arrays.asList(sb.toString().split(",")).stream().map(s -> s.trim()).collect(Collectors.toList());
					pv.setValues(values);
					sb = new StringBuilder();
				}
				pvs.add(pv);
				pv = null;
			}
			pp.setPvs(pvs);
		}
		
		

		return new JsonResult<>(pp);
	}
	
	/**
	 * <p>去新增产品页面</p>
	 * @return
	 * @author 刘建麟  2018年11月3日 上午11:56:24
	 */
	@RequestMapping(value="toAddProduct",method=RequestMethod.POST)
	public JsonResult<ProductParamsBrands> toAddProduct(){
		ProductParamsBrands pps = new ProductParamsBrands();
		List<com.azz.merchant.pojo.PlatformGoodsBrand> brands = brandMapper.selectBrand();
		if(null == brands)
			throw new BaseException(MerchantProductErrorCode.MERCHANT_BRAND_ERROR_NOT_EXIST);
		List<Brand> list = new ArrayList<>();
		for (com.azz.merchant.pojo.PlatformGoodsBrand platformGoodsBrand : brands) {
			Brand bd = new Brand();
			bd.setBrandCode(platformGoodsBrand.getBrandCode());
			bd.setBrandName(platformGoodsBrand.getBrandName());
			bd.setBrandId(platformGoodsBrand.getId());
			list.add(bd);
			bd = null;
		}
		pps.setBrands(list);
		
		return new JsonResult<>(pps);
	}
	/**
	 * <p>去到编辑页面</p>
	 * @param code
	 * @return
	 * @author 刘建麟  2018年11月2日 下午7:45:51
	 */
	public JsonResult<Products> toUpdateProduct(Long id){
		if(null == id)
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"产品ID不能为空");
		//查分类
		MerchantGoodsProduct mp = goodsProductMapper.selectByPrimaryKey(id);
		if(null == mp)
			throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_NOT_EXIST);
		
		//根据产品分类ID 查询
		if(null == mp.getAssortmentId())
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"分类ID不能为空");
		com.azz.merchant.pojo.PlatformGoodsClassification assortmentById = platformGoodsClassificationMapper.selectAssortmentById(mp.getAssortmentId());
		
		if(null ==assortmentById)
			throw new BaseException(MerchantProductErrorCode.MERCHANT_ASSORTMENT_IS_NULL);
		//根据产品品牌ID 查询
		if(null == mp.getBrandId())
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"品牌ID不能为空");
		com.azz.merchant.pojo.PlatformGoodsBrand selectBrandById = platformGoodsBrandMapper.selectBrandById(mp.getBrandId());
		if(null == selectBrandById)
			throw new BaseException(MerchantProductErrorCode.MERCHANT_BRAND_ERROR_NOT_EXIST);
		List<com.azz.merchant.pojo.PlatformGoodsBrand> selectBrand = platformGoodsBrandMapper.selectBrand();
		List<GoodsBrandInfo> gis = new ArrayList<>();
		if(null == selectBrand || selectBrand.size() == 0)
			throw new BaseException(MerchantProductErrorCode.MERCHANT_BRAND_ERROR_NOT_EXIST);
		for (PlatformGoodsBrand platformGoodsBrand : selectBrand) {
			GoodsBrandInfo gbi = new GoodsBrandInfo();
			gbi.setBrandCode(platformGoodsBrand.getBrandCode());
			gbi.setBrandName(platformGoodsBrand.getBrandName());
			gbi.setBrandId(platformGoodsBrand.getId());
			gis.add(gbi);
			gbi = null;
		}
		//获取模组
		MerchantGoodsModule module = goodsModuleMapper.selectModuleById(mp.getModuleId());
		//查询产品价格
		List<MerchantGoodsProductPrice> list = goodsProductPriceMapper.selectPriceByProductId(mp.getId());
		
		//查询产品参数
		List<MerchantGoodsProductParams> list2 = goodsProductParamsMapper.selectParamsByProductId(mp.getId());
		
		if(null == list || list.size() == 0) 
			throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_PRICE_IS_NULL);
		if(null == list2 || list2.size() == 0) 
			throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_VALUES_IS_NULL);
		
		Products pps = new Products();
		pps.setAssormentName(assortmentById.getAssortmentName());
		pps.setProductCode(mp.getProductCode());
		pps.setBrands(gis);
		pps.setProductId(mp.getId());
		pps.setBrandId(selectBrandById.getId());
		pps.setStatus(mp.getProductStatus());
		pps.setPrices(list);
		pps.setParams(list2);
		pps.setAssortmentCode(assortmentById.getAssortmentCode());
		pps.setAssortmentId(assortmentById.getId());
		if(null != module) {
			pps.setModuleId(module.getId());
			pps.setModuleName(module.getModuleName());
		}
		return new JsonResult<>(pps);
	}
	
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
				//获取品牌id
				Long brandId = params.getBrandId();
				if(null == brandId)
					throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"品牌Id不能为空");
				com.azz.merchant.pojo.PlatformGoodsBrand goodsBrand = platformGoodsBrandMapper.selectBrandById(brandId);
				if(null == goodsBrand) 
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_BRAND_NOT_EXIST);
				//品牌end
				
				//获取分类Id
				Long assid = params.getAssortmentId();
				if(null == assid)
					throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"分类ID不能为空");
				com.azz.merchant.pojo.PlatformGoodsClassification assortmentById = platformGoodsClassificationMapper.selectAssortmentById(assid);
				if(null == assortmentById)
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_ASSORTMENT_EXIST);
				
				//获取模组CODE
				Long moduleId = params.getModuleId();
				MerchantGoodsModule goodsModule = null;
				if(null != moduleId) {
					 goodsModule = goodsModuleMapper.selectModuleById(moduleId);
				}
				com.azz.merchant.pojo.PlatformGoodsParams goodsParams = platformGoodsParamsMapper.selectParamsByAssortmentId(assid);
				if(null == goodsParams)
					throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_VALUES_IS_NULL);
				
				// 产品编码唯一校验
				MerchantGoodsProduct countPro = goodsProductMapper.selectProductByProductCode(params.getProductCode());
                if(ObjectUtils.isNotNull(countPro)) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                            "产品编码已存在不许重复");
                }
				
				String code= dbSequenceService.getProductCodeNumber();
				//获取产品
				MerchantGoodsProduct mgp = new MerchantGoodsProduct();
				mgp.setAssortmentId(assid);
				mgp.setBrandId(brandId);
				mgp.setCreateTime(new Date());
				mgp.setCreator(params.getCreator());
				if(goodsModule != null) {
					mgp.setModuleId(goodsModule.getId());
				}
				mgp.setProductCode(params.getProductCode());
				mgp.setProductSystemCode(SystemSeqUtils.getSeq(code));
				mgp.setProductStatus(params.getStatus());
				mgp.setBrandId(brandId);
				mgp.setMerchantId(params.getMerchantId());
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
				if(null != pp && pp.size() > 0) {
					for (ProductParam productParam : pp) {
						if(StringUtils.isBlank(productParam.getValues()) && productParam.getChoice() == 2) {
							break;
						}
						MerchantGoodsProductParams mpp = new MerchantGoodsProductParams();
						mpp.setParamsId(goodsParams.getId());
						mpp.setParamsTermId(productParam.getTermId());
						mpp.setParamsName(productParam.getParamName());
						mpp.setParamsValue(productParam.getValues());
						mpp.setProductId(mgp.getId());
						/**
						 * 参数新增时  type 含义为 1 下拉 2填写  choice 含义为 1必选2非必选
						 * 产品选择参数是，此处含义有颠倒， type 为  1必选2非必选  choice为1 下拉 2填写 
						 */
						mpp.setParamsType(productParam.getType());
						mpp.setParamsChoice(productParam.getChoice());
						goodsProductParamsMapper.insertSelective(mpp);
						mpp = null;
					}
				}
			}
				
		return JsonResult.successJsonResult();
	}
	
	/**
	 * <p>编辑产品</p>
	 * @param params
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:47:30
	 */
	@SuppressWarnings("unused")
	@Transactional(rollbackFor=Exception.class)
	public JsonResult<String> updateProduct(@RequestBody ProductParams params){
			if(null != params) {
				//获取品牌id
				Long brandId = params.getBrandId();
				if(null == brandId)
					throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"品牌Id不能为空");
				com.azz.merchant.pojo.PlatformGoodsBrand goodsBrand = platformGoodsBrandMapper.selectBrandById(brandId);
				if(null == goodsBrand) 
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_BRAND_NOT_EXIST);
				//品牌end
				
				//获取分类Id
				Long assid = params.getAssortmentId();
				if(null == assid)
					throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"分类ID不能为空");
				com.azz.merchant.pojo.PlatformGoodsClassification assortmentById = platformGoodsClassificationMapper.selectAssortmentById(assid);
				if(null == assortmentById)
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_ASSORTMENT_EXIST);
				
				//获取模组CODE
				Long moduleId = params.getModuleId();
				MerchantGoodsModule goodsModule = null;
				if(null != moduleId) {
				 goodsModule = goodsModuleMapper.selectModuleById(moduleId);
				}
				com.azz.merchant.pojo.PlatformGoodsParams goodsParams = platformGoodsParamsMapper.selectParamsByAssortmentId(assid);
				if(null == goodsParams)
					throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_VALUES_IS_NULL);
				
				//获取产品
				MerchantGoodsProduct mgp = new MerchantGoodsProduct();
				mgp.setId(params.getProductId());
				mgp.setAssortmentId(assid);
				mgp.setBrandId(brandId);
				mgp.setModifier(params.getModify());
				mgp.setModifyTime(new Date());
				if(goodsModule != null) {
					mgp.setModuleId(goodsModule.getId());
				}
				mgp.setProductCode(params.getProductCode());
				//编辑不需要修改系统编码
				//mgp.setProductSystemCode(randomSequenceService.getProductCodeNumber());
				mgp.setProductStatus(params.getStatus());
				mgp.setBrandId(brandId);
				mgp.setMerchantId(params.getMerchantId());
				int selective = goodsProductMapper.updateByPrimaryKeySelective(mgp);
				if(selective != 1)
					throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_UPDATE_ERROR);
				//插入价格
				List<ProductPrice> prices = params.getPrices();
				//删除 再插入价格
				goodsProductPriceMapper.deleteByProductId(params.getProductId());
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
				//删除产品参数
				goodsProductParamsMapper.deleteByProductId(params.getProductId());
				//插入产品参数
				List<ProductParam> pp = params.getParams();
				if(null != pp && pp.size() > 0) {
					for (ProductParam productParam : pp) {
						MerchantGoodsProductParams mpp = new MerchantGoodsProductParams();
						mpp.setParamsId(goodsParams.getId());
						mpp.setParamsName(productParam.getParamName());
						mpp.setParamsValue(productParam.getValues());
						mpp.setProductId(mgp.getId());
						mpp.setParamsTermId(productParam.getTermId());
						mpp.setParamsType(productParam.getType());
						mpp.setParamsChoice(productParam.getChoice());
						goodsProductParamsMapper.insertSelective(mpp);
						mpp = null;
					}
				}
			}
				
		return JsonResult.successJsonResult();
	}
	
	/**
	 * <p>编辑下架</p>
	 * @param id
	 * @param type
	 * @return
	 * @author 刘建麟  2018年11月5日 上午11:48:37
	 */
	public JsonResult<String> deleteOrDownProduct(Long id,Byte type){
		if(null == id || null == type)
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"Id或类型不能为空");
		//type 1 删除  2下架
		if(type == 1)
			goodsProductMapper.updateProductById((byte)0, id);
		if(type == 2)
			goodsProductMapper.updateProductById((byte)2, id);
		if(type == 3)
			goodsProductMapper.updateProductById((byte)1, id);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * <p>批量新增产品
	 * 说明：此功能批量录入产品信息是在某个分类下录入统一参数的产品，录入的交期目前只录入一个即可。</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年12月14日 上午11:50:39
	 */
    @Transactional(rollbackFor=Exception.class)
	public JsonResult<String> batchAddProduct(@RequestBody BatchAddProduct param){
	    JSR303ValidateUtils.validate(param);
	    // 所属分类id
	    Long assortmentId = param.getAssortmentId();
	    // 所属商户id
	    Long merchantId = param.getMerchantId();
	    // 创建人
	    String creator = param.getCreator();
	    
	    // 校验所属分类id存在
        com.azz.merchant.pojo.PlatformGoodsClassification assortmentById = platformGoodsClassificationMapper.selectAssortmentById(assortmentId);
        if(null == assortmentById) {
            throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_ASSORTMENT_EXIST);
        }
        
	    List<BatchAddProductItem> items = param.getItems();
	    if(items.size() > 0) {
	        for (int i = 0; i < items.size(); i++) {
	            // 处理录入的产品信息
	            BatchAddProductItem productItem = items.get(i);
	            
	            com.azz.merchant.pojo.PlatformGoodsBrand goodsBrand = platformGoodsBrandMapper.selectBrandById(productItem.getBrandId());
                if(null == goodsBrand) 
                    throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_BRAND_NOT_EXIST);
                com.azz.merchant.pojo.PlatformGoodsParams goodsParams = platformGoodsParamsMapper.selectParamsByAssortmentId(assortmentId);
                if(null == goodsParams)
                    throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_VALUES_IS_NULL);
                
                // 产品编码唯一校验
                MerchantGoodsProduct countPro = goodsProductMapper.selectProductByProductCode(productItem.getProductCode());
                if(ObjectUtils.isNotNull(countPro)) {
                    throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                            "产品编码已存在不许重复");
                }
                
                String code= dbSequenceService.getProductCodeNumber();
                //获取产品
                MerchantGoodsProduct mgp = new MerchantGoodsProduct();
                mgp.setAssortmentId(assortmentId);
                mgp.setBrandId(productItem.getBrandId());
                mgp.setCreateTime(new Date());
                mgp.setCreator(creator);
                mgp.setProductCode(productItem.getProductCode());
                mgp.setProductSystemCode(SystemSeqUtils.getSeq(code));
                mgp.setMerchantId(merchantId);
                goodsProductMapper.insertSelective(mgp);
                
                //插入价格
                MerchantGoodsProductPrice mpp = new MerchantGoodsProductPrice();
                mpp.setDeliveryDate(productItem.getDeliveryDate());
                mpp.setPrice(productItem.getPrice());
                mpp.setProductId(mgp.getId());
                goodsProductPriceMapper.insertSelective(mpp);
                
                //插入产品参数
                List<ProductParam> pp = productItem.getParams();
                if(null != pp && pp.size() > 0) {
                    for (ProductParam productParam : pp) {
                        MerchantGoodsProductParams obj = new MerchantGoodsProductParams();
                        obj.setParamsId(goodsParams.getId());
                        obj.setParamsTermId(productParam.getTermId());
                        obj.setParamsName(productParam.getParamName());
                        obj.setParamsValue(productParam.getValues());
                        obj.setProductId(mgp.getId());
                        obj.setParamsType(productParam.getType());
                        obj.setParamsChoice(productParam.getChoice());
                        goodsProductParamsMapper.insertSelective(obj);
                    }
                }
	        }
	        
	    } else {
	        throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "未录入产品信息");
	    }
	    return JsonResult.successJsonResult();
	}
}

