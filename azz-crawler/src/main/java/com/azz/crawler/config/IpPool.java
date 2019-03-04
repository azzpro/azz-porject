/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月28日 下午5:33:54
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.azz.crawler.pojo.ProxyIp;

import lombok.Getter;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年2月28日 下午5:33:54
 */
public class IpPool {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IpPool.class);
	
	private static final String GET_IPS_URL = "http://mvip.piping.mogumiao.com/proxy/api/get_ip_bs?appKey=0f6f79066bc3495293767cec67a1b852&count=3&expiryDate=0&format=1&newLine=2";
	
	private static final String SUCCESS_CODE = "0";
	
	@Getter
	private Map<String, ProxyIp> proxyIps;
	
	@Getter
	private ProxyIp currentProxyIp;
	
	public IpPool() {
		initIps();
	}
	
	@SuppressWarnings("static-access")
	public void initIps(){
		LOGGER.info("正在初始化代理ip池的数据...");
		String result = doGet(GET_IPS_URL);
		List<ProxyIp> ipList = null;
		
		if(result != null) {
			JSONObject res = JSONObject.parseObject(result);
			String code = res.getString("code");
			if(SUCCESS_CODE.equals(code)) {// 请求成功
				JSONArray msg = res.getJSONArray("msg");
				ipList = msg.parseArray(msg.toJSONString(), ProxyIp.class);
			}else {
				throw new RuntimeException("获取ip代理池出错，请尝试重启应用重新获取");
			}
		}
		
		/*
		ProxyIp[] ipList = { new ProxyIp("36.24.42.249", 44353), 
				new ProxyIp("42.4.215.217", 38076),
				new ProxyIp("110.18.152.109", 45200), 
				new ProxyIp("27.22.95.249", 46588),
				new ProxyIp("42.248.202.20", 21491),
				new ProxyIp("116.210.34.237", 46968),
				new ProxyIp("125.119.35.227", 32082),
				new ProxyIp("121.231.92.207", 36449),
				new ProxyIp("49.76.87.207", 24623),
				new ProxyIp("114.226.166.52", 24822) };*/
		
		Map<String, ProxyIp> ips = new HashMap<>();
		for (ProxyIp proxyIp : ipList) {
			ips.put(proxyIp.getIp(), proxyIp);
		}
		if(ips.size() == 0) {
			LOGGER.info("代理ip池数量为空，初始化失败...");
			throw new RuntimeException("代理ip池数量为空");
		}
		this.proxyIps = ips;
		Set<String> keys = proxyIps.keySet();
		for (String ipKey : keys) {// 随便取一条代理ip即可
			this.currentProxyIp = proxyIps.get(ipKey);// 当前代理ip
			break;
		}
		LOGGER.info("代理ip池已初始化完毕...");
	}
	
	/**
	 * 
	 * <p>刷新代理ip池</p>
	 * @author 黄智聪  2019年3月4日 下午2:21:04
	 */
	@SuppressWarnings("static-access")
	public void refreshProxyIps() {
		String result = doGet(GET_IPS_URL);
		List<ProxyIp> ipList = null;
		if(result != null) {
			JSONObject res = JSONObject.parseObject(result);
			String code = res.getString("code");
			if(SUCCESS_CODE.equals(code)) {// 请求成功
				JSONArray msg = res.getJSONArray("msg");
				ipList = msg.parseArray(msg.toJSONString(), ProxyIp.class);
			}else {
				throw new RuntimeException("获取ip代理池出错，请尝试重启应用重新获取");
			}
		}
		Map<String, ProxyIp> ips = new HashMap<>();
		for (ProxyIp proxyIp : ipList) {
			ips.put(proxyIp.getIp(), proxyIp);
		}
		if(ips.size() == 0) {
			LOGGER.info("刷新代理ip池出错，无可用代理ip");
			throw new RuntimeException("刷新代理ip池出错，无可用代理ip");
		}
		this.proxyIps = ips;
	}
	
	/**
	 * 
	 * <p>获取下一个代理ip</p>
	 * @return
	 * @author 黄智聪  2019年3月4日 下午2:20:48
	 */
	public ProxyIp getNextProxyIp() {
		proxyIps.remove(this.currentProxyIp.getIp());// 移除掉当前key对应的ip
		if(proxyIps.isEmpty()) {// 刷新代理ip池
			LOGGER.info("代理ip池数量已用完，刷新代理ip池");
			refreshProxyIps();
		}
		Set<String> keys = proxyIps.keySet();
		ProxyIp proxyIp = null;
		for (String ipKey : keys) {// 随便取一条代理ip即可
			proxyIp = proxyIps.get(ipKey);
			this.currentProxyIp = proxyIp;
			break;
		}
		return proxyIp;
	}

	public static String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }
}

