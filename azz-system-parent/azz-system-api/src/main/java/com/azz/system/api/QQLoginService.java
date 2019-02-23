package com.azz.system.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.azz.core.common.JsonResult;
import com.azz.system.bo.QQClientRegistParam;
import com.azz.system.bo.WxClientRegistParam;
import com.azz.system.bo.WxLoginParam;
import com.azz.system.pojo.ClientWxUser;
import com.azz.system.vo.QQCallBackInfo;
import com.azz.system.vo.WxCallBackInfo;
import com.azz.system.vo.WxInfo;
import com.azz.system.vo.WxLoginInfo;

@FeignClient("azz-system-service")
public interface QQLoginService {

	/**
	 * 去到QQ扫码页面
	 * @return
	 */
	@RequestMapping(value="/azz/api/qq/goQQScanPage",method=RequestMethod.POST)
	public JsonResult<WxInfo> goQQScanPage();
		
	
	/**
	 * QQ回调
	 * @param code
	 * @param state
	 * @param key
	 * @return
	 */
	@RequestMapping(value="/azz/api/qq/callback",method=RequestMethod.POST)
	public JsonResult<WxCallBackInfo> callback(@RequestParam("access_token")String access_token,@RequestParam("expires_in")String expires_in) ;
	
	/**
	 * 绑定或者注册
	 * @param wcbi
	 * @return
	 */
	@RequestMapping(value="/azz/api/qq/goBindOrReg",method=RequestMethod.POST)
	public JsonResult<WxCallBackInfo> goBindOrReg(@RequestBody QQCallBackInfo wcbi) ;
	
	/**
	 * @param wsc
	 * @return
	 */
	@RequestMapping(value="/azz/api/qq/insert",method=RequestMethod.POST)
	public Integer insert(@RequestBody ClientWxUser wsc) ;
	
	/**
	 * 注册并绑定
	 * @param wcrp
	 * @return
	 */
	@RequestMapping(value="/azz/api/qq/regAndBind",method=RequestMethod.POST)
	public JsonResult<WxLoginInfo> regAndBind(@RequestBody QQClientRegistParam wcrp) ;
}
