/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月27日 上午11:02:10
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.platform.user.pojo.PlatformIndexColumn;
import com.azz.platform.user.pojo.bo.AddColumn;
import com.azz.platform.user.pojo.bo.EditColumn;
import com.azz.platform.user.pojo.vo.ColumnInfo;
import com.azz.platform.user.service.IndexService;

@RestController
@RequestMapping("/azz/api/index")
public class IndexController {

	
	@Autowired
	private IndexService indexService;
	
	/**
	 * <p>新增栏目</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月27日 下午9:33:43
	 */
	@RequestMapping(value="addColumn",method=RequestMethod.POST)
	JsonResult<String> addColumn(@RequestBody AddColumn param){
		return indexService.addColumn(param);
	}
	
	/**
	 * <p>获取所有栏目集合倒序</p>
	 * @return
	 * @author 彭斌  2018年11月27日 下午9:33:39
	 */
	@RequestMapping(value="getColumnLsit",method=RequestMethod.POST)
	JsonResult<List<ColumnInfo>> getColumnLsit(){
	    return indexService.getColumnLsit();
	}
	
	/**
	 * <p>编辑栏目</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月27日 下午9:33:37
	 */
	@RequestMapping(value="editColumn",method=RequestMethod.POST)
	JsonResult<String> editColumn(@RequestBody EditColumn param){
        return indexService.editColumn(param);
    }
	
	/**
	 * <p>删除栏目</p>
	 * @param columnId
	 * @return
	 * @author 彭斌  2018年11月27日 下午9:33:33
	 */
    @RequestMapping(value="delColumn",method=RequestMethod.GET)
    JsonResult<String> delColumn(@RequestParam("columnId") Long columnId){
    	return indexService.delColumn(columnId);
    }
    
    /**
     * <p>获取栏目详情</p>
     * @param columnId
     * @return
     * @author 彭斌  2018年11月27日 下午9:33:31
     */
    @RequestMapping(value="getColumnInfo",method=RequestMethod.GET)
    JsonResult<PlatformIndexColumn> getColumnInfo(@RequestParam("columnId") Long columnId){
        return indexService.getColumnInfo(columnId);
    }
}

