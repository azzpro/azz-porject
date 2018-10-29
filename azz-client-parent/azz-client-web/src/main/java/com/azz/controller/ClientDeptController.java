/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azz.client.pojo.bo.AddClientDeptParam;
import com.azz.client.pojo.bo.DelDeptParam;
import com.azz.client.pojo.bo.EditClientDeptParam;
import com.azz.client.pojo.bo.EditDeptIsEnableParam;
import com.azz.client.pojo.bo.SearchClientChildDeptParam;
import com.azz.client.pojo.bo.SearchClientDeptInfoByCodeParam;
import com.azz.client.pojo.bo.SearchClientDeptParam;
import com.azz.client.pojo.vo.ClientDeptInfo;
import com.azz.client.pojo.vo.ClientDeptList;
import com.azz.client.user.api.ClientDeptService;
import com.azz.controller.utils.WebUtils;
import com.azz.core.common.JsonResult;

/**
 * <P>部门控制器</P>
 * @version 1.0
 * @author 彭斌  2018年10月29日 下午2:00:55
 */
@RestController
@RequestMapping("/azz/api/client/dept")
public class ClientDeptController {


    @Autowired
    ClientDeptService clientDeptService;

    
    /**
     * <p>新增父级部门</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月29日 下午2:08:20
     */
    @RequestMapping("/addFirstLevelDept")
    public JsonResult<String> addFirstLevelDept(AddClientDeptParam param){
        param.setClientUserCompanyId(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCompanyId());
        param.setCreator(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCode());
        return clientDeptService.addFirstLevelDept(param);
    }

    /**
     * <p>新增子级部门</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月29日 下午2:14:35
     */
    @RequestMapping("/addChildDept")
    public JsonResult<String> addChildDept(AddClientDeptParam param){
        param.setClientUserCompanyId(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCompanyId());
        param.setCreator(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCode());
        return clientDeptService.addFirstLevelDept(param);
    }
    
    /**
     * 
     * <p>查询父级部门</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月29日 下午2:14:30
     */
    @RequestMapping("/searchClientDeptList")
    public JsonResult<List<ClientDeptList>> searchClientDeptList(SearchClientDeptParam param){
        param.setClientUserCompanyId(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCompanyId());
        return clientDeptService.searchClientDeptList(param);
    }
    
    /**
     * 
     * <p>查询子级部门</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月29日 下午2:14:26
     */
    @RequestMapping("/searchChildClientDeptList")
    public JsonResult<List<ClientDeptList>> searchChildClientDeptList(SearchClientChildDeptParam param){
        param.setClientUserCompanyId(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCompanyId());
        return clientDeptService.searchChildClientDeptList(param);
    }
    
    /**
     * 
     * <p>编辑部门</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月29日 下午2:14:22
     */
    @RequestMapping("/editDept")
    public JsonResult<String> editDept(EditClientDeptParam param){
        param.setClientUserCompanyId(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCompanyId());
        param.setModifier(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCode());
        return clientDeptService.editDept(param);
    }
    
    /**
     * 
     * <p>启用、禁用部门</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月29日 下午2:14:20
     */
    @RequestMapping("/isEnableDept")
    public JsonResult<String> isEnableDept(EditDeptIsEnableParam param){
        param.setClientUserCompanyId(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCompanyId());
        param.setModifier(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCode());
        return clientDeptService.isEnableDept(param);
    }
    
    /**
     * <p>删除部门</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月29日 下午2:14:09
     */
    @RequestMapping("/delDept")
    public JsonResult<String> delDept(DelDeptParam param){
        param.setModifier(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCode());
        return clientDeptService.delDept(param);
    }
    
    /**
     * <p>获取部门详情</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月29日 下午4:20:54
     */
    @RequestMapping("/getDeptInfo")
    public JsonResult<ClientDeptInfo> getDeptInfo(SearchClientDeptInfoByCodeParam param){
        param.setClientUserCompanyId(WebUtils.getLoginClientUser().getClientUserInfo().getClientUserCompanyId());
        return clientDeptService.getDeptInfo(param);
    }
}
