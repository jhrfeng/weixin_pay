package com.jing.weixin.apidemo;

import java.util.SortedMap;
import java.util.UUID;

import com.jing.weixin.httputil.HTTPClientUtils;
import com.jing.weixin.httputil.RequestHandler;
import com.jing.weixin.utils.WeixinConfig;


public class WeixinPayAPI {
	
	/**
	 * 统一下单 NATIVE
	 * @param packageParams
	 * @return
	 */
	public static String nativeOrder(SortedMap<String, String> packageParams){
		/**** 报文中必填字段  *****/
		String nonceStr = UUID.randomUUID().toString().toUpperCase();
		packageParams.put("appid", WeixinConfig.APPID); //公众账号ID
		packageParams.put("mch_id", WeixinConfig.MCH_ID); //商户号
		packageParams.put("nonce_str", nonceStr.replace("-", "")); //随机字符串
		packageParams.put("notify_url", WeixinConfig.NOTIFYURL); //通知地址
		packageParams.put("out_trade_no", RequestHandler.getRandomDateNo()); //商品订单号
		packageParams.put("trade_type", "NATIVE"); // 交易类型
		/**** 报文中非必填字段  *****/
		
		
		String prePayId = 
				HTTPClientUtils.sendNativeOrderRequest(packageParams, WeixinConfig.KEY);
		return prePayId;
	}
	
	
	/**
	 * 统一下单 JSAPI
	 * @param packageParams
	 * @return
	 */
	public static String jsApiOrder(SortedMap<String, String> packageParams){
		/**** 报文中必填字段  *****/
		String nonceStr = UUID.randomUUID().toString().toUpperCase();
		packageParams.put("appid", WeixinConfig.APPID); //公众账号ID
		packageParams.put("mch_id", WeixinConfig.MCH_ID); //商户号
		packageParams.put("nonce_str", nonceStr.replace("-", "")); //随机字符串
		packageParams.put("notify_url", WeixinConfig.NOTIFYURL); //通知地址
		packageParams.put("out_trade_no", RequestHandler.getRandomDateNo()); //商品订单号
		packageParams.put("trade_type", "JSAPI"); // 交易类型
		/**** 报文中非必填字段  *****/
		
		
		String prePayId = 
				HTTPClientUtils.sendJsApiOrderRequest(packageParams, WeixinConfig.KEY);
		return prePayId;
	}

}
