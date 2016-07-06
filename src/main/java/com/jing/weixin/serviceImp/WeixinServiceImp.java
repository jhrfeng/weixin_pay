package com.jing.weixin.serviceImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import com.jing.weixin.entity.DailySummary;
import com.jing.weixin.entity.FinaceOrder;
import com.jing.weixin.entity.WeiXinBill;
import com.jing.weixin.entity.WeiXinLog;
import com.jing.weixin.entity.WeiXinPayResult;
import com.jing.weixin.httputil.RequestHandler;
import com.jing.weixin.service.WeixinService;
import com.jing.weixin.utils.SqlConvertHelper;

@Service("WeixinService")
public class WeixinServiceImp implements WeixinService{
	
	@Override  
	public void saveWeixinOrderInfo(FinaceOrder finaceOrder) throws SQLException{
		
		//记录操作日志
		WeiXinLog weiXinLog=new WeiXinLog();
		weiXinLog.setOptStart(RequestHandler.newDate());
		weiXinLog.setName("保存微信订单");
		weiXinLog.setAction("saveWeixinOrderInfo");
		
		
		
		finaceOrder.setSync("0");
		finaceOrder.setStatus("0");
		finaceOrder.setWeixinStatus("0");
		finaceOrder.setCreateDate(RequestHandler.newDate());
		finaceOrder.setModifyDate(RequestHandler.newDate());
		
		Connection conn = DBUtil.getConnection();
		String sql ="";
		sql ="insert into table_XXX(dept_id,dept_name,create_date,gl_organ,idcard,mobile,modify_date,name,organ_id,company_name,remark,status,sync,total_num,trade_no,"+
		"url_id,result_code,mch_id,prepay_id,appid,code_url,nonce_str,return_code,trade_type) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<String> list = new ArrayList<String>();
		list.add(finaceOrder.getDeptId());
		list.add(finaceOrder.getDeptName());
		list.add(finaceOrder.getCreateDate());
		list.add(finaceOrder.getGlOrgan());
		list.add(finaceOrder.getIdcard());
		list.add(finaceOrder.getMobile());
		list.add(finaceOrder.getModifyDate());
		list.add(finaceOrder.getName());
		list.add(finaceOrder.getOrganId());
		list.add(finaceOrder.getCompanyName());
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
		try {
			DBUtilExt.update(buffSql.toString(), list.toArray(),conn);
			weiXinLog.setFlag(1+"");
			weiXinLog.setMessage("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			weiXinLog.setFlag(0+"");
			weiXinLog.setMessage(e.getMessage());
		}
		//调用添加日志表的方法
		saveWeixinWeiXinLog(weiXinLog);
	}
	
	@Override  
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
	
	@Override
	public void saveStatemenetOrder(SortedMap<String, String> packageParams) throws SQLException{
		String jsonStr =WeixinPayAPI.downloadBill(packageParams);
		List<WeiXinBill> billlist = new ArrayList<WeiXinBill>();
		jsonStr = jsonStr.replace(",", "");
		jsonStr = jsonStr.replaceAll("\r|\n", "");
		String sumStr = jsonStr.substring(jsonStr.indexOf("手续费总金额")+6,jsonStr.length());
		jsonStr = jsonStr.substring(jsonStr.indexOf("费率")+2, jsonStr.indexOf("总交易单数"));
		String[] li = jsonStr.split("`");
		String[] suml = sumStr.split("`");
		DailySummary summary = new DailySummary();
		summary.setTradeTime(li[1]);
		summary.setTradeNum(suml[1]);
		summary.setTradeSum(suml[2]);
		summary.setFeeSum(suml[3]);
		summary.setCompanySum(suml[4]);
		summary.setTotalSum(suml[5]);
		
		WeiXinBill bill = new WeiXinBill();
		int count = (li.length)/24;
		for(int i=0;i<count;i++){
			for(int j=1;j<li.length;j++){
				bill.setTradeTime(li[j]);
				bill.setAppid(li[j+1]);
				bill.setMchId(li[j+2]);
				bill.setSubMchId(li[j+3]);
				bill.setDeviceInfo(li[j+4]);
				bill.setTransactionId(li[j+5]);
				bill.setOutTradeNo(li[j+6]);
				bill.setOpenid(li[j+7]);
				bill.setTradeType(li[j+8]);
				bill.setTradeState(li[j+9]);
				bill.setBankType(li[j+10]);
				bill.setFeeType(li[j+11]);
				bill.setTotalFee(li[j+12]);
				bill.setCouponFee(li[j+13]);
				bill.setRefundId(li[j+14]);
				bill.setOutRefundNo(li[j+15]);
				bill.setSettlementRefundFee(li[j+16]);
				bill.setCouponRefundFee(li[j+17]);
				bill.setRefundType(li[j+18]);
				bill.setRefundStatus(li[j+19]);
				bill.setCommodityname(li[j+20]);
				bill.setAttach(li[j+21]);
				bill.setFee(li[j+22]);
				bill.setRate(li[j+23]);
				billlist.add(bill);
				j=j+24;
				break;
			}
		}
		
		//保存每日汇总账单
		String sql ="";
		sql = "insert into table(trade_time,trade_num,trade_sum,fee_sum,company_sum,total_sum,create_date,modify_date,status,sync)"+
		"values(?,?,?,?,?,?,?,?,?,?)";
		List<String> sqlList = new ArrayList<String>();
		sqlList.add(summary.getTradeTime());
		sqlList.add(summary.getTradeNum());
		sqlList.add(summary.getTradeSum());
		sqlList.add(summary.getFeeSum());
		sqlList.add(summary.getCompanySum());
		sqlList.add(summary.getTotalSum());
		sqlList.add(RequestHandler.newDate());
		sqlList.add(RequestHandler.newDate());
		sqlList.add("0");
		sqlList.add("0");
		StringBuffer buffSql = new StringBuffer(sql);
		SqlConvertHelper.sqlRemoveNull(buffSql,sqlList);
		DBUtilExt.update(buffSql.toString(), sqlList.toArray());
	}

	
	@Override
	public void saveWeixinWeiXinLog(WeiXinLog weiXinLog) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql ="";
		sql = "insert into wx_log(tid,name,action,flag,message,opt_start,opt_end)"+
		"values(?,?,?,?,?,?,?)";
		List<String> sqlList = new ArrayList<String>();
		sqlList.add(UUID.randomUUID()+"");
		sqlList.add(weiXinLog.getName());
		sqlList.add(weiXinLog.getAction());
		sqlList.add(weiXinLog.getFlag());
		sqlList.add(weiXinLog.getMessage());
		sqlList.add(weiXinLog.getOptStart());
		sqlList.add(RequestHandler.newDate());
		StringBuffer buffSql = new StringBuffer(sql);
		SqlConvertHelper.sqlRemoveNull(buffSql,sqlList);
		DBUtilExt.update(buffSql.toString(), sqlList.toArray());
	}
}
