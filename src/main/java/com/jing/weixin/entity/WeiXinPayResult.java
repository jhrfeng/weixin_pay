package com.jing.weixin.entity;
/**
 * 支付结果
 * @Description
 *
 */
public class WeiXinPayResult {
	/**
	 * 主键
	 */
	private String tid;
	/**
	 * 商户号
	 */
	private String mchId;
	/**
	 * 设备号
	 */
	private String deviceInfo;
	/**
	 * 随机字符串
	 */
	private String nonceStr;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 业务结果
	 */
	private String resultCode;
	/**
	 * 错误代码
	 */
	private String errCode;
	/**
	 * 错误代码描述
	 */
	private String errCodeDes;
	/**
	 * 用户标识
	 */
	private String openid;
	/**
	 * 是否关注公众账号
	 */
	private String isSubscribe;
	/**
	 * 交易类型
	 */
	private String tradeType;
	/**
	 * 付款银行
	 */
	private String bankType;
	/**
	 * 订单金额
	 */
	private String totalFee;
	/**
	 * 应结订单金额
	 */
	private String settlementTotalFee;
	/**
	 * 货币种类
	 */
	private String feeType;
	/**
	 * 现金支付金额
	 */
	private String cashFee;
	/**
	 * 现金支付货币类型
	 */
	private String cashFeeType;
	/**
	 * 代金券金额
	 */
	private String couponFee;
	/**
	 * 代金券使用数量
	 */
	private String couponCount;
	/**
	 * 代金券类型
	 */
	private String couponType;
	/**
	 * 代金券ID
	 */
	private String couponId;
	/**
	 * 单个代金券支付金额
	 */
	private String couponFeeN;
	/**
	 * 微信支付订单号
	 */
	private String transactionId;
	/**
	 * 商户订单号
	 */
	private String outTradeNo;
	/**
	 * 商家数据包
	 */
	private String attach;
	/**
	 * 支付完成时间
	 */
	private String timeEnd;
	/**
	 * 同步
	 */
	private String sync;
	/**
	 * 机构ID
	 */
	private String orgId;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 机构财务ID
	 */
	private String finaceId;
	/**
	 * 营服ID
	 */
	private String clothingId;
	/**
	 * 营服名称
	 */
	private String clothingName;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 金额
	 */
	private String paySum;
	/**
	 * 身份证
	 */
	private String idcard;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 备注
	 */
	private String remark;
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrCodeDes() {
		return errCodeDes;
	}
	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getIsSubscribe() {
		return isSubscribe;
	}
	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getSettlementTotalFee() {
		return settlementTotalFee;
	}
	public void setSettlementTotalFee(String settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getCashFee() {
		return cashFee;
	}
	public void setCashFee(String cashFee) {
		this.cashFee = cashFee;
	}
	public String getCashFeeType() {
		return cashFeeType;
	}
	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}
	public String getCouponFee() {
		return couponFee;
	}
	public void setCouponFee(String couponFee) {
		this.couponFee = couponFee;
	}
	public String getCouponCount() {
		return couponCount;
	}
	public void setCouponCount(String couponCount) {
		this.couponCount = couponCount;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getCouponFeeN() {
		return couponFeeN;
	}
	public void setCouponFeeN(String couponFeeN) {
		this.couponFeeN = couponFeeN;
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
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getSync() {
		return sync;
	}
	public void setSync(String sync) {
		this.sync = sync;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getFinaceId() {
		return finaceId;
	}
	public void setFinaceId(String finaceId) {
		this.finaceId = finaceId;
	}
	public String getClothingId() {
		return clothingId;
	}
	public void setClothingId(String clothingId) {
		this.clothingId = clothingId;
	}
	public String getClothingName() {
		return clothingName;
	}
	public void setClothingName(String clothingName) {
		this.clothingName = clothingName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPaySum() {
		return paySum;
	}
	public void setPaySum(String paySum) {
		this.paySum = paySum;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
