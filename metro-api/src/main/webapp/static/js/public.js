
function isSupportFileApi() {
	    if(window.File && window.FileList && window.FileReader && window.Blob) {
	        return true;
	    }
	    return false;
	}

	/**
     * 处理统一ajax loading
     */
    (function () {
        var loading_index =null;
        $.ajaxSetup({
        	timeout:20000,
        	cache:false
        	});
        $(document).ajaxStart(function () {
        	//console.info("ajaxStart");
        	loading_index=layer.open({type: 2});
        }).ajaxStop(function () {
        	layer.close(loading_index);
        });
    })();
    
    
//元素绑定事件
function bind(element, type, func) {
    if (element.addEventListener) {
        element.addEventListener(type, func, false); //false 表示冒泡
    } else if (element.attachEvent) {
        element.attachEvent('on' + type, func);
    } else {
        element['on' + type] = func;
    }
}

//获取页面总高度（总高度 = 卷去高度 + 可视区域高度）
function getPageHeight(){
    return document.documentElement.scrollHeight || document.body.scrollHeight ;
}
// 获取页面卷去的高度
function getScrollTop(){
    return document.documentElement.scrollTop || document.body.scrollTop;
}
// 获取页面可视区域高度
function getClientHeigth(){
    return document.documentElement.clientHeight || document.body.clientHeight;
}

function ajaxLoad (targetId, url){
	$("#"+targetId).load(url);
}

// 鼠标划入图片，显示赞数量
function zanShow(obj){
	$(obj).children("div .gczan").show();
}
//鼠标划出图片，隐藏赞数量
function zanHide(obj){
	$(obj).children("div .gczan").hide();
}
