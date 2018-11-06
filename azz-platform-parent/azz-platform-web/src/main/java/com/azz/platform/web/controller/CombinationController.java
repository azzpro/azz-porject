/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.web.controller;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azz.core.common.JsonResult;
import com.azz.platform.merchant.api.CombinationService;
import com.azz.platform.merchant.pojo.bo.AddCombinationParam;
import com.azz.platform.merchant.pojo.bo.AddCombinationWebParam;
import com.azz.platform.merchant.pojo.bo.CombinationPic;
import com.azz.util.Base64;
import com.azz.util.JSR303ValidateUtils;
import com.azz.utils.WebUtils;

/**
 * 
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月1日 下午9:20:41
 */
@RestController
@RequestMapping("/azz/api/merchant/combination")
public class CombinationController {
	
	@Autowired
	CombinationService combinationService;
	
	/**
	 * 
	 * <p>新增推荐组合</p>
	 * @param webParam
	 * @return
	 * @throws IOException
	 * @author 黄智聪  2018年11月6日 上午10:55:31
	 */
	@RequestMapping("/addCombination")
	public JsonResult<String> addCombination(AddCombinationWebParam webParam) throws IOException {
		JSR303ValidateUtils.validate(webParam);
		AddCombinationParam param = new AddCombinationParam();
		BeanUtils.copyProperties(webParam, param);
		MultipartFile combinationPicFile = webParam.getCombinationPicFile();
		CombinationPic combinationPic = new CombinationPic(combinationPicFile.getOriginalFilename(),
				combinationPicFile.getSize(), Base64.encode(combinationPicFile.getBytes()));
		param.setCombinationPic(combinationPic);
		param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return combinationService.addCombination(param);
	}

	
}
