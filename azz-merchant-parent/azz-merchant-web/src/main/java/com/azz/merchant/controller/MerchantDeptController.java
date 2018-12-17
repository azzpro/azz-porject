/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.merchant.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.exception.JSR303ValidationException;
import com.azz.merchant.api.DeptService;
import com.azz.merchant.pojo.MerchantDept;
import com.azz.merchant.pojo.bo.AddMerchantDeptParam;
import com.azz.merchant.pojo.bo.DelDeptParam;
import com.azz.merchant.pojo.bo.EditDeptIsEnableParam;
import com.azz.merchant.pojo.bo.EditMerchantDeptParam;
import com.azz.merchant.pojo.bo.ImportMerchantDeptParam;
import com.azz.merchant.pojo.bo.ImportMerchantDeptWebParam;
import com.azz.merchant.pojo.bo.SearchMerchantChildDeptParam;
import com.azz.merchant.pojo.bo.SearchMerchantDeptInfoParam;
import com.azz.merchant.pojo.bo.SearchMerchantDeptListParam;
import com.azz.merchant.pojo.vo.MerchantDeptList;
import com.azz.merchant.utils.WebUtils;
import com.azz.util.Base64;
import com.azz.util.JSR303ValidateUtils;

/**
 * <P>部门维护</P>
 * @version 1.0
 * @author 彭斌  2018年10月29日 下午2:47:28
 */
@RestController
@RequestMapping("/azz/api/merchant/dept")
public class MerchantDeptController {


    @Autowired
    DeptService deptService;

    
    @RequestMapping("/addFirstLevelDept")
    public JsonResult<String> addFirstLevelDept(AddMerchantDeptParam param){
        param.setMerchantId(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantId());
        param.setCreator(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
        return deptService.addFirstLevelDept(param);
    }

    @RequestMapping("/addChildDept")
    public JsonResult<String> addChildDept(AddMerchantDeptParam param){
        param.setMerchantId(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantId());
        param.setCreator(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
        return deptService.addChildDept(param);
    }
    
    @RequestMapping("/getDeptInfo")
    public JsonResult<MerchantDept> getDeptInfo(SearchMerchantDeptInfoParam param){
        param.setMerchantId(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantId());
        return deptService.getDeptInfo(param);
    }
    
    @RequestMapping("/editDept")
    public JsonResult<String> editDept(EditMerchantDeptParam param){
        param.setMerchantId(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantId());
        param.setModifier(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
        return deptService.editDept(param);
    }
    
    @RequestMapping("/searchDeptList")
    public JsonResult<List<MerchantDeptList>> searchDeptList(SearchMerchantDeptListParam param){
        param.setMerchantCode(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantCode());
        return deptService.searchDeptList(param);
    }
    
    @RequestMapping("/searchChildDeptList")
    public JsonResult<List<MerchantDeptList>> searchChildDeptList(SearchMerchantChildDeptParam param){
        param.setMerchantCode(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantCode());
        return deptService.searchChildDeptList(param);
    }
    
    @RequestMapping("/isEnableDept")
    public JsonResult<String> isEnableDept(EditDeptIsEnableParam param){
        param.setMerchantId(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantId());
        param.setModifier(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
        return deptService.isEnableDept(param);
    }
    
    @RequestMapping("/delDept")
    public JsonResult<String> delDept(DelDeptParam param){
        param.setMerchantId(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantId());
        param.setModifier(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
        return deptService.delDept(param);
    }
    
    @RequestMapping("/importMerchantDept")
    public JsonResult<String> importMerchantDept(ImportMerchantDeptWebParam webParam) throws IOException {
        JSR303ValidateUtils.validate(webParam);
        MultipartFile file = webParam.getFile();
        String fileName = file.getOriginalFilename();
        if(!fileName.endsWith(".xls")) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "文件格式有误");
        }
        ImportMerchantDeptParam param = new ImportMerchantDeptParam();
        param.setMerchantId(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantId());
        param.setCreator(WebUtils.getLoginMerchantUser().getMerchantUserInfo().getMerchantUserCode());
        param.setBase64Str(Base64.encode(file.getBytes()));
        return deptService.importMerchantDept(param);
    }
    
}
