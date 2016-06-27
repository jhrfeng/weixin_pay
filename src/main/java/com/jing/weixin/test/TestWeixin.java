package com.jing.weixin.test;

import java.io.BufferedOutputStream;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jing.weixin.apidemo.WeixinPayAPI;
import com.jing.weixin.httputil.RequestHandler;
import com.jing.weixin.httputil.ResponseHandler;
import com.jing.weixin.utils.WeixinConfig;

import WeiXinResult.WeiXinReq;
import WeiXinResult.WeiXinResult;


@Controller
@RequestMapping("config")
public class TestWeixin {
	
	 @RequestMapping(value="weixinJsApiPay")
	 @ResponseBody
	 public WeiXinResult jsApiOrder(HttpServletRequest request, HttpServletResponse response){
		SortedMap<String, String> packageParams =  new TreeMap<String, String>();
		/**** 报文中必填字段 --> 自定义字段 *****/
		packageParams.put("openid","oD9f8s9hzd8-hugv0lkFMiS22YBc"); //商品ID，NATIVE必填
		packageParams.put("total_fee", "1"); //总金额	
		packageParams.put("body", "太平押金"); //商品描述
		packageParams.put("total_fee", "1"); //总金额
		packageParams.put("spbill_create_ip", "192.168.2.1"); // 终端IP
		String result = WeixinPayAPI.jsApiOrder(packageParams);
			
		return new WeiXinResult(result, "111");
	 }
	 
	 @RequestMapping(value="weixinNativePay")
	 @ResponseBody
	 public WeiXinResult nativeOrder(WeiXinReq params,
			 HttpServletRequest request, HttpServletResponse response){
		SortedMap<String, String> packageParams =  new TreeMap<String, String>();
		/**** 报文中必填字段 --> 自定义字段 *****/
		packageParams.put("total_fee", "1"); //总金额	
		packageParams.put("body", "太平押金"); //商品描述
	//	packageParams.put("total_fee", (params.getTotalNum()*100)+""); //总金额
		packageParams.put("total_fee", params.getTotalNum()+""); //总金额
		packageParams.put("spbill_create_ip", "192.168.1.1"); // 终端IP
		packageParams.put("product_id","TPYX001"); //商品ID，NATIVE必填
		
		String result = WeixinPayAPI.nativeOrder(packageParams);
		
		return new WeiXinResult(result, "111");
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
        //判断签名是否正确
        if(resHandler.isTenpaySign()) {
            //------------------------------
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
            System.out.println("------------------weixinPay_result---------------------");
        } else{
            System.out.println("通知签名验证失败");
        }
    }
}
