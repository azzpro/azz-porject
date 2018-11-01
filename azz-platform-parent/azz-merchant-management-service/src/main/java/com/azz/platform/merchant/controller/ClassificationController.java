/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午6:19:44
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.pojo.bo.AddClassificationParam;
import com.azz.platform.merchant.pojo.bo.DelClassificationParam;
import com.azz.platform.merchant.pojo.bo.EditClassificationParam;
import com.azz.platform.merchant.pojo.bo.SearchClassificationListParam;
import com.azz.platform.merchant.pojo.vo.Classification;
import com.azz.platform.merchant.pojo.vo.ClassificationList;
import com.azz.platform.merchant.service.ClassificationService;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月24日 下午6:19:44
 */
@RestController
@RequestMapping("/azz/api/merchant/product")
public class ClassificationController {
	
	@Autowired
	private ClassificationService  classificationService;
	
	
	@RequestMapping(value="getClassificationList",method=RequestMethod.POST)
	public JsonResult<Pagination<ClassificationList>> getClassificationList(@RequestBody SearchClassificationListParam param){
	    return classificationService.getClassificationList(param);
	}
	
	@RequestMapping(value="addClassification",method=RequestMethod.POST)
	public JsonResult<String> addClassification(@RequestBody AddClassificationParam param){
	    return classificationService.addClassification(param);
	}
	
	@RequestMapping(value="editClassification",method=RequestMethod.POST)
	public JsonResult<String> editClassification(@RequestBody EditClassificationParam param){
	    return classificationService.editClassification(param);
	}
	
	@RequestMapping(value="delClassification",method=RequestMethod.POST)
	public JsonResult<String> delClassification(@RequestBody DelClassificationParam param){
	    return classificationService.delClassification(param);
	}
	
	@RequestMapping(value="getClassificationInfo",method=RequestMethod.POST)
	public JsonResult<Classification> getClassificationInfo(String assortmentCode){
	    return classificationService.getClassificationInfo(assortmentCode);
	}
	   
    
}

