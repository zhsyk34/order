require([ "jquery", "modal", "page", "datePicker", "crud", "intercept", "tool" ], function($, modal, page, datePicker, crud, intercept, tool) {
	search();
	find();

	function search() {
		$("#find").on("click", function() {
			find();
		});
		$("#begin").on("click", function() {
			WdatePicker({
				maxDate : "#F{$dp.$D('end')}"
			});
		});
		$("#end").on("click", function() {
			WdatePicker({
				maxDate : "%y-%M-%d"
			});
		});

		var pattern = "yyyy-MM-dd";

		// default:today
		$("#begin,#end").val(new Date().format(pattern));
		$("#today").on("click", function() {
			$("#begin,#end").val(new Date().format(pattern));
			find();
		});
		$("#yesterday").on("click", function() {
			var yestoday = new Date().spacing(-1, "day").format(pattern);
			$("#begin,#end").val(yestoday);
			find();
		});
		//
		$("#preweek").on("click", function() {
			var date = new Date();
			var count = (date.getDay() + 6) % 7;
			var begin = date.spacing(-count - 7, "day").format(pattern);
			var end = date.spacing(-count - 1, "day").format(pattern);
			$("#begin").val(begin);
			$("#end").val(end);
			find();
		});
		$("#week").on("click", function() {
			var date = new Date();
			var count = (date.getDay() + 6) % 7;
			var begin = date.spacing(-count, "day").format(pattern);
			$("#begin").val(begin);
			$("#end").val(date.spacing(6 - date.getDate(), "day").format(pattern));
			find();
		});
		//
		$("#premonth").on("click", function() {
			var current = new Date();
			var date = new Date(current.getFullYear(), current.getMonth() - 1, 1);

			var last = date.getMonthDate();
			var begin = date.format(pattern);
			var end = date.spacing(last - 1, "day").format(pattern);
			$("#begin").val(begin);
			$("#end").val(end);
			find();
		});
		$("#month").on("click", function() {
			var date = new Date();
			var index = date.getDate();
			var last = date.getMonthDate();
			var begin = date.spacing(1 - index, "day").format(pattern);
			var end = date.spacing(last - index, "day").format(pattern);
			$("#begin").val(begin);
			$("#end").val(end);
			find();
		});
	}

	function find() {
		var shop = $.trim($("#shop").val());
		var kitchen = $.trim($("#kitchen").val());
		var begin = $("#begin").val();
		var end = $("#end").val();

		var params = {
			shop : shop,
			kitchen : kitchen,
			begin : begin,
			end : end
		};

		$.ajax({
			url : "json/Order_statistic",
			data : params,
			success : function(data) {
				load(data);
				crud.page(data, find);
			}
		});

		function load(data) {
			$("#data").empty();

			var statistic = "<tr>";
			statistic += "<td class='all' colspan='2'></td>";
			statistic += "<td class='index'></td>";
			statistic += "<td class='food'></td>";
			statistic += "<td class='count'></td>";
			statistic += "<td class='total'></td>";
			statistic += "<td class='money'></td>";
			// statistic += "<td class='view'><button class='btn btn-small
			// btn-success'>查看</button></td>";
			statistic += "</tr>";

			var first = "<tr>";
			first += "<td class='shop'></td>";
			first += "<td class='kitchen'></td>";
			first += "<td class='index'></td>";
			first += "<td class='food'></td>";
			first += "<td class='count'></td>";
			first += "<td class='total'></td>";
			first += "<td class='money'></td>";
			// first += "<td class='view'><button class='btn btn-small
			// btn-success'>查看</button></td>";
			first += "</tr>";

			var normal = "<tr>";
			normal += "<td class='index'></td>";
			normal += "<td class='food'></td>";
			normal += "<td class='count'></td>";
			normal += "<td class='total'></td>";
			normal += "</tr>";

			var all = data.list;
			if (all) {
				var details = all.detailList;
				$.each(details, function(index, row) {
					var tr;
					if (index == 0) {
						tr = $(statistic);
						tr.find(".all,.money,.view").attr("rowspan", details.length);
						tr.find(".all").text($.message("sell-amount"));
						tr.find(".money").text(all.total);
					} else {
						tr = $(normal);
					}
					tr.find(".index").text(index + 1);
					tr.find(".food").text(row.name);
					tr.find(".count").text(row.count);
					tr.find(".total").text(row.total);
					$("#data").append(tr);
				});
			}

			$.each(data.detail || [], function(count, order) {
				var details = order.detailList || [];
				$.each(details, function(index, row) {
					var tr;
					if (index == 0) {
						tr = $(first);
						tr.find(".shop,.kitchen,.money,.view").attr("rowspan", details.length);
						tr.find(".shop").text(order.shop);
						tr.find(".kitchen").text(order.kitchen);
						tr.find(".money").text(order.total);
					} else {
						tr = $(normal);
					}
					tr.find(".index").text(index + 1);
					tr.find(".food").text(row.name);
					tr.find(".count").text(row.count);
					tr.find(".total").text(row.total);
					$("#data").append(tr);
				});
			});
		}
	}
});
