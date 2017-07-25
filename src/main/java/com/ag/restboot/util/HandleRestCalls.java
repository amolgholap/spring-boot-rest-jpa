package com.ag.restboot.util;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class HandleRestCalls {
	
	@Value("${proxyrequired}")
	boolean proxyrequired;
public String handleRestCalls(String url,String accessToken, String predixZoneId,String inputJson,org.springframework.http.HttpMethod method){
		
		String result=null;
		HttpGet getRequest = null;
		String proxyServer="3.234.164.81";
		int proxyPort=80;
		
		try{
		
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy= new Proxy(Type.HTTP, new InetSocketAddress(proxyServer, proxyPort));
	    //requestFactory.setProxy(proxy);
	    requestFactory.setReadTimeout(0);
	    requestFactory.setConnectTimeout(0);
	    RestTemplate restTemplateGenric = new RestTemplate();
	    if(proxyrequired)
	    	requestFactory.setProxy(proxy);
	    restTemplateGenric.setRequestFactory(requestFactory);
	    
		HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.set("predix-zone-id", predixZoneId);
    	headers.set("authorization", "Bearer "+accessToken);
		org.springframework.http.HttpEntity<String> entity  = new org.springframework.http.HttpEntity<String>(inputJson ,headers);
		
    	final ResponseEntity<String> resultEntity=restTemplateGenric.exchange(url, method, entity, String.class);
    	result=resultEntity.getBody();
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(null != getRequest){
				getRequest.releaseConnection();
			}
		}
		return result;
	}

public String handleRestCalls(String url,String inputJson,HttpMethod method){
	
	RestTemplate restTemplate = new RestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	
	org.springframework.http.HttpEntity<String> entity  = new org.springframework.http.HttpEntity<String>(inputJson,headers);
	
	final ResponseEntity<String> responseEntity = restTemplate.exchange(url,method,entity,String.class);
	
	String outPut= responseEntity.getBody();
	return outPut;
}
}
