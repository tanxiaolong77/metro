/*!
* 重庆大学网络教育学院技术支持部
* Date: 2012-03-21 16:57   
*/
//-------------------------------------------------- Global Function --------------------------------------------------
//创建名称空间，避免javascript 污染
//名称空间规范和 C# 一样

//--------------------------------------------------  Home/Index --------------------------------------------------
//载入指定的Url到指定的Html容器中
function LoadUrlToHtmlContainer(targetUrl, containerId) {
    AjaxSubmit(targetUrl, "GET", function (data) {
        $("#" + containerId).html(data);
    });
    //alert('重新载入WebGrid!');
}

//动态载入JS文件或者CSS文件到页面中
function $import(path, type, title) {
    var s, i;
    if (!type) type = path.substr(path.lastIndexOf(".") + 1);
    if (type == "js") {
        var ss = document.getElementsByTagName("script");
        for (i = 0; i < ss.length; i++) {
            if (ss[i].src && ss[i].src.indexOf(path) != -1 || ss[i].title == title) return ss[i];
        }
        s = document.createElement("script");
        s.type = "text/javascript";
        s.src = path;
        if (title) s.title = title;
    }
    else if (type == "css") {
        var ls = document.getElementsByTagName("link");
        for (i = 0; i < ls.length; i++) {
            if (ls[i].href && ls[i].href.indexOf(path) != -1 || ls[i].title == title) return ls[i];
        }
        s = document.createElement("link");
        s.rel = "stylesheet";
        s.type = "text/css";
        s.href = path;
        if (title) s.title = title;
        s.disabled = false;
    }
    else return;
    var head = document.getElementsByTagName("head")[0];
    head.appendChild(s);
    return s;
}
//时间事件
function tick() {
    var hours, minutes, seconds, xfile;
    var intHours, intMinutes, intSeconds;
    var today, theday;
    today = new Date();
    function initArray() {
        this.length = initArray.arguments.length
        for (var i = 0; i < this.length; i++)
            this[i + 1] = initArray.arguments[i]
    }
    var d = new initArray(
   " 星期日",
   " 星期一",
   " 星期二",
   " 星期三",
   " 星期四",
   " 星期五",
   " 星期六");
    var year = today.getYear();
    if (year < 1900) year = year + 1900;
    theday = year + "年" + [today.getMonth() + 1] + "月" + today.getDate() + "日" + d[today.getDay() + 1];
    intHours = today.getHours();
    intMinutes = today.getMinutes();
    intSeconds = today.getSeconds();
    if (intHours == 0) {
        hours = "12:";
        xfile = " 午夜 ";
    } else if (intHours < 12) {
        hours = intHours + ":";
        xfile = " 上午 ";
    } else if (intHours == 12) {
        hours = "12:";
        xfile = " 正午 ";
    } else {
        intHours = intHours - 12
        hours = intHours + ":";
        xfile = " 下午 ";
    }
    if (intMinutes < 10) {
        minutes = "0" + intMinutes + ":";
    } else {
        minutes = intMinutes + ":";
    }
    if (intSeconds < 10) {
        seconds = "0" + intSeconds + " ";
    } else {
        seconds = intSeconds + " ";
    }
    timeString = theday + xfile + hours + minutes + seconds;
    $('#clock').html(timeString);
    window.setTimeout("tick();", 100);
}



//-------------------------------------------------- CheckBox 选择 --------------------------------------------------
//全选
function CheckAll() {
    $(".webgrid input[type=checkbox]").each(function () {
        $(this).attr("checked", true);       
    });
}

//反选
function Invert() {
    $(".webgrid input[type=checkbox]").each(function () {
        if ($(this).attr("checked")) {
            $(this).attr("checked", false);           
        } else {
            $(this).attr("checked", true);
        }
    });

}
//不选
function Deselection() {
    $(".webgrid input[type=checkbox]").each(function () {
        $(this).attr("checked", false);       
    });
}

//取得选中的数据项编号
function GetSelectedList() {
    var selectList = "";  //选中的ID串，用“，”号分隔。
    $(".webgrid input[type=checkbox]").each(function () {
        if ($(this).attr("checked")) {
            if ($(this).attr("value") != "") {
                selectList += (selectList == "" ? "" : ",") + $(this).attr("value");
            }
        }
    });
    return selectList;
}


