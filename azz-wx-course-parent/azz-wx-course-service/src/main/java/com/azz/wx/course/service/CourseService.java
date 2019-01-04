/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 上午10:48:02
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.wx.course.service;

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
import com.azz.core.constants.WxCourseConstants;
import com.azz.core.constants.WxCourseConstants.CourseStatus;
import com.azz.core.constants.WxCourseConstants.IsChangeCoursePic;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.system.api.SystemImageUploadService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.azz.wx.course.mapper.WxCourseClassificationMapper;
import com.azz.wx.course.mapper.WxCourseMapper;
import com.azz.wx.course.mapper.WxCourseParamRelMapper;
import com.azz.wx.course.mapper.WxCourseStartClasRecordMapper;
import com.azz.wx.course.pojo.WxCourse;
import com.azz.wx.course.pojo.WxCourseClassification;
import com.azz.wx.course.pojo.WxCourseParamRel;
import com.azz.wx.course.pojo.bo.AddCourseParam;
import com.azz.wx.course.pojo.bo.CourseParam;
import com.azz.wx.course.pojo.bo.CoursePic;
import com.azz.wx.course.pojo.bo.EditCourseParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelCourseParam;
import com.azz.wx.course.pojo.bo.SearchCourseInfoParam;
import com.azz.wx.course.pojo.vo.CourseDetail;
import com.azz.wx.course.pojo.vo.CourseInfo;
import com.azz.wx.course.pojo.vo.Param;
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
	public JsonResult<CourseDetail> getCourseInfo(String courseCode){
		if(StringUtils.isBlank(courseCode)) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "请选择课程");
		}
		CourseDetail detail = wxCourseMapper.getCourseDetail(courseCode);
		if(detail == null) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "所选课程不存在");
		}
		// 分类所有参数
		List<Param> allParams = wxCourseMapper.getAllParamsByClassificationCode(detail.getClassificationCode());
		// 课程所选参数
		List<Param> courseParams = null;
		detail.setCourseParams(courseParams);
		detail.setAllParams(allParams);
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
		String courseCode = System.currentTimeMillis() + ""; // TODO
		// 上传课程主图
		UploadFileInfo fileInfo = uploadCoursePic(param.getCoursePic(), courseCode);
		// 新增课程信息
		WxCourse wxCourseRecord = WxCourse.builder()
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
		boolean exist = CourseStatus.checkStatusExist(param.getStatus());
		if(!exist) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程状态不存在");
		}
		// 是否绑定了开课信息
		int count = wxCourseStartClasRecordMapper.countStartClassRecordByCourseCode(courseCode);
		if(count > 0) {
			throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "课程下已关联开课信息，请先移除后再进行删除！");
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
	    // 图片url  TODO
	    JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix,
		    filedata, FileConstants.AZZ_MERCHANT, FileConstants.AZZ_MODULE_IMAGE_TYPE);
	    UploadFileInfo file = new UploadFileInfo();
	    if(jr.getCode() == SystemErrorCode.SUCCESS.getCode()) {
	    	file.setImgUrl(jr.getData());
	    	file.setOriginalFileName(originalFileName);
	    }else {
	    	throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE,"课程主图上传失败，请重试");
	    }
	    return file;
	}
	
	
}

