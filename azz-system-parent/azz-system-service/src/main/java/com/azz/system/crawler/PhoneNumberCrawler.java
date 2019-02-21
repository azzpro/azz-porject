/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月18日 上午10:04:45
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.system.crawler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;
import com.azz.util.OkHttpUtil;
import com.azz.util.StringUtils;

/**
 * <P>各大工业网站手机号爬虫程序</P>
 * @version 1.0
 * @author 沃德基尔邦硬  2019年2月18日 上午10:04:45
 */
public class PhoneNumberCrawler {
	
	public static void main(String[] args) throws Exception {
		
		/*
		HSSFWorkbook wb = zhiZaoYunCrawler();
		FileOutputStream output=new FileOutputStream("D:\\制造云手机号.xlsx");  
        wb.write(output);//写入磁盘  
        */
        
		
		HSSFWorkbook wb = faShaoYouCrawler();
		FileOutputStream output=new FileOutputStream("D:\\发烧友论坛手机号.xlsx");  
        wb.write(output);//写入磁盘  
        
        
	}

	/**
	 * 
	 * <p>制造云</p>
	 * @author 黄智聪  2019年2月18日 上午10:09:13
	 * @throws IOException 
	 */
	public static HSSFWorkbook zhiZaoYunCrawler() throws IOException {
		String url = "http://engineer.zhizaoyun.com/api/applicationform/GetModelForView?id=";
		int max_id = 9294; // 目前发现能查到的最大id数  TOTO
		int success_code = 1;
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet("phoneNumber");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row0 = sheet.createRow(0);
		// 添加表头
        row0.createCell(0).setCellValue("手机号/邮箱");
        // 放入set中可去重复值
        Set<String> phoneNumberOrEmails = new HashSet<>();
		for (int id = 1; id <= max_id; id++) {
			System.out.println("id已处理到：" + id);
			try {
				String result = OkHttpUtil.get(url + id);
				JSONObject json = JSONObject.parseObject(result);
				int code = json.getJSONObject("status").getInteger("code");
				if(success_code == code) {// 如果返回成功
					JSONObject data = json.getJSONObject("data");
					if(data != null) {
						String userName = data.getString("userName");
						// 若为手机号，则记录到excel
						if(userName != null && (StringUtils.isPhoneNumber(userName) || StringUtils.isEmail(userName))) {
							phoneNumberOrEmails.add(userName);
						}
					}
				}else {// 非请求成功，执行下一次循环
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int i = 0;// excel初始行数
		for (String phoneNumberOrEmail : phoneNumberOrEmails) {
			i++;
			HSSFRow row = sheet.createRow(i);
			row.createCell(0).setCellValue(phoneNumberOrEmail);
		}	
		wb.close();
		return wb;
	}
	
	/**
	 * 
	 * <p>发烧友</p>
	 * @author 黄智聪  2019年2月18日 上午10:09:13
	 * @throws IOException 
	 */
	public static HSSFWorkbook faShaoYouCrawler() throws IOException {
		int max_page = 25; // 论坛页数
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet("phoneNumber");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row0 = sheet.createRow(0);
		// 添加表头
        row0.createCell(0).setCellValue("手机号/邮箱");
        // 放入set中可去重复值
        Set<String> phoneNumberOrEmails = new HashSet<>();
		for (int page = 1; page <= max_page; page++) {
			String url = "http://bbs.elecfans.com/zhuti_jiqiren_" + page + ".html";
			System.out.println("正在处理第" + page + "页数据");
			try {
				Document doc = Jsoup.connect(url).get();
				Element threadlist = doc.getElementById("threadlist");
				Elements eles = threadlist.select("*[id^=normalthread]");
				for (Element element : eles) {
					try {
						String eachUrl = element.getElementsByTag("a").attr("href");
						// 处理每个url
						Document eachPageDoc = Jsoup.connect(eachUrl).get();
						Element pageNum_bottom = eachPageDoc.getElementById("pageNum_bottom");
						Elements spans = pageNum_bottom.getElementsByTag("span");
						if(spans.size() == 0) {// 若找不到总页数的描述，则说明只有一页
							Element postlist = eachPageDoc.getElementById("postlist");
							if(postlist != null) {
								Elements es = postlist.select("span a");
								for (Element e : es) {
									String userName = e.html().trim();
									if(StringUtils.isPhoneNumber(userName) || StringUtils.isEmail(userName)) {
										System.out.println(userName);
										phoneNumberOrEmails.add(userName);
									}
								}
							}
						}else {
							Element pageEle = spans.get(0);
							String pageContent = pageEle.attr("title");// 内容为：共 xx 页
							int start = pageContent.indexOf(" ");
							int end = pageContent.indexOf(" 页");
							String p = pageContent.substring(start + 1, end);
							int totalPages = Integer.parseInt(p);// 获取到总页数
							int index = eachUrl.indexOf("_1_1.html");
							String prefixUrl = eachUrl.substring(0, index);
							for (int j = 1; j <= totalPages; j++) {
								String suffix = "_" + j + "_1.html";
								String nextPageUrl = prefixUrl + suffix;
								Document d = Jsoup.connect(nextPageUrl).get();
								Element postlist = d.getElementById("postlist");
								if(postlist != null) {
									Elements es = postlist.select("span a");
									for (Element e : es) {
										String userName = e.html().trim();
										if(StringUtils.isPhoneNumber(userName) || StringUtils.isEmail(userName)) {
											System.out.println(userName);
											phoneNumberOrEmails.add(userName);
										}
									}
								}
							}
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				int i = 0;// excel初始行数
				for (String phoneNumberOrEmail : phoneNumberOrEmails) {
					i++;
					HSSFRow row = sheet.createRow(i);
					row.createCell(0).setCellValue(phoneNumberOrEmail);
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		wb.close();
		return wb;
	}

}

