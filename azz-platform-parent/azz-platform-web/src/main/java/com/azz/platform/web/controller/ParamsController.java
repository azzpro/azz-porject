/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月31日 下午2:17:53
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.api.ParamsService;
import com.azz.platform.merchant.pojo.bo.ParamsData;
import com.azz.platform.merchant.pojo.bo.SearchParams;
import com.azz.platform.merchant.pojo.vo.Params;
import com.azz.utils.WebUtils;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月31日 下午2:17:53
 */
@RestController
@RequestMapping("/azz/api/merchant")
public class ParamsController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ParamsService paramsService;
	
	/**
	 * <p>参数列表</p>
	 * @param param
	 * @return
	 * @author 刘建麟  2018年10月31日 下午2:38:44
	 */
	@RequestMapping(value="searchParamsList",method=RequestMethod.POST)
	public JsonResult<Pagination<Params>> searchParamsList(SearchParams param){
		return paramsService.searchParamsList(param);
	}
	
	@RequestMapping(value="addParams",method=RequestMethod.POST)
	public JsonResult<String> addParams(List<ParamsData> params){
		return paramsService.addParams(params,WebUtils.getLoginUser().getUserInfo().getUserCode());
		/*List<GoodsType> list = Arrays.asList(
	            new GoodsType(0, "typeName0", null),
	            new GoodsType(1, "typeName1", 0),
	            new GoodsType(2, "typeName2", 1),
	            new GoodsType(3, "typeName3", 2),
	            new GoodsType(4, "typeName4", 3),
	            new GoodsType(5, "typeName5", 4)
	    );
		Map<Integer, List<GoodsType>> map = list.stream()
	            .filter(o -> Objects.nonNull(o.getTypeParent()))
	            .collect(Collectors.groupingBy(GoodsType::getTypeParent));
	    //循环处理子节点 构建树状结构
	    list.forEach(goodsType -> {
	        if (map.containsKey(goodsType.getTypeId())) {
	            goodsType.setSubGoods(map.get(goodsType.getTypeId()));
	        }
	    });

	    //获取指定节点的对象
	    GoodsType result = list.stream().filter(goodsType -> goodsType.getTypeId() == 0).findFirst().orElse(null);
	    System.out.println(JSON.toJSONString(result, true));*/
	}
}