//取得修改的文本框内容和编号
function GetWebGridTextList(ClassName) {
    var textList = "";  //返回被修改的text，格式ID:VALUE，多个用，分隔
    $("." + ClassName + " input[type='text']").each(function () {
        if ($(this).attr("value") != $(this).attr("title")) {  //title保存了初始值。
            textList += (textList == "" ? "" : ",") + $(this).attr("name") + ":" + $(this).val();  //ID：Value格式。
        }

    });
    return textList;
}

//提交 Ajax 查询
//targetUrl: Ajax 查询地址
//submitType: 提交类型:"GET","POST"
//successFunction:执行成功后的调用方法。
function AjaxSubmit(targetUrl, submitType, successFunction) {
    var strArray = SplitUrl(targetUrl); //切分Url查询字符串

    $.ajax({
        async: false, //默认同步执行
        url: strArray[0],
        data: strArray[1],
        type: submitType,
        success: function (data) {
            //以下代码主要用于处理系统异常或者无权限的时候给出提示信息
            if (data.toString() == '[object Object]') { //返回值是数据对象，正常加载
                successFunction(data);
            }
            else if (data.indexOf('系统异常或者无权限等情况用到的提示信息') > -1) { //输出系统异常和权限提示信息
                //OpenErrorDialog(data);
                openAlertBox("error", "信息提示！", data);
            }
            else { //其它情况默认为正常加载，如有需要处理的返回类型再按需求增加处理流程
                successFunction(data);
            }
        },
        error: function (XMLHttpRequest, textStatus) { //请求出错处理
            openAlertBox("error", "信息提示！", '提交服务器发生错误!错误代码：' + XMLHttpRequest.status + '<br/>targetUrl:' + targetUrl);
        },
        cache: false
    });
}

//将Url用“?”号切开
//用于Ajax提交
function SplitUrl(targetUrl) {
    var urlArray = new Array();
    urlArray = encodeURI(targetUrl).split('?');

    if (urlArray.length == 1) {
        var strArray = new Array(2);
        strArray[0] = urlArray[0];
        strArray[1] = "";
        return strArray;
    }
    else {
        return urlArray;
    }
}
//将Form中的所有表单拼接为一个get的url地址串
function getFormQueryString(formId)    //frmID是表单的ID号，请在表单form中先命名一个ID号
{
    var frmId = document.getElementById(formId);
    var i, queryString = "", and = "";
    var item;
    var itemValue;
    for (i = 0; i < frmId.length; i++) {
        item = frmId[i];
        if (item.name != '') {
            if (item.type == 'select-one') {
                itemValue = item.options[item.selectedIndex].value;
            }
            else if (item.type == 'checkbox' || item.type == 'radio') {
                if (item.checked == false) {
                    continue;
                }
                itemValue = item.value;
            }
            else if (item.type == 'button' || item.type == 'submit' || item.type == 'reset' || item.type == 'image') {
                continue;
            }
            else {
                itemValue = item.value;
            }
            itemValue = escape(itemValue);
            queryString += and + item.name + '=' + itemValue;
            and = "&";
        }
    }
    return frmId.getAttribute('action') + '?' + queryString;
}
//JQuery扩展 对象居中
jQuery.fn.center = function () {
    this.css("position", "absolute");
    var t = ($(window).height() - this.height() - 150) / 2;
    if (t <= 0) t = 10;
    var top = t + $(window).scrollTop();
    if (top < 0) top = $(window).height() >= this.height() ? 10 : 0;
    this.css("top", top + "px");
    var left = ($(window).width() - this.width()) / 2 + $(window).scrollLeft();
    if ($(window).width() <= this.width() + 20) left = 0;
    this.css("margin-left", "0");
    this.css("left", left + "px");
    return this;
}

function saveScrollTop() {

    var cookietime = new Date();
    cookietime.setTime(cookietime.getTime() + (60 * 60 * 1000));//coockie保存一小时
    $.cookie("scrollTop", $(document).scrollTop(), { expires: cookietime });

}

function hideWindow() {
    //$("body").eq(0).css("overflow","scroll");
    $('#openWindowIFrame').attr('src', '');
    $(window).unbind("resize");
    $("body").css("overflow", "auto");
    $("html,body").animate({ scrollTop: $.cookie("scrollTop") }, 100);//跳到保存前的滚动条位置
    //$('#openWindowBtn').button('reset');
}

