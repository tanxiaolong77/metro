

//设定日期选择
//==================================================== 参数设定部分 =======================================================
var bMoveable=true;        //设置日历是否可以拖动
var _VersionInfo="Version:2.0"    //版本信息

//==================================================== WEB 页面显示部分 =====================================================
var strFrame;        //存放日历层的HTML代码
document.writeln('<iframe bgcolor="#000000" id=meizzDateLayer frameborder=0 style="position: absolute; width: 186; height: 213; z-index: 9998; display: none"></iframe>');
strFrame='<style>';
strFrame+='INPUT.button{BORDER: #006b75 1px solid;background-image:url(../include/images/calendar_buttonBg.gif);';
strFrame+=' font-family:宋体;}';
strFrame+='TD{FONT-SIZE: 9pt;font-family:宋体;}';
strFrame+='</style>';
strFrame+='<scr' + 'ipt>';
strFrame+='var datelayerx,datelayery;    /*存放日历控件的鼠标位置*/';
strFrame+='var bDrag;    /*标记是否开始拖动*/';
strFrame+='function document.onmousemove()    /*在鼠标移动事件中，如果开始拖动日历，则移动日历*/';
strFrame+='{if(bDrag && window.event.button==1)';
strFrame+='    {var DateLayer=parent.document.all.meizzDateLayer.style;';
strFrame+='        DateLayer.posLeft += window.event.clientX-datelayerx;/*由于每次移动以后鼠标位置都恢复为初始的位置，因此写法与div中不同*/';
strFrame+='        DateLayer.posTop += window.event.clientY-datelayery;}}';
strFrame+='function DragStart()        /*开始日历拖动*/';
strFrame+='{var DateLayer=parent.document.all.meizzDateLayer.style;';
strFrame+='    datelayerx=window.event.clientX;';
strFrame+='    datelayery=window.event.clientY;';
strFrame+='    bDrag=true;}';
strFrame+='function DragEnd(){        /*结束日历拖动*/';
strFrame+='    bDrag=false;}';
strFrame+='</scr' + 'ipt>';
strFrame+='<div style="z-index:9999;position: absolute; left:0; top:0;"  onselectstart="return false"><span id=tmpSelectYearLayer style="z-index: 9999;position: absolute;top: 3; left: 19;display: none;"></span>';
strFrame+='<span id=tmpSelectMonthLayer style="z-index: 9999;position: absolute;top: 3; left: 78;display: none"></span>';
strFrame+='<table cellSpacing="0" cellPadding="0" width="100%" border="0"><tr><td>';
// 控件边框颜色
strFrame+='<table border=1 cellspacing=0 cellpadding=0 width=182 height=160 bgColor="#FFFFFF" borderColorLight=#70a8b5 borderColorDark="#ffffff" >'; //底部按钮框架，边线颜色
strFrame+=' <tr ><td width=182 height=23  bgcolor=#FFFFFF><table border=0 cellspacing=0 cellpadding=0 width=182  height=27>';//顶部月框架
strFrame+='      <tr align=center ><td width=16 align=center bgcolor=#B6CAE4 style="font-size:12px;cursor: hand;background-image:url(../include/images/calendar_topBg.gif); background-position:top; background-repeat: repeat-x "';
strFrame+='        onclick="parent.meizzPrevM()" title="向前翻 1 月 " id=img1 ><img align="absmiddle" src="../include/images/calendar_topButton.gif"   ><img align="absmiddle" src="../include/images/calendar_topButton2.gif" style="display:none;" id=im2 >'; //左右翻页按纽
strFrame+='        </td><td width=60 align=center  style="font-size:12px;cursor:pointer;background-image:url(../include/images/calendar_topBg.gif); background-position:top; background-repeat: repeat-x "  ';//年份按纽
strFrame+='onmouseover="style.backgroundColor=\'#D7E1F0\'" onmouseout="style.backgroundColor=\'white\'" ';
strFrame+='onclick="parent.tmpSelectYearInnerHTML(this.innerText.substring(0,4))" title="点击这里选择年份"><span  id=meizzYearHead   style="color:#ffffff;text-align:center"></span></td>';
strFrame+='<td width=48 align=center style="font-size:12px;cursor:pointer;background-image:url(../include/images/calendar_topBg.gif); background-position:top; background-repeat: repeat-x "   ';  //月份选折
strFrame+='   onclick="parent.tmpSelectMonthInnerHTML(this.innerText.length==3?this.innerText.substring(0,1):this.innerText.substring(0,2))"';
strFrame+='        title="点击这里选择月份"><span id=meizzMonthHead   style="color:#ffffff;text-align:center"></span></td>';
strFrame+='        <td width=16 bgcolor=#B6CAE4 align=center style="font-size:12px;cursor:pointer;background-image:url(../include/images/calendar_topBg.gif); background-position:top; background-repeat: repeat-x " ';
strFrame+='         onclick="parent.meizzNextM()" title="向后翻 1 月" ><img align="absmiddle" src="../include/images/calendar_topButton3.gif"   ><img align="absmiddle" src="../include/images/calendar_topButton4.gif" style="display:none;" ></td></tr>';
strFrame+='    </table></td></tr>';
strFrame+=' <tr ><td width=182 height=18 >';  //
strFrame+='<table style="cursor:move;background-image:url(../include/images/calendar_dateBg.gif); background-position:top; background-repeat: repeat-x " border=0 cellspacing=0 cellpadding=0  ' + (bMoveable? 'onmousedown="DragStart()" onmouseup="DragEnd()"':'');
strFrame+='   width="100%" height=18  style="cursor:' + (bMoveable ? 'move':'default') + '">';
strFrame+='<tr  valign="middle" align="center"><td style="font-size:12px;color:#245d6e" ><b>日</b></td>';
strFrame+='<td style="font-size:12px;color:#245d6e" ><b>一</b></td><td style="font-size:12px;color:#245d6e" ><b>二</b></td>';
strFrame+='<td style="font-size:12px;color:#245d6e" ><b>三</b></td><td style="font-size:12px;color:#245d6e" ><b>四</b></td>';
strFrame+='<td style="font-size:12px;color:#245d6e"   ><b>五</b></td><td style="font-size:12px;color:#245d6e" ><b>六</b></td></tr>';
strFrame+='</table></td></tr>';
strFrame+=' <tr ><td width="100%" height=120 >';
strFrame+='    <table border=1 cellspacing=0 cellpadding=0 borderColorDark=#ffffff bgColor=#FFFFFF borderColorLight=#ffffff width="100%" height=120 >';//日期的TABLE
var n=0; for (j=0;j<5;j++){ strFrame+= ' <tr align=center >'; for (i=0;i<7;i++){
strFrame+='<td width=25 height=21 id=meizzDay'+n+' style="font-size:12px;"  onclick=parent.meizzDayClick(this.innerText,0)></td>';n++;}
strFrame+='</tr>';}
strFrame+='      <tr align=center >';
for (i=35;i<39;i++)strFrame+='<td width=25 height=20 id=meizzDay'+i+' style="font-size:12px" onclick="parent.meizzDayClick(this.innerText,0)"></td>';
strFrame+='        <td colspan=3 align=right ><span id="closeLink"></span>&nbsp;&nbsp;<span onclick=parent.closeLayer() style="font-size:12px;cursor: hand"';
strFrame+='          title="关闭"><u><SPAN style=" color: #0000FF">关闭</span></u></span>&nbsp;</td></tr>';
strFrame+='    </table></td></tr><tr ><td >';
strFrame+='        <table border=0 cellspacing=0 cellpadding=0 width=100% hight=31 style="background-image:url(../include/images/calendar_bottomBg.gif); background-position:top; background-repeat: repeat-x " >';
strFrame+='          <tr ><td height=31 ';
strFrame+='              align=center><input  style="cursor:hand" type=button class=button value=Today onclick="parent.meizzToday()" ';
strFrame+='             onfocus="this.blur()" title="当前日期" style="font-size: 12px; height: 19px; cursor:hand"></td> ';

