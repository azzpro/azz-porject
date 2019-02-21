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

import com.azz.crawler.pojo.Bdsh5Title;

import lombok.Getter;

/**
 * <P>本地生活网数据</P>
 * @version 1.0
 * @author 黄智聪  2019年2月19日 下午2:55:43
 */
public class Bdsh5Data {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Bdsh5Data.class);

	private static final String URL = "http://guangdong.bdsh5.com/";
	
	@Getter
	private List<Bdsh5Title> allTitles;
	
	public Bdsh5Data() {
		this.initData();
	}
	
	public void initData() {
		this.allTitles = new ArrayList<>();
		try {
			LOGGER.info("正在初始化本地生活网数据...");
			Document doc = Jsoup.connect(URL).get();
			Elements allLies = doc.getElementsByClass("lie");
			for (Element eachLie : allLies) {
				Elements allBoxes = eachLie.getElementsByClass("box");
				for (Element eachBox : allBoxes) {
					String titleUrl = eachBox.select("h3 a").attr("href");// 每个大标题对应的url
					String title = eachBox.select("h3 a").html();// 每个大标题的名字，如房产服务、商务服务等
					System.out.println("*****大标题：" + title + "对应Url为：" + titleUrl + "*****");
					Elements itemsOfTitle = eachBox.getElementsByClass("item").select("a");// 每个大标题下有多个子标题
					List<Bdsh5Title> subTitles = new ArrayList<>();
					for (Element item : itemsOfTitle) {
						String itemUrl = item.attr("href");
						String itemTitle = item.html();
						System.out.println("子标题：" + itemTitle + "对应Url为：" + itemUrl);
						subTitles.add(new Bdsh5Title(false, itemTitle, itemUrl, null));
					}
					allTitles.add(new Bdsh5Title(true, title, titleUrl, subTitles));
					System.out.println("---------------------------------");
				}
			}
			LOGGER.info("初始化本地生活网数据完毕...");
		} catch (IOException e) {
			LOGGER.error("初始化本地生活网数据出错", e);
			throw new RuntimeException("初始化本地生活网数据出错", e);
		}
	}
	
}

