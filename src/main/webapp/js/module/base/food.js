require([ "jquery", "modal", "page", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, checkctrl, crud, intercept, validate) {
	checkctrl.list($("#data"));

	dialog();

	loadType();
	loadTaste();
	listenMaterial();

	search();
	find();
	remove();

	// TODO
	function show() {
		$("#data").on("click", ".info", function() {
			var img = $(this).find("img");

			$("#thumbnail img").attr("src", img.attr("src"));

			var width = img.width();
			var height = img.height();

			$("#thumbnail .modal-dialog").css({
				left : "50%",
				top : "50%"
			})
			// $("#thumbnail").fadeIn();
		});
	}

	function dialog() {
		$("#material-dialog").modal({
			top : 100,
			title : $.message("picture-select"),
			zIndex : 2020,
			width : 500,
			buttons : null
		});
		$("#load-material").on("click", function() {
			loadMaterial();
			$("#material-dialog").modal("open");
		});

		//
		$("#editor").modal({
			width : 800,
			top : 80,
			zIndex : 2019,
			before : function() {
				return merge();
			},
			reset : function() {
				loadDialog();
			}
		});

		$("#name").on("change", function() {
			$("#show").find(".name").text($.trim($("#name").val()));
		});
		$("#price").on("change", function() {
			$("#show").find(".price").text($.trim($("#price").val()));
		});

		$("#add").on("click", function() {
			$("#editor").modal("title", $.message("add"));
			$("#editor").removeData("row");
			loadDialog();
			$("#editor").modal("open");
		});

		$("#data").on("click", ".update", function() {
			$("#editor").modal("title", $.message("mod"));
			var row = $(this).parents(".info").data("row");
			$("#editor").data("row", row);
			loadDialog();
			$("#editor").modal("open");
		});

		function loadDialog() {
			$("#editor").modal("clear");

			var row = $("#editor").data("row");
			if (row) {
				$("#id").val(row.id);
				$("#name").val(row.name);
				$("#abbreviation").val(row.abbreviation);
				$("#nickname").val(row.nickname);
				$("#typeId").val(row.typeId);
				$("#price").val(row.price);
				$("#materialId").val(row.materialId);
				$("#introduction").val(row.introduction);

				// taste array
				$.each(row.tasteList || [], function(index, taste) {
					$("#taste").find("li.taste :checkbox[value='" + taste.id + "']").prop("checked", true);
				});
				// style array
				$.each(row.styleList || [], function(index, style) {
					$("#taste").find("li.style :checkbox[value='" + style.id + "']").prop("checked", true);
				});
				// show
				$("#show").find("img").attr({
					"src" : row.path,
					"alt" : row.name
				});
				$("#show").find(".name").text(row.name);
				$("#show").find(".price").text(row.price);
			} else {
				$("#show").find("img").attr({
					"src" : "",
					"alt" : ""
				});
				$("#show").find(".name").text("");
				$("#show").find(".price").text("");
			}
		}
	}

	function merge() {
		var id = parseInt($("#id").val()) || null;
		var name = $.trim($("#name").val());
		var abbreviation = $.trim($("#abbreviation").val());
		var nickname = $.trim($("#nickname").val());
		var typeId = parseInt($("#typeId").val());
		var price = parseFloat($("#price").val());
		var materialId = parseInt($("#materialId").val());
		var introduction = $.trim($("#introduction").val());

		var styleIds = [];
		$("#taste").find("li.style :checkbox:checked").each(function() {
			var styleId = $(this).val();
			styleIds.push(styleId);
		});

		var tasteIds = [];
		$("#taste").find("li.taste :checkbox:checked").each(function() {
			var tasteId = $(this).val();
			tasteIds.push(tasteId);
		});

		var flag = false;
		$("#taste").find("ul").each(function() {
			var parent = $(this).find("li.style :checkbox:checked").length;
			var child = $(this).find("li.taste :checkbox:checked").length;

			if (parent > 0 && child == 0) {
				flag = true;
				return false;
			}
		});

		if (validate.isEmpty(name)) {
			$.alert($.message("name-required"));
			return false;
		}
		if (!validate.isPositive(price, true)) {
			$.alert($.message("price-required"));
			return false;
		}
		if (!validate.isNatural(materialId)) {
			$.alert($.message("food-material"));
			return false;
		}

		if (flag) {
			$.alert($.message("food-taste"));
			return false;
		}

		var params = {
			id : id,
			name : name,
			abbreviation : abbreviation,
			nickname : nickname,
			typeId : typeId,
			price : price,
			materialId : materialId,
			introduction : introduction,
			tasteIds : tasteIds,
			styleIds : styleIds
		};

		var url = id ? "json/Food_update" : "json/Food_save";

		var exist = true;
		$.ajax({
			url : "json/Food_exist",
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
		var url = "json/Food_delete";
		$("#data").on("click", ".del", function() {
			var row = $(this).parents(".info").data("row");
			var id = parseInt(row.id);
			crud.del(url, id, find);
		});
		$("#del-all").on("click", function() {
			var ids = [];
			$('#data :checkbox:checked').each(function() {
				var row = $(this).parents(".info").data("row");
				var id = parseInt(row.id);
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
		$("#search-type").on("change", function() {
			$("#page").page({
				pageNo : 1
			});
			find();
		});
	}

	function find() {
		var name = $.trim($("#search-name").val());
		var typeId = parseInt($("#search-type").val()) || 0;

		var options = $("#page").page("options");

		var params = {
			name : name,
			typeId : typeId,
			pageNo : options.pageNo,
			pageSize : options.pageSize
		};

		$.ajax({
			url : "json/Food_find",
			data : params,
			success : function(data) {
				crud.page(data, find);
				load(data);
			}
		});

		function load(data) {
			$("#data").empty();

			var str = "<div class='info'>";

			str += "<div class='wrap'>";
			str += "<img>";
			str += "<div class='desc'>";
			str += "<label class='name'></label>";
			str += "<label class='price'></label>";
			str += "</div>";
			str += "</div>";

			str += "<div class='operation'>";
			str += "<label class='inline'><input type='checkbox'>" + $.message("select") + "</label>";
			str += "<button class='btn btn-small btn-warning update'>" + $.message("mod") + "</button>";
			str += "<button class='btn btn-small btn-danger del'>" + $.message("del") + "</button>";
			str += "</div>";

			str += "</div>";

			$.each(data.list || [], function(index, row) {
				var div = $(str).data("row", row);
				div.find("img").attr("src", row.path);
				div.find(".name").text(row.name);
				div.find(".price").text(row.price);
				$("#data").append(div);
			});
		}
	}

	function listenMaterial() {
		crud.page(null, loadMaterial, $("#material-page"));

		$("#find-material").on("click", function() {
			$("#material-page").page({
				pageNo : 1
			});
			loadMaterial();
		});

		$("#material-dialog").on("click", ":radio", function() {
			var material = $(this).parents("tr").data("row");
			$("#materialId").val(material.id);
			$("#show").find("img").attr({
				"src" : material.path
			});
		});

	}

	function loadMaterial() {
		var name = $.trim($("#material-name").val());
		var options = $("#material-page").page("options");

		var params = {
			name : name,
			type : "image",
			pageNo : options.pageNo,
			pageSize : options.pageSize
		}

		var materialId = parseInt($("#materialId").val()) || 0;

		var tbody = $("#material-data").empty();

		var str = "<tr>";
		str += "<td><input type='radio' name='materialId'></td>";
		str += "<td class='materialName'></td>";
		str += "<td><img class='inline'></td>";
		str += "</tr>";

		$.ajax({
			url : "json/Material_find",
			async : false,
			data : params,
			success : function(data) {
				var list = data.list || [];
				for (var i = 0, len = list.length; i < len; i++) {
					var material = list[i];
					var tr = $(str).data("row", material);
					tr.find(":radio").val(material.id).prop("checked", material.id == materialId);
					tr.find(".materialName").text(material.name);
					tr.find("img").attr("src", material.path);
					tbody.append(tr);
				}
				crud.page(data, loadMaterial, $("#material-page"));
			}
		});
	}

	function loadType() {
		var select = $("#search-type,#typeId").empty();
		$("#search-type").append("<option value='0' selected>" + $.message("all") + "</option>");

		$.ajax({
			url : "json/Food_findType",
			success : function(data) {
				$.each(data.list || [], function(index, row) {
					var option = $("<option></option>");
					option.val(row.id);
					option.text(row.name);
					select.append(option);
				});
			}
		});
	}

	function loadTaste() {
		var map = {};
		var div = $("#taste").empty();

		var str = "<li><label><input type='checkbox'></label></li>";

		$.ajax({
			url : "json/Food_findTaste",
			success : function(data) {
				$.each(data.list || [], function(index, taste) {
					var key = taste.styleId;
					map[key] = map[key] || [];
					map[key].push(taste);
				});

				$.each(map, function(style, tastes) {
					var ul = $("<ul></ul>");

					var li = $(str).addClass("style");
					li.find("input").val(style).after($.message("food-must"));
					ul.append(li);

					$.each(tastes, function(index, taste) {
						var li = $(str).addClass("taste");
						li.find("input").val(taste.id).after(taste.name);
						ul.append(li);
					});
					div.append(ul);
				});
			}
		});
	}
});
