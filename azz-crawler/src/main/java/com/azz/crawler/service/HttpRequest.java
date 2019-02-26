package com.azz.crawler.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public abstract class HttpRequest {

	public static boolean proxyHost = false;
	public static boolean proxyPort = false;
	public int port = 80;

	public static void main(String[] args) {
		String result = sendGetWithNoParams("http://shenzhen.baixing.com/gongzuo/");
		System.out.println(result);
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGetWithNoParams(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			// 如果不设置，只要代理IP和代理端口正确,此项不设置也可以
			System.getProperties().setProperty("proxySet", "true"); 
			System.getProperties().setProperty("http.proxyHost", "112.85.131.170");
			System.getProperties().setProperty("http.proxyPort", "9999");
			
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/5.0 (Linux; U; Android 4.2.2; zh-cn; SCH-P709 Build/JDQ39) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	public static String getRandomIp(){
		//ip范围
		int[][] range = {{607649792,608174079},//36.56.0.0-36.63.255.255
				{1038614528,1039007743},//61.232.0.0-61.237.255.255
				{1783627776,1784676351},//106.80.0.0-106.95.255.255
				{2035023872,2035154943},//121.76.0.0-121.77.255.255
				{2078801920,2079064063},//123.232.0.0-123.235.255.255
				{-1950089216,-1948778497},//139.196.0.0-139.215.255.255
				{-1425539072,-1425014785},//171.8.0.0-171.15.255.255
				{-1236271104,-1235419137},//182.80.0.0-182.92.255.255
				{-770113536,-768606209},//210.25.0.0-210.47.255.255
				{-569376768,-564133889}, //222.16.0.0-222.95.255.255
		};
		
		Random rdint = new Random();
		int index = rdint.nextInt(10);
		String ip = num2ip(range[index][0]+new Random().nextInt(range[index][1]-range[index][0]));
		return ip;
	}
	
	public static String num2ip(int ip) {
		int [] b=new int[4] ;
		String x = "";
		
		b[0] = (int)((ip >> 24) & 0xff);
		b[1] = (int)((ip >> 16) & 0xff);
		b[2] = (int)((ip >> 8) & 0xff);
		b[3] = (int)(ip & 0xff);
		x=Integer.toString(b[0])+"."+Integer.toString(b[1])+"."+Integer.toString(b[2])+"."+Integer.toString(b[3]); 
		
		return x; 
	}
	
	 public static String getHtml( String url, String ip, String port) {
	        String entity = null;
	        CloseableHttpClient httpClient = HttpClients.createDefault();

	        //设置代理访问和超时处理
	        System.out.println("此时线程: " + Thread.currentThread().getName() + " 爬取所使用的代理为: "
	                + ip + ":" + port);
	        HttpHost proxy = new HttpHost(ip, Integer.parseInt(port));
	        RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(3000).
	                setSocketTimeout(3000).build();
	        HttpGet httpGet = new HttpGet(url);
	        httpGet.setConfig(config);

	        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
	                "q=0.9,image/webp,*/*;q=0.8");
	        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
	        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
	        httpGet.setHeader("Cache-Control", "no-cache");
	        httpGet.setHeader("Connection", "keep-alive");
	        httpGet.setHeader("Host", "www.xicidaili.com");
	        httpGet.setHeader("Pragma", "no-cache");
	        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
	        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
	                "(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

	        try {
	            //客户端执行httpGet方法，返回响应
	            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

	            //得到服务响应状态码
	            if (httpResponse.getStatusLine().getStatusCode() == 200) {
	                entity = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
	            }

	            httpResponse.close();
	            httpClient.close();
	        } catch (ClientProtocolException e) {
	            entity = null;
	        } catch (IOException e) {
	            entity = null;
	        }

	        return entity;
	    }

	    //对上一个方法的重载，使用本机ip进行网站爬取
	    public static String getHtml(String url) {
	        String entity = null;
	        CloseableHttpClient httpClient = HttpClients.createDefault();

	        //设置超时处理
	        RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).
	                setSocketTimeout(3000).build();
	        HttpGet httpGet = new HttpGet(url);
	        httpGet.setConfig(config);

	        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
	                "q=0.9,image/webp,*/*;q=0.8");
	        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
	        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
	        httpGet.setHeader("Cache-Control", "no-cache");
	        httpGet.setHeader("Connection", "keep-alive");
	        httpGet.setHeader("Host", "www.xicidaili.com");
	        httpGet.setHeader("Pragma", "no-cache");
	        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
	        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
	                "(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

	        try {
	            //客户端执行httpGet方法，返回响应
	            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

	            //得到服务响应状态码
	            if (httpResponse.getStatusLine().getStatusCode() == 200) {
	                entity = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
	            }

	            httpResponse.close();
	            httpClient.close();
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return entity;
	    }
}
