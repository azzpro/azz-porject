/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月27日 上午11:02:26
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

import com.azz.crawler.pojo.CityInfo;
import com.azz.crawler.pojo.GanJiTitle;

import lombok.Getter;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 彭斌  2019年2月27日 上午11:02:26
 */
public class GanjiKeyWordData {
    private static final Logger LOGGER = LoggerFactory.getLogger(GanjiKeyWordData.class);

    private static final String URL = "http://sz.ganji.com/";
    
    private static final String GANJIINDEXURL = "http://www.ganji.com/index.htm";
    //private static final String schoolURL = "http://www.zkbedu.com/school";
    
    @Getter
    private List<GanJiTitle> allTitles;
    
    @Getter
    private List<CityInfo> cityInfos;
    
    public GanjiKeyWordData() {
        this.initData();
    }
    
	public void initData() {
		this.allTitles = new ArrayList<>();
		try {
			LOGGER.info("正在初始化赶集网数据...");
			Document doc = Jsoup.connect(URL).get();
			Elements allLies = doc.getElementsByClass("content-col");
			for (Element eachLie : allLies) {
				Elements allBoxes4 = eachLie.getElementsByClass("col col-4");
				for (Element eachBox4 : allBoxes4) {
					List<GanJiTitle> subTitles = new ArrayList<>();
					String categoryArray[] = { "category business" };
					for (int i = 0; i < categoryArray.length; i++) {
						String categoryStr = categoryArray[i];
						Elements categoryVal = eachBox4.getElementsByClass(categoryStr);
						for (Element eachBoxHouse : categoryVal) {
							Elements elements = eachBoxHouse.select("a");
							for (Element element : elements) {
								String url = element.attr("href");
								String name = element.select("a").html();
								subTitles.add(new GanJiTitle(name, url, null));
							}
						}
					}
					allTitles.add(new GanJiTitle("第三列数据分析", null, subTitles));
				}

			}

			LOGGER.info("正在初始化赶集网初始化地区信息...");
			// 初始化地区信息
			this.cityInfos = new ArrayList<>();
	        try {
	            doc = Jsoup.connect(GANJIINDEXURL).get();
	            Elements allCities = doc.select("div.all-city dl");
	            for (int i = 0; i < allCities.size(); i++) {
	                Elements allCityLies = allCities.get(i).select("dd");
	                for (Element eachLetter : allCityLies) {// 每一个字母对应的城市 比如A: 鞍山、安阳。。。
	                	Elements aList = eachLetter.getElementsByTag("a");// a标签集合
	                	for (Element a : aList) {
	                		cityInfos.add(new CityInfo(a.attr("href"), a.text()));
						}
					}
	            }
	            try {
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        } catch (Exception e) {
	            e.getMessage();
	        }
			LOGGER.info("初始化赶集网初始化地区信息完毕...");
		} catch (IOException e) {
			LOGGER.error("初始化赶集网初始化地区信息出错", e);
			throw new RuntimeException("初始化赶集网初始化地区信息出错", e);
		}
    }
    
}

