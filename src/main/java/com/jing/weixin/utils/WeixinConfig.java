package com.jing.weixin.utils;

import java.net.URL;

public class WeixinConfig {
	
	  public static final String APPID = "wxa6cab777e1c735f5";
	  public static final String APPSECRET = "d1f20df1b3c654484bc829ecd76800f0"; // appsecret
	  public static final String MCH_ID = "1352280301"; // 商业号
	  public static final String KEY = "3a569abe4680a59fd4416808c81207cb"; // key
	  
	  public static final String NOTIFYURL = "http://ljh5522.xicp.net/wxpay/config/weixinPay_result";
	  
	  //统一下单API URL
	  public static final String sendNativeOrderRequestURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	  //查询订单API URL
	  public static final String sendOrdersQueryRequestURL = "https://api.mch.weixin.qq.com/pay/orderquery";
	  
	  //关闭订单API URL
	  public static final String sendCloseOrderRequestURL = "https://api.mch.weixin.qq.com/pay/closeorder";
	  
	  //申请退款API URL
	  public static final String sendRefundOrderRequestURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	  
	  //查询退款   URL
	  public static final String sendRefundQueryRequestURL = "https://api.mch.weixin.qq.com/pay/refundquery";
	  
	  //下载对账单  URL
	  public static final String sendDownloadBillRequestURL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	  
	  //交易保障  URL
	  public static final String sendReportOrderRequestURL = "https://api.mch.weixin.qq.com/payitil/report";
}
