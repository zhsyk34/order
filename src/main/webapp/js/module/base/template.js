require([ "jquery", "modal", "page", "checkctrl", "crud", "intercept", "tool", "validate" ], function($, modal, page, checkctrl, crud, intercept, tool, validate) {
	checkctrl.table($(".main table"));

	dialog();
	search();
	find();
	remove();

	selectDialog();
	operate();

	function dialog() {
		$("#editor").modal({
			width : 1000,
			top : 30,
			before : function() {
				return merge();
			},
			reset : function() {
				loadDialog();
			}
		});
		$("#editor :radio").on("change", function() {
			viewCtrl();
		});

		$("#add").on("click", function() {
			$("#editor").modal("title", $.message("add"));
			$("#editor").removeData("row");
			loadDialog();
			$("#editor").modal("open");
		});
		$("#data").on("click", ".update", function() {
			$("#editor").modal("title", $.message("mod"));
			var row = $(this).parents("tr").data("row");
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

				var type = row.type ? row.type.toLowerCase() : "";
				$(":radio[name='type'][value='" + type + "']").prop("checked", true);

				var content = row.content ? row.content.toLowerCase() : "";
				$(":radio[name='content'][value='" + content + "']").prop("checked", true);
				$("#rowcount").val(row.rowcount || "");
				$("#colcount").val(row.colcount || "");
				row.effect && $("#effect").val(row.effect)
				$("#interlude").val(row.interlude || "");
			} else {
				$("tr").find(":radio:first").each(function() {
					$(this).prop("checked", true);
				});
			}

			$("#logo,#foodList,#number,#videoList,#pictureList,#marqueeList").each(function() {
				var id = $(this).attr("id");
				var data = row ? row[id] : null;

				$(this).data("data", $.extract(data, null, true));
				var map = $.toMap(data);

				if (/logo|number|videoList|pictureList/.test(id)) {
					var original = $("#editor").data("materials");
					$("#editor").data("materials", $.extend(original, map));
				} else if (/foodList/.test(id)) {
					$("#editor").data("foods", map);
				} else if (/marqueeList/.test(id)) {
					$("#editor").data("marquees", map);
				}
			});

			loadData();
			viewCtrl();
		}

		// 根据type content显示视图
		function viewCtrl() {
			var type = $(":radio[name='type']:checked").val();
			var content = $(":radio[name='content']:checked").val();

			switch (type) {
			case "s01":
				$(".layout").hide();
				$("#logo").show();
				break;
			case "s02":
				$(".layout").show();
				$("#logo").hide();
				break;
			case "e01":
				break;
			}

			switch (content) {
			case "number":
				$("#number").show();
				$("#videoList,.picture").hide();
				break;
			case "video":
				$("#videoList").show();
				$("#number,.picture").hide();
				break;
			case "picture":
				$(".picture").show();
				$("#number,#videoList").hide();
				break;
			}
		}
	}

	function merge() {
		var id = parseInt($("#id").val()) || null;
		var name = $.trim($("#name").val());
		var type = $(":radio[name='type']:checked").val();
		var content = $(":radio[name='content']:checked").val();

		var url = id ? "json/Template_update" : "json/Template_save";

		if (validate.isEmpty(name)) {
			$.alert($.message("name-required"));
			return false;
		}

		var exist = true;
		$.ajax({
			url : "json/Template_exist",
			async : false,
			data : {
				id : id,
				name : name
			},
			success : function(data) {
				exist = data.exist;
			}
		});
		if (exist) {
			$.alert($.message("name-exist"));
			return false;
		}

		var params = {
			id : id,
			name : name,
			type : type,
			content : content
		};

		// foods
		var foods = $("#foodList").data("data");
		if (foods.length == 0) {
			$.alert($.message("template-foods"));
			return false;
		}
		params.foods = foods;

		switch (type) {
		case "s01":
			var logo = $("#logo").data("data");
			if (logo.length == 0) {
				$.alert($.message("template-logo"));
				return false;
			}
			params.logo = logo[0];
			break;
		case "s02":
			var rowcount = parseInt($("#rowcount").val());
			var colcount = parseInt($("#colcount").val());

			if (!validate.isNatural(rowcount) || !validate.isNatural(colcount) || rowcount != colcount) {
				$.alert($.message("template-size"));
				return false;
			}
			params.rowcount = rowcount;
			params.colcount = colcount;
			break;
		case "e01":
			break;
		}

		switch (content) {
		case "number":
			var number = $("#number").data("data");
			if (number.length == 0) {
				$.alert($.message("template-number"));
				return false;
			}
			params.number = number[0];
			break;
		case "video":
			var videos = $("#videoList").data("data");
			if (videos.length == 0) {
				$.alert($.message("template-video"));
				return false;
			}
			params.videos = videos;
			break;
		case "picture":
			var interlude = parseInt($("#interlude").val());
			if (!validate.isNatural(interlude)) {
				$.alert($.message("template-interlude"));
				return false;
			}

			var effect = $("#effect").val();

			var pictures = $("#pictureList").data("data");
			if (pictures.length == 0) {
				$.alert($.message("template-picture"));
				return false;
			}
			params.interlude = interlude;
			params.effect = effect;
			params.pictures = pictures;
			break;
		}

		var marquees = $("#marqueeList").data("data");
		params.marquees = marquees;

		crud.merge(url, params, find);
	}

	function remove() {
		var url = "json/Template_delete";
		$("#data").on("click", ".del", function() {
			var id = parseInt($(this).parents("tr").find(":checkbox").val());
			crud.del(url, id, find);
		});
		$("#del-all").on("click", function() {
			var ids = [];
			$("#data :checkbox:checked").each(function() {
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
		var name = $.trim($("#search-name").val());
		var options = $("#page").page("options");
		$.ajax({
			url : "json/Template_find",
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
			str += "<td class='type'></td>";
			str += "<td><button class='btn btn-warning btn-small update'>" + $.message("mod") + "</button><button class='btn btn-danger btn-small del'>" + $.message("del") + "</button></td>";
			str += "</tr>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				tr.find(".checkbox :checkbox").val(row.id);
				tr.find(".index").text(index + 1);
				tr.find(".name").text(row.name);
				tr.find(".type").text(row.type);
				$("#data").append(tr);
			});
		}
	}

	function selectDialog() {// 素材 餐点 跑马灯选择窗口
		/* 1.dialog */
		// 1.1:material
		$("#material-dialog").modal({
			zIndex : 2020,
			width : 600,
			title : $.message("template-material"),
			buttons : null,
		});
		// 1.2:food
		$("#food-dialog").modal({
			zIndex : 2021,
			width : 800,
			title : $.message("template-food"),
			buttons : null,
		});
		// 1.3:marquee
		$("#marquee-dialog").modal({
			zIndex : 2022,
			width : 700,
			title : $.message("template-marquee"),
			buttons : null,
		});

		/* 2.page search */
		// 2.1:material
		crud.page(null, loadMaterial, $("#material-page"));
		$("#find-material").on("click", function() {
			$("#material-page").page({
				pageNo : 1
			});
			loadMaterial();
		});
		// 2.2:food
		crud.page(null, loadFood, $("#food-page"));
		$("#find-food").on("click", function() {
			$("#food-page").page({
				pageNo : 1
			});
			loadFood();
		});
		// 2.3:marquee
		crud.page(null, loadMarquee, $("#marquee-page"));
		$("#find-marquee").on("click", function() {
			$("#marquee-page").page({
				pageNo : 1
			});
			loadMarquee();
		});

		/* 3.listen */
		// 3.1:material
		$("#logo,#number,#videoList,#pictureList").on("click", "button", function() {
			$("#material-dialog").data("target", $(this).parents("tr").attr("id"));
			loadMaterial();
			$("#material-dialog").modal("open");
		});
		// 3.2:food
		$("#foodList").on("click", "button", function() {
			$("#food-dialog").data("target", "foodList");
			loadFood();
			$("#food-dialog").modal("open");
		});
		// 3.3:marquee
		$("#marqueeList").on("click", "button", function() {
			$("#marquee-dialog").data("target", "marqueeList");
			loadMarquee();
			$("#marquee-dialog").modal("open");
		});

		/* load data */
		function loadFood() {
			var name = $.trim($("#food-name").val());
			var options = $("#food-page").page("options");
			var params = {
				name : name,
				pageNo : options.pageNo,
				pageSize : options.pageSize
			}

			var selected = $("#foodList").data("data");

			var tbody = $("#food-data").empty();
			var str = "<tr>";
			str += "<td><input type='checkbox'></td>";
			str += "<td></td>";
			str += "<td></td>";
			str += "<td></td>";
			str += "<td><img class='inline'></td>";
			str += "<td class='taste'></td>";
			str += "</tr>";

			$.ajax({
				url : "json/Food_find",
				async : false,
				data : params,
				success : function(data) {
					$.each(data.list || [], function(index, food) {
						var tr = $(str).data("row", food);
						tr.find(":checkbox").prop("checked", $.inArray(food.id, selected) > -1);
						tr.find("td").eq(1).text(index + 1);
						tr.find("td").eq(2).text(food.name);
						tr.find("td").eq(3).text(food.price);
						tr.find("img").attr("src", food.path);

						var taste = [];
						$.each(food.tasteList || [], function() {
							taste.push(this.name);
						});
						tr.find(".taste").text(taste.join());
						tbody.append(tr);
					});
					crud.page(data, loadFood, $("#food-page"));
				}
			});
			checkctrl.general($("#food-parent"), $("#food-dialog").find("td :checkbox"));
		}

		function loadMaterial() {
			var target = $("#material-dialog").data("target");

			var multiple = /videoList|pictureList/.test(target);
			$("#material-parent").html(multiple ? "<input type='checkbox'>" : "");
			var input = multiple ? "checkbox" : "radio";

			var name = $.trim($("#material-name").val());
			var options = $("#material-page").page("options");
			var params = {
				name : name,
				type : target == "videoList" ? "video" : "image",
				pageNo : options.pageNo,
				pageSize : options.pageSize
			}

			var selected = $("#" + target).data("data");

			var tbody = $("#material-data").empty();
			var str = "<tr>";
			str += "<td><input type='" + input + "' name='mid'></td>";
			str += "<td></td>";
			str += "<td></td>";
			str += "<td><img class='inline'></td>";
			str += "</tr>";

			$.ajax({
				url : "json/Material_find",
				async : false,
				data : params,
				success : function(data) {
					$.each(data.list || [], function(index, material) {
						var tr = $(str).data("row", material);
						tr.find(":" + input).prop("checked", $.inArray(material.id, selected) > -1);
						tr.find("td").eq(1).text(index + 1);
						tr.find("td").eq(2).text(material.name);
						tr.find("img").attr("src", material.path);
						tbody.append(tr);
					});
					crud.page(data, loadMaterial, $("#material-page"));
				}
			});

			checkctrl.general($("#material-dialog").find("th :checkbox"), $("#material-dialog").find("td :checkbox"));
		}

		function loadMarquee() {
			var name = $.trim($("#marquee-name").val());
			var options = $("#marquee-page").page("options");
			var params = {
				name : name,
				pageNo : options.pageNo,
				pageSize : options.pageSize
			}
			var selected = $("#marqueeList").data("data");

			var tbody = $("#marquee-data").empty();
			var str = "<tr>";
			str += "<td><input type='checkbox'></td>";
			str += "<td></td>";
			str += "<td></td>";
			str += "<td></td>";
			str += "</tr>";

			$.ajax({
				url : "json/Marquee_find",
				async : false,
				data : params,
				success : function(data) {
					$.each(data.list || [], function(index, marquee) {
						var tr = $(str).data("row", marquee);
						tr.find(":checkbox").prop("checked", $.inArray(marquee.id, selected) > -1);
						tr.find("td").eq(1).text(index + 1);
						tr.find("td").eq(2).text(marquee.title);
						tr.find("td").eq(3).text(marquee.content);
						tbody.append(tr);
					});
					crud.page(data, loadMarquee, $("#marquee-page"));
				}
			});
			checkctrl.general($("#marquee-parent"), $("#marquee-dialog").find("td :checkbox"));
		}
	}

	function loadData() {
		var materials = $("#editor").data("materials");
		var foods = $("#editor").data("foods");
		var marquees = $("#editor").data("marquees");

		$("#logo,#foodList,#number,#videoList,#pictureList,#marqueeList").each(function() {
			var thumbnail = $(this).find(".thumbnail").empty();
			var target = $(this).attr("id");

			var data = $(this).data("data");
			if (data.length == 0) {
				return true;
			}

			if (/logo|number|pictureList|videoList/.test(target)) {
				var str = "<div><img><div class='close'></div></div>";
				$.each(data, function(index, id) {
					var div = $(str).data("data", id);
					div.find("img").attr("src", materials[id].path);
					thumbnail.append(div);
				});
			} else if (/foodList/.test(target)) {
				var str = "<div><img><div class='close'></div></div>";
				$.each(data, function(index, id) {
					var div = $(str);
					$(div).data("data", id);
					div.find("img").attr("src", foods[id].path);
					thumbnail.append(div);
				});
			} else if (/marqueeList/.test(target)) {
				var str = "<div><div class='marquee-context'></div><div class='close'></div></div>";
				$.each(data, function(index, id) {
					var div = $(str).data("data", id);
					div.find(".marquee-context").text(marquees[id].title);
					thumbnail.append(div);
				});
			}
		});
	}

	function operate() {
		$(".thumbnail").on("click", ".close", function() {
			var id = $(this).parent().data("data");
			var target = $(this).parents("tr").attr("id");

			var data = $("#" + target).data("data");
			$("#" + target).data("data", data.remove(id));
			loadData();
		});

		$(".dialog-main").on("click", ":checkbox,:radio", function() {
			var dom = $(this).parents(".dialog-main").parent();
			var target = dom.data("target");

			var mapData = [], selected = [], unselected = [];
			dom.find("td :radio,td :checkbox").each(function() {
				var flag = $(this).prop("checked");
				var row = $(this).parents("tr").data("row");

				flag && mapData.push(row);
				flag && selected.push(row.id);
				flag || unselected.push(row.id);
			});

			var map = $.toMap(mapData);

			if (/logo|number|videoList|pictureList/.test(target)) {
				var original = $("#editor").data("materials");
				$("#editor").data("materials", $.extend(original, map));
			} else if (/foodList/.test(target)) {
				var original = $("#editor").data("foods");
				$("#editor").data("foods", $.extend(original, map));
			} else if (/marqueeList/.test(target)) {
				var original = $("#editor").data("marquees");
				$("#editor").data("marquees", $.extend(original, map));
			}

			if (/logo|number/.test(target)) {
				$("#" + target).data("data", selected);
			} else if (/videoList|pictureList|foodList|marqueeList/.test(target)) {
				var data = $("#" + target).data("data");
				$("#" + target).data("data", data.concat(selected).remove(unselected).unique());
			}

			loadData();
		});
	}
});
