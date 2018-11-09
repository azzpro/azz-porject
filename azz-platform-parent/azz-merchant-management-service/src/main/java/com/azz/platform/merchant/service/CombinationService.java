/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月2日 下午2:23:27
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.MerchantConstants;
import com.azz.core.constants.MerchantConstants.CombinationStatus;
import com.azz.core.constants.MerchantConstants.IsChangeCombinationPic;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.merchant.pojo.vo.UploadFileInfo;
import com.azz.platform.merchant.mapper.MerchantGoodsModuleMapper;
import com.azz.platform.merchant.mapper.MerchantGoodsProductMapper;
import com.azz.platform.merchant.mapper.PlatformCaseMapper;
import com.azz.platform.merchant.mapper.PlatformCombinationMapper;
import com.azz.platform.merchant.mapper.PlatformCombinationModuleMapper;
import com.azz.platform.merchant.pojo.PlatformCase;
import com.azz.platform.merchant.pojo.PlatformCombination;
import com.azz.platform.merchant.pojo.PlatformCombinationModule;
import com.azz.platform.merchant.pojo.bo.AddCombinationParam;
import com.azz.platform.merchant.pojo.bo.CombinationPic;
import com.azz.platform.merchant.pojo.bo.EditCombinationParam;
import com.azz.platform.merchant.pojo.bo.PutOnOrPutOffOrDelCombinationParam;
import com.azz.platform.merchant.pojo.bo.SearchCaseInfoParam;
import com.azz.platform.merchant.pojo.bo.SearchCombinationParam;
import com.azz.platform.merchant.pojo.bo.SearchGoodsModuleParam;
import com.azz.platform.merchant.pojo.bo.SearchProductInfoParam;
import com.azz.platform.merchant.pojo.vo.CaseInfo;
import com.azz.platform.merchant.pojo.vo.CombinationInfo;
import com.azz.platform.merchant.pojo.vo.GoodsModuleInfo;
import com.azz.platform.merchant.pojo.vo.ProdInfo;
import com.azz.system.api.SystemImageUploadService;
import com.azz.system.sequence.api.RandomSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>推荐组合业务</P>
 * @version 1.0
 * @author 黄智聪  2018年11月2日 下午2:23:27
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class CombinationService {
	
	@Autowired
	private RandomSequenceService randomSequenceService;
	
	@Autowired
    private SystemImageUploadService systemImageUploadService;

	@Autowired
	private PlatformCombinationMapper platformCombinationMapper;

	@Autowired
	private PlatformCombinationModuleMapper platformCombinationModuleMapper;
	
	@Autowired
	private MerchantGoodsModuleMapper merchantGoodsModuleMapper;
	
	@Autowired
	private PlatformCaseMapper platformCaseMapper;
	
	@Autowired
	private MerchantGoodsProductMapper merchantGoodsProductMapper;
	
	/**
	 * 
	 * <p>查询组合列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月5日 下午7:10:34
	 */
	public JsonResult<Pagination<CombinationInfo>> getCombinationInfoList(@RequestBody SearchCombinationParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<CombinationInfo> infos = platformCombinationMapper.getCombinationInfoList(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询组合详情</p>
	 * @param combinationCode
	 * @return
	 * @author 黄智聪  2018年11月5日 下午7:21:10
	 */
	public JsonResult<CombinationInfo> getCombinationInfo(String combinationCode){
		CombinationInfo combinationInfo = platformCombinationMapper.getCombinationInfo(combinationCode);
		if(combinationInfo == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐组合不存在");
		}
		List<GoodsModuleInfo> moduleInfos = platformCombinationMapper.getModuleInfoByCombinationCode(combinationCode);
		combinationInfo.setGoodsModuleInfos(moduleInfos);
		return JsonResult.successJsonResult(combinationInfo);
	}
	
	/**
	 * 
	 * <p>新增推荐组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月5日 下午7:41:49
	 */
	public JsonResult<String> addCombination(@RequestBody AddCombinationParam param) {
		// 参数校验
		JSR303ValidateUtils.validate(param);
		// 同方案下模组名称是否重复
		String combinationName = param.getCombinationName();
		// 根据caseCode查询该case是否存在 
		PlatformCase c = platformCaseMapper.selectByCaseCode(param.getCaseCode());
		if(c == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "方案不存在");
		}
		int count = platformCombinationMapper.countCombinationByCombinationNameAndCaseId(null, combinationName, c.getId());
		if(count > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "方案下推荐组合名称已存在");
		}
		List<String> moduleCodes = param.getModuleCodes();
		List<Long> moduleIds = null;
		if(moduleCodes != null && moduleCodes.size() != 0) {
			// 根据code查询出相应的id
			moduleIds = platformCombinationMapper.getModuleIdsByModuleCodes(moduleCodes);
			if(moduleIds.size() == 0) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "所选模组不存在");
			}
			// 同方案下关联商家模组是否完全一致
			count = platformCombinationMapper.countCombinationModuleByCaseIdAndModuleIds(null, moduleIds, c.getId());
			int moduleSize = moduleCodes.size();
			if(count == moduleSize) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "方案下存在商家模组配置一致的推荐组合");
			}
		}
		CombinationPic pic = param.getCombinationPic();
		String combinationCode = randomSequenceService.getCombinationCodeNumber();
		// 上传模组图片
		UploadFileInfo fileInfo = this.uploadCombinationPic(pic, combinationCode);
		// 插入推荐组合记录
		Date nowDate = new Date();
		PlatformCombination combinationRecord = PlatformCombination.builder()
				.caseId(c.getId())
				.combinationCode(combinationCode)
				.combinationName(param.getCombinationName())
				.combinationPicName(fileInfo.getOriginalFileName())
				.combinationPicUrl(fileInfo.getImgUrl())
				.status(param.getStatus())
				.createTime(nowDate)
				.creator(param.getCreator())
				.recommendReason(param.getRecommendReason())
				.build();
		platformCombinationMapper.insertSelective(combinationRecord);
		
		// 若维护了模组才向推荐组合、模组关系表中插入记录
		if(moduleIds != null && moduleIds.size() != 0) {
			// 插入组合与模组的关系记录
			List<PlatformCombinationModule> combinationModuleRecords = new ArrayList<>();
			for (Long moduleId : moduleIds) {
				PlatformCombinationModule combinationModuleRecord = PlatformCombinationModule.builder()
						.combinationId(combinationRecord.getId())
						.createTime(nowDate)
						.creator(param.getCreator())
						.moduleId(moduleId)
						.build();
				combinationModuleRecords.add(combinationModuleRecord);
			}
			platformCombinationModuleMapper.batchInsert(combinationModuleRecords);
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>修改推荐组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月5日 下午7:41:49
	 */
	public JsonResult<String> editCombination(@RequestBody EditCombinationParam param) {
		// 参数校验
		JSR303ValidateUtils.validate(param);
		String combinationCode = param.getCombinationCode();
		PlatformCombination combination = platformCombinationMapper.selectByCombinationCode(combinationCode);
		if(combination == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "该推荐组合不存在");
		}
		// 除本身的推荐组合以外的，同方案下模组名称是否重复
		String combinationName = param.getCombinationName();
		// 根据caseCode查询该case是否存在  
		PlatformCase c = platformCaseMapper.selectByCaseCode(param.getCaseCode());
		if(c == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "方案不存在");
		}
		int count = platformCombinationMapper.countCombinationByCombinationNameAndCaseId(combinationCode, combinationName, c.getId());
		if(count > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "方案下推荐组合名称已存在");
		}
		List<String> moduleCodes = param.getModuleCodes();
		List<Long> moduleIds = null;
		if(moduleCodes != null && moduleCodes.size() != 0) {
			// 根据code查询出相应的id
			moduleIds = platformCombinationMapper.getModuleIdsByModuleCodes(moduleCodes);
			if(moduleIds.size() == 0) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "所选模组不存在");
			}
			// 除本身的推荐组合以外的，同方案下关联商家模组是否完全一致
			count = platformCombinationMapper.countCombinationModuleByCaseIdAndModuleIds(combinationCode, moduleIds, c.getId());
			int moduleSize = moduleCodes.size();
			if(count == moduleSize) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "方案下存在商家模组配置一致的推荐组合");
			}
		}
		Date nowDate = new Date();
		PlatformCombination combinationRecord = PlatformCombination.builder()
				.caseId(c.getId())
				.combinationCode(combinationCode)
				.combinationName(param.getCombinationName())
				.status(param.getStatus())
				.recommendReason(param.getRecommendReason())
				.modifier(param.getModifier())
				.modifyTime(nowDate)
				.build();
		int isChangeCombinationPic = param.getIsChangeCombinationPic();
		if(isChangeCombinationPic == IsChangeCombinationPic.Y.getValue()) {
			CombinationPic pic = param.getCombinationPic();
			// 上传模组图片
			UploadFileInfo fileInfo = this.uploadCombinationPic(pic, combinationCode);
			combinationRecord.setCombinationPicName(fileInfo.getOriginalFileName());
			combinationRecord.setCombinationPicUrl(fileInfo.getImgUrl());
		}
		platformCombinationMapper.updateByCombinationCode(combinationRecord);
		
		// 先删掉原来的推荐组合与模组的绑定
		platformCombinationModuleMapper.deleteByCombinationId(combination.getId());

		// 若维护了模组才向推荐组合、模组关系表中插入记录
		if(moduleIds != null && moduleIds.size() != 0) {
			// 重新插入推荐组合与模组的关系记录
			List<PlatformCombinationModule> combinationModuleRecords = new ArrayList<>();
			for (Long moduleId : moduleIds) {
				PlatformCombinationModule combinationModuleRecord = PlatformCombinationModule.builder()
						.combinationId(combination.getId())
						.createTime(nowDate)
						.creator(param.getModifier())
						.moduleId(moduleId)
						.build();
				combinationModuleRecords.add(combinationModuleRecord);
			}
			platformCombinationModuleMapper.batchInsert(combinationModuleRecords);
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>上架、下架或删除组合</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:01:18
	 */
	public JsonResult<String> putOnOrPutOffOrDelCombination(@RequestBody PutOnOrPutOffOrDelCombinationParam param) {
		// 参数校验
		JSR303ValidateUtils.validate(param);
		String combinationCode = param.getCombinationCode();
		PlatformCombination combination = platformCombinationMapper.selectByCombinationCode(combinationCode);
		if(combination == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐组合不存在");
		}
		boolean yes = CombinationStatus.checkStatusExist(param.getStatus());
		if(!yes) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐组合状态不存在");
		}
		// 若为删除操作，需判断当前推荐组合是否绑定了模组
		if(param.getStatus() == CombinationStatus.DISABLE.getValue()) {
			int count = platformCombinationMapper.countBindingModuleByCombinationCode(combinationCode);
			if(count > 0) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐组合下存在关联的商家模组不可删除");
			}
		}
		PlatformCombination combinationRecord = PlatformCombination.builder()
				.modifier(param.getModifier())
				.modifyTime(new Date())
				.combinationCode(combinationCode)
				.status(param.getStatus())
				.build();
		platformCombinationMapper.updateByCombinationCode(combinationRecord);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>查询方案列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:43:38
	 */
	public JsonResult<Pagination<CaseInfo>> getCaseInfoList(@RequestBody SearchCaseInfoParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<CaseInfo> infos = platformCaseMapper.getCaseInfoList(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:43:38
	 */
	public JsonResult<Pagination<GoodsModuleInfo>> getModuleInfoList(@RequestBody SearchGoodsModuleParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<GoodsModuleInfo> infos = merchantGoodsModuleMapper.getGoodsModuleInfoList(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询产品列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2018年11月6日 下午3:43:38
	 */
	public JsonResult<Pagination<ProdInfo>> getProductInfoList(@RequestBody SearchProductInfoParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<ProdInfo> infos = merchantGoodsProductMapper.getProductInfoList(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>上传推荐组合主图文件</p>
	 * @param pic
	 * @param combinationCode
	 * @return
	 * @author 黄智聪  2018年11月1日 下午4:49:22
	 */
	public UploadFileInfo uploadCombinationPic(CombinationPic pic, String combinationCode) {
		String originalFileName = pic.getFileName();
	    if(StringUtils.isBlank(originalFileName)) {
	    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐组合主图文件名为空");
	    }
	    if(pic.getFileSize() > MerchantConstants.COMBINATION_FILE_SIZE_LIMIT) {
	    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐组合主图文件大小不能超过2M");
	    }
	    String filedata = pic.getFileBase64Str();
	    if(StringUtils.isBlank(filedata)) {
	    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐组合主图文件内容为空");
	    }
	    int dotIndex = originalFileName.lastIndexOf(".");
	    String fileNameNoSufix = originalFileName.substring(0, dotIndex);
	    String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
	    // 新名称为文件名 + 组合编码
	    String newFileName = fileNameNoSufix + "_" + combinationCode;
	    // 图片url
	    JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix,
		    filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_COMBINATION_IMAGE_TYPE);
	    UploadFileInfo file = new UploadFileInfo();
	    if(jr.getCode() == SystemErrorCode.SUCCESS.getCode()) {
	    	file.setImgUrl(jr.getData());
	    	file.setOriginalFileName(originalFileName);
	    }else {
	    	throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE,"推荐组合主图上传失败，请重试");
	    }
	    return file;
	}
	
}

