/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.api.GoodsModuleService;
import com.azz.platform.merchant.pojo.bo.PutOnOrPutOffGoodsModuleParam;
import com.azz.platform.merchant.pojo.bo.SearchGoodsModuleParam;
import com.azz.platform.merchant.pojo.vo.GoodModuleInfo;
import com.azz.utils.WebUtils;

/**
 * 
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月1日 下午9:20:41
 */
@RestController
@RequestMapping("/azz/api/merchant/goodsModule")
public class GoodsModuleController {
	
	@Autowired
	GoodsModuleService goodsModuleService;
	
	/**
	 * 
	 * <p>查询模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午3:33:53
	 */
	@RequestMapping("/getGoodModuleInfoList")
	JsonResult<Pagination<GoodModuleInfo>> getGoodModuleInfoList(SearchGoodsModuleParam param) {
		return goodsModuleService.getGoodModuleInfoList(param);
	}
	
	/**
	 * 
	 * <p>查询模组详情</p>
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年11月1日 下午8:45:15
	 */
	@RequestMapping("/getGoodModuleInfo")
	JsonResult<GoodModuleInfo> getGoodModuleInfo(String moduleCode) {
		return goodsModuleService.getGoodModuleInfo(moduleCode);
	}
	
	
	/**
	 * 
	 * <p>上架、下架模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月1日 下午8:02:16
	 */
	@RequestMapping("/putOnOrPutOffGoodsModule")
	JsonResult<String> putOnOrPutOffGoodsModule(PutOnOrPutOffGoodsModuleParam param) {
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return goodsModuleService.putOnOrPutOffGoodsModule(param);
	}

	
}
