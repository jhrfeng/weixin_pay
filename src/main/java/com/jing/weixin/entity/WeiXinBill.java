package com.jing.weixin.entity;


/**
 * 对账单
 * @Description
 * @date 2016年6月29日 上午10:56:55
 *
 */
public class WeiXinBill {
	private String tid;
	/**
	 * 交易时间
	 */
	private String tradeTime; 
	/**
	 * 公众账号ID
	 */
	private String appid;
	/**
	 * 商户号
	 */
	private String mchId;
	/**
	 * 子商户号
	 */
	private String subMchId;
	/**
	 * 设备号
	 */
	private String deviceInfo;
	/**
	 * 微信订单号
	 */
	private String transactionId;
	/**
	 * 商户订单号
	 */
	private String outTradeNo;
	/**
	 * 用户标识
	 */
	private String openid;
	/**
	 * 交易类型
	 */
	private String tradeType;
	/**
	 * 交易状态
	 */
	private String tradeState;
	/**
	 * 付款银行
	 */
	private String bankType;
	/**
	 * 货币种类
	 */
	private String feeType;
	/**
	 * 总金额
	 */
	private String totalFee;
	/**
	 * 代金券或立减优惠金额
	 */
	private String couponFee;
	/**
	 * 微信退款单号
	 */
	private String refundId;
	/**
	 * 商户退款单号
	 */
	private String outRefundNo;
	/**
	 * 退款金额
	 */
	private String settlementRefundFee;
	/**
	 * 代金券或立减优惠退款金额
	 */
	private String couponRefundFee;
	/**
	 * 退款类型
	 */
	private String refundType;
	/**
	 * 退款状态
	 */
	private String refundStatus;
	/**
	 * 商品名称
	 */
	private String commodityname;
	/**
	 * 商户数据包
	 */
	private String attach;
	/**
	 * 手续费
	 */
	private String fee;
	/**
	 * 费率
	 */
	private String rate;
	/**
	 * 首次同步时间
	 */
	private String createDate;
	/**
	 * 最新同步时间
	 */
	private String modifyDate;
	/**
	 * 0删除，1有效
	 */
	private String status;
	/**
	 * 0未同步，1已同步
	 */
	private String sync;
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getSubMchId() {
		return subMchId;
	}
	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTradeState() {
		return tradeState;
	}
	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getCouponFee() {
		return couponFee;
	}
	public void setCouponFee(String couponFee) {
		this.couponFee = couponFee;
	}
	public String getRefundId() {
		return refundId;
	}
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	public String getSettlementRefundFee() {
		return settlementRefundFee;
	}
	public String getOutRefundNo() {
		return outRefundNo;
	}
	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}
	public void setSettlementRefundFee(String settlementRefundFee) {
		this.settlementRefundFee = settlementRefundFee;
	}
	public String getCouponRefundFee() {
		return couponRefundFee;
	}
	public void setCouponRefundFee(String couponRefundFee) {
		this.couponRefundFee = couponRefundFee;
	}
	public String getRefundType() {
		return refundType;
	}
	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	public String getCommodityname() {
		return commodityname;
	}
	public void setCommodityname(String commodityname) {
		this.commodityname = commodityname;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
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
	
}
