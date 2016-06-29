package com.jing.weixin.entity;


/**
 * 对账单
 * @Description
 * @date 2016年6月29日 上午10:56:55
 *
 */
public class WeiXinBill {
	/**
	 * 交易时间
	 */
	private String time; 
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
	private String coupon_fee;
	/**
	 * 微信退款单号
	 */
	private String refund_id;
	/**
	 * 商户退款单号
	 */
	private String out_refund_no;
	/**
	 * 退款金额
	 */
	private String settlement_refund_fee_$n;
	/**
	 * 代金券或立减优惠退款金额
	 */
	private String coupon_refund_fee_$n;
	/**
	 * 退款类型
	 */
	private String refund_type;
	/**
	 * 退款状态
	 */
	private String refund_status_$n;
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
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public String getCoupon_fee() {
		return coupon_fee;
	}
	public void setCoupon_fee(String coupon_fee) {
		this.coupon_fee = coupon_fee;
	}
	public String getRefund_id() {
		return refund_id;
	}
	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public String getSettlement_refund_fee_$n() {
		return settlement_refund_fee_$n;
	}
	public void setSettlement_refund_fee_$n(String settlement_refund_fee_$n) {
		this.settlement_refund_fee_$n = settlement_refund_fee_$n;
	}
	public String getCoupon_refund_fee_$n() {
		return coupon_refund_fee_$n;
	}
	public void setCoupon_refund_fee_$n(String coupon_refund_fee_$n) {
		this.coupon_refund_fee_$n = coupon_refund_fee_$n;
	}
	public String getRefund_type() {
		return refund_type;
	}
	public void setRefund_type(String refund_type) {
		this.refund_type = refund_type;
	}
	public String getRefund_status_$n() {
		return refund_status_$n;
	}
	public void setRefund_status_$n(String refund_status_$n) {
		this.refund_status_$n = refund_status_$n;
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
	

}
