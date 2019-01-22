/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 上午10:48:02
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.MerchantProductErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.WxCourseConstants;
import com.azz.core.constants.WxCourseConstants.CourseStatus;
import com.azz.core.constants.WxCourseConstants.IsChangeCoursePic;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.system.api.SystemImageUploadService;
import com.azz.system.sequence.api.DbSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.azz.util.SystemSeqUtils;
import com.azz.wx.course.mapper.WxCourseClassificationMapper;
import com.azz.wx.course.mapper.WxCourseEvaluationMapper;
import com.azz.wx.course.mapper.WxCourseMapper;
import com.azz.wx.course.mapper.WxCourseParamMapper;
import com.azz.wx.course.mapper.WxCourseParamRelMapper;
import com.azz.wx.course.mapper.WxCourseParamTermMapper;
import com.azz.wx.course.mapper.WxCourseParamTermValueMapper;
import com.azz.wx.course.mapper.WxCourseStartClasRecordMapper;
import com.azz.wx.course.pojo.WxCourse;
import com.azz.wx.course.pojo.WxCourseClassification;
import com.azz.wx.course.pojo.WxCourseParam;
import com.azz.wx.course.pojo.WxCourseParamRel;
import com.azz.wx.course.pojo.WxCourseParamTerm;
import com.azz.wx.course.pojo.WxCourseParamTermValue;
import com.azz.wx.course.pojo.bo.AddCourseParam;
import com.azz.wx.course.pojo.bo.CourseParam;
import com.azz.wx.course.pojo.bo.CoursePic;
import com.azz.wx.course.pojo.bo.EditCourseParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelCourseParam;
import com.azz.wx.course.pojo.bo.SearchCourseInfoParam;
import com.azz.wx.course.pojo.bo.SearchEvaluationInfoParam;
import com.azz.wx.course.pojo.vo.CourseDetail;
import com.azz.wx.course.pojo.vo.CourseInfo;
import com.azz.wx.course.pojo.vo.EvaluationInfo;
import com.azz.wx.course.pojo.vo.Param;
import com.azz.wx.course.pojo.vo.ParamsValue;
import com.azz.wx.course.pojo.vo.ProductParams;
import com.azz.wx.course.pojo.vo.UploadFileInfo;
import com.github.pagehelper.PageHelper;

