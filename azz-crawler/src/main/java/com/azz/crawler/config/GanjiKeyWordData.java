/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月27日 上午11:02:26
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.config;

import java.io.FileOutputStream;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
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
                    String categoryArray[] = {"category business"};
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
           
           // 初始化地区信息
           
           
           
            LOGGER.info("初始化赶集网数据完毕...");
        } catch (IOException e) {
            LOGGER.error("初始化赶集网数据出错", e);
            throw new RuntimeException("初始化赶集网数据出错", e);
        }
    }
    
    
    public static void main(String[] args) {
        //HSSFWorkbook wb = new HSSFWorkbook();
        //HSSFSheet sheet = wb.createSheet("xxxxxx");
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        //HSSFRow row0 = sheet.createRow(0);
        //row0.createCell(0).setCellValue("学校名称");
        //row0.createCell(1).setCellValue("联系方式");
        //row0.createCell(2).setCellValue("专业信息");
        try {
            Document doc = Jsoup.connect(GANJIINDEXURL).get();
            Elements allLies = doc.select("div.all-city dl");
            System.out.println("所有dd标签的信息="+allLies);
            for (int i = 0; i < allLies.size(); i++) {
                Elements allCityLies = allLies.get(i).select("dd");
                System.out.println("allCityLies："+allCityLies);
                //Elements allCityDt = doc.select("div.all-city dd dt");
                //System.out.println("allCityLies="+allCityLies);
                
                /*for (int j = 0; j < allCityLies.size(); j++) {
                    Elements allCityDd = allLies.get(j).select("dd");
                    for (int k = 0; k < allCityDd.size(); k++) {
                        System.out.println(JSONObject.toJSONString(allCityDd.get(k).text().split(" ")));
                    }
                }*/
                
               /*for (int j = 0; j < allCityLies.size(); j++) {
                    System.out.println("市区链接："+allLies.get(j).select("a").attr("href"));
                    System.out.println("市区名字："+allLies.get(j).select("a").text());
                }*/
                //HSSFRow row = sheet.createRow(i);
                //String schoolName = allLies.get(i).select("dl.schoolinfo dd.course a h1").text();
                //System.out.println("schoolName="+schoolName);
                //row.createCell(0).setCellValue(schoolName);
                //String schoolNameUrl = allLies.get(i).getElementsByClass("keyword").select("a").attr("href");
                //Document shoolDetaildoc = Jsoup.connect(schoolNameUrl).get();
                //Elements details = shoolDetaildoc.select("div.commentL div.mpBox ul li");
                //Elements professionDetails = profession.get(i).select("div.item-info ul li");
                //String str = "";
                //for (int j = 4; j < details.size(); j++) {
                //        System.out.println(details.get(j).text());
                //        str += details.get(j).text()+"、";
                //}
                //row.createCell(1).setCellValue(str);
                
                //System.out.println("市区链接："+allLies.get(i).select("a").attr("href"));
            }
            
            try {
                //FileOutputStream output = new FileOutputStream("D:\\中国技校排名(全国技校排名,中专排名)【100所】.xls");
                //wb.write(output);
                //output.flush(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

