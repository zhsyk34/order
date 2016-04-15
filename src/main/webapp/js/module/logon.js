require([ "jquery", "modal" ], function($, modal) {
	$("#logon").on("click", function() {
		var name = $("#name").val();
		var password = $("#password").val();

		if (!/^[A-Za-z0-9]+$/.test(name)) {
			$.alert("请填写合法的用户名");
			return false;
		}

		if (!/^[A-Za-z0-9]+$/.test(password)) {
			$.alert("请填写合法的密码");
			return false;
		}

		$.ajax({
			url : "json/User_logon",
			traditional : true,
			async : false,
			data : {
				name : name,
				password : password
			},
			success : function(data) {
				switch (data.result) {
				case "success":
					window.location.href = basePath + "jsp/index.jsp";
					break;
				case "fail":
					$.alert("用户名密码不正确,请重新登录");
					$("#password").val("");
					// window.location.reload();
					break;
				}
			}
		});
	});

	function reload() {

	}

});