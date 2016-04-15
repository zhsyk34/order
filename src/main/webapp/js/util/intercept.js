$.ajaxSetup({
	cache : false,
	traditional : true,
	type : "POST",
	complete : function(xhr, status) {
		var data = xhr.getResponseHeader("sessionstatus");
		(data == "timeout") && relogon();
	}
});

$(function() {
	(!sessionstatus || sessionstatus == "null") && relogon();
});

function relogon() {
	var top = getTopWinow();
	// confirm("您尚未登录或者登录超时,请重新登录...") ;
	top.location.href = basePath + "jsp/logon.jsp";

	function getTopWinow() {
		var w = window;
		while (w != w.parent) {
			w = w.parent;
		}
		return w;
	}
}
