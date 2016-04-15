require([ "jquery", "modal", "page", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, checkctrl, crud, intercept, validate) {
	dialog();
	search();
	find();
	remove();

	function remove() {
		$("#data").on("click", ".del", function() {
			var id = parseInt($(this).parents("tr").data("row").id);

			$.confirm({
				after : function() {
					$.ajax({
						url : "json/Order_delete",
						traditional : true,
						async : false,
						data : {
							id : id
						},
						success : function(data) {
							if (data.result == "revoke") {
								$.alert($.message("crud-revoke"));
							}
							$("#page").page({
								pageNo : 1
							});
							find();
						}
					});
				}
			});
			$.confirm($.message("order-delete"));
		});
	}

	function dialog() {
		$("#editor").modal({
			width : 1000,
			top : 100,
			title : $.message("order-detail"),
			before : function() {
				return merge();
			},
			reset : function() {
				loadDialog(true);
			}
		});
		$("#add").on("click", function() {
			$("thead .detail").clone().appendTo($("#details"));
		});
		$("#editor").on("click", ".cancel", function() {
			$(this).parents("tr").remove();
		});

		$("#data").on("click", ".update", function() {
			var row = $(this).parents("tr").data("row");
			$("#editor").data("row", row);
			loadDialog(true);
			$("#editor").modal("open");
		});
		$("#data").on("click", ".show", function() {
			var row = $(this).parents("tr").data("row");
			$("#editor").data("row", row);
			loadDialog(false);
			$("#editor").modal("open");
		});

		function loadDialog(modify) {
			$("#editor").modal("clear");

			if (modify) {
				$("#editor").parents(".modal").find("button").show();
				$("#editor input").prop("readonly", false);
			} else {
				$("#editor").parents(".modal").find("button").hide();
				$("#editor input").prop("readonly", true);
			}

			var details = $("#details").empty();
			var row = $("#editor").data("row");
			if (row) {
				$("#orderNo").text(row.orderNo);
				$("#shop").text(row.shop);
				$("#kitchen").text(row.kitchen);
				$("#total").val(row.total);
				$("#income").val(row.income);
				$("#expense").val(row.expense);

				var clone = $("thead .detail");
				$.each(row.detailList || [], function(index, detail) {
					var tr = clone.clone();
					tr.find(".food").val(detail.name);
					tr.find(".price").val(detail.price);
					tr.find(".count").val(detail.count);

					var tastes = detail.tasteList;
					tr.find(".taste").val(tastes.join(","));

					details.append(tr);
				});
			} else {
				$("#orderNo,#shop,#kitchen").text("");
			}
		}
	}

	// TODO not used now
	function orderDetail() {

		crud.page(null, loadFood, $("#food-page"));
		$("#find-food").on("click", function() {
			$("#food-page").page({
				pageNo : 1
			});
			loadFood();
		});

		function loadFood() {

			var tbody = $("#food-dialog tbody").empty();

			var name = $.trim($("#food-name").val());
			var options = $("#food-page").page("options");
			var query = {
				name : name,
				pageNo : options.pageNo,
				pageSize : options.pageSize
			}

			var selectedList = $(".food").data("list");

			var str = "<tr>";
			str += "<td><input type='checkbox'></td>";
			str += "<td></td>";
			str += "<td></td>";
			str += "<td></td>";
			str += "<td><img class='inline'></td>";
			str += "<td><button class='btn btn-small btn-primary'>" + $.message("view") + "</button></td>";
			str += "</tr>";

			$.ajax({
				url : "json/Food_find",
				async : false,
				data : query,
				success : function(data) {
					$.each(data.list || [], function(index, food) {
						var tr = $(str).data("row", food);
						// tr.find(":checkbox").prop("checked",
						// detectData(selectedList, "id", food));
						tr.find("td").eq(1).text(index + 1);
						tr.find("td").eq(2).text(food.name);
						tr.find("td").eq(3).text(food.price);
						tr.find("img").attr("src", food.path);
						tbody.append(tr);
					});
					crud.page(data, loadFood, $("#food-page"));
				}
			});
			check.general($("#food-parent"), $("#food-dialog").find("td :checkbox"));
		}
	}

	function merge() {
		var url = "json/Order_update";

		var row = $("#editor").data("row");

		var params = {
			id : row.id,
			orderNo : row.orderNo,
			shop : row.shop,
			kitchen : row.kitchen
		}

		var total = parseFloat($("#total").val());
		var income = parseFloat($("#income").val());
		var expense = parseFloat($("#expense").val());

		// TODO
		if (!validate.isPositive(total, true) || !validate.isPositive(income, true) || !validate.isPositive(expense)) {
			$.alert("请填写正确的金额");
			return false;
		}
		params.total = total;
		params.income = income;
		params.expense = expense;

		var foods = [], tastes = [], prices = [], counts = [];

		var trs = $("#details").find("tr");
		for (var i = 0, len = trs.length; i < len; i++) {
			var tr = trs.eq(i);
			var food = $.trim(tr.find(".food").val());
			var taste = $.trim(tr.find(".taste").val());
			var price = parseFloat(tr.find(".price").val());
			var count = parseInt(tr.find(".count").val());

			if (validate.isEmpty(food)) {
				$.alert("餐点名称不能为空");
				return false;
			}
			if (validate.isEmpty(taste)) {

			}
			if (!validate.isPositive(price, true)) {
				$.alert("请填写正确的价格");
				return false;
			}
			if (!validate.isNatural(count, true)) {
				$.alert("餐点数量必须为正整数");
				return false;
			}

			foods.push(food);
			prices.push(price);
			counts.push(count);
			tastes.push(taste);
		}

		params.foods = foods;
		params.tastes = tastes;
		params.prices = prices;
		params.counts = counts;

		$.ajax({
			url : url,
			traditional : true,
			async : false,
			data : params,
			success : function(data) {
				find();
				if (data.result == "update") {
					$.alert("修改成功");
				}
			}
		});
	}

	function search() {
		crud.page(null, find);
		$("#find").on("click", function() {
			$("#page").page({
				pageNo : 1
			});
			find();
		});
		$("header :radio").on("change", function() {
			$("#page").page({
				pageNo : 1
			});
			find();
		})
	}

	function find() {
		var orderNo = $.trim($("#search-orderNo").val());
		var shop = $.trim($("#search-shop").val());
		var kitchen = $.trim($("#search-kitchen").val());
		var options = $("#page").page("options");
		var userId = $(":radio[name='from']:checked").val();
		var status = $(":radio[name='status']:checked").val();

		var params = {
			orderNo : orderNo,
			shop : shop,
			kitchen : kitchen,
			userId : userId,
			status : status,
			pageNo : options.pageNo,
			pageSize : options.pageSize
		};

		$.ajax({
			url : "json/Order_find",
			data : params,
			success : function(data) {
				load(data);
				crud.page(data, find);
			}
		});

		function load(data) {
			$("#data").empty();

			var str = "<tr>";
			str += "<td class='index'></td>";
			str += "<td class='orderNo'></td>";
			str += "<td class='shop'></td>";
			str += "<td class='kitchen'></td>";
			str += "<td class='total'></td>";
			str += "<td class='income'></td>";
			str += "<td class='expense'></td>";
			str += "<td class='createtime'></td>";
			str += "<td class='content'><button class='btn btn-success btn-small show'>" + $.message("view") + "</button></td>";
			str += "<td class='edit'><button class='btn btn-warning btn-small update'>修改</button><button class='btn btn-danger btn-small del'>" + $.message("revoke") + "</button></td>";
			str += "</tr>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				tr.find(".index").text(index + 1);
				tr.find(".orderNo").text(row.orderNo);
				tr.find(".shop").text(row.shop);
				tr.find(".total").text(row.total);
				tr.find(".kitchen").text(row.kitchen);
				tr.find(".income").text(row.income);
				tr.find(".expense").text(row.expense);
				tr.find(".createtime").text((row.createtime || "").replace("T", " "));

				validate.equalsIgnoreCase(row.status, "nullify") && (tr.find(".del").hide());

				$("#data").append(tr);
			});
			// TODO
			$(".edit .update").hide();
		}
	}
});
