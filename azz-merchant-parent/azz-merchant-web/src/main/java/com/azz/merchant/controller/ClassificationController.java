/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月7日 下午3:55:41
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.merchant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.platform.merchant.api.ClassificationService;
import com.azz.platform.merchant.pojo.bo.SearchClassificationListParam;
import com.azz.platform.merchant.pojo.vo.ClassificationList;
import com.azz.platform.merchant.pojo.vo.ClassificationParams;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月7日 下午3:55:41
 */
@RestController
@RequestMapping("/azz/api/merchant/classification")
public class ClassificationController {
    
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

