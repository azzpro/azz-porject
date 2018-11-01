package com.azz.platform.merchant.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.errorcode.SystemErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.ClientConstants;
import com.azz.core.constants.FileConstants;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.merchant.mapper.PlatformGoodsClassificationMapper;
import com.azz.platform.merchant.pojo.PlatformGoodsClassification;
import com.azz.platform.merchant.pojo.bo.AddClassificationParam;
import com.azz.platform.merchant.pojo.bo.ClassificationPic;
import com.azz.platform.merchant.pojo.bo.DelClassificationParam;
import com.azz.platform.merchant.pojo.bo.EditClassificationParam;
import com.azz.platform.merchant.pojo.bo.SearchClassificationListParam;
import com.azz.platform.merchant.pojo.vo.Classification;
import com.azz.platform.merchant.pojo.vo.ClassificationList;
import com.azz.system.api.SystemImageUploadService;
import com.azz.system.sequence.api.RandomSequenceService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.StringUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>
 * 分类管理
 * </P>
 * 
 * @version 1.0
 * @author 彭斌 2018年10月20日 下午2:54:40
 */
@Transactional(rollbackFor=Exception.class)
@Service
public class ClassificationService{

    @Autowired
    PlatformGoodsClassificationMapper platformGoodsClassificationMapper;
    
    @Autowired
    private RandomSequenceService randomSequenceService;
    
    @Autowired
    private SystemImageUploadService systemImageUploadService;

