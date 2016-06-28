package com.jing.weixin.entity;

public class WeiXinResult {
	
	/**
	 * 二维码
	 */
	public String urlCode;

	/**
	 * 微信订单号
	 */
	public String weixinOrder;
	
	/**
	 * 商品订单号
	 */
	public String tradeNo;
	
	/**
	 * 用户预标识
	 */
	public String prayId;
	
	public String resultCode;
	
	public String message;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public WeiXinResult(){
		
	}
	
	public WeiXinResult(String resultCode, String message){
		super();
		this.resultCode = resultCode;
		this.message = message;
	}

	public String getUrlCode() {
		return urlCode;
	}

	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}

	public String getWeixinOrder() {
		return weixinOrder;
	}

	public void setWeixinOrder(String weixinOrder) {
		this.weixinOrder = weixinOrder;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPrayId() {
		return prayId;
	}

	public void setPrayId(String prayId) {
		this.prayId = prayId;
	}
	
}
