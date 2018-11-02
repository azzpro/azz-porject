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
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.MerchantConstants.IsChangeGoodsBrandPic;
import com.azz.platform.merchant.api.GoodsBrandService;
import com.azz.platform.merchant.pojo.bo.AddGoodsBrandParam;
import com.azz.platform.merchant.pojo.bo.AddGoodsBrandWebParam;
import com.azz.platform.merchant.pojo.bo.DelGoodsBrandParam;
import com.azz.platform.merchant.pojo.bo.EditGoodsBrandParam;
import com.azz.platform.merchant.pojo.bo.EditGoodsBrandWebParam;
import com.azz.platform.merchant.pojo.bo.GoodsBrandPic;
import com.azz.platform.merchant.pojo.bo.SearchGoodsBrandParam;
import com.azz.platform.merchant.pojo.vo.GoodsBrandInfo;
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
@RequestMapping("/azz/api/merchant/goodsBrand")
public class GoodsBrandController {
	
	@Autowired
	GoodsBrandService goodsBrandService;
	
	/**
     * 
     * <p>查询品牌列表</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月2日 下午2:47:41
     */
	@RequestMapping("/getGoodsBrandInfoList")
	public JsonResult<Pagination<GoodsBrandInfo>> getGoodsBrandInfoList(SearchGoodsBrandParam param){
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
	public JsonResult<GoodsBrandInfo> getGoodsBrandInfo(String brandCode){
		return goodsBrandService.getGoodsBrandInfo(brandCode);
	}
	
	/**
	 * 
	 * <p>新增品牌</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月2日 下午3:04:06
	 * @throws IOException 
	 */
	@RequestMapping("/addGoodsBrand")
	public JsonResult<String> addGoodsBrand(AddGoodsBrandWebParam webParam) throws IOException {
		JSR303ValidateUtils.validate(webParam);
		AddGoodsBrandParam param = new AddGoodsBrandParam();
		BeanUtils.copyProperties(webParam, param);
		MultipartFile goodsBrandPicFile = webParam.getGoodsBrandPicFile();
		GoodsBrandPic goodsBrandPic = new GoodsBrandPic(goodsBrandPicFile.getOriginalFilename(),
		    		goodsBrandPicFile.getSize(), Base64.encode(goodsBrandPicFile.getBytes()));
		param.setGoodsBrandPic(goodsBrandPic);
		param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return goodsBrandService.addGoodsBrand(param);
	}
	
	/**
	 * 
	 * <p>修改品牌</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月2日 下午4:32:28
	 * @throws IOException 
	 */
	@RequestMapping("/editGoodsBrand")
	public JsonResult<String> editGoodsBrand(EditGoodsBrandWebParam webParam) throws IOException {
		JSR303ValidateUtils.validate(webParam);
		EditGoodsBrandParam param = new EditGoodsBrandParam();
		BeanUtils.copyProperties(webParam, param);
		MultipartFile goodsBrandPicFile = webParam.getGoodsBrandPicFile();
		if (webParam.getIsChangeGoodsBrandPic() == IsChangeGoodsBrandPic.Y.getValue() && goodsBrandPicFile != null) {
			GoodsBrandPic goodsBrandPic = new GoodsBrandPic(goodsBrandPicFile.getOriginalFilename(),
					goodsBrandPicFile.getSize(), Base64.encode(goodsBrandPicFile.getBytes()));
			param.setGoodsBrandPic(goodsBrandPic);
		}
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
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
	public JsonResult<String> delGoodsBrand(DelGoodsBrandParam param){
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return goodsBrandService.delGoodsBrand(param);
	}
	
}
