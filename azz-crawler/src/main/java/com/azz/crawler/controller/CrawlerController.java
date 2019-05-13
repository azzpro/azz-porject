/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月19日 下午2:00:23
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSONObject;
import com.azz.crawler.common.JsonResult;
import com.azz.crawler.common.MD5Encrypt;
import com.azz.crawler.common.OkHttpUtil;
import com.azz.crawler.pojo.BaixingTitle;
import com.azz.crawler.pojo.Bdsh5Title;
import com.azz.crawler.pojo.CityInfo;
import com.azz.crawler.pojo.GanJiTitle;
import com.azz.crawler.pojo.vo.BaoXianInfo;
import com.azz.crawler.pojo.vo.SearchInfo;
import com.azz.crawler.service.CrawlerService;


/**
 * <P>本地生活网爬虫</P>
 * @version 1.0
 * @author 黄智聪  2019年2月19日 下午2:00:23
 */
@Controller
@RequestMapping("/azz/crawler/")
public class CrawlerController {
	
	@Autowired
	CrawlerService crawlerService;
	
	@RequestMapping("menu")
	public String menu() {
		return "menu";
	}

	@RequestMapping("login")
    public String toLogin() {
        return "login";
    }
	
	@RequestMapping("bdsh5")
    public String bxsh5() {
        return "bdsh5";
    }
	
	@RequestMapping("ganji")
    public String ganji() {
        return "ganji";
	}
	
	@RequestMapping("baixing")
    public String baixing() {
        return "baixing";
    }
	
	@RequestMapping("citySelection")
    public String citySelection() {
        return "citySelection";
    }
	
	/**
	 * 
	 * <p>查询本地生活所有标题</p>
	 * @return
	 * @author 黄智聪  2019年2月20日 下午1:40:28
	 */
	@RequestMapping("getBdsh5Titles")
	@ResponseBody
	public JsonResult<List<Bdsh5Title>> getBdsh5Titles(){
		return crawlerService.getBdsh5Titles();
	}
	
	/**
	 * <p>初始所有赶集网基础标题路由数据</p>
	 * @return
	 * @author 彭斌  2019年2月27日 下午3:09:45
	 */
	@RequestMapping("getGanJiTitles")
    @ResponseBody
    public JsonResult<List<GanJiTitle>> getGanJiTitles(){
        return crawlerService.getGanJiTitles();
    }


