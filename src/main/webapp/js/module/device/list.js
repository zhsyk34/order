require([ "jquery", "page", "crud", "intercept" ], function($, page, crud, intercept) {
	crud.page(null, find);
	find();
	function find() {
		var options = $("#page").page("options");
		$.ajax({
			url : "json/Terminal_monitor",
			data : {
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

			var str = "<tr>";
			str += "<td class='terminalNo'></td>";
			str += "<td class='location'></td>";
			str += "<td class='date'></td>";
			str += "<td class='status'></td>";
			// str += "<td><button class='btn btn-primary
			// btn-small'>查看</button></td>";
			str += "</tr>";

			var online = "<span class='online'>" + $.message("online") + "</span>";
			var offline = "<span class='offline'>" + $.message("offline") + "</span>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				tr.find(".terminalNo").text(row.terminalNo);
				tr.find(".location").text(row.location);
				tr.find(".date").text((row.date || "").replace("T", " "));
				tr.find(".status").html(row.online ? online : offline);
				$("#data").append(tr);
			});
		}
	}
});
