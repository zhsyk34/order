require([ "jquery", "modal", "page", "form", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, form, checkctrl, crud, intercept, validate) {
	checkctrl.list($("#data"));
	dialog();
	search();
	find();
	remove();
	preview();

	function preview() {
		$("#data").on("click", ".info img", function() {
			var row = $(this).parent().data("row");
			var type = row.type ? row.type.toLowerCase() : "";

			var image = $("#preview").find("img");
			var video = $("#preview").find("object");
			switch (type) {
			case "image":
				$("#flash").val("");
				video.hide();
				image.attr({
					src : row.path,
					alt : row.name
				}).show();
				break;
			case "video":
				image.attr({
					src : "",
					alt : ""
				}).hide();
				var str = "xml={vcastr}";
				str += "{channel}{item}{source}[url]{/source}{/item}{/channel}";
				str += "{config}{isRepeat}true{/isRepeat}{isChangeProgram}false{/isChangeProgram}{contralPanelAlpha}1{/contralPanelAlpha}{controlPanelMode}none{/controlPanelMode}{/config}";
				str += "{/vcastr}";
				var url = row.path.replace(/\.jpg$/, ".flv");
				$("#flash").val(str.replace(/\[url\]/, "../../" + url));// TODO
				video.show();
				break;
			}
			$("#preview").parents(".modal").find(".modal-close").css("top", "2px");
			$("#preview").modal("open");
		});
	}

	function update() {
		var id = parseInt($("#id").val());
		var name = $.trim($("#name").val());

		if (validate.isEmpty(name)) {
			$.alert($.message("name-required"));
			return false;
		}

		var params = {
			id : id,
			name : name
		};

		var exist = true;
		$.ajax({
			url : "json/Material_exist",
			traditional : true,
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
		crud.merge("json/Material_update", params, find);
	}

	function dialog() {
		// update
		$("#editor").modal({
			width : 320,
			title : $.message("mod"),
			top : 100,
			before : function() {
				return update();
			}
		});
		$("#data").on("click", ".update", function() {
			var row = $(this).parents(".info").data("row");
			$("#id").val(row.id);
			$("#name").val(row.name);
			$("#editor").modal("open");
		});

		// upload
		$("#upload").modal({
			width : 600,
			title : $.message("material-upload"),
			top : 100,
			before : function() {
				return upload();
			},
			reset : function() {
				$(".clean").trigger("click");
			}
		});
		$("#add").on("click", function() {
			$(".follow").remove();
			$(".clean").trigger("click");
			$("#upload").modal("open");
		});
		$("#upload").on("click", ".clean", function() {
			var tr = $(this).parents("tr");
			tr.find("input").val("");
			tr.find(".path-info").text("");
			resetFileInput(tr);
		});
		$("#append").on("click", function() {
			var tr = "<tr class='follow'>";
			tr += "<td><label class='btn btn-default btn-small file'>" + $.message("browse") + "...<input type='file' name='upload'></label><span class='path-info inline'></span></td>";
			tr += "<td><input class='text' name='nameList'></td>";
			tr += "<th><button class='btn btn-info btn-small clean' type='button'>" + $.message("reset") + "</button>";
			tr += "<button class='btn btn-danger btn-small remove' type='button'>" + $.message("del") + "</button>";
			tr += "</th></tr>";
			$("#upload tbody").append(tr);
		});
		// remove
		$("#upload").on("click", ".remove", function() {
			$(this).parents("tr").remove();
		});
		// onchange
		$("#upload").on("change", "input[name='upload']", function() {
			var path = $(this).val();
			var name = path.substring(path.lastIndexOf("\\") + 1, path.lastIndexOf("."));
			$(this).parents("td").find(".path-info").text(path);
			$(this).parents("tr").find("input[name=nameList]").val(name);
		});
		// preview
		$("#preview").modal({
			buttons : null,
			title : null
		});

		// file-input reset
		function resetFileInput(target) {
			$(target).find("input[type='file']").each(function() {
				var file = $(this);
				file.after(file.clone().val(""));
				file.remove();
			});
		}
	}

	function upload() {
		var imageType = /\S+\.(jpg|jpeg|bmp|png|gif)$/i;
		var videoType = /\S+\.(flv|asx|asf|mpg|wmv|3gp|mp4|mov|avi|wmv9|rm|rmvb|vob)$/i;
		// 上传内容 类型
		var files = $("input[name='upload']");
		for (var i = 0, len = files.length; i < len; i++) {
			var file = files.eq(i);
			var path = file.val();
			if (validate.isEmpty(path)) {
				$.alert($.message("material-file"));
				return false;
			}
			if (!imageType.test(path) && !videoType.test(path)) {
				$.alert($.message("material-unsupported"));
				return false;
			}
		}

		// 素材名称
		var nameList = [];
		var names = $("input[name='nameList']");
		for (var i = 0, len = names.length; i < len; i++) {
			var name = $.trim(names.eq(i).val());
			if (validate.isEmpty(name)) {
				$.alert($.message("name-required"));
				return false;
			}
			nameList.push(name);
		}

		// 重名检测
		var exist = true, existName = "";
		$.ajax({
			url : "json/Material_exist",
			traditional : true,
			async : false,
			data : {
				nameList : nameList
			},
			success : function(data) {
				exist = data.exist;
				existName = data.name;
			}
		});
		if (exist) {
			$.alert($.message("material-name") + " [" + existName + "] " + $.message("exist"));
			return false;
		}

		$("#upload form").ajaxSubmit({
			async : false,
			success : function(data) {
				find();
				if (data.result == "upload") {
					$.alert($.message("crud-upload"));
				} else if (data.result == "error") {
					$.alert($.message("crud-error"));
				}
			}
		});

	}

	function remove() {
		var url = "json/Material_delete";
		$("#data").on("click", ".del", function() {
			var id = parseInt($(this).parents(".info").data("row").id);
			crud.del(url, id, find);
		});
		$("#del-all").on("click", function() {
			var ids = [];
			$('#data :checkbox:checked').each(function() {
				var id = parseInt($(this).parents(".info").data("row").id);
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
		$("header :radio").on("change", function() {
			$("#page").page({
				pageNo : 1
			});
			find();
		});
	}

	function find() {
		var name = $.trim($("#search-name").val());
		var type = $("#search-type :radio:checked").val();
		var options = $("#page").page("options");

		var query = {
			name : name,
			type : type,
			pageNo : options.pageNo,
			pageSize : options.pageSize
		};

		$.ajax({
			url : "json/Material_find",
			data : query,
			success : function(data) {
				load(data);
				crud.page(data, find);
			}
		});

		function load(data) {
			$("#data").empty();

			var str = "<div class='info'>";
			str += "<h4></h4>";
			str += "<img>";
			str += "<div class='operation'>";
			str += "<label class='inline'><input type='checkbox'>" + $.message("select") + "</label>";
			str += "<button class='btn btn-small btn-warning update'>" + $.message("mod") + "</button>";
			str += "<button class='btn btn-small btn-danger del'>" + $.message("del") + "</button>";
			str += "</div>";
			str += "</div>";

			$.each(data.list || [], function(index, row) {
				var div = $(str).data("row", row);
				div.find("h4").text(row.name);
				div.find("img").attr({
					"src" : row.path,
					"alt" : row.name
				});
				$("#data").append(div);
			});
		}
	}
});