//打开模态窗口
//title:标题; url:窗口内容地址,width:窗口宽度,height:窗口高度,isCloseOnly:只显示关闭按钮
function openWindow(title, url, width, height) {
    $('#openWindowModal').unbind('hidden', hideWindow);//取消隐藏绑定事件
    saveScrollTop();//保存页面滚动条位置。
    $("body").css("overflow", "hidden");
    if (width == '0' || width == null) { w = $(window).width() - 40 } else { w = width };
    if (height == '0' || height == null) { h = $(window).height() - 20 } else { h = height };
    $('#openWindowModal h3').html(title);
    $('#openWindowBtn').show();
    $('#openWindowIFrame').attr('src', url);
    $('#openWindowModal').width(w);
    $('#openWindowModal .modal-body').css('max-height', '9999px');
    $('#openWindowModal').height(h);
    $('#openWindowModal .modal-body').height(h - 50);
    $('#openWindowIFrame').height(h - 60);
    $('#openWindowModal').center();
    //$("body").eq(0).css("overflow","hidden");
    $('#openWindowModal').modal({ keyboard: true });
    $('#openWindowModal').bind('hidden', hideWindow);//重新绑定窗口隐藏事件    
    $(window).bind("resize", function () {
        if (width == '0' || width == null) w = $(window).width() - 40;
        if (height == '0' || height == null) h = $(window).height() - 20;
        $('#openWindowModal').width(w);
        $('#openWindowModal').height(h);
        $('#openWindowModal .modal-body').height(h - 50);
        $('#openWindowIFrame').height(h - 60);
        $('#openWindowModal').center();
    })

}


//打开信息窗口
//
function openInfoWindow(message, width, height) {
    if (width == '0' || width == null) { w = $(window).width() - 40 } else { w = width };
    if (height == '0' || height == null) { h = $(window).height() - 20 } else { h = height };
    $('#modal-info').css("text-align:center");
    $('#modal-info').html(message);
    $('#openInfoModal').width(w);
    $('#openInfoModal .modal-body').css('max-height', '9999px');
    $('#openInfoModal').height(h);
    $('#openInfoModal .modal-body').height(h - 50);
    $('#openInfoModal').height(h - 60);
    $('#openInfoModal').center();
    //$("body").eq(0).css("overflow","hidden");
    $('#openInfoModal').modal({ keyboard: true });
}
//打开模态窗口
//title:标题; id:隐藏的div,width:窗口宽度,height:窗口高度,isCloseOnly:只显示关闭按钮
function openDivWindow(title, id, width, height) {
    if (width == '0' || width == null) { w = $(window).width() - 40 } else { w = width };
    if (height == '0' || height == null) { h = $(window).height() - 20 } else { h = height };
    $('#openWindowModalDiv h3').html(title);
    $('#openWindowBtn').show();
    var divHtml = $('#' + id).html();
    $('#openWindowHtml').html(divHtml);
    $('#openWindowModalDiv').width(w);
    $('#openWindowModalDiv .modal-body').css('max-height', '9999px');
    $('#openWindowModalDiv').height(h);
    $('#openWindowModalDiv .modal-body').height(h - 50);
    $('#openWindowHtml').height(h - 60);
    $('#openWindowModalDiv').center();
    //$("body").eq(0).css("overflow","hidden");
    $('#openWindowModalDiv').modal({ keyboard: true });
    $('#openWindowModalDiv').bind('hidden', function () {
        //$("body").eq(0).css("overflow","scroll");
        $('#openWindowHtml').html('');
        $(window).unbind("resize");
        //$('#openWindowBtn').button('reset');
    });

    $(window).bind("resize", function () {
        if (width == '0' || width == null) w = $(window).width() - 40;
        if (height == '0' || height == null) h = $(window).height() - 20;
        $('#openWindowModalDiv').width(w);
        $('#openWindowModalDiv').height(h);
        $('#openWindowModalDiv .modal-body').height(h - 50);
        $('#openWindowHtml').height(h - 60);
        $('#openWindowModalDiv').center();
    })

}


//关闭模态窗口
function closeWindow() {
    $('#openWindowModal').modal('hide');
}
function closeDivWindow() {
    $('#openWindowModalDiv').modal('hide');
}







//删除操作
//targetUrl:删除操作提交Url地址
function DeleteDialog(targetUrl) {
    openConfirmBox('你确定要删除该条数据吗？<br>该条记录删除后将无法恢复！', function () {
        //确认按钮事件
        AjaxSubmit(targetUrl, "GET", function (data) {
            if (data.AjaxResult == "OK") {
                openAlertBox("success", "信息提示！", "数据删除成功！"); //"OK"为执行成功的返回值。
                document.body.scrollTop = 0; //跳转到页头
                setTimeout(function () {
                    window.location.reload();
                }, 1000);
            }
            else {
                openAlertBox("error", "信息提示！", data.AjaxResult);
                document.body.scrollTop = 0; //跳转到页头
            }
        })
    });
}


