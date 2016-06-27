package com.jing.weixin.httputil;

import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.jing.weixin.utils.WeixinConfig;

public class HTTPClientUtils {
	
    public static DefaultHttpClient httpclient;

    static {
        httpclient = new DefaultHttpClient();
        httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient);
    }
    
    public static String sendJsApiOrderRequest(
			SortedMap<String, String> packageParams,
			String key
				){
		String  requestUrl = WeixinConfig.sendNativeOrderRequestURL;
		// 做一次签名
		String sign = RequestHandler.createSign(packageParams, key);
		packageParams.put("sign", sign);
		// 转为微信规范的报文形式 XML
		String xmlParam = RequestHandler.parseXML(packageParams);
		
		//请求处理
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(requestUrl);
		String prepay_id = "";
		try{
			// 处理返回结果
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse weixinResponse = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
			System.out.println(jsonStr);
			
			if(jsonStr.indexOf("FAIL") != -1) {
				return prepay_id;
			}
			 Map map = RequestHandler.doXMLParse(jsonStr);
	         String return_code = (String) map.get("return_code");
	         prepay_id = (String) map.get("prepay_id");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return prepay_id;
	}

	public static String sendNativeOrderRequest(
			SortedMap<String, String> packageParams,
			String key
				){
		String  requestUrl = WeixinConfig.sendNativeOrderRequestURL;
		// 做一次签名
		String sign = RequestHandler.createSign(packageParams, key);
		packageParams.put("sign", sign);
		// 转为微信规范的报文形式 XML
		String xmlParam = RequestHandler.parseXML(packageParams);
		
		//请求处理
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(requestUrl);
		String prepay_id = "";
		String urlCode = "";
		try{
			// 处理返回结果
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse weixinResponse = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
			System.out.println(jsonStr);
			
			if(jsonStr.indexOf("FAIL") != -1) {
				return urlCode;
			}
			 Map map = RequestHandler.doXMLParse(jsonStr);
	         String return_code = (String) map.get("return_code");
	         prepay_id = (String) map.get("prepay_id");
	         urlCode = (String) map.get("code_url"); // 二维码图片URL
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return urlCode;
	}

	public static String sendOrdersQueryRequest(
			SortedMap<String, String> packageParams,
			String key
				){
		//请求查询的URL
		String  requestUrl = WeixinConfig.sendOrdersQueryRequestURL;
		// 做一次签名
		String sign = RequestHandler.createSign(packageParams, key);
		packageParams.put("sign", sign);
		// 转为微信规范的报文形式 XML
		String xmlParam = RequestHandler.parseXML(packageParams);
		
		//请求处理
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(requestUrl);
		String trade_state = "";
		try{
			// 处理返回结果
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse weixinResponse = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
			System.out.println(jsonStr);
			
			 Map map = RequestHandler.doXMLParse(jsonStr);
	         String return_code = (String) map.get("return_code");
	         trade_state = (String) map.get("trade_state");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return trade_state;
	}
}
