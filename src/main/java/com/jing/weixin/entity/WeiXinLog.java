package com.jing.weixin.entity;
/**
 * 微信log
 * @Description
 * @date 2016年6月30日 下午5:16:30
 * wx_log
 */
public class WeiXinLog {
	/**
	 * 主键
	 */
	private String tid;
	/**
	 * 操作名称
	 */
	private String name;
	/**
	 * 请求动作
	 */
	private String action;
	/**
	 * 标识 0失败 1成功
	 */
	private String flag;
	/**
	 * 消息记录
	 */
	private String message;
	/**
	 * 操作开始时间
	 */
	private String optStart;
	/**
	 * 操作结束时间
	 */
	private String optEnd;
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOptStart() {
		return optStart;
	}
	public void setOptStart(String optStart) {
		this.optStart = optStart;
	}
	public String getOptEnd() {
		return optEnd;
	}
	public void setOptEnd(String optEnd) {
		this.optEnd = optEnd;
	}
	

}
