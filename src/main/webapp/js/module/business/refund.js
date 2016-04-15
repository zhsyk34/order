require([ "jquery", "modal", "page", "datePicker", "crud", "intercept", "tool" ], function($, modal, page, datePicker, crud, intercept, tool) {
	search();
	find();
	update();

	function update() {
		$("#data").on("click", ".deal button", function() {
			var url = "json/Refund_update";

			var row = $(this).parents("tr").data("row");
			var id = row.id;
			var over = row.over ? 0 : 1;

			var params = {
				id : id,
				over : over
			};

			crud.merge(url, params, find);
		});
	}

	function search() {
		crud.page(null, find);
		$("#find").on("click", function() {
			find();
		});
		//
		$("#begin,#end").val("");
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
		//
		$("header").on("change", "select", function() {
			find();
		});
	}

	function find() {
		var authenticode = $.trim($("#authenticode").val());
		var orderNo = $.trim($("#orderNo").val());
		var terminalNo = $.trim($("#terminalNo").val());

		var reason = $("#reason").val();
		var type = $("#type").val();
		var over = $("#over").val();

		var begin = $("#begin").val();
		var end = $("#end").val();

		var params = {
			authenticode : authenticode,
			orderNo : orderNo,
			terminalNo : terminalNo,
			reason : reason,
			type : type,
			over : over,
			begin : begin,
			end : end
		};

		$.ajax({
			url : "json/Refund_find",
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
			str += "<td class='authenticode'></td>";
			str += "<td class='terminalNo'></td>";
			str += "<td class='orderNo'></td>";
			str += "<td class='reason'></td>";
			str += "<td class='type'></td>";
			str += "<td class='amount'></td>";
			str += "<td class='happentime'></td>";
			str += "<td class='dealtime'></td>";
			str += "<td class='deal'><button class=''></button></td>";
			str += "</tr>";

			var deal = "<button class='btn btn-small btn-primary'>" + $.message("deal") + "</button>";
			var revoke = "<button class='btn btn-small btn-danger'>" + $.message("revoke") + "</button>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);

				tr.find(".index").text(index + 1);
				tr.find(".authenticode").text(row.authenticode);
				tr.find(".terminalNo").text(row.terminalNo);
				tr.find(".orderNo").text(row.orderNo);
				tr.find(".reason").text(row.reason == "MACHINE" ? $.message("rule-machine") : $.message("rule-balance"));
				tr.find(".type").text(row.type == "LACK" ? $.message("rule-lack") : $.message("rule-error"));
				tr.find(".amount").text(row.amount);
				tr.find(".happentime").text(row.happentime.replace("T", " "));
				tr.find(".dealtime").text(row.over ? row.dealtime.replace("T", " ") : "");
				tr.find(".deal").html(row.over ? revoke : deal);

				$("#data").append(tr);
			});
		}
	}
});
