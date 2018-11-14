/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年11月14日 下午5:13:17
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.order.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.order.merchant.mapper.MerchantOrderMapper;
import com.azz.order.platform.bo.SearchMerchantOrderParam;
import com.azz.order.platform.vo.MerchantOrderList;
import com.github.pagehelper.PageHelper;

/**
 * <P>平台端商户订单管理</P>
 * @version 1.0
 * @author 彭斌  2018年11月14日 下午5:13:17
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class PlatformMerchantOrderService {
    @Autowired
    private MerchantOrderMapper merchantOrderMapper;
    
    /**
     * <p>获取订单列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年11月14日 下午5:15:26
     */
    public JsonResult<Pagination<MerchantOrderList>> getMerchantOrderInfoList(@RequestBody SearchMerchantOrderParam param){
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<MerchantOrderList> list = merchantOrderMapper.selectPlatformMerchantOrder(param);
        return JsonResult.successJsonResult(new Pagination<>(list));
    }
}

