/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月7日 下午1:42:42
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
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.PlatformConstants;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.merchant.mapper.PlatformSpecialPerformanceMapper;
import com.azz.platform.merchant.pojo.PlatformSpecialPerformance;
import com.azz.platform.merchant.pojo.bo.AddSpecialParam;
import com.azz.platform.merchant.pojo.bo.ChangeSpecialStatus;
import com.azz.platform.merchant.pojo.bo.EditSpecialParam;
import com.azz.platform.merchant.pojo.bo.SearchSpecialParam;
import com.azz.platform.merchant.pojo.bo.SpecialPic;
import com.azz.platform.merchant.pojo.vo.SpecialInfo;
import com.azz.system.api.SystemImageUploadService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.StringUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>专场service</P>
 * @version 1.0
 * @author 彭斌  2019年1月7日 下午1:42:42
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpecialService {
    
    @Autowired
    private PlatformSpecialPerformanceMapper platformSpecialPerformanceMapper;
    
    @Autowired
    private SystemImageUploadService systemImageUploadService;

    /**
     * <p>查询专场列表</p>
     * @param param
     * @return
     * @author 彭斌  2019年1月7日 下午2:25:01
     */
    public JsonResult<Pagination<SpecialInfo>> searchSpecialList(@RequestBody SearchSpecialParam param){
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<SpecialInfo> list = platformSpecialPerformanceMapper.getSpecialList(param);
        return JsonResult.successJsonResult(new Pagination<>(list));
    }
    
    /**
     * <p>专场详情</p>
     * @param code
     * @return 
     * @author 彭斌  2019年1月7日 下午2:29:00
     */
    public JsonResult<SpecialInfo> specialInfo(@RequestParam("code") String code){
        // 产品数量
        Integer productNumber = platformSpecialPerformanceMapper.getSumProductNumber(code);
        // 产品数量
        int moduleNumber = platformSpecialPerformanceMapper.getSumModuleNumber(code);
        // 详情
        SpecialInfo si = platformSpecialPerformanceMapper.getSpecialInfo(code);
        
        si.setProductNumber((long)productNumber);
        si.setModuleNumber((long)moduleNumber);
        
        PlatformSpecialPerformance record = new PlatformSpecialPerformance();
        record.setId(si.getId());
        record.setProductNumber((long)productNumber);
        record.setModuleNumber((long)moduleNumber);
        platformSpecialPerformanceMapper.updateByPrimaryKeySelective(record);
        
        return JsonResult.successJsonResult(si);
    }
    
    /**
     * <p>新建专场</p>
     * @param param
     * @return
     * @author 彭斌  2019年1月7日 下午2:54:03
     */
    public JsonResult<String> addSpecial(@RequestBody AddSpecialParam param){
        JSR303ValidateUtils.validate(param);
        
        // 校验专场名称是否唯一
        int countSpecial = platformSpecialPerformanceMapper.countSpecial(param.getSpecialName());
        if(countSpecial > 0 ) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "专场名称已存在");
        }
        SpecialPic mainPic = param.getMainPic();
        SpecialPic bgPic = param.getBgPic();
        PlatformSpecialPerformance record = new PlatformSpecialPerformance();
        record.setSpecialPerformanceName(param.getSpecialName());
        // SP+专场编码为时间戳
        String spfc = String.valueOf(System.currentTimeMillis());
        record.setSpecialPerformanceCode("SP" + spfc);
        record.setCreateTime(new Date());
        record.setCreator(param.getCreator());
        /*****************************专场主图start******************************/
        String originalFileNameMainPic = mainPic.getFileName();
        if (StringUtils.isBlank(originalFileNameMainPic)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "主图文件名为空");
        }
        if (mainPic.getFileSize() > PlatformConstants.CLASSIFICATION_FILE_SIZE_LIMIT) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "主图文件大小不能超过2M");
        }
        String filedata = mainPic.getFileBase64Str();
        if (StringUtils.isBlank(filedata)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "主图文件内容为空");
        }
        
        int dotIndex = originalFileNameMainPic.lastIndexOf(".");
        String fileNameNoSufix = originalFileNameMainPic.substring(0, dotIndex);
        String sufix = originalFileNameMainPic.substring(dotIndex + 1, originalFileNameMainPic.length());
        // 新名称为文件名 + 文件后缀
        String newFileName = fileNameNoSufix + "_main_" + record.getSpecialPerformanceCode();
        
        // 图片url
        JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix, filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_SPECIAL_PERFORMANCE_IMAGE_TYPE);
        
        if (jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
            throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
        }
        /*****************************专场主图end******************************/
        
        /*****************************专场背景图start******************************/
        String originalFileNameBgPic = bgPic.getFileName();
        if (StringUtils.isBlank(originalFileNameBgPic)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "主图文件名为空");
        }
        if (bgPic.getFileSize() > PlatformConstants.CLASSIFICATION_FILE_SIZE_LIMIT) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "主图文件大小不能超过2M");
        }
        String filedataBgPic = bgPic.getFileBase64Str();
        if (StringUtils.isBlank(filedataBgPic)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "主图文件内容为空");
        }
        
        int dotIndexBg = originalFileNameBgPic.lastIndexOf(".");
        String fileNameNoSufixBg = originalFileNameBgPic.substring(0, dotIndexBg);
        String sufixBg = originalFileNameBgPic.substring(dotIndexBg + 1, originalFileNameBgPic.length());
        // 新名称为文件名 + 文件后缀
        String newFileNameBg = fileNameNoSufixBg + "_bg_" + record.getSpecialPerformanceCode();
        
        // 图片url
        JsonResult<String> jrBg = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileNameBg, sufixBg, filedataBgPic, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_SPECIAL_PERFORMANCE_IMAGE_TYPE);
        
        if (jrBg.getCode() != SystemErrorCode.SUCCESS.getCode()) {
            throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
        }
        /*****************************专场背景图end******************************/
        record.setSpecialPerformanceMainPicName(originalFileNameMainPic);
        record.setSpecialPerformanceMainPicUrl(jr.getData());
        record.setSpecialPerformanceBgPicName(originalFileNameBgPic);
        record.setSpecialPerformanceBgPicUrl(jrBg.getData());
        record.setSpecialPerformanceLink("http://www.izz2025.com/special.html?specialCode=" + "SP" + spfc);
        platformSpecialPerformanceMapper.insertSelective(record);
        return JsonResult.successJsonResult();
    }
    
    /**
     * <p>修改专场信息</p>
     * @param param
     * @return
     * @author 彭斌  2019年1月7日 下午4:41:25
     */
    public JsonResult<String> editSpecial(@RequestBody EditSpecialParam param){
        JSR303ValidateUtils.validate(param);
        
        SpecialInfo si = platformSpecialPerformanceMapper.getSpecialInfo(param.getSpecialCode());
        if(ObjectUtils.isNull(si)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "专场不存在");
        }
        
        if(!si.getSpecialPerformanceName().equals(param.getSpecialName())) {
            // 校验编辑的专场名称是否唯一
            int countSpecial = platformSpecialPerformanceMapper.countSpecial(param.getSpecialName());
            if(countSpecial > 0 ) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                        "专场名称已存在");
            }
        }
        PlatformSpecialPerformance record = new PlatformSpecialPerformance();
        
        if(param.getIsEditMainPic() == 1) {
            SpecialPic mainPic = param.getMainPic();
           
            /*****************************专场主图start******************************/
            String originalFileNameMainPic = mainPic.getFileName();
            if (StringUtils.isBlank(originalFileNameMainPic)) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                        "主图文件名为空");
            }
            if (mainPic.getFileSize() > PlatformConstants.CLASSIFICATION_FILE_SIZE_LIMIT) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                        "主图文件大小不能超过2M");
            }
            String filedata = mainPic.getFileBase64Str();
            if (StringUtils.isBlank(filedata)) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                        "主图文件内容为空");
            }
            
            int dotIndex = originalFileNameMainPic.lastIndexOf(".");
            String fileNameNoSufix = originalFileNameMainPic.substring(0, dotIndex);
            String sufix = originalFileNameMainPic.substring(dotIndex + 1, originalFileNameMainPic.length());
            // 新名称为文件名 + 文件后缀
            String newFileName = fileNameNoSufix + "_main_" + record.getSpecialPerformanceCode();
            
            // 图片url
            JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix, filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_SPECIAL_PERFORMANCE_IMAGE_TYPE);
            
            if (jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
                throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
            }
            record.setSpecialPerformanceMainPicName(originalFileNameMainPic);
            record.setSpecialPerformanceMainPicUrl(jr.getData());
            /*****************************专场主图end******************************/
        }
        
        if(param.getIsEditBgPic() == 1) {
            SpecialPic bgPic = param.getBgPic();
            /*****************************专场背景图start******************************/
            String originalFileNameBgPic = bgPic.getFileName();
            if (StringUtils.isBlank(originalFileNameBgPic)) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                        "主图文件名为空");
            }
            if (bgPic.getFileSize() > PlatformConstants.CLASSIFICATION_FILE_SIZE_LIMIT) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                        "主图文件大小不能超过2M");
            }
            String filedataBgPic = bgPic.getFileBase64Str();
            if (StringUtils.isBlank(filedataBgPic)) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                        "主图文件内容为空");
            }
            
            int dotIndexBg = originalFileNameBgPic.lastIndexOf(".");
            String fileNameNoSufixBg = originalFileNameBgPic.substring(0, dotIndexBg);
            String sufixBg = originalFileNameBgPic.substring(dotIndexBg + 1, originalFileNameBgPic.length());
            // 新名称为文件名 + 文件后缀
            String newFileNameBg = fileNameNoSufixBg + "_bg_" + record.getSpecialPerformanceCode();
            
            // 图片url
            JsonResult<String> jrBg = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileNameBg, sufixBg, filedataBgPic, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_SPECIAL_PERFORMANCE_IMAGE_TYPE);
            
            if (jrBg.getCode() != SystemErrorCode.SUCCESS.getCode()) {
                throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
            }
            record.setSpecialPerformanceBgPicName(originalFileNameBgPic);
            record.setSpecialPerformanceBgPicUrl(jrBg.getData());
            /*****************************专场背景图end******************************/
        }
        record.setSpecialPerformanceName(param.getSpecialName());
        record.setId(si.getId());
        record.setModifier(param.getModifier());
        record.setModifyTime(new Date());
        platformSpecialPerformanceMapper.updateByPrimaryKeySelective(record);
        
        return JsonResult.successJsonResult();
    }
    
    /**
     * <p>上架下架</p>
     * @param param
     * @return
     * @author 彭斌  2019年1月7日 下午4:02:56
     */
    public JsonResult<String> changeStatus(@RequestBody ChangeSpecialStatus param){
        JSR303ValidateUtils.validate(param);
        
        SpecialInfo si = platformSpecialPerformanceMapper.getSpecialInfo(param.getSpecialCode());
        if(ObjectUtils.isNull(si)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "该专场不存在");
        }
        PlatformSpecialPerformance record = new PlatformSpecialPerformance();
        if("Y".equals(param.getStatus())) {
            // 上架
            record.setStatus((byte) 1);
        } else if("N".equals(param.getStatus())) {
            // 下架
            record.setStatus((byte) 2);
        }
        record.setId(si.getId());
        record.setModifier(param.getModifier());
        record.setModifyTime(new Date());
        platformSpecialPerformanceMapper.updateByPrimaryKeySelective(record);
        return JsonResult.successJsonResult();
    }
    
}

