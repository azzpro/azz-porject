/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月27日 下午7:03:29
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.PlatformConstants;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.user.mapper.PlatformIndexArticleMapper;
import com.azz.platform.user.mapper.PlatformIndexColumnMapper;
import com.azz.platform.user.mapper.PlatformIndexImageMapper;
import com.azz.platform.user.pojo.PlatformIndexColumn;
import com.azz.platform.user.pojo.bo.AddColumn;
import com.azz.platform.user.pojo.bo.EditColumn;
import com.azz.platform.user.pojo.bo.MainPicture;
import com.azz.platform.user.pojo.vo.ColumnInfo;
import com.azz.system.api.SystemImageUploadService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.StringUtils;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月27日 下午7:03:29
 */
@Service
public class IndexService {
    
    @Autowired
    PlatformIndexColumnMapper platformIndexColumnMapper;
    
    @Autowired
    PlatformIndexArticleMapper platformIndexArticleMapper;
    
    @Autowired
    PlatformIndexImageMapper platformIndexImageMapper;
    
    @Autowired
    private SystemImageUploadService systemImageUploadService;
    
    /**
     * <p>新增栏目</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月27日 下午7:26:16
     */
    public JsonResult<String> addColumn(@RequestBody AddColumn param){
        JSR303ValidateUtils.validate(param);
        
        // 主图基础校验
        MainPicture cp = param.getMainPicture();
        String originalFileName = cp.getFileName();
        
        if (StringUtils.isBlank(originalFileName)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "主图文件名为空");
        }
        if (cp.getFileSize() > PlatformConstants.INDEX_FILE_SIZE_LIMIT) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "主图文件大小不能超过5M");
        }
        String filedata = cp.getFileBase64Str();
        if (StringUtils.isBlank(filedata)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "主图文件内容为空");
        }

        int dotIndex = originalFileName.lastIndexOf(".");
        String fileNameNoSufix = originalFileName.substring(0, dotIndex);
        String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
        // 新名称为文件名 + 文件后缀
        String newFileName = fileNameNoSufix +"_"+ param.getColumnName();

        // 图片url
        JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix, filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_CLASSIFICATION_IMAGE_TYPE);
        if (jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
            throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
        }
        
        PlatformIndexColumn record = new PlatformIndexColumn();
        record.setColumnName(param.getColumnName());
        record.setColumnCode(param.getColumnCode());
        record.setColumnPicName(originalFileName);
        record.setColumnPicUrl(jr.getData());
        record.setColumnType(param.getColumnType());
        record.setCreateTime(new Date());
        record.setCreator(param.getUserCode());
        platformIndexColumnMapper.insertSelective(record);
        
        return JsonResult.successJsonResult(); 
    }

    /**
     * <p>获取所有栏目集合倒序</p>
     * @return
     * @author 彭斌  2018年11月27日 下午8:00:15
     */
    public JsonResult<List<ColumnInfo>> getColumnLsit(){
        List<ColumnInfo> list = platformIndexColumnMapper.getColumnList();
        return JsonResult.successJsonResult(list);
    }

    /**
     * <p>编辑栏目</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月27日 下午9:18:15
     */
    public JsonResult<String> editColumn(@RequestBody EditColumn param){
        JSR303ValidateUtils.validate(param);
        PlatformIndexColumn pic = platformIndexColumnMapper.selectByPrimaryKey(param.getColumnId());
        if(ObjectUtils.isNull(pic)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "未找到栏目信息");
        }
        
        if(1 == param.getEditStatus()) {
            // 主图基础校验
            MainPicture cp = param.getMainPicture();
            String originalFileName = cp.getFileName();
            
            if (StringUtils.isBlank(originalFileName)) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                        "主图文件名为空");
            }
            if (cp.getFileSize() > PlatformConstants.INDEX_FILE_SIZE_LIMIT) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                        "主图文件大小不能超过5M");
            }
            String filedata = cp.getFileBase64Str();
            if (StringUtils.isBlank(filedata)) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                        "主图文件内容为空");
            }

            int dotIndex = originalFileName.lastIndexOf(".");
            String fileNameNoSufix = originalFileName.substring(0, dotIndex);
            String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
            // 新名称为文件名 + 文件后缀
            String newFileName = fileNameNoSufix +"_"+ param.getColumnName();

            // 图片url
            JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix, filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_CLASSIFICATION_IMAGE_TYPE);
            if (jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
                throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
            }
            pic.setColumnPicName(originalFileName);
            pic.setColumnPicUrl(jr.getData());
        }
        
        pic.setColumnName(param.getColumnName());
        pic.setColumnCode(param.getColumnCode());
        pic.setColumnType(param.getColumnType());
        pic.setLastModifyTime(new Date());
        pic.setModifier(param.getUserCode());
        platformIndexColumnMapper.updateByPrimaryKeySelective(pic);
        
        return JsonResult.successJsonResult(); 
    }
    
    /**
     * <p>删除栏目</p>
     * @param columnId
     * @return
     * @author 彭斌  2018年11月27日 下午9:17:38
     */
    public JsonResult<String> delColumn(Long columnId){
        if(null == columnId) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "栏目参数缺失");
        }
        
        PlatformIndexColumn pic = platformIndexColumnMapper.selectByPrimaryKey(columnId);
        if(ObjectUtils.isNull(pic)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "未找到栏目信息");
        }
        
        int iaCount = platformIndexArticleMapper.getIndexArticleByColumnId(columnId);
        int iiCount = platformIndexImageMapper.getIndexImageByColumnId(columnId);
        if(iaCount > 0 || iiCount > 0) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "栏目已被使用，请处理后删除");
        }
        
        platformIndexColumnMapper.deleteByPrimaryKey(columnId);
        return JsonResult.successJsonResult();
    }

    /**
     * <p>获取栏目详情</p>
     * @param columnId
     * @return
     * @author 彭斌  2018年11月27日 下午9:19:57
     */
    public JsonResult<PlatformIndexColumn> getColumnInfo(Long columnId){
        PlatformIndexColumn pic = platformIndexColumnMapper.selectByPrimaryKey(columnId);
        return JsonResult.successJsonResult(pic);
    }
}

