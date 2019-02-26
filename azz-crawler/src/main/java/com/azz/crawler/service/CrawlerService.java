/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月19日 下午2:00:23
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azz.crawler.common.JsonResult;
import com.azz.crawler.config.BaixingKeyWordData;
import com.azz.crawler.config.Bdsh5KeyWordData;
import com.azz.crawler.pojo.Bdsh5Title;
import com.azz.crawler.pojo.vo.SearchInfo;


/**
 * <P>本地生活网爬虫</P>
 * @version 1.0
 * @author 黄智聪  2019年2月19日 下午2:00:23
 */
@Service
public class CrawlerService {

	@Autowired
	private Bdsh5KeyWordData bdsh5KeyWordData;// 本地生活网关键字数据
	
	@Autowired
	private BaixingKeyWordData baixingKeyWordData;// 百姓网关键字数据
	
	
	/*****************************************************************************************************************************/
	/*************************************************  本地生活网数据爬虫start  ******************************************************/
	/*****************************************************************************************************************************/
	
	/**
	 * 
	 * <p>查询本地生活所有标题</p>
	 * @return
	 * @author 黄智聪  2019年2月20日 下午1:40:28
	 */
	public JsonResult<List<Bdsh5Title>> getBdsh5Titles(){
		return JsonResult.successJsonResult(bdsh5KeyWordData.getAllTitles());
	}
	
	/**
	 * <p>爬虫登录</p>
	 * @param name
	 * @param pwd
	 * @return
	 * @author 彭斌  2019年2月23日 下午5:08:10
	 */
	public JsonResult<String> doLogin(String name, String pwd){
	    JsonResult<String> jsonResult = new JsonResult<String>();
	    if(null == name || null == pwd) {
	        throw new RuntimeException("账号密码异常");
        }
	    if(!"admin".equals(name) || !"5S3DyUZ8".equals(pwd)) {
	        throw new RuntimeException("账号密码错误");
	    }
	    return jsonResult;
	}
	
