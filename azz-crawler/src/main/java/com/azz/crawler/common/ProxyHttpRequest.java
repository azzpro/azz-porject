package com.azz.crawler.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ProxyHttpRequest {
	// 代理服务器
	final static String proxyHost = "113.119.0.196";
	final static Integer proxyPort = 4206;
	
	public static String doGetRequest(String url) {
		try {
			HttpGet httpGet = new HttpGet(url);
			String result = doRequest(httpGet);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置请求头
	 * 
	 * @param httpReq
	 */
	private static void setHeaders(HttpRequestBase httpReq) {
		httpReq.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36");
		httpReq.addHeader("xxx", "xxx");
	}

	/**
	 * 执行请求
	 * 
	 * @param httpReq
	 * @return
	 */
	public static String doRequest(HttpRequestBase httpReq) {
		String result = new String();
		RequestConfig reqConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(10000) // 设置连接超时时间
				.setSocketTimeout(10000) // 设置读取超时时间
				.setExpectContinueEnabled(false).setProxy(new HttpHost(proxyHost, proxyPort, "http"))
				.setCircularRedirectsAllowed(true) // 允许多次重定向
				.build();
		httpReq.setConfig(reqConfig);
		try {
			// 设置请求头
			setHeaders(httpReq);
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			// 执行请求
			CloseableHttpResponse httpResp = httpClient.execute(httpReq);
			
			// 保存Cookie
			
			// 获取http code
			int statusCode = httpResp.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			
			HttpEntity entity = httpResp.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}

			httpResp.close();
			httpClient.close();
			httpReq.abort();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
}