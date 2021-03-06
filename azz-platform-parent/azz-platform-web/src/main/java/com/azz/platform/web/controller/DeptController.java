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
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.user.api.DeptService;
import com.azz.platform.user.pojo.bo.AddDeptParam;
import com.azz.platform.user.pojo.bo.EditDeptParam;
import com.azz.platform.user.pojo.bo.ImportPlatformDeptParam;
import com.azz.platform.user.pojo.bo.ImportPlatformDeptWebParam;
import com.azz.platform.user.pojo.bo.SearchDeptParam;
import com.azz.platform.user.pojo.vo.Dept;
import com.azz.util.Base64;
import com.azz.util.JSR303ValidateUtils;
import com.azz.utils.WebUtils;

/**
 * <P>部门管理</P>
 * @version 1.0
 * @author 彭斌  2018年10月17日 下午3:04:47
 */
@RestController
@RequestMapping("/azz/api/dept")
public class DeptController {

	private static final Logger LOG = LoggerFactory.getLogger(DeptController.class);

	@Autowired
	DeptService deptService;
	
	/**
	 * <p>新增部门信息</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年10月17日 下午3:17:05
	 */
	@RequestMapping("/addDeptInfo")
	public JsonResult<String> addDeptInfo(AddDeptParam param) {
		LOG.info("###########开始新增部门信息###########");
		param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return deptService.addDeptInfo(param);
	}

	/**
	 * <p>获取父级部门下的信息</p>
	 * @param deptId
	 * @return
	 * @author 彭斌  2018年10月18日 下午1:42:11
	 */
	@RequestMapping("/getDeptParentList")
	public JsonResult<List<Dept>> getDeptParentList(@RequestParam("parentCode") String parentCode){
	    LOG.info("###########获取部门信息###########");
	    return deptService.getDeptParentInfo(parentCode);
	}
	
	/**
	 * <p>获取部门列表</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年10月18日 下午1:45:17
	 */
	@RequestMapping("/getDeptList")
	public JsonResult<List<Dept>> getDeptList(SearchDeptParam param){
	    LOG.info("###########获取部门列表信息###########");
	    return deptService.getDeptList(param);
	}
	
	/**
	 * <p>修改部门信息</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年10月18日 下午1:47:23
	 */
	@RequestMapping("/editDeptInfo")
	public JsonResult<String> editDeptInfo(EditDeptParam param){
	    LOG.info("###########修改部门信息###########");
	    param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
	    return deptService.editDeptInfo(param);
	}
	
	/**
	 * <p>删除部门信息</p>
	 * @param id
	 * @return
	 * @author 彭斌  2018年10月18日 下午1:49:02
	 */
	@RequestMapping("/delDeptInfo")
	JsonResult<String> delDeptInfo(@RequestParam("deptCode") String deptCode){
	    LOG.info("###########删除部门信息###########");
	    String modifier = WebUtils.getLoginUser().getUserInfo().getUserCode();
	    return deptService.delDeptInfo(deptCode,modifier);
	}
	
	/**
	 * <p>禁用部门信息</p>
	 * @param deptCode
	 * @return
	 * @author 彭斌  2018年10月20日 下午5:01:35
	 */
    @RequestMapping("/disableDeptInfo")
    JsonResult<String> disableDeptInfo(@RequestParam("deptCode") String deptCode){
        LOG.info("###########禁用部门信息###########");
        String modifier = WebUtils.getLoginUser().getUserInfo().getUserCode();
        return deptService.disableDeptInfo(deptCode, modifier);
    }
    
    /**
     * <p>启用部门信息</p>
     * @param deptCode
     * @return
     * @author 彭斌  2018年10月20日 下午5:01:35
     */
    @RequestMapping("/enableDeptInfo")
    JsonResult<String> enableDeptInfo(@RequestParam("deptCode") String deptCode){
        LOG.info("###########启用部门信息###########");
        String modifier = WebUtils.getLoginUser().getUserInfo().getUserCode();
        return deptService.enableDeptInfo(deptCode, modifier);
    }
    
    /**
     * <p>导入平台部门信息</p>
     * @param webParam
     * @return
     * @throws IOException
     * @author 彭斌  2018年12月12日 下午2:17:31
     */
    @RequestMapping(value = "/importPlatformDept")
    public JsonResult<String> importPlatformDept(ImportPlatformDeptWebParam webParam) throws IOException{
        JSR303ValidateUtils.validate(webParam);
        MultipartFile file = webParam.getFile();
        String fileName = file.getOriginalFilename();
        if(!fileName.endsWith(".xls")) {
            throw new JSR303ValidationException(JSR303ErrorCode.SYS_ERROR_INVALID_REQUEST_PARAM, "文件格式有误");
        }
        ImportPlatformDeptParam param = new ImportPlatformDeptParam();
        param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
        param.setBase64Str(Base64.encode(file.getBytes()));
        return deptService.importPlatformDept(param);
    }
}
