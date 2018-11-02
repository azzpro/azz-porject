/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:23:23
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.MerchantConstants.GoodsModuleStatus;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.merchant.mapper.MerchantGoodsModuleMapper;
import com.azz.platform.merchant.pojo.MerchantGoodsModule;
import com.azz.platform.merchant.pojo.bo.PutOnOrPutOffGoodsModuleParam;
import com.azz.platform.merchant.pojo.bo.SearchGoodsModuleParam;
import com.azz.platform.merchant.pojo.vo.GoodModuleInfo;
import com.azz.util.JSR303ValidateUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>模组业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月1日 下午2:23:23
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class GoodsModuleService {
	
	@Autowired
	private MerchantGoodsModuleMapper merchantGoodsModuleMapper;

	/**
	 * 
	 * <p>查询模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午3:33:53
	 */
	public JsonResult<Pagination<GoodModuleInfo>> getGoodModuleInfoList(@RequestBody SearchGoodsModuleParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<GoodModuleInfo> infos = merchantGoodsModuleMapper.getGoodsModuleInfoList(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询模组详情</p>
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年11月1日 下午8:45:15
	 */
	public JsonResult<GoodModuleInfo> getGoodModuleInfo(String moduleCode){
		return JsonResult.successJsonResult(merchantGoodsModuleMapper.getGoodsModuleInfo(moduleCode));
	}
	
	
	/**
	 * 
	 * <p>上架、下架或删除模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午8:02:16
	 */
	public JsonResult<String> putOnOrPutOffGoodsModule(@RequestBody PutOnOrPutOffGoodsModuleParam param){
		// 参数校验
		JSR303ValidateUtils.validate(param);
		String moduleCode = param.getModuleCode();
		MerchantGoodsModule module = merchantGoodsModuleMapper.selectByModuleCode(moduleCode);
		if(module == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "模组不存在");
		}
		boolean yes = GoodsModuleStatus.checkStatusExist(param.getModuleStatus());
		if(!yes) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "模组状态不存在");
		}
		MerchantGoodsModule merchantGoodsModuleRecord = MerchantGoodsModule.builder()
				.modifier(param.getModifier())
				.modifyTime(new Date())
				.moduleCode(moduleCode)
				.moduleStatus(param.getModuleStatus())
				.build();
		merchantGoodsModuleMapper.updateByModuleCode(merchantGoodsModuleRecord);
		return JsonResult.successJsonResult();
	}
	
}