//启用操作
//targetUrl:启用操作提交Url地址
//successFunction:执行成功后的调用方法。
function ActiveDialog(targetUrl, currentStatus) {
    var activeValue = request(targetUrl, 'Active');
    var info = '你确定要停用该条数据吗？<br>该条记录停用后将不再使用！';
    if (activeValue == 'True') {
        info = '你确定要启用该条数据吗？';
    }

    openConfirmBox(info, function () {
        AjaxSubmit(targetUrl, "GET", function (data) {
            //确认按钮事件
            if (data.AjaxResult == "OK") {
                openAlertBox("success", "信息提示！", "状态修改成功！"); //"OK"为执行成功的返回值。
                document.body.scrollTop = 0; //跳转到页头

                setTimeout(function () {
                    window.location.reload();
                }, 3000);
            }
            else {
                openAlertBox("error", "信息提示！", data.AjaxResult)
                document.body.scrollTop = 0; //跳转到页头
            }
        })
    });
}



//打开浮动确认提示窗口
function openConfirmBox(message, successFunction) {
    $('#modal-html').html(message);

    $('#modal-confirm').die();
    if (successFunction != null)
        $('#modal-confirm').live('click', successFunction);

    $('#openTipsModal').modal();
}
//Mvc3 Ajax 执行成功后的调用函数(context:json) 向导式
function StepAjaxSubmitResultCheck(context) {
    if (context.AjaxResult == 'OK') {
        openAlertBox("success", "信息提示！", "执行操作成功!"); //"OK"为执行成功的返回值。
        document.body.scrollTop = 0; //跳转到页头
        setTimeout(function () {
            var rawUrl = $('#RawUrl').val();
            window.location.href = encodeURI(rawUrl);
        }, 1);
    } else {
        openAlertBox("error", "信息提示！", context.AjaxResult);
        document.body.scrollTop = 0; //跳转到页头
    }
}





//Mvc3 Ajax 执行成功后的调用函数(context:json)
function AjaxSubmitResultCheck(context) {
    if (context.AjaxResult == 'OK') {
        openAlertBox("success", "信息提示！", "执行操作成功!"); //"OK"为执行成功的返回值。
        document.body.scrollTop = 0; //跳转到页头
        setTimeout(function () {
            var rawUrl = $('#RawUrl').val();
            window.location.href = encodeURI(rawUrl);
        }, 3000);
    } else {
        openAlertBox("error", "信息提示！", context.AjaxResult);
        document.body.scrollTop = 0; //跳转到页头
    }
}


//Mvc3 Ajax 浮动层执行成功后的调用函数(context:json)
function AjaxFloatSubmitResultCheck(context) {
    if (context.AjaxResult == 'OK') {
        openAlertBox("success", "信息提示！", "执行操作成功!"); //"OK"为执行成功的返回值。
        document.body.scrollTop = 0; //跳转到页头
        setTimeout(function () {
            window.parent.closeWindow();
            window.parent.location.reload();
        }, 2000);
    } else {
        openAlertBox("error", "信息提示！", context.AjaxResult);
        document.body.scrollTop = 0; //跳转到页头
    }
}



//弹出层 Mvc3 Ajax 执行成功后的调用函数(context:json)
function ModelAjaxSubmitResultCheck(context) {
    if (context.AjaxResult == 'OK') {
        openAlertBox("success", "信息提示！", "执行操作成功!"); //"OK"为执行成功的返回值。
        $('form')[0].reset();
        $('html,body').animate({ scrollTop: '0px' }, 200);//货到顶部
        setTimeout(function () {
            window.parent.location.reload();
        }, 2000);
    } else {
        openAlertBox("error", "信息提示！", context.AjaxResult);
        $('html,body').animate({ scrollTop: '0px' }, 200);//货到顶部
    }
}

//打开 AlertBox
function openAlertBox(type, title, message) {
    var htmlString = "<div class='{AlertType}'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>{Title}</h4><br/>{Message}</div>";

    if (!type) type = "info";
    if (type == "success") {
        htmlString = htmlString.replace('{AlertType}', 'alert alert-success');
        htmlString = htmlString.replace('{Title}', title);
    } else if (type == "error") {
        htmlString = htmlString.replace('{AlertType}', 'alert alert-error');
        htmlString = htmlString.replace('{Title}', title);
    } else if (type == "info") {
        htmlString = htmlString.replace('{AlertType}', 'alert alert-info');
        htmlString = htmlString.replace('{Title}', title);
    } else if (type == "warn") {
        htmlString = htmlString.replace('{AlertType}', 'alert alert-block');
        htmlString = htmlString.replace('{Title}', title);
    }

    htmlString = htmlString.replace('{Message}', message);
    $('#alertBox').html(htmlString);
}