strFrame+='</tr></table></td></tr></table></td></tr></table></div>';

window.frames.meizzDateLayer.document.writeln(strFrame);
window.frames.meizzDateLayer.document.close();        //解决ie进度条不结束的问题

//==================================================== WEB 页面显示部分 ======================================================
var callFunction="";
//加入回调函数功能,edit by liutongxin,如果需要回调的使用此函数设置日期控件
function setday2(tt,ps,obj,callFunc) {
	setday(tt,ps,obj);
	callFunction = callFunc;
}
//
var outObject;
var outButton;        //点击的按钮
var outDate="";        //存放对象的日期
var odatelayer=window.frames.meizzDateLayer.document.all;        //存放日历对象
function setday(tt,ps,obj) //主调函数
{
	//添加清空链接
	if(obj && obj.closeSpan=="true"){
		var html='<span onclick="parent.document.getElementById(\''+tt.id+'\').value=\'\';parent.closeLayer();" style="font-size:12px;cursor:pointer" title="清空"><u><SPAN style="color:#0000FF">清空</span></u></span>';
		window.frames.meizzDateLayer.document.getElementById("closeLink").innerHTML=html;
	}
	
    //if (arguments.length > 2){alert("对不起！传入本控件的参数太多！");return;}
    if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}
    
    //判断控件位置加上日历是否超过了屏幕
	if((event.screenY)+320>screen.height){
    	top.document.body.scrollTop += (event.screenY)+320-screen.height;
    }
    
    var dads = document.all.meizzDateLayer.style;
    
    var th = tt;
    if(ps==1){//显示在控件下方
	    var ttop = tt.offsetTop;     //TT控件的定位点高
    }else{//显示在控件上方
    	var ttop = tt.offsetTop-248;
    }
    
    var thei = tt.clientHeight; //TT控件本身的高
    var tleft = tt.offsetLeft;    //TT控件的定位点宽
    var ttyp = tt.type;          //TT控件的类型
    /*while (tt = tt.offsetParent){
    	ttop+=tt.offsetTop; 
    	tleft+=tt.offsetLeft;
    	//=================================
    	//Claudio修改： 防止div层移动滚动条时
    	if(parent.flag!=null){
    		if(tt.scrollTop!=0)
    			ttop -= tt.scrollTop;
    		if(tt.scrollLeft!=0)
    			tleft -= tt.scrollLeft;
    	}
    	//=================================
    }
    */
    
    //如果控件是放在div层中的，设置atDiv=true，以div的相对位置显示控件
	while ((tt = tt.offsetParent) && (!obj || obj.atDiv!="true" || tt.tagName!="DIV")){
		ttop+=tt.offsetTop; 
    	tleft+=tt.offsetLeft;
    	//=================================
    	//Claudio修改： 防止div层移动滚动条时
    	if(parent.flag!=null){
    		if(tt.scrollTop!=0)
    			ttop -= tt.scrollTop;
    		if(tt.scrollLeft!=0)
    			tleft -= tt.scrollLeft;
    	}
    	//=================================
	}
    
    dads.top = (ttyp=="image")? ttop+thei : ttop+thei+6;
    dads.left = tleft;
    //outObject = (arguments.length == 2) ? th : obj;
    //outButton = (arguments.length == 1) ? null : th;    //设定外部点击的按钮
	outObject = th;
    outButton = (arguments.length == 1) ? null : obj;    //设定外部点击的按钮
    //根据当前输入框的日期显示日历的年月
    var reg = /^(\d+)-(\d{1,2})-(\d{1,2})$/; 
    var r = outObject.value.match(reg); 
    if(r!=null){
        r[2]=r[2]-1; 
        var d= new Date(r[1], r[2],r[3]); 
        if(d.getFullYear()==r[1] && d.getMonth()==r[2] && d.getDate()==r[3]){
            outDate=d;        //保存外部传入的日期
        }
        else outDate="";
            meizzSetDay(r[1],r[2]+1);
    }
    else{
        outDate="";
        //meizzSetDay(new Date().getFullYear(), new Date().getMonth() + 1);
        setTimeout(function(){meizzSetDay(new Date().getFullYear(), new Date().getMonth() + 1);},50);
    }
    dads.display = "";

    event.returnValue=false;
}

