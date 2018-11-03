/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月2日 下午2:23:27
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.pojo.PlatformGoodsBrand;
import com.azz.platform.merchant.pojo.bo.AddGoodsBrandParam;
import com.azz.platform.merchant.pojo.bo.DelGoodsBrandParam;
import com.azz.platform.merchant.pojo.bo.EditGoodsBrandParam;
import com.azz.platform.merchant.pojo.bo.SearchGoodsBrandParam;
import com.azz.platform.merchant.pojo.vo.GoodsBrandInfo;

/**
 * <P>品牌业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月2日 下午2:23:27
 */
@FeignClient("azz-merchant-management-service")
public interface GoodsBrandService {
	
	@RequestMapping("/azz/api/merchant/goodsBrand/selectById")
	PlatformGoodsBrand selectById(@RequestParam("id") Long id);
	
	@RequestMapping("/azz/api/merchant/goodsBrand/selectBrand")
	List<PlatformGoodsBrand> selectBrand() ;
	/**
     * 
     * <p>查询品牌列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月2日 下午2:47:41
     */
	@RequestMapping("/azz/api/merchant/goodsBrand/getGoodsBrandInfoList")
	JsonResult<Pagination<GoodsBrandInfo>> getGoodsBrandInfoList(@RequestBody SearchGoodsBrandParam param);
	
	/**
     * 
     * <p>查询品牌详情</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月2日 下午2:47:41
     */
	@RequestMapping("/azz/api/merchant/goodsBrand/getGoodsBrandInfo")
	JsonResult<GoodsBrandInfo> getGoodsBrandInfo(@RequestParam("brandCode") String brandCode);
	
	/**
	 * 
	 * <p>新增品牌</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月2日 下午3:04:06
	 */
	@RequestMapping("/azz/api/merchant/goodsBrand/addGoodsBrand")
	JsonResult<String> addGoodsBrand(@RequestBody AddGoodsBrandParam param);
	
	/**
	 * 
	 * <p>修改品牌</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月2日 下午4:32:28
	 */
	@RequestMapping("/azz/api/merchant/goodsBrand/editGoodsBrand")
	JsonResult<String> editGoodsBrand(@RequestBody EditGoodsBrandParam param);
	
	/**
	 * 
	 * <p>删除品牌</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月2日 下午4:55:28
	 */
	@RequestMapping("/azz/api/merchant/goodsBrand/delGoodsBrand")
	JsonResult<String> delGoodsBrand(@RequestBody DelGoodsBrandParam param);
	
	
	
}

