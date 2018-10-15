/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月14日 上午9:27:50
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.goods.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.goods.api.DemoService;
import com.azz.platform.goods.mapper.DemoMapper;
import com.azz.platform.goods.pojo.Demo;
import com.azz.platform.goods.pojo.bo.DemoSearchParam;
import com.azz.platform.goods.pojo.vo.DemoInfo;
import com.azz.util.JSR303ValidateUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 刘建麟 2018年10月14日 上午9:27:50
 */
@RestController
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper d;

    @Override
    public List<Demo> getName() {
	return d.getName();
    }

    @Override
    public JsonResult<Pagination<DemoInfo>> getDemoInfosByIds(@RequestBody DemoSearchParam param) {
	// 参数校验
	JSR303ValidateUtils.validate(param);
	PageHelper.startPage(param.getPageNum(), param.getPageSize());
	List<DemoInfo> infos= d.getDemoInfosByIds(param.getIds());
	return JsonResult.successJsonResult(new Pagination<>(infos));
    }

}