var MonHead = new Array(12);               //定义阳历中每个月的最大天数
    MonHead[0] = 31; MonHead[1] = 28; MonHead[2] = 31; MonHead[3] = 30; MonHead[4] = 31; MonHead[5] = 30;
    MonHead[6] = 31; MonHead[7] = 31; MonHead[8] = 30; MonHead[9] = 31; MonHead[10] = 30; MonHead[11] = 31;

var meizzTheYear=new Date().getFullYear(); //定义年的变量的初始值
var meizzTheMonth=new Date().getMonth()+1; //定义月的变量的初始值
var meizzWDay=new Array(39);               //定义写日期的数组

document.onclick = function() //任意点击时关闭该控件    //ie6的情况可以由下面的切换焦点处理代替
{
	with(window.event)
	{
		if (srcElement.getAttribute("Author")==null && srcElement != outObject && srcElement != outButton){
			closeLayer();
	    }
	}
}

document.onkeyup = function()        //按Esc键关闭，切换焦点关闭
{
    if (window.event.keyCode==27){
        if(outObject)outObject.blur();
        	closeLayer();
    }
    else if(document.activeElement){
        if(document.activeElement.getAttribute("Author")==null && document.activeElement != outObject && document.activeElement != outButton)
        {
            closeLayer();
        }
	}
}

