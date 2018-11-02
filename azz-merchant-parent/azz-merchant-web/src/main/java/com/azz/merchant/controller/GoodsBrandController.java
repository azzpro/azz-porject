/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.merchant.api.GoodsBrandService;
import com.azz.merchant.pojo.bo.SearchGoodsBrandParam;
import com.azz.merchant.pojo.vo.GoodsBrandInfo;

/**
 * 
 * <P>
 * 品牌控制器
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月17日 下午1:42:55
 */
@RestController
@RequestMapping("/azz/api/merchant/goodsBrand")
public class GoodsBrandController {
	
	@Autowired
	GoodsBrandService goodsBrandService;
	
	/**
	 * 
	 * <p>查询品牌列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月2日 下午5:10:30
	 */
	@RequestMapping("/getGoodsBrandInfoList")
	public JsonResult<Pagination<GoodsBrandInfo>> getGoodsBrandInfoList(SearchGoodsBrandParam param) {
		return goodsBrandService.getGoodsBrandInfoList(param);
	}

}
