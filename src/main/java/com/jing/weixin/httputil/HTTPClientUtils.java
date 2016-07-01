package com.jing.weixin.httputil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

import com.jing.weixin.entity.WeiXinBill;
import com.jing.weixin.entity.WeiXinResult;
import com.jing.weixin.utils.WeixinConfig;

public class HTTPClientUtils {
	
    public static DefaultHttpClient httpclient;

    static {
        httpclient = new DefaultHttpClient();
        httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient);
    }
    
    public static WeiXinResult sendJsApiOrderRequest(
			SortedMap<String, String> packageParams,
			String key
				){
    	WeiXinResult weiXinResult = new WeiXinResult();
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
		try{
			// 处理返回结果
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse weixinResponse = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
			System.out.println(jsonStr);
			
			if(jsonStr.indexOf("FAIL") != -1) {
				return weiXinResult;
			}
			 Map map = RequestHandler.doXMLParse(jsonStr);
	         weiXinResult.setResultCode((String) map.get("return_code"));
	         weiXinResult.setPrayId((String) map.get("prepay_id"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return weiXinResult;
	}

	public static WeiXinResult sendNativeOrderRequest(
			SortedMap<String, String> packageParams,
			String key
				){
		WeiXinResult weiXinResult = new WeiXinResult();
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
		try{
			// 处理返回结果
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse weixinResponse = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
			System.out.println(jsonStr);
			
			if(jsonStr.indexOf("FAIL") != -1) {
				return weiXinResult;
			}
			 Map map = RequestHandler.doXMLParse(jsonStr);
			 System.out.println("map"+map);
			 weiXinResult.setResultCode((String) map.get("result_code"));
			 weiXinResult.setReturnCode((String) map.get("return_code"));
			 weiXinResult.setPrayId((String) map.get("prepay_id"));
			 weiXinResult.setUrlCode((String) map.get("code_url")); // 二维码图片URL
			 weiXinResult.setAppid((String)map.get("appid"));
			 weiXinResult.setMchId((String)map.get("mch_id"));
			 weiXinResult.setNonceStr((String)map.get("nonce_str"));
			 weiXinResult.setTradeType((String)map.get("trade_type"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return weiXinResult;
	}

	public static WeiXinResult sendOrdersQueryRequest(
			SortedMap<String, String> packageParams,
			String key
				){
		WeiXinResult weiXinResult = new WeiXinResult();
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
//		String trade_state = "";
		try{
			// 处理返回结果
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse weixinResponse = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
			System.out.println(jsonStr);
			
			 Map map = RequestHandler.doXMLParse(jsonStr);
			 weiXinResult.setResultCode((String) map.get("result_code"));
			 weiXinResult.setMessage((String)map.get("return_msg"));
//	         String return_code = (String) map.get("return_code");
//	         trade_state = (String) map.get("trade_state");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return weiXinResult;
	}
	
	public static WeiXinResult sendCloseOrderRequest(
			SortedMap<String, String> packageParams,
			String key
				){
		WeiXinResult weiXinResult = new WeiXinResult();
		//请求关闭订单的URL
		String  requestUrl = WeixinConfig.sendCloseOrderRequestURL;
		// 做一次签名
		String sign = RequestHandler.createSign(packageParams, key);
		packageParams.put("sign", sign);
		// 转为微信规范的报文形式 XML
		String xmlParam = RequestHandler.parseXML(packageParams);
		
		//请求处理
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(requestUrl);
		try{
			// 处理返回结果
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse weixinResponse = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
			System.out.println(jsonStr);
			
			 Map map = RequestHandler.doXMLParse(jsonStr);
	         weiXinResult.setMessage((String) map.get("return_msg"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return weiXinResult;
	}
	
	public static WeiXinResult sendRefundOrderRequest(
			SortedMap<String, String> packageParams,
			String key
				){
		WeiXinResult weiXinResult = new WeiXinResult();
		//请求申请退款的URL
		String  requestUrl = WeixinConfig.sendRefundOrderRequestURL;
		// 做一次签名
		String sign = RequestHandler.createSign(packageParams, key);
		packageParams.put("sign", sign);
		// 转为微信规范的报文形式 XML
		String xmlParam = RequestHandler.parseXML(packageParams);
		
		//请求处理
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(requestUrl);
		String return_msg = "";
		try{
			// 处理返回结果
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse weixinResponse = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
			System.out.println(jsonStr);
			
			 Map map = RequestHandler.doXMLParse(jsonStr);
//	         String return_code = (String) map.get("return_code");
	         return_msg = (String) map.get("return_msg");
	         weiXinResult.setMessage((String) map.get("return_msg"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return weiXinResult;
	}
	
	public static WeiXinResult sendRefundQueryRequest(
			SortedMap<String, String> packageParams,
			String key
				){
		WeiXinResult weiXinResult = new WeiXinResult();
		//请求查询退款的URL
		String  requestUrl = WeixinConfig.sendRefundQueryRequestURL;
		// 做一次签名
		String sign = RequestHandler.createSign(packageParams, key);
		packageParams.put("sign", sign);
		// 转为微信规范的报文形式 XML
		String xmlParam = RequestHandler.parseXML(packageParams);
		
		//请求处理
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(requestUrl);
		try{
			// 处理返回结果
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse weixinResponse = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
			System.out.println(jsonStr);
			
			 Map map = RequestHandler.doXMLParse(jsonStr);
	         weiXinResult.setMessage((String) map.get("return_msg"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return weiXinResult;
	}
	
	public static List<WeiXinBill> sendDownloadBillRequest(
			SortedMap<String, String> packageParams,
			String key
				){
		//请求下载对账单的URL
		String  requestUrl = WeixinConfig.sendDownloadBillRequestURL;
		// 做一次签名
		String sign = RequestHandler.createSign(packageParams, key);
		packageParams.put("sign", sign);
		// 转为微信规范的报文形式 XML
		String xmlParam = RequestHandler.parseXML(packageParams);
		
		//请求处理
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		HttpPost httpost = HttpClientConnectionManager.getPostMethod(requestUrl);
		String jsonStr = "";
		List<WeiXinBill> billlist = new ArrayList<WeiXinBill>();
		try{
			// 处理返回结果
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse weixinResponse = httpclient.execute(httpost);
			jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
			jsonStr = jsonStr.replace(",", "");
			jsonStr = jsonStr.replaceAll("\r|\n", "");
			jsonStr = jsonStr.substring(jsonStr.indexOf("费率")+2, jsonStr.indexOf("总交易单数"));
			String[] li = jsonStr.split("`");
			WeiXinBill bill = new WeiXinBill();
			int count = (li.length)/24;
			for(int i=0;i<count;i++){
				for(int j=1;j<li.length;j++){
					bill.setTradeTime(li[j]);
					bill.setAppid(li[j+1]);
					bill.setMchId(li[j+2]);
					bill.setSubMchId(li[j+3]);
					bill.setDeviceInfo(li[j+4]);
					bill.setTransactionId(li[j+5]);
					bill.setOutTradeNo(li[j+6]);
					bill.setOpenid(li[j+7]);
					bill.setTradeType(li[j+8]);
					bill.setTradeState(li[j+9]);
					bill.setBankType(li[j+10]);
					bill.setFeeType(li[j+11]);
					bill.setTotalFee(li[j+12]);
					bill.setCouponFee(li[j+13]);
					bill.setRefundId(li[j+14]);
					bill.setOutRefundNo(li[j+15]);
					bill.setSettlementRefundFee(li[j+16]);
					bill.setCouponRefundFee(li[j+17]);
					bill.setRefundType(li[j+18]);
					bill.setRefundStatus(li[j+19]);
					bill.setCommodityname(li[j+20]);
					bill.setAttach(li[j+21]);
					bill.setFee(li[j+22]);
					bill.setRate(li[j+23]);
					billlist.add(bill);
					j=j+24;
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return billlist;
	}
}