	/**
	 * 
	 * <p>根据标题查询本地生活网信息</p>
	 * @param titles 需要爬取数据的标题
	 * @return
	 * @author 黄智聪  2019年2月20日 下午2:06:46
	 */
	public JsonResult<Map<String, List<SearchInfo>>> getBdsh5SearchInfoByTitle(List<Bdsh5Title> titlesToSearch){
		if(titlesToSearch == null || titlesToSearch.size() == 0) {
			throw new RuntimeException("请选择需要爬取数据的标题");
		}
		Map<String, List<SearchInfo>> result = new LinkedHashMap<>();
		// 逐条查询
		for (Bdsh5Title eachTitle : titlesToSearch) {
			List<SearchInfo> searchInfos = new ArrayList<>();
			String titleName = eachTitle.getName();
			try {
				String url = eachTitle.getUrl();
				Document doc = Jsoup.connect(url).get();
				// 尾页连接地址，获取页数
				// 总页数，至少1页
				int totalPages = 1;
				try {
					String lastPageUrl = doc.getElementsByClass("page").select("a").last().attr("href");
					int start = lastPageUrl.indexOf("/p_");
					String pageStr = lastPageUrl.substring(start + 3, lastPageUrl.length() - 1);
					totalPages = Integer.parseInt(pageStr);
				} catch (Exception e) {// 若这里抛异常说明只有一页
					System.out.println("["+titleName+"]此页面只有一页数据");
				}
				System.out.println("准备爬取标题为[" + titleName + "]的数据，共" + totalPages + "页数据");
				for(int page = 1; page <= totalPages; page++) {
					System.out.println("正在爬取第"+ page +"页数据...");
					String pageSuffix = "p_" + page;
					String nextUrl = url + pageSuffix;
					Document newPageDoc = null;
					try {
						newPageDoc = Jsoup.connect(nextUrl).get();
					} catch (Exception e) {
						System.out.println("爬取["+titleName+"]时，在第"+ page +"页获取页面数据出错，跳过此页面，错误信息：" + e.getMessage());
						continue;
					}
					searchInfos.addAll(getEachPageInfo(newPageDoc));
				}
				result.put(titleName, searchInfos);
				System.out.println("标题为[" + titleName + "]的数据爬取完毕，共爬取了" + searchInfos.size() + "条数据");
				System.out.println("-------------------------------------------------");
			} catch (IOException e) {
				System.out.println("爬取["+titleName+"]时，获取页面数据出错，跳过此页面，错误信息：" + e.getMessage());
			}
		}
		return JsonResult.successJsonResult(result);
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
	public HSSFWorkbook exportBdsh5Data(Map<String, List<SearchInfo>> exportData) throws IOException {
		if(exportData == null || exportData.size() == 0) {
			throw new RuntimeException("无任何数据可导出");
		}
		Set<String> keys = exportData.keySet();
		HSSFWorkbook wb = new HSSFWorkbook();
		for (String key : keys) {// key为title
			String titleName = "";
			if(key.contains("/")) {
				titleName = key.replace("/", "、");
			}else {
				titleName = key;
			}
			// 建立新的sheet对象（excel的表单）
			HSSFSheet sheet = wb.createSheet(titleName);
			// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
			HSSFRow row0 = sheet.createRow(0);
			// 添加表头
			row0.createCell(0).setCellValue("联系人姓名");
			row0.createCell(1).setCellValue("联系人手机号");
			row0.createCell(2).setCellValue("标题");
			row0.createCell(3).setCellValue("描述");
			List<SearchInfo> infos = exportData.get(key);
			int i = 0;
			for (SearchInfo info : infos) {
				i++;
				HSSFRow row = sheet.createRow(i);
				row.createCell(0).setCellValue(info.getName());
				row.createCell(1).setCellValue(info.getPhoneNumber());
				row.createCell(2).setCellValue(info.getTitle());
				row.createCell(3).setCellValue(info.getDesc());
			}
		}
        wb.close();
		return wb;
	}

	/**
	 * 
	 * <p>爬取每页数据</p>
	 * @param doc
	 * @return
	 * @author 黄智聪  2019年2月20日 下午3:58:53
	 */
	private List<SearchInfo> getEachPageInfo(Document doc) {
		Element listPart = doc.getElementById("list");
		List<SearchInfo> searchInfos = new ArrayList<>();
		if(listPart != null) {
			// 每页的信息
			Elements eachPageInfos = listPart.select("ul li");
			if(eachPageInfos != null && eachPageInfos.size() > 0) {
				for (Element info : eachPageInfos) {
					SearchInfo si = new SearchInfo();
					// 每条记录的描述内容所在标签
					Elements eleOfTitle = info.select("h2 a");
					if(eleOfTitle != null && eleOfTitle.size() > 0) {
						si.setTitle(eleOfTitle.html());
					}
					// 每条记录的描述内容所在标签
					Elements eleOfContent = info.select(".cont p");
					if(eleOfContent != null && eleOfContent.size() > 0) {
						si.setDesc(eleOfContent.html());
					}
					// 联系电话所在标签
					Elements eleOfTel = info.getElementsByClass("t");
					if(eleOfTel != null && eleOfTel.size() > 0) {
						si.setPhoneNumber(eleOfTel.html());
					}
					// 联系人姓名所在标签
					Elements eleOfName = info.getElementsByClass("name");
					if(eleOfName != null && eleOfName.size() > 0) {
						si.setName(eleOfName.html());
					}
					searchInfos.add(si);
				}
			}
		}
		return searchInfos;
	}
	/***************************************************************************************************************************/
	/*************************************************  本地生活网数据爬虫end ******************************************************/
	/***************************************************************************************************************************/
	
	
	
	
	/*****************************************************************************************************************************/
	/*************************************************  本地生活网数据爬虫start  ******************************************************/
	/*****************************************************************************************************************************/
	
	
	
	
	
	
	
	
	/***************************************************************************************************************************/
	/*************************************************  本地生活网数据爬虫end  ******************************************************/
	/***************************************************************************************************************************/
}

