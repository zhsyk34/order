require([ "jquery", "modal", "intercept", "message", "validate" ], function($, modal, intercept, message, validate) {

	$("#sure").on("click", function() {
		update();
	});
	$("#reset").on("click", function() {
		$("#original,#password,#confirm").val("");
	});

	function update() {
		var id = parseInt($("#id").val()) || null;
		var original = $("#original").val();
		var password = $("#password").val();
		var confirm = $("#confirm").val();

		if (!id) {
			$.alert($.message("password-reload"));
			return false;
		}

		if (validate.isEmpty(original)) {
			$.alert($.message("password-original"));
			return false;
		}
		if (validate.isEmpty(password)) {
			$.alert($.message("password-required"));
			return false;
		}
		if (password != confirm) {
			$.alert($.message("password-confirm"));
			return false;
		}

		$.ajax({
			url : "json/User_modify",
			async : false,
			data : {
				id : id,
				original : original,
				password : password
			},
			success : function(data) {
				if (data.result == "notexist") {
					$.alert($.message("password-wrong"));
				} else if (data.result == "update") {
					$.alert($.message("crud-update"));
				} else {
					$.alert($.message("crud-error"));
				}
			}
		});
	}
});