function meizzWriteHead(yy,mm) //往 head 中写入当前的年与月
{
    odatelayer.meizzYearHead.innerText = yy + " 年";
    odatelayer.meizzMonthHead.innerText = mm + " 月";
}

function tmpSelectYearInnerHTML(strYear) //年份的下拉框
{
	if (strYear.match(/\D/)!=null){alert("年份输入参数不是数字！");return;}
	var m = (strYear) ? strYear : new Date().getFullYear();
	if (m < 1000 || m > 9999) {alert("年份值不在 1000 到 9999 之间！");return;}
	var n = m - 100;
	if (n < 1000) n = 1000;
	if (n + 26 > 9999) n = 9974;
	var s = "&nbsp;&nbsp;&nbsp;<select  name=tmpSelectYear style='font-size: 12px' "
	    s += "onblur='document.all.tmpSelectYearLayer.style.display=\"none\"' "
	    s += "onchange='document.all.tmpSelectYearLayer.style.display=\"none\";"
	    s += "parent.meizzTheYear = this.value; parent.meizzSetDay(parent.meizzTheYear,parent.meizzTheMonth)'>\r\n";
	var selectInnerHTML = s;
	for (var i = n; i < n + 200; i++)
	{
	    if (i == m)
	    {selectInnerHTML += "<option value='" + i + "' selected>" + i + "年" + "</option>\r\n";}
	    else {selectInnerHTML += "<option value='" + i + "'>" + i + "年" + "</option>\r\n";}
	}
	selectInnerHTML += "</select>";
	odatelayer.tmpSelectYearLayer.style.display="";
	odatelayer.tmpSelectYearLayer.innerHTML = selectInnerHTML;
	odatelayer.tmpSelectYear.focus();
}

function tmpSelectMonthInnerHTML(strMonth) //月份的下拉框
{
	if (strMonth.match(/\D/)!=null){alert("月份输入参数不是数字！");return;}
	var m = (strMonth) ? strMonth : new Date().getMonth() + 1;
	var s = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select  name=tmpSelectMonth style='font-size: 12px' "
	    s += "onblur='document.all.tmpSelectMonthLayer.style.display=\"none\"' "
	    s += "onchange='document.all.tmpSelectMonthLayer.style.display=\"none\";"
	    s += "parent.meizzTheMonth = this.value; parent.meizzSetDay(parent.meizzTheYear,parent.meizzTheMonth)'>\r\n";
	var selectInnerHTML = s;
	for (var i = 1; i < 13; i++)
	{
	    if (i == m)
	    {selectInnerHTML += "<option value='"+i+"' selected>"+i+"月"+"</option>\r\n";}
	    else {selectInnerHTML += "<option value='"+i+"'>"+i+"月"+"</option>\r\n";}
	}
	selectInnerHTML += "</select>";
	odatelayer.tmpSelectMonthLayer.style.display="";
	odatelayer.tmpSelectMonthLayer.innerHTML = selectInnerHTML;
	odatelayer.tmpSelectMonth.focus();
}

function closeLayer()               //这个层的关闭
{
    document.all.meizzDateLayer.style.display="none";
    //执行回调函数,edit by liutongxin
    if(callFunction != "") {
    	callFunction();
    	//清空回调功能
    	callFunction="";
    }
}

function IsPinYear(year)            //判断是否闰平年
{
    if (0==year%4&&((year%100!=0)||(year%400==0))) return true;else return false;
}

function GetMonthCount(year,month) //闰年二月为29天
{
    var c=MonHead[month-1];if((month==2)&&IsPinYear(year)) c++;return c;
}
function GetDOW(day,month,year)     //求某天的星期几
{
    var dt=new Date(year,month-1,day).getDay()/7; return dt;
}

