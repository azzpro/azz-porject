/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 上午11:26:14
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.PlatformGoodsErrorCode;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.exception.BaseException;
import com.azz.platform.merchant.mapper.PlatformGoodsParamsMapper;
import com.azz.platform.merchant.mapper.PlatformGoodsParamsTermMapper;
import com.azz.platform.merchant.mapper.PlatformGoodsParamsValueMapper;
import com.azz.platform.merchant.pojo.PlatformGoodsParams;
import com.azz.platform.merchant.pojo.PlatformGoodsParamsTerm;
import com.azz.platform.merchant.pojo.PlatformGoodsParamsValue;
import com.azz.platform.merchant.pojo.bo.ParamsData;
import com.azz.platform.merchant.pojo.bo.SearchMerchantParam;
import com.azz.platform.merchant.pojo.bo.SearchParams;
import com.azz.platform.merchant.pojo.vo.MerchantApproval;
import com.azz.platform.merchant.pojo.vo.Params;
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
	
	@Transactional(rollbackFor=Exception.class)
	public JsonResult<String> insertParams(@RequestBody List<ParamsData> ppt,String creator){
		if(null != ppt && ppt.size() > 0 && StringUtils.isNotBlank(creator)) {
			for (ParamsData paramsData : ppt) {
				JSR303ValidateUtils.validate(paramsData);
				if(null == paramsData) {
					throw new BaseException(PlatformGoodsErrorCode.PLATFORM_GOODS_ERROR_INVALID_USER);
				}else {
					PlatformGoodsParams goodsParams = new PlatformGoodsParams();
					goodsParams.setParamsCode(randomSequenceService.getProductParameterCodeNumber());
					goodsParams.setCreator(creator);
					goodsParams.setCreateTime(new Date());
					
					goodsParamsMapper.insertSelective(goodsParams);
					Long id2 = goodsParams.getId();
					
					PlatformGoodsParamsTerm goodsParamsTerm = new PlatformGoodsParamsTerm();
					goodsParamsTerm.setParamsName(paramsData.getParamCode());
					goodsParamsTerm.setParamsChoice(paramsData.getParamsChoice());
					goodsParamsTerm.setParamsType(paramsData.getParamsType());
					goodsParamsTerm.setCreateTime(new Date());
					goodsParamsTerm.setCreator(creator);
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
		}
		return JsonResult.successJsonResult();
	}
}

