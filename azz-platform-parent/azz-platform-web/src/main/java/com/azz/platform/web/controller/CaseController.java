/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月15日 下午3:05:46
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.web.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.api.CaseService;
import com.azz.platform.merchant.pojo.bo.AddCaseParam;
import com.azz.platform.merchant.pojo.bo.AddCaseWebParam;
import com.azz.platform.merchant.pojo.bo.CasePic;
import com.azz.platform.merchant.pojo.bo.CaseShelfParam;
import com.azz.platform.merchant.pojo.bo.DelCaseParams;
import com.azz.platform.merchant.pojo.bo.DelSelecttionParams;
import com.azz.platform.merchant.pojo.bo.EditCaseParam;
import com.azz.platform.merchant.pojo.bo.EditCaseWebParam;
import com.azz.platform.merchant.pojo.bo.SearchCaseListParam;
import com.azz.platform.merchant.pojo.bo.SearchCaseParamList;
import com.azz.platform.merchant.pojo.vo.CaseDetail;
import com.azz.platform.merchant.pojo.vo.CaseList;
import com.azz.platform.merchant.pojo.vo.CaseParams;
import com.azz.util.Base64;
import com.azz.utils.WebUtils;

/**
 * <P>分类管理</P>
 * @version 1.0
 * @author 彭斌  2018年10月17日 下午3:04:47
 */
@RestController
@RequestMapping("/azz/api/merchant/case")
public class CaseController {

	private static final Logger LOG = LoggerFactory.getLogger(CaseController.class);

	@Autowired
	CaseService  caseService;
	
	/**
	 * <p>方案列表查询</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月7日 下午7:59:44
	 */
	@RequestMapping("/searchCaseList")
	public JsonResult<Pagination<CaseList>> searchCaseList(SearchCaseListParam param){
	    return caseService.searchCaseList(param);
	}
	
	/**
	 * <p>新增方案</p>
	 * @param param
	 * @return
	 * @throws IOException
	 * @author 彭斌  2018年11月7日 上午11:29:14
	 */
	@RequestMapping("/addCase")
	public JsonResult<String> addCase(AddCaseWebParam param) throws IOException {
	    MultipartFile caseFile = param.getCaseFile();
	    CasePic cp = new CasePic(caseFile.getOriginalFilename(), caseFile.getSize(), Base64.encode(caseFile.getBytes()));
        param.setCreator(WebUtils.getLoginUser().getUserInfo().getUserCode());
		return caseService.addCase(new AddCaseParam(param.getClassificationId(),param.getCaseName(),param.getCaseStatus(),param.getRemark(),param.getCreator(),param.getParamsId(),cp));
	}
	
	/**
	 * 
	 * <p>编辑方案</p>
	 * @param param
	 * @return
	 * @throws IOException
	 * @author 彭斌  2018年11月7日 上午11:29:06
	 */
	@RequestMapping("/editCase")
	public JsonResult<String> editCase(EditCaseWebParam param) throws IOException{
	    CasePic cp = null;
	    if(1 == param.getIsEditPic()) {
	        MultipartFile classificationFile = param.getCaseFile();
	        cp = new CasePic(classificationFile.getOriginalFilename(), classificationFile.getSize(), Base64.encode(classificationFile.getBytes()));
	    }
	    param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
	    return caseService.editCase(new EditCaseParam(param.getCaseCode(),param.getClassificationId(),param.getCaseName(),param.getCaseStatus(),param.getRemark(),cp,param.getModifier(),param.getIsEditPic(),param.getParamsId()));
	}
	
	/**
	 * <p>根据分类编码获取参数信息</p>
	 * @param assortmentId
	 * @return
	 * @author 彭斌  2018年11月7日 上午11:29:01
	 */
	@RequestMapping("/getCaseParamList")
    public JsonResult<Pagination<CaseParams>> getCaseParamList(SearchCaseParamList param){
	    return caseService.getCaseParamList(param);
	}
	
	
	/**
	 * <p>根据方案编码获取方案详情和选型参数</p>
	 * @param caseCode
	 * @return
	 * @author 彭斌  2018年11月7日 上午11:28:37
	 */
	@RequestMapping("/getCaseInfo")
    public JsonResult<CaseDetail> getCaseInfo(@RequestParam("caseCode") String caseCode){
	    return caseService.getCaseInfo(caseCode);
	}
    
	/**
	 * <p>移除选型参数</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月7日 上午11:28:11
	 */
	@RequestMapping("/delSelectionParameter")
    public JsonResult<String> delSelectionParameter(DelSelecttionParams param) {
	    param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
        return caseService.delSelectionParameter(param);
    }
	
	/**
	 * <p>删除方案</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月8日 上午11:10:44
	 */
	@RequestMapping("/delCase")
	public JsonResult<String> delCase(DelCaseParams param){
	    param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
	    return caseService.delCase(param);
	}
	
	/**
	 * <p>方案上架下架</p>
	 * @param param
	 * @return
	 * @author 彭斌  2018年11月8日 上午11:28:00
	 */
	@RequestMapping("/caseShelf")
	public JsonResult<String> caseShelf(CaseShelfParam param){
	    param.setModifier(WebUtils.getLoginUser().getUserInfo().getUserCode());
	    return caseService.caseShelf(param);
	}
}
