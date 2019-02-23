/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月19日 下午2:55:43
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.azz.crawler.pojo.BaixingTitle;

import lombok.Getter;

/**
 * <P>百姓网数据</P>
 * @version 1.0
 * @author 黄智聪  2019年2月19日 下午2:55:43
 */
public class BaixingKeyWordData {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaixingKeyWordData.class);

	private static final String URL = "http://shenzhen.baixing.com";
	
	@Getter
	private List<BaixingTitle> allTitles;
	
	public BaixingKeyWordData() {
		this.initData();
	}
	
	public void initData() {
		try {
			LOGGER.info("正在初始化百姓网数据...");
			Document doc = Jsoup.connect(URL).get();
			Elements sectionBoxs = doc.select("section.section-box");
			List<BaixingTitle> firstTitles = new ArrayList<>();
			for (Element eachSectionBox : sectionBoxs) {
				// 这是大标题的内容
				String fistTitleName = eachSectionBox.select("div.title a").first().html();
				String firstTitleUrl = URL + eachSectionBox.select("div.title a").first().attr("href");
				System.out.println("*******  一级标题：" + fistTitleName + "，对应url为：" + firstTitleUrl);
				Elements lis = eachSectionBox.select("ul.content li.category-group");
				List<BaixingTitle> secondTitles = new ArrayList<>();
				for (Element li : lis) {
					String secondTitle = li.select("div").html();
					String secondTitleName = null;
					String secondUrl = null;
					if(secondTitle.startsWith("<a")) {
						secondUrl = URL + li.select("div").select("a").attr("href");
						secondTitleName = li.select("div").select("a").html();
						System.out.println("****  二级标题：是一个a标签的标题-->" + secondTitleName + "，对应url为：" + secondUrl);
					}else {
						secondTitleName = secondTitle;
						System.out.println("****  二级标题：是一个普通标签标题-->" + secondTitleName + "，无对应url");
					}
					Element ul = li.select("ul.category-ul").first();
					Elements secondTitleContent = ul.children();// 2级标题对应的内容
					List<BaixingTitle> thirdTitles = new ArrayList<>();
					for (Element content : secondTitleContent) {
						String thirdTitleName = content.select("a").html(); // 3级标题
						String thirdTitleUrl = URL + content.select("a").attr("href");// 3级标题对应的url
						System.out.println("*  三级标题：" + thirdTitleName + "，对应url为：" + thirdTitleUrl);
						thirdTitles.add(new BaixingTitle(thirdTitleName, thirdTitleUrl, null));
					}
					secondTitles.add(new BaixingTitle(secondTitleName, secondUrl, thirdTitles));
				}
				firstTitles.add(new BaixingTitle(fistTitleName, firstTitleUrl, secondTitles));
			}
			// System.out.println(JSONObject.toJSONString(firstTitles));
			this.allTitles = firstTitles;
			LOGGER.info("初始化百姓网数据完毕...");
		} catch (IOException e) {
			LOGGER.error("初始化百姓网数据出错", e);
			throw new RuntimeException("初始化百姓网数据出错", e);
		}
	}
	
}

