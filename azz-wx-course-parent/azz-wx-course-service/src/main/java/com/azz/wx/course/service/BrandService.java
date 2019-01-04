/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年1月4日 下午2:55:33
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
import com.azz.core.constants.MerchantConstants;
import com.azz.core.constants.MerchantConstants.IsChangeGoodsBrandPic;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.system.api.SystemImageUploadService;
import com.azz.util.JSR303ValidateUtils;
import com.azz.util.StringUtils;
import com.azz.util.SystemSeqUtils;
import com.azz.wx.course.mapper.WxCourseBrandMapper;
import com.azz.wx.course.pojo.WxCourseBrand;
import com.azz.wx.course.pojo.bo.AddBrandParam;
import com.azz.wx.course.pojo.bo.DelBrandParam;
import com.azz.wx.course.pojo.bo.EditBrandParam;
import com.azz.wx.course.pojo.bo.GoodsBrandPic;
import com.azz.wx.course.pojo.bo.SearchBrandParam;
import com.azz.wx.course.pojo.vo.BrandInfo;
import com.azz.wx.course.pojo.vo.UploadFileInfo;
import com.github.pagehelper.PageHelper;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年1月4日 下午2:55:33
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class BrandService {
    
    @Autowired
    private WxCourseBrandMapper wxCourseBrandMapper;
    
    @Autowired
    private SystemImageUploadService systemImageUploadService;

    
    /**
     * <p>品牌列表</p>
     * @param param
     * @return
     * @author 彭斌  2019年1月4日 下午4:02:25
     */
    public JsonResult<Pagination<BrandInfo>> getBrandInfoList(@RequestBody SearchBrandParam param){
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<BrandInfo> infos = wxCourseBrandMapper.getBrandInfoList(param);
        return JsonResult.successJsonResult(new Pagination<>(infos));
    }
    
    /**
     * <p>查询品牌详情</p>
     * @param brandCode
     * @return
     * @author 彭斌  2019年1月4日 下午4:02:42
     */
    public JsonResult<BrandInfo> getGoodsBrandInfo(String brandCode){
        return JsonResult.successJsonResult(wxCourseBrandMapper.getBrandInfo(brandCode));
    }
    
   /**
    * <p>新增品牌</p>
    * @param param
    * @return
    * @author 彭斌  2019年1月4日 下午4:07:06
    */
    public JsonResult<String> addGoodsBrand(@RequestBody AddBrandParam param) {
        // 参数校验
        JSR303ValidateUtils.validate(param);
        int count = wxCourseBrandMapper.countGoodsBrandByBrandName(param.getBrandName(), null);
        if(count > 0) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌名称已存在");
        }
        GoodsBrandPic pic = param.getGoodsBrandPic();
        String brandCode = String.valueOf(System.currentTimeMillis());
        // 上传模组图片
        UploadFileInfo fileInfo = this.uploadBrandPic(pic, SystemSeqUtils.getSeq(brandCode));
        
        WxCourseBrand goodsBrandRecord = WxCourseBrand.builder()
                .brandCode(SystemSeqUtils.getSeq(brandCode))
                .brandDescription(param.getBrandDescription())
                .brandInfo(param.getBrandInfo())
                .brandName(param.getBrandName())
                .brandPicName(fileInfo.getOriginalFileName())
                .brandPicUrl(fileInfo.getImgUrl())
                .createTime(new Date())
                .creator(param.getCreator())
                .build();
        wxCourseBrandMapper.insertSelective(goodsBrandRecord);
        return JsonResult.successJsonResult();
    }
    
    
    /**
     * 
     * <p>上传模组主图文件</p>
     * @param pic
     * @param moduleCode
     * @return
     * @author 黄智聪  2018年11月1日 下午4:49:22
     */
    public UploadFileInfo uploadBrandPic(GoodsBrandPic pic, String brandCode) {
        String originalFileName = pic.getFileName();
        if(StringUtils.isBlank(originalFileName)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌主图文件名为空");
        }
        if(pic.getFileSize() > MerchantConstants.GOODS_BRAND_FILE_SIZE_LIMIT) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌主图文件大小不能超过2M");
        }
        String filedata = pic.getFileBase64Str();
        if(StringUtils.isBlank(filedata)) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌主图文件内容为空");
        }
        int dotIndex = originalFileName.lastIndexOf(".");
        String fileNameNoSufix = originalFileName.substring(0, dotIndex);
        String sufix = originalFileName.substring(dotIndex + 1, originalFileName.length());
        // 新名称为文件名 + 品牌编码
        String newFileName = fileNameNoSufix + "_" + brandCode;
        // 图片url
        JsonResult<String> jr = systemImageUploadService.uploadImage(FileConstants.IMAGE_BUCKETNAME, newFileName, sufix,
            filedata, FileConstants.AZZ_PLATFORM, FileConstants.AZZ_BRAND_IMAGE_TYPE);
        UploadFileInfo file = new UploadFileInfo();
        if(jr.getCode() == SystemErrorCode.SUCCESS.getCode()) {
            file.setImgUrl(jr.getData());
            file.setOriginalFileName(originalFileName);
        }else {
            throw new BaseException(SystemErrorCode.SYS_ERROR_SERVICE_NOT_USE,"品牌主图上传失败，请重试");
        }
        return file;
    }
    
    /**
     * <p>修改品牌</p>
     * @param param
     * @return
     * @author 彭斌  2019年1月4日 下午4:18:12
     */
    public JsonResult<String> editBrand(@RequestBody EditBrandParam param) {
        // 参数校验
        JSR303ValidateUtils.validate(param);
        String brandCode = param.getBrandCode();
        WxCourseBrand brand = wxCourseBrandMapper.selectByBrandCode(brandCode);
        if(brand == null) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌不存在");
        }
        // 查询除自己以外的品牌是否存在相同的品牌名
        int count = wxCourseBrandMapper.countGoodsBrandByBrandName(param.getBrandName(), param.getBrandCode());
        if(count > 0) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌名称已存在");
        }
        WxCourseBrand goodsBrandRecord = WxCourseBrand.builder()
                .brandCode(brandCode)
                .brandDescription(param.getBrandDescription())
                .brandInfo(param.getBrandInfo())
                .brandName(param.getBrandName())
                .modifier(param.getModifier())
                .modifyTime(new Date())
                .build();
        // 修改模组图片，则重新上传
        int isChangeGoodsModulePic = param.getIsChangeGoodsBrandPic();
        if(isChangeGoodsModulePic == IsChangeGoodsBrandPic.Y.getValue()) {
            GoodsBrandPic pic = param.getGoodsBrandPic();
            // 上传模组图片
            UploadFileInfo fileInfo = this.uploadBrandPic(pic, brandCode);
            goodsBrandRecord.setBrandPicName(fileInfo.getOriginalFileName());
            goodsBrandRecord.setBrandPicUrl(fileInfo.getImgUrl());
        }       
        wxCourseBrandMapper.updateByBrandCode(goodsBrandRecord);
        return JsonResult.successJsonResult();
    }
    
    /**
     * <p>删除品牌</p>
     * @param param
     * @return
     * @author 彭斌  2019年1月4日 下午4:53:06
     */
    public JsonResult<String> delGoodsBrand(@RequestBody DelBrandParam param){
        // 参数校验
        JSR303ValidateUtils.validate(param);
        String brandCode = param.getBrandCode();
        WxCourseBrand brand = wxCourseBrandMapper.selectByBrandCode(brandCode);
        if(brand == null) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌不存在");
        }
        // 校验该品牌是否被其他商品所使用
        int count = wxCourseBrandMapper.countBindingCourse(brand.getBrandCode());
        if(count > 0) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "品牌信息使用中，请先处理后删除");
        }
        WxCourseBrand goodsBrandRecord = WxCourseBrand.builder()
                .brandCode(brandCode)
                .modifier(param.getModifier())
                .modifyTime(new Date())
                .status((byte) 0)
                .build();
        wxCourseBrandMapper.updateByBrandCode(goodsBrandRecord);
        return JsonResult.successJsonResult();
    }
}

