/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月1日 下午2:23:23
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.merchant.pojo.bo.SearchGoodsBrandParam;
import com.azz.merchant.pojo.vo.GoodsBrandInfo;

/**
 * <P>品牌业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月1日 下午2:23:23
 */
@FeignClient("azz-merchant-service")
public interface GoodsBrandService {
	
	/**
     * 
     * <p>查询品牌列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月2日 下午2:47:41
     */
	@RequestMapping("/azz/api/merchant/goodsBrand/getGoodsBrandInfoList")
	JsonResult<Pagination<GoodsBrandInfo>> getGoodsBrandInfoList(@RequestBody SearchGoodsBrandParam param);
}

