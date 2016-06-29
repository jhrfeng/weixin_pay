package com.jing.weixin.entity;

import java.util.Date;

public class FinaceOrder extends WeiXinReq{

	/**
	 * 商品订单号
	 */
	private String tradeNo;
	
	/**
	 * 微信订单号
	 */
	private String weixinOrder;
	
	/**
	 * 本地状态
	 * 0无效，1有效
	 */
	private String status;
	
	/**
	 * 同步状态
	 * 0未同步，1已同步，2同步失败？异常
	 */
	private String sync;
	
	/**
	 * 生效时间
	 */
	private Date createDate;
	
	/**
	 * 修改时间
	 */
	private Date modifyDate;
	
	/**
	 * 微信支付结果状态，0未支付成功，1已支付成功
	 */
	private String weixinStatus;
	/**
	 * 支付结果通知
	 */
	private WeiXinPayResult payResult;
	
	public FinaceOrder(){}
	
	public FinaceOrder(WeiXinReq weiXinReq){
		this.totalNum = weiXinReq.getTotalNum();
		this.name = weiXinReq.getName();
		this.remark = weiXinReq.getRemark();
		this.idcard = weiXinReq.getIdcard();
		this.mobile = weiXinReq.getMobile();
		this.orgId = weiXinReq.getOrgId();
		this.orgName = weiXinReq.getOrgName();
		this.finaceId = weiXinReq.getFinaceId();
		this.clothingId = weiXinReq.getClothingId();
		this.clothingName = weiXinReq.getClothingName();
		this.urlId = weiXinReq.getUrlId();
	}
	
	public WeiXinPayResult getPayResult() {
		return payResult;
	}

	public void setPayResult(WeiXinPayResult payResult) {
		this.payResult = payResult;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getWeixinOrder() {
		return weixinOrder;
	}

	public void setWeixinOrder(String weixinOrder) {
		this.weixinOrder = weixinOrder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSync() {
		return sync;
	}

	public void setSync(String sync) {
		this.sync = sync;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getWeixinStatus() {
		return weixinStatus;
	}

	public void setWeixinStatus(String weixinStatus) {
		this.weixinStatus = weixinStatus;
	}
	
	
}
