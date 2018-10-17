/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.platform.user.api.DeptService;
import com.azz.platform.user.pojo.bo.AddDeptParam;

/**
 * <P>部门管理</P>
 * @version 1.0
 * @author 彭斌  2018年10月17日 下午3:04:47
 */
@RestController
@RequestMapping("/azz/api/dept")
public class DeptController {

	private static final Logger LOG = LoggerFactory.getLogger(DeptController.class);

	@Autowired
	private DeptService deptService;
	
	/**
	 * <p>新增部门信息</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年10月17日 下午3:17:05
	 */
	@RequestMapping("addDeptInfo")
	public JsonResult<String> addDeptInfo(AddDeptParam param) {
		LOG.info("###########开始新增部门信息###########");
		return deptService.addDeptInfo(param);
	}

}
