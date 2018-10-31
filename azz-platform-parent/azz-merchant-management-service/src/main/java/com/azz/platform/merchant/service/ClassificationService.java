package com.azz.platform.merchant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.platform.merchant.mapper.PlatformGoodsClassificationMapper;
import com.azz.platform.merchant.pojo.bo.AddClassificationParam;
import com.azz.system.sequence.api.RandomSequenceService;
import com.azz.util.JSR303ValidateUtils;

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

    public JsonResult<String> addClassification(@RequestBody AddClassificationParam param) {
        JSR303ValidateUtils.validate(param);
        
        if(null != param.getAssortmentParentCode()) {
            
        }
        
        // 分类编码
        String classificationCode = randomSequenceService.getClassificationNumber();
        
        return JsonResult.successJsonResult();
    }


}

