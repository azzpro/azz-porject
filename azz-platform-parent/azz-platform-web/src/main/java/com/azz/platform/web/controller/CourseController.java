/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.core.constants.MerchantConstants.IsChangeGoodsBrandPic;
import com.azz.core.constants.WxCourseConstants.IsChangeCoursePic;
import com.azz.platform.merchant.pojo.bo.EditClassificationWebParam;
import com.azz.util.Base64;
import com.azz.util.JSR303ValidateUtils;
import com.azz.utils.WebUtils;
import com.azz.wx.course.api.CourseService;
import com.azz.wx.course.pojo.WxCourseClassification;
import com.azz.wx.course.pojo.bo.AddBrandParam;
import com.azz.wx.course.pojo.bo.AddBrandWebParam;
import com.azz.wx.course.pojo.bo.AddClassificationParam;
import com.azz.wx.course.pojo.bo.AddClassificationWebParam;
import com.azz.wx.course.pojo.bo.AddCourseParam;
import com.azz.wx.course.pojo.bo.AddCourseWebParam;
import com.azz.wx.course.pojo.bo.ClassificationPic;
import com.azz.wx.course.pojo.bo.CoursePic;
import com.azz.wx.course.pojo.bo.DelBrandParam;
import com.azz.wx.course.pojo.bo.DelClassificationParam;
import com.azz.wx.course.pojo.bo.EditBrandParam;
import com.azz.wx.course.pojo.bo.EditBrandWebParam;
import com.azz.wx.course.pojo.bo.EditClassificationParam;
import com.azz.wx.course.pojo.bo.EditCourseParam;
import com.azz.wx.course.pojo.bo.EditCourseWebParam;
import com.azz.wx.course.pojo.bo.GoodsBrandPic;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelCourseParam;
import com.azz.wx.course.pojo.bo.SearchBrandParam;
import com.azz.wx.course.pojo.bo.SearchClassificationListParam;
import com.azz.wx.course.pojo.bo.SearchCourseInfoParam;
import com.azz.wx.course.pojo.vo.BrandInfo;
import com.azz.wx.course.pojo.vo.ClassificationParams;
import com.azz.wx.course.pojo.vo.CourseClassification;
import com.azz.wx.course.pojo.vo.CourseDetail;
import com.azz.wx.course.pojo.vo.CourseInfo;
/**
 * 
 * <P>
 * 课程相关控制器
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月17日 下午1:42:55
 */
@RestController
@RequestMapping("/azz/api/platform/course")
public class CourseController {

	@Autowired
	CourseService courseService;

	/**
	 * 
	 * <p>
	 * 查询课程列表
	 * </p>
	 * 
	 * @param param
	 * @return
	 * @author 黄智聪 2019年1月4日 上午11:33:03
	 */
	@RequestMapping(value = "getCourseInfos", method = RequestMethod.POST)
	public JsonResult<Pagination<CourseInfo>> getCourseInfos(SearchCourseInfoParam param) {
		return courseService.getCourseInfos(param);
	}

	/**
	 * 
	 * <p>
	 * 查询课程详情
	 * </p>
	 * 
	 * @param courseCode
	 * @return
	 * @author 黄智聪 2019年1月4日 下午3:20:59
	 */
	@RequestMapping(value = "getCourseDetail", method = RequestMethod.POST)
	public JsonResult<CourseDetail> getCourseDetail(@RequestParam("courseCode") String courseCode) {
		return courseService.getCourseDetail(courseCode);
	}

