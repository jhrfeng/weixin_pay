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
	private String createDate;
	
	/**
	 * 修改时间
	 */
	private String modifyDate;
	
	/**
	 * 微信支付结果状态，0未支付成功，1已支付成功
	 */
	private String weixinStatus;
	/**
	 * 业务结果
	 */
	private String resultCode;
	/**
	 * 商户号
	 */
	private String mchId;
	/**
	 * 预支付交易会话标识
	 */
	private String prepayId;
	/**
	 * 公众账号ID
	 */
	private String appid;
	/**
	 * 二维码链接
	 */
	private String codeUrl;
	/**
	 * 随机字符串
	 */
	private String nonceStr;
	/**
	 * 返回状态码
	 */
	private String returnCode;
	/**
	 * 交易类型
	 */
	private String tradeType;
	
	public FinaceOrder(){}
	
	public FinaceOrder(WeiXinReq weiXinReq){
		this.totalNum = weiXinReq.getTotalNum();
		this.name = weiXinReq.getName();
		this.remark = weiXinReq.getRemark();
		this.idcard = weiXinReq.getIdcard();
		this.mobile = weiXinReq.getMobile();
		this.organId = weiXinReq.getOrganId();
		this.companyName = weiXinReq.getCompanyName();
		this.glOrgan = weiXinReq.getGlOrgan();
		this.deptId = weiXinReq.getDeptId();
		this.deptName = weiXinReq.getDeptName();
		this.urlId = weiXinReq.getUrlId();
	}
	
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getWeixinStatus() {
		return weixinStatus;
	}

	public void setWeixinStatus(String weixinStatus) {
		this.weixinStatus = weixinStatus;
	}
	
	
}