	/**
	 * <p>查询百姓网所有标题</p>
	 * @return
	 * @author 黄智聪  2019年2月20日 下午1:40:28
	 */
	@RequestMapping("getBaixingTitles")
	@ResponseBody
	public JsonResult<List<BaixingTitle>> getBaixingTitles(){
		return crawlerService.getBaixingTitles();
	}
	
	
	@RequestMapping(value = "doLogin",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<String> doLogin(@RequestParam("userName")String userName, @RequestParam("pwd")String pwd, HttpServletRequest req){
	    return crawlerService.doLogin(userName, pwd, req);
    }
	
	/**
	 * <p>初始所有赶集网基础标题路由数据</p>
	 * @return
	 * @author 彭斌  2019年2月27日 下午3:09:45
	 */
	@RequestMapping("getGanjiCities")
    @ResponseBody
    public JsonResult<List<CityInfo>> getGanjiCities(CityInfo info, HttpServletRequest request){
        return crawlerService.getGanjiCities();
    }
	
	/**
	 * 
	 * <p>根据标题查询本地生活网信息</p>
	 * @param titles 需要爬取数据的标题
	 * @return
	 * @author 黄智聪  2019年2月20日 下午2:06:46
	 */
	@RequestMapping(value = "getBdsh5SearchInfoByTitle", produces = "application/json;charset=utf-8")
	@ResponseBody
	public JsonResult<Map<String, List<SearchInfo>>> getBdsh5SearchInfoByTitle(@RequestBody List<Bdsh5Title> titlesToSearch, HttpServletRequest request){
		JsonResult<Map<String, List<SearchInfo>>> result = crawlerService.getBdsh5SearchInfoByTitle(titlesToSearch);
		Map<String, List<SearchInfo>> data = result.getData();
		WebUtils.setSessionAttribute(request, "bdsh5", data);
		return result;
	}
	
	/**
	 * 
	 * <p>选择赶集网城市</p>
	 * @param info
	 * @param request
	 * @return
	 * @author 黄智聪  2019年3月11日 下午6:32:19
	 */
	@RequestMapping(value = "selectCity",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonResult<String> selectCity(@RequestBody CityInfo info, HttpServletRequest request){
		WebUtils.setSessionAttribute(request, "ganjiCity", info);
        return JsonResult.successJsonResult();
    }
	
	
	@RequestMapping(value = "getGanjiSearchInfoByTitle", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonResult<Map<String, List<BaoXianInfo>>> getGanjiSearchInfoByTitle(@RequestBody List<GanJiTitle> titlesToSearch, HttpServletRequest request){
		CityInfo info = (CityInfo)WebUtils.getSessionAttribute(request, "ganjiCity");
		if(info == null) {
			throw new RuntimeException("请选择城市");
		}
		String prefixUrl = info.getCityUrl();
		// 对url进行处理
		for (GanJiTitle ganJiTitle : titlesToSearch) {
			String subfixUrl = ganJiTitle.getUrl();// 只是后缀：如baoxian/  
			String newUrl = prefixUrl + subfixUrl;
			ganJiTitle.setUrl(newUrl);
		}
		JsonResult<Map<String, List<BaoXianInfo>>> result = crawlerService.getGanjiSearchInfoByTitle(prefixUrl, titlesToSearch);
        Map<String, List<BaoXianInfo>> data = result.getData();
        WebUtils.setSessionAttribute(request, "ganji", data);
        return result;
    }
	
	/**
	 * 
	 * <p>导出本地生活网的数据</p>
	 * @param title
	 * @param infos
	 * @return
	 * @author 黄智聪  2019年2月20日 下午6:13:16
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("exportBdsh5Data")
	@ResponseBody
	public JsonResult<String> exportBdsh5Data(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, List<SearchInfo>> data = (Map<String, List<SearchInfo>>) WebUtils.getSessionAttribute(request, "bdsh5");
		HSSFWorkbook wb = crawlerService.exportBdsh5Data(data);
		WebUtils.setSessionAttribute(request, "bdsh5", null);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=bdsh5.xls");
		response.flushBuffer();
		wb.write(response.getOutputStream());
		return JsonResult.successJsonResult();
	}
	
	/**
	 * 
	 * <p>根据标题查询百姓网信息</p>
	 * @param titles 需要爬取数据的标题
	 * @return
	 * @author 黄智聪  2019年2月20日 下午2:06:46
	 */
	@RequestMapping(value = "getBaixingSearchInfoByTitle", produces = "application/json;charset=utf-8")
	@ResponseBody
	public JsonResult<Map<String, List<SearchInfo>>> getBaixingSearchInfoByTitle(@RequestBody List<BaixingTitle> titlesToSearch, HttpServletRequest request){
		JsonResult<Map<String, List<SearchInfo>>> result = crawlerService.getBaixingSearchInfoByTitle(titlesToSearch);
		Map<String, List<SearchInfo>> data = result.getData();
		if(data != null) {
			WebUtils.setSessionAttribute(request, "baixing", data);
		}else {
			throw new RuntimeException("爬取出错");
		}
		return result;
	}
	
	
	/**
	 * 
	 * <p>导出百姓网的数据</p>
	 * @param title
	 * @param infos
	 * @return
	 * @author 黄智聪  2019年2月20日 下午6:13:16
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("exportBaixingData")
	@ResponseBody
	public JsonResult<String> exportBaixingData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, List<SearchInfo>> data = (Map<String, List<SearchInfo>>) WebUtils.getSessionAttribute(request, "baixing");
		HSSFWorkbook wb = crawlerService.exportBaixingData(data);
		WebUtils.setSessionAttribute(request, "baixing", null);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=baixing.xls");
		response.flushBuffer();
		wb.write(response.getOutputStream());
		return JsonResult.successJsonResult();
	}
	
	/**
	 * <p>导出赶集网保险信息</p>
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @author 彭斌  2019年3月1日 下午2:13:39
	 */
	@SuppressWarnings("unchecked")
    @RequestMapping("exportGanjiBaoXianData")
    @ResponseBody
    public JsonResult<String> exportGanJiBaoxianData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, List<BaoXianInfo>> data = (Map<String, List<BaoXianInfo>>) WebUtils.getSessionAttribute(request, "ganji");
        HSSFWorkbook wb = crawlerService.exportGanJiBaoxianData(data);
        WebUtils.setSessionAttribute(request, "ganji", null);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=ganjibaoxian.xls");
        response.flushBuffer();
        wb.write(response.getOutputStream());
        return JsonResult.successJsonResult();
    }
	
	/**
	 * <p>导出技校信息</p>
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @author 彭斌  2019年3月7日 上午10:13:52
	 */
	/*@SuppressWarnings("unchecked")
    @RequestMapping("exportJXData")
    @ResponseBody
    public JsonResult<String> exportJXData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HSSFWorkbook wb = crawlerService.exportJXData();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=jx.xlsx");
        response.flushBuffer();
        wb.write(response.getOutputStream());
        return JsonResult.successJsonResult();
    }*/
	@RequestMapping("testLogin")
    @ResponseBody
	public JsonResult<String> login(){
		// 接口请求时，双方规定用于验签时的公钥
		final String key = "MKtbZiEa8ZO9KfgY";
		// 为了保证请求参数按照a-z从小到大的顺序，使用TreeMap
		Map<String, String> paramMap = new TreeMap<>();
		// 1.加入接口的请求参数
		paramMap.put("username", "test");
		paramMap.put("password", "123456");
		// 签名时会用到的时间戳参数
		String timestamp = System.currentTimeMillis() + "";
		paramMap.put("timestamp", timestamp);
		// 2.生成签名的步骤如下：
		// （1）得到有序的参数json字符串：将有序的参数对象，转成json格式的字符串
		String sortedParamJsonStr = JSONObject.toJSONString(paramMap);
		System.out.println("1.得到有序的参数json字符串 ==> " + sortedParamJsonStr);
		// （2）得到签名参数： 使用MD5加密，按照 “有序的参数json字符串、时间戳、双方约定的公钥” 的顺序拼接起来进行MD5加密
		String signature = MD5Encrypt.encryptMD5(sortedParamJsonStr + timestamp + key, "UTF-8");
		System.out.println("2.得到签名参数 ==> " + signature);
		// （3）记得加入到接口请求参数中，作为我方校验使用
		paramMap.put("signature", signature);
		System.out.println("3.发送的接口请求参数 ==> " + JSONObject.toJSONString(paramMap));
		// 3.发送post接口请求
		String requestUrl = "http://192.168.1.175:8081/hefa/api/client/member/login";
		String response = OkHttpUtil.postFormData(requestUrl, paramMap);
		if(StringUtils.isNotBlank(response)) {
			JSONObject jsonResult = JSONObject.parseObject(response);
			int code = jsonResult.getInteger("code");
			if(code == 0) {
				return JsonResult.successJsonResult(jsonResult.getString("data"));
			}
		}
		System.out.println("4.得到响应结果 ==> " + response);
		return JsonResult.successJsonResult();
	}
	
	@RequestMapping("addToShoppingCart")
    @ResponseBody
	public JsonResult<String> addToShoppingCart(String token){
		// 接口请求时，双方规定用于验签时的公钥
		final String key = "MKtbZiEa8ZO9KfgY";
		// 为了保证请求参数按照a-z从小到大的顺序，使用TreeMap
		Map<String, String> paramMap = new TreeMap<>();
		// 1.加入接口的请求参数
		paramMap.put("userCode", "-2");
		paramMap.put("productCode", "2");
		// 签名时会用到的时间戳参数
		String timestamp = System.currentTimeMillis() + "";
		paramMap.put("timestamp", timestamp);
		// 2.生成签名的步骤如下：
		// （1）得到有序的参数json字符串：将有序的参数对象，转成json格式的字符串
		String sortedParamJsonStr = JSONObject.toJSONString(paramMap);
		System.out.println("1.得到有序的参数json字符串 ==> " + sortedParamJsonStr);
		// （2）得到签名参数： 使用MD5加密，按照 “有序的参数json字符串、时间戳、双方约定的公钥” 的顺序拼接起来进行MD5加密
		String signature = MD5Encrypt.encryptMD5(sortedParamJsonStr + timestamp + key, "UTF-8");
		System.out.println("2.得到签名参数 ==> " + signature);
		// （3）记得加入到接口请求参数中，作为我方校验使用
		paramMap.put("signature", signature);
		System.out.println("3.发送的接口请求参数 ==> " + JSONObject.toJSONString(paramMap));
		// 3.header中加入token，获取方式通过调用登录接口获得
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("token", token);
		System.out.println("3.请求头中的token ==> " + token);
		// 4.发送post接口请求
		String requestUrl = "http://192.168.1.175:8081/hefa/api/client/selection/addProductToShoppingCart";
		String response = OkHttpUtil.postFormData(requestUrl, paramMap, headerMap);
		System.out.println("4.得到响应结果 ==> " + response);
		if(StringUtils.isNotBlank(response)) {
			JSONObject jsonResult = JSONObject.parseObject(response);
			int code = jsonResult.getInteger("code");
			if(code == 0) {
				return JsonResult.successJsonResult();
			}else {
				JsonResult<String> jr = new JsonResult<>();
				jr.setCode(code);
				jr.setMsg(jsonResult.getString("msg"));
				return jr;
			}
		}
		return JsonResult.successJsonResult();
	}
}

