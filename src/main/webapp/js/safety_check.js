
//验证手机验证码
var checkPhoneCode = function () {
    //初始化
	function init() {
		//发送手机验证短信
		sendSms();
		    //短信验证
		$("input[name='sure']").bind("click", function () {
			validatePhonecode();
		});
		    //重新发送手机验证短信
		$("input[name='resend']").bind("click", function () {
			disableInput();
			sendSms();
		});
		    //弹出框关闭事件 
		$("div.yz_close").bind("click", function () {
			$("div#dialog").hide();
		});
	}
		//短信验证
	function validatePhonecode() {
		   //数据验证
		var valConfig = "[{\"name\":\"idengCode\",\"label\":\"\u77ed\u4fe1\u9a8c\u8bc1\u7801\",\"tip\":\"\u8bf7\u8f93\u5165\u77ed\u4fe1\u9a8c\u8bc1\u7801\",\"required\":true}]";
		var validateResult = $TOOLS.validate(valConfig);
		if ("" != validateResult) {
			showMessage(validateResult);
			return false;
		}
	        //验证手机短信
		var result = $TOOLS.ajaxComm("validatePhonecode.html", {idengCode:$TOOLS.trimLR($("input[name='idengCode']").val())}, "POST", "JSON");
		switch (result.responseCode) {
		  case "0":
		  /*
			var href = window.location.href;
			var pcsign = "&";
			if ($TOOLS.isExistParamOfUrl("pcguid")) {
				href = $TOOLS.getDelParamOfUrl(href, "pcguid");
			}
			if (href.indexOf("?") == -1) {
				pcsign = "?";
			}
			window.location.href = href + pcsign + "pcguid=" + result.result;
			*/
			window.location.reload();
			break;
		  default:
			showMessage(result.msg);
			break;
		}
	}
		//发送手机验证短信
	function sendSms() {
		var result = $TOOLS.ajaxComm("sendPhonecode.html", {idengCode:$TOOLS.trimLR($("input[name='idengCode']").val())}, "POST", "JSON");
		switch (result.responseCode) {
		  case "0":
				   //300秒倒计时 60秒内 限制重发操作
			countDown();
			break;
		  default:
			showMessage(result.msg);
			break;
		}
	}
	var wait = 300;
	var wait1 = 59;
	var time;
	   //倒计时
	function countDown() {
		if (wait == 0) {
			wait = 0;
		} else {
			wait--;
			$(".yzmsr_time").find("font").text(wait);
			time = setTimeout(function () {
				countDown();
			}, 1000);
		}
		$(".content").find(".reset_div:eq(1)").find("input").attr("disabled", true);
		$(".content").find(".reset_div:eq(1)").find("input").val("\u91cd\u65b0\u53d1\u9001\u9a8c\u8bc1\u7801(\u7b49\u5f85" + wait1 + "\u79d2)");
		if (wait1 == 0) {
			wait1 = 0;
			$(".content").find(".reset_div:eq(1)").find("input").attr("disabled", false);
			$(".content").find(".reset_div:eq(1)").find("input").val("\u91cd\u65b0\u53d1\u9001\u9a8c\u8bc1\u7801");
		} else {
			wait1--;
		}
	}
     //使click失效
	function disableInput() {
		if (wait <= 240) {
			clearTimeout(time);
			wait = 300;
			wait1 = 60;
			$(".content").find(".reset_div:eq(1)").find("input").attr("disabled", false);
			countDown();
		}
	}
	//展示消息
	function showMessage(msg) {
		$("div#dialog").find("div[name='msg']").text(msg);
		$("div#dialog").show();
	}
	return {init:function () {
		init();
	}};
}();
window.$CPC = checkPhoneCode;
//验证用户交易密码
var checkUserPassword = function () {
	function init() {
		$("input[name='checkPwd']").bind("click", function () {
			validateUserPwd();
		});
		$(".reget_buton").bind("click", function () {
			validateUserPwd();
		});
	    //弹出框关闭事件 
		$("div.yz_close").bind("click", function () {
			$("div#dialog").hide();
		});
	}
	
	//验证用户密码
	function validateUserPwd() {
	    //数据验证
		var valConfig = "[{\"name\":\"keyword\",\"label\":\"\u5bc6\u7801\",\"tip\":\"\u8bf7\u8f93\u5165\u5bc6\u7801\",\"required\":true}]";
		var validateResult = $TOOLS.validate(valConfig);
		if ("" != validateResult) {
			showMessage(validateResult);
			return false;
		}
	    //验证用户密码
		var result = $TOOLS.ajaxComm("validateUserPassword.html", {password:$TOOLS.trimLR($("input[name='keyword']").val())}, "POST", "JSON");
		switch (result.responseCode) {
		  case "0":
			if (result.result.ESERVICE_RETURN_FLAG == "0") {
			/*
				var href = window.location.href;
				var pcsign = "&";
				if ($TOOLS.isExistParamOfUrl("upguid")) {
					href = $TOOLS.getDelParamOfUrl(href, "upguid");
				}
				if (href.indexOf("?") == -1) {
					pcsign = "?";
				}
				window.location.href = href + pcsign + "&upguid=" + result.result.upguid;
				*/
				window.location.reload();
			} else {
				if (result.result.ESERVICE_RETURN_FLAG == "1") {
					showMessage("\u6821\u9a8c\u5bc6\u7801\u5931\u8d25\uff08\u65e0\u4fdd\u5168\u6743\u9650\uff09");
				} else {
					if (result.result.ESERVICE_RETURN_FLAG == "2") {
						showMessage("\u6ca1\u6709\u6743\u9650\uff08\u5bc6\u7801\u6b63\u786e\u65e0\u4fdd\u5168\u6743\u9650\uff09");
					}
				}
			}
			break;
		  default:
			showMessage(result.msg);
			break;
		}
	}
//展示消息
	function showMessage(msg) {
		$("div#dialog").find("div[name='msg']").text(msg);
		$("div#dialog").show();
	}
	return {init:function () {
		init();
	}};
}();
window.$CUP = checkUserPassword;

