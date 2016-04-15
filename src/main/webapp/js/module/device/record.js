require([ "jquery", "page", "datePicker", "crud", "intercept" ], function($, page, datePicker, crud, intercept) {
	search();
	find();

	function search() {
		crud.page(null, find);
		$("#find").on("click", function() {
			$("#page").page({
				pageNo : 1
			});
			find();
		});

		$("nav").on("change", ":radio", function() {
			$("#page").page({
				pageNo : 1
			});
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
	}

	function find() {
		var options = $("#page").page("options");
		var params = {
			pageNo : options.pageNo,
			pageSize : options.pageSize,
			begin : $.trim($("#begin").val()),
			end : $.trim($("#end").val()),
			terminalNo : $.trim($("#terminalNo").val())
		}

		var online = $("#online :radio:checked").val();
		!!online && (params.online = online);

		$.ajax({
			url : "json/Terminal_log",
			data : params,
			success : function(data) {
				$("#data").empty();

				var str = "<tr>";
				str += "<td class='terminalNo'></td>";
				str += "<td class='location'></td>";
				str += "<td class='ip'></td>";
				str += "<td class='date'></td>";
				str += "<td class='status'></td>";
				str += "</tr>";

				var online = "<span class='online'>" + $.message("online") + "</span>";
				var offline = "<span class='offline'>" + $.message("offline") + "</span>";

				$.each(data.list || [], function(index, row) {
					var tr = $(str).data("row", row);
					tr.find(".terminalNo").text(row.terminalNo || "");
					tr.find(".location").text(row.location || "");
					tr.find(".ip").text(row.ip || "");
					tr.find(".date").text((row.date || "").replace("T", " "));
					tr.find(".status").html(row.online ? online : offline);
					$("#data").append(tr);
				});

				crud.page(data, find);
			}
		});
	}
});
