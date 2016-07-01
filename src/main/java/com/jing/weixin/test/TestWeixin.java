package com.jing.weixin.test;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jing.weixin.apidemo.WeixinPayAPI;
import com.jing.weixin.entity.FinaceOrder;
import com.jing.weixin.entity.WeiXinPayResult;
import com.jing.weixin.entity.WeiXinReq;
import com.jing.weixin.entity.WeiXinResult;
import com.jing.weixin.httputil.RequestHandler;
import com.jing.weixin.httputil.ResponseHandler;
import com.jing.weixin.service.WeixinService;
import com.jing.weixin.serviceImp.WeixinServiceImp;
import com.jing.weixin.utils.WeixinConfig;




@Controller
@RequestMapping("config")
public class TestWeixin {
	
	@Autowired
	private WeixinServiceImp weiXinService;
	
	 @RequestMapping(value="weixinJsApiPay")
	 @ResponseBody
	 public WeiXinResult jsApiOrder(HttpServletRequest request, HttpServletResponse response){
		FinaceOrder finaceOrder = new FinaceOrder();
		SortedMap<String, String> packageParams =  new TreeMap<String, String>();
		/**** 报文中必填字段 --> 自定义字段 *****/
		packageParams.put("openid","oD9f8s9hzd8-hugv0lkFMiS22YBc"); //商品ID，NATIVE必填
		packageParams.put("total_fee", "1"); //总金额	
		packageParams.put("body", "太平押金"); //商品描述
		packageParams.put("total_fee", "1"); //总金额
		packageParams.put("spbill_create_ip", "192.168.2.1"); // 终端IP
		WeiXinResult result = WeixinPayAPI.jsApiOrder(packageParams);
		return result;
	 }
	 
	 @RequestMapping(value="weixinNativePay")
	 @ResponseBody
	 public WeiXinResult nativeOrder(WeiXinReq params,
			 HttpServletRequest request, HttpServletResponse response){
		FinaceOrder finaceOrder = new FinaceOrder(params);
		SortedMap<String, String> packageParams =  new TreeMap<String, String>();
		String outTradeNo = RequestHandler.getRandomDateNo();
		/**** 报文中必填字段 --> 自定义字段 *****/
		packageParams.put("total_fee", "1"); //总金额	
		packageParams.put("body", params.getRemark()); //商品描述
	//	packageParams.put("total_fee", (params.getTotalNum()*100)+""); //总金额
		packageParams.put("total_fee", params.getTotalNum()+""); //总金额
		packageParams.put("spbill_create_ip", request.getRemoteHost()); // 终端IP
		packageParams.put("product_id","TPYX001"); //商品ID，NATIVE必填
		packageParams.put("out_trade_no",outTradeNo); //商品订单号
		/**** 报文中非必填字段  *****/
		packageParams.put("attach", params.getDeptId()+","+params.getDeptName());//附加数据
		
		WeiXinResult result = WeixinPayAPI.nativeOrder(packageParams);
		
		finaceOrder.setTradeNo(outTradeNo);	
		finaceOrder.setCodeUrl(result.getUrlCode());
		finaceOrder.setPrepayId(result.getPrayId());
		finaceOrder.setResultCode(result.getResultCode());
		finaceOrder.setReturnCode(result.getReturnCode());
		finaceOrder.setAppid(result.getAppid());
		finaceOrder.setMchId(result.getMchId());
		finaceOrder.setNonceStr(result.getNonceStr());
		finaceOrder.setTradeType(result.getTradeType());
		try {
			weiXinService.saveWeixinOrderInfo(finaceOrder);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	 }
	
	 @RequestMapping(value = "weixinPay_result")
	 public void wechatOrderBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //---------------------------------------------------------
        //财付通支付通知（后台通知）
        //---------------------------------------------------------
        //商户号
        String partner = WeixinConfig.MCH_ID;
        //密钥
        String key = WeixinConfig.KEY;
        //创建支付应答对象
        ResponseHandler resHandler = new ResponseHandler(request, response);
        resHandler.setKey(key);
        System.out.println("-----11----");
        //判断签名是否正确
        if(resHandler.isTenpaySign()) {
            //------------------------------
        	SortedMap<String, String> packageParams = resHandler.getAllParameters();
            //处理业务开始
            //------------------------------
            String resXml = "";
            if("SUCCESS".equals(resHandler.getParameter("result_code"))){
                // 同步返回给微信参数
				// resHandler.sendToCFT("SUCCESS");
				//通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            } else {
                System.out.println("支付失败,错误信息：" + resHandler.getParameter("err_code"));
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }
            //------------------------------
            //处理业务完毕
            //------------------------------
            BufferedOutputStream out = new BufferedOutputStream(
                    response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
            System.out.println("------------------weixinPay_result---------------------\n");
            System.out.println(packageParams.get("mch_id"));
            System.out.println(packageParams);
//            WeiXinPayResult result = new WeiXinPayResult();
            weiXinService.updateWeixinOrderResult(packageParams);
 //           resHandler.getHttpServletResponse().sendRedirect("test.html");
//            response.sendRedirect("test.html");
        } else{
            System.out.println("通知签名验证失败");
        }
    }
	 
	/**
	  * 查询订单
	  * @param request
	  * @param response
	  * @return
	  */
    @RequestMapping(value="ordersQuery")
	 @ResponseBody
	 public WeiXinResult ordersQuery(HttpServletRequest request, HttpServletResponse response){
		 SortedMap<String, String> packageParams =  new TreeMap<String, String>();
		/**** 报文中必填字段 --> 自定义字段 *****/
		
		 WeiXinResult result = WeixinPayAPI.ordersQuery(packageParams);
			
		return result;
	 } 
    
    /**
     * 关闭订单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="closeOrder")
	 @ResponseBody
	 public WeiXinResult closeOrder(HttpServletRequest request, HttpServletResponse response){
		 SortedMap<String, String> packageParams =  new TreeMap<String, String>();
		/**** 报文中必填字段 --> 自定义字段 *****/
		
		 WeiXinResult result = WeixinPayAPI.closeOrder(packageParams);
			
		return result;
	 } 
    /**
     * 申请退款
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="refundOrder")
	 @ResponseBody
	 public WeiXinResult refundOrder(HttpServletRequest request, HttpServletResponse response){
		 SortedMap<String, String> packageParams =  new TreeMap<String, String>();
		/**** 报文中必填字段 --> 自定义字段 *****/
		
		 WeiXinResult result = WeixinPayAPI.refundOrder(packageParams);
			
		return result;
	 } 
    
    /**
     * 查询退款
     * @param request
     * @param response
     * @return
     */
     @RequestMapping(value="refundQuery")
	 @ResponseBody
	 public WeiXinResult refundQuery(HttpServletRequest request, HttpServletResponse response){
		 SortedMap<String, String> packageParams =  new TreeMap<String, String>();
		/**** 报文中必填字段 --> 自定义字段 *****/
		
		 WeiXinResult result = WeixinPayAPI.refundQuery(packageParams);
			
		return result;
	 } 
    
    @RequestMapping(value="index")
    public String index(){
    	System.out.println("-------------index---------");
    	return "test/test";
    }
    
    @RequestMapping(value="index4")
    public void index4(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	System.out.println("-------------index---------");
    	resp.sendRedirect("test.html");
    }

}
