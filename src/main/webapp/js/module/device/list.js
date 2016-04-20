require([ "jquery", "modal", "page", "crud", "intercept" ], function($, modal, page, crud, intercept) {
	crud.page(null, find);
	find();
	dialog();

	function dialog() {
		$("#show").modal({
			width : 500,
			top : 120,
			buttons : null,
			title : $.message("view")
		});
		$("#data").on("click", ".view", function() {
			var row = $(this).parents("tr").data("row");
			loadDialog(row.terminalId);
			$("#show").modal("open");
		});

		function loadDialog(id) {
			var lan = (navigator.language || navigator.userLanguage).substr(-2, 2).toLowerCase();

			$.ajax({
				url : "json/Cashbox_query",
				data : {
					terminalId : id
				},
				success : function(data) {
					var min = data.min;
					var max = data.max;
					var target = data.target;

					for ( var key in target) {
						var css = target[key] > max[key] ? "above" : target[key] < min[key] ? "under" : "";
						$("#" + key).text(target[key]).addClass(css);
					}
					lan == "cn" ? $(".tw").hide() : $(".cn").hide();
				}
			});
		}
	}

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
			str += "<td><button class='btn btn-primary btn-small view'>" + $.message("view") + "</button></td>";
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
