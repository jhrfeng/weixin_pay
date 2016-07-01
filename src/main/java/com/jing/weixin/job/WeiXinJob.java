package com.jing.weixin.job;

import java.sql.SQLException;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.jing.weixin.service.WeixinService;

public class WeiXinJob {
	
	@Autowired
	private WeixinService weixinService;

	/**
	 * 处理微信同步账单信息，同时同步到资金系统
	 */
	public void asyncStatementOrder(){
		SortedMap<String, String> packageParams = null;
		try {
			weixinService.saveStatemenetOrder(packageParams);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
