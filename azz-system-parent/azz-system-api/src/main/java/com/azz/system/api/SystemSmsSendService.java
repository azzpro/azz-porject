/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年10月23日 下午1:47:30
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <P>短信发送</P>
 * @version 1.0
 * @author 刘建麟  2018年10月23日 下午1:47:30
 */
@FeignClient("azz-system-service")
public interface SystemSmsSendService {
	
	
	@RequestMapping(value="/azz/api/smsSend",method=RequestMethod.POST)
	String smsSend(@RequestParam("phone") String phone,@RequestParam("nickname")String nickname,@RequestParam("template") String template);
}