/**
 * <P>课程业务</P>
 * @version 1.0
 * @author 黄智聪  2019年1月4日 上午10:48:02
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class CourseService {
	
	@Autowired
	private WxCourseMapper wxCourseMapper;
	
	@Autowired
	private WxCourseParamRelMapper wxCourseParamRelMapper;
	
	@Autowired
	private WxCourseClassificationMapper wxCourseClassificationMapper;
	
	@Autowired
	private WxCourseStartClasRecordMapper wxCourseStartClasRecordMapper;
	
	@Autowired
    private SystemImageUploadService systemImageUploadService;
	
	@Autowired
    private DbSequenceService dbSequenceService; 
	
	@Autowired
	private WxCourseParamMapper wxCourseParamMapper;
	
	@Autowired
	private WxCourseParamTermMapper wxCourseParamTermMapper;
	
	@Autowired
	private WxCourseParamTermValueMapper wxCourseParamTermValueMapper;
	
	@Autowired
	private WxCourseEvaluationMapper wxCourseEvaluationMapper;

	/**
	 * 
	 * <p>查询课程列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 上午11:33:03
	 */
	public JsonResult<Pagination<CourseInfo>> getCourseInfos(@RequestBody SearchCourseInfoParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<CourseInfo> infos = wxCourseMapper.getCourseInfos(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询课程详情</p>
	 * @param courseCode
	 * @return
	 * @author 黄智聪  2019年1月4日 下午3:20:59
	 */
	public JsonResult<CourseDetail> getCourseDetail(String courseCode){
		if(StringUtils.isBlank(courseCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "请选择课程");
		}
		// 查询课程信息
		CourseDetail detail = wxCourseMapper.getCourseDetail(courseCode);
		if(detail == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "所选课程不存在");
		}
		// 查询课程所选参数
		Param courseParam = wxCourseMapper.getCourseParamsByCourseCode(courseCode);
		detail.setCourseParams(courseParam);
		return JsonResult.successJsonResult(detail);
	}
	
	/**
	 * 
	 * <p>新增课程</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 上午11:50:51
	 */
	public JsonResult<String> addCourse(@RequestBody AddCourseParam param){
		JSR303ValidateUtils.validate(param);
		WxCourseClassification classification = wxCourseClassificationMapper.selectByClassificationCode(param.getClassificationCode());
		if(classification == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "分类不存在");
		}
		if(classification.getClassificationTop() != 2) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "只允许选择三级分类");
		}
		Date nowDate = new Date();
		
		String courseCode = SystemSeqUtils.getSeq(dbSequenceService.getWxCourseNumber());
		// 上传课程主图
		UploadFileInfo fileInfo = uploadCoursePic(param.getCoursePic(), courseCode);
		// 新增课程信息
		WxCourse wxCourseRecord = WxCourse.builder()
				.brandCode(param.getBrandCode())
				.classificationCode(param.getClassificationCode())
				.courseCode(courseCode)
				.courseDescription(param.getCourseDescription())
				.courseInfo(param.getCourseInfo())
				.courseName(param.getCourseName())
				.coursePicName(fileInfo.getOriginalFileName())
				.coursePicUrl(fileInfo.getImgUrl())
				.createTime(nowDate)
				.creator(param.getCreator())
				.build();
		wxCourseMapper.insertSelective(wxCourseRecord);
		
		// 绑定课程参数
		List<CourseParam> courseParams =  param.getParams();
		if(courseParams != null) {
			for (CourseParam courseParam : courseParams) {
				JSR303ValidateUtils.validate(courseParam);
				WxCourseParamRel wxCourseParamRelRecord =  WxCourseParamRel.builder()
						.courseCode(courseCode)
						.createTime(nowDate)
						.creator(param.getCreator())
						.paramChoice(courseParam.getParamChoice())
						.paramCode(courseParam.getParamCode())
						.paramName(courseParam.getParamName())
						.paramTermCode(courseParam.getParamTermCode())
						.paramTermValue(courseParam.getParamValue())
						.paramType(courseParam.getParamType())
						.build();
				wxCourseParamRelMapper.insertSelective(wxCourseParamRelRecord);
			}
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>修改课程</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 上午11:50:51
	 */
	public JsonResult<String> editCourse(@RequestBody EditCourseParam param){
		JSR303ValidateUtils.validate(param);
		WxCourse course = wxCourseMapper.selectByCourseCode(param.getCourseCode());
		if(course == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程不存在");	
		}
		WxCourseClassification classification = wxCourseClassificationMapper.selectByClassificationCode(param.getClassificationCode());
		if(classification == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "分类不存在");
		}
		if(classification.getClassificationTop() != 2) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "只允许选择三级分类");
		}
		Date nowDate = new Date();
		// 课程信息
		WxCourse wxCourseRecord = WxCourse.builder()
				.id(course.getId())
				.brandCode(param.getBrandCode())
				.classificationCode(param.getClassificationCode())
				.courseDescription(param.getCourseDescription())
				.courseInfo(param.getCourseInfo())
				.courseName(param.getCourseName())
				.modifyTime(nowDate)
				.modifier(param.getModifier())
				.build();
		// 修改课程图片，则重新上传
		int isChangeCoursePic = param.getIsChangeCoursePic();
		if(isChangeCoursePic == IsChangeCoursePic.Y.getValue()) {
			// 上传课程主图
			UploadFileInfo fileInfo = uploadCoursePic(param.getCoursePic(), param.getCourseCode());
			wxCourseRecord.setCoursePicName(fileInfo.getOriginalFileName());
			wxCourseRecord.setCoursePicUrl(fileInfo.getImgUrl());
		}
		wxCourseMapper.updateByPrimaryKeySelective(wxCourseRecord);
		
		// 先删除原来课程所绑定的参数
		wxCourseParamRelMapper.deleteByCourseCode(param.getCourseCode());
		
		// 再重新绑定课程参数
		List<CourseParam> courseParams =  param.getParams();
		if(courseParams != null) {
			for (CourseParam courseParam : courseParams) {
				JSR303ValidateUtils.validate(courseParam);
				WxCourseParamRel wxCourseParamRelRecord =  WxCourseParamRel.builder()
						.courseCode(param.getCourseCode())
						.createTime(nowDate)
						.creator(param.getModifier())
						.paramChoice(courseParam.getParamChoice())
						.paramCode(courseParam.getParamCode())
						.paramName(courseParam.getParamName())
						.paramTermCode(courseParam.getParamTermCode())
						.paramTermValue(courseParam.getParamValue())
						.paramType(courseParam.getParamType())
						.build();
				wxCourseParamRelMapper.insertSelective(wxCourseParamRelRecord);
			}
		}
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>上架、下架或删除课程</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	public JsonResult<String> putOnOrPutOffOrDelCourse(@RequestBody PutOnOrPutOffOrDelCourseParam param){
		// 参数校验
		JSR303ValidateUtils.validate(param);
		String courseCode = param.getCourseCode();
		WxCourse course = wxCourseMapper.selectByCourseCode(courseCode);
		if(course == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程不存在");
		}
		Byte status = param.getStatus();
		boolean exist = CourseStatus.checkStatusExist(status);
		if(!exist) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程状态不存在");
		}
		// 删除要判断是否绑定了开课信息
		if(status.intValue() == CourseStatus.INVALID.getValue()) {
			int count = wxCourseStartClasRecordMapper.countStartClassRecordByCourseCode(courseCode);
			if(count > 0) {
				throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程下已关联开课信息，请先移除后再进行删除");
			}
		}
		WxCourse courseRecord = WxCourse.builder()
				.id(course.getId())
				.status(param.getStatus())
				.modifier(param.getModifier())
				.modifyTime(new Date())
				.build();
		wxCourseMapper.updateByPrimaryKeySelective(courseRecord);
		
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>上传课程主图文件</p>
	 * @param pic
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年11月1日 下午4:49:22
	 */
	public UploadFileInfo uploadCoursePic(CoursePic pic, String courseCode) {
		String originalFileName = pic.getFileName();
	    if(StringUtils.isBlank(originalFileName)) {
	    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程主图文件名为空");
	    }
	    if(pic.getFileSize() > WxCourseConstants.COURSE_PIC_FILE_SIZE_LIMIT) {
	    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程主图文件大小不能超过2M");
	    }
	    String filedata = pic.getFileBase64Str();
	    if(StringUtils.isBlank(filedata)) {
	    	throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程主图文件内容为空");
	    }
	    int dotIndex = originalFileName.lastIndexOf(".");
	    String fileNameNoSufix = originalFileName.substring(0, dotIndex);
	    String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
	    // 新名称为文件名 + 课程编码
	    String newFileName = fileNameNoSufix + "_" + courseCode;
	    // 图片url
	    JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix,
		    filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_WX_COURSE_IMAGE_TYPE);
	    UploadFileInfo file = new UploadFileInfo();
	    if(jr.getCode() == SystemErrorCode.SUCCESS.getCode()) {
	    	file.setImgUrl(jr.getData());
	    	file.setOriginalFileName(originalFileName);
	    }else {
	    	throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE,"课程主图上传失败，请重试");
	    }
	    return file;
	}
	
	/**
	 * <p>新增课程页面获取参数</p>
	 * @param params
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:47:30
	 */
	public JsonResult<ProductParams> getPrams(String code){
		if(org.apache.commons.lang3.StringUtils.isBlank(code))
			throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_ASSORTMENT_IS_NULL);
		WxCourseParam assortmentCode = wxCourseParamMapper.selectParamsByAssortmentCode(code);
		/*if(null == goodsParams)
			throw new BaseException(MerchantProductErrorCode.MERCHANT_PRODUCT_VALUES_IS_NULL);*/
		//根据参数ID 查询参数项类型
		List<WxCourseParamTerm> paramsId = null;
		if(null != assortmentCode) {
			paramsId = wxCourseParamTermMapper.selectParamsByParamsCode(assortmentCode.getParamCode());
		}
		ProductParams pp = new ProductParams();
		if(null !=  paramsId && paramsId.size() > 0) {
			List<ParamsValue> pvs = new ArrayList<>();
			List<String> values = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			//根据参数项ID 查询值
			for (WxCourseParamTerm platformGoodsParamsTerm : paramsId) {
				ParamsValue pv = new ParamsValue();
				pv.setChoice(platformGoodsParamsTerm.getParamChoice());
				pv.setType(platformGoodsParamsTerm.getParamType());
				pv.setParamName(platformGoodsParamsTerm.getParamName());
				pv.setTermCode(platformGoodsParamsTerm.getParamTermCode());
				pv.setParamCode(assortmentCode.getParamCode());
				if(platformGoodsParamsTerm.getParamType() == 1) {
					List<WxCourseParamTermValue> termId = wxCourseParamTermValueMapper.selectValueByCode(platformGoodsParamsTerm.getParamTermCode());
					for (WxCourseParamTermValue ppv : termId) {
						sb.append(ppv.getParamValue());
						if(ppv != termId.get(termId.size()-1)) {
							sb.append(",");
						}
						
					}
					values = Arrays.asList(sb.toString().split(",")).stream().map(s -> s.trim()).collect(Collectors.toList());
					pv.setValues(values);
					sb = new StringBuilder();
				}
				pvs.add(pv);
				pv = null;
			}
			pp.setPvs(pvs);
		}
		return new JsonResult<>(pp);
	}
	
	/*******************************  微信课程首页接口   start ********************************/
	
	/**
	 * 
	 * <p>查询首页课程列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月21日 下午4:25:49
	 */
	public JsonResult<Pagination<CourseInfo>> getIndexCourseInfos(@RequestBody SearchCourseInfoParam param){
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<CourseInfo> infos = wxCourseMapper.getIndexCourseInfos(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/**
	 * 
	 * <p>查询首页课程详情</p>
	 * @param courseCode
	 * @return
	 * @author 黄智聪  2019年1月4日 下午3:20:59
	 */
	public JsonResult<CourseDetail> getIndexCourseDetail(String courseCode){
		if(StringUtils.isBlank(courseCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "请选择课程");
		}
		// 查询课程信息
		CourseDetail detail = wxCourseMapper.getIndexCourseDetail(courseCode);
		if(detail == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "所选课程不存在");
		}
		// 默认购买数量加1000
		detail.setBuyCount(detail.getBuyCount() + WxCourseConstants.COURSE_BUY_COUNT);
		return JsonResult.successJsonResult(detail);
	}
	
	/**
	 * 
	 * <p>查询课程评价</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月21日 下午7:26:26
	 */
	public JsonResult<Pagination<EvaluationInfo>> getEvaluationInfos(@RequestBody SearchEvaluationInfoParam param){
		JSR303ValidateUtils.validate(param);
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<EvaluationInfo> infos = wxCourseEvaluationMapper.getEvaluationInfos(param);
		return JsonResult.successJsonResult(new Pagination<>(infos));
	}
	
	/*******************************  微信课程首页接口     end  ********************************/
}

