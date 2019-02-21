/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月19日 下午2:00:23
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azz.core.common.JsonResult;
import com.azz.crawler.config.Bdsh5Data;
import com.azz.crawler.pojo.Bdsh5Title;
import com.azz.crawler.pojo.bo.SearchInfoParam;
import com.azz.crawler.pojo.vo.SearchInfo;


/**
 * <P>本地生活网爬虫</P>
 * @version 1.0
 * @author 黄智聪  2019年2月19日 下午2:00:23
 */
@Service
public class CrawlerService {
	
	@Autowired
	private Bdsh5Data bdsh5Data;
	
	/**
	 * 
	 * <p>查询本地生活所有标题</p>
	 * @return
	 * @author 黄智聪  2019年2月20日 下午1:40:28
	 */
	public JsonResult<List<Bdsh5Title>> getBdsh5Titles(){
		return JsonResult.successJsonResult(bdsh5Data.getAllTitles());
	}
	
	/**
	 * 
	 * <p>根据标题查询本地生活网信息</p>
	 * @param titles 需要爬取数据的标题
	 * @return
	 * @author 黄智聪  2019年2月20日 下午2:06:46
	 */
	public JsonResult<List<SearchInfo>> getBdsh5SearchInfoByTitle(SearchInfoParam param){
		List<Bdsh5Title> titlesToSearch = param.getTitlesToSearch();
		List<SearchInfo> searchInfos = new ArrayList<>();
		// 逐条查询
		for (Bdsh5Title eachTitle : titlesToSearch) {
			try {
				String titleName = eachTitle.getName();
				String url = eachTitle.getUrl();
				Document doc = Jsoup.connect(url).get();
				// 尾页连接地址，获取页数
				String lastPageUrl = doc.getElementsByClass("page").select("a").last().attr("href");
				int start = lastPageUrl.indexOf("/p_");
				String pageStr = lastPageUrl.substring(start + 3, lastPageUrl.length() - 1);
				// 总页数
				int totalPages = Integer.parseInt(pageStr);
				System.out.println("准备爬取标题为[" + titleName + "]的数据，共" + totalPages + "页数据");
				for(int page = 1; page <= totalPages; page++) {
					System.out.println("正在爬取第"+ page +"页数据...");
					String pageSuffix = "p_" + page;
					String nextUrl = url + pageSuffix;
					Document newPageDoc = Jsoup.connect(nextUrl).get();
					searchInfos.addAll(getEachPageInfo(newPageDoc));
				}
				System.out.println("标题为[" + titleName + "]的数据爬取完毕...");
				System.out.println("共爬取了" + searchInfos.size() + "条数据");
				System.out.println("-------------------------------------------------");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return JsonResult.successJsonResult(searchInfos);
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
	public HSSFWorkbook exportBdsh5Data(String title, List<SearchInfo> infos) throws IOException {
		if(infos == null || infos.size() == 0) {
			throw new RuntimeException("无任何数据可导出");
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet(title);
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row0 = sheet.createRow(0);
		// 添加表头
        row0.createCell(0).setCellValue("联系人姓名");
        row0.createCell(1).setCellValue("联系人手机号");
        row0.createCell(2).setCellValue("标题");
        row0.createCell(3).setCellValue("描述");
        int i = 0;
        for (SearchInfo info : infos) {
        	i++;
			HSSFRow row = sheet.createRow(i);
			row.createCell(0).setCellValue(info.getName());
			row.createCell(1).setCellValue(info.getPhoneNumber());
			row.createCell(2).setCellValue(info.getTitle());
			row.createCell(3).setCellValue(info.getDesc());
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
	
	
}

