var updatePolicyListMap;
var isBaseInfoModified = false; //保单基础信息是否需要填写
var isAccInfoModified = false; //收费账号信息是否需要填写
var update_policy = function(){
    var customer;
    var wait = 300;
    var wait1 = 59;
    var time;
    
    
    
	return{
		
		//保单基本信息变更-获得可变更的保单基本信息列表
        getUpdatePolicyLB:function(){
        	// HG修改，此接口请求时间太久，iOS设备会自动10秒钟断开同步的Ajax请求，故在此使用异步请求方式
        	function finishMethod(data) {
	        	switch(data.responseCode){
	                case "0":
	                	//成功后先清理等待提示..
	                   $(".content").find(".bdhflb").remove();
	                   	//添加数据
	                   $(".content").append(data.result);
	                    //复选框操作
		               $TOOLS.opeCheckbox("all","checkbox");
		               
			  			$UPDATE_POLICY.gotoUpdatePolicyList();
	                   break;
	                default:
	                   $(".content").find(".bdxxcx_cxnr").remove();
		               var div =$("<div>").addClass("bdxxcx_cxnr").append($("<ul>").addClass("bdxxcx_ul").append($("<li>").append($("<div>").addClass("bdxxcx_ul_left").css({"font-size":"20px","width":"100%"}).text(data.msg))));
		               $(".content").find(".bdxxcx_bdhm:eq(1)").after(div);
	                   break;
	            
	            }
	            
		      //关闭提示框
		      $("div.yz_close").bind("click", function () {
					$("div#dialog").hide();
			   });
        	}
            var data = $TOOLS.ajaxAsyncComm("ps/getUpdatePolicyLB",null,"POST","JSON",finishMethod);
                //alert('code'+data.responseCode);
            
        },
       //保单基本信息变更-根据勾选的信息，跳转到变更保单基本信息页面
       gotoUpdatePolicyList:function(){
       	 //跳转页面
       	 $(".qrbg_btn").find(".reset_div").find("input").bind("click",function(){
       	     //判断复选框是否选中
		     if ($("input[name = 'checkbox']:checked").length > 0) {
		          //保单ID集合
		          var policys = "";
		          $("input[name = 'checkbox']:checked").each(function () {
			         policys +=  $(this).val()+ ",";
		          });
		          
		          // HG需求变更2013-12-26默认选择第一个作为默认值
		          try{
		          $("input[name = 'checkbox']:checked").each(function(m){
		          
		              var address = $(this).parent().parent().parent().parent().next().find(".nr_bg_rlist").find(".nr_bg_rul").find("li:eq(1)").find(".nrlis_right").text();
		              var zip = $(this).parent().parent().parent().parent().next().find(".nr_bg_rlist").find(".nr_bg_rul").find("li:eq(2)").find(".nrlis_right").text();
		              var bankaccount = $(this).parent().parent().parent().parent().next().find(".nr_bg_rlist").find(".nr_bg_rul").find("li:eq(5)").find(".nrlis_right").text();
		              var accountowner = $(this).parent().parent().parent().parent().next().find(".nr_bg_rlist").find(".nr_bg_rul").find("li:eq(6)").find(".nrlis_right").text();
		              var bankcode = $(this).parent().parent().parent().parent().next().find(".nr_bg_rlist").find(".nr_bg_rul").find("li:eq(7)").find(".nrlis_right").text();
		              var organid = $(this).parent().parent().parent().parent().next().find(".nr_bg_rlist").find(".nr_bg_rul").find("li:eq(8)").find(".nrlis_right").text();
	              	  // 取第一个就返回
		         	  window.location.href="updatePolicyList.html?policys="+policys.substring(0, policys.lastIndexOf(","))
		         	  +"&address="+address+"&zip="+zip
		         	  +"&bankaccount="+bankaccount+"&accountowner="+accountowner
		         	  +"&bankcode="+bankcode+"&organid="+organid;
	          
	              	  return false;
	              });
	              } catch(e) {
	              	alert(e.description);
	              }
		          //window.location.href="updatePolicyList.html?policys="+policys.substring(0, policys.lastIndexOf(","));
		          /**
		           * 需求变更，见PSController
		           */
		           /*
		          //开始判断被选中的个数
		          if($("input[name = 'checkbox']:checked").length==1){
		              var address = $("input[name = 'checkbox']:checked").parent().parent().parent().parent().next().find(".nr_bg_rlist").find(".nr_bg_rul").find("li:eq(1)").find(".nrlis_right").text();
		              var zip = $("input[name = 'checkbox']:checked").parent().parent().parent().parent().next().find(".nr_bg_rlist").find(".nr_bg_rul").find("li:eq(2)").find(".nrlis_right").text();
		              window.location.href="updatePolicyList.html?policys="+policys.substring(0, policys.lastIndexOf(","))+"&address="+address+"&zip="+zip;
		          }else{
		              //被选中的个数大于1，比对address和zip值是否相同
		               var psList = [{"name":"ADDRESS_FEE","oldValue":"","value":"","flag":true},{"name":"ZIP_LINK","oldValue":"","value":"","flag":true}];
		               $("input[name = 'checkbox']:checked").each(function(m){
		                   psList[0].value = $(this).parent().parent().parent().parent().next().find(".nr_bg_rlist").find(".nr_bg_rul").find("li:eq(1)").find(".nrlis_right").text();
		                   psList[1].value = $(this).parent().parent().parent().parent().next().find(".nr_bg_rlist").find(".nr_bg_rul").find("li:eq(2)").find(".nrlis_right").text();
		                   if(m==0){
		                      for(var i=0;i<psList.length;i++){
		                             psList[i].oldValue = psList[i].value;
		                      }
		                   }else{
			                   for(var i=0;i<psList.length;i++){
			                       if(psList[i].oldValue!=psList[i].value){
		                                 psList[i].flag = false;
		                           }
			                   }
		                   }
		              });
		              var same="{";
		              for(var i=0;i<psList.length;i++){
		                  if(psList[i].flag){
		                     same+= "\"" + psList[i].name + "\":\"" + psList[i].oldValue + "\",";
		                  }
		              }
		              same = same.substring(0, same.lastIndexOf(","))+"}";
		              window.location.href="updatePolicyList.html?policys="+policys.substring(0, policys.lastIndexOf(","))+"&same="+same;
		          }
		          */
		          
		     }else{
		           $("div#dialog").find("div[name='msg']").text("请选择你要变更的保单！");
			       $("div#dialog").show();
		     }
       	 });
	    },
	    //获得银行名称和机构名称
	    getBankAndOrganList:function(){
	        var data = $TOOLS.ajaxComm("query/getBankAndOrganList",null,"POST","JSON");
            switch(data.responseCode){
                case "0":
                   $("#zfqr_jiaz").css("display","none");
                   for(var i =0;i<data.result.bank.RESULT_LIST.length;i++){
                      //$(".neirong").find(".yzxxlr").find(".yzxxlr_nr:eq(2)").find("select").append($("<option>").attr("value",data.result.bank.RESULT_LIST[i].bankCode).text(data.result.bank.RESULT_LIST[i].bankName));
                      $("#userBank").append($("<option>").attr("value",data.result.bank.RESULT_LIST[i].bankCode).text(data.result.bank.RESULT_LIST[i].bankName));
                   }
                   /*
                   for(var j =0;j<data.result.organ.RESULT_LIST.length;j++){
                      $(".neirong").find(".yzxxlr").find(".yzxxlr_nr:eq(6)").find("select").append($("<option>").attr("value",data.result.organ.RESULT_LIST[j].organId).text(data.result.organ.RESULT_LIST[j].companyName));
                   }*/
                   for(var j =0;j<data.result.organ.RESULT_LIST.length;j++){
                      var compName = data.result.organ.RESULT_LIST[j].companyName;
                      compName = compName.replace("太平人寿保险有限公司","");
                      compName = compName.replace("分公司","");
                      
                      //$(".neirong").find(".yzxxlr").find(".yzxxlr_nr:eq(6)").find("select").append($("<option>").attr("value",data.result.organ.RESULT_LIST[j].organId).text(compName));
                   	  $("#userOrgan").append($("<option>").attr("value",data.result.organ.RESULT_LIST[j].organId).text(compName));
                   }
                   break;
                default:
                   $("div#dialog").find("div[name='msg']").text(data.msg);
			       $("div#dialog").show();
                   break;
            }
	    
	    },
	    //获得收费地址及收费邮编
	    getAddressAndZip:function(){
	    	try{
		    	var address = $("#address");
			    var feePost = $("#feePost");
			    var addStr = $TOOLS.getQueryParameter('address');
			    var zipStr = $TOOLS.getQueryParameter('zip');
				address.val(decodeURIComponent(addStr));
				feePost.val(decodeURIComponent(zipStr));
				// 默认关闭，没有修改
				var baseInfoDiv = $("#baseInfoDiv");
				$UPDATE_POLICY.disableSubElementsOfDiv(baseInfoDiv);
			} catch (e) {
				
			}
		              
	    },
	    getAccountInfo:function(){
	    	try{
		    	var userbank = $("#userBank");
			    var userNumber = $("#userNumber");
		    	var userHolder = $("#userHolder");
			    var userOrgan = $("#userOrgan");
			    var bankCodeStr = $TOOLS.getQueryParameter('bankcode');
			    var bankAccStr = $TOOLS.getQueryParameter('bankaccount');
			    var accOwnerStr = $TOOLS.getQueryParameter('accountowner');
			    var organIdStr = $TOOLS.getQueryParameter('organid');
				userbank.val(decodeURIComponent(bankCodeStr));
				userNumber.val(decodeURIComponent(bankAccStr));
				userHolder.val(decodeURIComponent(accOwnerStr));
				userOrgan.val(decodeURIComponent(organIdStr));
				
				// 默认关闭，没有修改
				var accountInfoDiv = $("#accInfoDiv");
				$UPDATE_POLICY.disableSubElementsOfDiv(accountInfoDiv);
			} catch (e) {
				
			}
		              
	    },
	    // 把DIV下所有的input、select、textarea都标为灰色
	    disableSubElementsOfDiv:function(obj){
	    	var disableColorName = "#777";
	    	obj.find("input").css("color",disableColorName);
	    	obj.find("input").change(function(){
	    		$UPDATE_POLICY.enableSubElementsOfDiv(obj);
	    	});
	    	obj.find("select").css("color",disableColorName);
	    	obj.find("select").change(function(){
	    		$UPDATE_POLICY.enableSubElementsOfDiv(obj);
	    	});
	    	obj.find("textarea").css("color",disableColorName);
	    	obj.find("textarea").change(function(){
	    		$UPDATE_POLICY.enableSubElementsOfDiv(obj);
	    	});
	    },
	    //把DIV下所有元素都标为黑色，正常，并且标记该DIV已经改变
	    enableSubElementsOfDiv:function(obj){
	    	//var baseInfoDiv = $("#baseInfoDiv");
			//var accountInfoDiv = $("#accInfoDiv");
	    	if (obj.attr("id") == "baseInfoDiv") {
	    		isBaseInfoModified = true;
	    	} else if (obj.attr("id") == "accInfoDiv") {
	    		isAccInfoModified = true;
	    	}
	    	var enableColorName = "#000";
	    	obj.find("input").css("color",enableColorName);
	    	obj.find("select").css("color",enableColorName);
	    	obj.find("textarea").css("color",enableColorName);
	    },
	     postUpdateAsyncPolicyList:function(param){
        	function finishMethod(data) {
	        	switch(data.responseCode){
	                case "0":
                   //变更成功，传值到批文页面
                   window.location.href="updatePolicyPW.html";
                   break;
                default:
                   $("div#dialog").find("div[name='msg']").text(data.msg);
			       $("div#dialog").show();
                   break;
	            }
        	}
            var data = $TOOLS.ajaxAsyncComm("ps/postUpdatePolicyList",{param: param },"POST","JSON",finishMethod);
        },
       //变更保单信息
       postUpdatePolicyList:function(){
       	  //$(".content").find(".qrbg_btn").find(".reset_div").find("input").bind("click",function(){   
       	  $(".content").find(".qrbg_btn").find(".reset_div").find("input").bind("click",function(){
       	       //格式验证
       	      	   /*
       	           if(!$.trim($("#address").val())){
       	              $UPDATE_POLICY.showFail("请输入收费地址");
                      $UPDATE_POLICY.closeFail();
                      return;
       	           }
       	           
       	           var feePost = $.trim($("#feePost").val());
       	           if(!feePost.match(/^\d{6}$/)){
       	              $UPDATE_POLICY.showFail("请输入正确的收费邮编！示例：200072");
                      $UPDATE_POLICY.closeFail();
                      return;
       	           }*/
       	           // 2013-12-18 黄程程要修改为收费账号信息要么都不修改，要么必须全部修改
       	           /*
       	           if($.trim($("#address").val())!=""){
       	           	  isBaseInfoModified = true;
       	           }
       	           if($.trim($("#feePost").val())!=""){
       	           	  isBaseInfoModified = true;
       	           }
       	           if($.trim($("#userOrgan").val())!=""){
       	           	  isAccInfoModified = true;
       	           }
       	           if($.trim($("#userHolder").val())!=""){
       	           	  isAccInfoModified = true;
       	           }
       	           if($.trim($("#userNumber").val())!=""){
       	           	  isAccInfoModified = true;
       	           }
       	           if($.trim($("#userBank").val())!=""){
       	           	  isAccInfoModified = true;
       	           }*/
       	           /*
       	           var count = 0;
       	           var alertMsg = "";
       	           if($.trim($("#userOrgan").val())==""){
       	           	  count += 1;
       	           }
       	           if($.trim($("#userHolder").val())==""){
       	           	  count += 2;
       	           }
       	           if($.trim($("#userNumber").val())==""){
       	           	  count += 4;
       	           }
       	           if($.trim($("#userBank").val())==""){
       	           	  count += 8;
       	           }
       	           if(count == 0 || count == 15) {
       	           	  //要么都填了，要么都没填
       	           } else {
	       	           if (count < 2){
	       	              alertMsg = "请选择账户所在地！";
	       	           } else if (count < 4) {
	       	              alertMsg = "请输入账户所有人！";
	       	           } else if (count < 8) {
	       	              alertMsg = "请输入收费账号！";
	       	           } else if (count < 16) {
	       	              alertMsg = "请选择账号所属银行！";
	       	           }
	      	           $UPDATE_POLICY.showFail(alertMsg);
	                   $UPDATE_POLICY.closeFail();
	                   return;
       	           }
       	           */
       	       //数据验证
	           var valConfig ="[{\"name\":\"address\",\"label\":\"收费地址\",\"required\":"+isBaseInfoModified+"},";
               valConfig+="{\"name\":\"feePost\",\"label\":\"收费邮编\",\"required\":"+isBaseInfoModified+",\"format\":\"zipcode\"},";
               valConfig+="{\"name\":\"userBank\",\"label\":\"所属银行\",\"required\":"+isAccInfoModified+"},";
               valConfig+="{\"name\":\"userNumber\",\"label\":\"收费账号\",\"required\":"+isAccInfoModified+"},";
               valConfig+="{\"name\":\"userType\",\"label\":\"账户类型\",\"required\":"+isAccInfoModified+"},";
               valConfig+="{\"name\":\"userHolder\",\"label\":\"账户所有人\",\"required\":"+isAccInfoModified+"},";
               valConfig+="{\"name\":\"userOrgan\",\"label\":\"账户所在地\",\"required\":"+isAccInfoModified+"}]";
               var validateResult= $TOOLS.validate(valConfig);
               if(""!=validateResult){
                  $("div#dialog").find("div[name='msg']").text(validateResult);
			      $("div#dialog").show();
			      return false;
               }
               //如果什么都没有填写，那么不允许通过，提示
               if(isBaseInfoModified==false && isAccInfoModified==false) {
                  $("div#dialog").find("div[name='msg']").text("请填写需要变更的信息");
			      $("div#dialog").show();
			      return false;
               }
              
		     var param = "{";
		     if(isBaseInfoModified!=false){
		     	param +="\"ADDRESS_FEE\":\"" + $("#address").val() + "\",\"ZIP_LINK\":\"" + $("#feePost").val() + "\",";
		     }
		     if(isAccInfoModified!=false){
			    param +="\"BANK_CODE\":\"" + $("#userBank").find("option:selected").val() + "\",\"BANK_ACCOUNT\":\"" + $("#userNumber").val() + "\",\"ACCO_OWNER_NAME\":\"" + $("#userHolder").val() + "\",";
			    param +="\"ACCOUNT_TYPE\":\"" + $("#userType").find("option:selected").val() + "\",\"ORGAN_ID\":\"" + $("#userOrgan").find("option:selected").val() + "\",";
		        param +="\"ORGAN_NAME\":\"" + $("#userOrgan").find("option:selected").text() + "\"," + "\"BANK_NAME\":\"" + $("#userBank").find("option:selected").text() + "\",";
		     }
	         param +="\"nothing\":\"nothing\"}";
	         $UPDATE_POLICY.postUpdateAsyncPolicyList(param);
	         /*var data =$TOOLS.ajaxComm("ps/postUpdatePolicyList",{param: param },"POST","JSON");
=======
	          //显示加载中....
         	 $("#zfqr_jiaz").css("display","block");
	         var data =$TOOLS.ajaxComm("ps/postUpdatePolicyList",{param: param },"POST","JSON");
>>>>>>> .r24619
	         switch(data.responseCode){
                case "0":
                   //变更成功，传值到批文页面
                   window.location.href="updatePolicyPW.html";
                   break;
                default:
                   $("#zfqr_jiaz").css("display","none");
                   $("div#dialog").find("div[name='msg']").text(data.msg);
			       $("div#dialog").show();
                   break;
            
             }*/
          }) 
       },
       //获得变更保单批文
       getUpdatePolicyPW:function(){
          var data = $TOOLS.ajaxComm("ps/getUpdatePolicyPW.html",null,"POST","JSON");
          switch (data.responseCode) {
		    case "0":
		    $(".content").append(data.result);
			break;
		  default:
			$("div#dialog").find("div[name='msg']").text(data.msg);
			$("div#dialog").show();
			break;
		 }
       },
       //客户基本资料变更-获得客户基本资料
       getUpdateCustomer:function(){
       		
            var data = $TOOLS.ajaxComm("query/getUpdateCustomer",null,"POST","JSON");
            switch(data.responseCode){
                case "0":
                   $(".content").append(data.result);
                   //保存原有用户信息
                   customer = {
                      homeAddress:$(".content").find(".neirong:eq(0)").find(".yzxxlr").find(".yzxxlr_nr:eq(0)").find(".yzxxlr_nrright").find("textarea").val(),
                      homePost:$(".content").find(".neirong:eq(0)").find(".yzxxlr").find(".yzxxlr_nr:eq(1)").find(".yzxxlr_nrright").find("input").val(),
                      homePhone:$(".content").find(".neirong:eq(0)").find(".yzxxlr").find(".yzxxlr_nr:eq(2)").find(".yzxxlr_nrright").find("input").val(),
                      homeTelphone:$(".content").find(".neirong:eq(0)").find(".yzxxlr").find(".yzxxlr_nr:eq(3)").find(".yzxxlr_nrright").find("input").val(),
                      homeEmail:$(".content").find(".neirong:eq(0)").find(".yzxxlr").find(".yzxxlr_nr:eq(4)").find(".yzxxlr_nrright").find("textarea").val(),
                      comAddress:$(".content").find(".neirong:eq(1)").find(".yzxxlr").find(".yzxxlr_nr:eq(0)").find(".yzxxlr_nrright").find("textarea").val(),
                      comPost:$(".content").find(".neirong:eq(1)").find(".yzxxlr").find(".yzxxlr_nr:eq(1)").find(".yzxxlr_nrright").find("input").val()
                   };
                   break;
                default:
                   var div = $("<div>").addClass("neirong").append($("<div>").addClass("yzxxlr").append($("<div>").addClass("yzxxlr_nr").append($("<div>").addClass("yzxxlr_nrleft").css({"font-size":"20px","width":"100%"}).text(data.msg))));
                   $(".content").append(div);
                   break;
            
            }
       
       },
      postAsyncUpdateCustomer:function(param){
	      function AsyncUpdatefinishMethod(data) {
	             switch(data.responseCode){
                case "0":
                   //变更成功，跳转到批文页面
                   window.location.href="update_customer_pw.html";
                   break;
                case "1":
                   $("#zfqr_jiaz").css("display","none");
                   $("div#dialog").find("div[name='msg']").text(data.msg);
			       $("div#dialog").show();
                   break;
                default:
                   $("#zfqr_jiaz").css("display","none");
                   $("div#dialog").find("div[name='msg']").text(data.msg);
			       $("div#dialog").show();
                   break;
            
            }
	      }
	      var data = $TOOLS.ajaxAsyncComm("ps/postUpdateCustomer",param,"POST","JSON",AsyncUpdatefinishMethod);

	    },
       //客户基本资料变更-提交变更
       postUpdateCustomer:function(){
        var homeAddress = $(".content").find(".neirong:eq(0)").find(".yzxxlr").find(".yzxxlr_nr:eq(0)").find(".yzxxlr_nrright").find("textarea").val();
        var homePost = $(".content").find(".neirong:eq(0)").find(".yzxxlr").find(".yzxxlr_nr:eq(1)").find(".yzxxlr_nrright").find("input").val();
        var homePhone = $(".content").find(".neirong:eq(0)").find(".yzxxlr").find(".yzxxlr_nr:eq(2)").find(".yzxxlr_nrright").find("input").val();
        var homeTelphone = $(".content").find(".neirong:eq(0)").find(".yzxxlr").find(".yzxxlr_nr:eq(3)").find(".yzxxlr_nrright").find("input").val();
        var homeEmail = $(".content").find(".neirong:eq(0)").find(".yzxxlr").find(".yzxxlr_nr:eq(4)").find(".yzxxlr_nrright").find("textarea").val();
        var comAddress = $(".content").find(".neirong:eq(1)").find(".yzxxlr").find(".yzxxlr_nr:eq(0)").find(".yzxxlr_nrright").find("textarea").val();
        var comPost = $(".content").find(".neirong:eq(1)").find(".yzxxlr").find(".yzxxlr_nr:eq(1)").find(".yzxxlr_nrright").find("input").val();
		//对比用户信息是否有所改变
		var list =""
                   if(customer.homeAddress!=homeAddress){
                      list+="{\"name\":\"" + "收费地址" + "\",\"value\":\"" + homeAddress + "\"},";
                   }
                   if(customer.homePost!=homePost){
                      list+="{\"name\":\"" + "家庭邮编" + "\",\"value\":\"" + homePost + "\"},";
                   }
                   if(customer.homePhone!=homePhone){
                      list+="{\"name\":\"" + "家庭电话" + "\",\"value\":\"" + homePhone + "\"},";
                   }
                   if(customer.homeTelphone!=homeTelphone){
                      list+="{\"name\":\"" + "手机" + "\",\"value\":\"" + homeTelphone + "\"},";
                   }
                   if(customer.homeEmail!=homeEmail){
                      list+="{\"name\":\"" + "收费邮箱" + "\",\"value\":\"" + homeEmail + "\"},";
                   }
                   if(customer.comAddress!=comAddress){
                      list+="{\"name\":\"" + "单位地址" + "\",\"value\":\"" + comAddress + "\"},";
                   }
                   if(customer.comPost!=comPost){
                      list+="{\"name\":\"" + "单位邮编" + "\",\"value\":\"" + comPost + "\"},";
                   }
        	// 校验是否修改了数据
        if(list==""){
            $("div#dialog").find("div[name='msg']").text("您客户基本信息未变更！");
			$("div#dialog").show();
			return false;
		}
				// 校验
		if(!$.trim(homeAddress)){
            $UPDATE_POLICY.showFail("请输入家庭地址！");
            $UPDATE_POLICY.closeFail();
            return;
        }
        if(!$.trim(homePost).match(/^\d{6}$/)){
       	    $UPDATE_POLICY.showFail("请输入正确的家庭邮编！示例：200070");
            $UPDATE_POLICY.closeFail();
            return;
     	}
        if(!$.trim(homePhone).match(/^\d{3,4}\-\d{6,8}$/)) {
			$UPDATE_POLICY.showFail("请输入正确的家庭联系电话！示例：021-12345678");
			$UPDATE_POLICY.closeFail();
            return;
        }
        if(!$.trim(homeTelphone).match(/^\d{11}$/)){
       	    $UPDATE_POLICY.showFail("请输入正确的手机号码！示例：18612345678");
            $UPDATE_POLICY.closeFail();
            return;
     	}
        if(!$.trim(homeEmail).match(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/)){
            $UPDATE_POLICY.showFail("请输入正确的电子邮件！示例：tp@abc.com");
            $UPDATE_POLICY.closeFail();
            return;
        }
        if(!$.trim(comAddress)){
            $UPDATE_POLICY.showFail("请输入单位地址！");
            $UPDATE_POLICY.closeFail();
            return;
        }
		if(!$.trim(comPost).match(/^\d{6}$/)){
       	    $UPDATE_POLICY.showFail("请输入正确的单位邮编！示例：200070");
            $UPDATE_POLICY.closeFail();
            return;
     	}
		
        //校验格式
        var valConfig ="[{\"name\":\"address1\",\"label\":\"家庭地址\",\"required\":true},";
        valConfig+="{\"name\":\"zip1\",\"label\":\"家庭邮编\",\"required\":true,\"format\":\"zipcode\"},";
        valConfig+="{\"name\":\"tel1\",\"label\":\"家庭联系电话\",\"required\":true,\"format\":\"telephone\"},";
        valConfig+="{\"name\":\"celler\",\"label\":\"手机号码\",\"required\":true,\"format\":\"mobilephone\"},";
        valConfig+="{\"name\":\"email\",\"label\":\"家庭电子邮件\",\"required\":true,\"format\":\"email\"},";
        valConfig+="{\"name\":\"jobAddress\",\"label\":\"单位地址\",\"required\":true},";
        valConfig+="{\"name\":\"jobZip\",\"label\":\"单位邮编\",\"required\":true,\"format\":\"zipcode\"}]";
        var validateResult= $TOOLS.validate(valConfig);
        if(""!=validateResult){
            $("div#dialog").find("div[name='msg']").text(validateResult);
			$("div#dialog").show();
			return false;
        }
        var updateCustomer = {
		    // 家庭信息——地址
			ADDRESS_1:homeAddress,
		    // 家庭信息——邮编
			ZIP_1:homePost,
			// 家庭信息——电话
			TEL_1:homePhone,
			// 家庭信息——手机
			CELLER:homeTelphone,
		    // 家庭信息——电子邮件
			EMAIL:homeEmail,
		    // 单位信息——地址
			JOB_ADDRESS:comAddress,
			// 单位信息——邮编
			JOB_ZIP:comPost
		 };
	     var param = {
	         updateCustomer:JSON.stringify(updateCustomer),
	         list:"["+list.substring(0, list.lastIndexOf(","))+"]"
	     };
	     //显示加载中....
         $("#zfqr_jiaz").css("display","block");
	      $UPDATE_POLICY.postAsyncUpdateCustomer(param);
		 /*var data = $TOOLS.ajaxComm("ps/postUpdateCustomer",param,"POST","JSON");
            switch(data.responseCode){
                case "0":
                   //变更成功，跳转到批文页面
                   window.location.href="update_customer_pw.html";
                   break;
                case "1":
                   $("div#dialog").find("div[name='msg']").text(data.msg);
			       $("div#dialog").show();
                   break;
                default:
                   $("div#dialog").find("div[name='msg']").text(data.msg);
			       $("div#dialog").show();
                   break;
            
            }*/
       
       },
       //获得客户基本资料变更批文
        getUpdateCustomerPW:function(){
           var data = $TOOLS.ajaxComm("ps/update_customer_pw.html",null,"POST","JSON");
           switch(data.responseCode){
              case "0":
                $(".content").append(data.result);
                break;
              default:
                var div = $("<div>").addClass("neirong").css("height","50px").append($("<div>").css("font-size","20px").text(data.msg));
                $(".content").append(div);
                break;
           }
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
       //添加单位数
       addInvest:function(policyCode,productId,total){
          //绑定添加按钮事件
          $(".sryzm_btn").bind("click",function(){
              var unit = $(this).parent().parent().prev().find(".dkzhxx_ttright").find("input").val();
              var from = $(this).parent().parent().parent().find("li:eq(0)").find(".dkzhxx_ttright").find("select").val();
              var to = $(this).parent().parent().parent().find("li:eq(1)").find(".dkzhxx_ttright").find("select").val();
              //验证单数数格式和大小
              if(unit==""||parseFloat(unit)<=0){
                  $UPDATE_POLICY.showFail("单位数不能为空或0！");
                  $UPDATE_POLICY.closeFail();
                  return;
              }
             if($(".content").find(".bdxxcx_cxnr").length>1){
                  var old = 0;
                  for(var i=1;i<$(".content").find(".bdxxcx_cxnr").length;i++){
                     old+=parseFloat($(".content").find(".bdxxcx_cxnr:eq('"+i+"')").find("li:eq(2)").find(".jbxx_ulright").text());
                  }
                  if(parseFloat(old)+parseFloat(unit)>parseFloat(total)){
                     $UPDATE_POLICY.showFail("单位数不能大于账户可转出基金单位数！");
                     $UPDATE_POLICY.closeFail();
                     return;
                  }
              }else{
                  if(parseFloat(unit)>parseFloat(total)){
                     $UPDATE_POLICY.showFail("单位数不能大于账户可转出基金单位数！");
                     $UPDATE_POLICY.closeFail();
                     return;
                  }
              }
              if(from==to){
                 $UPDATE_POLICY.showFail("源账户和目标账户不能相同！");
                 $UPDATE_POLICY.closeFail();
                 return;
              }
              
              //点击添加按钮展示div
              $(".content").find(".qrbg_btn").remove();
              var html = "<div class='bdxxcx_cxnr'>";
              html+= "<div class='tzzhh_phcont'> <div class='tzzhzh_c_tt'> <span><img src='images/tzzhdwzh_ycqicon.png' width='30' height='30' /></span>";
              html+="<h2>已申请添加账户转换</h2></div><ul class='bdxq_jbxx'>";
              html+=" <li><div class='jbxx_ulleft'>源账户：</div><div class='jbxx_ulright'> "+from+" </div> </li>";
              html+="<li><div class='jbxx_ulleft'>目标账户：</div> <div class='jbxx_ulright'> "+to+" </div> </li>";
              html+="<li><div class='jbxx_ulleft'>单位数：</div> <div class='jbxx_ulright'> "+unit+" </div></li></ul></div></div>";
              html+="<div class='qrbg_btn'><div class='reset_div'>";
              html+="<input type='button' class='reget_btn' value='确认转换' />";
              html+="</div><div class='blank'></div></div>";
              $(".content").append(html);
              //绑定确认转换按钮事件
              $(".reget_btn").bind("click",function(){
                  $UPDATE_POLICY.updateInvest(policyCode,productId);
              })
          })  
       },
       //转换账户单位数
       updateInvest:function(policyCode,productId){
       		var acc_list = new Array();
       		//增加到转换列表，该列表每种转换类型只能增加一次
            $.each($(".content").find(".bdxxcx_cxnr"),function(n, value) {
               if(n>0){
                 var trans = {
          			TO_ACCOUNT_CODE:	$.trim($(".content").find(".bdxxcx_cxnr:eq('"+n+"')").find(".tzzhh_phcont").find("ul").find("li:eq(1)").find(".jbxx_ulright").text()),
	             	UNIT:				$.trim($(".content").find(".bdxxcx_cxnr:eq('"+n+"')").find(".tzzhh_phcont").find("ul").find("li:eq(2)").find(".jbxx_ulright").text()),
	            	FROM_ACCOUNT_CODE:	$.trim($(".content").find(".bdxxcx_cxnr:eq('"+n+"')").find(".tzzhh_phcont").find("ul").find("li:eq(0)").find(".jbxx_ulright").text())
	          	 }
	           	acc_list.push(trans);
              }
            })
           var paramVal = {
	          POLICY_CODE:policyCode,
	          PRODUCT_ID:""+productId,
	          ACC_LIST:acc_list
	         
	       };
	       var param = {
	          paramVal:JSON.stringify(paramVal)
	       };
	       
           var data = $TOOLS.ajaxComm("ps/updateInvest",param,"POST","JSON");
           switch(data.responseCode){
                case "0":
                   window.location.href="updateInvestPW.html";
                   break;
                case "1":
                   $UPDATE_POLICY.showFail(data.msg);
                   $UPDATE_POLICY.closeFail();
                   break;
                default:
                   $UPDATE_POLICY.showFail(data.msg);
                   $UPDATE_POLICY.closeFail();
                   break;
            
          }
       },
       //获得转换批文
       getTransAccountyPW:function(){
           var data = $TOOLS.ajaxComm("query/getTransAccountyPW",null,"POST","JSON");
           switch(data.responseCode){
                case "0":
                   $(".content").append(data.result);
                   break;
                default:
                   $UPDATE_POLICY.showFail(data.msg);
                   $UPDATE_POLICY.closeFail();
                   break;
            
          }
       },
       //从session中全部账户名称
       getAccountName:function(){
          var data = $TOOLS.ajaxComm("query/updateInvestList.html",null,"POST","JSON");
          switch(data.responseCode){
              case "0":
                $(".content").append(data.result);
                $UPDATE_POLICY.addInvest(data.hiddenParameters.policyCode,data.hiddenParameters.productId,data.hiddenParameters.OUT_UNITS);
                break;
              default:
                $UPDATE_POLICY.showFail(data.msg);
                $UPDATE_POLICY.closeFail();
                break;
          } 
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
       //返回到投资账户单位变更页面
       backToupdateInvest:function(){
          $(".reset_div").find(".reget_btn").bind("click",function(){
             window.location.href="updateInvest.html";
          })
       }
      
	}
}();
window.$UPDATE_POLICY=update_policy;