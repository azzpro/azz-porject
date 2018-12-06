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
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.PlatformConstants;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.user.mapper.PlatformClientSignUpMapper;
import com.azz.platform.user.mapper.PlatformIndexArticleMapper;
import com.azz.platform.user.mapper.PlatformIndexColumnMapper;
import com.azz.platform.user.mapper.PlatformIndexImageMapper;
import com.azz.platform.user.pojo.PlatformClientSignUp;
import com.azz.platform.user.pojo.PlatformIndexArticle;
import com.azz.platform.user.pojo.PlatformIndexColumn;
import com.azz.platform.user.pojo.PlatformIndexImage;
import com.azz.platform.user.pojo.bo.AddArticle;
import com.azz.platform.user.pojo.bo.AddColumn;
import com.azz.platform.user.pojo.bo.AddImage;
import com.azz.platform.user.pojo.bo.EditArticle;
import com.azz.platform.user.pojo.bo.EditColumn;
import com.azz.platform.user.pojo.bo.EditImage;
import com.azz.platform.user.pojo.bo.EditSignUpCourseParam;
import com.azz.platform.user.pojo.bo.MainPicture;
import com.azz.platform.user.pojo.bo.SearchArticleParam;
import com.azz.platform.user.pojo.bo.SearchCourseParam;
import com.azz.platform.user.pojo.bo.SearchImageParam;
import com.azz.platform.user.pojo.vo.ArticleInfo;
import com.azz.platform.user.pojo.vo.ColumnInfo;
import com.azz.platform.user.pojo.vo.ImageInfo;
import com.azz.platform.user.pojo.vo.SignUpCourse;
import com.azz.system.api.SystemImageUploadService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.StringUtils;
import com.github.pagehelper.PageHelper;

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
    PlatformClientSignUpMapper platformClientSignUpMapper;
    
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
        
        int countName = platformIndexColumnMapper.countColumn(param.getColumnName());
        int countCode = platformIndexColumnMapper.countColumnByCode(param.getColumnCode());
        if(countName > 0 || countCode > 0) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "参数栏目名已经创建不允许重复创建");
        }
        
        PlatformIndexColumn record = new PlatformIndexColumn();
        // 主图基础校验
        MainPicture cp = param.getMainPicture();
        if(ObjectUtils.isNotNull(cp)) {
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
            record.setColumnPicName(originalFileName);
            record.setColumnPicUrl(jr.getData());
        }
        
       
        record.setColumnName(param.getColumnName());
        record.setColumnCode(param.getColumnCode());
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
        
        if(!pic.getColumnName().equals(param.getColumnName().trim())) {
            int countName = platformIndexColumnMapper.countColumn(param.getColumnName());
            int countCode = platformIndexColumnMapper.countColumnByCode(param.getColumnCode());
            if(countName > 0 || countCode > 0) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                        "参数栏目名已经创建不允许重复创建");
            }
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

    /**
     * <p>获取图片管理分页列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月28日 下午2:29:58
     */
    public JsonResult<Pagination<ImageInfo>> getImageList(@RequestBody SearchImageParam param){
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ImageInfo> list = platformIndexImageMapper.selectIndexImageList(param);
        return JsonResult.successJsonResult(new Pagination<>(list));
    }
    
    /**
     * <p>新增图片</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月28日 下午2:49:47
     */
    public JsonResult<String> addImage(@RequestBody AddImage param){
        JSR303ValidateUtils.validate(param);
        
        PlatformIndexImage record = new PlatformIndexImage();
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
        String newFileName = fileNameNoSufix +"_"+ param.getIndexColumnId();

        // 图片url
        JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix, filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_CLASSIFICATION_IMAGE_TYPE);
        if (jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
            throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
        }
        
        record.setPicName(originalFileName);
        record.setPicUrl(jr.getData());
        record.setIndexColumnId(param.getIndexColumnId());
        record.setJumpLink(param.getJumpLink());
        record.setCreator(param.getUserCode());
        record.setCreateTime(new Date());
        platformIndexImageMapper.insertSelective(record);
        return JsonResult.successJsonResult();
    }

    /**
     * <p>编辑图片管理</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月28日 下午3:23:34
     */
    public JsonResult<String> editImage(@RequestBody EditImage param){
        JSR303ValidateUtils.validate(param);
        PlatformIndexImage pii = platformIndexImageMapper.selectByPrimaryKey(param.getImageId());
        if(ObjectUtils.isNull(pii)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "未找到图片信息");
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
            String newFileName = fileNameNoSufix +"_"+ param.getImageId();

            // 图片url
            JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix, filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_CLASSIFICATION_IMAGE_TYPE);
            if (jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
                throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
            }
            pii.setPicName(originalFileName);
            pii.setPicUrl(jr.getData());
        }
        pii.setIndexColumnId(param.getColumnId());
        pii.setJumpLink(param.getJumpLink());
        pii.setLastModifyTime(new Date());
        pii.setModifier(param.getUserCode());
        platformIndexImageMapper.updateByPrimaryKeySelective(pii);
        
        return JsonResult.successJsonResult();
    }

    /**
     * <p>获取图片详情</p>
     * @param imageId
     * @return
     * @author 彭斌  2018年11月28日 下午3:33:33
     */
    public JsonResult<PlatformIndexImage> getImageInfo(Long imageId){
        PlatformIndexImage pii = platformIndexImageMapper.selectByPrimaryKey(imageId);
        return JsonResult.successJsonResult(pii);
    }

    /**
     * <p>删除图片管理</p>
     * @param imageId
     * @return
     * @author 彭斌  2018年11月28日 下午3:47:10
     */
    public JsonResult<String> delImage(Long imageId){
        platformIndexImageMapper.deleteByPrimaryKey(imageId);
        return JsonResult.successJsonResult();
    }

    /**
     * <p>获取文章分页列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月28日 下午5:12:43
     */
    public JsonResult<Pagination<ArticleInfo>> getArticleList(@RequestBody SearchArticleParam param){
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ArticleInfo> list = platformIndexArticleMapper.getArticleTitleList(param);
        return JsonResult.successJsonResult(new Pagination<>(list));
    }

    /**
     * <p>获取文章详情</p>
     * @param articleId
     * @return
     * @author 彭斌  2018年11月28日 下午7:11:28
     */
    public JsonResult<PlatformIndexArticle> getArticleInfo(Long articleId){
        PlatformIndexArticle pia = platformIndexArticleMapper.selectByPrimaryKey(articleId);
        return JsonResult.successJsonResult(pia);
    }
    
    /**
     * <p>新增文章</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 上午10:41:00
     */
    public JsonResult<String> addArticle(@RequestBody AddArticle param){
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
        String newFileName = fileNameNoSufix +"_"+ param.getIndexColumnId();

        // 图片url
        JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix, filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_CLASSIFICATION_IMAGE_TYPE);
        if (jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
            throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
        }
        
        PlatformIndexArticle record = new PlatformIndexArticle();
        record.setIndexColumnId(param.getIndexColumnId());
        record.setArticleContent(param.getArticleDetail());
        record.setArticlePicName(originalFileName);
        record.setArticlePicUrl(jr.getData());
        record.setArticleTitle(param.getArticleTitle());
        record.setCreateTime(new Date());
        record.setCreator(param.getUserCode());
        record.setPrice(param.getPrice());
        record.setRemark1(param.getRemark1());
        record.setRemark2(param.getRemark2());
        
        platformIndexArticleMapper.insertSelective(record);
        return JsonResult.successJsonResult();
    }

    /**
     * <p>修改文章</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月29日 上午11:42:48
     */
    public JsonResult<String> editArticle(@RequestBody EditArticle param){
        JSR303ValidateUtils.validate(param);
        
        PlatformIndexArticle pii = platformIndexArticleMapper.selectByPrimaryKey(param.getArticleId());
        
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
            String newFileName = fileNameNoSufix +"_"+ param.getIndexColumnId();

            // 图片url
            JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix, filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_CLASSIFICATION_IMAGE_TYPE);
            if (jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
                throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
            } 
            pii.setArticlePicName(originalFileName);
            pii.setArticlePicUrl(jr.getData());
        }
        pii.setModifier(param.getUserCode());
        pii.setLastModifyTime(new Date());
        pii.setRemark1(param.getRemark1());
        pii.setRemark2(param.getRemark2());
        pii.setIndexColumnId(param.getIndexColumnId());
        pii.setArticleContent(param.getArticleDetail());
        pii.setPrice(param.getPrice());
        pii.setArticleTitle(param.getArticleTitle());
        platformIndexArticleMapper.updateByPrimaryKeySelective(pii);
        return JsonResult.successJsonResult();
    }

    /**
     * <p>删除文章</p>
     * @param articleId
     * @return
     * @author 彭斌  2018年11月29日 上午11:46:49
     */
    public JsonResult<String> delArticle(Long articleId){
        platformIndexArticleMapper.deleteByPrimaryKey(articleId);
        return JsonResult.successJsonResult();
    }
    
    /**
     * <p>获取报名管理列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年12月6日 上午11:17:24
     */
    public JsonResult<Pagination<SignUpCourse>> getClientSignUpList(@RequestBody SearchCourseParam param){
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<SignUpCourse> list = platformClientSignUpMapper.getClientSignUpList(param);
        return JsonResult.successJsonResult(new Pagination<>(list));
    }
    
    /**
     * <p>处理报名管理</p>
     * @param param
     * @return
     * @author 彭斌  2018年12月6日 上午11:20:58
     */
    public JsonResult<String> editSignUp(@RequestBody EditSignUpCourseParam param){
        if(null == param.getId()) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "参数异常");
        }
        PlatformClientSignUp pcs = platformClientSignUpMapper.selectByPrimaryKey(param.getId());
        if(ObjectUtils.isNotNull(pcs)) {
            if(1 != pcs.getStatus()) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "已处理过不允许再次处理");
            } 
            pcs.setModifier(param.getClientUserCode());
            pcs.setModifyTime(new Date());
            pcs.setRemark(param.getRemark());
            platformClientSignUpMapper.updateByPrimaryKeySelective(pcs);
        }
        return JsonResult.successJsonResult();
    }
}

