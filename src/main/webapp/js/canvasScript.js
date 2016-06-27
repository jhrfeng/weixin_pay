
var canvas = document.getElementById("canvas");
//设置canvas宽高度

var cWidth 	= document.documentElement.clientWidth;
var cHeight = document.documentElement.clientHeight;
canvas.setAttribute('width', cWidth+"px");
canvas.setAttribute('height', cHeight+"px");
//alert(canvas.style.width);
//alert(canvas.style.height);
//alert(cHeight);


var touchable = 'createTouch' in document;
if (touchable) {
    canvas.addEventListener('touchstart', onTouchStart, false);
    canvas.addEventListener('touchmove', onTouchMove, false);
    canvas.addEventListener('touchend', onTouchEnd, false);
}
var canvasContext = canvas.getContext("2d");
//设置填充色(白色)
canvasContext.fillStyle="#FFFFFF";
	//canvasContext.fileText="11111";
//canvasContext.fillStyle="#FFFFFF";
canvasContext.fillRect(0,0,cWidth,cHeight); 
// 设置字体
canvasContext.font = "bold 16px Arial"; 
// 设置对齐方式
canvasContext.textAlign = "center";
// 设置填充颜色
canvasContext.fillStyle = "#FF0000"; 
// 设置字体内容，以及在画布上的位置
canvasContext.fillText("本次新契约回访的内容均为本人真实意愿，", 165, 70);
canvasContext.fillText("特此声明。(以下为签名区)", 165, 90);
canvasContext.fillText("-----------------------------------------------------------------------------", 165, 100);

var touch;
var lastX, lastY;
var lastX2, lastY2;
canvasContext.lineWidth = 3;
canvasContext.lineCap = 'round';
var eOffsetX = getLeft('canvas');
var eOffsetY = getTop('canvas');


function onTouchStart(event) {
    //do stuff
	touch = event.touches[0];
	touch = fixTouch(touch);
    lastX = touch.clientX;
    lastY = touch.clientY;
    //lastX2 = event.touches[1].clientX;
    //lastY2 = event.touches[1].clientY;
    
    canvasContext.moveTo(lastX, lastY);
//    alert(lastX+";"+lastY);
    
}

function onTouchMove(event) {
    // Prevent the browser from doing its default thing (scroll, zoom)
    //e.preventDefault();
    event.preventDefault();
    //canvasContext.clearRect(lastX - 25, lastY - 25, 50, 50);
    //canvasContext.clearRect(lastX2 - 25, lastY2 - 25, 50, 50);

	touch = event.touches[0];
	touch = fixTouch(touch);
    nowX = touch.clientX;
    nowY = touch.clientY;
//    alert(nowX+";"+nowY);
    
    if (event.touches.length == 1) {
        canvasContext.strokeStyle = "rgb(0,0,0)";
    	canvasContext.beginPath();
        canvasContext.moveTo(lastX, lastY);
        canvasContext.lineTo(nowX, nowY);
        canvasContext.moveTo(nowX, nowY);
        canvasContext.stroke();
    }
    /*
    else if (event.touches.length == 2) {
        canvasContext.strokeStyle = 'rgba(255, 127, 0, 1)';
        canvasContext.beginPath();
        canvasContext.moveTo(lastX, lastY);
        canvasContext.lineTo(event.touches[0].clientX, event.touches[0].clientY);
        canvasContext.moveTo(event.touches[0].clientX, event.touches[0].clientY);
        canvasContext.stroke();

        canvasContext.strokeStyle = 'rgba(0, 298, 168, 1)';
        canvasContext.beginPath();
        canvasContext.moveTo(lastX2, lastY2);
        canvasContext.lineTo(event.touches[1].clientX, event.touches[1].clientY);
        canvasContext.moveTo(event.touches[1].clientX, event.touches[1].clientY);
        canvasContext.stroke();
    }
    *///‰∏çÊîØÊåÅÂ§öÁÇπËß¶Êéß
    lastX = nowX;
    lastY = nowY;
    //lastX2 = event.touches[1].clientX;
    //lastY2 = event.touches[1].clientY;
}

function onTouchEnd(event) {
    //do stuff
}



//Ê∏ÖÈô§
function canvasClear(){
	canvasContext.clearRect(0, 0, canvas.width, canvas.height);
	//设置填充色(白色)
	canvasContext.fillStyle="#FFFFFF";
	canvasContext.fileText="11111";
	//canvasContext.fillRect(0,100,cWidth,cHeight); 
	canvasContext.fillRect(0,0,cWidth,cHeight); 
	// 设置字体
canvasContext.font = "bold 16px Arial"; 
// 设置对齐方式
canvasContext.textAlign = "center";
// 设置填充颜色
canvasContext.fillStyle = "#FF0000"; 
// 设置字体内容，以及在画布上的位置
canvasContext.fillText("本次新契约回访的内容均为本人真实意愿，", 165, 70);
canvasContext.fillText("特此声明。(以下为签名区)", 165, 90);
canvasContext.fillText("-----------------------------------------------------------------------------", 165, 100);
}

//Ëé∑ÂèñÂÖÉÁ¥†ÁöÑÁ∫µÂùêÊ†á
function getTop(e){
	var offset=e.offsetTop;
	if(e.offsetParent!=null) offset+=getTop(e.offsetParent);
	return offset;
}
//Ëé∑ÂèñÂÖÉÁ¥†ÁöÑÊ®™ÂùêÊ†á
function getLeft(e){
	var offset=e.offsetLeft;
	if(e.offsetParent!=null) offset+=getLeft(e.offsetParent);
	return offset;
} 

function fixTouch (touch) {
	
    var winPageX = getScroll().l,
        winPageY = getScroll().t,
        x = touch.clientX,
        y = touch.clientY;
    
    
    if (touch.pageY === 0 && Math.floor(y) > Math.floor(touch.pageY) ||
        touch.pageX === 0 && Math.floor(x) > Math.floor(touch.pageX)) {
        // iOS4 clientX/clientY have the value that should have been
        // in pageX/pageY. While pageX/page/ have the value 0
        x = x - winPageX;
        y = y - winPageY;
    } else if (y < (touch.pageY - winPageY) || x < (touch.pageX - winPageX) ) {
        // Some Android browsers have totally bogus values for clientX/Y
        // when scrolling/zooming a page. Detectable since clientX/clientY
        // should never be smaller than pageX/pageY minus page scroll
        x = touch.pageX - winPageX;
        y = touch.pageY - winPageY;
    }
     
    return {
        clientX:    x,
        clientY:    y
    };
}

function getScroll()
{
    var t, l, w, h;
     
    if (document.documentElement && document.documentElement.scrollTop) {
        t = document.documentElement.scrollTop;
        l = document.documentElement.scrollLeft;
        w = document.documentElement.scrollWidth;
        h = document.documentElement.scrollHeight;
    } else if (document.body) {
        t = document.body.scrollTop;
        l = document.body.scrollLeft;
        w = document.body.scrollWidth;
        h = document.body.scrollHeight;
    }
    return { t: t, l: l, w: w, h: h };
}
