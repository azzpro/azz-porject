package com.azz.crawler.common;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.azz.crawler.config.IpPool;
import com.azz.crawler.pojo.ProxyIp;

@Component
public class ProxyHttpRequest {
	
	@Autowired
	private IpPool ipPool;

	private final static String HTTP = "http";
	
	public String doGetRequest(String url) {
		try {
			return doRequest(new HttpGet(url), false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * <p>执行请求</p>
	 * @param httpReq
	 * @param changeProxy 是否更换代理
	 * @return
	 * @author 黄智聪  2019年2月28日 下午7:05:18
	 */
	public String doRequest(HttpRequestBase httpReq, boolean changeProxy) {
		String result = null;
		try {
			// 设置代理
			setProxy(httpReq, changeProxy);
			// 设置请求头
			setHeaders(httpReq);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			// 执行请求
			CloseableHttpResponse httpResp = httpClient.execute(httpReq);
			HttpEntity entity = httpResp.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			httpResp.close();
			httpClient.close();
			httpReq.abort();
		}catch (IOException e) {// 若请求失败，换代理再试
			System.out.println("当前代理ip为:"+ipPool.getCurrentProxyIp()+"，代理请求失败，错误信息："+ e.getMessage());
			return doRequest(httpReq, true);
		}catch (Exception e) {
			System.out.println("当前代理ip为:"+ipPool.getCurrentProxyIp()+"，代理请求失败，错误信息："+ e.getMessage());
			return null;
		}
		return result;
	}
	
	/**
	 * 设置请求头
	 * 
	 * @param httpReq
	 */
	private void setHeaders(HttpRequestBase httpReq) {
		httpReq.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36");
		httpReq.addHeader("xxx", "xxx");
	}

	private void setProxy(HttpRequestBase httpReq, boolean changeProxy) {
		ProxyIp proxyIp = ipPool.getCurrentProxyIp();
		HttpHost proxy = new HttpHost(proxyIp.getIp(), proxyIp.getPort(), HTTP);
		if(changeProxy) {
			proxyIp = ipPool.getNextProxyIp();
			proxy = new HttpHost(proxyIp.getIp(), proxyIp.getPort(), HTTP);
		}
		RequestConfig reqConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(5000)
				.setConnectTimeout(5000) // 设置连接超时时间
				.setSocketTimeout(5000) // 设置读取超时时间
				.setExpectContinueEnabled(false)
				.setProxy(proxy)// 设置代理
				.setCircularRedirectsAllowed(true) // 允许多次重定向
				.build();
		httpReq.setConfig(reqConfig);
	}
}