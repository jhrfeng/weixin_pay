package com.jing.weixin.serviceImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.UUID;

import org.springframework.stereotype.Service;










import com.cntaiping.intserv.basic.runtime.db.DBUtil;
import com.cntaiping.intserv.basic.runtime.db.DBUtilExt;
import com.jing.weixin.apidemo.WeixinPayAPI;
import com.jing.weixin.entity.FinaceOrder;
import com.jing.weixin.entity.WeiXinPayResult;
import com.jing.weixin.service.WeixinService;
import com.jing.weixin.utils.SqlConvertHelper;

@Service("WeixinService")
public class WeixinServiceImp implements WeixinService{

	public void saveWeixinOrderInfo(FinaceOrder finaceOrder) throws SQLException{
		finaceOrder.setSync("0");
		finaceOrder.setStatus("0");
		finaceOrder.setWeixinStatus("0");
		finaceOrder.setCreateDate("20160101");
		finaceOrder.setModifyDate("20160101");
		
		Connection conn = DBUtil.getConnection();
		String sql ="";
		sql ="insert into table_XXX(clothing_id,clothing_name,create_date,finace_id,idcard,mobile,modify_date,name,org_id,org_name,remark,status,sync,total_num,trade_no,"+
		"url_id,result_code,mch_id,prepay_id,appid,code_url,nonce_str,return_code,trade_type) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<String> list = new ArrayList<String>();
		list.add(finaceOrder.getClothingId());
		list.add(finaceOrder.getClothingName());
		list.add(finaceOrder.getCreateDate());
		list.add(finaceOrder.getFinaceId());
		list.add(finaceOrder.getIdcard());
		list.add(finaceOrder.getMobile());
		list.add(finaceOrder.getModifyDate());
		list.add(finaceOrder.getName());
		list.add(finaceOrder.getOrgId());
		list.add(finaceOrder.getOrgName());
		list.add(finaceOrder.getRemark());
		list.add(finaceOrder.getStatus());
		list.add(finaceOrder.getSync());
		list.add(finaceOrder.getTotalNum().toString());
		list.add(finaceOrder.getTradeNo());
		list.add(finaceOrder.getUrlId());
		list.add(finaceOrder.getResultCode());
		list.add(finaceOrder.getMchId());
		list.add(finaceOrder.getPrepayId());
		list.add(finaceOrder.getAppid());
		list.add(finaceOrder.getCodeUrl());
		list.add(finaceOrder.getNonceStr());
		list.add(finaceOrder.getReturnCode());
		list.add(finaceOrder.getTradeType());
//		list.add(finaceOrder.getWeixinOrder());
//		list.add(finaceOrder.getWeixinStatus());
		
		StringBuffer buffSql = new StringBuffer(sql);
		SqlConvertHelper.sqlRemoveNull(buffSql,list);
		DBUtilExt.update(buffSql.toString(), list.toArray(),conn);
		 
	}
	
	public void updateWeixinOrderResult(SortedMap<String, String> result) throws SQLException{
		String outTradeNo =  result.get("out_trade_no"); //商户订单号
		String tSql = "select count(1) result_flag from table_XX where out_trade_no = ?";
		Map resultMap = DBUtilExt.querySingle(tSql, outTradeNo);
		int resultFlag = 0;
		if(resultMap !=null && resultMap.size()>0){
			resultFlag = (Integer) resultMap.get("result_flag");
		}
		if(resultFlag>0){
			StringBuffer sql = new StringBuffer("update table_XX set appid=?,bank_type=?,cash_fee=?,fee_type=?,is_subscribe=?,");
			sql.append(" mch_id=?,nonce_str=?,openid=?,result_code=?,return_code=?,time_end=?,total_fee=?,trade_type=?,transaction_id=?,weixin_status='1'");
			sql.append(" where out_trade_no=?");
			List<String> list = new ArrayList<String>();
			list.add(result.get("appid"));
			list.add(result.get("bank_type"));
			list.add(result.get("cash_fee"));
			list.add(result.get("fee_type"));
			list.add(result.get("is_subscribe"));
			list.add(result.get("mch_id"));
			list.add(result.get("nonce_str"));
			list.add(result.get("openid"));
			list.add(result.get("result_code"));
			list.add(result.get("return_code"));
			list.add(result.get("time_end"));
			list.add(result.get("total_fee"));
			list.add(result.get("trade_type"));
			list.add(result.get("transaction_id"));
			list.add(result.get("out_trade_no"));
			DBUtilExt.update(sql.toString(),list.toArray());
		}else{
			System.out.println("支付失败");
		}
		
	}
	
	public void saveStatemenetOrder(SortedMap<String, String> packageParams){
		String result =WeixinPayAPI.downloadBill(packageParams);
		
	}
}
