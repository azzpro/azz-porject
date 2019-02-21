/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月19日 下午2:00:23
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.azz.core.common.JsonResult;
import com.azz.crawler.pojo.Bdsh5Title;
import com.azz.crawler.pojo.bo.SearchInfoParam;
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
	
	@RequestMapping("index")
	public String toIndex() {
		return "index";
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
	 * 
	 * <p>根据标题查询本地生活网信息</p>
	 * @param titles 需要爬取数据的标题
	 * @return
	 * @author 黄智聪  2019年2月20日 下午2:06:46
	 */
	@RequestMapping("getBdsh5SearchInfoByTitle")
	@ResponseBody
	public JsonResult<List<SearchInfo>> getBdsh5SearchInfoByTitle(SearchInfoParam param){
		return crawlerService.getBdsh5SearchInfoByTitle(param);
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
	@RequestMapping("exportBdsh5Data")
	@ResponseBody
	public JsonResult<String> exportBdsh5Data(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HSSFWorkbook wb = crawlerService.exportBdsh5Data("", null);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=bdsh5.xlsx");
		response.flushBuffer();
		wb.write(response.getOutputStream());
		return JsonResult.successJsonResult();
	}
}

