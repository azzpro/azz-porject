/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月14日 下午2:40:47
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.login.api.LoginService;
import com.azz.order.api.OrderService;
import com.azz.platform.goods.api.DemoService;
import com.azz.platform.goods.pojo.Demo;
import com.azz.platform.goods.pojo.bo.DemoSearchParam;
import com.azz.platform.goods.pojo.vo.DemoInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月14日 下午2:40:47
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

	
	@Autowired
	private DemoService ds;
	
	/*@Autowired
	private OrderService os;
	
	@RequestMapping("getGoods")
	public JSONObject getGoods() {
		JSONObject jb = new JSONObject();
		os.getD();
	    List<Demo> name = ds.getName();
	   // List<Order> orders = osc.getName();
		jb.put("data", name);
	//	jb.put("msg", orders);
		return jb;
	}*/
	
	@RequestMapping("getDemoInfosByIds")
	public JsonResult<Pagination<DemoInfo>> getDemoInfosByIds(DemoSearchParam param) {
		return ds.getDemoInfosByIds(param);
	}
	
	
}

