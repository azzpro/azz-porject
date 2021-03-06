/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月7日 下午3:49:56
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.platform.merchant.api.ClassificationService;
import com.azz.platform.merchant.pojo.bo.SearchClassificationListParam;
import com.azz.platform.merchant.pojo.vo.ClassificationList;
import com.azz.platform.merchant.pojo.vo.ClassificationParams;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月7日 下午3:49:56
 */
@RestController
@RequestMapping("/azz/api/merchant/classification")
public class ClassificationController {
    @Autowired
    private ClassificationService  classificationService;
    
    /**
     * <p>所有分类集合</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月7日 下午3:54:11
     */
    @RequestMapping(value="getClassificationList",method=RequestMethod.POST)
    public JsonResult<List<ClassificationList>> getClassificationList(@RequestBody SearchClassificationListParam param){
        return classificationService.getClassificationList(param);
    }
    
    /**
     * <p>父级分类列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月7日 下午3:54:15
     */
    @RequestMapping(value="getClassificationParent",method=RequestMethod.POST)
    public JsonResult<List<ClassificationParams>> getClassificationParent(@RequestBody SearchClassificationListParam param){
        return classificationService.getClassificationParent(param);
    }
    
    /**
     * <p>子级分类列表</p>
     * @param parentCode
     * @return
     * @author 彭斌  2018年11月7日 下午3:54:18
     */
    @RequestMapping(value="getClassificationChild",method=RequestMethod.POST)
    public JsonResult<List<ClassificationParams>> getClassificationChild(String parentCode){
        return classificationService.getClassificationChild(parentCode);
    }
}

