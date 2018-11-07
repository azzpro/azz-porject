package com.azz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableTransactionManagement
public class AzzPlatMerchantServiceApplication {

	public static void main(String[] args) {
		 //JSONObject jb = JSONObject.fromObject("{\"param\":[{\"param\":[\"25\",\"24\",\"23\"],\"paramsChoice\":1,\"paramName\":\"测试1\",\"paramsType\":1},{\"param\":[\"20\",\"24\"],\"paramsChoice\":2,\"paramName\":\"测试2\",\"paramsType\":1},{\"param\":[],\"paramsChoice\":\"1\",\"paramName\":\"gdfdg\",\"paramsType\":\"2\"}],\"paramCode\":\"IGC3827501889\",\"parentCode\":\"IGD2055342368\",\"modifier\":\"user1\",\"assortmentCode\":\"IGA3343967056\"}");
		 System.setProperty("spring.devtools.restart.enabled", "true");
		 SpringApplication.run(AzzPlatMerchantServiceApplication.class, args);
		/*JSONArray jsonArray = jb.getJSONArray("param");
		 for(int i=0 ; i < jsonArray.size();i++)
		   {
			 JSONObject jsonObject = jsonArray.getJSONObject(i); 
			System.out.println(jsonObject.get("paramName"));
			 JSONArray s = jsonObject.getJSONArray("param");
			 for (int a=0; a<s.size() ;a++) {
				System.out.println("="+s.getInt(a));
			}*/
			// System.out.println("=======");
		// System.out.println(jsonArray.get(i));

		}
		 
}
