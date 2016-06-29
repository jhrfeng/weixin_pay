package com.jing.weixin.serviceImp;

import java.util.Date;
import java.util.SortedMap;

import org.springframework.stereotype.Service;

import com.jing.weixin.apidemo.WeixinPayAPI;
import com.jing.weixin.entity.FinaceOrder;
import com.jing.weixin.service.WeixinService;

@Service("WeixinService")
public class WeixinServiceImp implements WeixinService{

	public void saveWeixinOrderInfo(FinaceOrder finaceOrder){
		finaceOrder.setSync("0");
		finaceOrder.setStatus("0");
		finaceOrder.setWeixinStatus("0");
		finaceOrder.setCreateDate(new Date());
		finaceOrder.setModifyDate(new Date());
	}
	
	public void updateWeixinOrderResult(FinaceOrder finaceOrder){
		String outTradeNo =  finaceOrder.getTradeNo(); //获取商品号
	}
	
	public void saveStatemenetOrder(SortedMap<String, String> packageParams){
		WeixinPayAPI.downloadBill(packageParams);
	}
}
