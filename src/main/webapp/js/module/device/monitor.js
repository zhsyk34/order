require([ "jquery", "page", "crud", "intercept" ], function($, page, crud, intercept) {
	search();
	find();
	setInterval(find, 10 * 1000);

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
	}

	function find() {
		var options = $("#page").page("options");
		var params = {
			pageNo : options.pageNo,
			pageSize : options.pageSize,
			terminalNo : $.trim($("#terminalNo").val())
		}

		var online = $("#online :radio:checked").val();
		!!online && (params.online = online);

		$.ajax({
			url : "json/Terminal_monitor",
			data : params,
			success : function(data) {
				load(data);
				crud.page(data, find);
			}
		});

		function load(data) {
			$("#data").empty();

			var str = "<div class='info'>";
			str += "<div class='wrap'>";
			str += "</div>";
			str += "<div class='description'>";
			str += "<div>" + $.message("monitor-status") + " <span class='online'></span></div>";
			str += "<div>" + $.message("monitor-terminal") + " <span class='terminalNo'></span></div>";
			str += "<div>" + $.message("monitor-location") + " <span class='location'><span></div>";
			str += "</div>";
			str += "</div>";

			var online = "<span class='online'>" + $.message("online") + "</span>";
			var offline = "<span class='offline'>" + $.message("offline") + "</span>";

			$.each(data.list || [], function(index, row) {
				var div = $(str);

				if (row.online) {
					div.find(".wrap").html("<img src='" + row.image + "'>");
				} else {
					div.find(".wrap").html($.message("monitor-interrupt")).addClass("empty");
				}
				div.find(".online").html(row.online ? online : offline);
				div.find(".terminalNo").text(row.terminalNo);
				div.find(".location").text(row.location);

				$("#data").append(div);
			});
		}
	}
});
