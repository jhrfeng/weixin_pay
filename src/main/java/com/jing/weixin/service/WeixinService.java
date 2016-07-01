package com.jing.weixin.service;

import java.sql.SQLException;
import java.util.SortedMap;

import org.springframework.stereotype.Service;

import com.jing.weixin.entity.FinaceOrder;
import com.jing.weixin.entity.WeiXinPayResult;

public interface WeixinService {
	
	/**
	 * 微信下单预存储
	 * @param finaceOrder
	 */
	public void saveWeixinOrderInfo(FinaceOrder finaceOrder) throws SQLException;
	
	/**
	 * 微信支付成功，保存结果
	 * 根据微信返回的订单号查询数据库预存结果进行更新
	 * @param outTradeNo
	 */
	public void updateWeixinOrderResult(SortedMap<String, String> result) throws Exception;
	
	/**
	 * 保存对账单信息    【定时任务触发】
	 * 1、微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致，bill_type为REVOKED；
	   2、微信在次日9点启动生成前一天的对账单，建议商户10点后再获取；
       3、对账单中涉及金额的字段单位为“元”。
       4、对账单接口只能下载三个月以内的账单。
	 * 
	 */
	public void saveStatemenetOrder(SortedMap<String, String> packageParams) throws SQLException;

}
