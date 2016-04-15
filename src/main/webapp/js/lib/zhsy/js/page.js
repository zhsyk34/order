(function($) {
	$.fn.page = function(options) {
		if (typeof options == "string") {
			switch (options) {
			case "options":
				return $.data(this[0], "page").options;
			}
		}
		options = options || {};
		return this.each(function() {
			var state = $.data(this, "page");
			if (state) {
				$.extend(state.options, options);
			} else {
				$.data(this, "page", {
					options : $.extend({}, $.fn.page.defaults, options)
				});
			}
			initHtml(this);
			listener(this);
		});
	};

	$.fn.page.defaults = {
		pageNo : 1,
		pageSize : 10,
		dataCount : 0,
		form : null,
		onChangePage : function(pageNo, pageSize) {
		}
	};

	function initHtml(target) {
		var options = $.data(target, "page").options;
		var pager = $(target).empty().addClass("page");
		var text = $.fn.page.defaults.text;

		var tr = $("<tr></tr>");
		var table = $("<table></table>").append(tr);
		pager.append(table);

		// back
		$("<td class='first'><span class='icon-step-backward'></span></td>").appendTo(tr);
		$("<td class='previous'><span class='icon-backward'></span></td>").appendTo(tr);
		$("<td class='separator'></td>").appendTo(tr);

		// pageNo
		var pageNoStr = "<span>" + $.message("page-nopre") + "</span>" + "<select class='pageNo'></select>" + "<span>" + $.message("page-nosuf") + "</span>";
		$("<td></td>").append(pageNoStr).appendTo(tr);
		$("<td class='separator'></td>").appendTo(tr);

		// forward
		$("<td class='next'><span class='icon-forward'></span></td>").appendTo(tr);
		$("<td class='last'><span class=' icon-step-forward'></span></td>").appendTo(tr);
		$("<td class='separator'></td>").appendTo(tr);

		// pageSize
		var pageSizeStr = "<span>" + $.message("page-sizepre") + "</span>" + "<input class='pageSize'>" + "<span>" + $.message("page-sizesuf") + "</span>";
		$("<td></td>").append(pageSizeStr).appendTo(tr);

		// dataCount
		var dataCountStr = "<span>" + $.message("page-countpre") + "</span>" + "<span class='dataCount'></span>" + "<span>" + $.message("page-countsuf") + "</span>";
		$("<td></td>").append(dataCountStr).appendTo(tr);

		// commit with form
		if (options.form) {
			$(options.form).find("input[name='pageNo']").length || $("input[name='pageNo']").appendTo($(options.form));
			$(options.form).find("input[name='pageSize']").length || $("input[name='pageSize']").appendTo($(options.form));
		}
		initData(target);
		initCss(target);
	}

	function initCss(target) {
		var options = $.data(target, "page").options;
		var pageNo = options.pageNo;
		var pageCount = options.pageCount;

		if (pageNo <= 1) {
			$(".first ,.previous").addClass("disabled");
		} else {
			$(".first ,.previous").removeClass("disabled");
		}

		if (pageNo >= pageCount) {
			$(".next ,.last").addClass("disabled");
		} else {
			$(".next ,.last").removeClass("disabled");
		}
	}
	function initData(target) {
		var options = $.data(target, "page").options;
		var pageNo = options.pageNo;
		var pageSize = options.pageSize;
		var dataCount = options.dataCount;
		var pageCount = Math.ceil(dataCount / pageSize);
		options.pageCount = pageCount;

		$(target).find(".dataCount").text(dataCount);
		$(target).find(".pageCount").text(pageCount);
		$(target).find(".pageSize").val(pageSize);

		if (options.form) {
			$(options.form).find("input[name='pageNo']").val(pageNo);
			$(options.form).find("input[name='pageSize']").val(pageSize);
		}

		var pageNoStr = "";
		if (pageCount == 0) {
			pageNoStr = "<option selected value='" + 1 + "'>" + 1 + "</option>";
		}
		for (var i = 1; i <= pageCount; i++) {
			if (pageNo === i) {
				pageNoStr += "<option selected value='" + i + "'>" + i + "</option>";
			} else {
				pageNoStr += "<option value='" + i + "'>" + i + "</option>";
			}
		}
		$(target).find(".pageNo").html(pageNoStr);
	}
	function listener(target) {
		var options = $.data(target, "page").options;

		// turn pageNo listener
		$(target).find("td").off(".page").on("click.page", function() {
			if ($(this).hasClass("disabled")) {
				return;
			}
			if ($(this).hasClass("first")) {
				options.pageNo = 1;
			} else if ($(this).hasClass("previous")) {
				options.pageNo = options.pageNo <= 1 ? 1 : options.pageNo - 1;
			} else if ($(this).hasClass("next")) {
				options.pageNo = options.pageNo < options.pageCount ? options.pageNo + 1 : options.pageCount;
			} else if ($(this).hasClass("last")) {
				options.pageNo = options.pageCount;
			} else {
				return;
			}
			initCss(target);
			initData(target);
			options.onChangePage.call(this, options.pageNo, options.pageSize);
		});

		// change pageNo listener
		$(target).find(".pageSize").off(".page").on("keydown.page", function(e) {
			if (e.keyCode == 13) {
				options.pageNo = 1;
				options.pageSize = parseInt($(this).val()) || options.pageSize;
				initCss(target);
				initData(target);
				options.onChangePage.call(this, options.pageNo, options.pageSize);
			}
		});
		// pageNo listener
		$(target).find(".pageNo").off(".page").on("change.page", function(e) {
			options.pageNo = parseInt($(this).val());
			initCss(target);
			initData(target);
			options.onChangePage.call(this, options.pageNo, options.pageSize);
		});
	}
})(jQuery);
