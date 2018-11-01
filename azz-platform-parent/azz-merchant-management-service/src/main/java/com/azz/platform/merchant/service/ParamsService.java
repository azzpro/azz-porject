/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 上午11:26:14
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.anakia.Escape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.PlatformGoodsErrorCode;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.merchant.api.ProductService;
import com.azz.platform.merchant.mapper.PlatformGoodsClassificationMapper;
import com.azz.platform.merchant.mapper.PlatformGoodsParamsMapper;
import com.azz.platform.merchant.mapper.PlatformGoodsParamsTermMapper;
import com.azz.platform.merchant.mapper.PlatformGoodsParamsValueMapper;
import com.azz.platform.merchant.pojo.PlatformGoodsClassification;
import com.azz.platform.merchant.pojo.PlatformGoodsParams;
import com.azz.platform.merchant.pojo.PlatformGoodsParamsTerm;
import com.azz.platform.merchant.pojo.PlatformGoodsParamsValue;
import com.azz.platform.merchant.pojo.bo.Param;
import com.azz.platform.merchant.pojo.bo.ParamsData;
import com.azz.platform.merchant.pojo.bo.SearchMerchantParam;
import com.azz.platform.merchant.pojo.bo.SearchParams;
import com.azz.platform.merchant.pojo.vo.MerchantApproval;
import com.azz.platform.merchant.pojo.vo.Params;
import com.azz.platform.merchant.pojo.vo.ParamsAll;
import com.azz.system.sequence.api.RandomSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>参数管理</P>
 * @version 1.0
 * @author 刘建麟  2018年10月31日 上午11:26:14
 */
@Service
public class ParamsService {

	@Autowired
	private PlatformGoodsParamsMapper goodsParamsMapper;
	
	@Autowired
	private PlatformGoodsParamsTermMapper goodsParamsTermMapper;
	
	@Autowired
	private PlatformGoodsParamsValueMapper goodsParamsValueMapper;
	
