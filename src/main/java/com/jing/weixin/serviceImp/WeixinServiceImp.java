package com.jing.weixin.serviceImp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
		
//		Connection conn = DBUtil.getConnection();
//		String sql ="";
//		
//		sql ="insert into table () values()";
//		List list = new ArrayList();
//		list.add(finaceOrder.getClothingId());
//		StringBuffer buffSql = new StringBuffer(sql);
//		SqlConvertHelper.sqlRemoveNull(buffSql,list);
//		DBUtilExt.update(buffSql.toString(), list.toArray(),conn);
		 
	}
	
	public void updateWeixinOrderResult(FinaceOrder finaceOrder){
		String outTradeNo =  finaceOrder.getTradeNo(); //获取商品号
	}
	
	public void saveStatemenetOrder(SortedMap<String, String> packageParams){
		WeixinPayAPI.downloadBill(packageParams);
	}
}
