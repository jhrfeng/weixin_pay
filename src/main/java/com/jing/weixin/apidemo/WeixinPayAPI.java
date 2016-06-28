package com.jing.weixin.apidemo;

import java.util.SortedMap;
import java.util.UUID;

import com.jing.weixin.entity.WeiXinResult;
import com.jing.weixin.httputil.HTTPClientUtils;
import com.jing.weixin.httputil.RequestHandler;
import com.jing.weixin.utils.WeixinConfig;


public class WeixinPayAPI {
	
	/**
	 * 统一下单 NATIVE
	 * @param packageParams
	 * @return
	 */
	public static WeiXinResult nativeOrder(SortedMap<String, String> packageParams){
		/**** 报文中必填字段  *****/
		String nonceStr = UUID.randomUUID().toString().toUpperCase();
		packageParams.put("appid", WeixinConfig.APPID); //公众账号ID
		packageParams.put("mch_id", WeixinConfig.MCH_ID); //商户号
		packageParams.put("nonce_str", nonceStr.replace("-", "")); //随机字符串
		packageParams.put("notify_url", WeixinConfig.NOTIFYURL); //通知地址
		packageParams.put("trade_type", "NATIVE"); // 交易类型
		/**** 报文中非必填字段  *****/
		
		
		WeiXinResult eiXinResult = 
				HTTPClientUtils.sendNativeOrderRequest(packageParams, WeixinConfig.KEY);
		return eiXinResult;
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

	/**
	 * 查询订单
	 * @param packageParams
	 * @return
	 */
	public static String ordersQuery(SortedMap<String, String> packageParams){
		/**** 报文中必填字段  *****/
		String nonceStr = UUID.randomUUID().toString().toUpperCase();
		packageParams.put("appid", WeixinConfig.APPID); //公众账号ID
		packageParams.put("mch_id", WeixinConfig.MCH_ID); //商户号
		packageParams.put("nonce_str", nonceStr.replace("-", "")); //随机字符串
		//订单查询的两种方式(微信订单号优先使用)
//		packageParams.put("out_trade_no", "201606271009352720"); //商品订单号(方式一)
		packageParams.put("transaction_id", "4000092001201606277956532063"); //微信订单号(方式二)
		/**** 报文中非必填字段  *****/
		
		//订单状态
		String trade_state = HTTPClientUtils.sendOrdersQueryRequest(packageParams, WeixinConfig.KEY);
		return trade_state;
	}
	
	
	/**
	 * 关闭订单
	 * @param packageParams
	 * @return
	 */
	public static String closeOrder(SortedMap<String, String> packageParams){
		/**** 报文中必填字段  *****/
		String nonceStr = UUID.randomUUID().toString().toUpperCase();
		packageParams.put("appid", WeixinConfig.APPID); //公众账号ID
		packageParams.put("mch_id", WeixinConfig.MCH_ID); //商户号
		packageParams.put("nonce_str", nonceStr.replace("-", "")); //随机字符串
		packageParams.put("out_trade_no", "201606271009352720"); //商品订单号
		/**** 报文中非必填字段  *****/
		
		//订单状态
		String return_msg = HTTPClientUtils.sendCloseOrderRequest(packageParams, WeixinConfig.KEY);
		return return_msg;
	}
	
	/**
	 * 申请退款
	 * @param packageParams
	 * @return
	 */
	public static String refundOrder(SortedMap<String, String> packageParams){
		/**** 报文中必填字段  *****/
		String nonceStr = UUID.randomUUID().toString().toUpperCase();
		packageParams.put("appid", WeixinConfig.APPID); //公众账号ID
		packageParams.put("mch_id", WeixinConfig.MCH_ID); //商户号
		packageParams.put("nonce_str", nonceStr.replace("-", "")); //随机字符串
		//申请退款的两种方式
//		packageParams.put("transaction_id", "");//微信订单号 (方式一)
		packageParams.put("out_trade_no", "201606271009352720"); //商品订单号(方式二)
		packageParams.put("out_refund_no", "");//商户退款单号
		packageParams.put("total_fee", "");//总金额
		packageParams.put("refund_fee", "");//退款金额
		packageParams.put("op_user_id", "");//操作员
		/**** 报文中非必填字段  *****/
		packageParams.put("device_info", "013467007045764");//设备号
		packageParams.put("refund_fee_type", "");//货币种类
		
		String return_msg = HTTPClientUtils.sendRefundOrderRequest(packageParams, WeixinConfig.KEY);
		return return_msg;
	}
	
	/**
	 * 查询退款
	 * @param packageParams
	 * @return
	 */
	public static String refundQuery(SortedMap<String, String> packageParams){
		/**** 报文中必填字段  *****/
		String nonceStr = UUID.randomUUID().toString().toUpperCase();
		packageParams.put("appid", WeixinConfig.APPID); //公众账号ID
		packageParams.put("mch_id", WeixinConfig.MCH_ID); //商户号
		packageParams.put("nonce_str", nonceStr.replace("-", "")); //随机字符串
		//退款的四种查询方式
		packageParams.put("transaction_id", "");//微信订单号(方式一)
//		packageParams.put("out_trade_no", "");//商户订单号(方式二)
//		packageParams.put("out_refund_no", "");//商户退款单号(方式三)
//		packageParams.put("refund_id", "");//微信退款单号(方式四)
		/**** 报文中非必填字段  *****/
		packageParams.put("device_info", "013467007045764");//设备号
		
		String return_msg = HTTPClientUtils.sendRefundQueryRequest(packageParams, WeixinConfig.KEY);
		return return_msg;
	}
	
	/**
	 * 下载对账单
	 * @param packageParams
	 * @return
	 */
	public static String downloadBill(SortedMap<String, String> packageParams){
		/**** 报文中必填字段  *****/
		String nonceStr = UUID.randomUUID().toString().toUpperCase();
		packageParams.put("appid", WeixinConfig.APPID); //公众账号ID
		packageParams.put("mch_id", WeixinConfig.MCH_ID); //商户号
		packageParams.put("nonce_str", nonceStr.replace("-", "")); //随机字符串
		packageParams.put("bill_date", "");//对账单日期
		/**** 报文中非必填字段  *****/
		packageParams.put("device_info", "013467007045764");//设备号
		packageParams.put("bill_type", "");//账单类型
		
		String return_msg = HTTPClientUtils.sendDownloadBillRequest(packageParams, WeixinConfig.KEY);
		return return_msg;
	}
	
	/**
	 * 交易保障
	 * @param packageParams
	 * @return
	 */
	public static String payReport(SortedMap<String, String> packageParams){
		/**** 报文中必填字段  *****/
		String nonceStr = UUID.randomUUID().toString().toUpperCase();
		packageParams.put("appid", WeixinConfig.APPID); //公众账号ID
		packageParams.put("mch_id", WeixinConfig.MCH_ID); //商户号
		packageParams.put("nonce_str", nonceStr.replace("-", "")); //随机字符串
		packageParams.put("interface_url", "");//接口URL	
		/**** 报文中非必填字段  *****/
		packageParams.put("device_info", "013467007045764");//设备号
		packageParams.put("bill_type", "");//账单类型
		
		String return_msg = HTTPClientUtils.sendDownloadBillRequest(packageParams, WeixinConfig.KEY);
		return return_msg;
	}

}
