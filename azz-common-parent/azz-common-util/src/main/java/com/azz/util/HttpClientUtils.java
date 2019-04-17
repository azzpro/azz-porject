/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年4月16日 下午3:09:52
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

/**
 * <P>http请求工具类</P>
 * @version 1.0
 * @author 黄智聪  2019年4月16日 下午3:09:52
 */
public class HttpClientUtils {
	
	/**
	 * 
	 * <p>发送get请求</p>
	 * @param httpUrl
	 * @return
	 * @author 黄智聪  2019年4月16日 下午4:55:25
	 */
    public static String sendHttpGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建HttpGet
		return sendHttp(httpGet);
    }
    
    /**
     * 
     * <p>发送 post请求 发送json数据</p>
     * @param httpUrl  地址
     * @param paramsJson  参数(格式 json)
     * @return
     * @author 黄智聪  2019年4月16日 下午4:56:17
     */
    public static String sendHttpPostJson(String httpUrl, String paramsJson) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            // 设置参数
            if (!StringUtils.isEmpty(paramsJson)) {
                StringEntity stringEntity = new StringEntity(paramsJson, "UTF-8");
                stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                httpPost.setEntity(stringEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttp(httpPost);
    }
    
    /**
     * 发送请求
     * 
     * @param httpRequest http请求
     * @return
     */
	private static String sendHttp(HttpRequestBase httpRequest) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		// 响应内容
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			// 配置请求信息
			httpRequest.setConfig(getReqConfig());
			// 执行请求
			response = httpClient.execute(httpRequest);
			// 得到响应实例
			HttpEntity entity = response.getEntity();
			// 判断响应状态
			if (response.getStatusLine().getStatusCode() >= 300) {
				throw new Exception(
						"HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
			}
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				responseContent = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
	
	private static RequestConfig getReqConfig() {
		RequestConfig reqConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(5000)
				.setConnectTimeout(5000) // 设置连接超时时间
				.setSocketTimeout(5000) // 设置读取超时时间
				.setExpectContinueEnabled(false)
				.setCircularRedirectsAllowed(true) // 允许多次重定向
				.build();
		return reqConfig;
	}
}

