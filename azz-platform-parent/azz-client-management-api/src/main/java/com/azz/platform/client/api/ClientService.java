package com.azz.platform.client.api;
/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月22日 下午3:54:14
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.azz.core.common.JsonResult;
import com.azz.core.common.page.Pagination;
import com.azz.platform.client.pojo.bo.SearchClientParam;
import com.azz.platform.client.pojo.vo.ClientCertification;
import com.azz.platform.client.pojo.vo.ClientInfo;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2018年10月22日 下午3:54:14
 */
@FeignClient("azz-client-management-service")
public interface ClientService {
    
    /**
     * <p>获取客户审批列表</p>
     * @param param
     * @return
     * @author 彭斌  2018年10月24日 下午1:39:13
     */
    @PostMapping("searchClientList")
    JsonResult<Pagination<ClientCertification>> searchClientList(@RequestBody SearchClientParam param);
    
    /**
     * <p>客户详情信息</p>
     * @param clientCode
     * @return
     * @author 彭斌  2018年10月24日 下午1:39:17
     */
    @GetMapping("searchClientInfo")
    JsonResult<ClientInfo> searchClientInfo(String clientCode);
}

