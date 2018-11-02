/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月2日 下午2:23:27
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.merchant.mapper.PlatformGoodsBrandMapper;
import com.azz.merchant.pojo.bo.SearchGoodsBrandParam;
import com.azz.merchant.pojo.vo.GoodsBrandInfo;
import com.github.pagehelper.PageHelper;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月2日 下午2:23:27
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class GoodsBrandService {
	
	@Autowired
	private PlatformGoodsBrandMapper platformGoodsBrandMapper;
	
	/**
     * 
     * <p>查询品牌列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月2日 下午2:47:41
     */
	public JsonResult<Pagination<GoodsBrandInfo>> getGoodsBrandInfoList(@RequestBody SearchGoodsBrandParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<GoodsBrandInfo> infos = platformGoodsBrandMapper.getGoodsBrandInfoList(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
}

