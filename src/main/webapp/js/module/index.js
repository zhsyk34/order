require([ "jquery", "modal", "page", "checkctrl", "crud", "intercept", "tool", "validate" ], function($, modal, page, checkctrl, crud, intercept, tool, validate) {
	forbidSelect();
	logout();
	accordion();
	footer();

	function accordion() {
		$(".accordion").on("click", "p", function() {
			$(this).next().slideToggle(300);
		});
		$(".accordion").on("click", "li", function() {
			var link = $(this).find("a").attr("href");
			$("#content iframe").attr("src", link);

			$(".accordion li").removeClass("active");
			$(this).addClass("active");
			return false;
		});
	}

	function logout() {
		$("#logout").on("click", function() {
			$.post("json/User_logout");
			window.location.href = basePath + "jsp/logon.jsp";
		});
	}

	function forbidSelect() {
		$(document).on("selectstart", function() {
			return false;
		});
	}

	function footer() {
		date();
		setInterval(date, 1000);
		function date() {
			var current = new Date();

			var week = " 星期" + "一二三四五六七".charAt(current.getDay());
			var date = current.format("yyyy-MM-dd HH:mm:ss");
			var result = date.slice(0, 11) + week + date.slice(-9);
			$("#date").html(result);
		}
	}
});