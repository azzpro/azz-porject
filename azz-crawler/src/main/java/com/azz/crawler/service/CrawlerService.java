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

import javax.servlet.http.HttpServletRequest;

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
import com.azz.crawler.common.ManagerHttpUtils;
import com.azz.crawler.common.ProxyHttpRequest;
import com.azz.crawler.config.BaixingKeyWordData;
import com.azz.crawler.config.Bdsh5KeyWordData;
import com.azz.crawler.config.GanjiKeyWordData;
import com.azz.crawler.pojo.BaixingTitle;
import com.azz.crawler.pojo.Bdsh5Title;
import com.azz.crawler.pojo.GanJiTitle;
import com.azz.crawler.pojo.User;
import com.azz.crawler.pojo.vo.BaoXianInfo;
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
	
	@Autowired
	private ProxyHttpRequest proxyHttpRequest;
	
	@Autowired
    private GanjiKeyWordData ganjiKeyWordData;// 赶集关键字数据
	
	
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
	 * <p>初始所有赶集基础数据</p>
	 * @return
	 * @author 彭斌  2019年2月27日 下午3:09:05
	 */
	public JsonResult<List<GanJiTitle>> getGanJiTitles(){
        return JsonResult.successJsonResult(ganjiKeyWordData.getAllTitles());
    }
	
	/**
	 * <p>爬虫登录</p>
	 * @param name
	 * @param pwd
	 * @return
	 * @author 彭斌  2019年2月23日 下午5:08:10
	 */
	public JsonResult<String> doLogin(String name, String pwd, HttpServletRequest req){
	    JsonResult<String> jsonResult = new JsonResult<String>();
	    if(null == name || null == pwd) {
	        throw new RuntimeException("账号密码异常");
        }
	    if(!"admin".equals(name) || !"5S3DyUZ8".equals(pwd)) {
	        throw new RuntimeException("账号密码错误");
	    }
	    
	    User user = new User();
	    user.setUserName(name);
	    ManagerHttpUtils.setUser(user, req);
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
					searchInfos.addAll(getBdsh5EachPageInfo(newPageDoc));
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
	
	public JsonResult<Map<String, List<BaoXianInfo>>> getGanjiSearchInfoByTitle(List<GanJiTitle> titlesToSearch){
        if(titlesToSearch == null || titlesToSearch.size() == 0) {
            throw new RuntimeException("请选择需要爬取数据的标题");
        }
        Map<String, List<BaoXianInfo>> result = new LinkedHashMap<>();
        // 逐页查询
        for (GanJiTitle ganJiTitle : titlesToSearch) {
            List<BaoXianInfo> searchInfos = new ArrayList<>();
            String titleName = ganJiTitle.getName();
            int totalPages = 1;
                String url = ganJiTitle.getUrl();
                Document doc = getGanJiDocument(url);
                try {
                    Element pageSize = doc.select("div.leftBox div.pageBox ul li").last().previousElementSibling();
                    totalPages = Integer.parseInt(pageSize.text());
                } catch (Exception e) {// 若这里抛异常说明只有一页
                    System.out.println("["+titleName+"]此页面只有一页数据");
                }
                
                for(int page = 1; page <= totalPages; page++) {
                    System.out.println("正在爬取第"+ page +"页数据...");
                    String pageSuffix = "o" + page;
                    String nextUrl = url + pageSuffix;
                    Document newPageDoc = null;
                    try {
                        System.out.println("nextUrl="+nextUrl);
                        newPageDoc = getGanJiDocument(nextUrl);
                    } catch (Exception e) {
                        System.out.println("爬取保险时，在第"+ page +"页获取页面数据出错，跳过此页面，错误信息：" + e.getMessage());
                        continue;
                    }
                    searchInfos.addAll(getGanjiBaoxianSeachPageInfo(newPageDoc));
                }
                result.put(titleName, searchInfos);
                System.out.println("标题为[" + titleName + "]的数据爬取完毕，共爬取了" + searchInfos.size() + "条数据");
                System.out.println("-------------------------------------------------");  
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
	private List<SearchInfo> getBdsh5EachPageInfo(Document doc) {
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
	
	/**
	 * <p>导出赶集网保险信息</p>
	 * @param doc
	 * @return
	 * @author 彭斌  2019年3月1日 下午2:09:12
	 */
	private List<BaoXianInfo> getGanjiBaoxianSeachPageInfo(Document doc) {
	    List<BaoXianInfo> searchInfos = new ArrayList<>();
        Elements lastPageLi = doc.select("div.leftBox div.list ul").last().children();
        System.out.println("开始爬取赶集网保险信息");
        if(lastPageLi != null) {
            // 每页的信息
            if(lastPageLi != null && lastPageLi.size() > 0) {
                for (Element info : lastPageLi) {
                    String baoxianUrl = info.select("div.txt p a").attr("href").trim();
                    String detailUrl = "http://sz.ganji.com"+baoxianUrl;
                    System.out.println("detailUrl="+detailUrl);
                    Document newPageDoc = null;
                    try {
                        newPageDoc = Jsoup.connect(detailUrl).get();
                        newPageDoc = getGanJiDocument(detailUrl);
                    } catch (Exception e) {
                        System.out.println("爬取保险时，在["+ baoxianUrl +"]页面数据出错，跳过此页面，错误信息：" + e.getMessage());
                        continue;
                    }
                    if(newPageDoc.getElementById("dzcontactus")!=null) {
                        Elements detailInfo = newPageDoc.getElementById("dzcontactus").select("div.con ul li");
                        BaoXianInfo si = new BaoXianInfo();
                        si.setTitle(detailInfo.get(0).select("li.fb").text());
                        String description = "";
                        String phone = "";
                        for (int i = 0; i < detailInfo.size(); i++) {
                            description += detailInfo.get(i).select("li").text();
                            if(detailInfo.get(i).select("li").text().contains("联系电话：")) {
                                phone = detailInfo.get(i).select("li p").text();
                                
                            }
                        }
                        si.setPhoneNumber(phone);
                        si.setDesc(description);
                        searchInfos.add(si);
                    }
                }
            }
        }
        return searchInfos;
    }
	/***************************************************************************************************************************/
	/*************************************************  本地生活网数据爬虫end ******************************************************/
	/***************************************************************************************************************************/

    private Document getGanJiDocument(String detailUrl) {
        Document newPageDoc = null;
        String ganJiErrorMsg = "访问过于频繁，本次访问做以下验证码校验";
        String ganjiErrorMsg2 = "ERR_ACCESS_DENIED";
        String resp = proxyHttpRequest.doGetRequest(detailUrl);
        if(resp.contains(ganJiErrorMsg)) {
            newPageDoc = Jsoup.parse(proxyHttpRequest.doGetRequest(detailUrl, true));
        } if(resp.contains(ganjiErrorMsg2)){
            newPageDoc = Jsoup.parse(proxyHttpRequest.doGetRequest(detailUrl, true));
        }else {
            newPageDoc = Jsoup.parse(resp);
        }
        return newPageDoc;
    }
    
    private Document getBaixingDocument(String detailUrl) {
        Document newPageDoc;
        String baixingErrorMsg = "s9verify_html?identity=spider_";
        String resp = proxyHttpRequest.doGetRequest(detailUrl);
        if(resp.contains(baixingErrorMsg)) {
            newPageDoc = Jsoup.parse(proxyHttpRequest.doGetRequest(detailUrl, true));
        }else {
            newPageDoc = Jsoup.parse(resp);
        }
        return newPageDoc;
    }
    
	
	/*****************************************************************************************************************************/
	/*************************************************  本地生活网数据爬虫start  ******************************************************/
	/*****************************************************************************************************************************/
	
	
	/**
	 * 
	 * <p>查询百姓网所有标题</p>
	 * @return
	 * @author 黄智聪  2019年2月20日 下午1:40:28
	 */
	public JsonResult<List<BaixingTitle>> getBaixingTitles(){
		return JsonResult.successJsonResult(baixingKeyWordData.getAllTitles());
	}
	
	/**
	 * 
	 * <p>根据标题查询百姓网信息</p>
	 * @param titles 需要爬取数据的标题
	 * @return
	 * @author 黄智聪  2019年2月20日 下午2:06:46
	 */
	public JsonResult<Map<String, List<SearchInfo>>> getBaixingSearchInfoByTitle(List<BaixingTitle> titlesToSearch){
		if(titlesToSearch == null || titlesToSearch.size() == 0) {
			throw new RuntimeException("请选择需要爬取数据的标题");
		}
		Map<String, List<SearchInfo>> result = new LinkedHashMap<>();
		// 逐条查询
		for (BaixingTitle eachTitle : titlesToSearch) {
			String titleName = eachTitle.getName();
			String url = eachTitle.getUrl();
			List<SearchInfo> searchInfos = new ArrayList<>();
			Document doc = getBaixingDocument(url);
			int totalPages = 1;
			try {
				Element lastPageLi = doc.select("section.listing-pager-section ul li").last().previousElementSibling();
				String pageStr = lastPageLi.select("a").html();
				totalPages = Integer.parseInt(pageStr);
			} catch (Exception e) {// 若这里抛异常说明只有一页
				System.out.println("["+titleName+"]此页面只有一页数据");
			}
			// 逐页查询
			for(int page = 1; page <= totalPages; page++) {
				System.out.println("正在爬取第"+ page +"页数据...");
				String pageSuffix = "?page=" + page;
				String nextUrl = url + pageSuffix;
				Document newPageDoc = null;
				try {
					newPageDoc = getBaixingDocument(nextUrl);
				} catch (Exception e) {
					System.out.println("爬取["+titleName+"]时，在第"+ page +"页获取页面数据出错，跳过此页面，错误信息：" + e.getMessage());
					continue;
				}
				searchInfos.addAll(getBaixingEachPageInfo(page, newPageDoc));
			}
			result.put(titleName, searchInfos);
			System.out.println("标题为[" + titleName + "]的数据爬取完毕，共爬取了" + searchInfos.size() + "条数据");
			System.out.println("-------------------------------------------------");
		}
		return JsonResult.successJsonResult(result);
	}
	
	/**
	 * 
	 * <p>爬取每页数据</p>
	 * @param doc
	 * @return
	 * @author 黄智聪  2019年2月20日 下午3:58:53
	 */
	private List<SearchInfo> getBaixingEachPageInfo(int page, Document doc) {
		Elements listPart = doc.select("ul li.listing-ad");
		List<SearchInfo> searchInfos = new ArrayList<>();
		if(listPart != null && listPart.size() > 0) {
			int i = 0 ;
			for (Element info : listPart) {// 当前页中的每一个栏目
				i++;
				System.out.println("正在处理第" + page + "页的第" + i + "条栏目");
				String detailUrl = info.select("a").attr("href");
				Document newPageDoc = null;
				SearchInfo si = new SearchInfo();
				StringBuffer otherDesc = new StringBuffer();
				try {
					newPageDoc = getBaixingDocument(detailUrl);
				} catch (Exception e) {
					System.out.println("爬取url["+detailUrl+"]的第"+page+"页时，获取页面数据出错，跳过此条数据，错误信息：" + e.getMessage());
					continue;
				}
				String phoneNumber = "无";
				try {
					phoneNumber = newPageDoc.getElementById("mobileNumber").children().first().html();
					System.out.println("title:content-->"+"联系：" + phoneNumber);
				} catch (Exception e) {
					System.out.println("无手机号可获取");
					continue;// 无手机号可获取，直接放弃此页数据
				}
				si.setPhoneNumber(phoneNumber);
				Elements items = newPageDoc.select("div.viewad-meta div.viewad-meta-item");
				if(items != null && items.size() > 0) {
					for (Element item : items) {
						Elements labels = item.select("label");
						if(labels != null && labels.size() > 0) {
							int count = labels.size();
							if(count == 2) {
								String title = labels.get(0).html();// 标题
								String content = labels.get(1).html();// 内容
								System.out.println("title:content-->"+title + content);
								otherDesc.append(title + content+"  ");
							}else {
								String title = labels.get(0).html();// 标题
								String tagName = labels.get(0).nextElementSibling().tagName();
								String content = "";
								if("div".equals(tagName) ) {
									boolean isGetContent = false;
									Elements span = labels.get(0).nextElementSibling().select("span");
									if(span!=null && span.size()>0) {
										content = labels.get(0).nextElementSibling().select("span").html().replace("\n", " ");
										isGetContent = true;
									}
									Elements a = labels.get(0).nextElementSibling().select("a");
									if(a!=null && a.size()>0) {
										content = labels.get(0).nextElementSibling().select("a").html().replace("\n", "");
										isGetContent = true;
									}
									if(!isGetContent){
										content = labels.get(0).nextElementSibling().select("label").html().replace("\n", " ");
									}
									System.out.println("title:content-->"+title + content);
								}else if("span".equals(tagName)){
									Elements a = labels.get(0).nextElementSibling().select("a");
									if(a!=null && a.size()>0) {
										content = labels.get(0).nextElementSibling().select("a").html();
									}else {
										content = labels.get(0).nextElementSibling().html();
									}
									System.out.println("title:content-->"+title + content);
								}else if("a".equals(tagName)){
									content = labels.get(0).nextElementSibling().html();
									System.out.println("title:content-->"+title + content);
								}else {
									// 未知情况
									System.out.println("未发现此标签"+"["+tagName+"]");
									continue;
								}
								otherDesc.append(title + content+"  ");
							}
						}
					}
				}
				Elements items2 = newPageDoc.select("div.viewad-meta2 div.viewad-meta2-item");
				if(items2 != null && items2.size() > 0) {
					for (Element item : items2) {
						Elements labels = item.select("label");
						if(labels != null && labels.size() > 0) {
							int count = labels.size();
							if(count == 2) {
								String title = labels.get(0).html();// 标题
								String content = labels.get(1).html();// 内容
								System.out.println("title:content-->"+title + content);
								otherDesc.append(title + content+"  ");
							}else {
								String title = labels.get(0).html();// 标题
								String tagName = labels.get(0).nextElementSibling().tagName();
								String content = "";
								if("div".equals(tagName) ) {
									boolean isGetContent = false;
									Elements span = labels.get(0).nextElementSibling().select("span");
									if(span!=null && span.size()>0) {
										content = labels.get(0).nextElementSibling().select("span").html().replace("\n", " ");
										isGetContent = true;
									}
									Elements a = labels.get(0).nextElementSibling().select("a");
									if(a!=null && a.size()>0) {
										content = labels.get(0).nextElementSibling().select("a").html().replace("\n", "");
										isGetContent = true;
									}
									if(!isGetContent){
										content = labels.get(0).nextElementSibling().select("label").html().replace("\n", " ");
									}
									System.out.println("title:content-->"+title + content);
								}else if("span".equals(tagName)){
									Elements a = labels.get(0).nextElementSibling().select("a");
									if(a!=null && a.size()>0) {
										content = labels.get(0).nextElementSibling().select("a").html().replace("<br>", "").replace("\n", "");
									}else {
										content = labels.get(0).nextElementSibling().html().replace("<br>", "").replace("\n", "");
									}
									System.out.println("title:content-->"+title + content);
								}else if("a".equals(tagName)){
									content = labels.get(0).nextElementSibling().html().replace("<br>", "").replace("\n", "");
									System.out.println("title:content-->"+title + content);
								}else {
									// 未知情况
									System.out.println("未发现此标签"+"["+tagName+"]");
									continue;
								}
								otherDesc.append(title + content+"  ");
							}		
						}
					}
				}
				Elements items3 = newPageDoc.select("section.viewad-meta ul li");
				if(items3 != null && items3.size() > 0) {
					for (Element item : items3) {
						Elements labels = item.select("label");
						if(labels != null && labels.size() > 0) {
							int count = labels.size();
							if(count == 2) {
								String title = labels.get(0).html().replace("<br>", "").replace("\n", "");// 标题
								String content = labels.get(1).html().replace("<br>", "").replace("\n", "");// 内容
								System.out.println("title:content-->"+title + content);
								otherDesc.append(title + content+"  ");
							}else {
								String title = labels.get(0).html().replace("<br>", "").replace("\n", "");// 标题
								String tagName = labels.get(0).nextElementSibling().tagName();
								String content = "";
								if("div".equals(tagName) ) {
									boolean isGetContent = false;
									Elements span = labels.get(0).nextElementSibling().select("span");
									if(span!=null && span.size()>0) {
										content = labels.get(0).nextElementSibling().select("span").html().replace("\n", " ");
										isGetContent = true;
									}
									Elements a = labels.get(0).nextElementSibling().select("a");
									if(a!=null && a.size()>0) {
										content = labels.get(0).nextElementSibling().select("a").html().replace("\n", "");
										isGetContent = true;
									}
									if(!isGetContent){
										content = labels.get(0).nextElementSibling().select("label").html().replace("\n", " ");
									}
									System.out.println("title:content-->"+title + content);
								}else if("span".equals(tagName)){
									Elements a = labels.get(0).nextElementSibling().select("a");
									if(a!=null && a.size()>0) {
										content = labels.get(0).nextElementSibling().select("a").html().replace("\n", " ");
									}else {
										content = labels.get(0).nextElementSibling().html().replace("<br>", "").replace("\n", "");
									}
									System.out.println("title:content-->"+title + content);
								}else if("a".equals(tagName)){
									content = labels.get(0).nextElementSibling().html().replace("<br>", "").replace("\n", "");
									System.out.println("title:content-->"+title + content);
								}else {
									// 未知情况
									System.out.println("未发现此标签"+"["+tagName+"]");
									continue;
								}
								otherDesc.append(title + content+"  ");
							}		
						}
					}
				}
				si.setOtherDesc(otherDesc.toString());
				/*
				Elements descs = newPageDoc.select("section.viewad-description div.viewad-text");
				if(descs != null && descs.size() > 0) {
					String description = descs.get(0).nextElementSibling().html().replace("<br>", "").replace("\n", "");
					System.out.println("*************"+description);
					si.setDesc(description);
				}
				Elements titleEle = newPageDoc.select(".viewad-title");
				if(titleEle != null && titleEle.size() > 0) {
					String title = titleEle.get(0).nextElementSibling().html().replace("<br>", "").replace("\n", "");
					System.out.println("*************"+title);
					si.setTitle(title);
				}
				*/
				searchInfos.add(si);
			}
		}else {
			System.out.println("此页[第"+page+"页]无符合的数据可处理");
		}
		return searchInfos;
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
	public HSSFWorkbook exportBaixingData(Map<String, List<SearchInfo>> exportData) throws IOException {
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
			//row0.createCell(0).setCellValue("标题");
			row0.createCell(0).setCellValue("联系人手机号");
			//row0.createCell(2).setCellValue("描述");
			row0.createCell(1).setCellValue("其他描述");
			List<SearchInfo> infos = exportData.get(key);
			int i = 0;
			for (SearchInfo info : infos) {
				i++;
				HSSFRow row = sheet.createRow(i);
				//row.createCell(0).setCellValue(info.getTitle());
				row.createCell(0).setCellValue(info.getPhoneNumber());
				//row.createCell(2).setCellValue(info.getDesc());
				row.createCell(1).setCellValue(info.getOtherDesc());
			}
		}
        wb.close();
		return wb;
	}

	/**
	 * <p>导出赶集网保险信息</p>
	 * @param exportData
	 * @return
	 * @throws IOException
	 * @author 彭斌  2019年3月1日 下午2:13:00
	 */
	public HSSFWorkbook exportGanJiBaoxianData(Map<String, List<BaoXianInfo>> exportData) throws IOException {
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
            row0.createCell(0).setCellValue("保险公司名称");
            row0.createCell(1).setCellValue("联系电话");
            row0.createCell(2).setCellValue("描述");
            List<BaoXianInfo> infos = exportData.get(key);
            int i = 0;
            for (BaoXianInfo info : infos) {
                i++;
                HSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(info.getTitle());
                row.createCell(1).setCellValue(info.getPhoneNumber());
                row.createCell(2).setCellValue(info.getDesc());
            }
        }
        wb.close();
        return wb;
    }

	/***************************************************************************************************************************/
	/*************************************************  本地生活网数据爬虫end  ******************************************************/
    
}