function meizzPrevY() //往前翻 Year
{
    if(meizzTheYear > 999 && meizzTheYear <10000){meizzTheYear--;}
    else{alert("年份超出范围（1000-9999）！");}
    meizzSetDay(meizzTheYear,meizzTheMonth);
}
function meizzNextY() //往后翻 Year
{
    if(meizzTheYear > 999 && meizzTheYear <10000){meizzTheYear++;}
    else{alert("年份超出范围（1000-9999）！");}
    meizzSetDay(meizzTheYear,meizzTheMonth);
}
function meizzToday() //Today Button
{
    var today;
    meizzTheYear = new Date().getFullYear();
    meizzTheMonth = new Date().getMonth()+1;
    meizzTheDay = new Date().getDate();
    if(meizzTheMonth<10)	meizzTheMonth = "0" + meizzTheMonth;
	if(meizzTheDay<10)	meizzTheDay = "0" + meizzTheDay;
    today=new Date().getDate();
    //meizzSetDay(meizzTheYear,meizzTheMonth);
    if(outObject){
        outObject.value=meizzTheYear + "-" + meizzTheMonth + "-" + meizzTheDay;
    }
    closeLayer();
}
function meizzPrevM() //往前翻月份
{
    if(meizzTheMonth>1){meizzTheMonth--}else{meizzTheYear--;meizzTheMonth=12;}
    meizzSetDay(meizzTheYear,meizzTheMonth);
}
function meizzNextM() //往后翻月份
{
    if(meizzTheMonth==12){meizzTheYear++;meizzTheMonth=1}else{meizzTheMonth++}
    meizzSetDay(meizzTheYear,meizzTheMonth);
}

