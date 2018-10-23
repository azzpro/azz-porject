/*******************************************************************************
 * Project Key : CPPII Create on 2018年10月22日 下午8:31:02 Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.merchant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.azz.core.common.JsonResult;
import com.azz.core.common.errorcode.PlatformUserErrorCode;
import com.azz.core.common.page.Pagination;
import com.azz.core.exception.BaseException;
import com.azz.platform.merchant.api.MerchantService;
import com.azz.platform.merchant.mapper.MerchantApplyMapper;
import com.azz.platform.merchant.mapper.MerchantMapper;
import com.azz.platform.merchant.pojo.bo.SearchMerchantParam;
import com.azz.platform.merchant.pojo.vo.MerchantApproval;
import com.azz.platform.merchant.pojo.vo.MerchantInfo;
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
@RestController
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantMapper merchantMapper;

    @Autowired
    MerchantApplyMapper merchantApplyMapper;

    @Override
    public JsonResult<Pagination<MerchantApproval>> searchMerchantList(SearchMerchantParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<MerchantApproval> merchantList = merchantMapper.selectMerchantList(param);
        return JsonResult.successJsonResult(new Pagination<>(merchantList));
    }

    @Override
    public JsonResult<MerchantInfo> searchMerchantInfo(String merchantCode) {
        if (StringUtils.isBlank(merchantCode)) {
            throw new BaseException(PlatformUserErrorCode.PLATFORM_MERCHANT_ERROR_NO_EXIST);
        }
        MerchantInfo merchantobj = merchantApplyMapper.selectMerchantInfoByCode(merchantCode);
        return JsonResult.successJsonResult(merchantobj);
    }

}

