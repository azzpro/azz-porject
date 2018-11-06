package com.azz.platform.merchant.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.constants.FileConstants;
import com.azz.core.constants.PlatformConstants;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.merchant.mapper.PlatformCaseMapper;
import com.azz.platform.merchant.pojo.PlatformCase;
import com.azz.platform.merchant.pojo.bo.AddCaseParam;
import com.azz.platform.merchant.pojo.bo.CasePic;
import com.azz.platform.merchant.pojo.bo.ClassificationPic;
import com.azz.platform.merchant.pojo.bo.EditCaseParam;
import com.azz.system.api.SystemImageUploadService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.StringUtils;

/**
 * <P>
 * 分类管理
 * </P>
 * 
 * @version 1.0
 * @author 彭斌 2018年10月20日 下午2:54:40
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class CaseService {

    @Autowired
    SystemImageUploadService systemImageUploadService;
    
    @Autowired
    PlatformCaseMapper platformCaseMapper;
    
    /**
     * <p>新增方案</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月5日 下午7:23:52
     */
    public JsonResult<String> addCase(@RequestBody AddCaseParam param) {
        JSR303ValidateUtils.validate(param);

        // 主图基础校验
        CasePic cp = param.getCasePic();
        String originalFileName = cp.getFileName();
        
        PlatformCase pcObj = new PlatformCase();
        
        // 新增方案编码 TODO 系统生成
        String caseCode = "";
        
        if (StringUtils.isBlank(originalFileName)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "主图文件名为空");
        }
        if (cp.getFileSize() > PlatformConstants.CLASSIFICATION_FILE_SIZE_LIMIT) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "主图文件大小不能超过2M");
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
        String newFileName = fileNameNoSufix +"_"+ caseCode;

        // 图片url
        JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix, filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_CLASSIFICATION_IMAGE_TYPE);
        if (jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
            throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
        }
        
        pcObj.setCaseCode(caseCode);
        pcObj.setCaseName(param.getCaseName());
        pcObj.setCasePicName(originalFileName);
        pcObj.setCasePicUrl(jr.getData());
        pcObj.setCaseStatus(param.getCaseStatus());
        pcObj.setClassificationId(param.getClassificationId());
        pcObj.setCreateTime(new Date());
        pcObj.setCreator(param.getCreator());
        pcObj.setRemark(param.getRemark());
       
        platformCaseMapper.insertSelective(pcObj);
        return JsonResult.successJsonResult();
    }


    public JsonResult<String> editCase(@RequestBody EditCaseParam param){
        
        PlatformCase pcObj = platformCaseMapper.selectByCaseCode(param.getCaseCode());
        
        if(param.getIsEditPic() == 1) {
            // 主图基础校验
            CasePic cp = param.getCasePic();
            String originalFileName = cp.getFileName();
            if (StringUtils.isBlank(originalFileName)) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                        "主图文件名为空");
            }
            if (cp.getFileSize() > PlatformConstants.CLASSIFICATION_FILE_SIZE_LIMIT) {
                throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                        "主图文件大小不能超过2M");
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
            String newFileName = fileNameNoSufix + "_" + param.getCaseCode();
            
            // 图片url
            JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix, filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_CLASSIFICATION_IMAGE_TYPE);
            
            if (jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
                throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
            }
            
            pcObj.setCasePicName(originalFileName);
            pcObj.setCasePicUrl(jr.getData());
        }
        
        if(!pcObj.getCaseName().equals(param.getCaseName())) {
            // 编辑的方案名称和原始的方案名不一致校验方案名称是否唯一
            PlatformCase platformCase = platformCaseMapper.selectByCaseName(param.getCaseName());
            if(ObjectUtils.isNotNull(platformCase)) {
                // 方案名称存在
                throw new BaseException(
                        PlatformUserErrorCode.PLATFORM_MERCHANT_AUDIT_STATUS_ERROR_NO_EXIST);
            }
        }
        
        pcObj.setCaseName(param.getCaseName());
        pcObj.setCaseStatus(param.getCaseStatus());
        pcObj.setClassificationId(param.getClassificationId());
        pcObj.setRemark(param.getRemark());
        platformCaseMapper.updateByPrimaryKeySelective(pcObj);
        return null;
    }
    
}

