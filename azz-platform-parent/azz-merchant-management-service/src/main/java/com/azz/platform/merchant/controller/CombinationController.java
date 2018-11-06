/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月5日 下午6:44:48
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.platform.merchant.pojo.bo.AddCombinationParam;
import com.azz.platform.merchant.service.CombinationService;

/**
 * <P>组合推荐相关控制器</P>
 * @version 1.0
 * @author 黄智聪  2018年11月5日 下午6:44:48
 */
@RestController
@RequestMapping("/azz/api/merchant/combination")
public class CombinationController {
	
	@Autowired
	CombinationService combinationService;
	
	/**
	 * 
	 * <p>新增推荐组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 上午10:55:57
	 */
	@RequestMapping("/addCombination")
	public JsonResult<String> addCombination(@RequestBody AddCombinationParam param) {
		return combinationService.addCombination(param);
	}

}

