/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年5月13日 下午4:53:12
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
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.WxSolicitContributionConstants;
import com.azz.core.constants.WxSolicitContributionConstants.IsChangeSolicitContributionPic;
import com.azz.core.constants.WxSolicitContributionConstants.SolicitContributionStatus;
import com.azz.core.exception.BaseException;
import com.azz.core.reconstructed.exception.ValidationException;
import com.azz.system.api.SystemImageUploadService;
import com.azz.system.bo.UploadImageParam;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.azz.wx.course.mapper.WxSolicitContributionMapper;
import com.azz.wx.course.pojo.WxSolicitContribution;
import com.azz.wx.course.pojo.bo.AddSolicitContributionParam;
import com.azz.wx.course.pojo.bo.EditSolicitContributionParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelSolicitContributionParam;
import com.azz.wx.course.pojo.bo.SearchSolicitContributionParam;
import com.azz.wx.course.pojo.bo.SolicitContributionPic;
import com.azz.wx.course.pojo.vo.SolicitContributionInfo;
import com.azz.wx.course.pojo.vo.UploadFileInfo;
import com.github.pagehelper.PageHelper;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年5月13日 下午4:53:12
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class SolicitContributionService {
	
	@Autowired
	private WxSolicitContributionMapper wxSolicitContributionMapper; 
	
	@Autowired
	private SystemImageUploadService systemImageUploadService;
	
	/**
	 * 
	 * <p>查询征稿列表</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:08:04
	 */
	public JsonResult<Pagination<SolicitContributionInfo>> getSolicitContributionInfos(@RequestBody SearchSolicitContributionParam param) {
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<SolicitContributionInfo> solicitContributions = wxSolicitContributionMapper.getSolicitContributions(param);
		return JsonResult.successJsonResult(new Pagination<>(solicitContributions));
	}
	
	/**
	 * 
	 * <p>查询征稿详情</p>
	 * @param solicitContributionCode
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:23:36
	 */
	public JsonResult<SolicitContributionInfo> getSolicitContributionDetail(@RequestParam("solicitContributionCode")String solicitContributionCode){
		JSR303ValidateUtils.validateNullOrBlank(solicitContributionCode, "请选择记录");
		SolicitContributionInfo detail = wxSolicitContributionMapper.getSolicitContributionDetail(solicitContributionCode);
		if(detail == null) {
			throw new ValidationException("征稿记录不存在");
		}
		return JsonResult.successJsonResult(detail);
	}
	
	/**
	 * 
	 * <p>新增征稿</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年5月13日 下午5:43:38
	 */
	public JsonResult<String> addSolicitContribution(@RequestBody AddSolicitContributionParam param){
		JSR303ValidateUtils.validateInputParam(param);
		SolicitContributionPic pic = param.getSolicitContributionPicFile();
		String solicitContributionCode = WxSolicitContributionConstants.SOLICIT_CONTRIBUTION_CODE_PRIFIX + System.currentTimeMillis();
		UploadFileInfo fileInfo = uploadSolicitContributionPic(pic, solicitContributionCode);
		WxSolicitContribution record = WxSolicitContribution.builder()
				.createTime(new Date())
				.creator(param.getCreator())
				.remark(param.getRemark())
				.solicitContributionCode(solicitContributionCode)
				.solicitContributionContent(param.getSolicitContributionContent())
				.solicitContributionName(param.getSolicitContributionName())
				.solicitContributionPicUrl(fileInfo.getImgUrl())
				.solicitContributionPicName(fileInfo.getOriginalFileName())
				.voteCount(0)
				.solicitContributionStatus(param.getSolicitContributionStatus())
				.build();
		wxSolicitContributionMapper.insertSelective(record);
		return  JsonResult.successJsonResult();
	}
	
	
	/**
	 * 
	 * <p>修改征稿</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年4月17日 上午11:58:12
	 */
	public JsonResult<String> editSolicitContribution(@RequestBody EditSolicitContributionParam param) {
		JSR303ValidateUtils.validateInputParam(param);
		WxSolicitContribution solicitContribution = wxSolicitContributionMapper.selectByCode(param.getSolicitContributionCode());
		if(solicitContribution == null) {
			throw new ValidationException("征稿不存在");
		}
		WxSolicitContribution record = WxSolicitContribution.builder()
				.id(solicitContribution.getId())
				.solicitContributionContent(param.getSolicitContributionContent())
				.solicitContributionName(param.getSolicitContributionName())
				.solicitContributionStatus(param.getSolicitContributionStatus())
				.modifyTime(new Date())
				.modifier(param.getModifier())
				.remark(param.getRemark())
				.build();
		// 修改了主图，则重新上传
		int isChangeSolicitContributionPic = param.getIsChangeSolicitContributionPic();
		if(isChangeSolicitContributionPic == IsChangeSolicitContributionPic.Y.getValue()) {
			UploadFileInfo fileInfo = uploadSolicitContributionPic(param.getSolicitContributionPicFile(), param.getSolicitContributionCode());
			record.setSolicitContributionPicName(fileInfo.getOriginalFileName());
			record.setSolicitContributionPicUrl(fileInfo.getImgUrl());
		}
		wxSolicitContributionMapper.updateByPrimaryKeySelective(record);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>上架、下架或删除征稿</p>
	 * @param param
	 * @return
	 * @author 黄智聪  2019年1月4日 下午2:51:18
	 */
	public JsonResult<String> putOnOrPutOffOrDelSolicitContribution(@RequestBody PutOnOrPutOffOrDelSolicitContributionParam param){
		// 参数校验
		JSR303ValidateUtils.validateInputParam(param);
		WxSolicitContribution solicitContribution = wxSolicitContributionMapper.selectByCode(param.getSolicitContributionCode());
		if(solicitContribution == null) {
			throw new ValidationException("征稿不存在");
		}
		Byte status = param.getSolicitContributionStatus();
		boolean exist = SolicitContributionStatus.checkStatusExist(status);
		if(!exist) {
			throw new ValidationException("征稿状态不存在");
		}
		WxSolicitContribution record = WxSolicitContribution.builder()
				.id(solicitContribution.getId())
				.modifyTime(new Date())
				.modifier(param.getModifier())
				.solicitContributionStatus(status)
				.build();
		wxSolicitContributionMapper.updateByPrimaryKeySelective(record);
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>上传征稿主图文件</p>
	 * @param pic
	 * @param moduleCode
	 * @return
	 * @author 黄智聪  2018年11月1日 下午4:49:22
	 */
	public UploadFileInfo uploadSolicitContributionPic(SolicitContributionPic pic, String solicitContributionCode) {
		String originalFileName = pic.getFileName();
	    if(StringUtils.isBlank(originalFileName)) {
	    	throw new ValidationException("征稿主图文件名为空");
	    }
	    if(pic.getFileSize() > WxSolicitContributionConstants.SOLICIT_CONTRIBUTION_PIC_FILE_SIZE_LIMIT) {
	    	throw new ValidationException("征稿主图文件大小不能超过2M");
	    }
	    String filedata = pic.getFileBase64Str();
	    if(StringUtils.isBlank(filedata)) {
	    	throw new ValidationException("征稿主图文件内容为空");
	    }
	    int dotIndex = originalFileName.lastIndexOf(".");
	    String fileNameNoSufix = originalFileName.substring(0, dotIndex);
	    String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
	    // 新名称为文件名 + 征稿编码
	    String newFileName = fileNameNoSufix + "_" + solicitContributionCode;
	    // 图片url
	    UploadImageParam up = new UploadImageParam();
	    up.setBucketname(FileConstants.IMAGE_BUCKETNAME);
	    up.setFiledata(filedata);
	    up.setFilename(newFileName);
	    up.setImagetype(FileConstants.AZZ_WX_COURSE_IMAGE_TYPE);
	    up.setPlattype(FileConstants.AZZ_PLATFORM);
	    up.setSuffix(sufix);
	    JsonResult<String> jr = systemImageUploadService.uploadImage(up);
	    UploadFileInfo file = new UploadFileInfo();
	    if(jr.getCode() == SystemErrorCode.SUCCESS.getCode()) {
	    	file.setImgUrl(jr.getData());
	    	file.setOriginalFileName(originalFileName);
	    }else {
	    	throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE,"征稿主图上传失败，请重试");
	    }
	    return file;
	}

}

