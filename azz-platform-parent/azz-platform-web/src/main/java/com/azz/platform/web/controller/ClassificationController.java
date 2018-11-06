/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.web.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azz.core.common.JsonResult;
import com.azz.platform.merchant.api.ClassificationService;
import com.azz.platform.merchant.pojo.bo.AddClassificationParam;
import com.azz.platform.merchant.pojo.bo.AddClassificationWebParam;
import com.azz.platform.merchant.pojo.bo.ClassificationPic;
import com.azz.platform.merchant.pojo.bo.DelClassificationParam;
import com.azz.platform.merchant.pojo.bo.EditClassificationParam;
import com.azz.platform.merchant.pojo.bo.EditClassificationWebParam;
import com.azz.platform.merchant.pojo.bo.SearchClassificationListParam;
import com.azz.platform.merchant.pojo.vo.Classification;
import com.azz.platform.merchant.pojo.vo.ClassificationList;
import com.azz.platform.merchant.pojo.vo.ClassificationParams;
import com.azz.util.Base64;
import com.azz.utils.WebUtils;

/**
 * <P>分类管理</P>
 * @version 1.0
 * @author 彭斌  2018年10月17日 下午3:04:47
 */
@RestController
@RequestMapping("/azz/api/merchant/product")
public class ClassificationController {

	private static final Logger LOG = LoggerFactory.getLogger(ClassificationController.class);

	@Autowired
	ClassificationService  classificationService;
	
	/**
	 * <p>查询所有分类集合</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月2日 上午10:32:58
	 */
	@RequestMapping("/getClassificationList")
	public JsonResult<List<ClassificationList>> getClassificationList(SearchClassificationListParam param) {
		return classificationService.getClassificationList(param);
	}
	
	/**
	 * <p>新增分类信息</p>
	 * @param param
	 * @return
	 * @throws IOException
	 * @author 彭斌  2018年11月2日 上午10:33:13
	 */
	@RequestMapping("/addClassification")
	public JsonResult<String> addClassification(AddClassificationWebParam param) throws IOException{
	    MultipartFile classificationFile = param.getClassificationFile();
	    ClassificationPic cp = new ClassificationPic(classificationFile.getOriginalFilename(), classificationFile.getSize(), Base64.encode(classificationFile.getBytes()));
	    param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
	    return classificationService.addClassification(new AddClassificationParam(param.getAssortmentParentCode(),param.getAssortmentName(),param.getAssortmentSort(),param.getCreator(),cp));
	}
	
	/**
	 * <p>修改分类信息</p>
	 * @param param
	 * @return
	 * @throws IOException
	 * @author 彭斌  2018年11月2日 上午10:33:33
	 */
	@RequestMapping("/editClassification")
    public JsonResult<String> editClassification(EditClassificationWebParam param) throws IOException{
	    ClassificationPic cp = new ClassificationPic();
	    if(param.getIsEditPic() == 1) {
	        MultipartFile classificationFile = param.getClassificationFile();
	        cp = new ClassificationPic(classificationFile.getOriginalFilename(), classificationFile.getSize(), Base64.encode(classificationFile.getBytes()));
	    }
        param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
	    return classificationService.editClassification(new EditClassificationParam(param.getAssortmentCode(),param.getAssortmentParentCode(),param.getAssortmentName(),param.getAssortmentSort(),param.getModifier(),cp,param.getIsEditPic()));
	}
	
	/**
	 * <p>删除分类</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月2日 上午10:33:50
	 */
	@RequestMapping("/delClassification")
    public JsonResult<String> delClassification(DelClassificationParam param){
	    param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
	    return classificationService.delClassification(param);
	}
	
	/**
	 * <p>获取分类详情</p>
	 * @param assortmentCode
	 * @return
	 * @author 彭斌  2018年11月2日 上午10:34:02
	 */
	@RequestMapping("/getClassificationInfo")
    public JsonResult<Classification> getClassificationInfo(@RequestParam("assortmentCode") String assortmentCode){
	    return classificationService.getClassificationInfo(assortmentCode);
	}
    
	/**
	 * <p>分类父级清单</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月6日 上午11:04:56
	 */
	@RequestMapping("/getClassificationParent")
    public JsonResult<List<ClassificationParams>> getClassificationParent(SearchClassificationListParam param) {
        return classificationService.getClassificationParent(param);
    }
	
	/**
	 * <p>根据分类编码获取子级清单</p>
	 * @param parentCode
	 * @return
	 * @author 彭斌  2018年11月6日 上午11:05:00
	 */
	@RequestMapping("/getClassificationChild")
	public JsonResult<List<ClassificationParams>> getClassificationChild(String parentCode){
        return classificationService.getClassificationChild(parentCode);
    }
}
