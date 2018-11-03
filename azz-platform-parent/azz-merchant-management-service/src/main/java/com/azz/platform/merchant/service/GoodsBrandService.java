/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月2日 下午2:23:27
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.MerchantConstants;
import com.azz.core.constants.MerchantConstants.GoodsBrandStatus;
import com.azz.core.constants.MerchantConstants.IsChangeGoodsBrandPic;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.merchant.pojo.vo.UploadFileInfo;
import com.azz.platform.merchant.mapper.PlatformGoodsBrandMapper;
import com.azz.platform.merchant.pojo.PlatformGoodsBrand;
import com.azz.platform.merchant.pojo.bo.AddGoodsBrandParam;
import com.azz.platform.merchant.pojo.bo.DelGoodsBrandParam;
import com.azz.platform.merchant.pojo.bo.EditGoodsBrandParam;
import com.azz.platform.merchant.pojo.bo.GoodsBrandPic;
import com.azz.platform.merchant.pojo.bo.SearchGoodsBrandParam;
import com.azz.platform.merchant.pojo.vo.GoodsBrandInfo;
import com.azz.system.api.SystemImageUploadService;
import com.azz.system.sequence.api.RandomSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
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
	
	@Autowired
	private RandomSequenceService randomSequenceService;
	
	@Autowired
    private SystemImageUploadService systemImageUploadService;
	

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
	
	/**
     * 
     * <p>查询品牌详情</p>
     * @param param
     * @return
     * @author 黄智聪  2018年11月2日 下午2:47:41
     */
	public JsonResult<GoodsBrandInfo> getGoodsBrandInfo(String brandCode){
		return JsonResult.successJsonResult(platformGoodsBrandMapper.getGoodsBrandInfo(brandCode));
	}
	
	/**
	 * 
	 * <p>新增品牌</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月2日 下午3:04:06
	 */
	public JsonResult<String> addGoodsBrand(@RequestBody AddGoodsBrandParam param) {
		// 参数校验
		JSR303ValidateUtils.validate(param);
		int count = platformGoodsBrandMapper.countGoodsBrandByBrandName(param.getBrandName(), null);
		if(count > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌名称已存在");
		}
		count = platformGoodsBrandMapper.countGoodsBrandByBrandEnglishName(param.getBrandName(), null);
		if(count > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌英文名称已存在");
		}
		GoodsBrandPic pic = param.getGoodsBrandPic();
		String brandCode = randomSequenceService.getBrandCodeNumber();
		// 上传模组图片
		UploadFileInfo fileInfo = this.uploadBrandPic(pic, brandCode);
		
		PlatformGoodsBrand goodsBrandRecord = PlatformGoodsBrand.builder()
				.brandCode(brandCode)
				.brandDescription(param.getBrandDescription())
				.brandEnglishName(param.getBrandEnglishName())
				.brandName(param.getBrandName())
				.brandPicName(fileInfo.getOriginalFileName())
				.brandPicUrl(fileInfo.getImgUrl())
				.createTime(new Date())
				.creator(param.getCreator())
				.build();
		platformGoodsBrandMapper.insertSelective(goodsBrandRecord);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>修改品牌</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月2日 下午4:32:28
	 */
	public JsonResult<String> editGoodsBrand(@RequestBody EditGoodsBrandParam param) {
		// 参数校验
		JSR303ValidateUtils.validate(param);
		String brandCode = param.getBrandCode();
		PlatformGoodsBrand brand = platformGoodsBrandMapper.selectByBrandCode(brandCode);
		if(brand == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌不存在");
		}
		// 查询除自己以外的品牌是否存在相同的品牌名
		int count = platformGoodsBrandMapper.countGoodsBrandByBrandName(param.getBrandName(), param.getBrandCode());
		if(count > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌名称已存在");
		}
		// 查询除自己以外的品牌是否存在相同的品牌名
		count = platformGoodsBrandMapper.countGoodsBrandByBrandEnglishName(param.getBrandName(), param.getBrandCode());
		if(count > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌英文名称已存在");
		}
		PlatformGoodsBrand goodsBrandRecord = PlatformGoodsBrand.builder()
				.brandCode(brandCode)
				.brandDescription(param.getBrandDescription())
				.brandEnglishName(param.getBrandEnglishName())
				.brandName(param.getBrandName())
				.modifier(param.getModifier())
				.modifyTime(new Date())
				.build();
		// 修改模组图片，则重新上传
		int isChangeGoodsModulePic = param.getIsChangeGoodsBrandPic();
		if(isChangeGoodsModulePic == IsChangeGoodsBrandPic.Y.getValue()) {
			GoodsBrandPic pic = param.getGoodsBrandPic();
			// 上传模组图片
			UploadFileInfo fileInfo = this.uploadBrandPic(pic, brandCode);
			goodsBrandRecord.setBrandPicName(fileInfo.getOriginalFileName());
			goodsBrandRecord.setBrandPicUrl(fileInfo.getImgUrl());
		}		
		platformGoodsBrandMapper.updateByBrandCode(goodsBrandRecord);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>删除品牌</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月2日 下午4:55:28
	 */
	public JsonResult<String> delGoodsBrand(@RequestBody DelGoodsBrandParam param){
		// 参数校验
		JSR303ValidateUtils.validate(param);
		String brandCode = param.getBrandCode();
		PlatformGoodsBrand brand = platformGoodsBrandMapper.selectByBrandCode(brandCode);
		if(brand == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌不存在");
		}
		// 校验该品牌是否被其他商品所使用
		int count = platformGoodsBrandMapper.countBindingProduct(brand.getId());
		if(count > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌信息使用中，请先处理后删除");
		}
		PlatformGoodsBrand goodsBrandRecord = PlatformGoodsBrand.builder()
				.brandCode(brandCode)
				.modifier(param.getModifier())
				.modifyTime(new Date())
				.status(GoodsBrandStatus.INVALID.getValue())
				.build();
		platformGoodsBrandMapper.updateByBrandCode(goodsBrandRecord);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>上传模组主图文件</p>
	 * @param pic
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年11月1日 下午4:49:22
	 */
	public UploadFileInfo uploadBrandPic(GoodsBrandPic pic, String brandCode) {
		String originalFileName = pic.getFileName();
	    if(StringUtils.isBlank(originalFileName)) {
	    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌主图文件名为空");
	    }
	    if(pic.getFileSize() > MerchantConstants.GOODS_BRAND_FILE_SIZE_LIMIT) {
	    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌主图文件大小不能超过2M");
	    }
	    String filedata = pic.getFileBase64Str();
	    if(StringUtils.isBlank(filedata)) {
	    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌主图文件内容为空");
	    }
	    int dotIndex = originalFileName.lastIndexOf(".");
	    String fileNameNoSufix = originalFileName.substring(0, dotIndex);
	    String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
	    // 新名称为文件名 + 品牌编码
	    String newFileName = fileNameNoSufix + "_" + brandCode;
	    // 图片url
	    JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix,
		    filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_BRAND_IMAGE_TYPE);
	    UploadFileInfo file = new UploadFileInfo();
	    if(jr.getCode() == SystemErrorCode.SUCCESS.getCode()) {
	    	file.setImgUrl(jr.getData());
	    	file.setOriginalFileName(originalFileName);
	    }else {
	    	throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE,"品牌主图上传失败，请重试");
	    }
	    return file;
	}
	
	
	/**
	 * <p>根据ID查询品牌</p>
	 * @param code
	 * @return
	 * @author 刘建麟  2018年11月2日 下午7:37:03
	 */
	public List<PlatformGoodsBrand> selectBrand() {
		return platformGoodsBrandMapper.selectBrand();
	}
	
	public PlatformGoodsBrand selectById(Long id) {
		return platformGoodsBrandMapper.selectById(id);
	}
	
}

