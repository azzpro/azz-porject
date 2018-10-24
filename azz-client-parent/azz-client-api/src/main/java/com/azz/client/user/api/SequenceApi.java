/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月24日 下午2:05:44
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.client.user.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年10月24日 下午2:05:44
 */
@FeignClient("azz-client-service")
public interface SequenceApi {
	
	@RequestMapping(value="/azz/api/client/getsequence",method=RequestMethod.GET)
	String getsequence();
}

