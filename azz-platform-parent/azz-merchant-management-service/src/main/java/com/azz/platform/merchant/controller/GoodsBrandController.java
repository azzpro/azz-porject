/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午6:19:44
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.pojo.PlatformGoodsBrand;
import com.azz.platform.merchant.pojo.bo.AddGoodsBrandParam;
import com.azz.platform.merchant.pojo.bo.DelGoodsBrandParam;
import com.azz.platform.merchant.pojo.bo.EditGoodsBrandParam;
import com.azz.platform.merchant.pojo.bo.SearchGoodsBrandParam;
import com.azz.platform.merchant.pojo.vo.GoodsBrandInfo;
import com.azz.platform.merchant.service.GoodsBrandService;

/**
 * 
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2018年11月2日 下午5:29:00
 */
@RestController
@RequestMapping("/azz/api/merchant/goodsBrand")
public class GoodsBrandController {
	
	@Autowired
	private GoodsBrandService goodsBrandService;
	
	
	
	/**
	 * <p>根据ID查询品牌</p>
	 * @param code
	 * @return
	 * @author 刘建麟  2018年11月2日 下午7:37:03
	 */
	@RequestMapping("selectBrand")
	public List<PlatformGoodsBrand> selectBrand() {
		return goodsBrandService.selectBrand();
	}
	
	/**
     * 
     * <p>查询品牌列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月2日 下午2:47:41
     */
	@RequestMapping("/getGoodsBrandInfoList")
	public JsonResult<Pagination<GoodsBrandInfo>> getGoodsBrandInfoList(@RequestBody SearchGoodsBrandParam param){
		return goodsBrandService.getGoodsBrandInfoList(param);
	}
	
	/**
     * 
     * <p>查询品牌详情</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月2日 下午2:47:41
     */
	@RequestMapping("/getGoodsBrandInfo")
	public JsonResult<GoodsBrandInfo> getGoodsBrandInfo(@RequestParam("brandCode") String brandCode){
		return goodsBrandService.getGoodsBrandInfo(brandCode);
	}
	
	/**
	 * 
	 * <p>新增品牌</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月2日 下午3:04:06
	 */
	@RequestMapping("/addGoodsBrand")
	public JsonResult<String> addGoodsBrand(@RequestBody AddGoodsBrandParam param) {
		return goodsBrandService.addGoodsBrand(param);
	}
	
	/**
	 * 
	 * <p>修改品牌</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月2日 下午4:32:28
	 */
	@RequestMapping("/editGoodsBrand")
	public JsonResult<String> editGoodsBrand(@RequestBody EditGoodsBrandParam param) {
		return goodsBrandService.editGoodsBrand(param);
	}
	
	/**
	 * 
	 * <p>删除品牌</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月2日 下午4:55:28
	 */
	@RequestMapping("/delGoodsBrand")
	public JsonResult<String> delGoodsBrand(@RequestBody DelGoodsBrandParam param){
		return goodsBrandService.delGoodsBrand(param);
	}
	
}

