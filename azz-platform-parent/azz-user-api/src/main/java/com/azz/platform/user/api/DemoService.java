/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月14日 上午8:52:28
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/

package com.azz.platform.user.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.user.pojo.Demo;
import com.azz.platform.user.pojo.DemoInfo;
import com.azz.platform.user.pojo.bo.DemoSearchParam;

/**
 * <P>
 * TODO
 * </P>
 * 
 * @version 1.0
 * @author 刘建麟 2018年10月14日 上午8:52:28
 */
@FeignClient("azz-goods-service")
public interface DemoService {

    @GetMapping("getName")
    List<Demo> getName();

    @PostMapping("getDemoInfosByIds")
    JsonResult<Pagination<DemoInfo>> getDemoInfosByIds(DemoSearchParam param);

}
