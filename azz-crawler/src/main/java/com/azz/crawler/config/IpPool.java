/*******************************************************************************
 * Project Key : CPPII
 * Create on 2019年2月28日 下午5:33:54
 * Copyright (c) 2018. 爱智造.
 * 注意：本内容仅限于爱智造内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
 
package com.azz.crawler.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azz.crawler.pojo.ProxyIp;

import lombok.Getter;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 黄智聪  2019年2月28日 下午5:33:54
 */
public class IpPool {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IpPool.class);
	
	@Getter
	private Map<String, ProxyIp> proxyIps;
	
	@Getter
	private ProxyIp currentProxyIp;
	
	public IpPool() {
		initIps();
	}
	
	public void initIps(){
		LOGGER.info("正在初始化代理ip池的数据...");
		ProxyIp[] proxyIpArr = { new ProxyIp("119.129.236.155", 4206), 
				new ProxyIp("116.23.227.52", 4206),
				new ProxyIp("116.23.226.28", 4206), 
				new ProxyIp("116.23.227.39", 4206),
				new ProxyIp("119.129.236.234", 4206), 
				new ProxyIp("116.23.226.215", 4206),
				new ProxyIp("113.119.11.12", 4206),
				new ProxyIp("119.129.237.55", 4206),
				new ProxyIp("113.119.11.125", 4206),
				new ProxyIp("119.129.236.107", 4206) };
		Map<String, ProxyIp> ips = new HashMap<>();
		for (ProxyIp proxyIp : proxyIpArr) {
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
	
	public ProxyIp getNextProxyIp() {
		proxyIps.remove(this.currentProxyIp.getIp());// 移除掉当前key对应的ip
		Set<String> keys = proxyIps.keySet();
		if(keys.isEmpty()) { // ip池没ip了，要报错了
			throw new RuntimeException("代理ip池数量已用完");
		}
		ProxyIp proxyIp = null;
		for (String ipKey : keys) {// 随便取一条代理ip即可
			proxyIp = proxyIps.get(ipKey);
			this.currentProxyIp = proxyIp;
			break;
		}
		return proxyIp;
	}

}

