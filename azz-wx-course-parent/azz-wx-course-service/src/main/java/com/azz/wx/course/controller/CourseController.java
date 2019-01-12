/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.wx.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.wx.course.pojo.WxCourseClassification;
import com.azz.wx.course.pojo.bo.AddBrandParam;
import com.azz.wx.course.pojo.bo.AddClassificationParam;
import com.azz.wx.course.pojo.bo.AddCourseParam;
import com.azz.wx.course.pojo.bo.DelBrandParam;
import com.azz.wx.course.pojo.bo.DelClassificationParam;
import com.azz.wx.course.pojo.bo.EditBrandParam;
import com.azz.wx.course.pojo.bo.EditClassificationParam;
import com.azz.wx.course.pojo.bo.EditCourseParam;
import com.azz.wx.course.pojo.bo.PutOnOrPutOffOrDelCourseParam;
import com.azz.wx.course.pojo.bo.SearchBrandParam;
import com.azz.wx.course.pojo.bo.SearchClassificationListParam;
import com.azz.wx.course.pojo.bo.SearchCourseInfoParam;
import com.azz.wx.course.pojo.vo.BrandInfo;
import com.azz.wx.course.pojo.vo.ClassificationParams;
import com.azz.wx.course.pojo.vo.CourseClassification;
import com.azz.wx.course.pojo.vo.CourseDetail;
import com.azz.wx.course.pojo.vo.CourseInfo;
import com.azz.wx.course.pojo.vo.ProductParams;
import com.azz.wx.course.service.BrandService;
import com.azz.wx.course.service.ClassificationService;
import com.azz.wx.course.service.CourseService;

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

	@Autowired
	ClassificationService classificationService;
	
	@Autowired
	BrandService brandService;
	
	/**
	 * <p>去新增课程页面</p>
	 * @param params
	 * @return
	 * @author 刘建麟  2018年10月31日 下午7:47:30
	 */
	@RequestMapping(value="getPrams",method=RequestMethod.POST)
	public JsonResult<ProductParams> getPrams(String code){
		JsonResult<ProductParams> addProduct = courseService.getPrams(code);
		return addProduct;
	}
	
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
	public JsonResult<Pagination<CourseInfo>> getCourseInfos(@RequestBody SearchCourseInfoParam param) {
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
	 */
	@RequestMapping(value = "addCourse", method = RequestMethod.POST)
	public JsonResult<String> addCourse(@RequestBody AddCourseParam param) {
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
	 */
	@RequestMapping(value = "editCourse", method = RequestMethod.POST)
	public JsonResult<String> editCourse(@RequestBody EditCourseParam param) {
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
	public JsonResult<String> putOnOrPutOffOrDelCourse(@RequestBody PutOnOrPutOffOrDelCourseParam param) {
		return courseService.putOnOrPutOffOrDelCourse(param);
	}

	/**
	 * <p>新增分类</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 上午11:50:06
	 */
	@RequestMapping(value = "addClassification", method = RequestMethod.POST)
	public JsonResult<String> addClassification(@RequestBody AddClassificationParam param){
	    return classificationService.addClassification(param);
	}
	
	/**
	 * 
	 * <p>修改分类</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 上午11:50:15
	 */
	@RequestMapping(value="editClassification",method=RequestMethod.POST)
    public JsonResult<String> editClassification(@RequestBody EditClassificationParam param){
        return classificationService.editClassification(param);
    }
	
	/**
	 * 
	 * <p>删除分类</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 上午11:50:19
	 */
	@RequestMapping(value="delClassification",method=RequestMethod.POST)
    public JsonResult<String> delClassification(@RequestBody DelClassificationParam param){
        return classificationService.delClassification(param);
    }
	
	/**
	 * 
	 * <p>获取分类详情</p>
	 * @param assortmentCode
	 * @return
	 * @author 彭斌  2019年1月11日 上午11:50:23
	 */
	@RequestMapping(value="getClassificationInfo",method=RequestMethod.POST)
    public JsonResult<WxCourseClassification> getClassificationInfo(String assortmentCode){
        return classificationService.getClassificationInfo(assortmentCode);
    }
	
	/**
	 * 
	 * <p>获取父级分类信息</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 上午11:50:28
	 */
	@RequestMapping(value="getClassificationParent",method=RequestMethod.POST)
    public JsonResult<List<ClassificationParams>> getClassificationParent(@RequestBody SearchClassificationListParam param){
        return classificationService.getClassificationParent(param);
    }
	
	/**
	 * 
	 * <p>获取子级分类</p>
	 * @param parentCode
	 * @return
	 * @author 彭斌  2019年1月11日 上午11:50:33
	 */
	@RequestMapping(value="getClassificationChild",method=RequestMethod.POST)
    public JsonResult<List<ClassificationParams>> getClassificationChild(String parentCode){
        return classificationService.getClassificationChild(parentCode);
    }
	
	/**
	 * <p>获取分类列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 上午11:50:36
	 */
	@RequestMapping(value="getClassificationList",method=RequestMethod.POST)
	public JsonResult<List<CourseClassification>> getClassificationList(
            @RequestBody SearchClassificationListParam param){
	    return classificationService.getClassificationList(param);
	}
	
	/**
	 * <p>新增品牌</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 上午11:59:16
	 */
	@RequestMapping(value="addBrand",method=RequestMethod.POST)
	public JsonResult<String> addBrand(@RequestBody AddBrandParam param){
	    return brandService.addBrand(param);
	}
	
	/**
	 * <p>品牌列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 下午12:00:58
	 */
	@RequestMapping(value="getBrandInfoList",method=RequestMethod.POST)
	public JsonResult<Pagination<BrandInfo>> getBrandInfoList(@RequestBody SearchBrandParam param){
	    return brandService.getBrandInfoList(param);
	}
	
	/**
	 * <p>获取品牌详情</p>
	 * @param brandCode
	 * @return
	 * @author 彭斌  2019年1月11日 下午12:03:07
	 */
	@RequestMapping(value="getBrandInfo", method = RequestMethod.POST)
	public JsonResult<BrandInfo> getBrandInfo(@RequestParam("brandCode") String brandCode){
	    return brandService.getBrandInfo(brandCode);
	}
	
	/**
	 * <p>修改品牌</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 下午12:05:19
	 */
	@RequestMapping(value="editBrand",method=RequestMethod.POST)
	public JsonResult<String> editBrand(@RequestBody EditBrandParam param) {
	    return brandService.editBrand(param);
	}
	
	/**
	 * <p>删除品牌</p>
	 * @param param
	 * @return
	 * @author 彭斌  2019年1月11日 下午12:06:03
	 */
	@RequestMapping(value="delBrand",method=RequestMethod.POST)
	public JsonResult<String> delBrand(@RequestBody DelBrandParam param){
	    return brandService.delBrand(param);
	}
	
	/**
     * <p>获取所有下拉品牌</p>
     * @param param
     * @return
     * @author 彭斌  2019年1月11日 下午12:06:03
     */
	@RequestMapping(value="getAllBrand",method=RequestMethod.POST)
    public JsonResult<List<BrandInfo>> getAllBrand(){
        return brandService.getAllBrand();
    }
	
}