//取 url 参数值
function request(url, paras) {
    var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
    var paraObj = {}
    for (i = 0; j = paraString[i]; i++) {
        paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
    }
    var returnValue = paraObj[paras.toLowerCase()];
    if (typeof (returnValue) == "undefined") {
        return "";
    } else {
        return returnValue;
    }
}

function OpenModalDialog(url, obj, width, height) {
    var winArgs = window.showModalDialog(url + '&' + Math.random(), obj, 'help=no;resizable=no;status=no;scrollbars=no;dialogWidth=' + width + 'px;dialogHeight=' + height + 'px;center=yes');
    return winArgs;
    //    if (winArgs != null) {
    //        document.getElementById(winArgs[0] + '_panAttachmentInfo').style.visibility = 'visible';
    //        document.all[winArgs[0] + '_hlDownloadFileName'].innerText = winArgs[1];
    //document.all[winArgs[0] + '_hlDownloadFileName'].href = winArgs[1] + '&' + Math.random();
    //document.all[winArgs[0] + '_cbDeleteFile'].checked = false;
    //}
}


//跳转,解决IE7以下浏览器无法Request.UrlReferrer为空的解决 
function GetJump(url) {
    //判断是IE且版本小于7
    //下面这句只支持win2003和win2008平台，win xp有问题
    //if (getIEVersion() < 7 && getIEVersion() > 0) {
    //下面支持各平台
    if (getIEVersion() > 0) {
        var tempa = document.createElement("a");
        tempa.href = url;
        document.getElementsByTagName("body")[0].appendChild(tempa);
        tempa.click();
    } else {
        //其它浏览器直接导航
        window.location.href = url;
    }
}

function getIEVersion() {
    var rv = -1; // Return value assumes failure.  
    if (navigator.appName == "Microsoft Internet Explorer") {
        var ua = navigator.userAgent;
        var re = new RegExp("MSIE ([0-9]{1,}[/.0-9]{0,})");
        if (re.exec(ua) != null)
            rv = parseFloat(RegExp.$1);
    }
    return rv;
}

//合并列单元格
jQuery.fn.rowspan = function (colIdx) { //封装的一个JQuery小插件 
    return this.each(function () {
        var that;
        $('tr', this).each(function (row) {
            $('td:eq(' + colIdx + ')', this).filter(':visible').each(function (col) {
                if (that != null && $(this).html() == $(that).html()) {
                    rowspan = $(that).attr("rowSpan");
                    if (rowspan == undefined) {
                        $(that).attr("rowSpan", 1);
                        rowspan = $(that).attr("rowSpan");
                    }
                    rowspan = Number(rowspan) + 1;
                    $(that).attr("rowSpan", rowspan);
                    $(this).hide();
                } else {
                    that = this;
                }
            });
        });
    });
}

//跳到语音答疑,courseName没有用，是为了保留以前的参数
function ToJumpTeach(courseId, courseName, studentName, userId) {
   
    //$("#frmHiddenTeach").attr("action", ONLINETEACH_URL);
    $("#username").val(userId);
    $("#name").val(studentName);
    $("#course_id").val(courseId);
    $("#user_type").val(2);
   // document.frmHiddenTeach.acceptCharset = "gb2312";
    $("#frmHiddenTeach").submit();
}

function utcToDate(utcCurrTime) {
    utcCurrTime = utcCurrTime + "";
    var date = "";
    var month = new Array();
    month["Jan"] = 1;
    month["Feb"] = 2;
    month["Mar"] = 3;
    month["Apr"] = 4;
    month["May"] = 5;
    month["Jun"] = 6;
    month["Jul"] = 7;
    month["Aug"] = 8;
    month["Sep"] = 9;
    month["Oct"] = 10;
    month["Nov"] = 11;
    month["Dec"] = 12;
    var week = new Array();
    week["Mon"] = "一";
    week["Tue"] = "二";
    week["Wed"] = "三";
    week["Thu"] = "四";
    week["Fri"] = "五";
    week["Sat"] = "六";
    week["Sun"] = "日";


    str = utcCurrTime.split(" ");
    date = str[5] + "-";
    date = date + month[str[1]] + "-" + str[2] + "-" + str[3];
    return date;
}