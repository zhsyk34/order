require([ "jquery", "modal", "page", "crud", "intercept", "validate" ], function($, modal, page, crud, intercept, validate) {

	dialog();
	search();
	find();
	remove();

	function dialog() {
		$("#editor").modal({
			width : 360,
			top : 100,
			before : function() {
				return merge();
			},
			reset : function() {
				loadDialog();
			}
		});
		$("#add").on("click", function() {
			$("#editor").modal("title", $.message("add"));
			$("#editor").removeData("row");
			loadDialog();
			$("#editor").modal("open");
		});
		$("table").on("click", ".update", function() {
			$("#editor").modal("title", $.message("mod"));
			var row = $(this).parents("tr").data("row");
			$("#editor").data("row", row);
			loadDialog();
			$("#editor").modal("open");
		});

		function loadDialog() {
			var row = $("#editor").data("row");
			if (row) {
				$("#id").val(row.id);
				$("#terminalNo").val(row.terminalNo);
				$("#type :radio[value='" + row.type.toLowerCase() + "']").prop("checked", true);
				$("#location").val(row.location);
			} else {
				$("#editor").modal("clear");
			}
		}
	}

	function merge() {
		var id = parseInt($("#id").val()) || null;
		var terminalNo = $.trim($("#terminalNo").val());
		var type = $("#type :radio:checked").val();
		var location = $.trim($("#location").val());

		var url = id ? "json/Terminal_update" : "json/Terminal_save";

		if (validate.isEmpty(terminalNo)) {
			$.alert($.message("terminal-required"));
			return false;
		}
		if (validate.isEmpty(location)) {
			$.alert($.message("terminal-location"));
			return false;
		}

		var params = {
			id : id,
			terminalNo : terminalNo,
			type : type,
			location : location
		};

		var exist = true;
		$.ajax({
			url : "json/Terminal_exist",
			async : false,
			data : params,
			success : function(data) {
				exist = data.exist;
			}
		});
		if (exist) {
			$.alert($.message("terminal-exist"));
			return false;
		}

		crud.merge(url, params, find);
	}

	function remove() {
		var url = "json/Terminal_delete";
		$("#data").on("click", ".del", function() {
			var id = parseInt($(this).parents("tr").data("row").id);
			crud.del(url, id, find);
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
		$("header").on("change", ":radio", function() {
			$("#page").page({
				pageNo : 1
			});
			find();
		});
	}

	function find() {
		var terminalNo = $.trim($("#search-terminalNo").val());
		var type = $("#search-type :radio:checked").val();
		var options = $("#page").page("options");

		$.ajax({
			url : "json/Terminal_find",
			data : {
				terminalNo : terminalNo,
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

			var version = data.version.replace("v", "");

			var str = "<tr>";
			str += "<td class='terminalNo'></td>";
			str += "<td class='type'></td>";
			str += "<td class='location'></td>";
			str += "<td class='version'></td>";
			// str += "<td class='process'><button class='btn btn-warning
			// btn-small update'>修改</button><button class='btn btn-danger
			// btn-small del'>删除</button></td>";
			str += "</tr>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				tr.find(".terminalNo").text(row.terminalNo);
				tr.find(".type").text(row.type == "SHOP" ? $.message("terminal-shop") : $.message("terminal-kitchen"));
				tr.find(".location").text(row.location);

				var color = version == row.version ? "green" : "red";
				tr.find(".version").text(row.version || "").css("color", color);

				$("#data").append(tr);
			});
			// $(".process").hide();// TODO
		}
	}
});