function meizzSetDay(yy,mm)   //主要的写程序**********
{
	meizzWriteHead(yy,mm);
	//设置当前年月的公共变量为传入值
	meizzTheYear=yy;
	meizzTheMonth=mm;
	
	for (var i = 0; i < 39; i++){meizzWDay[i]=""}; //将显示框的内容全部清空
	var day1 = 1,day2=1,firstday = new Date(yy,mm-1,1).getDay(); //某月第一天的星期几
	for (i=0;i<firstday;i++)meizzWDay[i]=GetMonthCount(mm==1?yy-1:yy,mm==1?12:mm-1)-firstday+i+1    //上个月的最后几天
	for (i = firstday; day1 < GetMonthCount(yy,mm)+1; i++){meizzWDay[i]=day1;day1++;}
	for (i=firstday+GetMonthCount(yy,mm);i<39;i++){meizzWDay[i]=day2;day2++}
	for (i = 0; i < 39; i++)
	{ 
		var da = eval("odatelayer.meizzDay"+i)     //书写新的一个月的日期星期排列
	    if (meizzWDay[i]!="")
	    { 
	        //初始化边框
	        da.borderColorLight="#ffffff";
	        da.borderColorDark="#ffffff";
	        if(i<firstday)        //上个月的部分
	        {
	            da.innerHTML="<font style=' color: #aaaaaa;'>" + meizzWDay[i] + "</font>";
	            da.title=(mm==1?12:mm-1) +"月" + meizzWDay[i] + "日";
	            da.onclick=Function("meizzDayClick(this.innerText,-1)");
	            
	            if(!outDate)
	                da.style.backgroundColor = ((mm==1?yy-1:yy) == new Date().getFullYear() && 
	                    (mm==1?12:mm-1) == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate()) ?
	                    "#E4E3F2":"#FFFFFF";
	            else
	            {
	                da.style.backgroundColor =((mm==1?yy-1:yy)==outDate.getFullYear() && (mm==1?12:mm-1)== outDate.getMonth() + 1 && 
	                meizzWDay[i]==outDate.getDate())? "#E8F5E7" : // 选中日期颜色
	                (((mm==1?yy-1:yy) == new Date().getFullYear() && (mm==1?12:mm-1) == new Date().getMonth()+1 && 
	                meizzWDay[i] == new Date().getDate()) ? "#E4E3F2":"#FFFFFF"); // 当前系统时间颜色
	                //将选中的日期显示为凹下去
	                if((mm==1?yy-1:yy)==outDate.getFullYear() && (mm==1?12:mm-1)== outDate.getMonth() + 1 && 
	                meizzWDay[i]==outDate.getDate())
	                {
	                    //da.borderColorLight="#E4E3F2";
						//da.borderColorDark="#E4E3F2"; //     选择日期边框颜色
	                }
	            }
	            
	        }
	        else if (i>=firstday+GetMonthCount(yy,mm))        //下个月的部分
	        {
	            da.innerHTML="<font style=' color: #aaaaaa;'>" + meizzWDay[i] + "</font>";//下月字体颜色
	            da.title=(mm==12?1:mm+1) +"月" + meizzWDay[i] + "日";
	            da.onclick=Function("meizzDayClick(this.innerText,1)");
	            if(!outDate)
	                da.style.backgroundColor = ((mm==12?yy+1:yy) == new Date().getFullYear() && 
	                    (mm==12?1:mm+1) == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate()) ?
	                    "#E4E3F2":"#FFFFFF";
	            else
	            {
	                da.style.backgroundColor =((mm==12?yy+1:yy)==outDate.getFullYear() && (mm==12?1:mm+1)== outDate.getMonth() + 1 && 
	                meizzWDay[i]==outDate.getDate())? "#E8F5E7" : // 选中日期颜色
	                (((mm==12?yy+1:yy) == new Date().getFullYear() && (mm==12?1:mm+1) == new Date().getMonth()+1 && 
	                meizzWDay[i] == new Date().getDate()) ? "#E4E3F2":"#FFFFFF"); // 当前系统时间
	                //将选中的日期显示为凹下去
	                if((mm==12?yy+1:yy)==outDate.getFullYear() && (mm==12?1:mm+1)== outDate.getMonth() + 1 && 
	                meizzWDay[i]==outDate.getDate())
	                {
	                    da.borderColorLight="#E4E3F2";
	                    da.borderColorDark="#E4E3F2"; //     选择日期边框颜色
	                }
	            }
	        }
	        else        //本月的部分
	        {
	            da.innerHTML="<font style='color: none;'>" + meizzWDay[i] + "</FONT>";
	            da.title=mm +"月" + meizzWDay[i] + "日";
	            da.onclick=Function("meizzDayClick(this.innerText,0)");        //给td赋予onclick事件的处理
	            //如果是当前选择的日期，则显示亮蓝色的背景；如果是当前日期，则显示暗黄色背景
	           if(!outDate){
	                da.style.backgroundColor = (yy == new Date().getFullYear() && mm == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate())?
	                    "#E4E3F2":"#FFFFFF";
	            }else{
	                da.style.backgroundColor =(yy==outDate.getFullYear() && mm== outDate.getMonth() + 1 && meizzWDay[i]==outDate.getDate())?
	                    "#d7eff1":((yy == new Date().getFullYear() && mm == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate())?
	                    "#ffffff":"#ffffff"); // 前一个当前系统时间,后一个是本月时间低色
	                //将选中的日期显示为凹下去
	                if(yy==outDate.getFullYear() && mm== outDate.getMonth() + 1 && meizzWDay[i]==outDate.getDate())
	                {
	                    da.borderColorLight="#8b003b";
	                    da.borderColorDark="#8b003b"; //     选择日期边框颜色
	                }
	            }
	            if((firstday+meizzWDay[i])%7==0){
	            	da.style.color="#009900";
	            }else if((firstday+meizzWDay[i])%7==1){
	            	da.style.color="#ff9600";
	            }else{
	            	da.style.color="#010101";
	            }
	        }
	        da.style.cursor="pointer"
	        da.onmouseover=Function("this.backgroundColor='#8b003b';this.borderColorDark='#8b003b';this.borderColorLight='#8b003b';");
	        da.onmouseout=Function("this.bgColor='#000000';this.borderColorDark='#ffffff';this.borderColorLight='#ffffff';");
	    }
	    else{
	    	da.innerHTML="";
	    	da.style.backgroundColor="";
	    	da.style.cursor="default";
	    	da.onmouseover=Function("this.backgroundColor='#000000';this.borderColorDark='#000099';this.borderColorLight='#000099';");
	        da.onmouseout=Function("this.bgColor='#000000';this.borderColorDark='#dae5f2';this.borderColorLight='#dae5f2';");
		}
	}
}

function meizzDayClick(n,ex) //点击显示框选取日期，主输入函数*************
{
	var yy=meizzTheYear;
	var mm = parseInt(meizzTheMonth)+ex;    //ex表示偏移量，用于选择上个月份和下个月份的日期
    //判断月份，并进行对应的处理
    if(mm<1){
        yy--;
        mm=12+mm;
    }
    else if(mm>12){
        yy++;
        mm=mm-12;
	}
	    
	if (mm < 10){
		mm = "0" + mm;
	}
	if (outObject)
	{
	    if (!n) {//outObject.value=""; 
	    return;}
	    if ( n < 10){n = "0" + n;}
	    outObject.value= yy + "-" + mm + "-" + n ; //注：在这里您可以输出改成您想要的格式
	    closeLayer(); 
	}
	else {
		closeLayer(); 
		alert("您所要输出的控件对象并不存在！");
	}
}

function switchSysBar(){
	if (switchPoint.innerText==3){
		switchPoint.innerText=4
		document.all("frmTitle").style.display="none"
	}
	else{
		switchPoint.innerText=3
		document.all("frmTitle").style.display=""
	}
}