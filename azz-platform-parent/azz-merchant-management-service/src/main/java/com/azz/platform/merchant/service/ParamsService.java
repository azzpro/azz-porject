/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 上午11:26:14
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.anakia.Escape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.PlatformGoodsErrorCode;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.merchant.mapper.PlatformGoodsParamsMapper;
import com.azz.platform.merchant.mapper.PlatformGoodsParamsTermMapper;
import com.azz.platform.merchant.mapper.PlatformGoodsParamsValueMapper;
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
	public JsonResult<List<ParamsAll>> toUpdateParams(String code){
		if(StringUtils.isBlank(code)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,"参数CODE不能为空");
		}
		List<ParamsAll> list = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		//查询 属于 参数的参数项
		PlatformGoodsParams paramsByCode = goodsParamsMapper.selectParamsByCode(code);
		if(null != paramsByCode) {
			List<PlatformGoodsParamsTerm> byCode = goodsParamsTermMapper.selectParamsTermByCode(paramsByCode.getId());
			if(null != byCode && byCode.size() > 0) {
				for (PlatformGoodsParamsTerm platformGoodsParamsTerm : byCode) {
					ParamsAll pa = new ParamsAll();
					pa.setParamsName(platformGoodsParamsTerm.getParamsName());
					pa.setParamsChoice(platformGoodsParamsTerm.getParamsChoice());
					pa.setParamsType(platformGoodsParamsTerm.getParamsType());
					pa.setParamsCode(platformGoodsParamsTerm.getParamsCode());
					List<PlatformGoodsParamsValue> valueById = goodsParamsValueMapper.selectValueById(platformGoodsParamsTerm.getId());
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
		if(null != ppt) {
			PlatformGoodsParamsTerm pt = new PlatformGoodsParamsTerm();
			pt.setModifier(ppt.getModifier());
			pt.setModifyTime(new Date());
			pt.setParamsChoice(ppt.getParamsChoice());
			pt.setParamsType(ppt.getParamsType());
			pt.setParamsName(ppt.getParamName());
			pt.setParamsCode(ppt.getParamCode());
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
				PlatformGoodsParams goodsParams = new PlatformGoodsParams();
				goodsParams.setParamsCode(randomSequenceService.getProductParameterCodeNumber());
				goodsParams.setCreator(ppt.getCreator());
				goodsParams.setCreateTime(new Date());
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
}

