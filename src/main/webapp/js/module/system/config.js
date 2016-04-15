require([ "jquery", "modal", "crud", "intercept", "message", "validate" ], function($, modal, crud, intercept, message, validate) {

	find();
	view();
	submit();

	function viewCtrl() {
		var takeShow = $(":radio[name='takeShow']:checked").val() > 0;
		takeShow ? $("#takeAway").show() : $("#takeAway").hide();

		var accessory = $(":radio[name='accessory']:checked").val() > 0;
		accessory ? $(".accessory").show() : $(".accessory").hide();
	}

	function view() {
		$(":radio[name='takeShow'],:radio[name='accessory']").on("click", function() {
			viewCtrl();
		});
	}

	function submit() {
		// reset
		$("#reset").on("click", function() {
			find();
		});

		// update
		$("#sure").on("click", function() {
			var url = "json/Config_update", params = {};

			var radios = [ "takeShow", "seat", "foodType", "accessory" ];
			var checkboxs = [ "shopOrder", "kitchenOrder", "cash", "creditcard", "wechat", "alipay", "member", "metrocard", "easycard" ];

			$.each(radios, function(index, key) {
				var value = $(":radio[name='" + key + "']:checked").val() > 0;
				params[key] = value;
			});

			$.each(checkboxs, function(index, key) {
				params[key] = $("#" + key).prop("checked");
			});

			params.takeAway = $("#takeAway").val() > 0;

			var accessory = $(":radio[name='accessory']:checked").val() > 0;
			if (accessory) {
				var name = $.trim($("#accessoryName").val());
				var percent = parseInt($("#accessoryPercent").val());

				if (validate.isEmpty(name)) {
					$.alert($.message("config-accname"));
					return false;
				}
				if (!validate.isNatural(percent)) {
					$.alert($.message("config-accpercent"));
					return false;
				}
				params.accessoryName = name;
				params.accessoryPercent = percent;
			}

			crud.merge(url, params, find);
		});
	}

	function find() {
		$.ajax({
			url : "json/Config_find",
			success : function(data) {
				var config = data.config;

				var radios = [ "takeShow", "seat", "foodType", "accessory" ];
				var checkboxs = [ "shopOrder", "kitchenOrder", "cash", "creditcard", "wechat", "alipay", "member", "metrocard", "easycard" ];

				$.each(radios, function(index, key) {
					var value = config[key] ? 1 : 0;
					$(":radio[name='" + key + "'][value='" + value + "']").prop("checked", true);
				});

				$("#takeAway").val(config["takeAway"] ? 1 : 0);
				$("#accessoryName").val(config["accessoryName"]);
				$("#accessoryPercent").val(config["accessoryPercent"]);

				$.each(checkboxs, function(index, key) {
					$("#" + key).prop("checked", config[key]);
				});

				viewCtrl();
			}
		});
	}
});
