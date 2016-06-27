var policy = function(){
   return{
        //获得全部保单列表信息
        getPolicyList:function(){
            var data = $TOOLS.ajaxComm("query/getPolicyList",null,"POST","JSON");
            $POLICY.getData(data);

        },     
        getVIPInfo:function(){
        	var data = $TOOLS.ajaxComm("query/getVIPInfo",null,"POST","JSON");
        	var custLevel="&nbsp;";
        	$.each(data.result,function(index,result){
        		if(result.custLevel==1){
        			custLevel="VIP客户-黄金级";
        		}else if(result.custLevel==2){
        			custLevel="VIP客户-铂金级";
        		}else if(result.custLevel==3){
        			custLevel="VIP客户-钻石级";
        		}
        		$("#sp_amt").append(result.custPoints);
        		$("#sp_level").append(custLevel);
        	})
        },                                                                                                                                                                                                       
        //跳转到保单详情页面
        gotoPolicyInfo:function(){
            $(".content").find(".bdhflb").each(function(){
                $(this).find(".bdhf_cont").find(".bdhf_cont_right").find("input").bind("click",function(){
                   window.location.href="policyInfo.html?policyCode="+$(this).attr("id");
                }) 
            })
       },
       //获得保单详情
       getPolicyInfo:function(){
          var policyCode = $POLICY.getPolicyCode();
	      var param = {
	         policyCode:policyCode
	      };
	      var data = $TOOLS.ajaxComm("query/getPolicyInfo",param,"POST","JSON");
	      $POLICY.getPolicyOrCusInfo(data);
	      $POLICY.showProduct();
       },
       //显示险种信息
       showProduct:function(){
       
          $(".content03").find(".bdxqxx").find(".bdxqxx_nr").find("h2").bind("click",function(){
                if($(this).parent().find("ul").css("display")=="none"){
       	  			// 险种收缩按键绑定
                	if ($(this).hasClass("bdxq_jxzxxicon_off")) {
	                    $(this).removeClass("bdxq_jxzxxicon_off");
	                    $(this).addClass("bdxq_jxzxxicon");
                    }
          			// 信件发送方式收缩按键绑定
                    if ($(this).hasClass("bdxq_xjfsicon_off")) {
	                    $(this).removeClass("bdxq_xjfsicon_off");
	                    $(this).addClass("bdxq_xjfsicon");
                    }
                    $(this).parent().find("ul").css("display","block");
                }else{
                	if ($(this).hasClass("bdxq_jxzxxicon")) {
	                    $(this).removeClass("bdxq_jxzxxicon");
	                    $(this).addClass("bdxq_jxzxxicon_off");
                    }
                    if ($(this).hasClass("bdxq_xjfsicon")) {
	                    $(this).removeClass("bdxq_xjfsicon");
	                    $(this).addClass("bdxq_xjfsicon_off");
                    }
                    $(this).parent().find("ul").css("display","none");
                }
          })
       },
       //跳转到客户详情页面
       gotoCustomerInfo:function(){
          $(".content").find(".bdhflb").each(function(){
                $(this).find(".bdhf_cont").find(".bdhf_cont_right").find("input").bind("click",function(){
                   window.location.href="customerInfo.html?policyCode="+$(this).attr("id");
                }) 
            })
       },
       //获得客户详细信息
       getCustomerInfo:function(){
           var policyCode = $POLICY.getPolicyCode();
	       var param = {
	          policyCode:policyCode
	       };
	       var data = $TOOLS.ajaxComm("query/getCustomerInfo",param,"POST","JSON");
	       $POLICY.getPolicyOrCusInfo(data);
       },
       //跳转到结算账户详细信息页面
       gotoVerifyPassword:function(){
          $(".content").find(".bdhflb").each(function(){
                $(this).find(".bdhf_cont").find(".bdhf_cont_right").find("input").bind("click",function(){
                   window.location.href="settleAccountInfo.html?policyCode="+$(this).attr("id");
                }) 
            })
       },
       //获得结算账户信息
       getSettleAccountInfo:function(){
	       var data = $TOOLS.ajaxComm("query/settleAccountInfo.html",null,"POST","JSON");
	       switch(data.responseCode){
	           case "0":
	              $(".content04").find(".blank:eq(0)").after(data.result);
	              break;
               default:
                  var div=$("<div>").addClass("jszh_cont").append($("<div>").addClass("jszh_top").append($("<div>").addClass("jszh_tbg")),$("<div>").addClass("jszh_nr").append($("<div>").addClass("jszh_nr_pgxx").append($("<div>").addClass("jszh_grzh_left").css({"font-size":"20px","width":"100%"}).text(data.msg))),$("<div>").addClass("jszh_nr_bottom"))
	              $(".content04").find(".blank:eq(0)").after(div);
                  break;
           }
       },
       //获得分红账户全部保单列表信息
       getBounsAccountList:function(){
            var data = $TOOLS.ajaxComm("query/getBounsAccountList",null,"POST","JSON");
            $POLICY.getData(data);
        },
       gotobounsAccountPwd:function(){
           $(".content").find(".bdhflb").each(function(){
                $(this).find(".bdhf_cont").find(".bdhf_cont_right").find("input").bind("click",function(){
                   window.location.href="bounsAccountInfo.html?policyCode="+$(this).attr("id");
                }) 
            })
       },
       //给页面时间赋初始值
       getTime:function(){
           var data = new Date();
           var day = data.getDate();
           var month = data.getMonth();
           month = month+1;
           //日期改为yyyy-m-d格式，且默认时间为去年今日至今天
           $(".dkzhxx_lable01").find("input").val((data.getFullYear()-1).toString()+"-"+month.toString()+"-"+day.toString()+""+"");
           $(".dkzhxx_lable02").find("input").val(data.getFullYear().toString()+"-"+month.toString()+"-"+day.toString()+""+"");
       },
       //获得分红账户信息
       getBounsAccountInfo:function(){
         var START_DATE = $(".dkzhxx_lable01").find("input").val();
         var END_DATE = $(".dkzhxx_lable02").find("input").val();
         if(START_DATE==""||END_DATE==""){
            $("div#dialog").find("div[name='msg']").text("分配日期不能为空！");
			$("div#dialog").show();
			return false;
         }
         if(START_DATE>END_DATE){
            $("div#dialog").find("div[name='msg']").text("开始时间不能大于结束时间！");
			$("div#dialog").show();
			return false;
         }
	     var paramVal = {
	        START_DATE:$TOOLS.trim(START_DATE),
	        END_DATE:$TOOLS.trim(END_DATE)
	     };
	     var param = {
	         paramVal:JSON.stringify(paramVal)
	     };
	     var data = $TOOLS.ajaxComm("query/bounsAccountInfo.html",param,"POST","JSON");
	     switch(data.responseCode){
	         case "0":
	              $(".content").find(".bdxxcx_cxnr").remove(); 
	              $(".content").find(".bdxxcx_bdhm:eq(1)").after(data.result);
	              break;
	         default:
                  $(".content").find(".bdxxcx_cxnr").remove();
	              var div =$("<div>").addClass("bdxxcx_cxnr").append($("<ul>").addClass("bdxxcx_ul").append($("<li>").append($("<div>").addClass("bdxxcx_ul_left").css({"font-size":"20px","width":"100%"}).text(data.msg))));
	              $(".content").find(".bdxxcx_bdhm:eq(1)").after(div);
                  break;
          }
       },
       gotoLoadAccountPwd:function(){
          $(".content").find(".bdhflb").each(function(){
                $(this).find(".bdhf_cont").find(".bdhf_cont_right").find("input").bind("click",function(){
                   window.location.href="loanAccountInfo.html?policyCode="+$(this).attr("id");
                }) 
            })
       },
       //获得贷款账户信息
       getLoanAccountInfo:function(){ 
          var START_DATE = $(".dkzhxx_lable01").find("input").val();
          var END_DATE = $(".dkzhxx_lable02").find("input").val();
          if(START_DATE==""||END_DATE==""){
              $POLICY.showFail("分配日期不能为空！");
              $POLICY.closeFail();
              return;
          }
          if(START_DATE>END_DATE){
            $POLICY.showFail("开始时间不能大于结束时间！");
            $POLICY.closeFail();
            return;
          }
	      var  paramVal= {
	           START_DATE:$TOOLS.trim(START_DATE),
	           END_DATE:$TOOLS.trim(END_DATE)
	      }
	      var param = {
	         paramVal:JSON.stringify(paramVal)
	      };
	      var data = $TOOLS.ajaxComm("query/loanAccountInfo.html",param,"POST","JSON");
	      switch(data.responseCode){
	          case "0":
	               $(".content").find(".bdxxcx_cxnr").remove();
	               $(".content").find(".qrbg_btn").before(data.result);
	               break;
	         default:
                  $(".content").find(".bdxxcx_cxnr").remove();
	              var div =$("<div>").addClass("bdxxcx_cxnr").append($("<ul>").addClass("bdxxcx_ul").append($("<li>").append($("<div>").addClass("bdxxcx_ul_left").css({"font-size":"20px","width":"100%"}).text(data.msg))));
	              $(".content").find(".qrbg_btn").before(div);
                  break;
	       }
       },
       //跳转到保全信息页面
       gotoGuardInfo:function(){
          $(".content").find(".bdhflb").each(function(){
                $(this).find(".bdhf_cont").find(".bdhf_cont_right").find("input").bind("click",function(){
                   window.location.href="guardInfo.html?policyCode="+$(this).attr("id");
                }) 
           })
       },
       //获得保全信息
       getGuardInfo:function(){
           var policyCode = $POLICY.getPolicyCode();
	       var param = {
	          policyCode:policyCode
	       };
	       var data = $TOOLS.ajaxComm("query/getGuardInfo",param,"POST","JSON");
	       switch(data.responseCode){
	         case "0":
	               $(".content").find(".bdxxcx_cxnr").remove();
	               $(".content").find(".qrbg_btn").before(data.result);
	               break;
	         default:
                  $(".content").find(".bdxxcx_cxnr").remove();
	              var div =$("<div>").addClass("bdxxcx_cxnr").append($("<ul>").addClass("bdxxcx_ul").append($("<li>").append($("<div>").addClass("bdxxcx_ul_left").css({"font-size":"20px","width":"100%"}).text(data.msg))));
	              $(".content").find(".qrbg_btn").before(div);
                  break;
	       }
       },

       //跳转到保全的批文信息
       gotoGuardApproval:function(){
         $("a.more").bind("click",function(){
             window.location.href="approvalInfo.html?changeId="+$(this).attr("id");
         });
       
       },
       //通过保全Id获的变更记录
       getRecordByChangeId:function(){
           var reg = new RegExp("(^|&)" + "changeId" + "=([^&]*)(&|$)", "i");
	       var r = window.location.search.substr(1).match(reg);
	       if (r != null) {
		      var changeId = r[0].substring(r[0].indexOf("=")+1);
	       }
	       var param ={
	          changeId:changeId
	       };
	       var data = $TOOLS.ajaxComm("query/getRecordByChangeId",param,"POST","JSON");
	       switch(data.responseCode){
	         case "0":
	              $(".bd_tt").after(data.result);
	               break;
	         default:
                  var div = $("<div>").addClass("neirong").append($("<div>").addClass("pwxx_text").text(data.msg));
                  break;
	       }
       },
       //返回保全信息列表页面
       backToGuardList:function(){
          $(".qrbg_btn").find(".reset_div").find("input").bind("click",function(){
               window.location.href="policyTransRecordList.html";
          })
       
       },
       //获得客户姓名和保单号
       getNameAndPolicyCode:function(){
       	   var r = window.location.toString();
           //var policyCode = $("input[name='policyCode']").val();
           var policyCode = $POLICY.getPolicyCode();
	       var param = {
	          policyCode:policyCode
	       };
	       /* 2014-02-17 */
	       
	       var data = $TOOLS.ajaxComm("query/getCustomer",param,"POST","JSON");
	       switch(data.responseCode){
	          case "0":
	            if(data.result.ESERVICE_RETURN_FLAG==0){
	               if(data.result.RESULT_LIST.length>0){
	                   //判断是不是贷款账户页面
	                   if(r.indexOf("loanAccountInfo",10)=="-1"){
	                     $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text(data.result.RESULT_LIST[0].realName);
	                     $(".content").find(".bdxxcx_bdhm:eq(0)").find(".dkzhxx_ttright").text(data.result.RESULT_LIST[0].policyCode);
	                   }else{
	                     $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text(data.result.RESULT_LIST[0].realName);
	                     // HG增加
	                     $(".content").find(".bdxxcx_bdhm:eq(0)").find(".dkzhxx_ttright").text(data.result.RESULT_LIST[0].policyCode);
	                   }
	               }else{
	                   //判断是不是贷款账户页面
	                   if(r.indexOf("loanAccountInfo",10)=="-1"){
	                     $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text("******");
	                     $(".content").find(".bdxxcx_bdhm:eq(0)").find(".dkzhxx_ttright").text("******");
	                   }else{
	                     $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text("******");
	                   }
	               }
	            }else{
	                  //判断是不是贷款账户页面
	                   if(r.indexOf("loanAccountInfo",10)=="-1"){
	                     $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text("******");
	                     $(".content").find(".bdxxcx_bdhm:eq(0)").find(".dkzhxx_ttright").text("******");
	                   }else{
	                     $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text("******");
	                   }
	               }
	            break;
	          default:
                //判断是不是贷款账户页面
	            if(r.indexOf("loanAccountInfo",10)=="-1"){
	                $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text("******");
	                $(".content").find(".bdxxcx_bdhm:eq(0)").find(".dkzhxx_ttright").text("******");
	             }else{
	                $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text("******");
	             }
	           break;
	        }
	        /*
			var data = $TOOLS.ajaxComm("query/getUserMessage",null,"POST","JSON");
			switch(data.responseCode){
	          case "0":
	            if(data.result.ESERVICE_RETURN_FLAG==0){
	               if(data.result != null){
	                   //判断是不是贷款账户页面
	                   if(r.indexOf("loanAccountInfo",10)=="-1"){
	                     $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text(data.result.commName);
	                   }else{
	                     $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text(data.result.commName);
	                   }
	               }else{
	                   //判断是不是贷款账户页面
	                   if(r.indexOf("loanAccountInfo",10)=="-1"){
	                     $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text("******");
	                     $(".content").find(".bdxxcx_bdhm:eq(0)").find(".dkzhxx_ttright").text("******");
	                   }else{
	                     $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text("******");
	                   }
	               }
	            }else{
	                  //判断是不是贷款账户页面
	                   if(r.indexOf("loanAccountInfo",10)=="-1"){
	                     $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text("******");
	                     $(".content").find(".bdxxcx_bdhm:eq(0)").find(".dkzhxx_ttright").text("******");
	                   }else{
	                     $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text("******");
	                   }
	               }
	            break;
	          default:
                //判断是不是贷款账户页面
	            if(r.indexOf("loanAccountInfo",10)=="-1"){
	                $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text("******");
	                $(".content").find(".bdxxcx_bdhm:eq(0)").find(".dkzhxx_ttright").text("******");
	             }else{
	                $(".content").find(".bdxxcx_khxx").find(".dkzhxx_ttright").text("******");
	             }
	           break;
	        }*/
       },
       //获得url上的policyCode
       getPolicyCode:function(){
           var reg = new RegExp("(^|&)" + "policyCode" + "=([^&]*)(&|$)", "i");
	       var r = window.location.search.substr(1).match(reg);
	       if (r != null) {
		      var policyCode = r[0].substring(r[0].indexOf("=")+1);
	       }
	       return policyCode;
	      
       },
       //获得列表数据
       getData:function(data){
           switch(data.responseCode){
                case "0":
                   $(".content").append(data.result);
                   break;
               default:
                  var div =$("<div>").addClass("bdhflb").append($("<div>").addClass("bdhf_cont").append($("<div>").addClass("bdhf_cont_left").css("width","100%").append($("<p>").addClass("bdhf_lptt").text(data.msg))));
                  $(".content").append(div);
                  break;
             }
       },
       //获得保单或者客户详情页面信息
       getPolicyOrCusInfo:function(data){
            switch(data.responseCode){
	          case "0":
	             $(".bd_tt02").after(data.result);
	             break;
              default:
                 var div =$("<div>").addClass("bdxqxx").append($("<div>").addClass("bdxqxx_nr").css("font-size","20px").text(data.msg));
	             $(".bd_tt02").after(div);
                 break;
	  
	      }
       },
       //返回到结算账户列表页面
       backToSAL:function(){
          $(".reset_div").find("input").bind("click",function(){
              window.location.href="settleAccountList.html";
           })
       },
        //获得投资可转换保单列表
       getUpdateInvestList:function(){
           var data = $TOOLS.ajaxComm("query/getUpdateInvestList",null,"POST","JSON");
           switch(data.responseCode){
                case "0":
                   $(".content").append(data.result);
                   break;
                case "1":
                  var div =$("<div>").addClass("bdhflb").append($("<div>").addClass("bdhf_cont").append($("<div>").addClass("bdhf_cont_left").css("width","100%").append($("<p>").addClass("bdhf_lptt").text("未查找到符合条件的数据！"))));
                  $(".content").append(div);
                   break;
               default:
                  var div =$("<div>").addClass("bdhflb").append($("<div>").addClass("bdhf_cont").append($("<div>").addClass("bdhf_cont_left").css("width","100%").append($("<p>").addClass("bdhf_lptt").text(data.msg))));
                  $(".content").append(div);
                  break;
             }
       },
       //跳转到投资可转换保单详情页面
       gotoUpdateInvestList:function(){
          $(".content").find(".bdhflb").each(function(){
                $(this).find(".bdhf_cont").find(".bdhf_cont_right").find("input").bind("click",function(){
                     window.location.href="updateInvestInfo.html?policyCode="+$(this).attr("id");
                }) 
           })
       },
       //获得投连账户信息
       getInvestAccountInfo:function(){
           var param = {
              policyCode:$TOOLS.trimLR($POLICY.getPolicyCode()),
           };
           var data = $TOOLS.ajaxComm("query/updateInvestInfo.html",param,"POST","JSON");
           switch(data.responseCode){
                case "0":
                   $(".content").append(data.result);
                   break;
               default:
                  var div =$("<div>").addClass("bdhflb").append($("<div>").addClass("bdhf_cont").append($("<div>").addClass("bdhf_cont_left").css("width","100%").append($("<p>").addClass("bdhf_lptt").text(data.msg))));
                  $(".content").append(div);
                  break;
             }
       },
       //跳到开始转换页面
       gotoStartConvert:function(){
          $(".bdhf_rbtn").bind("click",function(){
//               window.location.href="updateInvestList.html?accountName="+$(this).attr("name");
        	  var accountName = $(this).attr("name");
        		accountName = encodeURI(encodeURI(accountName));
             window.location.href="updateInvestList.html?accountName="+accountName;
          })
       },
       closeFail:function(){
          $(".yz_fail").find(".yz_fail_nr").find(".yz_close").bind("click",function(){
              $(".yz_fail").css("display","none");
          })
       },
       showFail:function(str){
          $(".yz_fail").find(".yz_fail_nr").find(".yz_failfont").text("");
          $(".yz_fail").find(".yz_fail_nr").find(".yz_failfont").text(str);
          $(".yz_fail").css("display","block");
       },
       
       // 交费记录查询
       getRenewPolicyList:function(){
           var data = $TOOLS.ajaxComm("query/getRenewPayPolicyList.html",null,"POST","JSON");
           switch(data.responseCode){
               case "0":
                   $(".content").append(data.result);
                   break;
               default:
                  var div =$("<div>").addClass("bdhflb").append($("<div>").addClass("bdhf_cont").append($("<div>").addClass("bdhf_cont_left").css("width","100%").append($("<p>").addClass("bdhf_lptt").text(data.msg))));
                  $(".content").append(div);
                  break;
             }
       }

    }

}();
window.$POLICY=policy;