    /**
     * <p>新增分类</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月31日 下午4:16:31
     */
    public JsonResult<String> addClassification(@RequestBody AddClassificationParam param) {
        JSR303ValidateUtils.validate(param);
        
        // 主图基础校验
        ClassificationPic cp = param.getClassificationPic();
        String originalFileName = cp.getFileName();
        if(StringUtils.isBlank(originalFileName)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "主图文件名为空");
        }
        if(cp.getFileSize() > ClientConstants.AVATAR_FILE_SIZE_LIMIT) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "主图文件大小不能超过2M");
        }
        String filedata = cp.getFileBase64Str();
        if(StringUtils.isBlank(filedata)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "主图文件内容为空");
        }
        
        int dotIndex = originalFileName.lastIndexOf(".");
        String fileNameNoSufix = originalFileName.substring(0, dotIndex);
        String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
        // 新名称为文件名 + 文件后缀
        String newFileName = fileNameNoSufix + "." + sufix;
        
        // 图片url
        JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.AZZ_CLASSIFICATION_PATH, newFileName, sufix,
            filedata, FileConstants.AZZ_CLIENT, FileConstants.AZZ_AVATAR_IMAGE_TYPE);
        if(jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
            throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
        }
        
        PlatformGoodsClassification record = new PlatformGoodsClassification();
        if(null != param.getAssortmentParentCode()) {
            PlatformGoodsClassification pgc = platformGoodsClassificationMapper.selectByAssortmentCode(param.getAssortmentParentCode());
            if(ObjectUtils.isNull(pgc)) {
                throw new BaseException(PlatformUserErrorCode.PLATFORM_PRODUCT_CLASSIFICATION_NO_EXIST);
            }
            
            if(pgc.getAssortmentTop().equals(1)) {
                record.setAssortmentTop((byte)2);
            } else if(pgc.getAssortmentTop().equals(2)) {
                record.setAssortmentTop((byte)3);
            }
        } else {
            record.setAssortmentTop((byte)1);
        }
        
        // 分类编码
        String classificationCode = randomSequenceService.getClassificationNumber();
        record.setAssortmentCode(classificationCode);
        record.setAssortmentParentCode(null == param.getAssortmentParentCode()?"0":param.getAssortmentParentCode());
        record.setAssortmentName(param.getAssortmentName());
        record.setAssortmentSort(param.getAssortmentSort());
        record.setAssortmentPicName(originalFileName);
        record.setAssortmentPicUrl(jr.getData());
        record.setCreateTime(new Date());
        record.setCreator(param.getCreator());
        platformGoodsClassificationMapper.insertSelective(record);
        
        return JsonResult.successJsonResult();
    }


    /**
     * <p>修改分类</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月31日 下午4:36:55
     */
    public JsonResult<String> editClassification(@RequestBody EditClassificationParam param) {
        JSR303ValidateUtils.validate(param);
        
        // 主图基础校验
        ClassificationPic cp = param.getClassificationPic();
        String originalFileName = cp.getFileName();
        if(StringUtils.isBlank(originalFileName)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "主图文件名为空");
        }
        if(cp.getFileSize() > ClientConstants.AVATAR_FILE_SIZE_LIMIT) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "主图文件大小不能超过2M");
        }
        String filedata = cp.getFileBase64Str();
        if(StringUtils.isBlank(filedata)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "主图文件内容为空");
        }
        
        int dotIndex = originalFileName.lastIndexOf(".");
        String fileNameNoSufix = originalFileName.substring(0, dotIndex);
        String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
        // 新名称为文件名 + 文件后缀
        String newFileName = fileNameNoSufix + "." + sufix;
        
        // 图片url
        JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.AZZ_CLASSIFICATION_PATH, newFileName, sufix,
            filedata, FileConstants.AZZ_CLIENT, FileConstants.AZZ_AVATAR_IMAGE_TYPE);
        if(jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
            throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
        }
        
        PlatformGoodsClassification pgcObj = platformGoodsClassificationMapper.selectByAssortmentCode(param.getAssortmentParentCode());
        
        if(null != param.getAssortmentParentCode()) {
            PlatformGoodsClassification pgc = platformGoodsClassificationMapper.selectByAssortmentCode(param.getAssortmentParentCode());
            if(ObjectUtils.isNull(pgc)) {
                throw new BaseException(PlatformUserErrorCode.PLATFORM_PRODUCT_CLASSIFICATION_NO_EXIST);
            }
            
            if(pgc.getAssortmentTop().equals(1)) {
                pgcObj.setAssortmentTop((byte)2);
            } else if(pgc.getAssortmentTop().equals(2)) {
                pgcObj.setAssortmentTop((byte)3);
            }
        } else {
            pgcObj.setAssortmentTop((byte)1);
        }
        
        // 分类编码
        pgcObj.setAssortmentParentCode(null == param.getAssortmentParentCode()?"0":param.getAssortmentParentCode());
        pgcObj.setAssortmentName(param.getAssortmentName());
        pgcObj.setAssortmentSort(param.getAssortmentSort());
        pgcObj.setAssortmentPicName(originalFileName);
        pgcObj.setAssortmentPicUrl(jr.getData());
        pgcObj.setModifyTime(new Date());
        pgcObj.setModifier(param.getModifier());
        platformGoodsClassificationMapper.updateByPrimaryKeySelective(pgcObj);
        
        return JsonResult.successJsonResult();
    }
    
    /**
     * <p>删除分类</p>
     * @param assortmentCode
     * @return
     * @author 彭斌  2018年10月31日 下午4:52:11
     */
    public JsonResult<String> delClassification(@RequestBody DelClassificationParam param){
        JSR303ValidateUtils.validate(param);
        PlatformGoodsClassification pgcObj = platformGoodsClassificationMapper.selectByAssortmentCode(param.getAssortmentCode());
        if(ObjectUtils.isNull(pgcObj)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_PRODUCT_CLASSIFICATION_NO_EXIST);
        }
        pgcObj.setStatus(1);
        pgcObj.setModifyTime(new Date());
        pgcObj.setModifier(param.getModifier());
        platformGoodsClassificationMapper.updateByPrimaryKeySelective(pgcObj);
        return JsonResult.successJsonResult();
    }
    
    /**
     * <p>获取详情信息</p>
     * @param assortmentCode
     * @return
     * @author 彭斌  2018年10月31日 下午5:17:08
     */
    public JsonResult<Classification> getClassificationInfo(@RequestParam("assortmentCode") String assortmentCode){
        if(null == assortmentCode) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "分类编码不存在");
        }
        Classification classification = platformGoodsClassificationMapper.selectDetailByAssortmentCode(assortmentCode);
        return JsonResult.successJsonResult(classification);
    }
    
    /**
     * <p>获取分类列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月1日 上午11:12:52
     */
    public JsonResult<List<ClassificationList>> getClassificationList(@RequestBody SearchClassificationListParam param){
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<ClassificationList> list = new ArrayList<>();
        List<ClassificationList> classificationSetList = platformGoodsClassificationMapper.selectByParam(param.getParam());
        
        if(null != classificationSetList && !classificationSetList.isEmpty()) {
            for (ClassificationList classificationSet : classificationSetList) {
                // 有效并且为父级分类
                if (classificationSet.getAssortmentTop()==0)  {
                    list.add(classificationSet);
                }
            }
        }
        
        // 递归填充二级三级分类
        for (ClassificationList classificationList : list) {
            // 根据分类编码查询下级分类信息
            classificationList.setChildList(selectClassificationSubList(classificationList.getAssortmentCode()));
        }
        
        return JsonResult.successJsonResult(list);
    }
    
    @SuppressWarnings("unused")
    private List<ClassificationList> selectClassificationSubList(String childCode){
        List<ClassificationList> result = new ArrayList<>();
        List<ClassificationList> childList = platformGoodsClassificationMapper.selectParentByParam(childCode);
        for (ClassificationList classificationList : childList) {
            classificationList.setChildList(selectClassificationSubList(classificationList.getAssortmentCode()));
            result.add(classificationList);
        }
        return result;
    }
    
}