	/**
	 * 
	 * <p>
	 * 新增课程
	 * </p>
	 * 
	 * @param param
	 * @return
	 * @author 黄智聪 2019年1月4日 上午11:50:51
	 * @throws IOException 
	 */
	@RequestMapping(value = "addCourse", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public JsonResult<String> addCourse(@RequestBody AddCourseWebParam webParam) throws IOException {
		JSR303ValidateUtils.validate(webParam);
		AddCourseParam param = new AddCourseParam();
		BeanUtils.copyProperties(webParam, param);
		MultipartFile coursePicFile = webParam.getCoursePicFile();
		CoursePic coursePic = new CoursePic(coursePicFile.getOriginalFilename(),
				coursePicFile.getSize(), Base64.encode(coursePicFile.getBytes()));
		param.setCoursePic(coursePic);
		param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return courseService.addCourse(param);
	}

	/**
	 * 
	 * <p>
	 * 修改课程
	 * </p>
	 * 
	 * @param param
	 * @return
	 * @author 黄智聪 2019年1月4日 上午11:50:51
	 * @throws IOException 
	 */
	@RequestMapping(value = "editCourse", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public JsonResult<String> editCourse(@RequestBody EditCourseWebParam webParam) throws IOException {
		JSR303ValidateUtils.validate(webParam);
		EditCourseParam param = new EditCourseParam();
		BeanUtils.copyProperties(webParam, param);
		MultipartFile coursePicFile = webParam.getCoursePicFile();
		if (webParam.getIsChangeCoursePic() == IsChangeCoursePic.Y.getValue() && coursePicFile != null) {
			CoursePic goodsModulePic = new CoursePic(coursePicFile.getOriginalFilename(),
					coursePicFile.getSize(), Base64.encode(coursePicFile.getBytes()));
			param.setCoursePic(goodsModulePic);
		}
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return courseService.editCourse(param);
	}

	/**
	 * 
	 * <p>
	 * 上架、下架或删除课程
	 * </p>
	 * 
	 * @param param
	 * @return
	 * @author 黄智聪 2019年1月4日 下午2:51:18
	 */
	@RequestMapping(value = "putOnOrPutOffOrDelCourse", method = RequestMethod.POST)
	public JsonResult<String> putOnOrPutOffOrDelCourse(PutOnOrPutOffOrDelCourseParam param) {
		param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return courseService.putOnOrPutOffOrDelCourse(param);
	}
	
	/**
	 * <p>新增分类</p>
	 * @param param
	 * @return
	 * @throws IOException
	 * @author 彭斌  2019年1月11日 下午2:23:57
	 */
	@RequestMapping(value = "addClassification", method = RequestMethod.POST)
	public JsonResult<String> addClassification(AddClassificationWebParam param) throws IOException{
	    MultipartFile classificationFile = param.getClassificationFile();
        ClassificationPic cp = new ClassificationPic(classificationFile.getOriginalFilename(), classificationFile.getSize(), Base64.encode(classificationFile.getBytes()));
        param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
        return courseService.addClassification(new AddClassificationParam(param.getAssortmentParentCode(),param.getAssortmentName(),param.getAssortmentSort(),param.getCreator(),cp));
	}
	
	/**
	 * <p>编辑分类</p>
	 * @param param
	 * @return
	 * @throws IOException
	 * @author 彭斌  2019年1月11日 下午2:57:57
	 */
	@RequestMapping(value = "editClassification", method = RequestMethod.POST)
	public JsonResult<String> editClassification(EditClassificationWebParam param) throws IOException{
	    ClassificationPic cp = null;
        if(param.getIsEditPic() == 1) {
            MultipartFile classificationFile = param.getClassificationFile();
            cp = new ClassificationPic(classificationFile.getOriginalFilename(), classificationFile.getSize(), Base64.encode(classificationFile.getBytes()));
        }
        byte sort = 0;
        if(null != param.getAssortmentSort()) {
            Integer assortmentSort = param.getAssortmentSort();
            sort = (byte)assortmentSort.intValue();
        }
        param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
	    return courseService.editClassification(new EditClassificationParam(param.getAssortmentCode(),param.getAssortmentParentCode(),param.getAssortmentName(),sort,param.getModifier(),cp,param.getIsEditPic()));
	}
	
	/**
	 * <p>删除分类</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 下午3:04:56
	 */
	@RequestMapping(value = "delClassification", method = RequestMethod.POST)
	public JsonResult<String> delClassification(DelClassificationParam param){
	    param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
	    return courseService.delClassification(param);
	}
	
	/**
	 * <p>获取分类详细</p>
	 * @param assortmentCode
	 * @return
	 * @author 彭斌  2019年1月11日 下午3:09:15
	 */
	@RequestMapping(value = "getClassificationInfo", method = RequestMethod.POST)
	public JsonResult<WxCourseClassification> getClassificationInfo(@RequestParam("assortmentCode") String assortmentCode){
	    return courseService.getClassificationInfo(assortmentCode);
	}
	
	/**
	 * <p>获取分类父级</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 下午3:16:00
	 */
	@RequestMapping(value = "getClassificationParent", method = RequestMethod.POST)
	public JsonResult<List<ClassificationParams>> getClassificationParent(SearchClassificationListParam param){
	    return courseService.getClassificationParent(param);
	}
	
	/**
	 * <p>获取分类子级</p>
	 * @param parentCode
	 * @return
	 * @author 彭斌  2019年1月11日 下午3:18:55
	 */
	@RequestMapping(value = "getClassificationChild", method = RequestMethod.POST)
	public JsonResult<List<ClassificationParams>> getClassificationChild(@RequestParam("parentCode") String parentCode){
	    return courseService.getClassificationChild(parentCode);
	}
	
	/**
	 * <p>分类列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 下午3:22:05
	 */
	@RequestMapping(value = "getClassificationList", method = RequestMethod.POST)
	public JsonResult<List<CourseClassification>> getClassificationList(SearchClassificationListParam param){
	    return courseService.getClassificationList(param);
	}
	
	/**
	 * <p>新增品牌</p>
	 * @param webParam
	 * @return
	 * @throws IOException
	 * @author 彭斌  2019年1月11日 下午3:26:16
	 */
	@RequestMapping(value = "addBrand", method = RequestMethod.POST)
	public JsonResult<String> addGoodsBrand(AddBrandWebParam webParam) throws IOException{
	    AddBrandParam param = new AddBrandParam();
        BeanUtils.copyProperties(webParam, param);
        MultipartFile goodsBrandPicFile = webParam.getBrandPicFile();
        GoodsBrandPic goodsBrandPic = new GoodsBrandPic(goodsBrandPicFile.getOriginalFilename(),
                    goodsBrandPicFile.getSize(), Base64.encode(goodsBrandPicFile.getBytes()));
        param.setGoodsBrandPic(goodsBrandPic);
        param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
	    return courseService.addBrand(param);
	}
	
	/**
	 * <p>修改品牌</p>
	 * @param webParam
	 * @return
	 * @throws IOException
	 * @author 彭斌  2019年1月11日 下午3:50:02
	 */
	@RequestMapping(value = "editBrand", method = RequestMethod.POST)
	public JsonResult<String> editBrand(EditBrandWebParam webParam) throws IOException{
	    EditBrandParam param = new EditBrandParam();
        BeanUtils.copyProperties(webParam, param);
        MultipartFile goodsBrandPicFile = webParam.getBrandPicFile();
        if (webParam.getIsChangeGoodsBrandPic() == IsChangeGoodsBrandPic.Y.getValue() && goodsBrandPicFile != null) {
            GoodsBrandPic goodsBrandPic = new GoodsBrandPic(goodsBrandPicFile.getOriginalFilename(),
                    goodsBrandPicFile.getSize(), Base64.encode(goodsBrandPicFile.getBytes()));
            param.setGoodsBrandPic(goodsBrandPic);
        }
        param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
	    return courseService.editBrand(param);
	}
	
	/**
	 * <p>删除品牌</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 下午3:52:10
	 */
	@RequestMapping(value = "delBrand", method = RequestMethod.POST)
	public JsonResult<String> delBrand(DelBrandParam param){
	    param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
	    return courseService.delBrand(param);
	}
	
	/**
	 * <p>品牌列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 下午4:05:27
	 */
	@RequestMapping(value = "getBrandInfoList", method = RequestMethod.POST)
	public JsonResult<Pagination<BrandInfo>> getBrandInfoList(SearchBrandParam param){
	    return courseService.getBrandInfoList(param);
	}
    
	/**
	 * <p>获取品牌详情</p>
	 * @param brandCode
	 * @return
	 * @author 彭斌  2019年1月11日 下午4:05:31
	 */
	@RequestMapping(value = "getBrandInfo", method = RequestMethod.POST)
    public JsonResult<BrandInfo> getBrandInfo(@RequestParam("brandCode") String brandCode){
        return courseService.getBrandInfo(brandCode);
    }
	
	/**
	 * <p>获取所有品牌下拉列</p>
	 * @return
	 * @author 彭斌  2019年1月12日 下午3:50:28
	 */
	@RequestMapping(value = "getAllBrand", method = RequestMethod.POST)
	public JsonResult<List<BrandInfo>> getAllBrand(){
	    return courseService.getAllBrand();
	}
}
