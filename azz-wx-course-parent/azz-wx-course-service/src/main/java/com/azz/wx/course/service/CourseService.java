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
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.system.api.SystemImageUploadService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.azz.wx.course.mapper.WxCourseMapper;
import com.azz.wx.course.mapper.WxCourseParamRelMapper;
import com.azz.wx.course.pojo.WxCourse;
import com.azz.wx.course.pojo.bo.AddCourseParam;
import com.azz.wx.course.pojo.bo.CoursePic;
import com.azz.wx.course.pojo.bo.SearchCourseInfoParam;
import com.azz.wx.course.pojo.vo.CourseInfo;
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
	 * <p>新增课程</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 上午11:50:51
	 */
	public JsonResult<String> addCourse(@RequestBody AddCourseParam param){
		JSR303ValidateUtils.validate(param);
		Date nowDate = new Date();
		String courseCode = System.currentTimeMillis() + ""; // TODO
		// 上传课程主图
		UploadFileInfo fileInfo = uploadCoursePic(param.getCoursePic(), courseCode);
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

