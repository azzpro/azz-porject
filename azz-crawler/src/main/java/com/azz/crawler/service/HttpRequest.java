package com.azz.crawler.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public abstract class HttpRequest {
	
	public static void main(String[] args) {
		// String result = sendGetWithNoParams("http://shenzhen.baixing.com/qichebaoyang/");
		
		String result = httpGet("http://shenzhen.baixing.com/qichebaoyang/");
		//String result = getHtml("http://shenzhen.baixing.com/gongzuo/","116.209.55.4","9999");
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
//			System.getProperties().setProperty("proxySet", "true"); 
//			System.getProperties().setProperty("http.proxyHost", "112.85.131.170");
//			System.getProperties().setProperty("http.proxyPort", "9999");
			
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("116.23.226.111", 4206));
			
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection(proxy);
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
	
	/**
	 * java使用代理发送http请求
	 * @return
	 */
	public static String httpGet(String url) {
		String ip = "116.23.226.111";
		int port = 4206;
		String content = null;
		DefaultHttpClient httpclient = null;
		try {
			httpclient = new DefaultHttpClient();
			/** 设置代理IP **/
			HttpHost proxy = new HttpHost(ip, port);
			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
 
			HttpGet httpget = new HttpGet(url);
			
			httpget.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,1000*30);	//设置请求超时时间
			httpget.setHeader("Proxy-Authorization","Basic eXVsb3JlOll1bG9yZVByb3h5MjAxMzo=");
			httpget.setHeader("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1");
			httpget.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
 
			HttpResponse responses = httpclient.execute(httpget);
			HttpEntity entity = responses.getEntity();
			content = EntityUtils.toString(entity);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();	//关闭连接
		}
		return content;
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
			e.printStackTrace();
			entity = null;
		} catch (IOException e) {
			e.printStackTrace();
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
