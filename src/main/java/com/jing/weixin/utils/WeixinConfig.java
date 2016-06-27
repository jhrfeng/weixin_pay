package com.jing.weixin.utils;

import java.net.URL;

public class WeixinConfig {
	
	  public static final String APPID = "wxf1d002b05e5c41fa"; //"wxa6cab777e1c735f5";
	  public static final String APPSECRET = "ygyOINB6BZTMR5NVTNfP-EZ7yTTQWt6eaOOr1-nTBLhAIMx0n771EZpp-kO7_lDW"; //"d1f20df1b3c654484bc829ecd76800f0"; // appsecret
	  public static final String MCH_ID = "1346170301";  //"1352280301"; // 商业号
	  public static final String KEY = "5514z7d2y139c859d304064d22bba153"; //"3a569abe4680a59fd4416808c81207cb"; // key
	  
	  public static final String NOTIFYURL = "http://ljh5522.xicp.net/wxpay/config/weixinPay_result";

	  
	  //统一下单API URL
	  public static final String sendNativeOrderRequestURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	   //查询订单API URL
	  public static final String sendOrdersQueryRequestURL = "https://api.mch.weixin.qq.com/pay/orderquery";
	  
	  //关闭订单API URL
	  public static final String sendCloseOrderRequestURL = "https://api.mch.weixin.qq.com/pay/closeorder";
}
