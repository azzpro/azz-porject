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
    //private static final String schoolURL = "http://www.zkbedu.com/school";
    
    @Getter
    private List<GanJiTitle> allTitles;
    
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
                Elements allBoxes1 = eachLie.getElementsByClass("col col-1");
                Elements allBoxes2 = eachLie.getElementsByClass("col col-2");
                Elements allBoxes4 = eachLie.getElementsByClass("col col-4");
                for (Element eachBox1 : allBoxes1) {
                    List<GanJiTitle> subTitles = new ArrayList<>();
                    String categoryArray[] = {"category house","category secondhand-car","category new-car","category car-service","category zhaoshang noborder"};
                    for (int i = 0; i < categoryArray.length; i++) {
                        String categoryStr = categoryArray[i];
                        Elements categoryVal = eachBox1.getElementsByClass(categoryStr);
                        for (Element eachBoxHouse : categoryVal) {   
                            Elements elements = eachBoxHouse.select("a");
                            for (Element element : elements) {
                                String url = URL + element.attr("href");
                                String name = element.select("a").html();
                                subTitles.add(new GanJiTitle(name, url, null));
                            }
                        }
                    }
                    allTitles.add(new GanJiTitle("第一列数据分析", null, subTitles));
                }
                
                for (Element eachBox2 : allBoxes2) {
                    List<GanJiTitle> subTitles = new ArrayList<>();
                    String categoryArray[] = {"category clearfix","category ershoushichang","category"};
                    for (int i = 0; i < categoryArray.length; i++) {
                        String categoryStr = categoryArray[i];
                        Elements categoryVal = eachBox2.getElementsByClass(categoryStr);
                        for (Element eachBoxHouse : categoryVal) {   
                            Elements elements = eachBoxHouse.select("a");
                            for (Element element : elements) {
                                String url = URL + element.attr("href");
                                String name = element.select("a").html();
                                subTitles.add(new GanJiTitle(name, url, null));
                            }
                        }
                    }
                    allTitles.add(new GanJiTitle("第二列数据分析", null, subTitles));
                }
                
                for (Element eachBox4 : allBoxes4) {
                    List<GanJiTitle> subTitles = new ArrayList<>();
                    String categoryArray[] = {"category noborder life","category noborder building","category noborder wedding","category tour","category business","category edu noborder"};
                    for (int i = 0; i < categoryArray.length; i++) {
                        String categoryStr = categoryArray[i];
                        Elements categoryVal = eachBox4.getElementsByClass(categoryStr);
                        for (Element eachBoxHouse : categoryVal) {   
                            Elements elements = eachBoxHouse.select("a");
                            for (Element element : elements) {
                                String url = URL + element.attr("href");
                                String name = element.select("a").html();
                                subTitles.add(new GanJiTitle(name, url, null));
                            }
                        }
                    }
                    allTitles.add(new GanJiTitle("第三列数据分析", null, subTitles));
                }
                
            }
            LOGGER.info("初始化赶集网数据完毕...");
        } catch (IOException e) {
            LOGGER.error("初始化赶集网数据出错", e);
            throw new RuntimeException("初始化赶集网数据出错", e);
        }
    }
    
}

