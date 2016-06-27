var batchID = location.search;
var localURL = window.location.href.substr(0,window.location.href.indexOf("/",3))+"/toQianghb.html?"+batchID;
function test(str,ch,num){
	var index=-1;
	var count=0;
	for(var i=0;i<num-1;i++){
		str=str.replace(ch,"(@#$)");count++;
	}
	if((index=str.indexOf(ch))!=-1){
	    return index-count*5+3;
	}
	return -1;
}
var dataForWeixin={
   appId:"",
   MsgImg:window.location.href.substr(0,test(window.location.href,"/",4))+"/images-small/lh.jpg",
   TLImg:window.location.href.substr(0,test(window.location.href,"/",4))+"/images-small/lh.jpg",
   url:window.location.href,
   title:"发年货喽!",
   desc:"给您送来一箱年货，好友已买单，打开看一看！祝：家人太平，羊年幸福！",
   fakeid:"",
   callback:function(){}
};

var data = $TOOLS.ajaxComm("query/wxConfig.html",{url:window.location.href} , "POST", "JSON");
wx.config({
  debug: false,
  appId: data.result.appid,
  timestamp: data.result.timestamp,
  nonceStr: data.result.nonce_Str,
  signature: data.result.signature,
  jsApiList: [
    'onMenuShareTimeline',
    'onMenuShareAppMessage'
  ]
});
 wx.ready(function(){
	//分享到朋友圈
	wx.onMenuShareTimeline({
	   title: dataForWeixin.title, // 分享标题
	   link: dataForWeixin.link, // 分享链接
	   imgUrl: dataForWeixin.MsgImg, // 分享图标
	   success: function () { 
	       // 用户确认分享后执行的回调函数
	   },
	   cancel: function () { 
	       // 用户取消分享后执行的回调函数
	   }
	});
	//分享给朋友
	wx.onMenuShareAppMessage({
	    title: dataForWeixin.title, // 分享标题
	    link: dataForWeixin.link, // 分享链接
	    desc: dataForWeixin.desc, // 分享描述
	    imgUrl: dataForWeixin.MsgImg, // 分享图标
	    type: 'link', // 分享类型,music、video或link，不填默认为link
	    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
});