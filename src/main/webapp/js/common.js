//function $(id) {
//	if(id != null && id != undefined) {
//		return document.getElementById(id);
//	}
//}
/**
 * 封装 创建 xhr 的过程
 * 返回值 ：创建好的 xhr 对象
 */
function createXhr() {
	var xhr;
	//判断浏览器是否支持XMLHttpRequest
	if(window.XMLHttpRequest) {
		xhr = new XMLHttpRequest();
	} else {
		xhr = new ActiveXObject("Microsoft.XMLHttp");
	}
	return xhr;
}
/*
 * 封装ajax请求
 * 发送json字符串，返回json字符串
 */
function $_ajax(method, url, data) {
	var xhr = createXhr();
	xhr.open(method, url, true);
	if(dataType != null && dataType != undefined && dataType != "") {
		xhr.responseType = dataType;
	}
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			return xhr.responseText;
		}
	}
	if(data != null && data != "") {
		xhr.send(null);
	} else {
		xhr.send(data);
	}	
}