package com.azz.wx.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.azz.system.api.SystemImageUploadService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.ObjectUtils;
import com.azz.util.StringUtils;
import com.azz.util.SystemSeqUtils;
import com.azz.wx.course.mapper.WxCourseClassificationMapper;
import com.azz.wx.course.pojo.WxCourseClassification;
import com.azz.wx.course.pojo.bo.AddClassificationParam;
import com.azz.wx.course.pojo.bo.ClassificationPic;
import com.azz.wx.course.pojo.bo.DelClassificationParam;
import com.azz.wx.course.pojo.bo.EditClassificationParam;
import com.azz.wx.course.pojo.bo.SearchClassificationListParam;
import com.azz.wx.course.pojo.bo.SearchSameLevelClassification;
import com.azz.wx.course.pojo.vo.ClassificationParams;
import com.azz.wx.course.pojo.vo.CourseClassification;

/**
 * <P>分类管理</P>
 * @version 1.0
 * @author 彭斌  2019年1月4日 上午10:54:50
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ClassificationService {


    @Autowired
    private SystemImageUploadService systemImageUploadService;

    
    @Autowired
    private WxCourseClassificationMapper wxCourseClassificationMapper;
    /**
     * <p>
     * 新增分类
     * </p>
     * 
     * @param param
     * @return
     * @author 彭斌 2018年10月31日 下午4:16:31
     */
    public JsonResult<String> addClassification(@RequestBody AddClassificationParam param) {
        JSR303ValidateUtils.validate(param);

        // 主图基础校验
        ClassificationPic cp = param.getClassificationPic();
        String originalFileName = cp.getFileName();
        // TODO 分类编码
        String classificationCode = String.valueOf(System.currentTimeMillis());
        
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
        String newFileName = fileNameNoSufix +"_"+ SystemSeqUtils.getSeq(classificationCode);

        // 图片url
        JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix, filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_CLASSIFICATION_IMAGE_TYPE);
        if (jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
            throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
        }

        WxCourseClassification record = new WxCourseClassification();
        if (null != param.getAssortmentParentCode() && !"0".equals(param.getAssortmentParentCode())) {
            WxCourseClassification pgc = wxCourseClassificationMapper
                    .selectByClassificationCode(param.getAssortmentParentCode());
            if (ObjectUtils.isNull(pgc)) {
                throw new BaseException(
                        PlatformUserErrorCode.PLATFORM_PRODUCT_CLASSIFICATION_NO_EXIST);
            }

            if (pgc.getClassificationTop() == 0) {
                record.setClassificationTop((byte) 1);
            } else if (pgc.getClassificationTop() == 1) {
                record.setClassificationTop((byte) 2);
            }
        } else {
            record.setClassificationTop((byte) 0);
        }
        // 校验分类等级不允许超过三级分类
        List<CourseClassification> clList = wxCourseClassificationMapper.selectByParam(param.getAssortmentParentCode());
        if(clList.size()>0) {
            if(clList.get(0).getClassificationTop() == 2) {
                throw new BaseException(PlatformUserErrorCode.PLATFORM_PRODUCT_THIRD_LEVEL_CLASSIFICATION);
            }
        }
        // 校验分类名称
        SearchSameLevelClassification searchSameLevelClassification = new SearchSameLevelClassification();
        searchSameLevelClassification.setAssortmentName(param.getAssortmentName());
        searchSameLevelClassification.setAssortmentTop(record.getClassificationTop());
        searchSameLevelClassification.setAssortmentParentCode(param.getAssortmentParentCode());
        int count = wxCourseClassificationMapper.selectSameLevelClassification(searchSameLevelClassification);
        
        if (count > 0) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_SAME_CLASSIFICATION_ID);
        }
       
        record.setClassificationCode(classificationCode);
        record.setClassificationParentCode(
                null == param.getAssortmentParentCode() ? "0" : param.getAssortmentParentCode());
        record.setClassificationName(param.getAssortmentName());
        record.setSort(param.getAssortmentSort());
        record.setClassificationPicName(originalFileName);
        record.setClassificationPicUrl(jr.getData());
        record.setCreateTime(new Date());
        record.setCreator(param.getCreator());
        wxCourseClassificationMapper.insertSelective(record);

        return JsonResult.successJsonResult();
    }


    /**
     * <p>
     * 修改分类
     * </p>
     * 
     * @param param
     * @return
     * @author 彭斌 2018年10月31日 下午4:36:55
     */
    public JsonResult<String> editClassification(@RequestBody EditClassificationParam param) {
        JSR303ValidateUtils.validate(param);
        
        WxCourseClassification wcc = wxCourseClassificationMapper
                .selectByClassificationCode(param.getAssortmentCode());
        
        if(param.getIsEditPic() == 1) {
            // 主图基础校验
            ClassificationPic cp = param.getClassificationPic();
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
            String newFileName = fileNameNoSufix + "_" + param.getAssortmentCode();
            
            // 图片url
            JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix, filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_CLASSIFICATION_IMAGE_TYPE);
            
            if (jr.getCode() != SystemErrorCode.SUCCESS.getCode()) {
                throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE, "主图上传失败，请重试");
            }
            
            wcc.setClassificationPicName(originalFileName);
            wcc.setClassificationPicUrl(jr.getData());
        }

       

        if (null != param.getAssortmentParentCode() && !"0".equals(param.getAssortmentParentCode())) {
            WxCourseClassification wccParent = wxCourseClassificationMapper
                    .selectByClassificationCode(param.getAssortmentParentCode());
            if (ObjectUtils.isNull(wccParent)) {
                throw new BaseException(
                        PlatformUserErrorCode.PLATFORM_PRODUCT_CLASSIFICATION_NO_EXIST);
            }
            if(wccParent.getClassificationTop() == 0) {
                wcc.setClassificationTop((byte) 1);
            }else if (wccParent.getClassificationTop() == 1) {
                wcc.setClassificationTop((byte) 2);
            }
        } else {
            wcc.setClassificationTop((byte) 0);
        }

        // 校验分类等级不允许超过三级分类
        List<CourseClassification> clList = wxCourseClassificationMapper.selectByParam(param.getAssortmentParentCode());
        if(clList.size()>0) {
            if(clList.get(0).getClassificationTop() == 2) {
                throw new BaseException(PlatformUserErrorCode.PLATFORM_PRODUCT_THIRD_LEVEL_CLASSIFICATION);
            }
        }
        
        // 校验分类名称
        if (wcc.getClassificationName().equals(param.getAssortmentName().trim())) {
            wcc.setClassificationName(param.getAssortmentName());
        } else {
            SearchSameLevelClassification searchSameLevelClassification = new SearchSameLevelClassification();
            searchSameLevelClassification.setAssortmentName(param.getAssortmentName());
            searchSameLevelClassification.setAssortmentTop(wcc.getClassificationTop());
            searchSameLevelClassification.setAssortmentParentCode(param.getAssortmentParentCode());
            int count = wxCourseClassificationMapper.selectSameLevelClassification(searchSameLevelClassification);
            
            if (count > 0) {
                throw new BaseException(PlatformUserErrorCode.PLATFORM_SAME_CLASSIFICATION_ID);
            }
            wcc.setClassificationName(param.getAssortmentName());
        }
        // 分类编码
        wcc.setClassificationParentCode(
                null == param.getAssortmentParentCode() ? "0" : param.getAssortmentParentCode());
        wcc.setSort(param.getAssortmentSort());
        wcc.setModifyTime(new Date());
        wcc.setModifier(param.getModifier());
        wxCourseClassificationMapper.updateByPrimaryKeySelective(wcc);
        return JsonResult.successJsonResult();
    }

    /**
     * <p>
     * 删除分类
     * </p>
     * 
     * @param assortmentCode
     * @return
     * @author 彭斌 2018年10月31日 下午4:52:11
     */
    public JsonResult<String> delClassification(@RequestBody DelClassificationParam param) {
        JSR303ValidateUtils.validate(param);
        WxCourseClassification wccObj = wxCourseClassificationMapper
                .selectByClassificationCode(param.getAssortmentCode());
        if (ObjectUtils.isNull(wccObj)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_PRODUCT_CLASSIFICATION_NO_EXIST);
        }
        // 校验 分类删除前判断分类下是否存在参数项被关联使用，存在报错“分类下存在参数项，请先处理后删除！
        int countParams = wxCourseClassificationMapper.countClassification(param.getAssortmentCode());
        if (countParams > 0) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "分类下存在参数项，请先处理后删除");
        }
        //状态(0:无效 1：有效 2:禁用)
        List<WxCourseClassification> pgc = wxCourseClassificationMapper.selectByAssortmentParentCode(param.getAssortmentCode());
        if(pgc.size() > 0) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_PRODUCT_CHILD_CLASSIFICATION_EXIST);
        }
        
        wccObj.setModifyTime(new Date());
        wccObj.setModifier(param.getModifier());
        wccObj.setStatus((byte) 0);
        wxCourseClassificationMapper.updateByPrimaryKeySelective(wccObj);
        return JsonResult.successJsonResult();
    }

    /**
     * <p>
     * 获取详情信息
     * </p>
     * 
     * @param assortmentCode
     * @return
     * @author 彭斌 2018年10月31日 下午5:17:08
     */
    public JsonResult<WxCourseClassification> getClassificationInfo(String assortmentCode) {
        if (null == assortmentCode) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "分类编码不存在");
        }
        WxCourseClassification wcc = wxCourseClassificationMapper
                .selectByClassificationCode(assortmentCode);
        return JsonResult.successJsonResult(wcc);
    }

    /**
     * <p>
     * 获取分类列表
     * </p>
     * 
     * @param param
     * @return
     * @author 彭斌 2018年11月1日 上午11:12:52
     */
    public JsonResult<List<CourseClassification>> getClassificationList(
            @RequestBody SearchClassificationListParam param) {
        List<CourseClassification> list = new ArrayList<>();
        List<CourseClassification> classificationSetList =
                wxCourseClassificationMapper.selectByParam(param.getParam());

        if (null != classificationSetList && !classificationSetList.isEmpty()) {
            for (CourseClassification classificationSet : classificationSetList) {
                // 有效并且为父级分类
                if (classificationSet.getClassificationTop() == 0) {
                    list.add(classificationSet);
                }
            }
        }

        // 递归填充二级三级分类
        for (CourseClassification classificationList : list) {
            // 根据分类编码查询下级分类信息
            classificationList.setChildList(
                    selectClassificationSubList(classificationList.getClassificationCode()));
        }

        return JsonResult.successJsonResult(list);
    }

    /**
     * <p>
     * 根据分类编码查询下级分类
     * </p>
     * 
     * @param childCode
     * @return
     * @author 彭斌 2018年11月1日 下午9:23:43
     */
    @SuppressWarnings("unused")
    private List<CourseClassification> selectClassificationSubList(String childCode) {
        List<CourseClassification> result = new ArrayList<>();
        List<CourseClassification> childList =
                wxCourseClassificationMapper.selectParentByParam(childCode);
        for (CourseClassification classificationList : childList) {
            classificationList.setChildList(
                    selectClassificationSubList(classificationList.getClassificationCode()));
            result.add(classificationList);
        }
        return result;
    }

    
    /**
     * <p>获取一级分类</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月6日 上午10:35:48
     */
    public JsonResult<List<ClassificationParams>> getClassificationParent(@RequestBody SearchClassificationListParam param){
        List<ClassificationParams> list = wxCourseClassificationMapper.selectParentByAssortmentName(param);
        return JsonResult.successJsonResult(list);
    }
    
    /**
     * <p>根据分类编码获取子级的分类</p>
     * @param parentCode
     * @return
     * @author 彭斌  2018年11月6日 上午10:49:44
     */
    public JsonResult<List<ClassificationParams>> getClassificationChild(String parentCode){
        if(null == parentCode || "".equals(parentCode)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM,
                    "分类编码不存在");
        }
        List<ClassificationParams> list = wxCourseClassificationMapper.selectChild(parentCode);
        return JsonResult.successJsonResult(list);
    }
    
}

