require([ "jquery", "modal", "page", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, checkctrl, crud, intercept, validate) {
	checkctrl.table($(".main table"));
	crud.page(null, find);
	dialog();
	find();
	remove();
	used();

	function dialog() {
		$("#editor").modal({
			width : 400,
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
				$("#prefix").val(row.prefix);
				$("#length").val(row.length);
				$("#start").val(row.start);
			} else {
				$("#editor").modal("clear");
			}
		}
	}

	function merge() {
		var id = parseInt($("#id").val()) || null;
		var prefix = $.trim($("#prefix").val());
		var length = parseInt($("#length").val());
		var start = parseInt($("#start").val());
		var url = id ? "json/OrderRule_update" : "json/OrderRule_save";

		if (validate.isEmpty(prefix)) {
			$.alert($.message("rule-prefix"));
			return false;
		}

		if (!validate.isNatural(length)) {
			$.alert($.message("rule-length"));
			return false;
		}
		if (!validate.isNatural(start)) {
			$.alert($.message("rule-start"));
			return false;
		}

		var params = {
			id : id,
			prefix : prefix,
			length : length,
			start : start
		};

		crud.merge(url, params, find);
	}

	function used() {
		$("#data").on("click", ".switch", function() {
			var url = "json/OrderRule_used";
			var row = $(this).parents("tr").data("row");
			var id = row.id;
			var used = !row.used;

			var params = {
				id : id,
				used : used
			};
			crud.merge(url, params, find);
		});
	}

	function remove() {
		var url = "json/OrderRule_delete";
		$("#data").on("click", ".del", function() {
			var id = parseInt($(this).parents("tr").find(":checkbox").val());
			crud.del(url, id, find);
		});
		$("#del-all").on("click", function() {
			var ids = [];
			$('#data :checkbox:checked').each(function() {
				var id = parseInt($(this).val());
				ids.push(id);
			});
			crud.del(url, ids, find);
		});
	}

	function find() {
		var options = $("#page").page("options");
		$.ajax({
			url : "json/OrderRule_find",
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
			$("#check-parent").prop("checked", false);

			var str = "<tr>";
			str += "<td><input type='checkbox'></td>";
			str += "<td class='index'></td>";
			str += "<td><span>" + $.message("rule-prestr") + "<span><span class='prefix'></span><span>" + $.message("rule-lenstr") + "</span><span class='length'></span><span>" + $.message("rule-stastr") + "</span><span class='start'></span></td>";
			str += "<td class='used'></td>";
			str += "<td><button class='switch btn btn-small'></button><button class='btn btn-warning btn-small update'>" + $.message("mod") + "</button><button class='btn btn-danger btn-small del'>" + $.message("del") + "</button></td>";
			str += "</tr>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				tr.find(":checkbox").val(row.id);
				tr.find(".index").text(index + 1);
				tr.find(".prefix").text(row.prefix);
				tr.find(".length").text(row.length);
				tr.find(".start").text(row.start);
				tr.find(".used").text(row.used ? $.message("enable") : $.message("disable"));
				tr.find(".switch").addClass(row.used ? "btn-info" : "btn-success").text(row.used ? $.message("disable") : $.message("enable"));

				$("#data").append(tr);
			});
		}
	}
});
