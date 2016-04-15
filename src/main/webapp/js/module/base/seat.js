require([ "jquery", "modal", "page", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, checkctrl, crud, intercept, validate) {
	checkctrl.table($(".main table"));
	dialog();
	search();
	find();
	remove();

	function dialog() {
		$("#editor").modal({
			width : 400,
			top : 100,
			before : function() {
				return merge();
			},
			reset : function() {
				loadDialog();
			}
		});

		$("#batch").on("click", function() {
			var batch = !$(this).data("batch");
			$(this).data("batch", batch);
			batch ? $(".begin,.end").show() : $(".begin,.end").hide();
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
			$("#batch").data("batch", false);
			$(".begin,.end").hide();

			var row = $("#editor").data("row");
			if (row) {
				$("#id").val(row.id);
				$("#name").val(row.name);
				$("#batch").hide();
			} else {
				$("#editor").modal("clear");
				$("#batch").show();
			}
		}
	}

	function merge() {
		var id = parseInt($("#id").val()) || null;
		var name = $.trim($("#name").val());
		var batch = $("#batch").data("batch");
		var url = id ? "json/Seat_update" : "json/Seat_save";

		if (validate.isEmpty(name)) {
			$.alert($.message("name-required"));
			return false;
		}

		var params = {
			id : id,
			name : name,
			batch : batch
		};

		if (batch) {
			var begin = parseInt($("#begin").val());
			var end = parseInt($("#end").val());
			if (!validate.isNatural(begin) || !validate.isNatural(end)) {
				$.alert($.message("seat-number"));
				return false;
			}
			params.begin = begin;
			params.end = end;
		}

		var exist = true;
		$.ajax({
			url : "json/Seat_exist",
			async : false,
			data : params,
			success : function(data) {
				exist = data.exist;
			}
		});
		if (exist) {
			$.alert($.message("name-exist"));
			return false;
		}

		crud.merge(url, params, find);
	}

	function remove() {
		var url = "json/Seat_delete";
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

	function search() {
		crud.page(null, find);
		$("#find").on("click", function() {
			$("#page").page({
				pageNo : 1
			});
			find();
		});
	}

	function find() {
		var name = $.trim($("#seat-name").val());
		var options = $("#page").page("options");
		$.ajax({
			url : "json/Seat_find",
			data : {
				name : name,
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
			str += "<td class='checkbox'><input type='checkbox'></td>";
			str += "<td class='index'></td>";
			str += "<td class='name'></td>";
			str += "<td><button class='btn btn-warning btn-small update'>" + $.message("mod") + "</button><button class='btn btn-danger btn-small del'>" + $.message("del") + "</button></td>";
			str += "</tr>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				tr.find(".checkbox :checkbox").val(row.id);
				tr.find(".index").text(index + 1);
				tr.find(".name").text(row.name);

				$("#data").append(tr);
			});
		}
	}
});
