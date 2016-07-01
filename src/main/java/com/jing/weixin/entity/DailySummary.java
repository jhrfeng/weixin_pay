package com.jing.weixin.entity;
/**
 * 每日汇总
 * @Description
 * @date 2016年6月30日 下午5:14:01
 *
 */
public class DailySummary {
	/**
	 * 交易日期
	 */
	private String tradeTime;
	/**
	 * 总交易单数
	 */
	private String tradeNum;
	/**
	 * 总交易额
	 */
	private String tradeSum;
	/**
	 * 手续费总金额
	 */
	private String feeSum;
	/**
	 * 总企业红包退款金额
	 */
	private String companySum;
	/**
	 * 总退款金额
	 */
	private String totalSum;
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
	
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getTradeNum() {
		return tradeNum;
	}
	public void setTradeNum(String tradeNum) {
		this.tradeNum = tradeNum;
	}
	public String getTradeSum() {
		return tradeSum;
	}
	public void setTradeSum(String tradeSum) {
		this.tradeSum = tradeSum;
	}
	public String getFeeSum() {
		return feeSum;
	}
	public void setFeeSum(String feeSum) {
		this.feeSum = feeSum;
	}
	public String getCompanySum() {
		return companySum;
	}
	public void setCompanySum(String companySum) {
		this.companySum = companySum;
	}
	public String getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(String totalSum) {
		this.totalSum = totalSum;
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
