require([ "jquery", "modal", "page", "slider", "spectrum", "checkctrl", "crud", "intercept", "validate" ], function($, modal, page, slider, spectrum, checkctrl, crud, intercept, validate) {

	var timer = null;// TODO
	checkctrl.table($(".main table"));

	dialog();
	search();
	find();
	remove();

	function dialog() {
		$("#editor").modal({
			width : 800,
			top : 40,
			before : function() {
				return merge();
			},
			reset : function() {
				loadDialog();
				preview();
			}
		});

		function init(speed, size, color, background) {
			$("#speed").slider({
				value : speed || 50,
				min : 1,
				onStop : function() {
					preview();
				}
			});

			$("#size").slider({
				value : size || 20,
				min : 10,
				max : 80,
				onStop : function() {
					preview();
				}
			});

			$("#color").val(color || "#000");
			$("#color").spectrum({
				color : color || "#000",
				change : function(c) {
					$(this).val(c.toHexString());
					preview();
				}
			});

			$("#background").val(background || "#fff");
			$("#background").spectrum({
				color : background || "#fff",
				change : function(c) {
					$(this).val(c.toHexString());
					preview();
				}
			});
		}

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
				$("#title").val(row.title);
				$("#content").val(row.content);

				var dir = row.direction ? row.direction.toLowerCase() : "";
				$("#direction :radio[value=" + dir + "]").prop("checked", true);

				$("#font").val(row.font);
				init(row.speed, row.size, row.color, row.background);
			} else {
				$("#editor").modal("clear");
				init();
			}
			preview();
		}
		// listener
		$("#content,#direction :radio,#font").on("change", function() {
			preview();
		});
	}

	function getParam() {
		var params = {};
		params.id = parseInt($("#id").val()) || null;
		params.title = $.trim($("#title").val());
		params.content = $.trim($("#content").val());
		params.direction = $("#direction :radio:checked").val();
		params.speed = parseInt($("#speed").slider("value"));
		params.font = $("#font").val();
		params.size = parseInt($("#size").slider("value"));
		params.color = $("#color").val();
		params.background = $("#background").val();
		return params;
	}

	function preview() {
		clearInterval(timer);// TODO
		timer = null;

		var params = getParam();
		if (!params.content) {
			$("#preview").text("");
			$("#wrap").css("background", "#fff");
			return;
		}

		$("#wrap").css("background", params.background);

		$("#preview").text(params.content);
		$("#preview").css({
			"color" : params.color,
			"font-family" : params.font,// TODO
			"font-size" : params.size + "px"
		});

		var dir = params.direction == "left";
		var width = $("#wrap").width(), left = dir ? width : 0;
		function move() {// TODO
			if (dir) {
				left -= 20;
				if (left < 0) {
					left = width;
				}
			} else {
				left += 20;
				if (left > width) {
					left = 0;
				}
			}
			$("#preview").css("left", left);
		}
		timer = setInterval(move, 150 - params.speed);
	}

	function merge() {
		var params = getParam();

		if (validate.isEmpty(params.title)) {
			$.alert($.message("title-required"));
			return false;
		}
		if (validate.isEmpty(params.content)) {
			$.alert($.message("content-required"));
			return false;
		}

		var exist = true;
		$.ajax({
			url : "json/Marquee_exist",
			async : false,
			data : {
				id : params.id,
				title : params.title
			},
			success : function(data) {
				exist = data.exist;
			}
		});
		if (exist) {
			$.alert($.message("title-exist"));
			return false;
		}

		var url = params.id ? "json/Marquee_update" : "json/Marquee_save";
		crud.merge(url, params, find);
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
	function remove() {
		var url = "json/Marquee_delete";
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
		var title = $.trim($("#search-title").val());
		var content = $.trim($("#search-content").val());
		var options = $("#page").page("options");

		$.ajax({
			url : "json/Marquee_find",
			data : {
				title : title,
				content : content,
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

			var str = "<tr>"
			str += "<td class='checkbox'><input type='checkbox'></td>";
			str += "<td class='index'></td>";
			str += "<td class='title'></td>";
			str += "<td class='content'></td>";
			str += "<td class='direction'></td>";
			str += "<td class='speed'></td>";
			str += "<td class='font'></td>";
			str += "<td class='size'></td>";
			str += "<td class='color'><div></div></td>";
			str += "<td class='background'><div></div></td>";
			str += "<td><button class='btn btn-warning btn-small update'>" + $.message("mod") + "</button><button class='btn btn-danger btn-small del'>" + $.message("del") + "</button></td>";
			str += "</tr>";

			$.each(data.list || [], function(index, row) {
				var tr = $(str).data("row", row);
				tr.find(".checkbox :checkbox").val(row.id);
				tr.find(".index").text(index + 1);
				tr.find(".title").text(row.title);
				tr.find(".content").text(row.content);
				tr.find(".direction").text(validate.equalsIgnoreCase("left", row.direction) ? "←" : "→");
				tr.find(".speed").text(row.speed);
				tr.find(".font").text(row.font);
				tr.find(".size").text(row.size);
				tr.find(".color div").css("background", row.color);
				tr.find(".background div").css("background", row.background);

				$("#data").append(tr);
			});
		}
	}
});
