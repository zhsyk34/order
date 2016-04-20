require([ "jquery", "modal", "page", "progress", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, progress, checkctrl, crud, intercept, validate) {
	checkctrl.table($(".main table"));
	crud.page(null, find);
	template();
	find();
	remote();
	var timer = null;// TODO

	function remote() {
		var url = "json/Terminal_remote";
		var params = {};
		$("#remote").on("click", "button", function() {
			var ids = [];
			$("#data :checkbox:checked").each(function() {
				var id = $(this).parents("tr").data("row").id;
				ids.push(id);
			});
			if (ids.length == 0) {
				$.alert($.message("remote-select"));
				return false;
			}
			params.ids = ids;
			params.status = $(this).attr("id");

			$.ajax({
				url : url,
				async : false,
				traditional : true,
				data : params,
				success : function(data) {
					if (data.result == "success") {
						$.alert($.message("remote-send"));
					}
				}
			});
		});
	}

	function template() {
		$("#template").modal({
			width : 650,
			top : 100,
			title : $.message("remote-template"),
			buttons : null,
			after : function() {
				clearInterval(timer);
				timer = null;
			}
		});

		$("#data").on("click", ".update", function() {
			var id = $(this).parents("tr").data("row").id;
			$("#template").data("id", id);
			loadTemplate();
			$("#template").modal("open");
		});

		crud.page(null, loadTemplate, $("#template-page"));
		$("#find-template").on("click", function() {
			$("#template-page").page({
				pageNo : 1
			});
			loadTemplate();
		});

		// TODO
		$("#template").on("click", "button", function() {
			var url = "json/Terminal_mergeTemplate";
			var row = $(this).parents("tr").data("row");

			var params = {
				terminalId : row.terminalId,
				templateId : row.templateId
			};

			var clazz = $(this).attr("class");

			if (/del/.test(clazz)) {
				params.status = "waitdelete";
			} else if (/down/.test(clazz)) {
				params.status = "waitdown";
			} else if (/use/.test(clazz)) {
				url = "json/Terminal_useTemplate";
			} else if (/cancel/.test(clazz)) {
				params.status = "canceldown";
			}
			crud.merge(url, params, loadTemplate);
		});

		function loadTemplate() {
			clearInterval(timer);
			loadProgress();
			timer = setInterval(loadProgress, 1 * 1000);
		}

		function loadProgress() {
			var terminalId = $("#template").data("id");
			var templateName = $.trim($("#template-name").val());
			var options = $("#template-page").page("options");

			$.ajax({
				url : "json/Terminal_findTemplate",
				data : {
					terminalId : terminalId,
					templateName : templateName,
					pageNo : options.pageNo,
					pageSize : options.pageSize
				},
				async : false,
				success : function(data) {
					var tbody = $("#template-data");
					tbody.empty();

					var str = "<tr>";
					str += "<td class='name'></td>";
					str += "<td class='status'></td>";
					str += "<td class='used'></td>";
					str += "<td class='operation'></td>";
					str += "</tr>";

					var down = "<button class='btn btn-primary btn-small down'>" + $.message("download") + "</button>";
					var use = "<button class='btn btn-success btn-small use'>" + $.message("enable") + "</button>";
					var del = "<button class='btn btn-danger btn-small del'>" + $.message("del") + "</button>";
					var cancel = "<button class='btn btn-warning btn-small cancel'>" + $.message("cancel") + "</button>";

					$.each(data.list || [], function(index, row) {
						var tr = $(str).data("row", row);
						tr.find(".name").text(row.templateName);

						switch (row.status) {
						case null:
							tr.find(".status").text($.message("remote-undown"));
							tr.find(".operation").html(down);
							break;
						case "hasdown":
							tr.find(".status").text($.message("remote-down"));
							tr.find(".used").html(row.used ? $.message("remote-used") : use);
							row.used || tr.find(".operation").html(del);
							break;
						case "canceldown":
							tr.find(".status").text($.message("remote-revoking"));
							break;
						case "waitdown":
							var div = $("<div class='show'></div>");
							tr.find(".status").html(div);
							tr.find(".operation").html(cancel);

							var percent = row.total > 0 ? parseInt(row.down * 100 / row.total) : 0;
							div.progress({
								width : 120,
								height : 14,
								tip : false,
								value : Math.min(100, percent)
							});
							break;
						case "hasdelete":
							tr.find(".status").text($.message("remote-undown"));
							tr.find(".operation").html(down);
							break;
						case "waitdelete":
							tr.find(".status").text($.message("remote-deleting"));
							break;
						}
						tbody.append(tr);
					});
					crud.page(data, loadTemplate, $("#template-page"));
				}
			});
		}

	}

	function find() {
		var options = $("#page").page("options");
		$.ajax({
			url : "json/Terminal_search",
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
			str += "<td><input type='checkbox'></td>";
			str += "<td class='terminalNo'></td>";
			str += "<td class='location'></td>";
			str += "<td class='type'></td>";
			str += "<td class='editor'></td>";
			str += "</tr>";

			var button = "<button class='btn btn-primary btn-small update'>" + $.message("install") + "</button>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				tr.find(".terminalNo").text(row.terminalNo);
				tr.find(".location").text(row.location);
				if (row.type == "SHOP") {
					tr.find(".type").text($.message("remote-shop"));
				} else {
					tr.find(".type").text($.message("remote-kitchen"));
					tr.find(".editor").html(button);
				}

				$("#data").append(tr);
			});
		}
	}
});
