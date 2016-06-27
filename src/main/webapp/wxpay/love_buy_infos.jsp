<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>中国太平保险集团公司--购买详情</title>
    <meta name="viewport" content="width=device-width,user-scalable=no;" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script language="javascript" type="text/javascript"
			src="js/jquery-1.9.1.min.js"></script>
    <script language="javascript" src="js/jquery.js"></script>
	<script language="javascript" type="text/javascript" src="js/tools.js"></script>
    <script language="javascript" src="js/lazyloadv3.js"></script>
    <script src="js/md5.js"></script>
    <script src="js/sha1.js"></script>
    <script language="javascript">
	  $(document).ready(function(){
         $TOOLS.hideOptionMenu();
         $("#back").click(function(){
          	  window.history.back();
          });
      });
      //商家测试请修改此四个参数，并将页面放在支付授权目录下，在申请了支付的公众账号访问此页面，方可调起支付。
            //修改开始
            function getPartnerId(){//替换partnerid
                return "1352280301";
            }            
            function getPartnerKey(){//替换partnerkey
                return "d1f20df1b3c654484bc829ecd76800f0";
            }			
            function getAppId(){//替换appid
                return "wxa6cab777e1c735f5";
            }            
            function getAppKey(){//替换appkey
                return "3a569abe4680a59fd4416808c81207cb";
            }		
			//修改到此结束		
            //辅助函数
            function Trim(str,is_global){
                var result;
                result = str.replace(/(^\s+)|(\s+$)/g,"");
                if(is_global.toLowerCase()=="g") result = result.replace(/\s/g,"");
                return result;
            }
            function clearBr(key){
                key = Trim(key,"g");
                key = key.replace(/<\/?.+?>/g,"");
                key = key.replace(/[\r\n]/g, "");
                return key;
            }            
            //获取随机数
            function getANumber(){
                var date = new Date();
                var times1970 = date.getTime();
                var times = date.getDate() + "" + date.getHours() + "" + date.getMinutes() + "" + date.getSeconds();
                var encrypt = times * times1970;
                if(arguments.length == 1){
                    return arguments[0] + encrypt;
                }else{
                    return encrypt;
                }
            }
            //以下是package组包过程：            
            var oldPackageString;//记住package，方便最后进行整体签名时取用       
            function getPackage(){
                var banktype = "WX";
                var body = "测试昌";//商品名称信息，这里由测试网页填入。
                var fee_type = "1"//费用类型，这里1为默认的人民币
                var input_charset = "UTF-8";//字符集，可以使用GBK或者UTF-8
                //生产环境
                var notify_url = "http://ljh5522.xicp.net/wxpay/notify_url";//支付成功后将通知该地址
                //测试环境
                //var notify_url = "http://116.228.52.92/wxqhb/wxpay/payback";//支付成功后将通知该地址
                var out_trade_no = getTimeStamp();//订单号，商户需要保证该字段对于本商户的唯一性
                var partner = getPartnerId();//测试商户号
                var spbill_create_ip = "127.0.0.1";//用户浏览器的ip，这个需要在前端获取。这里使用127.0.0.1测试值
                //生产环境
                var total_fee = "1";//总金额。
                //测试环境
                //var total_fee = "1";//总金额。
                var partnerKey = getPartnerKey();//这个值和以上其他值不一样是：签名需要它，而最后组成的传输字符串不能含有它。这个key是需要商户好好保存的。
                //首先第一步：对原串进行签名，注意这里不要对任何字段进行编码。这里是将参数按照key=value进行字典排序后组成下面的字符串,在这个字符串最后拼接上key=XXXX。由于这里的字段固定，因此只需要按照这个顺序进行排序即可。
                var signString = "bank_type="+banktype+"&body="+body+"&fee_type="+fee_type+"&input_charset="+input_charset+"&notify_url="+notify_url+"&out_trade_no="+out_trade_no+"&partner="+partner+"&spbill_create_ip="+spbill_create_ip+"&total_fee="+total_fee+"&key="+partnerKey;
                var md5SignValue =  ("" + CryptoJS.MD5(signString)).toUpperCase();
                //然后第二步，对每个参数进行url转码，如果您的程序是用js，那么需要使用encodeURIComponent函数进行编码。
                banktype = encodeURIComponent(banktype);
                body=encodeURIComponent(body);
                fee_type=encodeURIComponent(fee_type);
                input_charset = encodeURIComponent(input_charset);
                notify_url = encodeURIComponent(notify_url);
                out_trade_no = encodeURIComponent(out_trade_no);
                partner = encodeURIComponent(partner);
                spbill_create_ip = encodeURIComponent(spbill_create_ip);
                total_fee = encodeURIComponent(total_fee);
                //然后进行最后一步，这里按照key＝value除了sign外进行字典序排序后组成下列的字符串,最后再串接sign=value
                var completeString = "bank_type="+banktype+"&body="+body+"&fee_type="+fee_type+"&input_charset="+input_charset+"&notify_url="+notify_url+"&out_trade_no="+out_trade_no+"&partner="+partner+"&spbill_create_ip="+spbill_create_ip+"&total_fee="+total_fee;
                completeString = completeString + "&sign="+md5SignValue;
                oldPackageString = completeString;//记住package，方便最后进行整体签名时取用
                return completeString;
            }
            //下面是app进行签名的操作：
            var oldTimeStamp ;//记住timestamp，避免签名时的timestamp与传入的timestamp时不一致
            var oldNonceStr ; //记住nonceStr,避免签名时的nonceStr与传入的nonceStr不一致          
            function getTimeStamp(){
                var timestamp=new Date().getTime();
                var timestampstring = timestamp.toString();//一定要转换字符串
                oldTimeStamp = timestampstring;
                return timestampstring;
            }
            function getNonceStr(){
                var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
                var maxPos = $chars.length;
                var noceStr = "";
                for (i = 0; i < 32; i++) {
                    noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
                }
                oldNonceStr = noceStr;
                return noceStr;
            }
            function getSignType(){
                return "SHA1";
            }
            function getSign(){
                var app_id = getAppId().toString();
                var app_key = getAppKey().toString();
                var nonce_str = oldNonceStr;
                var package_string = oldPackageString;
                var time_stamp = oldTimeStamp;
                //第一步，对所有需要传入的参数加上appkey作一次key＝value字典序的排序
                var keyvaluestring = "appid="+app_id+"&appkey="+app_key+"&noncestr="+nonce_str+"&package="+package_string+"&timestamp="+time_stamp;
                sign = CryptoJS.SHA1(keyvaluestring).toString();
                return sign;
            }
	</script>
    <script Language="javascript">
            //当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
            document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
               //公众号支付
               jQuery('input#pay').click(function(e){
                   alert("公众号名称:"+getAppId()+"            "+"时间戳:"+getTimeStamp()+"随机串:"+getNonceStr()+"微信签名方式:"+getSignType()+"微信签名:"+getSign()+"扩展包"+getPackage());
                  WeixinJSBridge.invoke('getBrandWCPayRequest',{
                     "appId" : getAppId(), //公众号名称，由商户传入
                     "timeStamp" : getTimeStamp(), //时间戳
                     "nonceStr" : getNonceStr(), //随机串
                     "package" : getPackage(),//扩展包
                     "signType" : getSignType(), //微信签名方式:1.sha1
                     "paySign" : getSign() //微信签名
                   },function(res){
                   alert("res"+res);
                   alert("res.err_msg"+res.err_msg);
                      if(res.err_msg == "get_brand_wcpay_request:ok") {
                         window.location.href="toTransactionDetail.html?orderId="+$("#pay").attr("orderId");
                      }
                     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                     //因此微信团队建议，当收到ok返回时，向商户后台询问是否收到交易成功的通知，若收到通知，前端展示交易成功的界面；若此时未收到通知，商户后台主动调用查询订单接口，查询订单的当前状态，并反馈给前端展示相应的界面。
                  }); 
                });
                WeixinJSBridge.log('yo~ ready.');
            }, false)
        </script>
  </head>
  
  <body>
     <div class="content04">
  <div class="gmxx_cont">
    <div class="gmxx_top">
      <div class="gmxx_tbg"> </div>
    </div>
    <div class="gmxx_nr">
      <div class="gmxx_nr_tt">购买信息</div>
      <div class="gmxx_nr_grzh">
        <ul class="bdxq_gmxx">
          <li>
            <div class="gmxx_ulleft">投保人姓名：</div>
            <div class="gmxx_ulright">测试</div>
          </li>
          <li>
            <div class="gmxx_ulleft">身份证号：</div>
            <div class="gmxx_ulright">测试</div>
          </li>
          <li>
            <div class="gmxx_ulleft">手机号码：</div>
            <div class="gmxx_ulright">测试</div>
          </li>
        </ul>
        <ul class="bdxq_gmxx ">
          <li>
            <div class="gmxx_ulleft">被保人姓名：</div>
            <div class="gmxx_ulright">测试</div>
          </li>
          <li>
            <div class="gmxx_ulleft">身份证号：</div>
            <div class="gmxx_ulright">测试</div>
          </li>
        </ul>
        <ul class="bdxq_gmxx">
          <li>
            <div class="gmxx_ulleft">产品名称：</div>
            <div class="gmxx_ulright">测试</div>
          </li>
          <li>
            <div class="gmxx_ulleft">投保份数：</div>
            <div class="gmxx_ulright">测试</div>
          </li>
          <li>
            <div class="gmxx_ulleft">产品总价：</div>
            <div class="gmxx_ulright">测试</div>
          </li>
        </ul>
      </div>
      <div class="gmxx01">
    
        <div class="gmxx01_tt">
          <div class="gm_tleft" style="width: 44%;">保险期间</div>
          <div class="gm_tright" style="width: 55%;">每份保额</div>
          <div style="clear:both"></div>
        </div>
   
        
     
        <div class="gmxx01_cont">
        <!-- 根据用户购买的险种类型展示对应保额 -->
        <!-- 跳转到该页面的属性值设置为(orderInfo) -->
        <!-- orderInfo的id值根据后台设定的值调整 -->
      
        	
        
        	
        	
        </div>
      </div>
      
      
     
      <!-- end -->
    </div>
    <div class="gmxx_nr_bottom"></div>
    <div class="anjianxx_botton">
      <div class="yes_no" style="display: ">
        <div class="yes_div">
          <input type="button" class="reget_btn" value="返回修改" id="back"/>
        </div>
        <div class="no_div">
          <input  type="button" class="reget_btn" value="确 认" id="pay"/>
        </div>
        <div style="clear:both"></div>
      </div>
    </div>
  <div class="blank"></div>
</div></div>
  </body>
</html>
