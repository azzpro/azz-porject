/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月5日 下午4:54:06
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
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.WxCourseConstants.CourseStatus;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.merchant.mapper.PlatformRecommendMapper;
import com.azz.platform.merchant.mapper.PlatformRecommendModuleProductRelMapper;
import com.azz.platform.merchant.mapper.PlatformRecommendModuleRelMapper;
import com.azz.platform.merchant.mapper.PlatformSpecialPerformanceMapper;
import com.azz.platform.merchant.pojo.PlatformRecommend;
import com.azz.platform.merchant.pojo.PlatformRecommendModuleProductRel;
import com.azz.platform.merchant.pojo.PlatformRecommendModuleRel;
import com.azz.platform.merchant.pojo.PlatformSpecialPerformance;
import com.azz.platform.merchant.pojo.bo.AddOrRemoveModuleParam;
import com.azz.platform.merchant.pojo.bo.AddOrRemoveProductParam;
import com.azz.platform.merchant.pojo.bo.AddRecommendParam;
import com.azz.platform.merchant.pojo.bo.EditRecommendParam;
import com.azz.platform.merchant.pojo.bo.PutOnOrPutOffRecommendParam;
import com.azz.platform.merchant.pojo.bo.SearchRecommendInfoParam;
import com.azz.platform.merchant.pojo.bo.SearchRecommendProductInfoParam;
import com.azz.platform.merchant.pojo.bo.SearchRelatedModuleInfoParam;
import com.azz.platform.merchant.pojo.bo.SearchSpecialPerformanceRelatedModuleInfoParam;
import com.azz.platform.merchant.pojo.vo.ModuleInfo;
import com.azz.platform.merchant.pojo.vo.RecommendInfo;
import com.azz.platform.merchant.pojo.vo.RecommentProductInfo;
import com.azz.platform.merchant.pojo.vo.RelatedModuleInfo;
import com.azz.util.JSR303ValidateUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>专场--推荐活动业务</P>
 * @version 1.0
 * @author 黄智聪  2019年1月5日 下午4:54:06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RecommendService {
	
	@Autowired
	private PlatformSpecialPerformanceMapper platformSpecialPerformanceMapper;
	
	@Autowired
	private PlatformRecommendMapper platformRecommendMapper;
	
	@Autowired
	private PlatformRecommendModuleRelMapper platformRecommendModuleRelMapper;
	
	@Autowired
	private PlatformRecommendModuleProductRelMapper platformRecommendModuleProductRelMapper;
	
	/**
	 * 
	 * <p>查询某专场的推荐活动列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 上午11:27:04
	 */
	public JsonResult<List<RecommendInfo>> getRecommendInfos(@RequestBody SearchRecommendInfoParam param){
		JSR303ValidateUtils.validate(param);
		List<RecommendInfo> recommendInfos = platformRecommendMapper.getRecommendInfos(param);
		return JsonResult.successJsonResult(recommendInfos);
	}
	
	/**
	 * 
	 * <p>新增专场推荐活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 上午11:56:09
	 */
	public JsonResult<String> addRecommend(@RequestBody AddRecommendParam param){
		JSR303ValidateUtils.validate(param);
		PlatformSpecialPerformance sp = platformSpecialPerformanceMapper.selectBySpecialPerformanceCode(param.getSpecialPerformanceCode());
		if(sp == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "专场不存在");
		}
		
		String recommendCode = System.currentTimeMillis() + "";// TODO
		Byte status = param.getStatus();
		boolean exist = CourseStatus.checkStatusExist(status);
		if(!exist) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐活动状态不存在");
		}
		PlatformRecommend record = PlatformRecommend.builder()
				.createTime(new Date())
				.creator(param.getCreator())
				.recommendCode(recommendCode)
				.recommendName(param.getRecommendName())
				.status(status)
				.specialPerformanceCode(param.getSpecialPerformanceCode())
				.build();
		platformRecommendMapper.insertSelective(record);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>修改专场推荐活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 上午11:56:09
	 */
	public JsonResult<String> editRecommend(@RequestBody EditRecommendParam param){
		JSR303ValidateUtils.validate(param);
		PlatformRecommend recommend = platformRecommendMapper.selectByRecommendCode(param.getRecommendCode());
		if(recommend == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐活动不存在");
		}
		Byte status = param.getStatus();
		boolean exist = CourseStatus.checkStatusExist(status);
		if(!exist) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐活动状态不存在");
		}
		PlatformRecommend record = PlatformRecommend.builder()
				.id(recommend.getId())
				.recommendName(param.getRecommendName())
				.status(status)
				.modifier(param.getModifier())
				.modifyTime(new Date())
				.build();
		platformRecommendMapper.updateByPrimaryKeySelective(record);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>上架或下架推荐活动</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午1:08:46
	 */
	public JsonResult<String> putOnOrPutOffRecommend(@RequestBody PutOnOrPutOffRecommendParam param){
		JSR303ValidateUtils.validate(param);
		PlatformRecommend recommend = platformRecommendMapper.selectByRecommendCode(param.getRecommendCode());
		if(recommend == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐活动不存在");
		}
		Byte status = param.getStatus();
		boolean exist = CourseStatus.checkStatusExist(status);
		if(!exist) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "推荐活动状态不存在");
		}
		PlatformRecommend record = PlatformRecommend.builder()
				.id(recommend.getId())
				.status(status)
				.modifier(param.getModifier())
				.modifyTime(new Date())
				.build();
		platformRecommendMapper.updateByPrimaryKeySelective(record);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>查询关联模组信息</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:38:35
	 */
	public JsonResult<Pagination<ModuleInfo>> getRelatedModuleInfos(@RequestBody SearchRelatedModuleInfoParam param){
		JSR303ValidateUtils.validate(param);
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<ModuleInfo> infos = platformRecommendMapper.getRelatedModuleInfos(param);
		return JsonResult.successJsonResult(new Pagination<ModuleInfo>(infos));
	}
	
	/**
	 * 
	 * <p>新增或移除模组</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:52:04
	 */
	public JsonResult<String> addOrRemoveModule(@RequestBody AddOrRemoveModuleParam param){
		JSR303ValidateUtils.validate(param);
		switch (param.getAddOrRemove()) {
			case 1: // 新增模组
				if(platformRecommendModuleRelMapper.existModule(param.getModuleCode()) == 0) {
					throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "所选模组不存在");
				}
				
				// 计算该模组已被关联的数量
				int count = platformRecommendModuleRelMapper.countRelatedModule(param.getModuleCode());
				if(count > 0) {// 如果该模组已被其他推荐活动关联，啥也不做
					return JsonResult.successJsonResult();
				}
				// 查询模组的产品编码集合
				List<String> productCodes = platformRecommendModuleRelMapper.getProductCodesByModuleCode(param.getModuleCode());
				PlatformRecommendModuleRel record = PlatformRecommendModuleRel.builder()
						.createTime(new Date())
						.creator(param.getCreator())
						.moduleCode(param.getModuleCode())
						.recommendCode(param.getRecommendCode())
						.productNumber(productCodes.size())
						.build();
				platformRecommendModuleRelMapper.insertSelective(record);
				
				if(productCodes.size() > 0) { // 该模组下存在产品
					// 批量插入该模组下所有产品
					List<PlatformRecommendModuleProductRel> records = new ArrayList<>();
					for (String productCode : productCodes) {
						PlatformRecommendModuleProductRel moduleProductRelRecord = PlatformRecommendModuleProductRel.builder()
								.createTime(new Date())
								.creator(param.getCreator())
								.moduleCode(param.getModuleCode())
								.productCode(productCode)
								.build();
						records.add(moduleProductRelRecord);
					}
					platformRecommendModuleProductRelMapper.batchInsert(records);	
				}
				break;
			case 2: //　移除模组
				platformRecommendModuleRelMapper.deleteRecommendModule(param.getModuleCode(), param.getRecommendCode());
				// 将关联的所有模组产品信息删除
				platformRecommendModuleProductRelMapper.deleteByModuleCode(param.getModuleCode());
				break;
			default:
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "操作不存在");
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>查询专场所关联的模组列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:52:04
	 */
	public JsonResult<Pagination<RelatedModuleInfo>> getSpecialPerformanceRelatedModuleInfos(@RequestBody SearchSpecialPerformanceRelatedModuleInfoParam param){
		JSR303ValidateUtils.validate(param);
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<RelatedModuleInfo> infos = platformRecommendMapper.getSpecialPerformanceRelatedModuleInfos(param);
		return JsonResult.successJsonResult(new Pagination<RelatedModuleInfo>(infos));
	}
	
	/**
	 * 
	 * <p>查询活动中某个模组所关联的产品列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:52:04
	 */
	public JsonResult<Pagination<RecommentProductInfo>> getRecommentProductInfos(@RequestBody SearchRecommendProductInfoParam param){
		JSR303ValidateUtils.validate(param);
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<RecommentProductInfo> infos = platformRecommendMapper.getRecommentProductInfos(param);
		return JsonResult.successJsonResult(new Pagination<RecommentProductInfo>(infos));
	}
	
	/**
	 * 
	 * <p>新增或移除模组中的产品</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月7日 下午2:52:04
	 */
	public JsonResult<String>  addOrRemoveProduct(@RequestBody AddOrRemoveProductParam param){
		JSR303ValidateUtils.validate(param);
		switch (param.getAddOrRemove()) {
			case 1: // 新增产品
				// 查询产品是否被绑定过
				int count = platformRecommendModuleProductRelMapper.countProduct(param.getProductCode());
				if(count > 0) { // 如果被绑定过，啥也不干
					return JsonResult.successJsonResult();
				}
				PlatformRecommendModuleProductRel record = PlatformRecommendModuleProductRel.builder()
						.createTime(new Date())
						.creator(param.getCreator())
						.moduleCode(param.getModuleCode())
						.productCode(param.getProductCode())
						.build();
				platformRecommendModuleProductRelMapper.insertSelective(record);	
				// 产品数量+1
				platformRecommendModuleRelMapper.updateProductNumber(param.getModuleCode(), 1);
				break;
			case 2: //　移除产品 
				int rows = platformRecommendModuleProductRelMapper.deleteByModuleCodeAndProductCode(param.getModuleCode(), param.getProductCode());
				if(rows > 0) {// 如果有更新才产品数量才减1
					// 产品数量-1
					platformRecommendModuleRelMapper.updateProductNumber(param.getModuleCode(), -1);
				}
				break;
			default:
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "操作不存在");
		}
		return JsonResult.successJsonResult();
	}
	
}

