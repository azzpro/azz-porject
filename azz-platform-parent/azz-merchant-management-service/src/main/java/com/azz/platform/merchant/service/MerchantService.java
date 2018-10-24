/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月22日 下午8:31:02 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.merchant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.JSR303ErrorCode;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.exception.BaseException;
import com.azz.exception.JSR303ValidationException;
import com.azz.platform.merchant.mapper.MerchantApplyMapper;
import com.azz.platform.merchant.mapper.MerchantMapper;
import com.azz.platform.merchant.pojo.bo.SearchMerchantListParam;
import com.azz.platform.merchant.pojo.bo.SearchMerchantParam;
import com.azz.platform.merchant.pojo.bo.SearchMerchantUserParam;
import com.azz.platform.merchant.pojo.vo.MerchantApproval;
import com.azz.platform.merchant.pojo.vo.MerchantInfo;
import com.azz.platform.merchant.pojo.vo.MerchantInfoOpen;
import com.azz.platform.merchant.pojo.vo.MerchantListInfo;
import com.azz.platform.merchant.pojo.vo.MerchantUserInfo;
import com.azz.util.StringUtils;
import com.github.pagehelper.PageHelper;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 彭斌 2018年10月22日 下午8:31:02
 */
@Service
public class MerchantService{

    @Autowired
    MerchantMapper merchantMapper;

    @Autowired
    MerchantApplyMapper merchantApplyMapper;

    public JsonResult<Pagination<MerchantApproval>> searchMerchantList(@RequestBody SearchMerchantParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<MerchantApproval> merchantList = merchantMapper.selectMerchantList(param);
        return JsonResult.successJsonResult(new Pagination<>(merchantList));
    }

    public JsonResult<MerchantInfo> searchMerchantInfo(String merchantCode) {
        if (StringUtils.isBlank(merchantCode)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_MERCHANT_ERROR_NO_EXIST);
        }
        MerchantInfo merchantobj = merchantApplyMapper.selectMerchantInfoByCode(merchantCode);
        return JsonResult.successJsonResult(merchantobj);
    }
    
    /**
     * <p>商户管理列表</p>
     * @param param
     * @return
     * @author 刘建麟  2018年10月24日 下午7:31:57
     */
    public JsonResult<Pagination<MerchantListInfo>> searchMerchantListInfo(@RequestBody SearchMerchantListParam param) {
    	 PageHelper.startPage(param.getPageNum(), param.getPageSize());
    	 List<MerchantListInfo> merchantInfoList = merchantMapper.selectMerchantInfoList(param);
         return JsonResult.successJsonResult(new Pagination<>(merchantInfoList));
    }
    
    /**
     * <p>商户管理 启动 禁用</p>
     * @param param
     * @return
     * @author 刘建麟  2018年10月24日 下午7:31:57
     */
    @Transactional(rollbackFor=Exception.class)
    public JsonResult<String> merchantStatusChange(String code,Integer status) {
    	 merchantMapper.merchantStatusChange(code,status);
    	 return JsonResult.successJsonResult();
    }
    
    /**
     * <p>商户管理 详情</p>
     * @param param
     * @return
     * @author 刘建麟  2018年10月24日 下午7:31:57
     */
    public JsonResult<MerchantInfoOpen> getMerchantInfo(String code) {
    	 MerchantInfoOpen info = merchantMapper.getMerchantInfo(code);
    	 return JsonResult.successJsonResult(info);
    }
    
    /**
     * <p>商户管理列表 成员信息</p>
     * @param param
     * @return
     * @author 刘建麟  2018年10月24日 下午7:31:57
     */
    public JsonResult<Pagination<MerchantUserInfo>> getMerchantUserInfo(@RequestBody SearchMerchantUserParam param) {
    	 PageHelper.startPage(param.getPageNum(), param.getPageSize());
    	 List<MerchantUserInfo> merchantInfoList = merchantMapper.getMerchantUserInfo(param);
         return JsonResult.successJsonResult(new Pagination<>(merchantInfoList));
    }

}