	@Autowired
	private RandomSequenceService randomSequenceService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PlatformGoodsClassificationMapper goodsClassificationMapper;
	 /**
	 * <p>参数列表</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月31日 上午11:29:49
	 */
	public JsonResult<Pagination<Params>> searchParamsList(@RequestBody SearchParams param) {
	        PageHelper.startPage(param.getPageNum(), param.getPageSize());
	        List<Params> paramsList = goodsParamsMapper.searchParamsList(param);
	        return JsonResult.successJsonResult(new Pagination<>(paramsList));
	}
	
	
	/**
	 * <p>去更新参数页面</p>
	 * @param code
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:50:34
	 */
	@SuppressWarnings("unused")
	public JsonResult<List<ParamsAll>> toUpdateParams(String code){
		if(StringUtils.isBlank(code)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"参数CODE不能为空");
		}
		List<ParamsAll> list = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		//查询 属于 参数的参数项
		PlatformGoodsParams paramsByCode = goodsParamsMapper.selectParamsByCode(code);
		if(null == paramsByCode)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_CODE_NOTEXIST);
		//查询分类名称
		PlatformGoodsClassification key = goodsClassificationMapper.selectByPrimaryKey(paramsByCode.getAssortmentId());
		if(null == key)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_ASSORTMENT_EXIST);
		Long id = paramsByCode.getId();
		if(null != paramsByCode) {
			List<PlatformGoodsParamsTerm> byCode = goodsParamsTermMapper.selectParamsTermByCode(paramsByCode.getId());
			if(null != byCode && byCode.size() > 0) {
				for (PlatformGoodsParamsTerm platformGoodsParamsTerm : byCode) {
					ParamsAll pa = new ParamsAll();
					pa.setParentCode(code);
					pa.setParamsName(platformGoodsParamsTerm.getParamsName());
					pa.setParamsChoice(platformGoodsParamsTerm.getParamsChoice());
					pa.setParamsType(platformGoodsParamsTerm.getParamsType());
					pa.setParamsCode(platformGoodsParamsTerm.getParamsCode());
					List<PlatformGoodsParamsValue> valueById = goodsParamsValueMapper.selectValueById(platformGoodsParamsTerm.getId());
					String b = productService.selectProductByAssortmentId(key.getId());
					if(Objects.equals(b, "NO")) {
						pa.setUpdatFlag((byte)1);
					}else {
						pa.setUpdatFlag((byte)0);
					}
					if(null != valueById && valueById.size() > 0) {
						for (PlatformGoodsParamsValue platformGoodsParamsTerm2 : valueById) {
							sb.append(platformGoodsParamsTerm2.getParamsValue());
							if(platformGoodsParamsTerm2 != valueById.get(valueById.size()-1)) {
								sb.append(",");
							}
						}
						pa.setValues(sb.toString());
						list.add(pa);
						sb = new StringBuffer();
						pa = null;
					}else {
						throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
					}
				}
			}else {
				throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
			}
		}else {
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
		}
		
		return new JsonResult<List<ParamsAll>>(list);
	}
	
	/**
	 * <p>更新参数</p>
	 * @param ppt
	 * @return
	 * @author 刘建麟  2018年10月31日 下午5:32:45
	 */
	@Transactional(rollbackFor=Exception.class)
	public JsonResult<String> updateParams(Param ppt){
		//todo 判断分类ID下是否有产品  有则不让更新  无可以更新  productService
		if(null != ppt) {
			StringBuilder sb = new StringBuilder();
			PlatformGoodsParams paramsByCode = goodsParamsMapper.selectParamsByCode(ppt.getParentCode());
			if(null == paramsByCode)
				throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
			Long id = paramsByCode.getId();
			if(StringUtils.isBlank(ppt.getAssortmentCode()))
				throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_CODE_NOTEXIST);
			PlatformGoodsClassification key = goodsClassificationMapper.selectByAssortmentCode(ppt.getAssortmentCode());
			if(null == key)
				throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_ASSORTMENT_EXIST);
			
			String b = productService.selectProductByAssortmentId(key.getId());
			if(Objects.equals(b, "NO"))
				throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_PRODUCT_EXIST);
			
			int idById = goodsParamsMapper.updateAssormentIdById(key.getId(),id);
			if(idById != 1) 
				throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_UPDATE_ERROR);
			//更新参数值
			PlatformGoodsParamsTerm pt = new PlatformGoodsParamsTerm();
			pt.setModifier(ppt.getModifier());
			pt.setModifyTime(new Date());
			pt.setParamsChoice(ppt.getParamsChoice());
			pt.setParamsType(ppt.getParamsType());
			pt.setParamsName(ppt.getParamName());
			pt.setParamsCode(ppt.getParamCode());
			PlatformGoodsParamsTerm paramsTerm = goodsParamsTermMapper.selectIdTermByCode(pt.getParamsCode());
			if(null == paramsTerm)
				throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_CODE_NOTEXIST);
			int i = goodsParamsTermMapper.updateBycode(pt);
			if(i != 1) {
				throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_UPDATE_ERROR);
			}else {
				String values = ppt.getParam();
				String code = ppt.getParamCode();
				PlatformGoodsParamsTerm termByCode = goodsParamsTermMapper.selectIdTermByCode(code);
				if(null != termByCode) {
					goodsParamsValueMapper.deleteByParentId(termByCode.getId());
				}else {
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
				}
				String[] split = values.split(",");
				for (String string : split) {
					PlatformGoodsParamsValue pv = new PlatformGoodsParamsValue();
					pv.setParamsParentId(termByCode.getId());
					pv.setParamsValue(Long.parseLong(string));
					goodsParamsValueMapper.insert(pv);
					pv = null;
				}
			}
		}else {
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
		}
		return JsonResult.successJsonResult();
	}
	
	
	/**
	 * <p>新增参数</p>
	 * @param ppt
	 * @return
	 * @author 刘建麟  2018年10月31日 下午5:32:45
	 */
	@Transactional(rollbackFor=Exception.class)
	public JsonResult<String> insertParams(ParamsData ppt){
		if(null != ppt) {
			List<com.azz.platform.merchant.pojo.bo.Param> list = ppt.getParams();
			if(null != list && list.size() > 0) {
				TreeSet<String> set = new TreeSet<>();
				for(Param paramsData1 : list) {
					set.add(paramsData1.getParamName());
				}
				if(set.size() != list.size()) {
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_PARAMS);
				}
				//查询分类
				PlatformGoodsClassification code = goodsClassificationMapper.selectByAssortmentCode(ppt.getAssortmentCode());
				if(null == code)
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_CODE_NOTEXIST);
					
				PlatformGoodsParams goodsParams = new PlatformGoodsParams();
				goodsParams.setParamsCode(randomSequenceService.getProductParameterCodeNumber());
				goodsParams.setCreator(ppt.getCreator());
				goodsParams.setCreateTime(new Date());
				goodsParams.setAssortmentId(code.getId());
				goodsParamsMapper.insertSelective(goodsParams);
				Long id2 = goodsParams.getId();
				for (Param paramsData : list) {
					JSR303ValidateUtils.validate(paramsData);
					if(null == paramsData) {
						throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
					}else {
						PlatformGoodsParamsTerm goodsParamsTerm = new PlatformGoodsParamsTerm();
						goodsParamsTerm.setParamsName(paramsData.getParamName());
						goodsParamsTerm.setParamsChoice(paramsData.getParamsChoice());
						goodsParamsTerm.setParamsType(paramsData.getParamsType());
						goodsParamsTerm.setCreateTime(new Date());
						goodsParamsTerm.setCreator(ppt.getCreator());
						goodsParamsTerm.setParamsId(id2);
						goodsParamsTerm.setParamsCode(randomSequenceService.getParameterItemCodeNumber());
						goodsParamsTermMapper.insertSelective(goodsParamsTerm);
						//获取主键
						Long id = goodsParamsTerm.getId();
						
						String param = paramsData.getParam();
						String[] split = param.split(",");
						for (String string : split) {
							PlatformGoodsParamsValue ppv = new PlatformGoodsParamsValue();
							ppv.setParamsValue(Long.valueOf(string));
							ppv.setParamsParentId(id);
							goodsParamsValueMapper.insertSelective(ppv);
							ppv = null;
						}
					}
				}
			}else {
				throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
			}
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * <p>删除参数</p>
	 * @param code
	 * @return
	 * @author 刘建麟  2018年11月1日 下午2:18:47
	 */
	@Transactional(rollbackFor=Exception.class)
	public JsonResult<String> deleteParams(String code){
		JSR303ValidateUtils.validate(code);
		////todo 判断分类ID下是否有产品  有则不让删除  无可以删除  productService
		StringBuilder sb1 = new StringBuilder();
		PlatformGoodsParams byCode = goodsParamsMapper.selectParamsByCode(code);
		if(null == byCode)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_CODE_NOTEXIST);
		
		//删除参数值
		Long id = byCode.getId();
		PlatformGoodsClassification key = goodsClassificationMapper.selectByPrimaryKey(byCode.getAssortmentId());
		if(null == key)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_ASSORTMENT_EXIST);
		String b = productService.selectProductByAssortmentId(key.getId());
		if(Objects.equals(b, "NO"))
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_PRODUCT_EXIST);
		
		List<PlatformGoodsParamsTerm> termByCode = goodsParamsTermMapper.selectParamsTermByCode(id);
		
		StringBuilder sb = new StringBuilder();
		if(null == termByCode || termByCode.size() <=0)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
		
		for (PlatformGoodsParamsTerm platformGoodsParamsTerm : termByCode) {
			sb.append(platformGoodsParamsTerm.getId());
			if(platformGoodsParamsTerm != termByCode.get(termByCode.size()-1)) {
				sb.append(",");
			}
		}
		String[] split = sb.toString().split(",");
		goodsParamsValueMapper.deleteValue((long[]) ConvertUtils.convert(split,long.class));
		
		//删除参数项
		goodsParamsTermMapper.deleteByParamsId(id);
		
		//删除参数
		goodsParamsMapper.deleteByCode(code);
		
		return JsonResult.successJsonResult();
	}
}

