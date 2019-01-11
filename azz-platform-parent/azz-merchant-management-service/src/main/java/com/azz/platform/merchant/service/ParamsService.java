/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 上午11:26:14
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.PlatformGoodsErrorCode;
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
import com.azz.platform.merchant.pojo.bo.SearchParams;
import com.azz.platform.merchant.pojo.vo.Params;
import com.azz.platform.merchant.pojo.vo.ParamsAll;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.SystemSeqUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>参数管理</P>
 * @version 1.0
 * @author 刘建麟  2018年10月31日 上午11:26:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ParamsService {
	
	private final Logger LOG = org.slf4j.LoggerFactory.getLogger(getClass());

	@Autowired
	private PlatformGoodsParamsMapper goodsParamsMapper;
	
	@Autowired
	private PlatformGoodsParamsTermMapper goodsParamsTermMapper;
	
	@Autowired
	private PlatformGoodsParamsValueMapper goodsParamsValueMapper;
	
	@Autowired
	private DbSequenceService dbSequenceService;
	
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
	        for (Params params : paramsList) {
	        	String b = productService.selectProductByAssortmentId(params.getAId());
	        	if(Objects.equals(b, "NO")) {
					params.setFlag((byte)1); //不能修改
				}else {
					params.setFlag((byte)0);
				}
			}
	        
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
					pa.setAssortName(key.getAssortmentName());
					pa.setAssortCode(key.getAssortmentCode());
					pa.setParamsName(platformGoodsParamsTerm.getParamsName());
					pa.setParamsChoice(platformGoodsParamsTerm.getParamsChoice());
					pa.setParamsType(platformGoodsParamsTerm.getParamsType());
					pa.setParamsCode(platformGoodsParamsTerm.getParamsCode());
					pa.setParamParentId(platformGoodsParamsTerm.getId());
					pa.setParamsHidden(platformGoodsParamsTerm.getParamsHidden());
					List<PlatformGoodsParamsValue> valueById = goodsParamsValueMapper.selectValueById(platformGoodsParamsTerm.getId());
					if(null != valueById && valueById.size() > 0 && platformGoodsParamsTerm.getParamsType() == 1) {
						for (PlatformGoodsParamsValue platformGoodsParamsTerm2 : valueById) {
							sb.append(platformGoodsParamsTerm2.getParamsValue());
							if(platformGoodsParamsTerm2 != valueById.get(valueById.size()-1)) {
								sb.append(",");
							}
						}
						pa.setValues(sb.toString());
						sb = new StringBuffer();
					}
					list.add(pa);
					pa = null;
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
	public JsonResult<String> updateParams(ParamsData ppt){
		//校验参数
		JSR303ValidateUtils.validate(ppt);
		if(null != ppt) {
			List<com.azz.platform.merchant.pojo.bo.Param> list = ppt.getParams();
			if(null != list && list.size() > 0) {
				TreeSet<String> set = new TreeSet<>();
				TreeSet<String> set2 = new TreeSet<>();
				for(Param paramsData1 : list) {
					set.add(paramsData1.getParamName());
				}
				if(set.size() != list.size()) {
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_PARAMS);
				}
				List<Param> params = ppt.getParams();
				for (Param param : params) {
					String[] param2 = param.getParam();
					for (int i = 0; i < param2.length; i++) {
						set2.add(param2[i]);
					}
					if(set2.size() != param2.length) {
						throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_VALUES);
					}
				}
			}
		}
		//根据父参数编码 查询父参数
		PlatformGoodsParams paramsByCode = goodsParamsMapper.selectParamsByCode(ppt.getParentCode());
		if(null == paramsByCode)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
		//根据分类CODE 查询分类
		PlatformGoodsClassification key = goodsClassificationMapper.selectByAssortmentCode(ppt.getAssortmentCode());
		if(null == key)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_ASSORTMENT_EXIST);	
		
		//一个分类只能被选中一次
		/*int count = goodsParamsMapper.selectAssortCountByCode(key.getId());
		LOG.info("新增参数分类存在次数------------>"+count);
		if(count >= 1) {
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_TOOMANY);
		}*/
		List<PlatformGoodsParamsTerm> termByCode = goodsParamsTermMapper.selectParamsTermByCode(paramsByCode.getId());
		StringBuilder sb = new StringBuilder();
		if(null == termByCode || termByCode.size() <=0)
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
		
		for (PlatformGoodsParamsTerm platformGoodsParamsTerm : termByCode) {
			sb.append(platformGoodsParamsTerm.getId());
			if(platformGoodsParamsTerm != termByCode.get(termByCode.size()-1)) {
				sb.append(",");
			}
			// 清空 参数项 
			goodsParamsTermMapper.deleteByPrimaryKey(platformGoodsParamsTerm.getId());
		}
		String[] split = sb.toString().split(",");
		goodsParamsValueMapper.deleteValue(split);//删除参数值
			
		//参数主表更新 分类ID
		int idById = goodsParamsMapper.updateAssormentIdById(key.getId(),paramsByCode.getId());
		
		if(idById != 1) 
			throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_UPDATE_ERROR);
		//获取参数值
		List<com.azz.platform.merchant.pojo.bo.Param> list = ppt.getParams();
			if(null != list && list.size() > 0) {
				//参数名不能重复
				TreeSet<String> set = new TreeSet<>();
				for(Param paramsData1 : list) {
					set.add(paramsData1.getParamName());
				}
				if(set.size() != list.size()) {
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_PARAMS);
				}
				for (Param paramsData : list) {
					JSR303ValidateUtils.validate(paramsData);
					if(null == paramsData) {
						throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_NULL);
					}else {
						String code = dbSequenceService.getParameterItemCodeNumber();
						PlatformGoodsParamsTerm goodsParamsTerm = new PlatformGoodsParamsTerm();
						goodsParamsTerm.setParamsName(paramsData.getParamName());
						goodsParamsTerm.setParamsChoice(paramsData.getParamsChoice());
						goodsParamsTerm.setParamsType(paramsData.getParamsType());
						goodsParamsTerm.setCreateTime(new Date());
						goodsParamsTerm.setCreator(ppt.getCreator());
						goodsParamsTerm.setParamsId(paramsByCode.getId());
						goodsParamsTerm.setParamsCode(SystemSeqUtils.getSeq(code));
						goodsParamsTerm.setParamsHidden(paramsData.getParamsHidden());
						goodsParamsTermMapper.insertSelective(goodsParamsTerm);
						//获取主键
						Long ids = goodsParamsTerm.getId();
						
						String[] param = paramsData.getParam();
						//String[] split = param.split(",");
						for (String string : param) {
							PlatformGoodsParamsValue ppv = new PlatformGoodsParamsValue();
							ppv.setParamsValue(string);
							ppv.setParamsParentId(ids);
							goodsParamsValueMapper.insertSelective(ppv);
							ppv = null;
						}
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
				List<Param> params = ppt.getParams();
				for (Param param : params) {
					TreeSet<String> set2 = new TreeSet<>();
					String[] param2 = param.getParam();
					for (int i = 0; i < param2.length; i++) {
						set2.add(param2[i]);
					}
					if(set2.size() != param2.length) {
						throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_VALUES);
					}
					set2 = null;
				}
				//查询分类
				PlatformGoodsClassification code = goodsClassificationMapper.selectByAssortmentCode(ppt.getAssortmentCode());
				if(null == code)
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_CODE_NOTEXIST);
				//当前分类只能存在一次
				int count = goodsParamsMapper.selectAssortCountByCode(code.getId());
				LOG.info("新增参数分类存在次数------------>"+count);
				if(count >= 1) {
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_TOOMANY);
				}
				String codes = dbSequenceService.getParameterCodeNumber();
				PlatformGoodsParams goodsParams = new PlatformGoodsParams();
				goodsParams.setParamsCode(SystemSeqUtils.getSeq(codes));
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
						String codea = dbSequenceService.getParameterItemCodeNumber();
						PlatformGoodsParamsTerm goodsParamsTerm = new PlatformGoodsParamsTerm();
						goodsParamsTerm.setParamsName(paramsData.getParamName());
						goodsParamsTerm.setParamsChoice(paramsData.getParamsChoice());
						goodsParamsTerm.setParamsType(paramsData.getParamsType());
						goodsParamsTerm.setCreateTime(new Date());
						goodsParamsTerm.setCreator(ppt.getCreator());
						goodsParamsTerm.setParamsId(id2);
						goodsParamsTerm.setParamsCode(SystemSeqUtils.getSeq(codea));
						goodsParamsTerm.setParamsHidden(paramsData.getParamsHidden());
						goodsParamsTermMapper.insertSelective(goodsParamsTerm);
						//获取主键
						Long id = goodsParamsTerm.getId();
						
						String[] param = paramsData.getParam();
						//String[] split = param.split(",");
						for (String string : param) {
							PlatformGoodsParamsValue ppv = new PlatformGoodsParamsValue();
							ppv.setParamsValue(string);
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
		// 判断分类ID下是否有产品  有则不让删除  无可以删除  productService
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
		goodsParamsValueMapper.deleteValue(split);
		
		//删除参数项
		goodsParamsTermMapper.deleteByParamsId(id);
		
		//删除参数
		goodsParamsMapper.deleteByCode(code);
		
		return JsonResult.successJsonResult();
	}
}

