/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午8:27:42
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.platform.merchant.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.merchant.pojo.bo.AddCaseParam;
import com.azz.platform.merchant.pojo.bo.CaseShelfParam;
import com.azz.platform.merchant.pojo.bo.DelCaseParams;
import com.azz.platform.merchant.pojo.bo.DelSelecttionParams;
import com.azz.platform.merchant.pojo.bo.EditCaseParam;
import com.azz.platform.merchant.pojo.bo.SearchCaseListParam;
import com.azz.platform.merchant.pojo.bo.SearchCaseParamList;
import com.azz.platform.merchant.pojo.vo.CaseDetail;
import com.azz.platform.merchant.pojo.vo.CaseList;
import com.azz.platform.merchant.pojo.vo.CaseParams;
import com.azz.platform.merchant.pojo.vo.CaseParamsList;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年11月5日 下午6:43:08
 */
@FeignClient("azz-merchant-management-service")
public interface CaseService {
    
    /**
     * <p>获取方案列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月7日 下午7:58:42
     */
    @PostMapping("/azz/api/merchant/case/searchCaseList")
    JsonResult<Pagination<CaseList>> searchCaseList(@RequestBody SearchCaseListParam param);
    
    /**
     * <p>新增方案</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月7日 上午10:27:49
     */
    @PostMapping("/azz/api/merchant/case/addCase")
    JsonResult<String> addCase(@RequestBody AddCaseParam param);
    
    /**
     * <p>编辑方案</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月7日 上午10:27:40
     */
    @PostMapping("/azz/api/merchant/case/editCase")
    JsonResult<String> editCase(@RequestBody EditCaseParam param);

    /**
     * <p>根据分类编码获取参数信息</p>
     * @param assortmentId
     * @return
     * @author 彭斌  2018年11月7日 上午10:27:32
     */
    @PostMapping("/azz/api/merchant/case/getCaseParamList")
    JsonResult<Pagination<CaseParams>> getCaseParamList(@RequestBody SearchCaseParamList param);

    /**
     * <p>根据方案编码获取选型参数</p>
     * @param caseCode
     * @return
     * @author 彭斌  2018年11月7日 上午10:27:08
     */
    @PostMapping("/azz/api/merchant/case/getCaseSelectionParameter")
    JsonResult<List<CaseParamsList>> getCaseSelectionParameter(@RequestParam("caseCode") String caseCode);
    
    /**
     * <p>移除选型参数</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月7日 上午10:27:03
     */
    @PostMapping("/azz/api/merchant/case/delSelectionParameter")
    JsonResult<String> delSelectionParameter(@RequestBody DelSelecttionParams param);
    
    /**
     * <p>根据方案编码获取方案详情</p>
     * @param caseCode
     * @return
     * @author 彭斌  2018年11月8日 上午10:20:58
     */
    @PostMapping("/azz/api/merchant/case/getCaseInfo")
    JsonResult<CaseDetail> getCaseInfo(@RequestParam("caseCode") String caseCode);
    
    /**
     * <p>删除方案</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月8日 上午10:49:34
     */
    @PostMapping("/azz/api/merchant/case/delCase")
    JsonResult<String> delCase(@RequestBody DelCaseParams param);
    
    /**
     * <p>方案上架下架</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月8日 上午11:26:22
     */
    @PostMapping("/azz/api/merchant/case/caseShelf")
    JsonResult<String> caseShelf(@RequestBody CaseShelfParam param);
}

