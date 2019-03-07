/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月19日 下午2:00:23
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.azz.crawler.common.JsonResult;
import com.azz.crawler.pojo.BaixingTitle;
import com.azz.crawler.pojo.Bdsh5Title;
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


	/* * 
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
	
	
	@RequestMapping(value = "getGanjiSearchInfoByTitle", produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonResult<Map<String, List<BaoXianInfo>>> getGanjiSearchInfoByTitle(@RequestBody List<GanJiTitle> titlesToSearch, HttpServletRequest request){
        JsonResult<Map<String, List<BaoXianInfo>>> result = crawlerService.getGanjiSearchInfoByTitle(titlesToSearch);
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
		response.setHeader("Content-disposition", "attachment;filename=bdsh5.xlsx");
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
		response.setHeader("Content-disposition", "attachment;filename=baixing.xlsx");
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
        response.setHeader("Content-disposition", "attachment;filename=ganjibaoxian.xlsx");
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
}

