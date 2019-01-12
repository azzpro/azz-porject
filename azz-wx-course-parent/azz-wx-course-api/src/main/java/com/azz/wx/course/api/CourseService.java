/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.wx.course.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

/**
 * 
 * <P>
 * 课程相关控制器
 * </P>
 * 
 * @version 1.0
 * @author 黄智聪 2018年10月17日 下午1:42:55
 */
@FeignClient("azz-wx-course-service")
public interface CourseService {


	@RequestMapping(value="/azz/api/platform/course/getPrams",method=RequestMethod.POST)
	public JsonResult<ProductParams> getPrams(@RequestParam("code") String code);
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
	@RequestMapping(value = "/azz/api/platform/course/getCourseInfos", method = RequestMethod.POST)
	public JsonResult<Pagination<CourseInfo>> getCourseInfos(@RequestBody SearchCourseInfoParam param);

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
	@RequestMapping(value = "/azz/api/platform/course/getCourseDetail", method = RequestMethod.POST)
	public JsonResult<CourseDetail> getCourseDetail(@RequestParam("courseCode") String courseCode);

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
	@RequestMapping(value = "/azz/api/platform/course/addCourse", method = RequestMethod.POST)
	public JsonResult<String> addCourse(@RequestBody AddCourseParam param);

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
	@RequestMapping(value = "/azz/api/platform/course/editCourse", method = RequestMethod.POST)
	public JsonResult<String> editCourse(@RequestBody EditCourseParam param);

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
	@RequestMapping(value = "/azz/api/platform/course/putOnOrPutOffOrDelCourse", method = RequestMethod.POST)
	public JsonResult<String> putOnOrPutOffOrDelCourse(@RequestBody PutOnOrPutOffOrDelCourseParam param);

	
	@RequestMapping(value = "/azz/api/platform/course/addClassification", method = RequestMethod.POST)
	public JsonResult<String> addClassification(@RequestBody AddClassificationParam param);
	
	@RequestMapping(value = "/azz/api/platform/course/editClassification", method = RequestMethod.POST)
	public JsonResult<String> editClassification(@RequestBody EditClassificationParam param);
	
	@RequestMapping(value = "/azz/api/platform/course/delClassification", method = RequestMethod.POST)
	public JsonResult<String> delClassification(@RequestBody DelClassificationParam param);
	
	@RequestMapping(value = "/azz/api/platform/course/getClassificationInfo", method = RequestMethod.POST)
	public JsonResult<WxCourseClassification> getClassificationInfo(@RequestParam("assortmentCode") String assortmentCode);
	
	@RequestMapping(value = "/azz/api/platform/course/getClassificationParent", method = RequestMethod.POST)
	public JsonResult<List<ClassificationParams>> getClassificationParent(@RequestBody SearchClassificationListParam param);
	
	@RequestMapping(value = "/azz/api/platform/course/getClassificationChild", method = RequestMethod.POST)
	public JsonResult<List<ClassificationParams>> getClassificationChild(@RequestParam("parentCode") String parentCode);
	
	@RequestMapping(value = "/azz/api/platform/course/getClassificationList", method = RequestMethod.POST)
	public JsonResult<List<CourseClassification>> getClassificationList(
            @RequestBody SearchClassificationListParam param);
	
	@RequestMapping(value = "/azz/api/platform/course/addBrand", method = RequestMethod.POST)
	public JsonResult<String> addBrand(@RequestBody AddBrandParam param);
	
	@RequestMapping(value = "/azz/api/platform/course/getBrandInfoList", method = RequestMethod.POST)
	public JsonResult<Pagination<BrandInfo>> getBrandInfoList(@RequestBody SearchBrandParam param);
	
	@RequestMapping(value = "/azz/api/platform/course/getBrandInfo", method = RequestMethod.POST)
	public JsonResult<BrandInfo> getBrandInfo(@RequestParam("brandCode") String brandCode);
	
	@RequestMapping(value = "/azz/api/platform/course/editBrand", method = RequestMethod.POST)
	public JsonResult<String> editBrand(@RequestBody EditBrandParam param);
	
	@RequestMapping(value = "/azz/api/platform/course/delBrand", method = RequestMethod.POST)
	public JsonResult<String> delBrand(@RequestBody DelBrandParam param);
	
	@RequestMapping(value = "/azz/api/platform/course/getAllBrand", method = RequestMethod.POST)
	public JsonResult<List<BrandInfo>> getAllBrand();
}
