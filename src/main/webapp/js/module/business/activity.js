require([ "jquery", "modal", "page", "datePicker", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, datePicker, checkctrl, crud, intercept, validate) {
	var href = window.location.href;
	var type = href.substring(href.lastIndexOf("/") + 1, href.lastIndexOf(".jsp"));
	checkctrl.table($(".main table"));
	dialog();
	search();
	find();
	used();
	revoke();

	function update() {
		var url = "json/Activity_update";

		var foodIds = select().foodIds;
		var kitchenIds = select().kitchenIds;

		var begin = $.trim($("#begin").val());
		var end = $.trim($("#end").val());
		var count = parseInt($.trim($("#count").val()));
		var used = $("#used :radio:checked").val();

		var unit = parseInt($.trim($("#unit").val()));
		var percent = parseInt($.trim($("#percent").val()));
		var discount = parseFloat($.trim($("#discount").val()));

		var params = {
			foodIds : foodIds,
			kitchenIds : kitchenIds,
			type : type,
			begin : begin,
			end : end,
			count : count,
			used : used
		};

		switch (type) {
		case "stop":
			var pattern = $("#pattern :radio:checked").val();
			switch (pattern) {
			case "count":
				if (!validate.isNatural(count)) {
					$.alert($.message("activity-stopcount"));
					return false;
				}
				params.begin = null;
				params.end = null;
				break;
			case "date":
				if (validate.isEmpty(begin)) {
					$.alert($.message("activity-begin"));
					return false;
				}
				if (validate.isEmpty(end)) {
					$.alert($.message("activity-end"));
					return false;
				}
				params.count = 0;
				break;
			}
			break;
		case "gift":
			if (validate.isEmpty(begin)) {
				$.alert($.message("activity-begin"));
				return false;
			}
			if (validate.isEmpty(end)) {
				$.alert($.message("activity-end"));
				return false;
			}
			if (!validate.isNatural(count)) {
				$.alert($.message("activity-giftcount"));
				return false;
			}
			if (!validate.isNatural(unit)) {
				$.alert($.message("activity-unit"));
				return false;
			}
			params.unit = unit;
			break;
		case "discount":
			if (validate.isEmpty(begin)) {
				$.alert($.message("activity-begin"));
				return false;
			}
			if (validate.isEmpty(end)) {
				$.alert($.message("activity-end"));
				return false;
			}
			if (!validate.isNatural(count)) {
				$.alert($.message("activity-discountcount"));
				return false;
			}
			var pattern = $("#pattern :radio:checked").val();
			switch (pattern) {
			case "discount":
				if (!discount) {
					$.alert($.message("activity-price"));
					return false;
				}
				params.discount = discount;
				break;
			case "percent":
				if (!percent || percent < 1 || percent > 99) {
					$.alert($.message("activity-percent"));
					return false;
				}
				params.percent = percent;
				break;
			}
			break;
		}
		crud.merge(url, params, find);
	}

	function dialog() {
		$("#editor").modal({
			width : 400,
			top : 100,
			title : $.message("install"),
			before : function() {
				return update();
			},
			reset : function() {
				loadDialog();
			}
		});
		// 单选事件
		$("#pattern").on("change", ":radio", function() {
			showByRadio();
		});

		// 日期控件
		$("#begin").on("click", function() {
			WdatePicker({
				maxDate : "#F{$dp.$D('end')}",
				dateFmt : "yyyy-MM-dd HH:mm:ss"
			});
		});
		$("#end").on("click", function() {
			WdatePicker({
				minDate : "#F{$dp.$D('begin')}",
				dateFmt : "yyyy-MM-dd HH:mm:ss"
			});
		});

		// 编辑事件
		$("#data").on("click", ".update", function() {
			$("#data").find(":checkbox").prop("checked", false);
			$(this).parents("tr").find(":checkbox").prop("checked", true);
			var row = $(this).parents("tr").data("row");
			$("#editor").data("row", row);
			loadDialog();
			$("#editor").modal("open");
		});
		$("#update-all").on("click", function() {
			if (select().foodIds.length == 0) {
				$.alert($.message("activity-datas"));
				return false;
			}
			$("#editor").removeData("row");
			loadDialog();
			$("#editor").modal("open");
		});

		// 加载窗口
		function loadDialog() {
			var row = $("#editor").data("row");
			$("#editor").modal("clear");
			if (row) {
				$("#begin").val(row.begin ? row.begin.replace("T", " ") : "");
				$("#end").val(row.end ? row.end.replace("T", " ") : "");
				$("#count").val(row.count || "");

				var value = row.used ? 1 : 0;
				$("#used :radio[value='" + value + "']").prop("checked", true);

				switch (type) {
				case "stop":
					$("#pattern :radio[value='count']").prop("checked", !!row.count);
					break;
				case "gift":
					$("#unit").val(row.unit);
					break;
				case "discount":
					$("#pattern :radio[value='percent']").prop("checked", !!row.percent);
					$("#percent").val(row.percent || "");
					$("#discount").val(row.discount);
					break;
				}
			}
			showByRadio();
		}

		// 单选框判断
		function showByRadio() {
			var pattern = $("#pattern :radio:checked").val();
			switch (pattern) {
			case "count":
				$(".pattern-count").show();
				$(".pattern-date").hide();
				break;
			case "date":
				$(".pattern-count").hide();
				$(".pattern-date").show();
				break;
			case "discount":
				$(".pattern-percent").hide();
				$(".pattern-discount").show();
				break;
			case "percent":
				$(".pattern-percent").show();
				$(".pattern-discount").hide();
				break;
			}
		}
	}

	function revoke() {
		var url = "json/Activity_delete";
		$("#data").on("click", ".revoke", function() {
			var id = parseInt($(this).parents("tr").data("row").id);
			crud.del(url, id, find);
		});
		$("#revoke-all").on("click", function() {
			var ids = select().ids;
			crud.del(url, ids, find);
		});
	}

	function used() {
		var url = "json/Activity_used";

		var params = {
			type : type,
			used : 0
		};

		$("#data").on("click", ".disable", function() {
			var row = $(this).parents("tr").data("row");
			params.used = 0;
			params.foodIds = [ parseInt(row.foodId) ];
			params.kitchenIds = [ parseInt(row.kitchenId) ];
			crud.merge(url, params, find);
		});
		$("#data").on("click", ".enable", function() {
			var row = $(this).parents("tr").data("row");
			params.used = 1;
			params.foodIds = [ parseInt(row.foodId) ];
			params.kitchenIds = [ parseInt(row.kitchenId) ];
			crud.merge(url, params, find);
		});

		$("#unused-all").on("click", function() {
			var result = select();
			params.foodIds = result.foodIds;
			params.kitchenIds = result.kitchenIds;
			if (params.foodIds.length == 0) {
				$.alert($.message("activity-datas"));
				return false;
			}
			params.used = 0;
			crud.merge(url, params, find);
		});
		$("#used-all").on("click", function() {
			var result = select();
			params.foodIds = result.foodIds;
			params.kitchenIds = result.kitchenIds;
			if (params.foodIds.length == 0) {
				$.alert($.message("activity-datas"));
				return false;
			}
			params.used = 1;
			crud.merge(url, params, find);
		});
	}

	// util
	function select() {
		var result = {
			ids : [],
			kitchenIds : [],
			foodIds : []
		};
		$("#data :checkbox:checked").each(function() {
			var row = $(this).parents("tr").data("row");
			var id = parseInt(row.id);
			var kitchenId = parseInt(row.kitchenId);
			var foodId = parseInt(row.foodId);
			id && result.ids.push(id);
			kitchenId && result.kitchenIds.push(kitchenId);
			foodId && result.foodIds.push(foodId);
		});
		return result;
	}

	function search() {
		crud.page(null, find);
		$("#find").on("click", function() {
			$("#page").page({
				pageNo : 1
			});
			find();
		});
		$("#search-used").on("change", ":radio", function() {
			$("#page").page({
				pageNo : 1
			});
			find();
		});
	}

	function find() {
		var kitchen = $.trim($("#search-kitchen").val());
		var food = $.trim($("#search-food").val());
		var used = $("#search-used :radio:checked").val();
		var options = $("#page").page("options");
		$.ajax({
			url : "json/Activity_find",
			data : {
				kitchen : kitchen,
				food : food,
				used : used,
				type : type,
				pageNo : options.pageNo,
				pageSize : options.pageSize
			},
			success : function(data) {
				load(data);
				crud.page(data, find);
			}
		});

		function load(data) {
			$("#data").empty();
			$("#check-parent").prop("checked", false);

			var str = "<tr>";
			str += "<td><input type='checkbox'></td>";
			str += "<td class='index'></td>";
			str += "<td class='kitchenNo'></td>";
			str += "<td class='location'></td>";
			str += "<td class='foodName'></td>";
			str += "<td><div class='begin'></div><div class='end'></div></td>";
			str += "<td class='count'></td>";
			switch (type) {
			case "stop":
				break;
			case "gift":
				str += "<td class='unit'></td>";
				break;
			case "discount":
				str += "<td class='price'></td>";
				str += "<td class='discount'></td>";
				break;
			}
			str += "<td class='used'></td>";
			str += "<td><button class='btn btn-primary btn-small update'>" + $.message("install") + "</button></td>";
			str += "<td class='delete'></td>";
			str += "</tr>";

			var enable = "<button class='btn btn-success btn-small enable'>" + $.message("enable") + "</button>";
			var disable = "<button class='btn btn-warning btn-small disable'>" + $.message("disable") + "</button>";
			var revoke = "<button class='btn btn-danger btn-small revoke'>" + $.message("revoke") + "</button>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				// tr.find(":checkbox").val(row.id);
				tr.find(".index").text(index + 1);
				tr.find(".kitchenNo").text(row.kitchenNo);
				tr.find(".location").text(row.location);
				tr.find(".foodName").text(row.foodName);
				tr.find(".begin").text(row.begin ? $.message("from") + row.begin.replace("T", " ") : "");
				tr.find(".end").text(row.end ? $.message("to") + row.end.replace("T", " ") : "");
				tr.find(".count").text(row.count || "");

				tr.find(".delete").html(row.type == type.toUpperCase() ? revoke : "");

				switch (type) {
				case "stop":
					tr.find(".used").html(row.used ? disable : enable);
					break;
				case "gift":
					tr.find(".unit").text(row.unit || "");
					tr.find(".used").html(row.used ? disable : enable);
					break;
				case "discount":
					tr.find(".price").text(row.price);
					tr.find(".discount").text(row.discount || "");
					tr.find(".used").html(row.used ? disable : (row.id ? enable : ""));// 促销不能立即启用
					break;
				}

				$("#data").append(tr);
			});
		}
	}
});
