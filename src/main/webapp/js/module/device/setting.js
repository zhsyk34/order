require([ "jquery", "modal", "page", "datePicker", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, datePicker, checkctrl, crud, intercept, validate) {
	crud.page(null, find);
	dialog();
	find();
	seatDialog();
	findSeat();

	function findSeat() {
		var ul = $("#seat ul").empty();
		var str = "<li class='inline'><label><input type='checkbox'><span></span></lable></li>";
		$.ajax({
			url : "json/Seat_find",
			data : {
				pageNo : -1,
				pageSize : -1
			},
			success : function(data) {
				$.each(data.list || [], function(index, seat) {
					var li = $(str);
					li.find(":checkbox").val(seat.id);
					li.find("span").text(seat.name);
					$("#seat ul").append(li);
				});
			}
		});
	}

	function seatDialog() {
		$("#seat").modal({
			width : 800,
			top : 100,
			title : $.message("setting-seat"),
			before : function() {
				return updateSeat();
			},
			reset : function() {
				loadSeat();
			}
		});

		$("#data").on("click", ".seat", function() {
			var row = $(this).parents("tr").data("row");
			$("#seat").data("row", row);
			loadSeat();
			$("#seat").modal("open");
		});

		function loadSeat() {
			var row = $("#seat").data("row");

			$("#seat-terminal").text(row.terminalNo);
			var seats = row.seats || [];

			$("#seat ul :checkbox").each(function() {
				var id = parseInt($(this).val());
				$(this).prop("checked", $.inArray(id, seats) > -1);
			});

			checkctrl.general($("#seat-parent"), $("#seat ul :checkbox"));
		}

		function updateSeat() {
			var url = "json/Terminal_seat";
			var id = $("#seat").data("row").id;
			var seatIds = [];
			$("#seat ul :checkbox:checked").each(function() {
				seatIds.push(parseInt($(this).val()));
			});

			var params = {
				id : id,
				seatIds : seatIds
			};
			crud.merge(url, params, find);
		}
	}

	function dialog() {
		$("#editor").modal({
			width : 600,
			top : 100,
			before : function() {
				return merge();
			},
			reset : function() {
				loadDialog();
			}
		});

		$("table").on("click", ".update", function() {
			$("#editor").modal("title", $.message("mod"));
			var row = $(this).parents("tr").data("row");
			$("#editor").data("row", row);
			loadDialog();
			$("#editor").modal("open");
		});

		function loadDialog() {
			$("#editor").modal("clear");

			var row = $("#editor").data("row");

			$("#id").val(row.id);
			$("#terminalNo").text(row.terminalNo);
			$("#teamViewer").val(row.teamViewer);

			var invoice = row.invoice ? 1 : 0;
			$("#invoice :radio[value=" + invoice + "]").prop("checked", true);

			var shut = row.shut ? 1 : 0;
			$("#shut :radio[value=" + shut + "]").prop("checked", true);

			var boots = $(".times .boots");
			var shuts = $(".times .shuts");
			$.each(row.bootList || [], function(index, time) {
				boots.eq(index).val(time);
			});
			$.each(row.shutList || [], function(index, time) {
				shuts.eq(index).val(time);
			});
		}

		$(".boots,.shuts").on("click", function() {
			WdatePicker({
				dateFmt : "HH:mm:ss"
			});
		});
	}

	function merge() {
		var id = parseInt($("#id").val());
		var teamViewer = $.trim($("#teamViewer").val());
		var invoice = $("#invoice :radio:checked").val() > 0;
		var shut = $("#shut :radio:checked").val() > 0;

		var boots = [], shuts = [];
		$(".times .boots").each(function() {
			var time = $.trim($(this).val());
			if (!validate.isEmpty(time)) {
				boots.push(time);
			}
		});
		$(".times .shuts").each(function() {
			var time = $.trim($(this).val());
			if (!validate.isEmpty(time)) {
				shuts.push(time);
			}
		});

		var url = "json/Terminal_config";

		var params = {
			id : id,
			teamViewer : teamViewer,
			invoice : invoice,
			shut : shut,
			boots : boots,
			shuts : shuts

		};

		crud.merge(url, params, find);
	}

	function find() {
		var options = $("#page").page("options");
		$.ajax({
			url : "json/Terminal_query",
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
			str += "<td class='invoice'></td>";
			str += "<td class='teamViewer'></td>";
			str += "<td><div class='boottime'></div></td>";
			str += "<td><div class='shuttime'></div></td>";
			str += "<td class='shut'></td>";
			str += "<td><button class='btn btn-primary btn-small update'>" + $.message("mod") + "</button></td>";
			str += "<td><button class='btn btn-primary btn-small seat'>" + $.message("install") + "</button></td>";
			str += "</tr>";

			// var shutable = "<button class='btn btn-small btn-success'>" +
			// $.message("enable") + "</button>";
			// var unshutable = "<button class='btn btn-small btn-warning'>" +
			// $.message("disable") + "</button>";

			var invoice = "<span class='enable'>" + $.message("yes") + "</span>";
			var uninvoice = "<span class='disable'>" + $.message("no") + "</span>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				tr.find(".terminalNo").text(row.terminalNo);
				tr.find(".teamViewer").text(row.teamViewer || "");

				var boottime = "";
				$.each(row.bootList || [], function(i, t) {
					boottime += "<div>" + t.replace("T", " ") + "</div>";
				});
				tr.find(".boottime").html(boottime);

				var shuttime = "";
				$.each(row.shutList || [], function(i, t) {
					shuttime += "<div>" + t.replace("T", " ") + "</div>";
				});
				tr.find(".shuttime").html(shuttime);

				tr.find(".invoice").html(row.invoice ? invoice : uninvoice);
				tr.find(".shut").html(row.shut ? invoice : uninvoice);

				$("#data").append(tr);
			});
		}
	}
});
