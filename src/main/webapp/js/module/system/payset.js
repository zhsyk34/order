require([ "jquery", "modal", "crud", "intercept", "message", "validate" ], function($, modal, crud, intercept, message, validate) {

	find();
	select();
	submit();

	function select() {
		$("#name").on("change", function() {
			find();
		});
	}

	function submit() {
		// reset
		$("#reset").on("click", function() {
			find();
		});

		// update
		$("#sure").on("click", function() {
			var url = "json/Config_paySet";

			var id = $("#id").val() || null;

			var name = $("#name").val();

			var title = $.trim($("#title").val());
			var appId = $.trim($("#appId").val());
			var partner = $.trim($("#partner").val());

			var publicKey = $("#publicKey").val();
			var privateKey = $("#privateKey").val();

			if (validate.isEmpty(title) || validate.isEmpty(appId) || validate.isEmpty(partner)) {
				$.alert($.message("payset-must"));
				return false;
			}
			var params = {
				id : id,
				name : name,
				title : title,
				appId : appId,
				partner : partner,
				publicKey : publicKey,
				privateKey : privateKey
			}
			if (!id) {
				if (validate.isEmpty(privateKey)) {
					$.alert($.message("payset-must"));
					return false;
				}
				switch (name) {
				case "wechat":
					break;
				case "alipay":
					if (validate.isEmpty(publicKey)) {
						$.alert($.message("payset-must"));
						return false;
					}
					break;
				}
			}

			crud.merge(url, params, find);
		});
	}

	function find() {
		var name = $("#name").val();
		$.ajax({
			url : "json/Config_payFind",
			data : {
				name : name
			},
			success : function(data) {
				$("input,textarea").val("");
				var pay = data.pay || {};

				$("#id").val(pay.id);
				$("#title").val(pay.title);
				$("#appId").val(pay.appId);
				$("#partner").val(pay.partner);

				switch (name) {
				case "wechat":
					$(".publicKey").hide();
					break;
				case "alipay":
					$(".publicKey").show();
					break;
				}
			}
		});
	}
});
