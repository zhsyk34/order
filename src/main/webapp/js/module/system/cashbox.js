require([ "jquery", "modal", "crud", "intercept", "message", "validate" ], function($, modal, crud, intercept, message, validate) {

	find();
	submit();

	function submit() {
		// reset
		$("#reset").on("click", function() {
			find();
		});

		// update
		$("#sure").on("click", function() {
			// 数值验证
			function numeric() {
				var flag = true;
				$(".main tbody input:not([readonly])").each(function() {
					var value = parseInt($(this).val());
					if (!value) {
						$.alert($.message("cashbox-count"));
						flag = false;
						return false;
					}
				});
				return flag;
			}

			// 两端
			function ends() {
				var flag = true;
				$(".main tbody tr").each(function() {
					var min = parseInt($(this).find("input:first").val());
					var max = parseInt($(this).find("input:last").val());
					if (min > max) {
						$.alert($.message("cashbox-minmax"));
						flag = false;
						return false;
					}
				});
				return flag;
			}

			// 总数量边界验证
			function limit() {
				function count(clazz, max) {
					var total = 0;
					$(clazz).each(function() {
						total += parseInt($(this).find("input:last").val());
					});
					if (total > max) {
						$.alert($.message("cashbox-total"));
					}
					return total <= max;
				}
				return count(".nv9.tw", 600) && count(".nv9.cn", 600) && count(".hopper.tw", 1000) && count(".hopper.cn", 1000);

			}

			function update() {
				var url = "json/Cashbox_update";
				var params = {};
				$(".main input:not([readonly])").each(function() {
					var key = $(this).attr("id");
					var value = parseInt($(this).val());
					params[key] = value;
				});

				crud.merge(url, params, find);
			}

			numeric() && ends() && limit() && update();
		});
	}

	function find() {
		var lan = (navigator.language || navigator.userLanguage).substr(-2, 2).toLowerCase();

		$.ajax({
			url : "json/Cashbox_find",
			success : function(data) {
				var min = data.min;
				var max = data.max;

				for ( var key in min) {
					$("#" + key + "Min").val(min[key]);
				}
				for ( var key in max) {
					$("#" + key + "Max").val(max[key]);
				}

				lan == "cn" ? $(".tw").hide() : $(".cn").hide();
			}
		});
	}
});
